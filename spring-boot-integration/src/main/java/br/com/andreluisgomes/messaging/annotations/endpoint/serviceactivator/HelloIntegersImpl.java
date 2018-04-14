package br.com.andreluisgomes.messaging.annotations.endpoint.serviceactivator;

import br.com.andreluisgomes.messaging.annotations.endpoint.IntegerEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * Created by agomes on 17/07/16.
 */
@Service
public class HelloIntegersImpl implements IntegerEndpoint {


  @Override
  @ServiceActivator(inputChannel = "channerIntergers") // Este Endpoint tem como entrada o canal 'channel1'
  public void get(Integer value) {
      System.out.println( "[ServiceActivator Endpoint for Integers] " + value );
  }
}
