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
('611f0b53195fe7949b332316210e746e','¨Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0idsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0jtit\0$e0777b41-75fd-432d-9d98-7f0cb9a78a4ex\0sr\0java.util.DatehjÅKYt\0\0xpw\0\0tƒÕú@xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valueq\0~\0xpt$eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiZTA3NzdiNDEtNzVmZC00MzJkLTlkOTgtN2YwY2I5YTc4YTRlIiwiaWQiOjEsImV4cCI6MTYwMTAyNzg1NCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiI4MTY3ZGE2MS01Y2EzLTQ5NzctYmQ3MC03YjJkYzk3ZWMyZjMiLCJjbGllbnRfaWQiOiJnYXRld2F5In0.mB8QTJ860H8ppOdtcXaYaUpwi5WC8UVvFCgqScw42Vodlp0XAu14zgsTStyVxXEHZ_QmOW9-EDmIp0jQAh971dBm_pyBi8bS1DZK37TIrHB5v9WjZ-MCC5iu_F2WGvtLSQWVcutU5JYmtYVslCcfIVQ0wTLn1aUcAtaD9BRcCLssq\0~\0w\0\0tƒ≤RÖxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxt\0bearertËeyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiaWQiOjEsImV4cCI6MTYwMTAyOTY0MywiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJlMDc3N2I0MS03NWZkLTQzMmQtOWQ5OC03ZjBjYjlhNzhhNGUiLCJjbGllbnRfaWQiOiJnYXRld2F5In0.lcZ5fh4qQxaZwXwVqc7kPqhBzqgqvlphQIFBTQnH9NvWAqa9tYmGbRrf38sRbHglJ_Jo6uvSxXN59IgIBkcX6RzTL-I64gmeGIXITxK0IGAcCs405TQfTLLRMla4L1dLuc0NFWMYZe4d6vTAwZgC5l2BZNI7Z6DLyZfAiSg2HRg','2f1340f50280f798434696522244202b','admin','gateway','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0adminxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xpsr\09org.springframework.security.oauth2.provider.TokenRequest÷*Ñ∏œ8¯\0L\0	grantTypeq\0~\0xq\0~\0t\0gatewaysq\0~\0sq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rrefresh_tokenteyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiOWYwM2IyNzEtZTgwNC00NWVkLWE0MDQtOWFmZGM5NTA2YjFhIiwiaWQiOjEsImV4cCI6MTYwMTAyNzg1NCwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiODE2N2RhNjEtNWNhMy00OTc3LWJkNzAtN2IyZGM5N2VjMmYzIiwiY2xpZW50X2lkIjoiZ2F0ZXdheSJ9.ThnR4GYbweIW3_ZH5lasKaY3SBrJdKAROksGWCFlrB_E1d_S2SkAHUddC8uWV_Gn2n25flFVJ5FlniwGi4RLmEMA6oZYvp6go-SgHjdPNHVax21Q7-0qiz0v-Zr6ShpHzFKbrHd5HniTwb-RLKdQibeURcSIpD1eF_vJ6o2BijEt\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qt\0\ngrant_typet\0\rrefresh_tokent\0	client_idq\0~\0.xsq\0~\0\"sq\0~\0$w\0\0\0?@\0\0\0\0\0\0xq\0~\06sq\0~\0%w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0[org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0Apt\0\0sr\01com.fank243.cloud.auth.oauth2.model.MyUserDetailsﬁnºnËÉ\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUserµ;Zálmã\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0!','97cf8b0e6bc22b71c28ecd4ac023e83f');

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
('c66dec24f4941d129cde8d5949ec8fb7','¨Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valuet\0Ljava/lang/String;xptteyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiZTFjZTM3NDctMjA5Mi00MTQ3LTg2ZTMtYTljZmZjNjQ5OWEzIiwiZXhwIjoxNjAxMDIxMDIyLCJqdGkiOiI5MmQ1MjEyZS0yZDNjLTQ3YzMtODQzNy1mZGMyNTkxYTBhYzQiLCJjbGllbnRfaWQiOiJnYXRld2F5In0.--PKo9-yAN4L6P30ht2BN2_gAoZELoyxj3sC8FCBHBQsr\0java.util.DatehjÅKYt\0\0xpw\0\0tƒJYx','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0\"w\0\0\0?@\0\0\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0roleq\0~\0xpt\0adminxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0\"w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0\"w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0\0w\0\0\0\0xq\0~\01sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qq\0~\0q\0~\0\Zq\0~\0q\0~\0q\0~\0q\0~\0x\0psr\01com.fank243.cloud.auth.oauth2.model.MyUserDetails∑ÏÊ¢„^6\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUserµ;Zálmã\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0\0w\0\0\0\0xq\0~\0'),
('6a27ab6e6a169edfc16f48256fcb4533','¨Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valuet\0Ljava/lang/String;xptîeyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiOTViN2FiNDItZjAyNC00OGQyLTkwMjMtNzRmMTgxMTc4OTlkIiwiZXhwIjoxNjAxMDI1MzQ1LCJhdXRob3JpdGllcyI6WyJhZG1pbiJdLCJqdGkiOiI2YmY4N2Q2Ni1hNjBhLTQwMzMtYTRlYi0zN2JlMzU5ZWNlZjQiLCJjbGllbnRfaWQiOiJnYXRld2F5In0.9_F_g9N7jICGk__72LIob3ieLyDDhxY8R2_P3ld3eYosr\0java.util.DatehjÅKYt\0\0xpw\0\0tƒå	Sx','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0adminxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0%w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\03sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qq\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!x\0psr\01com.fank243.cloud.auth.oauth2.model.MyUserDetails∑ÏÊ¢„^6\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUserµ;Zálmã\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0!'),
('3005be7b6417de02bcbffa89ab5b658b','¨Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valuet\0Ljava/lang/String;xpt\0$8167da61-5ca3-4977-bd70-7b2dc97ec2f3sr\0java.util.DatehjÅKYt\0\0xpw\0\0tƒ≤RÖx','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0adminxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0%w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\03sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qq\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!x\0psr\01com.fank243.cloud.auth.oauth2.model.MyUserDetailsﬁnºnËÉ\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUserµ;Zálmã\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0!'),
('9208fe5cd0609615d9abe4d14e600184','¨Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/ﬂGcù–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens·\ncT‘^\0L\0valuet\0Ljava/lang/String;xpteyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY2xvdWQtc2VydmljZS11c2VyIiwiY2xvdWQtc2VydmljZS1ub3RpY2UiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJBTEwiXSwiYXRpIjoiOWYwM2IyNzEtZTgwNC00NWVkLWE0MDQtOWFmZGM5NTA2YjFhIiwiaWQiOjEsImV4cCI6MTYwMTAyNzg1NCwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiODE2N2RhNjEtNWNhMy00OTc3LWJkNzAtN2IyZGM5N2VjMmYzIiwiY2xpZW50X2lkIjoiZ2F0ZXdheSJ9.ThnR4GYbweIW3_ZH5lasKaY3SBrJdKAROksGWCFlrB_E1d_S2SkAHUddC8uWV_Gn2n25flFVJ5FlniwGi4RLmEMA6oZYvp6go-SgHjdPNHVax21Q7-0qiz0v-Zr6ShpHzFKbrHd5HniTwb-RLKdQibeURcSIpD1eF_vJ6o2BijEsr\0java.util.DatehjÅKYt\0\0xpw\0\0tƒ≤RÖx','¨Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µÏé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0ÄÀ^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0\0L\0rolet\0Ljava/lang/String;xpt\0adminxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0gatewaysr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0gatewayt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSetÿl◊Zï›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0ALLxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0adminxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0%w\0\0\0?@\0\0\0\0\0t\0cloud-service-usert\0cloud-service-noticexsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\03sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\0>$vLli~R|-[BuKzwgE8Qq\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!x\0psr\01com.fank243.cloud.auth.oauth2.model.MyUserDetailsﬁnºnËÉ\0L\0currUsert\01Lcom/fank243/cloud/component/domain/dto/CurrUser;xpsr\0/com.fank243.cloud.component.domain.dto.CurrUserµ;Zálmã\0L\0idt\0Ljava/lang/Long;L\0passwordq\0~\0L\0permListq\0~\0L\0usernameq\0~\0xpsr\0java.lang.Long;ã‰êÃè#ﬂ\0J\0valuexr\0java.lang.NumberÜ¨ïî‡ã\0\0xp\0\0\0\0\0\0\0t\0<$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUqsq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0!');

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
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ÂàõÂª∫‰∫∫',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÂàõÂª∫Êó∂Èó¥',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '‰øÆÊîπ‰∫∫',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '‰øÆÊîπÊó∂Èó¥',
  `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT 'Â≠óÂÖ∏Á±ªÂûã',
  `dict_label` varchar(100) NOT NULL DEFAULT '' COMMENT 'Â≠óÂÖ∏Ê†áÁ≠æ',
  `dict_value` varchar(100) NOT NULL DEFAULT '' COMMENT 'Â≠óÂÖ∏ÂêçÁß∞',
  `list_class` varchar(100) NOT NULL DEFAULT '' COMMENT 'Ë°®Ê†ºÂõûÊòæÊ†∑Âºè',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ÊòØÂê¶ÈªòËÆ§Ôºà1ÊòØ 0Âê¶Ôºâ',
  `dict_sort` int(4) NOT NULL DEFAULT '0' COMMENT 'Â≠óÂÖ∏ÊéíÂ∫è',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Áä∂ÊÄÅÔºà0Ê≠£Â∏∏ 1ÂÅúÁî®Ôºâ',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT 'Â§áÊ≥®',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Â≠óÂÖ∏Êï∞ÊçÆË°®';

/*Data for the table `tb_sys_dict_data` */

/*Table structure for table `tb_sys_dict_type` */

DROP TABLE IF EXISTS `tb_sys_dict_type`;

CREATE TABLE `tb_sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ÂàõÂª∫‰∫∫',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÂàõÂª∫Êó∂Èó¥',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '‰øÆÊîπ‰∫∫',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '‰øÆÊîπÊó∂Èó¥',
  `dict_type` varchar(100) NOT NULL DEFAULT '' COMMENT 'Â≠óÂÖ∏Á±ªÂûã',
  `dict_name` varchar(100) NOT NULL DEFAULT '' COMMENT 'Â≠óÂÖ∏ÂêçÁß∞',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Áä∂ÊÄÅÔºà0Ê≠£Â∏∏ 1ÂÅúÁî®Ôºâ',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT 'Â§áÊ≥®',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Â≠óÂÖ∏Á±ªÂà´Ë°®';

