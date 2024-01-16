#!/bin/bash

## déplace les fichiers du site

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Installation du certificat SSL - "$IP

# echo "=> [1] - Installation de snapd"
# sudo apt install snapd -y >> $LOG_FILE 2>&1
# echo "=> [2] - Installation de snap core"
# sudo snap install core >> $LOG_FILE 2>&1
# echo "=> [3] - Suppression de certbot"
# sudo apt-get remove certbot >> $LOG_FILE 2>&1
# echo "=> [4] - Installation de certbot"
# sudo snap install --classic certbot >> $LOG_FILE 2>&1
# echo "=> [5] - Préparation de la commande Certbot"
# sudo ln -s /snap/bin/certbot /usr/bin/certbot 
# echo "=> [6] - Installation du certificat SSL"
# sudo certbot --apache --register-unsafely-without-email --agree-tos

echo "END - Installation du certificat SSL - "$IP