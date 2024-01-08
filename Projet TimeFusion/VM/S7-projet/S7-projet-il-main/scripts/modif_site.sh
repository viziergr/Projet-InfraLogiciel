#!/bin/bash

## déplace les fichiers du site

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Deplacement des fichiers - "$IP

cd /var/www/html/
mkdir git
cd git
git clone git@github.com:viziergr/Projet-InfraLogiciel.git

cd /var/www/html/git/Projet-InfraLogiciel/timefusion-web/src/main/webapp/
mkdir /var/www/html/siteweb/
cp -r * /var/www/html/siteweb/
cd /var/www/html/siteweb/
cp -r /var/www/html/siteweb/HTML/* /var/www/html/siteweb/

cd /etc/apache2/sites-available/
# modification de la configuration du site 000-default.conf pour pointer sur le dossier siteweb
sed -i 's/DocumentRoot \/var\/www\/html/DocumentRoot \/var\/www\/html\/siteweb/g' 000-default.conf

echo "END - Deplacement des fichiers"
