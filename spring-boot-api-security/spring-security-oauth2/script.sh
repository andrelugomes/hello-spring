#!/bin/bash

client_id=api-client-1
client_secret=FG4BnmvD69BwD1fbt8Gb9yuI8VN0PsUM

ACCESS_TOKEN=$(curl -X POST "http://localhost:8080/realms/api-authorization/protocol/openid-connect/token" \
             --header "Content-Type: application/x-www-form-urlencoded" \
             --data-urlencode "client_id=${client_id}" \
             --data-urlencode "client_secret=${client_secret}"  \
             --data-urlencode "grant_type=client_credentials" | jq -j .access_token)

echo "User INFO"

curl -X POST "http://localhost:8080/realms/api-authorization/protocol/openid-connect/userinfo" \
      -H "Content-Type: application/x-www-form-urlencoded" \
      -d "access_token=${ACCESS_TOKEN}" | jq .

echo "Token Introspection"

curl -X POST "http://localhost:8080/realms/api-authorization/protocol/openid-connect/token/introspect" \
      -u "${client_id}:${client_secret}" \
      -H "Content-Type: application/x-www-form-urlencoded" \
      -d "token=${ACCESS_TOKEN}" | jq .

echo "Call Spring API - Userinfo"

curl -v -H "Authorization: ${ACCESS_TOKEN}" localhost:8081/secure-user

echo "Call Spring API - Tokeninstropection"

curl -v -H "Authorization: ${ACCESS_TOKEN}" localhost:8081/secure-introspection