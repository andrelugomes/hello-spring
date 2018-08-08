package com.example.springboot2kafka.config;

public interface Topics {

    String TOPIC_STRING = "topic.string";

    String TOPIC_MULTI_CONSUMERS = "topic.multi.consumers";

    String TOPIC_SET_PARTITION_2 = "topic.set.partitions.2";

    String TOPIC_REPLICATED_PARTITION_2 = "topic.replicated.partitions.2";

    String TOPIC_SET_REPLICATED_PARTITION_2 = "topic.set.replicated.partitions.2";

    String TOPIC_2_PARTITIONS_1_CONSUMER = "topic.consumer.1.partitions.2";

    String TOPIC_2_PARTITIONS_2_CONSUMERS = "topic.consumer.2.partitions.2";

    String TOPIC_2_PARTITIONS_2_CONSUMERS_SAME_GROUP = "topic.consumer.2.partitions.2.samegroup";

}
