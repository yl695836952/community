## springboot学习


## 资料

[Spring doc](https://spring.io/guides)
[Spring Web](https://spring.io/guides/gs/serving-web-content/)
[Bootstrap](https://www.bootcss.com/)
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[authorizing-oauth-apps](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)

## 工具

[Git](https://git-scm.com/download)
[visual-paradigm](https://www.visual-paradigm.com/) 



## 脚本
```
user表
    CREATE TABLE `user` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `NAME` varchar(50) DEFAULT NULL,
      `ACCOUNT_ID` varchar(100) DEFAULT NULL,
      `TOKEN` char(36) DEFAULT NULL,
      `GMT_CREATE` bigint(20) DEFAULT NULL,
      `GMT_MODIFIED` bigint(20) DEFAULT NULL,
      `bio` varchar(255) DEFAULT NULL,
      `avatar_url` varchar(100) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

```
```
question表
    CREATE TABLE `question` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `title` varchar(50) DEFAULT NULL,
      `description` text,
      `gmt_create` bigint(20) DEFAULT NULL,
      `gmt_modified` bigint(20) DEFAULT NULL,
      `creator` int(11) DEFAULT NULL,
      `comment_count` int(11) DEFAULT NULL,
      `view_count` int(11) DEFAULT NULL,
      `like_count` int(11) DEFAULT NULL,
      `tag` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
```bash
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```