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

    /**
     *
     * Round Robin over partitions 0 and 1
     *
     * publishing='m1' to topic='topic.consumer.1.partitions.2'
     * consumer partition=0, offset=11, received message=m1
     * 
     * publishing='m2' to topic='topic.consumer.1.partitions.2'
     * consumer partition=1, offset=1, received message=m2
     */
    @KafkaListener(topics = TOPIC_2_PARTITIONS_1_CONSUMER, groupId = "single_consumer")
    public void consumer(@Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset) {
        LOG.info("consumer partition={}, offset={}, received message={}",partition, offset, message);
    }
}
