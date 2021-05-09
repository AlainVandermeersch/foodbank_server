#!/bin/bash

git pull --rebase origin master
git remote show upstream || git remote add upstream https://github.com/AlainVandermeersch/foodbank_server.git
git pull --rebase upstream master

mkdir target && \
    cd target && \
    git clone git@github.com:AlainVandermeersch/foodbank-client.git && \
    npm run build-prod && \
    docker build -t foodbank-client . && \
    cd ..

docker-compose up -d