# Spring Security TLS and mTLS

+ https://www.baeldung.com/spring-tls-setup
+ https://www.baeldung.com/x-509-authentication-in-spring-security
+ https://medium.com/ing-tech-romania/a-simple-mtls-guide-for-spring-boot-microservices-c6bfc9878369

## Configuration

Create KeyStore a.k.a server cert
```shell
 
keytool -genkeypair -alias server -keyalg RSA -keysize 4096 -validity 365 -dname "CN=Server,OU=Server,O=ANDRELUGOMES,L=,S=SP,C=BR" -keypass changeit -keystore server.p12 -storeType PKCS12 -storepass changeit
```

Create TrustStore a.k.a trueted client certs
```shell

keytool -genkeypair -alias client1 -keyalg RSA -keysize 4096 -validity 365 -dname "CN=Client1,OU=Client1,O=andre,L=,S=SP,C=BR" -keypass changeit -keystore client1.p12 -storeType PKCS12 -storepass changeit

keytool -exportcert -alias client1 -file client1.cer -keystore client1.p12 -storepass changeit

openssl pkcs12 -in client1.p12 -nokeys -out client1.pem
openssl pkcs12 -in client1.p12 -nodes -nocerts -out client1.key
```

Put the client1.cer into TrustStore
```shell

P12 >
keytool -importcert -keystore server-truststore.p12 -alias client1-public -file client1.cer -storepass changeit -noprompt


keytool -importcert -keystore server-truststore.p12 -alias client1-public -file client2.pem -storepass changeit -noprompt

JKS >
keytool -importcert -keystore server-truststore.jks -alias client1 -file client1.cer -storepass changeit -noprompt 

keytool -importcert -keystore server-truststore.jks -alias client2 -file client2.pem -storepass changeit -noprompt
```

```shell
curl -vk https://localhost:8084/api/secure

curl -vk --cert src/main/resources/client1.pem --key src/main/resources/client1.key  https://localhost:8084/api/secure
```
 