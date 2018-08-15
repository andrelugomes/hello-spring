package com.example.springboot2kafka.publisher;

import static com.example.springboot2kafka.config.Topics.TOPIC_STRING;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public void publish(String message){
        LOG.info("publishing='{}' to topic='{}'", message, TOPIC_STRING);
        kafkaTemplate.send(TOPIC_STRING, message);
    }

    public void publish(final String topic, final String message){
        LOG.info("publishing='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }

    public void publish(final String topic, final int partition, final String message) {
        LOG.info("publishing='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }

    public void publish(final String topic, final int partition, final String key, final String message) {
        LOG.info("publishing='{}' to topic='{}', key={}", message, topic, key);
        kafkaTemplate.send(topic, key, message);
    }
}
