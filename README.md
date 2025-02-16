# entrando-backend

Construir las images con el comando

mvn spring-boot:build-image

Ejecucion de los modulos que necesitan imagen

```bash 
mvn clean install && mvn spring-boot:build-image -pl entrando-acceso-datos,entrando-discovery,entrando-gateway,entrando-business,entrando-config,entrando-public-users
```

mvn spring-boot:build-image -pl entrando-config

mvn spring-boot:build-image -pl entrando-gateway

mvn spring-boot:build-image -pl entrando-discovery

mvn spring-boot:build-image -pl entrando-config

mvn spring-boot:build-image -pl entrando-config

mvn spring-boot:build-image -pl entrando-acceso-datos



docker compose up postgres postgreskc keycloak entrando-discovery entrando-config entrando-gateway jaeger entrando-datos entrando-business 

docker compose up postgres postgreskc keycloak entrando-discovery entrando-config entrando-gateway jaeger -d


## SSH para poder entrar al keycloak
ssh -L 8081:localhost:8081 jnsierrac@46.202.92.9