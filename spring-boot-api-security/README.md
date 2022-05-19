# Spring API Security

+ OAuth 2.0 uisng Keycloak

## Start Keycloak

```shell
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev
```

Setup Realm and Client Credentials

## spring-rest-token-validation

Validade a token by hand, without framework

+ Userinfo endpoint
+ Token Introspection endpoint

```shell
./script.sh
```