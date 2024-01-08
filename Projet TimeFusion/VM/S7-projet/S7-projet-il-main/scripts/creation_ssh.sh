#!/bin/bash

## install git in vm

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"


# Générer la clé SSH
ssh-keygen -t rsa -b 2048 -f ~/.ssh/id_rsa -N ""

# Afficher la clé générée
echo "La clé SSH a été générée avec succès:"
cat ~/.ssh/id_rsa.pub