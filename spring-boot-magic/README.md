# Spring Boot Magic

## Auto configuration

https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html

## Profile

Difference between `@Conditional` and `@Profile` Annotations

```text

Both Spring @Profiles and @Conditional annotations are used to develop an ?If-Then-Else? conditional checking. However, Spring 4 @Conditional  is more generalized version of @Profile annotation.

+ Spring 3.1 @Profiles is used to write conditional checking based on Environment variables only. Profiles can be used for loading application configuration based on environments.
+ Spring 4 @Conditional annotation allows Developers to define user-defined strategies for conditional checking. @Conditional can be used for conditional bean registrations.

https://javapapers.com/spring/spring-conditional-annotation/
```


## Conditional

### @ConditionalOnBean X @ConditionalOnMissingBean

+ Probably `@ConditionalOnMissingBean` is evaluate after `@ConditionalOnBean`