# entrando-backend

Construir las images con el comando

mvn spring-boot:build-image

Ejecucion de los modulos que necesitan imagen

```bash 
mvn spring-boot:build-image -pl entrando-acceso-datos,entrando-discovery,entrando-gateway,entrando-business,entrando-config
```

mvn spring-boot:build-image -pl entrando-config