# JWK
+ https://github.com/cisco/node-jose#importing-and-exporting-a-single-key
+ https://stackoverflow.com/questions/69179822/jwk-key-creation-with-x5c-and-x5t-parameters

## Certificate

```shell
openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 -keyout CA.key -out CA.crt -subj "/C=BR/ST=SP/L=IBATE/O=ANDRELUGOMES/OU=GITHUB/CN=Root CA"

openssl req -newkey rsa:4096 -nodes -keyout server.key -out server.csr -subj "/C=BR/ST=SP/L=IBATE/O=ANDRELUGOMES/OU=GITHUB/CN=Server"

openssl x509 -req -in server.csr -CA CA.crt -CAkey CA.key -CAcreateserial -out server.crt -sha256
```

## RSA KEYS from Certificate

Read the public key from a certificate
```shell
openssl x509 -pubkey -noout -in server.crt
     
-----BEGIN PUBLIC KEY-----
MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAw8sgVF9hWR7ezl1FnPiS
Zuuwp/JKhjPscsmlRyoib56dqb7OJSX/an1IHgRMFiubxCfTtxDVxn0ckQCevfex
jP3uVZbrGi2ecB1xFfmUJi+oxZyor6iRABj/b8egPgw5lJ0fYnhc9SjB9G9GUweN
AVM5+KWR2WcpuFOl9oNW+BynOvPCagSd/RtF1+1N+7G6wB9EJ3w6WyWBzqI49heo
862aXNJW8nDjtR0caf+BTB/33032JgnjYr/0mwITVgpEdfWWjK7TemvoYwQMqbAf
Y51K8aikAZD2ioc3Gu3r1bLvBg+cFRsbSXtDl54Xz/LuG4RR67WAhSasXHEih8ve
FBl39tF3k8V+tCdmXSFk1O1lFW+6+M21G1S14eG2PLKNsCi+qvwV+mmWPNkkslb9
P5A69Ghku5mAf4WVq9ipCLjRAGE1krEHEn7fJwydFB3cksWnuWOxT8Xj0shsjGOW
9lxhKJfRLGsUYOucoAga+XpsimJDBuTMSqNvepxXP5ONTuAiM2eRJK/EnoxeF7K2
ZJZ8mWamTyCNJQd2Dzo9L2IRKsbyCAiy38Q2r0dLUv8CLMeMrmHM0Ps/96vzIeG0
CWllcJxivUxRjyUxJT5ag5pFnRCZU0ppi9bsVzg0GD/5CcGVtIeyxg1RgVtGBoTh
rN2zxUOdAS99On4bPkY2KkkCAwEAAQ==
-----END PUBLIC KEY-----


openssl x509 -pubkey -noout -in server.crt > server-pub-key.pem
```

### x5c - Encoded DER

