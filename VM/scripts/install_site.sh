#!/bin/bash

## déplace les fichiers du site

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Deplacement des fichiers - "$IP
# Définir le chemin du répertoire
repertoire="/var/www/html/Projet-InfraLogiciel"

# Vérifier si le répertoire existe
if [ -d "$repertoire" ]; then
    # Le répertoire existe, exécuter git pull
    cd "$repertoire" || exit
    git pull
else
    # Le répertoire n'existe pas, exécuter git clone
    git clone "https://github.com/viziergr/Projet-InfraLogiciel.git" "$repertoire"
    git checkout gregoire/testArchitecture
fi


rm -r /var/www/html/Projet-InfraLogiciel/.vscode
rm -r /var/www/html/Projet-InfraLogiciel/livrables
rm -r /var/www/html/Projet-InfraLogiciel/timefusion-dekstop
rm -r /var/www/html/Projet-InfraLogiciel/VM
rm /var/www/html/Projet-InfraLogiciel/Configurations.txt
rm /var/www/html/Projet-InfraLogiciel/ProjetInfraLog.drawio
rm /var/www/html/Projet-InfraLogiciel/README.md

mkdir /var/www/html/Projet-InfraLogiciel/timefusion-web/myadmin
mv /var/www/html/myadmin/* /var/www/html/Projet-InfraLogiciel/timefusion-web/myadmin/

# modification de la configuration du site 000-default.conf pour pointer sur le dossier siteweb/PHP/public
sed -i 's/\/var\/www\/html/\/var\/www\/html\/Projet-Infralogiciel\/timefusion-web\/public/g' /etc/apache2/sites-available/000-default.conf

service apache2 reload
echo "END - Deplacement des fichiers"
