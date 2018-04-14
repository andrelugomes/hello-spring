package br.com.andreluisgomes.messaging.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * Created by agomes on 23/07/16.
 */
@EnableIntegration
public class ChannelConfig {

  @Bean
  public MessageChannel channelRoute(){
    return new DirectChannel();
  }

  @Bean
  public MessageChannel channerIntergers(){
    return new DirectChannel();
  }

  @Bean
  public MessageChannel channel1(){
    return new PublishSubscribeChannel();
  }

  @Bean
  public MessageChannel channel2(){
    return new PublishSubscribeChannel();
  }

  @Bean
  public MessageChannel channel3(){
    return new PublishSubscribeChannel();
  }

  @Bean
  public MessageChannel channel4(){
    return new PublishSubscribeChannel();
  }

  @Bean
  public MessageChannel channel5(){
    return new DirectChannel();
  }

  @Bean
  public MessageChannel discardedChannel(){
    return new DirectChannel();
  }
}
