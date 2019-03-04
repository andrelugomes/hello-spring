package springboot.conditionalonclass;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.SpringBootMagicApplication;

@Configuration
public class MyAutoConfiguration {

    @Bean
    @ConditionalOnClass(SpringBootMagicApplication.class)
    public void main() {
        System.out.println("There is "+SpringBootMagicApplication.class.getName());
    }

    @Bean
    @ConditionalOnClass(name = "Foo")
    public void foo() {
        System.out.println("Where...");
    }
}
