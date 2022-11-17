#!/bin/bash

CURRENT_DIRECTORY=$(pwd)
cd target && \
    git clone git@gitlab.com:dubus.dominique/food-it.git && \
    cd food-it && \
    git checkout new-version && \
    cd docker/wildfly && \
    docker build --no-cache -t foodbank-stock-app-wildfly .

cd $CURRENT_DIRECTORY


