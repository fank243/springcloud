# Spring Cloud

基于 Spring Cloud + Oauth2 + Nacos + JPA + Redis + MySQL5.7 开发

相关工具类使用 hutool, 不需要重复造轮子

该项目主要用于学习Spring Cloud 相关知识,该项目会一直开发下去,并会一直跟随Spring Cloud 脚步, 所有相关依赖jar均会不定期升级到最新版本

# 端口分布

Spring Security Oauth2 官方SQL脚本存放在 cloud-auth/cloud-auth-oauth2/oauth2.sql

|*端口*|*模块*|*数据库*|
|---|---|---|
|8000|cloud-gateway-admin|fank-cloud|
|8001|cloud-gateway-app|fank-cloud|
|8100|cloud-auth-oauth2|fank-cloud|
|8200|cloud-service-user|fank-cloud|
|8201|cloud-service-notice|fank-cloud-notice|

