#!/bin/bash

source /etc/profile

echo "begin rebuild"

echo $PATH

env

cd /root/work/BrainStromWeb/

ls

git pull

mvn clean -Paliyun package

rm -rf /usr/share/tomcat7/webapps1/brain/ 

rm -rf /usr/share/tomcat7/webapps1/brain.war

mv target/brain.war /usr/share/tomcat7/webapps1/

echo 'kill brain'

ps -ef|grep tomcat7 |grep -v grep|awk '{print $2}'|xargs kill -9

cd /usr/share/tomcat7/

sh bin/startup.sh
