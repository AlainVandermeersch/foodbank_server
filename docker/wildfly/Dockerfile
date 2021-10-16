FROM openjdk:17-jdk-slim-buster

ENV WILDFLY_VERSION 25.0.0.Final
ENV WILDFLY_HOME /opt/wildfly

RUN apt-get update && apt-get install -y curl

RUN useradd -d ${WILDFLY_HOME} -m wildfly
USER wildfly
WORKDIR ${WILDFLY_HOME}

ADD build/install_wildfly.sh /
RUN /install_wildfly.sh

ADD build/run.sh /

ENV ADMIN_USER admin
ENV ADMIN_PASS wildfly

EXPOSE 8080 8443 9090

CMD ["/run.sh"]
