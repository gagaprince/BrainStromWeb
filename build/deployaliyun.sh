#!/bin/bash

source /etc/profile

echo "begin rebuild"

echo $PATH

env

cd /root/work/BrainStromWeb/

ls

git pull

mvn clean -Paliyun package

rm -rf /usr/share/tomcat7/webapps1/*

mv target/brain /usr/share/tomcat7/webapps1/

echo 'kill brain'

killall -9 java

sh /usr/share/tomcat7/bin/startup.sh 
