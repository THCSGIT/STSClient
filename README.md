# STSClient
Uma implementação do STSClient em Java 17.

## Pré-requisitos
- Java 17

## Como rodar em desenvolvimento
`./gradlew bootRun`
Logo ao subir, a aplicação vai fazer uma requisição ao STS especificado no no application.yaml.

## Como fazer build
`./gradlew assemble`

## Como criar imagem Docker 
`docker build -t itau/sts-client:1.0.0 -t itau/sts-client:latest .`

