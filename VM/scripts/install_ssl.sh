#!/bin/bash

## déplace les fichiers du site

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Installation du certificat SSL - "$IP
# Création d'un dossier qui contiendra toutes les clés
mkdir /etc/apache2/ssl
cd /etc/apache2/ssl || exit

# Génération d'une clé SSL sans pass phrase
openssl genrsa -des3 -passout pass:TimeFusion -out domain.key 2048

# Création d'un CSR (Certificate Signing Request) avec la clé
openssl req -key domain.key -new -out domain.csr -passin pass:TimeFusion -subj "/C=FR/ST=Maine-et-Loire/L=Angers/O=ESEO/CN=TimeFusion"

# Création d'un certificat auto-signé avec la clé et le CSR
openssl x509 -signkey domain.key -in domain.csr -req -days 365 -out domain.crt -passin pass:TimeFusion 

openssl req -x509 -sha256 -days 1825 -newkey rsa:2048 -keyout rootCA.key -out rootCA.crt -passout pass:TimeFusion -subj "/C=FR/ST=Maine-et-Loire/L=Angers/O=ESEO/CN=TimeFusion"

# Création d'un fichier domain.ext avec le contenu :
echo "authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
subjectAltName = @alt_names
[alt_names]
DNS.1 = 192.168.56.80" > domain.ext

openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in domain.csr -out domain.crt -days 365 -CAcreateserial -extfile domain.ext

echo "END - Installation du certificat SSL - "$IP