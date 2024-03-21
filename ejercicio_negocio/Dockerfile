FROM openjdk:17-jdk-alpine AS build-stage

COPY ./target/ejercicio_negocio-0.0.1-SNAPSHOT.jar /opt/app.jar

ENTRYPOINT ["java", "-jar", "/opt/app.jar"]