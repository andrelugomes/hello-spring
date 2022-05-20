#!/bin/bash

client_id=api-client-1
client_secret=FG4BnmvD69BwD1fbt8Gb9yuI8VN0PsUM

ACCESS_TOKEN=$(curl -X POST "http://localhost:8080/realms/api-authorization/protocol/openid-connect/token" \
             --header "Content-Type: application/x-www-form-urlencoded" \
             --data-urlencode "client_id=${client_id}" \
             --data-urlencode "client_secret=${client_secret}"  \
             --data-urlencode "grant_type=client_credentials" | jq -j .access_token)

echo "Request without ACCESS_TOKEN"

#curl -v localhost:8082/secure

echo "Request using ACCESS_TOKEN"

curl -v -H "Autorization: Bearer ${ACCESS_TOKEN}" localhost:8082/secure

