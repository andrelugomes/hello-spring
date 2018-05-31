package com.andrelugomes.springboot2subscriberrest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${publisher.name}", url = "${publisher.url}")
public interface PublisherClient {

    @RequestMapping(method = RequestMethod.POST, value = "/callback", consumes = "application/json")
    String callback(String callback);
}