package br.com.andreluisgomes.messaging.annotations.endpoint.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.concurrent.Future;

/**
 * Created by agomes on 26/07/16.
 */
@MessagingGateway(name = "fullNameGateway")
public interface FullName {

  @Gateway(requestChannel = "channelRoute")
  public Future<String> print(String name);
}
