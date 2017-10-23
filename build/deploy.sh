#!/bin/bash

source /etc/profile

echo 'kill BrainStrom'

killall -9 java

sudo tomcat start