```shell
openssl x509 -in server.crt -outform DER | base64 

MIIFTjCCAzYCFFMqScOmAd4FOqGJQQvRe7v4CcDXMA0GCSqGSIb3DQEBCwUAMGQxCzAJBgNVBAYT
AkJSMQswCQYDVQQIDAJTUDEOMAwGA1UEBwwFSUJBVEUxFTATBgNVBAoMDEFORFJFTFVHT01FUzEP
MA0GA1UECwwGR0lUSFVCMRAwDgYDVQQDDAdSb290IENBMB4XDTIyMDcyMTE4Mzc0NVoXDTIyMDgy
MDE4Mzc0NVowYzELMAkGA1UEBhMCQlIxCzAJBgNVBAgMAlNQMQ4wDAYDVQQHDAVJQkFURTEVMBMG
A1UECgwMQU5EUkVMVUdPTUVTMQ8wDQYDVQQLDAZHSVRIVUIxDzANBgNVBAMMBlNlcnZlcjCCAiIw
DQYJKoZIhvcNAQEBBQADggIPADCCAgoCggIBAMPLIFRfYVke3s5dRZz4kmbrsKfySoYz7HLJpUcq
Im+enam+ziUl/2p9SB4ETBYrm8Qn07cQ1cZ9HJEAnr33sYz97lWW6xotnnAdcRX5lCYvqMWcqK+o
kQAY/2/HoD4MOZSdH2J4XPUowfRvRlMHjQFTOfilkdlnKbhTpfaDVvgcpzrzwmoEnf0bRdftTfux
usAfRCd8Olslgc6iOPYXqPOtmlzSVvJw47UdHGn/gUwf999N9iYJ42K/9JsCE1YKRHX1loyu03pr
6GMEDKmwH2OdSvGopAGQ9oqHNxrt69Wy7wYPnBUbG0l7Q5eeF8/y7huEUeu1gIUmrFxxIofL3hQZ
d/bRd5PFfrQnZl0hZNTtZRVvuvjNtRtUteHhtjyyjbAovqr8FfppljzZJLJW/T+QOvRoZLuZgH+F
lavYqQi40QBhNZKxBxJ+3ycMnRQd3JLFp7ljsU/F49LIbIxjlvZcYSiX0SxrFGDrnKAIGvl6bIpi
QwbkzEqjb3qcVz+TjU7gIjNnkSSvxJ6MXheytmSWfJlmpk8gjSUHdg86PS9iESrG8ggIst/ENq9H
S1L/AizHjK5hzND7P/er8yHhtAlpZXCcYr1MUY8lMSU+WoOaRZ0QmVNKaYvW7Fc4NBg/+QnBlbSH
ssYNUYFbRgaE4azds8VDnQEvfTp+Gz5GNipJAgMBAAEwDQYJKoZIhvcNAQELBQADggIBAKmGAT2/
EA6INzF2k7kPHMK4YdXMcH67zWHo8VX8D1WJ08/nGsnbtdsoqlVCSL6e7v2I/eE4ccLDznLwTIhL
d+YbbER4bdxGb3vHZZkRrFLmArgqamQ8NZuVgmrGt7w1bt9gFRLX5+0aKNDYKIyka8F2tgwG8KVk
Hffln5Lur1hoh/loD2him+pj6X/lQqzwq+uUhXXVIRR8kFU5P5S5xz9xtkKOKQtdEo4gIGdBN8b8
qI/w3jyXcHt7+jq+infJMUBO7lEsvGsQA7XJ7ktq6/ZTg2fchYkQU2BlPBo/9v0nzGmsDCq3QG0L
V2N5bsLVE8wRU1+gTwshxddSl4wRSPcVQaKB8f+uOZ+GhE2EfLlUlYP9yCBOJoLG/tenK6BOHX2z
jMV7l3c+3NTIhyrfHr5KcTZa67MJjeeYRW6nyztYw9jWWEWquRrcCJMjAVHkC4FDxFs+UNNx5MkX
byvdNlN+Cxx9j6EX5bpYe08KDfh0CuL4JmZtApZwY21iiKknYX/c+18aCMFPgH0qCDwBD/1hGLX8
Px/eZts/5La4ppvaYnqyjiNwTP6kSDKvZGY38CWmh0FOID80m5xj72yB6X64SYnhx2Zx4oVafdTX
PGgazH5KBcKC5iZ9pbGWStG4cdjM+8D7Ws0FLQ+mP76DW/hGdnggn6YrQlffb9GbJh4/

```

### x5t - fingerprint

```shell
openssl x509 -in server.crt -noout -fingerprint

SHA1 Fingerprint=06:73:BF:DA:48:25:BA:0B:23:4D:01:92:6A:7B:B7:46:4C:FF:C7:C1

openssl x509 -in server.crt -noout -fingerprint | cut -d "=" -f 2 | tr -d ':' | base64 | tr '/+' '_-' | tr -d '='
MDY3M0JGREE0ODI1QkEwQjIzNEQwMTkyNkE3QkI3NDY0Q0ZGQzdDMQo
```

## Only RSA Keys

```shell
openssl genrsa -out private_key.pem 4096
openssl rsa -pubout -in private_key.pem -out public_key.pem
```

## JWK to PEM
+ https://8gwifi.org/jwkconvertfunctions.jsp

```shell
node jwk-2-pem.js
```

# JWKS
+ https://sometimes-react.medium.com/jwks-and-node-jose-9273f89f9a02
+ https://openbanking.sandbox.api.pagseguro.com/auth/realms/open-banking/protocol/openid-connect/certs


```shell
curl -k https://openbanking.sandbox.api.pagseguro.com/auth/realms/open-banking/protocol/openid-connect/certs | jq . >> jwks.json
```