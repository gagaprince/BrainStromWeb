#!/bin/bash

source /etc/profile

echo "begin rebuild"

echo $PATH

env

cd /root/work/blog/

ls

git pull

mvn clean -Paliyun package

mv target/ROOT.war /usr/share/tomcat7/webapps/

echo 'kill blog'

ps -ef|grep tomcat7 |grep -v grep|awk '{print $2}'|xargs kill -9

cd /usr/share/tomcat7/

sh bin/startup.sh
