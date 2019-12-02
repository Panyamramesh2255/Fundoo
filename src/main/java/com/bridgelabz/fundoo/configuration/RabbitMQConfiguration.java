package com.bridgelabz.fundoo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.fundoo.util.Util;

@Configuration
public class RabbitMQConfiguration {

	String routingKey = "key";
	String queueExhcnage = "queue";

	@Bean
	Queue queue() {
		return new Queue(routingKey, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(queueExhcnage);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(routingKey);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	Util utilSender() {
		return new Util();
	}

	@Bean
	MessageListenerAdapter myQueueListener(Util util) {
		return new MessageListenerAdapter(util, "sendMail");
	}
	
}
