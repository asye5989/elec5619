package elec5619.clientuiservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import elec5619.clientuiservice.domain.Match;
import elec5619.clientuiservice.domain.User;
import elec5619.clientuiservice.dto.UserDTO;
import elec5619.clientuiservice.repository.MatchRepository;
import elec5619.clientuiservice.repository.UserRepository;
import elec5619.common.domain.Gender;

@Service
public class UserService {

	//
	private final static int EVERY_10_MINUTES=600000;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	MatchRepository matchRepository;

	
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@RabbitListener(queues = "${user.rabbitmq.queue.create}")
	public void processCreatedUser(final UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(dto, User.class);
		userRepository.save(user);
		log.info("processed user with id:" + user.getId() + " username" + user.getUsername());
		log.debug("  created user :" + user.toString());
	}

	@RabbitListener(queues = "${user.rabbitmq.queue.update}")
	public void processUpdatedUser(final UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(dto, User.class);
		userRepository.save(user);
		log.info("processed user with id:" + user.getId() + " username" + user.getUsername());
		log.debug("updated user :" + user.toString());
	}

	@RabbitListener(queues = "${user.rabbitmq.queue.delete}")
	public void processDeletedUsers(final UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(dto, User.class);
		userRepository.delete(user);
		log.info("processed user with id:" + user.getId() + " username" + user.getUsername());
	}

	@RabbitListener(queues = "${user.rabbitmq.queue.match}")
	public void processMatchedUsers(final UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(dto, User.class);
		User user2 = userRepository.findById(dto.getMatchId()).get();
		// create new match

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
		
	}

	@RabbitListener(queues = "${user.rabbitmq.routingkey.match.delete}")
	public void processDeletedMatch(final UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(dto, User.class);
		if (user.getGender() == Gender.MALE) {
			matchRepository.deleteMalePartnerMatch(user.getId());
		} else {
			matchRepository.deleteFemalePartnerMatch(user.getId());
		}
		log.info("Match deletion  processed for " + user.toString());
	}

	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

	public Match getMatchForPartner(Long id) {
		User user = getUser(id);

		if (user.getGender() == Gender.MALE) {
			return matchRepository.getMalePartnerMatch(user.getId());
		} else {
			return matchRepository.getFemalePartnerMatch(user.getId());
		}
	}
	

	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	
	@Scheduled(fixedRate = EVERY_10_MINUTES)
	public void scheduleFixedRateTask() {
		//TODO 
	    // check in match if any match score is negative
		List<Match> list =matchRepository.findUnScoredMatches();
		
		// for each match not score checjk if persoalitytype codes available fo both users
		
		// if yes then get the score from psychology service 
	}

}