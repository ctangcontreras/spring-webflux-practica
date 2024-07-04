FROM openjdk:11
MAINTAINER jchaupez-dev
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} DESAFIO-CLIENTE-1.0.jar
ENTRYPOINT ["java","-jar","/DESAFIO-CLIENTE-1.0.jar"]