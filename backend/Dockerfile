# syntax=docker/dockerfile:1.10.0
FROM maven:3.9.9-amazoncorretto-17-alpine AS package
WORKDIR /app
COPY pom.xml pom.xml
COPY src src
RUN --mount=type=cache,id=repository,target=/root/.m2/repository mvn clean package -DskipTests

FROM openjdk:17-ea-17-slim-buster
EXPOSE 8080
RUN addgroup --gid 1001 kanghouchao && \
    adduser --disabled-login --gid 1001  kanghouchao
USER kanghouchao:kanghouchao
WORKDIR /home/kanghouchao
COPY --from=package /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-server", "-jar", "app.jar"]