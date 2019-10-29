package elec5619.clientuiservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import elec5619.clientuiservice.domain.Match;
import elec5619.clientuiservice.domain.User;
import elec5619.clientuiservice.dto.UserDTO;
import elec5619.clientuiservice.service.UserService;
import elec5619.common.domain.Gender;

/**
 * 
 * @author ahmed
 *
 *  This class has only two synchronous endpoints
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;

	// user/{id}
	@GetMapping("/user/{id}")
	public void getUser(@RequestParam Long id) {
		User user = userService.getUser(id);
		Match match = userService.getMatchForPartner(id);
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		if (userDTO.getGender() == Gender.MALE) {
			userDTO.setPartner(match.getFemalePartner());
		} else {
			userDTO.setPartner(match.getMalePartner());
		}

		ResponseEntity.ok(userDTO);
	}

	@GetMapping("/user/all")
	public void getAllUsers() {
		ResponseEntity.ok(userService.getAllUsers());
	}

	
	
	
}
