#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=vincent-example-aws

echo "> copy build file"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> check pid in running application"

CURRENT_PID=$(pgrep -fl vincent-example-aws | grep jar | awk '{print $1}')

echo "running application pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> not found running application then do not stop"
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5


fi

echo "> deploy new application"


JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | grep -v plain |
tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> add executable at $JAR_NAME"

chmod +x $JAR_NAME

echo "> execute $JAR_NAME"

nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-real-db.properties \
        -Dspring.profiles.active=real \
        $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
