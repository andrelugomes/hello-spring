package springboot.conditionalonmissingclass;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarAutoConfiguration {

    @Bean
    @ConditionalOnMissingClass(value = "Bar")
    public void foo() {
        System.out.println("There isn't Bar.class");
    }
}
