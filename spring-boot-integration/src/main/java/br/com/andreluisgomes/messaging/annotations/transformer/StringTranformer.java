package br.com.andreluisgomes.messaging.annotations.transformer;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

/**
 * Created by agomes on 18/07/16.
 */

@MessageEndpoint
public class StringTranformer {

  @Transformer(inputChannel = "channel1", outputChannel = "channel2")
  public String transformer(String message) {
    System.out.println("[Transformer] "+message);
    return new String(message).toUpperCase();
  }

}
