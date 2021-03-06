#!/bin/bash
echo 'Starting backend...'
java -jar backend-service/target/backend-service-1.0-SNAPSHOT.jar &

sleep 3

echo 'Starting crawler...'
java -jar crawler-service/target/crawler-service-1.0-SNAPSHOT.jar &

sleep 3

echo 'Starting frontend...'
java -jar frontend-service/target/frontend-service-1.0-SNAPSHOT.jar &

sleep 5
echo 'Servers started.'