package br.com.andreluisgomes.messaging.annotations.endpoint.serviceactivator;

import br.com.andreluisgomes.messaging.annotations.endpoint.HelloService;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * Created by agomes on 17/07/16.
 */
@Service
public class HelloServiceImpl implements HelloService {

  //o.s.i.endpoint.EventDrivenConsumer : Adding {service-activator:helloServiceImpl.hello.serviceActivator} as a subscriber to the 'channel1' channel

  @Override
  @ServiceActivator(inputChannel = "channel1") // Este Endpoint tem como entrada o canal 'channel1'
  public void hello(String name) {
      System.out.println( "[ServiceActivator Endpoint] Hello, " + name );
  }
}
