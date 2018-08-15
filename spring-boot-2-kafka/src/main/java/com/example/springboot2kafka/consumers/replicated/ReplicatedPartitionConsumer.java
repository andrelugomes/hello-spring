package com.example.springboot2kafka.consumers.replicated;

import static com.example.springboot2kafka.config.Topics.TOPIC_SET_REPLICATED_PARTITION_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReplicatedPartitionConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ReplicatedPartitionConsumer.class);

    @KafkaListener(topicPartitions = @TopicPartition(topic = TOPIC_SET_REPLICATED_PARTITION_2, partitions = {"0"}),
            groupId = "set-repl-g-0")
    public void consumerOne(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        LOG.info("consumerTwo partition={}, offset={}, key={}, message={}", partition, offset, key, message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = TOPIC_SET_REPLICATED_PARTITION_2, partitions = {"1"}),
            groupId = "set-repl-g-1")
    public void consumerTwo(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) String offset,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        LOG.info("consumerTwo partition={}, offset={}, key={}, message={}", partition, offset, key, message);
    }
}
