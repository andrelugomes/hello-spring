package br.com.andreluisgomes.messaging.annotations.endpoint.serviceactivator;

import br.com.andreluisgomes.messaging.annotations.endpoint.HelloService;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * Created by agomes on 18/07/16.
 */
@Service
public class HelloAggregatorImpl implements HelloService {

  @Override
  @ServiceActivator(inputChannel = "channel5") // Este Endpoint tem como entrada o canal 'channel5' agregado
  public void hello(String aggregated) {
    System.out.println("[ServiceActivator Endpoint Aggregator] H+E+L+L+O -> " + aggregated );
  }
}
