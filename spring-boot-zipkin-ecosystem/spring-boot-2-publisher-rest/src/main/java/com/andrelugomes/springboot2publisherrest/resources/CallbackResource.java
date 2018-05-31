package com.andrelugomes.springboot2publisherrest.resources;

import static java.lang.String.*;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackResource {

    private static Logger log = LoggerFactory.getLogger(CallbackResource.class);

    @PostMapping("/callback")
    public String callback(@RequestBody @Valid String callback){
        log.info("callback({})", callback);
        return format("Response : %s",callback);
    }
}