/*Data for the table `tb_sys_dict_type` */

/*Table structure for table `tb_sys_permission` */

DROP TABLE IF EXISTS `tb_sys_permission`;

CREATE TABLE `tb_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ÂàõÂª∫‰∫∫',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÂàõÂª∫Êó∂Èó¥',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '‰øÆÊîπ‰∫∫',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '‰øÆÊîπÊó∂Èó¥',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Áà∂ËäÇÁÇπID(0Ôºö‰∏ÄÁ∫ßÊùÉÈôê)',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT 'ËµÑÊ∫êÂêçÁß∞',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT 'ËµÑÊ∫êÁ±ªÂûã(button,menu)',
  `permission` varchar(30) NOT NULL DEFAULT '' COMMENT 'ÊùÉÈôê',
  `uri` varchar(255) DEFAULT '' COMMENT 'URI',
  `external` tinyint(1) unsigned DEFAULT '0' COMMENT 'ÊòØÂê¶Â§ñÈÉ®ÈìæÊé•(0:Âê¶,1:ÊòØ)',
  `icon` varchar(30) DEFAULT '' COMMENT 'ÂõæÊ†á',
  `sort` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT 'Â∫èÂè∑',
  `enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT 'ÊòØÂê¶ÂèØÁî®(0:Âê¶Ôºå1ÔºöÊòØ)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='ÊùÉÈôêË°®';

