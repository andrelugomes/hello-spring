package springboot.conditionalonproperty;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfiguration {

    @Bean
    @ConditionalOnProperty(value = "my.property")
    public void myProperty() {
        System.out.println("There is `my.property`");
    }

    @Bean
    @ConditionalOnProperty(value = "my.property", havingValue = "enabled")
    public void myPropertyEnabled() {
        System.out.println("Property 'my.property' = enabled");
    }

    @Bean
    @ConditionalOnProperty(value = "my.property", havingValue = "disabled")
    public void myPropertyDisabled() {
        System.out.println("Property 'my.property' = disabled");
    }

    @Bean
    @ConditionalOnProperty( prefix = "my", value = "property")
    public void myPropertyExist() {
        System.out.println("Property 'my.property' is OK!");
    }

}
