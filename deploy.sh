#!/bin/bash

cd target && \
    git clone git@github.com:AlainVandermeersch/foodbank-client.git && \
    npm run build-prod && \
    docker build -t foodbank-client . && \
    cd ..

git pull --rebase git@github.com:AlainVandermeersch/foodbank_server.git master

docker-compose up -d