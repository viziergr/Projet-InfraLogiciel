#!/bin/bash


cp /etc/cron.d/moodle /home/vagrant

echo "* * * * * sh /vagrant/scripts/sav.sh" > /home/vagrant/lama

crontab -u vagrant /home/vagrant/lama

rm /home/vagrant/lama