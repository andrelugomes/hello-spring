package br.com.andreluisgomes.messaging.annotations.aggregator;

import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.messaging.Message;

import java.util.List;

/**
 * Created by agomes on 18/07/16.
 */
@MessageEndpoint
public class StringAggregator {

  @Aggregator(inputChannel = "channel4", outputChannel = "channel5", sendPartialResultsOnExpiry = "false")
  public String agragate(String[] character) { //List<Message<String>> character
    System.out.println("[Aggregator] "+ character);
    StringBuilder resut = new StringBuilder();
    for (String ch: character)
      resut.append(ch);

    return resut.toString();
  }

  /**
   * If not present on any method, the aggregator will use the SequenceSizeCompletionStrategy.
   *
   * MessageHeaders.SEQUENCE_SIZE
   *
   * @param messages
   * @return
   */
  @ReleaseStrategy // Quando liberar
  public boolean canRelease(List<Message<?>>  messages) { //MessageGroup messages
      if (messages.size()==14)
        return true;
    return false;
  }

  /**
   * If no correlation strategy is indicated, the aggregator will use the HeaderAttributeCorrelationStrategy based on CORRELATION_ID.
   * @param message
   * @return
   */
 @CorrelationStrategy //Como juntar
  public Object correlateBy(Message<String> message) {
    return message.getHeaders().get("correlationId");
  }
}
