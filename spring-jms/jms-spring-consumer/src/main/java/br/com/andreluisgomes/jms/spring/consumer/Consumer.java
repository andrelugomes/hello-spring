package br.com.andreluisgomes.jms.spring.consumer;

import javax.jms.JMSException;

import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Consumer {
	
	@Autowired
	private RedeliveryPolicy redeliveryPolicy;

    @JmsListener(destination = "queue.destination")
    public void receiveMessage(String message) {
        System.out.println("Received : " + message);
    }
    /**
     * A Exception faz com que o Listener não consuma a fila, portanto o ActiveMQ irá colocar no ActiveMQ.DLQ.
     * @param message
     * @throws JMSException
     */
    @JmsListener(destination = "queue.destination.retry")
    public void receiveMessageAnRetry(String message) throws JMSException {
        throw new JMSException("Wrong!");
    }
    
    /**
     * Consumindo o Dead Letter Queue
     * @param message
     * @throws JMSException
     */
    @JmsListener(destination = "ActiveMQ.DLQ")
    public void receiveMessageFromDLQ(String message) throws JMSException {
    	System.out.println("Received DLQ after "+redeliveryPolicy.getMaximumRedeliveries()+" times : " + message);
    }

    @JmsListener(destination = "queue.destination.scheduled")
    public void receiveMessageScheduled(String message) {
        System.out.println("Received : " + message+" at:" + LocalDateTime.now().toString());
    }
}
