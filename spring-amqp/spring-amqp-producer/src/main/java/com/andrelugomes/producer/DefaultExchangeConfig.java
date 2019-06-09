package com.andrelugomes.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultExchangeConfig {

  @Bean
  public Queue defaultQueue() {
    return new Queue("default-ex-queue-1");
  }
}
