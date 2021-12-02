#!/user/bin/env bash

ABSDIR=$(dirname $ABSPATH)

source ${ABSDIR}/profile.sh

IDLE_PORT=$(find_idle_port)

echo "> checked application pid as running with $IDLE_PORT"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
  echo "> if not exist running application then does not stop"
else
  echo "> kill -15 $IDLE_PID"
  sleep 5
fi
