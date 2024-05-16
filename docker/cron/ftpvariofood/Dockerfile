FROM openjdk:17-alpine

RUN mkdir -p /usr/src/workdir

WORKDIR /usr/src/workdir

COPY FtpVariofood.java ./
COPY mysql-connector-j-8.0.33.jar ./
COPY commons-net-3.10.0.jar ./
RUN javac FtpVariofood.java
CMD ["java", "-classpath", "mysql-connector-j-8.0.33.jar:commons-net-3.10.0.jar:.","FtpVariofood"]


