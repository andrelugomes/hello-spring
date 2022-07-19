# Spring Security OAuth2 Authorization Redirect WEB

+ https://www.appsdeveloperblog.com/keycloak-authorization-code-grant-example/

## Client 

`spring-boot-starter-oauth2-client`

+ https://ravthiru.medium.com/springboot-oauth2-with-keycloak-as-provider-c31b2897e913
+ https://www.youtube.com/watch?v=3BUm0ZlzBLk
+ https://www.youtube.com/watch?v=NvNnE67wMnQ
+ https://github.com/SaiUpadhyayula/spring-security-oauth2-keycloak-demo/blob/master/oauth2-authorization-code-demo/pom.xml
+ https://github.com/ravthiru/keycloak-recepies/blob/master/springboot-keycloak/build.gradle
+ https://www.baeldung.com/spring-security-oauth-resource-server

## Access

[Access](http://localhost:8085)

user1 / user1

## Flow

GET: http://localhost:8085/index -> 302 -> Location: http://localhost:8085/oauth2/authorization/app-authorization-redirect

GET: http://localhost:8085/oauth2/authorization/app-authorization-redirect -> 302 -> Location: http://localhost:8080/realms/api-authorization/protocol/openid-connect/auth?response_type=code&client_id=spring-security-oauth2-authorization-redirect&scope=openid%20profile%20email&state=8xqFEp6tTSlKgXIQs2aWn11rXv6U81Pcazc6j7dVYHA%3D&redirect_uri=http://localhost:8085/login/oauth2/code/app-authorization-redirect&nonce=uSaMC9qBUANLlKPkbeHQxx3cekniCCGvXPUpDd2ns2s

GET: http://localhost:8080/realms/api-authorization/protocol/openid-connect/auth?response_type=code&client_id=spring-security-oauth2-authorization-redirect&scope=openid%20profile%20email&state=8xqFEp6tTSlKgXIQs2aWn11rXv6U81Pcazc6j7dVYHA%3D&redirect_uri=http://localhost:8085/login/oauth2/code/app-authorization-redirect&nonce=uSaMC9qBUANLlKPkbeHQxx3cekniCCGvXPUpDd2ns2s
-> 200 

POST: http://localhost:8080/realms/api-authorization/login-actions/authenticate?session_code=8N5RV1LD-C_RX_Nc68vkyugTIOyfxmZJaZXxgtyh10c&execution=11c6f962-4bde-4769-aeaa-8174829aa109&client_id=spring-security-oauth2-authorization-redirect&tab_id=QBx7qWlcvMs -> 302 -> Location: http://localhost:8085/login/oauth2/code/app-authorization-redirect?state=8xqFEp6tTSlKgXIQs2aWn11rXv6U81Pcazc6j7dVYHA%3D&session_state=3322021d-c22c-49f6-bdf6-df11ac818b34&code=bfa6c632-d7ba-472e-b2ef-e40142f82ea9.3322021d-c22c-49f6-bdf6-df11ac818b34.865fc0ee-e26d-4516-a152-4a7a76484712

Request URL: http://localhost:8085/login/oauth2/code/app-authorization-redirect?state=8xqFEp6tTSlKgXIQs2aWn11rXv6U81Pcazc6j7dVYHA%3D&session_state=3322021d-c22c-49f6-bdf6-df11ac818b34&code=bfa6c632-d7ba-472e-b2ef-e40142f82ea9.3322021d-c22c-49f6-bdf6-df11ac818b34.865fc0ee-e26d-4516-a152-4a7a76484712 -> 302 -> Location: http://localhost:8085/index

POST http://localhost:8080/realms/api-authorization/protocol/openid-connect/token
```text
("application/x-www-form-urlencoded;charset=UTF-8") 

grant_type=[authorization_code], 
code=[bfa6c632-d7ba-472e-b2ef-e40142f82ea9.3322021d-c22c-49f6-bdf6-df11ac818b34.865fc0ee-e26d-4516-a152-4a7a76484712], 
redirect_uri=[http://localhost:8085/login/oauth2/code/app-authorization-redirect]
```

GET : http://localhost:8085/index -> 200 


