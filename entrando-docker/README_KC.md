# KEYCLOAK
## Creación imagen Keycloak custom
Creamos la imagen con las configuraciones necesarias para Keycloak
```shell
docker build -t keycloak-custom -f Dockerfile .
```
# Configuracion SSL Keycloak

## Generamos la llave privada
```shell
openssl genrsa -out server.key
```
## Generacion del CSR
```
openssl req -new -key server.key -out server.csr
```
```
Country Name (2 letter code) [AU]: CO                       # Colombia
State or Province Name (full name) [Some-State]: BOG        # Bogotá
Locality Name (eg, city) []: Bogota                         # Ciudad
Organization Name (eg, company) []: Hashticket              # Nombre de la empresa
Organizational Unit Name (eg, section) []: IT               # Departamento IT
Common Name (e.g. server FQDN) []: keycloak.hashticket.com  # Dominio de Keycloak
Email Address []: admin@hashticket.com                      # Correo administrativo

# Campos opcionales (presiona Enter para dejarlos en blanco):
A challenge password []:
An optional company name []:
```

## Generacion del certificado

```shell
openssl x509 -req -in server.csr -signkey server.key -days 365 -out server.crt
```
