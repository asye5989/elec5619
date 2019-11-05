package elec5619.psychologyservice.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConf {
	private static Logger log = LoggerFactory.getLogger(RabbitMQConf.class);

	@Value("${psychology.rabbitmq.exchange}")
	String exchange;

	

	
	@Value("${psychology.rabbitmq.matchscore}")
	String matchScoreQueueName;

	@Value("${psychology.rabbitmq.matchscore.key}")
	private String matchscoreKey;

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}


	@Bean
	Queue matchScoreQueue() {
		return new Queue(matchScoreQueueName, true);
	}

	// create binindgs

	
	@Bean
	Binding binding1(Queue matchScoreQueueName, DirectExchange exchange) {
		return BindingBuilder.bind(matchScoreQueueName).to(exchange).with(matchscoreKey);
	}

	// define JSON rabbit template
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {

		final var rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		log.info("########################  new rabbit template");
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}