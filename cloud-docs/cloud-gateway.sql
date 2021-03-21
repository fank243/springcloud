/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.31 : Database - cloud-gateway
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloud-gateway` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `cloud-gateway`;

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_access_token` */

insert  into `oauth_access_token`(`token_id`,`token`,`authentication_id`,`user_name`,`client_id`,`authentication`,`refresh_token`) values 
('611f0b53195fe7949b332316210e746e','��\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$��\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0idsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0jtit\0$e0777b41-75fd-432d-9d98-7f0cb9a78a4ex\0sr\0java.util.Datehj�KYt\0\0xpw\0\0t�͜@xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valueq\0~\0xpt$eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiZTA3NzdiNDEtNzVmZC00MzJkLTlkOTgtN2YwY2I5YTc4YTRlIiwiaWQiOjEsImV4cCI6MTYwMTAyNzg1NCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiI4MTY3ZGE2MS01Y2EzLTQ5NzctYmQ3MC03YjJkYzk3ZWMyZjMiLCJjbGllbnRfaWQiOiJnYXRld2F5In0.mB8QTJ860H8ppOdtcXaYaUpwi5WC8UVvFCgqScw42Vodlp0XAu14zgsTStyVxXEHZ_QmOW9-EDmIp0jQAh971dBm_pyBi8bS1DZK37TIrHB5v9WjZ-MCC5iu_F2WGvtLSQWVcutU5JYmtYVslCcfIVQ0wTLn1aUcAtaD9BRcCLssq\0~\0w\0\0tĲR�xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxt\0bearert�eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiaWQiOjEsImV4cCI6MTYwMTAyOTY0MywiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJlMDc3N2I0MS03NWZkLTQzMmQtOWQ5OC03ZjBjYjlhNzhhNGUiLCJjbGllbnRfaWQiOiJnYXRld2F5In0.lcZ5fh4qQxaZwXwVqc7kPqhBzqgqvlphQIFBTQnH9NvWAqa9tYmGbRrf38sRbHglJ_Jo6uvSxXN59IgIBkcX6RzTL-I64gmeGIXITxK0IGAcCs405TQfTLLRMla4L1dLuc0NFWMYZe4d6vTAwZgC5l2BZNI7Z6DLyZfAiSg2HRg','2f1340f50280f798434696522244202b','admin','gateway','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0adminxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xpsr\09org.springframework.security.oauth2.provider.TokenRequest�*���8�\0L\0	grantTypeq\0~\0xq\0~\0t\0gatewaysq\0~\0sq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rrefresh_tokenteyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiOWYwM2IyNzEtZTgwNC00NWVkLWE0MDQtOWFmZGM5NTA2YjFhIiwiaWQiOjEsImV4cCI6MTYwMTAyNzg1NCwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiODE2N2RhNjEtNWNhMy00OTc3LWJkNzAtN2IyZGM5N2VjMmYzIiwiY2xpZW50X2lkIjoiZ2F0ZXdheSJ9.ThnR4GYbweIW3_ZH5lasKaY3SBrJdKAROksGWCFlrB_E1d_S2SkAHUddC8uWV_Gn2n25flFVJ5FlniwGi4RLmEMA6oZYvp6go-SgHjdPNHVax21Q7-0qiz0v-Zr6ShpHzFKbrHd5HniTwb-RLKdQibeURcSIpD1eF_vJ6o2BijEt\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qt\0\ngrant_typet\0\rrefresh_tokent\0	client_idq\0~\0.xsq\0~\0\"sq\0~\0$w\0\0\0?@\0\0\0\0\0\0xq\0~\06sq\0~\0%w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0[org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0Apt\0\0sr\01com.fank243.cloud.auth.oauth2.model.MyUserDetails�n�n��\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUser�;Z�lm�\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0!','97cf8b0e6bc22b71c28ecd4ac023e83f');

/*Table structure for table `oauth_approvals` */

