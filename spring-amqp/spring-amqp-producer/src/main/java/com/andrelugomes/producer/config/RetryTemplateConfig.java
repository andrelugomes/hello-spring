package com.andrelugomes.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryTemplateConfig {

  @Bean
  public RetryTemplate retryTemplate() {
    final RetryTemplate retryTemplate = new RetryTemplate();
    final SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
    final ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();

    retryPolicy.setMaxAttempts(5);

    backOffPolicy.setInitialInterval(1000);
    backOffPolicy.setMultiplier(10);
    backOffPolicy.setMaxInterval(10000);

    retryTemplate.setBackOffPolicy(backOffPolicy);
    retryTemplate.setRetryPolicy(retryPolicy);

    return retryTemplate;
  }

}
