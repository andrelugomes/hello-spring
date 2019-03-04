package com.example.configs;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:my-configs.properties")
@ConditionalOnResource(resources = "classpath:my-configs.properties")
public class MyConfigsAutoConfiguration {

    @Bean
    public MyConfigs myConfigs(Environment env) {
        System.out.println("::::: MY CONFIGS Auto Configuration :::::");

        return new MyConfigs(
                env.getProperty("my-configs.one"),
                env.getProperty("my-configs.two"),
                env.getProperty("my-configs.three")
        );
    }
}
