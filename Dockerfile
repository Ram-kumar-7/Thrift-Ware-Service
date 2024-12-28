FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/Thrift-Ware-0.0.1-SNAPSHOT.jar .

# Verify if the JAR file is correctly copied
RUN ls -l /app

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "Thrift-Ware-0.0.1-SNAPSHOT.jar" ]
