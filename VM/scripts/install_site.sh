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
git clone https://github.com/viziergr/Projet-InfraLogiciel.git

mkdir /var/www/html/siteweb/

rm -r /var/www/html/git/Projet-InfraLogiciel.vscode
rm -r /var/www/html/git/Projet-InfraLogiciellivrables
rm -r /var/www/html/git/Projet-InfraLogicieltimefusion-desktop
rm -r /var/www/html/git/Projet-InfraLogicielVM
rm /var/www/html/git/Projet-InfraLogicielConfigurations.txt
rm /var/www/html/git/Projet-InfraLogicielProjetInfraLog.drawio
rm /var/www/html/git/Projet-InfraLogicielREADME.md

cp -r /var/www/html/git/Projet-InfraLogiciel/timefusion-web/src/main/webapp/* /var/www/html/siteweb/
cp -r /var/www/html/siteweb/HTML/* /var/www/html/siteweb/
mkdir /var/www/html/siteweb/myadmin
mv /var/www/html/myadmin/* /var/www/html/siteweb/myadmin/

# modification de la configuration du site 000-default.conf pour pointer sur le dossier siteweb
sed -i 's/DocumentRoot \/var\/www\/html/DocumentRoot \/var\/www\/html\/siteweb/g' /etc/apache2/sites-available/000-default.conf

service apache2 reload
echo "END - Deplacement des fichiers"
