# AMQP Spring Boot Sample

## Run Docker - RabbitMQ

```
docker run -d --name rabbitmq -p 15672:15672 -p 5671:5671 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
```

## Amqp

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
