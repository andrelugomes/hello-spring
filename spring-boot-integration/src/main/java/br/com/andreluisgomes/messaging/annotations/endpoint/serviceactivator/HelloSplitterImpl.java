package br.com.andreluisgomes.messaging.annotations.endpoint.serviceactivator;

import br.com.andreluisgomes.messaging.annotations.endpoint.HelloService;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * Created by agomes on 18/07/16.
 */
@Service
public class HelloSplitterImpl implements HelloService {

  @Override
  @ServiceActivator(inputChannel = "channel3") // Este Endpoint tem como entrada o canal 'channel3' splitado
  public void hello(String character) {
    System.out.println("[ServiceActivator Endpoint Splitter]" + character );
  }
}
