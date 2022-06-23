# JWS

## shellscript JWT
+ https://willhaley.com/blog/generate-jwt-with-bash/

```shell
./jwt.sh
```


```shell
echo "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCIsImtpZCI6IjAwMDEiLCJpc3MiOiJCYXNoIEpXVCBHZW5lcmF0b3IiLCJpYXQiOjE2NTQyNTYzMjksImV4cCI6MTY1NDI1NjMzMH0.eyJJZCI6MSwiTmFtZSI6IkhlbGxvLCB3b3JsZCEiLCJ1c2VyIjoiQW5kcsOpZmZmZmZmZmZmZmYifQ.a4D-eQkeaFL41l210zDSzQBFxrtHmRrZ6W6yMi-sl9Qtad5yBN5pHPS_WI8lHJKe" | ./verify-jwt.sh

```

## Sign using RSA

+ https://github.com/cisco/node-jose
+ https://github.com/cisco/node-jose/blob/master/README.md#keys-used-for-signing-and-verifying

```shell
openssl genrsa -out private_key.pem 4096
openssl rsa -pubout -in private_key.pem -out public_key.pem

openssl genrsa -out other_private_key.pem 4096
openssl rsa -pubout -in other_private_key.pem -out other_public_key.pem
```

sign using private key
```shell
node jws.js 
```

verify using public key
```shell
node verify-jws.js
```