#!/bin/bash

RESULT_VARIABLE="$(mysql -uroot -p$MYSQL_ROOT_PASSWORD -sse "SELECT EXISTS(SELECT 1 FROM mysql.user WHERE user = 'keycloak')")"

if [ "$RESULT_VARIABLE" = 0 ]; then
  mysql -uroot -p$MYSQL_ROOT_PASSWORD < /tmp/scripts/init.sql
  echo "USER CREATED"
else
  echo "USER ALREADY EXISTS"
fi