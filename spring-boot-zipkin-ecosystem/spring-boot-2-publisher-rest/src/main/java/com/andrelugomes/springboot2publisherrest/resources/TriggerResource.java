package com.andrelugomes.springboot2publisherrest.resources;

import java.time.LocalDateTime;

import com.andrelugomes.springboot2publisherrest.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggerResource {

    private static Logger log = LoggerFactory.getLogger(TriggerResource.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/pub")
    public void pub(){
        log.info("pub()");
        
        final String now = LocalDateTime.now().toString();
        
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, now);
    }

}
