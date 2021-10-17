FROM openjdk:17-jdk-slim-buster

ENV WILDFLY_VERSION 22.0.1.Final
ENV WILDFLY_HOME /opt/wildfly

RUN apt-get update && apt-get install -y curl

RUN useradd -d ${WILDFLY_HOME} -m wildfly
USER wildfly
WORKDIR ${WILDFLY_HOME}

COPY build/install_wildfly.sh /
RUN /install_wildfly.sh

COPY build/run.sh /
COPY build/config/ /tmp/wildfly/config/
RUN cp -r /tmp/wildfly/config/* /opt/wildfly/

ENV ADMIN_USER admin
ENV ADMIN_PASS wildfly

EXPOSE 8080 8443 9990 8787

CMD ["/run.sh"]
