#!/bin/bash

## déplace les fichiers du site

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Installation du certificat SSL - "$IP

echo "=> [1] - Installation de snapd"
apt-get install $APT_OPT \
  snapd \
  core \
  >> $LOG_FILE 2>&1

echo "=> [2] - Suppression de certbot"
sudo apt-get remove certbot
echo "=> [3] - Installation de certbot"
sudo snap install --classic certbot
echo "=> [4] - Préparation de la commande Certbot"
sudo ln -s /snap/bin/certbot /usr/bin/certbot
echo "=> [5] - Installation du certificat SSL"
sudo certbot --apache --register-unsafely-without-email --agree-tos

echo "END - Installation du certificat SSL - "$IP