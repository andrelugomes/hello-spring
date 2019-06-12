package com.andrelugomes.producer.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitTemplateConfig {

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
  	final RabbitTemplate template = new RabbitTemplate(connectionFactory);
  	final RetryTemplate retryTemplate = new RetryTemplate();
		final SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		final ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();

		retryPolicy.setMaxAttempts(3);

		backOffPolicy.setInitialInterval(1000);
		backOffPolicy.setMultiplier(2);
		backOffPolicy.setMaxInterval(10000);

		retryTemplate.setBackOffPolicy(backOffPolicy);
		retryTemplate.setRetryPolicy(retryPolicy);

		//Retry strategy for listeners
		template.setRetryTemplate(retryTemplate);

		//Transactional strategy for producers
		template.setChannelTransacted(true);

		return template;
	}
}
