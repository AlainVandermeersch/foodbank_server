FROM alpine:3.16 as builder

ARG FRONTEND_URL
ARG REDIRECT_URIS
ARG REDIRECT_URIS_ISIS
ARG MYSQL_HOST
ARG MYSQL_PORT
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MYSQL_DATABASE
ARG SMTP_USER
ARG SMTP_PASSWORD

COPY ./build/realm/*.json /tmp/import/realm/
RUN sed -i 's@###REDIRECT_URIS###@'"$REDIRECT_URIS"'@' /tmp/import/realm/*.json && \
    sed -i 's@###REDIRECT_URIS_ISIS###@'"$REDIRECT_URIS_ISIS"'@' /tmp/import/realm/*.json && \
    sed -i 's@###FRONTEND_URL###@'"$FRONTEND_URL"'@' /tmp/import/realm/*.json && \
    sed -i 's@###SMTP_PASSWORD###@'"$SMTP_PASSWORD"'@' /tmp/import/realm/*.json && \
    sed -i 's@###SMTP_USER###@'"$SMTP_USER"'@' /tmp/import/realm/*.json


FROM quay.io/keycloak/keycloak:16.1.1
COPY ./build/datasource.xml /tmp/datasource.xml
COPY ./build/theme/foodbank /opt/jboss/keycloak/themes/foodbank
# RUN sed -i 's@<drivers>@'"$(cat /tmp/datasource.xml)"'@' /opt/jboss/keycloak/standalone/configuration/standalone*.xml
RUN sed -e '/<drivers>/ {' -e 'r /tmp/datasource.xml' -e 'd' -e '}' -i /opt/jboss/keycloak/standalone/configuration/standalone-ha.xml
COPY ./build/providers/*.jar /opt/jboss/keycloak/standalone/deployments/
COPY --from=builder /tmp/import/realm /tmp/import/realm/

