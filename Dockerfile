FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder
MAINTAINER SQ

WORKDIR /build/

COPY pom.xml /build/
COPY src /build/src/
COPY src/main/resources/sqlite/sqmusic.db /cache/sqmusic.db

RUN mvn clean package

From amazoncorretto:17.0.12-alpine3.19

WORKDIR /app

COPY --from=builder  /build/target/MusicServer2.0.jar /app/app.jar

EXPOSE 8099

VOLUME ["/music"]

VOLUME ["/cache"]

CMD ["java", "-jar", "app.jar"]

