#!/usr/bin/env bash

BRANCH=$(git symbolic-ref --short HEAD | tr -cd '[:alnum:]._-')

if [ -z "$BRANCH" ]; then
  BRANCH=$(git rev-parse HEAD)
fi

if [[ "master" == "$BRANCH" ]]; then
  echo "Integration tests cannot be run on master or they will fail to test the Docker build properly. Switch to another branch like 'update-temp' and try again."
  exit 1
fi

CWD=`basename $PWD`

if [ "$CWD" == "testing" ]; then
  cd ..
fi

echo "Cleaning Gradle functions..."
find ../aws-greengrass-lambda-functions -name "build.gradle" -exec sh -c 'cd $(dirname {}); ./gradlew clean' \;

if [ $? -ne 0 ]
then
  echo "Gradle functions were NOT cleaned properly!"
fi

set -e

echo "Cleaning venv directories..."
find ../aws-greengrass-lambda-functions/functions -name "venv" -exec rm -rf {} \;

echo "Updating Dockerhub..."
./testing/push-to-dockerhub.sh

echo "Cleaning build directories and credentials directory..."
rm -rf build out credentials
echo "Starting integration tests..."
./gradlew clean build integrationTest $@ --info
echo "Finished integration tests. Cleaning the out directory and the credentials directory."
rm -rf out credentials
