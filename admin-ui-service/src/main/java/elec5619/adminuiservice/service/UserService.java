package elec5619.adminuiservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import elec5619.adminuiservice.domain.User;
import elec5619.adminuiservice.dto.UserDTO;
import elec5619.adminuiservice.exception.InvalidOperationException;

@Service
public class UserService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${user.rabbitmq.exchange}")
	private String userExchange;

	@Value("${gateway.rabbitmq.exchange}")
	private String gatewayExchange;

	@Value("${user.rabbitmq.routingkey.create}")
	private String creatKey;

	
	@Value("${user.rabbitmq.routingkey.update}")
	private String updateKey;

	@Value("${user.rabbitmq.routingkey.delete}")
	private String deleteKey;

	@Value("${user.rabbitmq.routingkey.match}")
	private String matchKey;

	@Value("${user.rabbitmq.routingkey.match.delete}")
	private String deleteMatcKey;

	@Value("${gateway.rabbitmq.routingkey.create}")
	private String gateWayUserCreateKey;

	@Value("${gateway.rabbitmq.routingkey.changePass}")
	private String gatewaychangePassKey;

	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	private ModelMapper mapper = new ModelMapper();

	public void createUser(User user, String password) throws InvalidOperationException {
		if (user.getFullname() == null || user.getUsername() == null || user.getGender() == null || password == null)
			throw new InvalidOperationException();

		// inform user sevices
		rabbitTemplate.convertAndSend(userExchange, creatKey, mapper.map(user, UserDTO.class));

		// inform gateway
		rabbitTemplate.convertAndSend(gatewayExchange, gateWayUserCreateKey,
				mapper.map(user, UserDTO.class).setPassword(password));

		log.info("user profile created via user service");
	}

	public void updateUser(User user) {
		rabbitTemplate.convertAndSend(userExchange, updateKey, mapper.map(user, UserDTO.class));
		log.info("user profile update via user service");
	}

	public User getUser(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		// CLIENT Service from eureka
		UserDTO dto = restTemplate.exchange("http://CLIENT-UI-SERVICE/user/{id}", HttpMethod.GET, null,
				new ParameterizedTypeReference<UserDTO>() {
				}, id).getBody();

		return mapper.map(dto, User.class);
	}

	public void deleteUser(User user) throws InvalidOperationException {
		rabbitTemplate.convertAndSend(userExchange, deleteKey, mapper.map(user, UserDTO.class));
		log.info("user profile deleted via user service");
	}

	public void makeMatch(Long user1, Long user2) {
		rabbitTemplate.convertAndSend(userExchange, matchKey, new UserDTO().setMatchId(user2).setId(user1));
		log.info("user profile makeMatch via user service");
	}

	public void removeMatch(Long id) {
		rabbitTemplate.convertAndSend(userExchange, deleteMatcKey, new UserDTO().setId(id));
		log.info("user profile removeMatch via user service");
	}

	public List<User> getAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		// CLIENT Service from eureka
		List<User> list = restTemplate.exchange("http://CLIENT-UI-SERVICE/user/all", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<UserDTO>>() {
				}).getBody().stream().map((dto) -> mapper.map(dto, User.class))
				.collect(Collectors.toCollection(ArrayList::new));

		return list;

	}

	public void changePass(User dto) {
		rabbitTemplate.convertAndSend(userExchange, matchKey, dto);
		log.info("user profile makeMatch via user service");
	}
	

}
