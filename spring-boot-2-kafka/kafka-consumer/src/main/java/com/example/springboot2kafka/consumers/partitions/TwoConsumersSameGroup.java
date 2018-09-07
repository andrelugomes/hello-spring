package com.example.springboot2kafka.consumers.partitions;

import static com.example.springboot2kafka.config.Topics.TOPIC_2_PARTITIONS_2_CONSUMERS_SAME_GROUP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * publishing='m1' to topic='topic.consumer.2.partitions.2.samegroup'
 * consumerTwo partition=1, offset=0, received message=m1
 *
 * publishing='m2' to topic='topic.consumer.2.partitions.2.samegroup'
 * consumerOne partition=0, offset=6, received message=m2
 *
 * publishing='m3' to topic='topic.consumer.2.partitions.2.samegroup'
 * consumerTwo partition=1, offset=1, received message=m3
 *
 * publishing='m4' to topic='topic.consumer.2.partitions.2.samegroup'
 * consumerOne partition=0, offset=7, received message=m4
 *
 * publishing='m5' to topic='topic.consumer.2.partitions.2.samegroup'
 * consumerTwo partition=1, offset=2, received message=m5
 *
 * publishing='m6' to topic='topic.consumer.2.partitions.2.samegroup'
 * consumerOne partition=0, offset=8, received message=m6
 * 
 */
@Service
public class TwoConsumersSameGroup {

    private static final Logger LOG = LoggerFactory.getLogger(TwoConsumersSameGroup.class);

    @KafkaListener(topics = TOPIC_2_PARTITIONS_2_CONSUMERS_SAME_GROUP, groupId = "same_group")
    public void consumerOne(@Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset) {
        LOG.info("consumerOne partition={}, offset={}, received message={}", partition, offset, message);
    }

    @KafkaListener(topics = TOPIC_2_PARTITIONS_2_CONSUMERS_SAME_GROUP, groupId = "same_group")
    public void consumerTwo(@Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset) {
        LOG.info("consumerTwo partition={}, offset={}, received message={}", partition, offset, message);
    }
}
