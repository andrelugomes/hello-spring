package br.com.andreluisgomes.messaging.annotations.router;

import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

/**
 * Created by agomes on 23/07/16.
 */
@Component
public class PayloadRouter {
    private  static final String DEFAULT_CHANNEL = "defaultChannel";

  @Router(inputChannel = "channelRoute")
  public String route(Object payload) {
    if (payload instanceof String){
      return "channel1";
    }else if (payload instanceof  Integer){
      return "channerIntergers";
    }else{
      return DEFAULT_CHANNEL;
    }
  }
}
