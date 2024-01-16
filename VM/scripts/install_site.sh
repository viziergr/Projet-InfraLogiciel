#!/bin/bash

## déplace les fichiers du site

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Deplacement des fichiers - "$IP
# Définir le chemin du répertoire
repertoire="/var/www/html/"

# Vérifier si le répertoire existe
if [ -d "$repertoire/Projet-InfraLogiciel" ]; then
    echo "=> [1] - Le répertoire existe"
    # Le répertoire existe, exécuter git pull
    cd "$repertoire" || exit
    echo "=> [2] - Git pull"
    git pull
    echo "=> [3] - Git Modification de la configuration du site 000-default.conf"
    # Modification de la configuration du site 000-default.conf pour pointer sur le dossier Projet-InfraLogiciel/timefusion-web/public
    sed -i 's/DocumentRoot \/var\/www\/html/DocumentRoot \/var\/www\/html\/Projet-InfraLogiciel\/timefusion-web\/public/g' /etc/apache2/sites-available/000-default.conf
    echo "=> [4] - Déplacement du répertoire myadmin"
    # Déplacement des fichiers de myadmin
    mkdir /var/www/html/Projet-InfraLogiciel/timefusion-web/myadmin
    mv /var/www/html/myadmin/* /var/www/html/Projet-InfraLogiciel/timefusion-web/myadmin/
else
    # Le répertoire n'existe pas, exécuter git clone
    cd "$repertoire" || exit
    echo "=> [1] - Git clone"
    git clone "https://github.com/viziergr/Projet-InfraLogiciel.git"
    cd Projet-InfraLogiciel || exit
    echo "=> [2] - Git checkout gregoire/testArchitecture"
    git checkout gregoire/testArchitecture
fi

echo "START - Suppression des fichiers inutiles"
# Suppression de tous les autres fichiers inutiles
rm -r /var/www/html/Projet-InfraLogiciel/.vscode
rm -r /var/www/html/Projet-InfraLogiciel/livrables
rm -r /var/www/html/Projet-InfraLogiciel/timefusion-dekstop
rm -r /var/www/html/Projet-InfraLogiciel/VM
rm /var/www/html/Projet-InfraLogiciel/Configurations.txt
rm /var/www/html/Projet-InfraLogiciel/ProjetInfraLog.drawio
rm /var/www/html/Projet-InfraLogiciel/README.md
echo "END - Suppression des fichiers inutiles"

service apache2 reload
echo "END - Deplacement des fichiers"
