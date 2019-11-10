package elec5619.clientuiservice.conf;

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
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration 
public class RabbitMQConf  {
	
	private static Logger log = LoggerFactory.getLogger(RabbitMQConf.class);
	
	
	@Value("${user.rabbitmq.exchange}")
	private String exchange;

	@Value("${user.rabbitmq.queue.create}")
	private String queueCreateName;
	
	@Value("${user.rabbitmq.queue.update}")
	private String queueUpdateName;
	
	@Value("${user.rabbitmq.queue.delete}")
	private String queueDeleteName;
	
	@Value("${user.rabbitmq.queue.match.update}")
	private String queueMatcName;
	
	@Value("${user.rabbitmq.queue.match.delete}")
	private String queueMatchDeleteName;

	@Value("${user.rabbitmq.routingkey.create}")
	private  String createRoutingkey;
	@Value("${user.rabbitmq.routingkey.update}")
	private  String upateRoutingkey;
	@Value("${user.rabbitmq.routingkey.delete}")
	private  String deleteRoutingkey;
	@Value("${user.rabbitmq.routingkey.match.update}")
	private  String matchKey;
	@Value("${user.rabbitmq.routingkey.match.delete}")
	private  String matchDeletekey;
	
	@Bean
	Queue queueCreateName() {
		return new Queue(queueCreateName, true);
	}

	@Bean
	Queue queueUpdateName() {
		return new Queue(queueUpdateName, true);
	}

	@Bean
	Queue queueDeleteName() {
		return new Queue(queueDeleteName, true);
	}

	@Bean
	Queue queueMatcName() {
		return new Queue(queueMatcName, true);
	}

	@Bean
	Queue queueMatchDelete() {
		return new Queue(queueMatchDeleteName, true);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding binding1(Queue queueCreateName, DirectExchange exchange) {
		return BindingBuilder.bind(queueCreateName).to(exchange).with(createRoutingkey);
	}

	@Bean
	Binding binding2(Queue queueUpdateName, DirectExchange exchange) {
		return BindingBuilder.bind(queueUpdateName).to(exchange).with(upateRoutingkey);
	}

	@Bean
	Binding binding3(Queue queueDeleteName, DirectExchange exchange) {
		return BindingBuilder.bind(queueDeleteName).to(exchange).with(deleteRoutingkey);
	}

	@Bean
	Binding binding4(Queue queueMatcName, DirectExchange exchange) {
		return BindingBuilder.bind(queueMatcName).to(exchange).with(matchKey);
	}

	@Bean
	Binding binding5(Queue queueMatchDelete, DirectExchange exchange) {
		return BindingBuilder.bind(queueMatchDelete).to(exchange).with(matchDeletekey);
	}

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
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	
	
	  
}