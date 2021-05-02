FROM openjdk:8-jdk-alpine
MAINTAINER alain.vandermeersch@gmail.com
COPY target/foodbank-it-server-0.0.1.jar foodbank-it-server-0.0.1.jar
ENTRYPOINT ["java","-jar","/foodbank-it-server-0.0.1.jar"]