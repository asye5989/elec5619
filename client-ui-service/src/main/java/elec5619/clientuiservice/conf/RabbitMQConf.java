package elec5619.clientuiservice.conf;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConf {

	@Value("${user.rabbitmq.exchange}")
	String exchange;

	@Value("${user.rabbitmq.queue.create}")
	String queueCreateName;
	@Value("${user.rabbitmq.queue.update}")
	String queueUpdateName;
	@Value("${user.rabbitmq.queue.delete}")
	String queueDeleteName;
	@Value("${user.rabbitmq.queue.match}")
	String queueMatcName;
	@Value("${user.rabbitmq.queue.match.delete}")
	String queueMatchDeleteName;

	@Value("${user.rabbitmq.routingkey.create}")
	private String createRoutingkey;
	@Value("${user.rabbitmq.routingkey.update}")
	private String upateRoutingkey;
	@Value("${user.rabbitmq.routingkey.delete}")
	private String deleteRoutingkey;
	@Value("${user.rabbitmq.routingkey.match}")
	private String matchKey;
	@Value("${user.rabbitmq.routingkey.match.delete}")
	private String matchDeletekey;

	@Bean
	Queue queueCreateName() {
		return new Queue(queueCreateName, false);
	}

	@Bean
	Queue queueUpdateName() {
		return new Queue(queueUpdateName, false);
	}

	@Bean
	Queue queueDeleteName() {
		return new Queue(queueDeleteName, false);
	}

	@Bean
	Queue queueMatcName() {
		return new Queue(queueMatcName, false);
	}

	@Bean
	Queue queueMatchDeleteName() {
		return new Queue(queueMatchDeleteName, false);
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
		return BindingBuilder.bind(queueUpdateName).to(exchange).with(matchDeletekey);
	}

	@Bean
	Binding binding3(Queue queueDeleteName, DirectExchange exchange) {
		return BindingBuilder.bind(queueDeleteName).to(exchange).with(upateRoutingkey);
	}

	@Bean
	Binding binding4(Queue queueMatcName, DirectExchange exchange) {
		return BindingBuilder.bind(queueMatcName).to(exchange).with(deleteRoutingkey);
	}

	@Bean
	Binding binding5(Queue queueMatchDeleteName, DirectExchange exchange) {
		return BindingBuilder.bind(queueMatchDeleteName).to(exchange).with(matchKey);
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
}