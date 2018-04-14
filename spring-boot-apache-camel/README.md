# Spring-Boot + Apache Camel

## ActiveMQ

```
docker run --name activemq -d -p 61616:61616 -p 8161:8161 andrelugomes/docker-activemq:5.12.0
```

## Spring Boot

```
mvn clean install spring-boot:run
```