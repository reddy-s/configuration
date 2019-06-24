FROM openjdk:8-jre-alpine

LABEL application=io.virtuelabs.configuration \
      maintainer=SangramReddy<reddy.horcrux@gmail.com>

COPY ./app/configuration-*.jar /app/app.jar

RUN addgroup -S virtuelabs && adduser -S virtuelabs -G virtuelabs && \
    chown -R virtuelabs:virtuelabs /app && \
    chmod -R 755 /app

WORKDIR /app

ENV RUN_PORT=20001
EXPOSE 20001

USER virtuelabs

CMD java -jar app.jar