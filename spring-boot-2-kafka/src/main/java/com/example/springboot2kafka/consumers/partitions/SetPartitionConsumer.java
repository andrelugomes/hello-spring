package com.example.springboot2kafka.consumers.partitions;

import static com.example.springboot2kafka.config.Topics.TOPIC_SET_PARTITION_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class SetPartitionConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SetPartitionConsumer.class);

    @KafkaListener(topicPartitions = @TopicPartition(topic = TOPIC_SET_PARTITION_2, partitions = {"0"}),
            groupId = "set-g-0")
    public void consumerOne(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOG.info("consumerOne partition={} received message={}", partition, message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = TOPIC_SET_PARTITION_2, partitions = {"1"}),
            groupId = "set-g-1")
    public void consumerTwo(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOG.info("consumerTwo partition={} received message={}", partition, message);
    }
}
