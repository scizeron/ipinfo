[![Build Status](https://travis-ci.org/scizeron/ipinfo.svg?branch=master)](https://travis-ci.org/scizeron/ipinfo)[![codecov.io](https://codecov.io/github/scizeron/ipinfo.svg?branch=master)](https://codecov.io/github/cainus/codecov.io?branch=master)

# ipinfo

## Table Of Contents
- [Build](#build)
- [Run](#run-locally)
- [Docker](#docker)

---

## build

```sh
mvn clean install
```
---

## run locally 

behind a corporate proxy

```sh
java -Djava.net.preferIPv4Stack=true -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT -jar target/app.jar  
```

To simulate a remote client (locally)

```sh
curl -H X-FORWARDED-FOR:$(curl -s icanhazip.com) http://localhost:8080
```

## docker

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