DROP TABLE IF EXISTS `oauth_approvals`;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_approvals` */

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values 
('gateway','cloud-service-user,cloud-service-notice','$2a$10$aeoUvhXsBdPeRRj0k3tSwuPzxMsWNZb3KbGutkKkL7fnaJbCHMYb2','ALL','password,refresh_token','https://www.google.com','admin',1800,3600,NULL,NULL);

/*Table structure for table `oauth_client_token` */

DROP TABLE IF EXISTS `oauth_client_token`;

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_client_token` */

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_code` */

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_refresh_token` */

insert  into `oauth_refresh_token`(`token_id`,`token`,`authentication`) values 
('c66dec24f4941d129cde8d5949ec8fb7','��\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valuet\0Ljava/lang/String;xptteyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiZTFjZTM3NDctMjA5Mi00MTQ3LTg2ZTMtYTljZmZjNjQ5OWEzIiwiZXhwIjoxNjAxMDIxMDIyLCJqdGkiOiI5MmQ1MjEyZS0yZDNjLTQ3YzMtODQzNy1mZGMyNTkxYTBhYzQiLCJjbGllbnRfaWQiOiJnYXRld2F5In0.--PKo9-yAN4L6P30ht2BN2_gAoZELoyxj3sC8FCBHBQsr\0java.util.Datehj�KYt\0\0xpw\0\0t�JYx','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0\"w\0\0\0?@\0\0\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0roleq\0~\0xpt\0adminxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0\"w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0\"w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0\0w\0\0\0\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qq\0~\0q\0~\0\Zq\0~\0q\0~\0q\0~\0q\0~\0x\0psr\01com.fank243.cloud.auth.oauth2.model.MyUserDetails����^6\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUser�;Z�lm�\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0\0w\0\0\0\0xq\0~\0'),
('6a27ab6e6a169edfc16f48256fcb4533','��\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valuet\0Ljava/lang/String;xpt�eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiOTViN2FiNDItZjAyNC00OGQyLTkwMjMtNzRmMTgxMTc4OTlkIiwiZXhwIjoxNjAxMDI1MzQ1LCJhdXRob3JpdGllcyI6WyJhZG1pbiJdLCJqdGkiOiI2YmY4N2Q2Ni1hNjBhLTQwMzMtYTRlYi0zN2JlMzU5ZWNlZjQiLCJjbGllbnRfaWQiOiJnYXRld2F5In0.9_F_g9N7jICGk__72LIob3ieLyDDhxY8R2_P3ld3eYosr\0java.util.Datehj�KYt\0\0xpw\0\0tČ	Sx','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0adminxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0%w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\03sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qq\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!x\0psr\01com.fank243.cloud.auth.oauth2.model.MyUserDetails����^6\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUser�;Z�lm�\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0!'),
('3005be7b6417de02bcbffa89ab5b658b','��\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valuet\0Ljava/lang/String;xpt\0$8167da61-5ca3-4977-bd70-7b2dc97ec2f3sr\0java.util.Datehj�KYt\0\0xpw\0\0tĲR�x','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0adminxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0%w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\03sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qq\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!x\0psr\01com.fank243.cloud.auth.oauth2.model.MyUserDetails�n�n��\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUser�;Z�lm�\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0!'),
('9208fe5cd0609615d9abe4d14e600184','��\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valuet\0Ljava/lang/String;xpteyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiOWYwM2IyNzEtZTgwNC00NWVkLWE0MDQtOWFmZGM5NTA2YjFhIiwiaWQiOjEsImV4cCI6MTYwMTAyNzg1NCwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiODE2N2RhNjEtNWNhMy00OTc3LWJkNzAtN2IyZGM5N2VjMmYzIiwiY2xpZW50X2lkIjoiZ2F0ZXdheSJ9.ThnR4GYbweIW3_ZH5lasKaY3SBrJdKAROksGWCFlrB_E1d_S2SkAHUddC8uWV_Gn2n25flFVJ5FlniwGi4RLmEMA6oZYvp6go-SgHjdPNHVax21Q7-0qiz0v-Zr6ShpHzFKbrHd5HniTwb-RLKdQibeURcSIpD1eF_vJ6o2BijEsr\0java.util.Datehj�KYt\0\0xpw\0\0tĲR�x','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0adminxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0%w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\03sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qq\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!x\0psr\01com.fank243.cloud.auth.oauth2.model.MyUserDetails�n�n��\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUser�;Z�lm�\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0!');

