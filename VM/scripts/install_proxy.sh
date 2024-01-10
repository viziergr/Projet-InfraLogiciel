#!/bin/bash

## déplace les fichiers du site

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Installation reverse proxy - "$IP

sudo a2enmod proxy
sudo a2enmod proxy_http
sudo a2enmod proxy_balancer
sudo a2enmod lbmethod_byrequests
sudo systemctl restart apache2

echo "<VirtualHost *:80>
    ProxyPreserveHost On

    ProxyPass / http://192.168.56.80/
    ProxyPassReverse / http://192.168.56.80/

</VirtualHost>">/etc/apache2/sites-available/001-reverse-proxy.conf
sudo a2dissite 000-default.conf
sudo a2ensite 001-reverse-proxy.conf
sudo systemctl restart apache2

echo "END - Installation reverse proxy"
