#!/user/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh

IDLE_PORT=$(find_idle_port)

echo "> Health Check Start!"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://localhost:$IDLE_PORT/profile"
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l)

  if [ $(UP_COUNT) -ge 1 ]
  then
    echo "> SUCCESS Health Check"
    switch_proxy
    break
  else
    echo "> Unknown response of Health Check or Not running application"
    echo "> Health check: ${RESPONSE}"
  fi

  if [ ${RETRY_COUNT} -eq 10 ]
  then
    echo "> FAIL Health Check"
    echo "> Finished deploy but does not link the nginx"
    exit 1
  fi

  echo "> Connection fail at Health check. retry..."
  sleep 10
done