package springboot.conditionalonexpression;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("'${my.property}'=='enabled'")
public class SpELConfiguration {

    @Bean
    public void match() {
        System.out.println("Spring Expression Language Auto COnfiguration OK!");
    }
}
