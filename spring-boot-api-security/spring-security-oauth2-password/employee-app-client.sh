#!/bin/bash

echo "Username: $1";
echo "password: $2";

client_id=employee-app-client
client_secret=jVjv6kWjkday5scdlHbOychbhKtVMGn4
user=$1
password=$2

ACCESS_TOKEN=$(curl -X POST "http://localhost:8080/realms/api-authorization/protocol/openid-connect/token" \
             --header "Content-Type: application/x-www-form-urlencoded" \
             --data-urlencode "client_id=${client_id}" \
             --data-urlencode "client_secret=${client_secret}" \
             --data-urlencode "username=${user}"  \
             --data-urlencode "password=${password}"  \
             --data-urlencode "grant_type=password" | jq -j .access_token)

echo $ACCESS_TOKEN
echo -e "\n"
echo -e "Calling /api/user\n"
curl -X GET http://localhost:8082/api/user --header "Authorization: bearer ${ACCESS_TOKEN}"
echo -e "\n"
echo -e "Calling /api/admin\n"
curl -X GET http://localhost:8082/api/admin --header "Authorization: bearer ${ACCESS_TOKEN}"
echo -e "\n"
echo -e "Calling /api/all-users\n"
curl -X GET http://localhost:8082/api/all-users --header "Authorization: bearer ${ACCESS_TOKEN}"