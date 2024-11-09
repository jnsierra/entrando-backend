# entrando-backend

Construir las images con el comando

mvn spring-boot:build-image

Ejecucion de los modulos que necesitan imagen

```bash 
mvn clean install && mvn spring-boot:build-image -pl entrando-acceso-datos,entrando-discovery,entrando-gateway,entrando-business,entrando-config,entrando-public-users
```

mvn spring-boot:build-image -pl entrando-config

mvn spring-boot:build-image -pl entrando-gateway



docker compose up postgres postgreskc keycloak entrando-discovery entrando-config entrando-gateway jaeger entrando-datos entrando-business 

docker compose up postgres postgreskc keycloak entrando-discovery entrando-config entrando-gateway jaeger -d

mvn versions:set -DnewVersion=0.0.2 -DprocessParent=false -DgenerateBackupPoms=false -DprocessProject=true -DprocessFromLocalAggregationRoot=true -pl entrando-gateway
mvn versions:commit
