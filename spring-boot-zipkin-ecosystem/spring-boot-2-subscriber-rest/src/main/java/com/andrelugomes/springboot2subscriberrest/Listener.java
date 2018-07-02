package com.andrelugomes.springboot2subscriberrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.SpanName;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    @Autowired
    private PublisherClient client;

    @SpanName("receiveMessage")
    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveMessage(final String message) {
        log.info("Received message: {}", message);

        final String callback = client.callback(message);
        
        log.info("Callback from REST {}", callback);
    }

}
