FROM alpine as builder

ARG REDIRECT_URIS
ARG MYSQL_HOST
ARG MYSQL_PORT
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MYSQL_DATABASE

COPY ./build/realm/*.json /tmp/import/realm/
RUN sed -i 's@###REDIRECT_URIS###@'"$REDIRECT_URIS"'@' /tmp/import/realm/*.json && \
    sed -i 's@###MYSQL_HOST###@'"$MYSQL_HOST"'@' /tmp/import/realm/*.json && \
    sed -i 's@###MYSQL_PORT###@'"$MYSQL_PORT"'@' /tmp/import/realm/*.json && \
    sed -i 's@###MYSQL_USER###@'"$MYSQL_USER"'@' /tmp/import/realm/*.json && \
    sed -i 's@###MYSQL_PASSWORD###@'"$MYSQL_PASSWORD"'@' /tmp/import/realm/*.json && \
    sed -i 's@###MYSQL_DATABASE###@'"$MYSQL_DATABASE"'@' /tmp/import/realm/*.json


FROM jboss/keycloak:12.0.4

COPY ./build/providers/*.jar /opt/jboss/keycloak/standalone/deployments/
COPY --from=builder /tmp/import/realm /tmp/import/realm/

