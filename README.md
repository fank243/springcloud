# Spring Cloud

基于 SpringCloud Alibaba + Mybatis + Redis + MySQL5.7 开发

相关工具类使用 hutool, 不需要重复造轮子

该项目主要用于学习Spring Cloud 相关知识,该项目会一直开发下去,并会一直跟随Spring Cloud 脚步, 所有相关依赖jar均会不定期升级到最新版本

# 更新版本命令

```bash
# 更新版本号 -DgenerateBackupPoms=false 不生成备份文件

mvn versions:set -DnewVersion=0.2.0 -DgenerateBackupPoms=false
mvn -N versions:update-child-modules

# 基于备份文件进行回滚
mvn versions:revert

# 版本提交
mvn versions:commit
```


