package elec5619.adminuiservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import elec5619.adminuiservice.domain.User;
import elec5619.adminuiservice.dto.UserDTO;
import elec5619.adminuiservice.exception.InvalidOperationException;
import elec5619.adminuiservice.service.UserService;

@RestController
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public void createUser(@RequestBody UserDTO dto) {
		ModelMapper modelMapper = new ModelMapper();

		// map dto to domain model
		User user = modelMapper.map(dto, User.class);
		try {
			userService.createUser(user,dto.getPassword());
			ResponseEntity.accepted();
		} catch (InvalidOperationException e) {
			log.warn("Invalid user profile for creation aborted ");
			ResponseEntity.badRequest();
		}

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@RequestParam Long id) {
		try {
			User user = userService.getUser(id);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/user/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return ResponseEntity.ok(list);

	}

	@PutMapping("/user/{id}")
	public void updateUser(@RequestBody UserDTO dto,@PathVariable(value = "id") Long id) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(dto, User.class);
		user.setId(id);
		userService.updateUser(user);
		ResponseEntity.accepted();
	}

	@PutMapping("/user/make-match")
	public void makeMatch(@RequestParam(value = "user1") Long user1, @RequestParam(value = "user2") Long user2) {
		userService.makeMatch(user1, user2);
		ResponseEntity.accepted();

	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable(value = "id") Long id) {		 
		try {
			userService.deleteUser(id);
			ResponseEntity.accepted();
		} catch (InvalidOperationException e) {
			log.error(e.getLocalizedMessage());
			ResponseEntity.badRequest();
		}
	}
	
	
	@DeleteMapping("/user/remove-match/{id}")
	public void deleteUserMatch(@PathVariable(value = "id") Long id) {
		User user = new User();
		user.setId(id);// only id is required in DTO after that		 
			userService.removeMatch(id);
			ResponseEntity.accepted(); 
	}

	@PostMapping("/user/password-change")
	public void updateUserPass(@RequestBody UserDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(dto, User.class); 
		userService.changePass(user);
		ResponseEntity.accepted();
	}
}