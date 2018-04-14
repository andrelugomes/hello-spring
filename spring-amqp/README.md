# AMQP Spring Boot Sample

## Run Docker - RabbitMQ

```
docker run -d --name rabbitmq -p 15672:15672 -p 5671:5671 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
```

## Amqp

- Queue
- TopicExchange
- Binding