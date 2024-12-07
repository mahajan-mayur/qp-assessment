FROM gradle:8.4-jdk17 AS builder

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

RUN ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-slim

LABEL authors="MAYUR MAHAJAN"

WORKDIR /app

COPY --from=builder /app/build/libs/GroceryApi-0.0.1-SNAPSHOT.jar GroceryApi.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "GroceryApi.jar"]
