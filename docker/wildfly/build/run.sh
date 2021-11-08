#!/bin/sh -e
${WILDFLY_HOME}/bin/add-user.sh ${ADMIN_USER} ${ADMIN_PASS}
${WILDFLY_HOME}/bin/jboss-cli.sh --file=bin/adapter-elytron-install-offline.cli
${WILDFLY_HOME}/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 --debug *:8787
