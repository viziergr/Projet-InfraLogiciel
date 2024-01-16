#!/bin/bash


DB_USER="root"
DB_PASS="root"
DB_NAME="TimeFusion"
HOST_DIR="/home/vagrant/.ssh/known_hosts"
BACKUP_FILE="db_backup"${TIMESTAMP}.sql

# Répertoire de sauvegarde local
LOCAL_BACKUP_DIR="/home/vagrant/sav"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)

#Vérification si le dossier où sont stockées les sauvegardes existe, sinon création et attribution des bons droits
if [ ! -d "$LOCAL_BACKUP_DIR" ]; then
    mkdir -p "$LOCAL_BACKUP_DIR"
    chown vagrant $SSH_DIR
    chmod 600 $SSH_DIR
fi

#Ajout de l'hôte distant en "trusted hôte" manuellement

ssh -o StrictHostKeyChecking=no -l "vagrant" "192.168.56.82"






# Répertoire de sauvegarde distant
REMOTE_BACKUP_DIR="vagrant@192.168.56.82:/home/vagrant/sav"

# Commande de sauvegarde avec mysqldump
mysqldump -u $DB_USER -p$DB_PASS $DB_NAME > /home/vagrant/sav/"$BACKUP_FILE"

    chown vagrant "$LOCAL_BACKUP_DIR/$BACKUP_FILE"
    chmod 777 "$LOCAL_BACKUP_DIR/$BACKUP_FILE"

scp -i /home/vagrant/.ssh/id_rsa /home/vagrant/sav/"$BACKUP_FILE" vagrant@192.168.56.82:/home/vagrant/sav




