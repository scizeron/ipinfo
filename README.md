[![Build Status](https://travis-ci.org/scizeron/ipinfo.svg?branch=master)](https://travis-ci.org/scizeron/ipinfo)
[![Code Coverage](https://img.shields.io/codecov/c/github/scizeron/ipinfo/master.svg)](https://codecov.io/github/scizeron/ipinfo?branch=master)

Table of contents
=================
  * [Java](#Java)
    * [Build](#Build)
    * [Run](#Run)
  * [Docker](#Docker)
    * [Build](#Build)
    * [Run](#Run)
    * [Push](#Push)
    * [Swarm](#Swarm)   
  * [Live demo](#Live-demo)

---

Java
====

Build
-----

```sh
mvn clean install
```
---

Run
---

Behind a corporate proxy

```sh
java -Djava.net.preferIPv4Stack=true -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT -jar target/app.jar  
```

To simulate a remote client, populate the http request header X-FORWARDED-FOR with a public IP.

```sh
curl -H X-FORWARDED-FOR:$(curl -s icanhazip.com) http://localhost:8080
```

Docker
======

Build
-----

```sh
docker build -t scizeron/ipinfo .
```

Run
---

behind a corporate proxy

```sh
docker run -d -p8080:8080 -e "JAVA_OPTS=-Djava.net.preferIPv4Stack=true -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT" scizeron/ipinfo
```

Push
----

Push the docker image on hub.docker.com (docker login)

```sh
docker push scizeron/ipinfo
```

See all tags [here](https://hub.docker.com/r/scizeron/ipinfo/tags/).

![docker-image](docs/images/dockerImage.png)


Swarm
-----

```sh
docker service create --name ipinfo --replicas 1 --publish 8080:8080 scizeron/ipinfo
```

Live demo
=========

[here](https://azure-api.scizeron-dev.com/v2/ipinfo)
