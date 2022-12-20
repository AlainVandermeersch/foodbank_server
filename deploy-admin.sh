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
    docker build -t foodbank-client --build-arg=prod .

cd $CURRENT_DIRECTORY
rm -rf target/foodbank-client

docker run --rm \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "$PWD:$PWD" \
    -w="$PWD" \
    docker/compose:1.29.1 --env-file=.env.ovh-prod up -d --build backend
docker run --rm \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "$PWD:$PWD" \
    -w="$PWD" \
    docker/compose:1.29.1 --env-file=.env.ovh-prod up -d

