#!/bin/sh
# start.sh

# specifically made for raspberry pi

BOT_PATH=home/pi/moby-bot-t-1000/
JAR_PATH=out/artifacts/moby_T_1000_jar

sleep 10

cd /

while [ true ]
    do

	cd /
	cd $BOT_PATH
	git pull
	
	cd $JAR_PATH
    java -jar moby-T-1000.jar
    
    sleep 5
    
done