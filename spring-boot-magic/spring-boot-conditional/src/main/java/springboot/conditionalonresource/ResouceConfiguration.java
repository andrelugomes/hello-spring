package springboot.conditionalonresource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResouceConfiguration {

    @Bean
    @ConditionalOnResource(resources = "classpath:application.properties")
    public void hasResourcesDotProperties() {
        System.out.println("Resource 'application.properties' is OK!");
    }

    @Bean
    @ConditionalOnResource(resources = "classpath:application.yml")
    public void hasResourcesDotYml() {
        System.out.println("Resource 'application.yml' is OK!");
    }
}
