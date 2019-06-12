package com.andrelugomes.producer.exchange;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultExchangeConfig {

  @Bean
  public Queue defaultQueue() {
    /**
     * The queue is durable, non-exclusive and non auto-delete.
     */
    return new Queue("default-ex-queue-1");
  }
}