/*Table structure for table `sys_userdto` */

DROP TABLE IF EXISTS `sys_userdto`;

CREATE TABLE `sys_userdto` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_userdto` */

/*Table structure for table `tb_role_permission` */

DROP TABLE IF EXISTS `tb_role_permission`;

CREATE TABLE `tb_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK9bjy5co948v1rxk6ts0tv1l2n` (`permission_id`),
  CONSTRAINT `FK9bjy5co948v1rxk6ts0tv1l2n` FOREIGN KEY (`permission_id`) REFERENCES `tb_sys_permission` (`id`),
  CONSTRAINT `FKgtg5vqxwmv8e5bu48jr03986d` FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_role_permission` */

insert  into `tb_role_permission`(`role_id`,`permission_id`) values 
(1,1);

/*Table structure for table `tb_sys_dict_data` */

DROP TABLE IF EXISTS `tb_sys_dict_data`;

CREATE TABLE `tb_sys_dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
  `dict_label` varchar(100) NOT NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
  `list_class` varchar(100) NOT NULL DEFAULT '' COMMENT '表格回显样式',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认（1是 0否）',
  `dict_sort` int(4) NOT NULL DEFAULT '0' COMMENT '字典排序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

/*Data for the table `tb_sys_dict_data` */

/*Table structure for table `tb_sys_dict_type` */

DROP TABLE IF EXISTS `tb_sys_dict_type`;

CREATE TABLE `tb_sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
  `dict_name` varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典类别表';

/*Data for the table `tb_sys_dict_type` */

/*Table structure for table `tb_sys_permission` */

DROP TABLE IF EXISTS `tb_sys_permission`;

CREATE TABLE `tb_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父节点ID(0：一级权限)',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '资源名称',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '资源类型(button,menu)',
  `permission` varchar(30) NOT NULL DEFAULT '' COMMENT '权限',
  `uri` varchar(255) DEFAULT '' COMMENT 'URI',
  `external` tinyint(1) unsigned DEFAULT '0' COMMENT '是否外部链接(0:否,1:是)',
  `icon` varchar(30) DEFAULT '' COMMENT '图标',
  `sort` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '序号',
  `enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用(0:否，1：是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

/*Data for the table `tb_sys_permission` */

insert  into `tb_sys_permission`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`pid`,`name`,`type`,`permission`,`uri`,`external`,`icon`,`sort`,`enable`) values 
(1,0,'2020-09-25 00:37:16',0,'2020-09-25 04:03:23',0,'menu:query','MENU','ROLE_ADMIN','',0,'',0,1);

/*Table structure for table `tb_sys_role` */

DROP TABLE IF EXISTS `tb_sys_role`;

CREATE TABLE `tb_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(30) DEFAULT '' COMMENT '角色描述',
  `enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用(0:否，1：是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `tb_sys_role` */

insert  into `tb_sys_role`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`name`,`description`,`enable`) values 
(1,0,'2020-09-25 00:36:05',0,'2020-09-25 03:40:04','ROLE_ROOT','超管',1);

/*Table structure for table `tb_sys_user` */

DROP TABLE IF EXISTS `tb_sys_user`;

CREATE TABLE `tb_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(20) DEFAULT '' COMMENT '姓名',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `wx_number` varchar(20) DEFAULT '' COMMENT '微信号码',
  `email` varchar(100) DEFAULT '' COMMENT '电子邮箱',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '登录密码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0:正常，1：禁用)',
  `login_err_count` tinyint(2) NOT NULL DEFAULT '0' COMMENT '登录错误次数',
  `login_lock_time` timestamp NULL DEFAULT NULL COMMENT '登录锁定时间',
  `last_login_ip` varchar(46) DEFAULT '' COMMENT '最后登录IP',
  `last_login_ip_addr` varchar(30) DEFAULT '' COMMENT '最后登录IP归属地',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录IP',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:未删除，1：已删除)',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h7ijwj639ve7gqr9se2yt6k08` (`username`),
  UNIQUE KEY `UK_6kg2i0vryqmphpmk5011xkvnp` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

