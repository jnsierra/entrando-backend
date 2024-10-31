# entrando-backend

Construir las images con el comando

mvn spring-boot:build-image

Ejecucion de los modulos que necesitan imagen

```bash 
mvn spring-boot:build-image -pl entrando-acceso-datos,entrando-discovery,entrando-gateway,entrando-business,entrando-config
```

mvn spring-boot:build-image -pl entrando-config



docker compose up postgres postgreskc keycloak entrando-discovery entrando-config entrando-gateway jaeger entrando-datos entrando-business 

docker compose up postgres postgreskc keycloak entrando-discovery entrando-config entrando-gateway jaeger -d