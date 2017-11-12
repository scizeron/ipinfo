[![Build Status](https://travis-ci.org/scizeron/ipinfo.svg?branch=master)](https://travis-ci.org/scizeron/ipinfo)
[![Code Coverage](https://img.shields.io/codecov/c/github/scizeron/ipinfo/master.svg)](https://codecov.io/github/scizeron/ipinfo?branch=master)

# ipinfo

## Table Of Contents
- [Build](#Build)
- [Run](#Run-locally)
- [Docker](#Docker)

---

## Build

```sh
mvn clean install
```
---

## Run locally 

Behind a corporate proxy

```sh
java -Djava.net.preferIPv4Stack=true -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT -jar target/app.jar  
```

To simulate a remote client, populate the http request header X-FORWARDED-FOR with a public IP.D

```sh
curl -H X-FORWARDED-FOR:$(curl -s icanhazip.com) http://localhost:8080
```

## Docker

### build

```sh
docker build -t scizeron/ipinfo .
```

### run

behind a corporate proxy

```sh
docker run -d -p8080:8080 -e "JAVA_OPTS=-Djava.net.preferIPv4Stack=true -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT" scizeron/ipinfo
```

### push 

Push the docker image on hub.docker.com (docker login)

```sh
docker push scizeron/ipinfo
```

See all tags [here](https://hub.docker.com/r/scizeron/ipinfo/tags/).

![docker-image](docs/images/dockerImage.png)

## swarm

### service

```sh
docker service create --name ipinfo --replicas 1 --publish 8080:8080 scizeron/ipinfo
```
