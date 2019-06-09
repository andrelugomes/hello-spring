package com.andrelugomes.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderExchangeConfig {

  @Bean
  public Queue headerQueueOne() {
    return new Queue("header-queue-1");
  }

  @Bean
  public Queue headerQueueTwo() {
    return new Queue("header-queue-2");
  }

  @Bean
  public Queue headerQueueThree() {
    return new Queue("header-queue-3");
  }

  @Bean
  public HeadersExchange headersExchange() {
    return new HeadersExchange("headers-exchange");
  }

  @Bean
  public Binding bindingHeadersOne() {
    return BindingBuilder.bind(headerQueueOne()).to((headersExchange()))
            .where("ARG").matches("2");
  }

  @Bean
  public Binding bindingHeadersTwo() {
    return BindingBuilder.bind(headerQueueTwo()).to((headersExchange()))
            .where("ARG").matches("1");
  }

  @Bean
  public Binding bindingHeadersThree() {
    return BindingBuilder.bind(headerQueueThree()).to((headersExchange()))
            .where("ARG").exists();
  }

}
