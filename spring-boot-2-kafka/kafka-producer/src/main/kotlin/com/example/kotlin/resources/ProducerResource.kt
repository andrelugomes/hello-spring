package com.example.kotlin.resources

import com.example.kotlin.services.Producer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/publishes")
class ProducerResource {

    @Autowired
    lateinit var producer: Producer

    @GetMapping("/topic")
    fun produceMessageChosiengTopic(
        @RequestParam(name = "topic") topic: String,
        @RequestParam(name = "message") message: String) {
        producer.publish(topic, message)
    }

    @GetMapping("/key")
    fun fullProducer(
        @RequestParam(name = "partition") partition: Int,
        @RequestParam(name = "topic") topic: String,
        @RequestParam(name = "key") key: String,
        @RequestParam(name = "message") message: String) {
        producer.publish(topic, message, partition, key)
    }
}
