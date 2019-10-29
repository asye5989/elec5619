package elect5619.gatewayservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elect5619.gatewayservice.domain.User;
import elect5619.gatewayservice.dto.JWTResonseDTO;
import elect5619.gatewayservice.dto.LoginRequest;
import elect5619.gatewayservice.exception.AuthenticationException;
import elect5619.gatewayservice.service.SecurityService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SecurityService securityService;

	@PostMapping("/fromClient")
	private ResponseEntity<JWTResonseDTO> loginClient(@RequestBody LoginRequest loginRequest) {
		try {
			User user = securityService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
			// RestTemplate restTemplate = new RestTemplate();
			return ResponseEntity.ok(new JWTResonseDTO().setToken(user.getAssignedToken()));

		} catch (AuthenticationException e) {
			logger.warn(" Authentication attempt failed for username/email: " + loginRequest.getUsername());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/fromAdmin")
	private ResponseEntity<JWTResonseDTO> loginAdmin(@RequestBody LoginRequest loginRequest) {
		try {
			User user = securityService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
			if (!user.isAdmin()) {
				logger.warn("TOKEN VALID  - NOT AUTHORIZED FOR ADMIN SITE ACCESS "+user.getUsername());
				new AuthenticationException();
			}
			// RestTemplate restTemplate = new RestTemplate();
			return ResponseEntity.ok(new JWTResonseDTO().setToken(user.getAssignedToken()));
		} catch (AuthenticationException e) {
			logger.warn(" Authentication attempt failed for username/email: " + loginRequest.getUsername());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
