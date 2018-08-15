package com.example.springboot2kafka.consumers.partitions;

import static com.example.springboot2kafka.config.Topics.TOPIC_2_PARTITIONS_1_CONSUMER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OneConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(OneConsumer.class);

    @KafkaListener(topics = TOPIC_2_PARTITIONS_1_CONSUMER, groupId = "single_consumer")
    public void consumer(@Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset) {
        LOG.info("consumer partition={}, offset={}, received message={}",partition, offset, message);
    }
}
