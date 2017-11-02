FROM anapsix/alpine-java

MAINTAINER scizeron

ADD target/app.jar /app.jar
ADD docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/entrypoint.sh"]