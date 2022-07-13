FROM maven as build
COPY pom.xml /app/pom.xml
COPY foodbank-keycloak-user-provider/pom.xml /app/foodbank-keycloak-user-provider/pom.xml
COPY foodbank-rest-api/pom.xml /app/foodbank-rest-api/pom.xml
RUN mvn -f /app/pom.xml dependency:resolve dependency:resolve-plugins
COPY . /app
RUN mvn -f /app/pom.xml clean install

FROM openjdk:16-alpine
ARG JAR_FILE=*.jar
COPY --from=build /app/foodbank-rest-api/target/*.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000","-jar","/app.jar"]