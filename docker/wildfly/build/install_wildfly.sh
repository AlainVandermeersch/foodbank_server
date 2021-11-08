#!/bin/sh -ex

# DOWNLOAD AND UNZIP WILDFLY
curl -LfSo /tmp/wildfly-${WILDFLY_VERSION}.tar.gz https://download.jboss.org/wildfly/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.tar.gz
tar xzf /tmp/wildfly-${WILDFLY_VERSION}.tar.gz --strip-components=1;
rm /tmp/wildfly-${WILDFLY_VERSION}.tar.gz

# DOWNLOAD AND UNZIP KEYCLOAK WILDFLY OPENID CONNECT ADAPTER
tar xzf /tmp/wildfly/keycloak-adapter/keycloak-oidc-wildfly-adapter-15.0.2.tar.gz
#rm /tmp/wildfly/keycloak-adapter/keycloak-oidc-wildfly-adapter-15.0.2.tar.gz