/*Data for the table `tb_sys_permission` */

insert  into `tb_sys_permission`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`pid`,`name`,`type`,`permission`,`uri`,`external`,`icon`,`sort`,`enable`) values 
(1,0,'2020-09-25 00:37:16',0,'2020-09-25 04:03:23',0,'menu:query','MENU','ROLE_ADMIN','',0,'',0,1);

/*Table structure for table `tb_sys_role` */

DROP TABLE IF EXISTS `tb_sys_role`;

CREATE TABLE `tb_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ÂàõÂª∫‰∫∫',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÂàõÂª∫Êó∂Èó¥',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '‰øÆÊîπ‰∫∫',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '‰øÆÊîπÊó∂Èó¥',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT 'ËßíËâ≤ÂêçÁß∞',
  `description` varchar(30) DEFAULT '' COMMENT 'ËßíËâ≤ÊèèËø∞',
  `enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT 'ÊòØÂê¶ÂèØÁî®(0:Âê¶Ôºå1ÔºöÊòØ)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='ËßíËâ≤Ë°®';

/*Data for the table `tb_sys_role` */

insert  into `tb_sys_role`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`name`,`description`,`enable`) values 
(1,0,'2020-09-25 00:36:05',0,'2020-09-25 03:40:04','ROLE_ROOT','Ë∂ÖÁÆ°',1);

