### 这个工程是我系统学习ouath2.0的起步练习，分了三个主要练习：

1、spring-cloud-security-oauth-uaa 、spring-cloud-security-oauth-resource 基本练习

2、spring-cloud-security-oauth-uaa-jwt 、 spring-cloud-security-oauth-resource-jwt 使用jwt生成access token

3、spring-cloud-security-oauth-cloud-uaa 、 spring-cloud-security-oauth-cloud-resource 、spring-cloud-security-oauth-cloud-gateway 使用spring cloud 练习

4、（最主要的sso）spring-cloud-security-oauth-uaa-jwt-sso 、spring-cloud-security-oauth-zuul-jwt-sso、spring-cloud-security-ouath-client1-jwt-sso、spring-cloud-security-ouath-client2-jwt-sso 使用sso单点登录


### 使用的技术框架：

apollo

springboot

spring cloud

spring security

spring security oauth2.0

### 本工程属于自己练习项目，可能有一些配置是在apollo配置，如下：

#mysql
spring.datasource.url = jdbc:mysql://192.168.206.11:3306/security?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username = 
spring.datasource.password = 
#redis
spring.redis.model = 1
spring.redis.hostname = 127.0.0.1
spring.redis.port = 6379
spring.redis.cluster.nodes = 
#jwt
jwt.header = Authorization
jwt.secret = mySecret
jwt.expiration = 86400
jwt.tokenHead = Bearer
#eureka
eureka.client.register-with-eureka = true
eureka.client.fetch-registry = true
eureka.client.serviceUrl.defaultZone = http://**:**@192.168.206.11:8000/eureka,http://**:**@192.168.206.66:8000/eureka,http://**:**@192.168.206.127:8000/eureka

### 下面的计划：

1、接入github sso登录

2、接入qq sso 登录

3、接入weibo sso 登录

### 欢迎一起学习进步， qq： 279576159 ， 欢迎指正。
