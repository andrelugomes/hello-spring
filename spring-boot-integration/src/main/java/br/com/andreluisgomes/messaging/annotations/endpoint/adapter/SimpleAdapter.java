package br.com.andreluisgomes.messaging.annotations.endpoint.adapter;

import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 * Created by agomes on 25/07/16.
 */
@Component
public class SimpleAdapter {

  @InboundChannelAdapter(value = "channel1", poller = @Poller(fixedRate = "60000"))
  public Message<String> inboundChannelAdapter() {
    Message<String> message = new GenericMessage<>("André!Luis!Gomes!");
    return message;
  }
}
