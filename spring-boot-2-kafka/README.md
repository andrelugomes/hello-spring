# Spring Boot 2 Kafka

## Kafka

+ https://kafka.apache.org/quickstart

Download and un-tar
```bash
tar -xzf kafka_2.11-2.0.0.tgz
cd kafka_2.11-2.0.0
``` 
Start Zookeeper and Kafka
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties
```
Mini cluster

```bash
bin/kafka-server-start.sh config/server-1.properties

bin/kafka-server-start.sh config/server-2.properties
```

## Producer Application
Start application
```bash
$ ./gradlew bootRun -p kafka-producer
```

```bash
$ curl http://localhost:8080/publishes/simple?message=wwwwwww
```
```bash
$ curl http://localhost:8080/publishes/topic?topic=topic.set.partitions.2&message=m2

```
```bash
$ curl http://localhost:8080/publishes/?partition=0&topic=topic.consumer.2.partitions.2&message=m1
```

## Consumer Application
Start application
```bash
$ ./gradlew bootRun -p kafka-consumer
```

## Concepts

### Producer

#### Topics

A topic is a category or feed name to which records are published. 
Topics in Kafka are always multi-subscriber; that is, a topic can have zero, one, or many consumers that subscribe to the data written to it.

For each topic, the Kafka cluster maintains a partitioned log

```bash
kafka_2.11-2.0.0 bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic topic.replicated.partitions.2

Topic:topic.replicated.partitions.2	PartitionCount:2	ReplicationFactor:2	Configs:
	Topic: topic.replicated.partitions.2	Partition: 0	Leader: 1	Replicas: 1,2	Isr: 1,2
	Topic: topic.replicated.partitions.2	Partition: 1	Leader: 2	Replicas: 2,1	Isr: 2,1
```

#### Partition

Is an ordered, immutable sequence of records that is continually appended to a structured commit log. 
The records in the partitions are each assigned a sequential id number called the offset that uniquely identifies each record within the partition.

#### Leader

Is the node responsible for all reads and writes for the given partition. Each node will be the leader for a randomly selected portion of the partitions.

#### Repicas and Replication Factor

Is the list of nodes that replicate the log for this partition regardless of whether they are the leader or even if they are currently alive.

#### ISR ("in-sync" replicas)

Is the set of "in-sync" replicas. This is the subset of the replicas list that is currently alive and caught-up to the leader.

#### Key

Keys are mostly useful/necessary if you require strong order for a key and are developing something like a state machine. 
If you require that messages with the same key (for instance, a unique id) are always seen in the correct order, 
attaching a key to messages will ensure **messages with the same key always go to the same partition in a topic**. 
Kafka guarantees order within a partition, but not across partitions in a topic, so alternatively not providing a key - which will result in round-robin distribution across partitions - will not maintain such order.

Chosing Key and Partition
```bash
$ curl http://localhost:8081/publishes/key?topic=topic.consumer.2.partitions.2.samegroup&message=m3&partition=1&key=abc_123

```

### Consumer

#### Group ID

```java
@KafkaListener(topics = TOPIC, groupId = "GROUP")
```

#### Offset

### Transactions


## links

+ https://kafka.apache.org/intro
+ https://docs.spring.io/spring-kafka/reference/htmlsingle/
+ https://www.confluent.io/blog/hands-free-kafka-replication-a-lesson-in-operational-simplicity/
+ http://cloudurable.com/blog/kafka-tutorial-kafka-producer-advanced-java-examples/index.html

+ https://www.udemy.com/apache-kafka/

