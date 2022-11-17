#!/bin/bash

CURRENT_DIRECTORY=$(pwd)
cd target && \
    git clone git@gitlab.com:dubus.dominique/food-it.git && \
    cd food-it && \
    git checkout new-version && \
    cd docker/wildfly && \
    docker build --no-cache -t foodbank-stock-app-wildfly .

cd $CURRENT_DIRECTORY
docker run --rm \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "$PWD:$PWD" \
    -w="$PWD" \
    docker/compose:1.29.1 --env-file=.env.ovh-dev up -d

cd target/food-it && \
    /bin/bash deploy.sh --libext /home/ubuntu/lib-ext --password $WILDFLY_ADMIN_PASS
cd $CURRENT_DIRECTORY
sudo rm -rf target/food-it
