package com.example.springboot2kafka.consumers.partitions;

import static com.example.springboot2kafka.config.Topics.TOPIC_2_PARTITIONS_2_CONSUMERS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TwoConsumers {

    private static final Logger LOG = LoggerFactory.getLogger(TwoConsumers.class);

    @KafkaListener(topics = TOPIC_2_PARTITIONS_2_CONSUMERS, groupId = "2-c-1")
    public void consumerOne(@Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset) {
        LOG.info("consumerOne partition={}, offset={}, received message={}", partition, offset, message);
    }

    @KafkaListener(topics = TOPIC_2_PARTITIONS_2_CONSUMERS, groupId = "2-c-2")
    public void consumerTwo(@Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset) {
        LOG.info("consumerTwo partition={}, offset={}, received message={}", partition, offset, message);
    }
}
