FROM openjdk:17-alpine

RUN mkdir -p /usr/src/workdir

WORKDIR /usr/src/workdir

COPY DbCleanup.java ./
COPY mysql-connector-j-8.0.33.jar ./

RUN javac DbCleanup.java

CMD ["java", "-classpath", "mysql-connector-j-8.0.33.jar:.","DbCleanup"]

