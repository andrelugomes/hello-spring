package br.com.andrelugomes.producer;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class TopicPublisher {

    private static final String TOPIC_QUEUE = "text.topic";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendText(final String message) {

        ActiveMQTopic topic = new ActiveMQTopic(TOPIC_QUEUE);

        jmsTemplate.send(topic, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                System.out.println("Sending text message to topic.");
                return session.createTextMessage(message);
            }
        });
    }


}
