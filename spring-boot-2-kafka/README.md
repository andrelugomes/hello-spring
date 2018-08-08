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

Start application
```bash
$ ./gradlew clean build bootRun

```
## Publishing

```bash
$ curl http://localhost:8080/publishes/simple?message=wwwwwww
```

```bash
$ curl http://localhost:8080/publishes/?partition=0&topic=topic.consumer.2.partitions.2&message=m1
```

## Concepts

### Producer

#### Topics

```bash
kafka_2.11-2.0.0 bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic topic.replicated.partitions.2

Topic:topic.replicated.partitions.2	PartitionCount:2	ReplicationFactor:2	Configs:
	Topic: topic.replicated.partitions.2	Partition: 0	Leader: 1	Replicas: 1,2	Isr: 1,2
	Topic: topic.replicated.partitions.2	Partition: 1	Leader: 2	Replicas: 2,1	Isr: 2,1
```

#### Partition
#### Leader
#### Repicas and Replication Factor
#### ISR ("in-sync" replicas)

### Consumer

#### Group ID

#### Offset

### Transactions
