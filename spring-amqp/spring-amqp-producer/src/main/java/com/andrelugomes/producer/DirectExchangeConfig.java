package com.andrelugomes.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

  @Bean
  public Queue directQueueOne() {
    return new Queue("direct-queue-1");
  }

  @Bean
  public Queue directQueueTwo() {
    return new Queue("direct-queue-2");
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange("direct-exchange");
  }

  @Bean
  public Binding bindingOne() {
    return BindingBuilder.bind(directQueueOne()).to(directExchange()).with("direct-1");
  }

  @Bean
  public Binding bindingTwo() {
    return BindingBuilder.bind(directQueueTwo()).to(directExchange()).with("direct-1");
  }
}
