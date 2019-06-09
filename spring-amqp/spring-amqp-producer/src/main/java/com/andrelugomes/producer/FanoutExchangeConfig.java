package com.andrelugomes.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {

  @Bean
  public Queue fanoutQueueOne() {
    return new Queue("fanout-queue-1");
  }

  @Bean
  public Queue fanoutQueueTwo() {
    return new Queue("fanout-queue-2");
  }

  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange("fanout-exchange");
  }

  @Bean
  public Binding bindingFanoutOne() {
    return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
  }

  @Bean
  public Binding bindingFanoutTwo() {
    return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
  }
}
