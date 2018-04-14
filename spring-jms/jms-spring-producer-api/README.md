# JMS Spring Producer API

## Build

```
mvn clean package
```

## Run

```
java -jar target/jms-spring-producer-api-0.0.1-SNAPSHOT.jar

OR

mvn spring-boot:run
```

## Rest Producer API

http://localhost:8181/swagger-ui.html

## Depends On ActiveMQ

```console
docker run --name activemq -d -p 61616:61616 -p 8161:8161 andrelugomes/docker-activemq:5.12.0
```