#!/bin/sh -ex
curl -LfSo /tmp/wildfly-${WILDFLY_VERSION}.tar.gz https://download.jboss.org/wildfly/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.tar.gz
tar xzf /tmp/wildfly-${WILDFLY_VERSION}.tar.gz --strip-components=1;
rm /tmp/wildfly-${WILDFLY_VERSION}.tar.gz