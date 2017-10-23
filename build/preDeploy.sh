#!/bin/bash

source /etc/profile

echo "begin rebuild"

echo $PATH

env

cd /home/gagaprince/work/server/BrainStromWeb

ls

git pull

mvn clean -Plocal package

#如果是多台机器则要循环执行下列shell
sudo scp target/ROOT.war root@wzdmeituan02:/usr/share/tomcat/webapps/

ssh gagaprince@wzdmeituan02   << remotessh

sudo rm -rf /usr/share/tomcat/webapps/ROOT

sh /home/gagaprince/build/deploy.sh

exit

remotessh

sudo rm -rf /usr/share/tomcat/webapps/ROOT

sudo mv target/ROOT.war /usr/share/tomcat/webapps/



