FROM amazoncorretto:17-alpine as deps

ARG APPLICATION_USER=itau
RUN adduser --no-create-home -u 1000 -D $APPLICATION_USER
RUN mkdir /itau && chown -R $APPLICATION_USER /itau
WORKDIR /itau

COPY  build/libs/sts.jar  /itau/sts.jar
COPY src/main/resources/application.yaml /itau/application.yml

USER 1000

EXPOSE 8080

ENTRYPOINT java -jar /itau/sts.jar