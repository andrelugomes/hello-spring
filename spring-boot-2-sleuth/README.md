#Spring Boot Sleuth

## tracing

```text
018-05-26 14:20:51.821 ERROR [HelloSpringBootSleuth,37b633aa0c98a8b6,37b633aa0c98a8b6,false] 15775 --- [nio-8080-exec-3] c.a.springboot2sleuth.TestResource       : test()
2018-05-26 14:20:58.474  WARN [HelloSpringBootSleuth,484188d0c04121ee,484188d0c04121ee,false] 15775 --- [nio-8080-exec-2] c.a.springboot2sleuth.TestResource       : test()
2018-05-26 14:21:10.764  WARN [HelloSpringBootSleuth,a649e246ee9acdc0,a649e246ee9acdc0,false] 15775 --- [nio-8080-exec-5] c.a.springboot2sleuth.TestResource       : test()
```

[appname,traceId,spanId,exportable] entries from the MDC:

spanId: The ID of a specific operation that took place.

appname: The name of the application that logged the span.

traceId: The ID of the latency graph that contains the span.

exportable: Whether the log should be exported to Zipkin. When would you like the span not to be exportable? When you want to wrap some operation in a Span and have it written to the logs only.



