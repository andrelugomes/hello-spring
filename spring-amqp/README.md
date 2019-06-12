# AMQP Spring Boot Sample

## Run Docker - RabbitMQ

```
docker run -d --name rabbitmq -p 15672:15672 -p 5671:5671 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
```

## Amqp

### Retry

If no RabbitTemplate bean is defined, you can use default retry behavior turned on:

```text
spring.rabbitmq.template.retry.enabled=true
spring.rabbitmq.template.retry.initial-interval=2000ms
spring.rabbitmq.template.retry.max-attempts=5
spring.rabbitmq.template.retry.max-interval=30000ms
spring.rabbitmq.template.retry.multiplier=
```
If a RabbitTemplate bean is overrided. You need to define a bean for RetryTemplate.


### Queue
+ https://docs.spring.io/spring-amqp/api/org/springframework/amqp/core/Queue.html

```text
isAutoDelete() = True if the server should delete the queue when it is no longer in use (the last consumer is cancelled).
isDurable() = A durable queue will survive a server restart.
isExclusive() = True if the server should only send messages to the declarer's connection.
```

### Exchange

+ https://medium.com/faun/different-types-of-rabbitmq-exchanges-9fefd740505d

If no binding is set. Default behavior is:

```text
Default exchange
The default exchange is implicitly bound to every queue, with a routing key equal to the queue name. It is not possible to explicitly bind to, or unbind from the default exchange. It also cannot be deleted.
```

+ Direct
+ Fanout
+ topic
+ Headers

### Binding
