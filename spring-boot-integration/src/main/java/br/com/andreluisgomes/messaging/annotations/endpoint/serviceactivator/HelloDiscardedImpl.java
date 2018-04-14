package br.com.andreluisgomes.messaging.annotations.endpoint.serviceactivator;

import br.com.andreluisgomes.messaging.annotations.endpoint.HelloService;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * Created by agomes on 17/07/16.
 */
@Service
public class HelloDiscardedImpl implements HelloService {

  @Override
  @ServiceActivator(inputChannel = "discardedChannel") // Este Endpoint tem como entrada o canal 'channel1'
  public void hello(String name) {
      System.out.println( "[ServiceActivator Discarted Messages] " + name );
  }
}
