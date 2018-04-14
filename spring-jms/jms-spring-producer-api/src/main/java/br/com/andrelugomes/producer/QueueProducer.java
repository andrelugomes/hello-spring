package br.com.andrelugomes.producer;

import br.com.andrelugomes.producer.message.JsonMessage;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class QueueProducer {

    private static final String TEXT_QUEUE = "text.queue";
    private static final String NUMBER_QUEUE = "number.queue";
    private static final String JSON_QUEUE = "json.queue";
    private static final String XML_QUEUE = "xml.queue";
    private static final String SCHEDULED_QUEUE = "scheduled.queue";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendText(final String message) {
        jmsTemplate.send(new ActiveMQQueue(TEXT_QUEUE), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println("Sending text message.");
                return session.createTextMessage(message);
            }
        });
    }

    public void sendNumber(final Integer number){
        jmsTemplate.send(NUMBER_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println("Sending number message.");
                return session.createObjectMessage(number);
            }
        });
    }

    /**
     * Habilitar  schedulerSupport="true" no arquivo Activemq.xml
     *
     * <broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost" dataDirectory="${activemq.data}" schedulerSupport="true">
     * @param message
     * @param delay
     */
    public  void sendScheduledMessage(final String message, final Long delay){
        jmsTemplate.send(SCHEDULED_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println("Scheduling message.");
                TextMessage messageToSend = session.createTextMessage(message);
                messageToSend.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
                return messageToSend;
            }
        });
    }

    public void sendJsonMessage(final JsonMessage container) {
    	jmsTemplate.convertAndSend(JSON_QUEUE, container); //Using Bean jacksonJmsMessageConverter()
    }

    public void sendXml(String xml) {
        jmsTemplate.send(XML_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println("xml message.");
                TextMessage messageToSend = session.createTextMessage(xml);
                return messageToSend;
            }
        });
    }
}
