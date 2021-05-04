FROM maven as build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:16-alpine
ARG JAR_FILE=*.jar
COPY --from=build /usr/src/app/target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]