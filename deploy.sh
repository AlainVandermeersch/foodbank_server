#!/bin/bash

git pull --rebase origin master
git remote show upstream || git remote add upstream https://github.com/AlainVandermeersch/foodbank_server.git
git pull --rebase upstream master

rm -rf target
mkdir target
cd target && \
    git clone https://github.com/AlainVandermeersch/foodbank-client.git && \
    docker build -t foodbank-client .
cd ..

docker run --rm \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "$PWD:$PWD" \
    -w="$PWD" \
    docker/compose:1.29.1 --env-file=.env.gcloud-dev up -d