/*Table structure for table `tb_sys_user` */

DROP TABLE IF EXISTS `tb_sys_user`;

CREATE TABLE `tb_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ÂàõÂª∫‰∫∫',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÂàõÂª∫Êó∂Èó¥',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '‰øÆÊîπ‰∫∫',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '‰øÆÊîπÊó∂Èó¥',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT 'Áî®Êà∑Âêç',
  `realname` varchar(20) DEFAULT '' COMMENT 'ÂßìÂêç',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT 'ÊâãÊú∫Âè∑Á†Å',
  `wx_number` varchar(20) DEFAULT '' COMMENT 'ÂæÆ‰ø°Âè∑Á†Å',
  `email` varchar(100) DEFAULT '' COMMENT 'ÁîµÂ≠êÈÇÆÁÆ±',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT 'ÁôªÂΩïÂØÜÁ†Å',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Áä∂ÊÄÅ(0:Ê≠£Â∏∏Ôºå1ÔºöÁ¶ÅÁî®)',
  `login_err_count` tinyint(2) NOT NULL DEFAULT '0' COMMENT 'ÁôªÂΩïÈîôËØØÊ¨°Êï∞',
  `login_lock_time` timestamp NULL DEFAULT NULL COMMENT 'ÁôªÂΩïÈîÅÂÆöÊó∂Èó¥',
  `last_login_ip` varchar(46) DEFAULT '' COMMENT 'ÊúÄÂêéÁôªÂΩïIP',
  `last_login_ip_addr` varchar(30) DEFAULT '' COMMENT 'ÊúÄÂêéÁôªÂΩïIPÂΩíÂ±ûÂú∞',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT 'ÊúÄÂêéÁôªÂΩïIP',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ÊòØÂê¶Â∑≤Âà†Èô§(0:Êú™Âà†Èô§Ôºå1ÔºöÂ∑≤Âà†Èô§)',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT 'Âà†Èô§Êó∂Èó¥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h7ijwj639ve7gqr9se2yt6k08` (`username`),
  UNIQUE KEY `UK_6kg2i0vryqmphpmk5011xkvnp` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='ÁÆ°ÁêÜÂëòË°®';

/*Data for the table `tb_sys_user` */

insert  into `tb_sys_user`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`username`,`realname`,`mobile`,`wx_number`,`email`,`password`,`status`,`login_err_count`,`login_lock_time`,`last_login_ip`,`last_login_ip_addr`,`last_login_time`,`is_deleted`,`deleted_time`) values 
(1,0,'2020-09-25 00:35:33',0,'2020-09-25 00:52:41','admin','ÊµãËØï','13212345678','','','$2a$10$n3q9LCeDv0jtRslYPRZBtOe3M0Lb4fmxAte2d7oNG7Kfsp.vv8QUq',0,0,NULL,'','',NULL,0,NULL);

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
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ÂàõÂª∫‰∫∫',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÂàõÂª∫Êó∂Èó¥',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '‰øÆÊîπ‰∫∫',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '‰øÆÊîπÊó∂Èó¥',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT 'Áî®Êà∑Âêç',
  `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT 'ÊòµÁß∞',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT 'ÊâãÊú∫Âè∑Á†Å',
  `photo` varchar(255) NOT NULL DEFAULT '' COMMENT 'Â§¥ÂÉèÂú∞ÂùÄ',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT 'ÁôªÂΩïÂØÜÁ†Å',
  `salt` varchar(20) NOT NULL DEFAULT '' COMMENT 'ÂØÜÁ†ÅÂä†Áõê',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Áä∂ÊÄÅ(0ÔºöÊ≠£Â∏∏Ôºå1ÔºöÁ¶ÅÁî®Ôºå1ÔºöÁôªÂΩïÈîÅÂÆö)',
  `login_err_count` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ËøûÁª≠ÁôªÂΩïÈîôËØØÊ¨°Êï∞',
  `login_lock_time` timestamp NULL DEFAULT NULL COMMENT 'ÁôªÂΩïÈîÅÂÆöÊó∂Èó¥',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT 'ÊúÄËøëÁôªÂΩïÊó∂Èó¥',
  `last_login_ip` varchar(46) DEFAULT '' COMMENT 'ÊúÄËøëÁôªÂΩïIP',
  `last_login_ip_addr` varchar(30) DEFAULT '' COMMENT 'ÊúÄËøëÁôªÂΩïIPÂΩíÂ±ûÂú∞',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ÊòØÂê¶Â∑≤Âà†Èô§(0ÔºöÂê¶Ôºå1ÔºöÊòØ)',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT 'Âà†Èô§Êó∂Èó¥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`),
  UNIQUE KEY `UK_c4dxpoujf358m9ekdstivfqti` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Áî®Êà∑Ë°®';

