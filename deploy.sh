#!/bin/bash
git pull --ff-only
./gradlew build
docker build .
docker-compose rm -f
docker-compose up --build -d