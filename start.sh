#!/bin/bash
set -e

# Define variables for your project
PROJECT_NAME1="configserver"
PROJECT_NAME2="accounts"
PROJECT_NAME3="cards"
PROJECT_NAME3="loans"

echo "### Starting Gradle clean and build for '$PROJECT_NAME1' ###"

(cd configserver && ./gradlew clean && ./gradlew build && ./gradlew bootRun)

echo "### Starting Docker build and compose for '$PROJECT_NAME2' ###"

(cd accounts && ./gradlew clean && ./gradlew build && ./gradlew bootRun)

echo "### Starting Docker build and compose for '$PROJECT_NAME3' ###"

(cd cards && ./gradlew clean && ./gradlew build && ./gradlew bootRun)

echo "### Starting Docker build and compose for '$PROJECT_NAME4' ###"

(cd cards && ./gradlew clean && ./gradlew build && ./gradlew bootRun)
