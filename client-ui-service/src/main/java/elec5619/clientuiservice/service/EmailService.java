package elec5619.clientuiservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static final Logger log = LoggerFactory.getLogger(UserMessageProcessingService.class);

	public void sendEmailToAdmin(String subject, String message) {
		log.info("SIMULTAE EMAIL SERVICE... ");
		log.info("Email Subject: "+subject);
		log.info("Email Message: "+message);
		
	}
}
