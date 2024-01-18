#!/bin/bash

## Relier les deux VM

IP=$(hostname -I | awk '{print $2}')

APT_OPT="-o Dpkg::Progress-Fancy="0" -q -y"
LOG_FILE="/vagrant/logs/install_git.log"
DEBIAN_FRONTEND="noninteractive"

echo "START - Link des deux VM - "$IP

iptables --flush

echo "START - Partie MySQL"

mysql -e "CREATE USER 'root'@'%';"
mysql -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;"
mysql -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.56.1' WITH GRANT OPTION;"
mysql -e "FLUSH PRIVILEGES;"

echo "END - Partie MySQL"
echo "START - Modification des fichiers de configuration"
sed -i 's/127.0.0.1/0.0.0.0/g' /etc/mysql/mariadb.conf.d/50-server.cnf
sed -i 's/# port = 3306/port = 3306/g' /etc/mysql/my.cnf
echo "END - Modification des fichiers de configuration"

service mariadb restart

echo "END - Link des deux VM"
