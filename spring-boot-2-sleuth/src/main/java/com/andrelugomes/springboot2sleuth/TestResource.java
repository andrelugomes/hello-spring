package com.andrelugomes.springboot2sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

    private static Logger log = LoggerFactory.getLogger(TestResource.class);

    @GetMapping("/info")
    public void info(){
        log.info("test()");
    }

    @GetMapping("/warn")
    public void warn(){
        log.warn("test()");
    }

    @GetMapping("/error")
    public void error(){
        log.error("test()");
    }
}
