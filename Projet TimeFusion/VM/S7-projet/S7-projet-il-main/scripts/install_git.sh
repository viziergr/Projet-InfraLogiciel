#!/bin/bash

## install web server with php

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - install git - "$IP

echo "=> [1]: Installing required packages..."
apt-get install $APT_OPT \
  git \
  >> $LOG_FILE 2>&1

echo "=> [2]: Git configuration"

cd /var/www/html/
mkdir git
cd git
git clone 

echo "END - install git"

