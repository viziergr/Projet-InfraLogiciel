#!/bin/bash

SSH_DIR="/home/vagrant/.ssh"
SSH_PRIVATE_KEY="$SSH_DIR/id_rsa"
SSH_PUBLIC_KEY="$SSH_PRIVATE_KEY.pub"

# Vérifier que le répertoire ssh existe déjà pour les backup, sinon le créer

if [ ! -d "$SSH_DIR/id_rsa" ]; then
    mkdir -p "$SSH_DIR"
fi

# Vérifier que le répertoire sav existe déjà pour les backup, sino le créer

LOCAL_BACKUP_DIR="/home/vagrant/sav"

if [ ! -d "$LOCAL_BACKUP_DIR" ]; then
    mkdir -p "$LOCAL_BACKUP_DIR"
    chown vagrant $LOCAL_BACKUP_DIR
    chmod 700 $LOCAL_BACKUP_DIR
fi

#Vérifie que les clés existent, si tel est le cas, pas de nouvelles clées crées.
if [ ! -f "$SSH_PRIVATE_KEY" ] || [ ! -f "$SSH_PUBLIC_KEY" ]; then
    cp /vagrant/id_rsa.pub "$SSH_DIR"
    cat "$SSH_PUBLIC_KEY" >> "$SSH_DIR"/authorized_keys
    chown vagrant "$SSH_PUBLIC_KEY"
    chmod 700 "$SSH_PUBLIC_KEY"
    echo "Clés SSH copiées et ajoutées avec succès."
else
    echo "Les clés SSH existent déjà. Aucune nouvelle clé n'a été copiée."
fi