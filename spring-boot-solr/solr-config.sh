#!/bin/bash

echo "Iniciando container Solr"
sudo docker rm -f solr;
sudo docker run --name solr -d -p 8983:8983 -t solr solr-create -c docs;

sleep 3s;
