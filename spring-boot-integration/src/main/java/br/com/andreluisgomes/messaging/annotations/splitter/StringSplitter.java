package br.com.andreluisgomes.messaging.annotations.splitter;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;

/**
 * Created by agomes on 18/07/16.
 */

@MessageEndpoint
public class StringSplitter {

  /**
   * SET MessageHeaders.SEQUENCE_SIZE
   * SET MessageHeaders.CORRELATION_ID
   *
   * @param message
   * @return
   */
  @Splitter(inputChannel = "channel2", outputChannel = "channel3")
  public String[] splitter(String message) {
    System.out.println("[Splitter] "+ message);
    return new String(message).split("(?!^)"); //Split on !
  }

}
