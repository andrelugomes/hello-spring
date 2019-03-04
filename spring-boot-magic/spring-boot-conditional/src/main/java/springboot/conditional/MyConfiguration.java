package springboot.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean("stringBeam")
    @Conditional(EnableConditional.class)
    public String stringBeamEnable() {
        return "String Beam Enabled";
    }

    @Bean("stringBeam")
    @Conditional(DisableConditional.class)
    public String stringBeamDisable() {
        return "String Beam Disabled";
    }


}
