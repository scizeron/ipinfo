FROM anapsix/alpine-java
LABEL maintainer="stephane.cizeron@gmail.com"
ADD target/app.jar app.jar
ENV JAVA_OPTS="-Xmx256m -Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true"
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar "$@"