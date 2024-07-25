FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder
MAINTAINER SQ

WORKDIR /build/

COPY pom.xml /build/
COPY src /build/src/
COPY src/main/resources/sqlite/sqmusic.db /cache/sqmusic.db

RUN mvn clean package

From amazoncorretto:17.0.12-alpine3.19

RUN apk update && \
    apk upgrade -U && \
    apk add ca-certificates ffmpeg && rm -rf /var/cache/*


RUN echo "http://dl-cdn.alpinelinux.org/alpine/v3.3/main" >> /etc/apk/repositories


RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories


RUN search libwebp

RUN apk add --no-cache libwebp=0.4.4-r0 libwebp-tools=0.4.4-r0


WORKDIR /app

COPY --from=builder  /build/target/MusicServer2.0.jar /app/app.jar

EXPOSE 8099

VOLUME ["/music"]

VOLUME ["/cache"]

CMD ["java", "-jar", "app.jar"]

