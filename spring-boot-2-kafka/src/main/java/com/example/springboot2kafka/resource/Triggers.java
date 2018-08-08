package com.example.springboot2kafka.resource;

import com.example.springboot2kafka.publisher.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishes")
public class Triggers {

    @Autowired
    private Producer producer;

    @GetMapping("/simple")
    public void string(@RequestParam(name = "message") final String message) {
        producer.publish(message);
    }

    @GetMapping("/")
    public void string(@RequestParam(name = "partition") final int partition,
            @RequestParam(name = "topic") final String topic,
            @RequestParam(name = "message") final String message) {
        producer.publish(topic, partition, message);
    }
}