/*Data for the table `tb_user` */

/*Table structure for table `tb_user_info` */

DROP TABLE IF EXISTS `tb_user_info`;

CREATE TABLE `tb_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ÂàõÂª∫‰∫∫',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÂàõÂª∫Êó∂Èó¥',
  `last_modified_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '‰øÆÊîπ‰∫∫',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '‰øÆÊîπÊó∂Èó¥',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Áî®Êà∑ID',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT 'ÊâãÊú∫Âè∑Á†Å',
  `realname` varchar(10) DEFAULT '' COMMENT 'ÂßìÂêç',
  `id_card` varchar(18) DEFAULT '' COMMENT 'Ë∫´‰ªΩËØÅÂè∑Á†Å',
  `is_verify_realname` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ÊòØÂê¶Â∑≤ÂÆûÂêçÈ™åËØÅ(0ÔºöÊú™È™åËØÅÔºå1ÔºöÂ∑≤È™åËØÅ)',
  `email` varchar(100) DEFAULT '' COMMENT 'ÁîµÂ≠êÈÇÆ‰ª∂',
  `is_verify_email` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ÈÇÆÁÆ±ÊòØÂê¶Â∑≤È™åËØÅ(0ÔºöÊú™È™åËØÅÔºå1ÔºöÂ∑≤È™åËØÅ)',
  `gender` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0Ôºö‰øùÂØÜÔºå1ÔºöÁî∑Ôºå2ÔºöÂ•≥',
  `wx_number` varchar(20) DEFAULT '' COMMENT 'ÂæÆ‰ø°Âè∑',
  `alipay_number` varchar(20) DEFAULT '' COMMENT 'ÊîØ‰ªòÂÆùË¥¶Âè∑',
  `qq` varchar(11) DEFAULT '' COMMENT 'QQÂè∑Á†Å',
  `weibo` varchar(20) DEFAULT '' COMMENT 'ÂæÆÂçöË¥¶Âè∑',
  `source` tinyint(2) NOT NULL DEFAULT '0' COMMENT 'Ê≥®ÂÜåÊù•Ê∫ê(0ÔºöÊú™Áü•Ôºå1ÔºöÂÆâÂçìÔºå2ÔºöËãπÊûúÔºå3ÔºöÂæÆ‰ø°Ôºå99ÔºöÂÖ∂‰ªñ)',
  `device_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ÂΩìÂâç‰ΩøÁî®ËÆæÂ§áÁ±ªÂûã(0ÔºöÊú™Áü•Ôºå1ÔºöÂÆâÂçìÔºå2ÔºöËãπÊûúÔºå3ÔºöÂæÆ‰ø°)',
  `ios_device_number` varchar(50) DEFAULT '' COMMENT 'IOSËÆæÂ§áÂè∑',
  `android_device_number` varchar(50) DEFAULT '' COMMENT 'AndroidËÆæÂ§áÂè∑',
  `device_token` varchar(64) DEFAULT '' COMMENT 'ËÆæÂ§áËØÜÂà´Á†Å(Êé®ÈÄÅApiÊèê‰æõ)',
  `addr` varchar(200) DEFAULT '' COMMENT 'ÂΩìÂâç‰ΩèÂùÄ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kaam2e8lownim63k9ffvdhfxd` (`user_id`),
  UNIQUE KEY `UK_4qomiubpnsspuhudrn6937grv` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Áî®Êà∑‰ø°ÊÅØË°®';

/*Data for the table `tb_user_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
