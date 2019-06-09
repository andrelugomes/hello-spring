package com.andrelugomes.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

  @Bean
  public Queue topicQueueOne() {
    return new Queue("topic-queue-1");
  }

  @Bean
  public Queue topicQueueTwo() {
    return new Queue("topic-queue-2");
  }

  @Bean
  public Queue topicQueueThree() {
    return new Queue("topic-queue-3");
  }

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange("topic-exchange");
  }

  @Bean
  public Binding bindingTopicOne() {
    return BindingBuilder.bind(topicQueueOne()).to((topicExchange())).with("KEY.1");
  }

  @Bean
  public Binding bindingTopicTwo() {
    return BindingBuilder.bind(topicQueueTwo()).to((topicExchange())).with("KEY.2");
  }

  @Bean
  public Binding bindingTopicThree() {
    return BindingBuilder.bind(topicQueueThree()).to((topicExchange())).with("KEY.#");
  }
}
