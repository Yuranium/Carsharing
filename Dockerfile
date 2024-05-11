FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/carsharing-0.0.1-SNAPSHOT.jar /app/carsharing-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "carsharing-0.0.1-SNAPSHOT.jar"]