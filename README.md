# micro-starter
[![gitter](https://badges.gitter.im/trezor/community.svg)](https://gitter.im/micro-starter)
[![Build Status](https://travis-ci.org/sky233/micro-starter.svg?branch=master)](https://travis-ci.org/sky233/micro-starter)
[![Docker Pulls](https://img.shields.io/docker/pulls/sky233/config-service.svg)](https://hub.docker.com/r/sky233/config-service)
[![Docker Stars](https://img.shields.io/docker/stars/sky233/config-service.svg)](https://hub.docker.com/r/sky233/config-service)
[![](https://badge.imagelayers.io/sky233/config-service:latest.svg)](https://imagelayers.io/?images=sky233/config-service:latest)

该应用程序演示使用 Axonframework Spring Boot，Spring Cloud 的 CQRS 微服务架构模式。

## 应用架构图

![Architecture](https://github.com/sky233/micro-starter/blob/master/slides/architecture-01.png "Architecture")

## 如何运行
  * 开发模式
```` 
docker-compose -f docker/docker-compose.yml up
````
  * Docker swarm：
````  
docker stack deploy -c docker/docker-compose-aliyun-swui.yml micro-starter
````  

### 服务端口
  * http://ip-self:8080 - Gateway
  * http://ip-self:8761 - Eureka Dashboard
  * http://ip-self:6161/hystrix - Hystrix Dashboard
  * http://ip-self:9998 - Admin server Dashboard
  * http://ip-self:888 - Swarmpit ui Dashboard
  * http://ip-self:15672 - RabbitMq management (default micro/micro)
  * http://ip-self:27017 - Mongodb
  * http://ip-self:3306 - MySQL (default micro/micro)

### 图示 
* ![Swarmpit ui](https://github.com/sky233/micro-starter/blob/master/slides/swarmpit.png "Swarmpit ui")
* ![Admin server](https://github.com/sky233/micro-starter/blob/master/slides/adminserver.png "Admin server")

## 在线示例
  * [Api docs](https://onlythinking.github.io/micro-starter/1.0)
   
## 参考文献
  * [Micro Services](http://microservices.io/patterns/microservices.html)
  * [Axonframework](http://www.axonframework.org/)
  * [Spring Cloud](http://projects.spring.io/spring-cloud/)

## 欢迎反馈
  * 有任何问题随时与我联系，感谢您的反馈。 
  * lixingping233@gmail.com
  * [@Twitter](https://twitter.com/lixingping233)
