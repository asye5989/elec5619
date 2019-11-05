package elec5619.clientuiservice.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import elec5619.clientuiservice.domain.Match;
import elec5619.clientuiservice.domain.User;
import elec5619.clientuiservice.dto.MatchDTO;
import elec5619.clientuiservice.dto.UserDTO;
import elec5619.clientuiservice.repository.MatchRepository;
import elec5619.clientuiservice.repository.UserRepository;
import elec5619.common.domain.Gender;

@Service
public class UserMessageProcessingService {

	private static final Logger log = LoggerFactory.getLogger(UserMessageProcessingService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private EmailService emailService;

	@Value("${psychology.rabbitmq.matchscore.key}")
	private String psychologyMatchScoreKey;

	@Value("${psychology.rabbitmq.exchange}")
	private String psychologyExchnage;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	ModelMapper mapper = new ModelMapper();

	public UserMessageProcessingService() {
		mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
	}

	@RabbitListener(queues = "${user.rabbitmq.queue.create}")
	public void processCreatedUser(final UserDTO dto) {

		User user = mapper.map(dto, User.class);
		try {
			userRepository.save(user);
			log.info("processed user with id:" + user.getId() + " username" + user.getUsername());
			log.debug("  created user :" + user.toString());
		} catch (Exception e) {
			log.error("Error saving user : " + e.getLocalizedMessage() + " -- DTO:" + dto + " EMAIL  TO ADMIN");
			emailService.sendEmailToAdmin("Failure creating user", e.toString() + " DTO:" + dto.toString());
		}

	}

	@RabbitListener(queues = "${user.rabbitmq.queue.update}")
	public void processUpdatedUser(final UserDTO dto) {

		User user = userRepository.findById(dto.getId()).get();
		mapper.map(dto, user);
		userRepository.save(user);

		// check if the user match also has personality code & also has a match that has
		// valid match then update score

		if (user.getPersonalityTypeKey() != null) {
			try {
				if (user.getGender() == Gender.MALE) {
					Match match = matchRepository.getMalePartnerMatch(user.getId());
					if (match == null)
						throw new Exception("No match available");
					User female = match.getFemalePartner();
					if (female.getPersonalityTypeKey() != null) {
						// now score it
						MatchDTO matchDTO = new MatchDTO().setType1(user.getPersonalityTypeKey())
								.setType2(female.getPersonalityTypeKey());
						rabbitTemplate.convertAndSend(psychologyExchnage, psychologyMatchScoreKey, matchDTO);
						log.info("submitted scoring request from psychology service");
					}

				} else {
					Match match = matchRepository.getFemalePartnerMatch(user.getId());
					if (match == null)
						throw new Exception("No match available");
					User male = match.getMalePartner();
					if (male.getPersonalityTypeKey() != null) {
						// now score it
						MatchDTO matchDTO = new MatchDTO().setType2(user.getPersonalityTypeKey())
								.setType1(male.getPersonalityTypeKey());
						rabbitTemplate.convertAndSend(psychologyExchnage, psychologyMatchScoreKey, matchDTO);
						log.info("submitted scoring request from psychology service");
					}

				}
			} catch (Exception e) {
				log.info(" no match found for user:" + user.toString());
			}
		}

		log.info("updated user :" + user.toString());
	}

	@RabbitListener(queues = "${user.rabbitmq.queue.delete}")
	public void processDeletedUsers(final UserDTO dto) {
		User user = mapper.map(dto, User.class);
		userRepository.delete(user);
		log.info("deleted user with id:" + user.getId() + " username" + user.getUsername());
	}

	@RabbitListener(queues = "${user.rabbitmq.queue.match}")
	public void processMatchedUsers(final UserDTO dto) {
		try {
			User user = mapper.map(dto, User.class);

			user = userRepository.findById(dto.getId()).get();
			User user2 = userRepository.findById(dto.getMatchId()).get();
			// create new match

			if (user.getGender() == user2.getGender()) {
				throw new Exception("Un Supported operation: Psychological tool is not compatible ");
			}

			// make new match
			Match match = new Match();
			// delete old matches if any& make new
			if (user.getGender() == Gender.MALE) {
				matchRepository.deleteMalePartnerMatch(user.getId());
				match.setMalePartner(user);
				match.setFemalePartner(user2);
			} else {
				matchRepository.deleteFemalePartnerMatch(user.getId());
				match.setMalePartner(user2);
				match.setFemalePartner(user);
			}
			// now the match is made , save it
			matchRepository.save(match);
			log.debug("Match" + match.toString() + " made");
			log.info("Match processed " + match.toString());
		} catch (Exception e) {
			log.error("Error saving user : " + e.getLocalizedMessage() + " -- DTO:" + dto + " EMAIL  TO ADMIN");
			emailService.sendEmailToAdmin("Failure creating match", e.toString() + " DTO:" + dto.toString());
		}

	}

	@RabbitListener(queues = "${user.rabbitmq.routingkey.match.delete}")
	public void processDeletedMatch(final UserDTO dto) {

		User user = mapper.map(dto, User.class);
		if (user.getGender() == Gender.MALE) {
			matchRepository.deleteMalePartnerMatch(user.getId());
		} else {
			matchRepository.deleteFemalePartnerMatch(user.getId());
		}
		log.info("Match deletion  processed for " + user.toString());
	}

	@RabbitListener(queues = "${user.rabbitmq.routingkey.match.update}")
	public void processUpdateMatch(final MatchDTO dto) {
		try {
			Match match = matchRepository.findById(dto.getMatchId()).get();
			match.setMatchScore(dto.getScore());
			matchRepository.save(match);
			log.info("Match processed");
		} catch (Exception e) {
			log.error("Unable to process Match Update");
		}
	}

}