package br.com.andreluisgomes.messaging.annotations.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by agomes on 17/07/16.
 */
@Service
public class GreeterServiceImpl implements GreeterService {

  @Autowired
  private MessageChannel channelRoute;

  @Override
  public void greet(Object payload){
    channelRoute.send(MessageBuilder.withPayload(payload).build() );
  }
}
