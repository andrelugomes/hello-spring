package springboot.conditionalonwebapplication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebApplicationAutoConfiguration {

    @Bean
    @ConditionalOnWebApplication
    public void isAWebApplication() {
        System.out.println("IS A WEB APPLICATION");
    }

    @Bean
    @ConditionalOnNotWebApplication
    public void isntAWebApplication() {
        System.out.println("ISN'T A WEB APPLICATION");
    }

}
