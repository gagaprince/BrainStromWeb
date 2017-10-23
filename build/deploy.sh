#!/bin/bash

source /etc/profile

echo 'kill BrainStrom'

ps -ef|grep tomcat |grep -v grep|awk '{print $2}'|xargs sudo kill -9

sudo tomcat start


