package springboot.conditionalonmissingbean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(name = MyDefaultConfiguration.STRING_BEAM)
public class MyDefaultConfiguration {

    public static final String STRING_BEAM = "stringBeam";

    @Bean(STRING_BEAM)
    public String stringBeamDefault() {
        return "String Beam DEFAULT";
    }

}
