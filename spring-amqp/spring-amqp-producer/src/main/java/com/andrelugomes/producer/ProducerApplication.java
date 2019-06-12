package com.andrelugomes.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	RetryTemplate retryTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(final String... args) {
		//rabbitTemplate.convertAndSend("amq.topic", "ROUTING.KEY", "message");
		//rabbitTemplate.convertAndSend("amq.fanout", "ROUTING.KEY", "message");
		//rabbitTemplate.convertAndSend("amq.headers", "ROUTING.KEY", "message");
		//rabbitTemplate.convertAndSend("amq.direct", "ROUTING.KEY", "message");

		//Default exchange
		//rabbitTemplate.convertAndSend("default-ex-queue-1", "teste");

		//Direct exchange
		//rabbitTemplate.convertAndSend("direct-exchange","direct-1","teste");

		//Fanout exchange - No routing key
		//rabbitTemplate.convertAndSend("fanout-exchange",null,"teste");

		//Topic exchange - Routing key
		//rabbitTemplate.convertAndSend("topic-exchange","KEY.1","teste1");
		//rabbitTemplate.convertAndSend("topic-exchange","KEY.2","teste2");

		//Header exchange - Header property
		final Message message1 = MessageBuilder
						.withBody("teste1".getBytes())
						.setHeader("ARG", "1")
						.setHeader("ANOTHER", "HEADER")
						.build();

		final MessageProperties properties = new MessageProperties();
		properties.setHeader("ARG", "2");
		final Message message2 = MessageBuilder
						.withBody("teste2".getBytes())
						.andProperties(properties)
						.build();

		//rabbitTemplate.send("headers-exchange",null, message1);
		//rabbitTemplate.send("headers-exchange",null, message2);

		try {
			
			retryTemplate.execute(
							new RetryCallback<Object, Exception>() {

								@Override
								public Object doWithRetry(RetryContext context) {
									context.setAttribute("message", message1);

									System.out.println("RETRY=" +context.getRetryCount());
									rabbitTemplate.convertAndSend("headers-exchange", null, message1);
									return message1;
								}
							}, new RecoveryCallback<Object>() {

								@Override
								public Object recover(RetryContext context) {
									Object message = context.getAttribute("message");
									Throwable t = context.getLastThrowable();
									System.out.println("ERROR=" +t.getMessage());

									return null;
								}
							});
		} catch (Exception e) {
			e.printStackTrace();
		}


		try {
			retryTemplate.execute((RetryCallback<Object, Exception>) context -> {
								context.setAttribute("message", message1);
								System.out.println("RETRY=" +context.getRetryCount());
								rabbitTemplate.convertAndSend("headers-exchange", null, message1);
								return message1;
							}, context -> {
								Object message = context.getAttribute("message");
								Throwable t = context.getLastThrowable();
								System.out.println("ERROR=" +t.getMessage());

								return null;
							}
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
