package springboot.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnableConditional implements Condition {

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        final String property = context.getEnvironment().getProperty("my.property", "disabled");
        return property.equalsIgnoreCase("enabled");
    }
}
