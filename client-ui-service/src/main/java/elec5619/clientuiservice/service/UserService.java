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

	@Autowired
	UserRepository userRepository;

	@Autowired
	MatchRepository matchRepository;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

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

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	 

}