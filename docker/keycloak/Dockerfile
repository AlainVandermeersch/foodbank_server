FROM jboss/keycloak:12.0.4

COPY ./build/providers/*.jar /opt/jboss/keycloak/standalone/deployments/
COPY ./build/realm/*.json /tmp/import/realm/