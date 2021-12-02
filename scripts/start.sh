#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/step3

PROJECT_NAME=vincent-example-aws

echo "> copy build files"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY

echo "> deploy new application"
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | grep -v plain | tail -n 1)

echo "> add executable privileges at $JAR_NAME"

chmod +x $JAR_NAME

echo "> execute $JAR_NAME"

IDLE_PROFILE=$(find_idle_profile)

echo "> execute the $JAR_NAME by $IDLE_PROFILE as profile"
nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-real-db.properties \
        -Dspring.profiles.active=$IDLE_PROFILE \
        $REPOSITORY/$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