/*Data for the table `tb_sys_user` */

insert  into `tb_sys_user`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`username`,`realname`,`mobile`,`wx_number`,`email`,`password`,`status`,`login_err_count`,`login_lock_time`,`last_login_ip`,`last_login_ip_addr`,`last_login_time`,`is_deleted`,`deleted_time`) values 
(1,0,'2020-09-25 00:35:33',0,'2020-09-25 00:52:41','admin','测试','13212345678','','','$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUq',0,0,NULL,'','',NULL,0,NULL);

/*Table structure for table `tb_sys_user_role` */

DROP TABLE IF EXISTS `tb_sys_user_role`;

CREATE TABLE `tb_sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK2errn13lk10uamvqsc74vu6do` (`role_id`),
  CONSTRAINT `FK2errn13lk10uamvqsc74vu6do` FOREIGN KEY (`role_id`) REFERENCES `tb_sys_role` (`id`),
  CONSTRAINT `FKmvhc1vphdthitrkdhi47ypbgw` FOREIGN KEY (`user_id`) REFERENCES `tb_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_sys_user_role` */

insert  into `tb_sys_user_role`(`user_id`,`role_id`) values 
(1,1);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT '昵称',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `photo` varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '登录密码',
  `salt` varchar(20) NOT NULL DEFAULT '' COMMENT '密码加盐',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0：正常，1：禁用，1：登录锁定)',
  `login_err_count` tinyint(1) NOT NULL DEFAULT '0' COMMENT '连续登录错误次数',
  `login_lock_time` timestamp NULL DEFAULT NULL COMMENT '登录锁定时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最近登录时间',
  `last_login_ip` varchar(46) DEFAULT '' COMMENT '最近登录IP',
  `last_login_ip_addr` varchar(30) DEFAULT '' COMMENT '最近登录IP归属地',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0：否，1：是)',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`),
  UNIQUE KEY `UK_c4dxpoujf358m9ekdstivfqti` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `tb_user` */

/*Table structure for table `tb_user_info` */

DROP TABLE IF EXISTS `tb_user_info`;

CREATE TABLE `tb_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码',
  `realname` varchar(10) DEFAULT '' COMMENT '姓名',
  `id_card` varchar(18) DEFAULT '' COMMENT '身份证号码',
  `is_verify_realname` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已实名验证(0：未验证，1：已验证)',
  `email` varchar(100) DEFAULT '' COMMENT '电子邮件',
  `is_verify_email` tinyint(1) NOT NULL DEFAULT '0' COMMENT '邮箱是否已验证(0：未验证，1：已验证)',
  `gender` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：保密，1：男，2：女',
  `wx_number` varchar(20) DEFAULT '' COMMENT '微信号',
  `alipay_number` varchar(20) DEFAULT '' COMMENT '支付宝账号',
  `qq` varchar(11) DEFAULT '' COMMENT 'QQ号码',
  `weibo` varchar(20) DEFAULT '' COMMENT '微博账号',
  `source` tinyint(2) NOT NULL DEFAULT '0' COMMENT '注册来源(0：未知，1：安卓，2：苹果，3：微信，99：其他)',
  `device_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '当前使用设备类型(0：未知，1：安卓，2：苹果，3：微信)',
  `ios_device_number` varchar(50) DEFAULT '' COMMENT 'IOS设备号',
  `android_device_number` varchar(50) DEFAULT '' COMMENT 'Android设备号',
  `device_token` varchar(64) DEFAULT '' COMMENT '设备识别码(推送Api提供)',
  `addr` varchar(200) DEFAULT '' COMMENT '当前住址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kaam2e8lownim63k9ffvdhfxd` (`user_id`),
  UNIQUE KEY `UK_4qomiubpnsspuhudrn6937grv` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

/*Data for the table `tb_user_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
