FROM php:7.4-cli
RUN docker-php-ext-install mysqli
RUN docker-php-ext-enable mysqli
ARG host
ARG database
ARG user
ARG password
ENV MYSQL_HOST $host
ENV MYSQL_DATABASE $database
ENV MYSQL_USER  $user
ENV MYSQL_PASSWORD  $password
COPY ./dashboard.php /usr/src/myapp
WORKDIR /usr/src/myapp
CMD [ "php", "./dashboard.php" ]