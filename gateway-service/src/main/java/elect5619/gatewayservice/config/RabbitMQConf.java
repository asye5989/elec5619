package elect5619.gatewayservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitMQConf {
 

	
	@Value("${gateway.rabbitmq.createQueue}")
	String createUserQueueName;
	
	@Value("${gateway.rabbitmq.changePassQueue}")
	String changePassQueName;
	

	@Value("${gateway.rabbitmq.exchange}")
	String exchange;

	@Value("${gateway.rabbitmq.routingkey.create}")
	private String routingkeyCreate;
	
	@Value("${gateway.rabbitmq.routingkey.changePass}")
	private String routingkeyChangePass;

	@Bean
	Queue changePassQue() {
		return new Queue(changePassQueName, true);
	}
	
	@Bean
	Queue createUserQueue() {
		return new Queue(createUserQueueName, true);
	}

	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding bindingCreateQ(Queue createUserQueue, DirectExchange exchange) {
		return BindingBuilder.bind(createUserQueue).to(exchange).with(routingkeyCreate);
	}
	
	@Bean
	Binding bindingchangePassQ(Queue changePassQue, DirectExchange exchange) {
		return BindingBuilder.bind(changePassQue).to(exchange).with(routingkeyChangePass);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
 
	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
	

	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}