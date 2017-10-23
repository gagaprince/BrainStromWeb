#!/bin/bash

source /etc/profile

echo "begin rebuild"

echo $PATH

env

cd /home/gagaprince/work/server/BrainStromWeb

ls

git pull

mvn clean -Plocal package

sudo rm -rf /usr/share/tomcat/webapps/ROOT

sudo mv target/ROOT.war /usr/share/tomcat/webapps/

