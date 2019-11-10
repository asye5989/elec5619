package elec5619.psychologyservice.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import elec5619.psychologyservice.domain.MBITQuestion;
import elec5619.psychologyservice.domain.MBITResult;
import elec5619.psychologyservice.domain.MBTIResponse;
import elec5619.psychologyservice.dto.UserDTO;
import elec5619.psychologyservice.service.PsychologyService;

@RestController
public class PsychologyController {

	@Autowired
	private PsychologyService psychologyService;

	Logger log = org.slf4j.LoggerFactory.getLogger(PsychologyController.class);

	private ModelMapper mapper = new ModelMapper();

	@GetMapping("/presentMBTI")
	public ResponseEntity<List<MBITQuestion>> presentMBITQuestions() {// simple presenttion
	 return	ResponseEntity.ok(psychologyService.getMBITQuestionsList());
	}

	@PostMapping("/processAnswers")
	public void processAnswer(List<MBTIResponse> answerList, UserDTO user) {
		try {
			psychologyService.processAnswers(answerList, mapper.map(user, UserDTO.class));
			ResponseEntity.accepted();
		} catch (Exception e) {
			log.warn("Unable to process answers :" + user.toString() + " Exception:" + e.toString());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// This will return HTML for the personalitytype using thyme leaf
	@GetMapping("/personalityPortrait/{key}")
	public String getPersonalityType(@RequestParam String key) {
		return "portrait-" + key;
	}
	
	
}
