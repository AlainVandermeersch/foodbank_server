#!/bin/bash

CURRENT_DIRECTORY=$(pwd)
cd target && \
    git clone git@gitlab.com:dubus.dominique/food-it.git && \
    cd food-it && \
    git checkout new-version && \
    cd docker/wildfly && \
    docker build --no-cache -t foodbank-stock-app-wildfly .

cd $CURRENT_DIRECTORY

cd target/food-it && \
    /bin/bash deploy.sh --libext /home/ubuntu/lib-ext --password $WILDFLY_ADMIN_PASS
cd $CURRENT_DIRECTORY
sudo rm -rf target/food-it
