package elect5619.gatewayservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import elect5619.gatewayservice.domain.User;
import elect5619.gatewayservice.dto.UserDTO;
import elect5619.gatewayservice.exception.AuthenticationException;
import elect5619.gatewayservice.exception.InvalidTokenException;
import elect5619.gatewayservice.exception.SessionExpiredException;
import elect5619.gatewayservice.repository.UserRepository;
import elect5619.gatewayservice.util.JWTUtil;

/**
 * 
 * @author ahmed
 */
@Service
public class SecurityService implements UserDetailsService {

	private static final String SIGN_KEY;
	static {
		// Benefot of this scheme is that if the service is down all token are
		// invalidated
		// SIGN_KEY = new
		// StringBuilder("GATEWAY-SERVICE").append(Math.random()).toString();

		SIGN_KEY = new StringBuilder("GATEWAY-SERVICE").toString();

	}

	@Autowired
	private UserRepository userRepository;

	public User authenticateUser(String email, String sha512) throws AuthenticationException {
		User user = userRepository.findFirstByUsernameAndPassword(email, sha512)
				.orElseThrow(() -> new AuthenticationException());
		userRepository.save(user.setAssignedToken(JWTUtil.buildToken(user.getUsername(), SIGN_KEY)));
		return user;

	}

	public User authenticateAdmin(String email, String sha512) throws AuthenticationException {
		// admin is also a user in system
		// this method is done just to wrap nicely
		return authenticateUser(email, sha512);
	}

	public User validateRawToken(String token) throws InvalidTokenException, SessionExpiredException {
		if (token.startsWith("Bearer "))
			token = token.replaceFirst("Bearer", "").trim();
		JWTUtil.validateToken(token, SIGN_KEY);
		return userRepository.findFirstByAssignedToken(token).orElseThrow(() -> new SessionExpiredException());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findFirstByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("username:" + username));
	}

	@RabbitListener(queues = "${gateway.rabbitmq.createQueue}")
	public void processCreatedUsers(final UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(dto, User.class);
		userRepository.save(user);
	}

	@RabbitListener(queues = "${gateway.rabbitmq.changePassQueue}")
	public void processUpdatePassword(final UserDTO dto) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(dto, User.class);
		userRepository.save(user);
	}

}
