# 授权服务器

oauth2.sql

- oauth_access_token 生成的token
- oauth_client_details 客户端信息
- oauth_code 授权码
- oauth_refresh_token 刷新token

## jwt.jks 文件生成命令

```bash
keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
```