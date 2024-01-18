#!/bin/bash

LOG_FILE="/vagrant/logs/install_proxy.log"
# Ajout du reverse proxy dans le fichier ci-dessous qui permet d'accéder au site
echo "start - install proxy"
sudo sed -i 's/<\/VirtualHost>//g' /etc/apache2/sites-available/000-default.conf
echo "ServerName .reverse-proxy
        # Activation du module de proxy
        ProxyRequests Off
        ProxyPreserveHost On

        <Proxy *>
         Order deny,allow
         Allow from all
        </Proxy>

        # Configuration du reverse proxy
        ProxyPass / http://192.168.56.80/
        ProxyPassReverse / http://192.168.56.80/
</VirtualHost>" >> /etc/apache2/sites-available/000-default.conf

# Activation des modules proxy et proxy_http sur le serveur web Apache
a2enmod proxy proxy_http

# Création du nouvel hôte pour le reverse proxy
sudo mkdir -p /etc/apache2/sites-available 
sudo touch /etc/apache2/sites-available/reverse-proxy.conf

# Activation du reverse proxy
sudo a2ensite reverse-proxy 

# Relancer apache2 pour mettre à jour le proxy
sudo systemctl reload apache2 
sudo service apache2 restart 
echo "END - reverse proxy installed"