# JWE

## org.bitbucket.b_c:jose4j
+ https://bitbucket.org/b_c/jose4j/wiki/JWE%20Examples

```shell

openssl genrsa -out rsa-2048.pem 2048

openssl genrsa -out private.pem 2048
openssl rsa -in private.pem -outform PEM -pubout -out public.pem 

openssl genrsa -out private_key.pem 4096
openssl rsa -pubout -in private_key.pem -out public_key.pem


EC
openssl ecparam -name prime256v1 -genkey -noout -out ec-prime256v1.pem

OCT
openssl rand -out oct-128-bit.bin 16

RSA
openssl genrsa -out rsa-2048.pem 2048


openssl req -x509 -sha256 -newkey rsa:2048 -keyout ca.key -out ca.pem -subj "/C=BR/ST=SP/L=IBATE/O=ANDRELUGOMES/OU=GITHUB/CN=Root" -passout pass:changeit
```
