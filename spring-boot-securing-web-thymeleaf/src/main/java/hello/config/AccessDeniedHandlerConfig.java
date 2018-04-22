package hello.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class AccessDeniedHandlerConfig {

   /*@Bean
   public AccessDeniedHandler accessDeniedHandler(){
       return new AccessDeniedHandler() {

           @Override
           public void handle (final HttpServletRequest request, final HttpServletResponse response,
                   final AccessDeniedException accessDeniedException) throws IOException {
               response.sendRedirect("/access-denied");
           }
       };
   }*/

   @Bean
   public AccessDeniedHandler accessDeniedHandler(){
       return (HttpServletRequest request,  HttpServletResponse response,
                    AccessDeniedException accessDeniedException) -> response.sendRedirect("/access-denied");
   }

}
