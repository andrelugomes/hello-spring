# Spring Security mTLS using Root CA

+ https://erfin-feluzy.medium.com/tutorial-secure-your-api-with-x509-mutual-authentication-with-spring-boot-on-openshift4-416a00a47af8

## Configuration

Create Root CA
```shell
openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 -keyout CA.key -out CA.crt -subj "/C=BR/ST=SP/L=IBATE/O=ANDRELUGOMES/OU=GITHUB/CN=Root CA"
```

Create Server Certificate request, Certificate and KeyStore
```shell
openssl req -newkey rsa:4096 -nodes -keyout server.key -out server.csr -subj "/C=BR/ST=SP/L=IBATE/O=ANDRELUGOMES/OU=GITHUB/CN=Server"

openssl x509 -req -in server.csr -CA CA.crt -CAkey CA.key -CAcreateserial -out server.crt -sha256

openssl pkcs12 -export -out server-keystore.p12 -name "server" -inkey server.key -in server.crt
```

Put the CA.crt into TrustStore
```shell
keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file CA.crt -keystore server-truststore.jks
```

Create Client Certificate request and Certificate
```shell
openssl req -newkey rsa:4096 -nodes -keyout client.key -out client.csr -subj "/C=BR/ST=SP/L=IBATE/O=ANDRELUGOMES/OU=GITHUB/CN=Client"

openssl x509 -req -in client.csr -CA CA.crt -CAkey CA.key -CAcreateserial -out client.crt -sha256
```

```shell
curl -vk https://localhost:8085/api/secure

curl -vk --cert src/main/resources/client.crt --key src/main/resources/client.key  https://localhost:8085/api/secure
```

# Extra
## .p12

```shell
#or to .p12
keytool -importkeystore -srckeystore server-truststore.jks -srcstoretype JKS -deststoretype PKCS12 -destkeystore server-truststore.p12

#add new CA
keytool -importcert -trustcacerts -noprompt -alias new-ca -storetype pkcs12 -file CA.crt -keystore server-truststore.p12  


#doesn't works yet
OR
openssl pkcs12 -export -out server-truststore.p12 -name "server-truststore" -inkey CA.key -in CA.crt

OR
cat CA.key > CA-key.pem
openssl x509 -in CA.crt -out CA.pem -outform PEM
openssl pkcs12 -export -out server-truststore.p12 -inkey CA-key.pem -in CA.pem
```
 