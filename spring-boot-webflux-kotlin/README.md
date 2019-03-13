# Spring 5 WebFlux

+ Kotlin
+ Reactive MongoDB
+ Spring Boot

```
Spring WebFlux uses a library called Reactor for its reactive support. Reactor 
is an implementation of the Reactive Streams specification.

Reactor Provides two main types called Flux and Mono. Both of these types implement 
the Publisher interface provided by Reactive Streams. Flux is used to represent 
a stream of 0..N elements and Mono is used to represent a stream of 0..1 element.
```

## Flux

https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html

## MongoDB

```bash
docker run -d --name mongo-reactive -p 27017:27017 mongo:latest   
```

## TwitterPoll and Rest Kafka Consumer

+ https://github.com/andrelugomes/udemy/blob/master/kafka-for-beginners/

```java
TwitterPoll.java
RestConsumer.java
```

## Content

+ https://www.callicoder.com/reactive-rest-apis-spring-webflux-reactive-mongo/
+ https://projectreactor.io/docs/core/release/reference/#core-features