package elec5619.psychologyservice.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import elec5619.psychologyservice.dto.MatchDTO;

@Service
public class PsychologyMessageProcessingService {
	private static final Logger log = LoggerFactory.getLogger(PsychologyMessageProcessingService.class);

	@Autowired
	PsychologyService psychologyService;

	ModelMapper mapper = new ModelMapper();

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${user.rabbitmq.exchange}")
	private String userExchange;

	@Value("${user.rabbitmq.routingkey.match.update}")
	private String matchUpdateKey;

	/*
	 * 
	 * psychology.rabbitmq.exchange=psychology.exchange
	 * psychology.rabbitmq.matchscore=psychology.matchscore
	 * psychology.rabbitmq.matchscore.key=psychology.matchscore.key
	 * psychology.rabbitmq.mbti=psychology.mbti
	 * psychology.rabbitmq.mbti.key=psychology.mbti.key
	 * psychology.rabbitmq.routingkey.match.update=user.rabbitmq.routingkey.match.
	 * update
	 * psychology.rabbitmq.routingkey.match.update.key=user.rabbitmq.routingkey.
	 * match.update.key
	 */
	@RabbitListener(queues = "${psychology.rabbitmq.matchscore}")
	public void processMatchScore(final MatchDTO dto) {
		int score = psychologyService.getMatchScore(dto.getType1(), dto.getType2());
		dto.setScore(score);
		rabbitTemplate.convertAndSend(userExchange, matchUpdateKey, dto);
		log.info("processed mathed users score: dto:" + dto);
	}

	
}
