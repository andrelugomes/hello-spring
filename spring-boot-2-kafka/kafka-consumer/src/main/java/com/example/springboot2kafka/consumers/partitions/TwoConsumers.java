package com.example.springboot2kafka.consumers.partitions;

import static com.example.springboot2kafka.config.Topics.TOPIC_2_PARTITIONS_2_CONSUMERS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * publishing='m2' to topic='topic.consumer.2.partitions.2'
 * consumerTwo partition=0, offset=7, received message=m2
 * consumerOne partition=0, offset=7, received message=m2
 * 
 * publishing='m3' to topic='topic.consumer.2.partitions.2'
 * consumerOne partition=1, offset=1, received message=m3
 * consumerTwo partition=1, offset=1, received message=m3
 */
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
