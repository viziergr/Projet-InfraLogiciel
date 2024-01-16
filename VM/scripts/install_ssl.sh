#!/bin/bash

## déplace les fichiers du site

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Installation du certificat SSL - "$IP
echo "=> [1] - Installation de snapd"
sudo apt install snapd
echo "=> [2] - Installation de snap core"
sudo snap install core
echo "=> [3] - Suppression de certbot"
sudo apt-get remove certbot
echo "=> [4] - Installation de certbot"
sudo snap install --classic certbot
echo "=> [5] - Préparation de la commande Certbot"
sudo ln -s /snap/bin/certbot /usr/bin/certbot
echo "=> [6] - Installation du certificat SSL"
sudo certbot --apache

echo "END - Installation du certificat SSL - "$IP