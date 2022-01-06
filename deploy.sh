#!/bin/bash

CURRENT_DIRECTORY=$(pwd)

git pull --rebase origin master
git remote show upstream || git remote add upstream https://github.com/AlainVandermeersch/foodbank_server.git
git pull --rebase upstream master

rm -rf target
mkdir target
cd target && \
    git clone https://github.com/AlainVandermeersch/foodbank-client.git && \
    cd foodbank-client && \
    docker build -t foodbank-client .

cd $CURRENT_DIRECTORY
rm -rf target/foodbank-client

cd target && \
    git clone git@gitlab.com:dubus.dominique/food-it.git && \
    cd food-it && \
    git checkout keycloak-migration && \
    cd docker/wildfly && \
    docker build -t foodbank-stock-app-wildfly .
cd $CURRENT_DIRECTORY

docker run --rm \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "$PWD:$PWD" \
    -w="$PWD" \
    docker/compose:1.29.1 --env-file=.env.gcloud-dev up -d --build backend
docker run --rm \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "$PWD:$PWD" \
    -w="$PWD" \
    docker/compose:1.29.1 --env-file=.env.gcloud-dev up -d

cd target/food-it && \
    /bin/bash build.sh && \
    /bin/bash deploy.sh --password $WILDFLY_ADMIN_PASS
cd $CURRENT_DIRECTORY
