package com.example.springboot2kafka.consumers.multi;

import static com.example.springboot2kafka.config.Topics.TOPIC_MULTI_CONSUMERS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MultiConsumers {

    private static final Logger LOG = LoggerFactory.getLogger(MultiConsumers.class);
    

    @KafkaListener(topics = TOPIC_MULTI_CONSUMERS)
    public void listenerDefault(@Payload String message) {
        LOG.info("listenerDefault received message='{}'", message);
    }

    @KafkaListener(topics = TOPIC_MULTI_CONSUMERS, groupId = "one")
    public void listenerOne(@Payload String message) {
        LOG.info("listenerOne received message='{}'", message);
    }

    @KafkaListener(topics = TOPIC_MULTI_CONSUMERS, groupId = "two")
    public void listenerTwo(@Payload String message) {
        LOG.info("listenerTwo received message='{}'", message);
    }
}
