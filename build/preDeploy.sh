#!/bin/bash

source /etc/profile

echo "begin rebuild"

echo $PATH

env

cd /home/gagaprince/work/server/BrainStromWeb

ls

git pull

mvn clean -Plocal package

sudo mv target/ROOT.war /usr/share/tomcat/webapps/

echo 'kill BrainStrom'

ps -ef|grep tomcat |grep -v grep|awk '{print $2}'|xargs sudo kill -9
