FROM php:7.4-cli
RUN docker-php-ext-install mysqli
COPY ./dashboard.php /usr/src/myapp
WORKDIR /usr/src/myapp
CMD [ "php", "./dashboard.php" ]