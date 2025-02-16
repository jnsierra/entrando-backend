# Esto sirve para deshabilitar el ssl en el realm de keycloak
update REALM set ssl_required = 'NONE'

# Reinicio de apache
sudo systemctl restart httpd

systemctl status httpd

systemctl stop httpd

# Ubicacion apache
/etc/httpd/conf.d/ssl.conf

# Ubicacion de los archivos
/var/www/html/