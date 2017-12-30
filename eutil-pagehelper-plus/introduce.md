## 介绍
本工具类是基于jdk1.8和pagehelper实现的分页封装,本文不提供pagehelper的依赖,需要使用时候自行导入
,多环境的问题.

## 使用介绍
本介绍基于spring boot介绍
其他项目请基于官方文档的参考[pagehelper文档](https://gitee.com/free/Mybatis_PageHelper)中的集成示例

1. 导入pagehelper的依赖
```xml
<!--mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.1</version>
        </dependency>
        <!--mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>1.1.4</version>
        </dependency>
        <!--pagehelper-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.1</version>
        </dependency>
```

2.yaml配置

```yaml
pagehelper:
  helperDialect: mysql
  reasonable: true
  supportMethodsArguments: true
  params=count: countSql
```

3.工具类使用
```java

BaseServiceUtil<UserInfo,PageInfo> serviceUtil = BaseServiceUtil.getInstance();
Pagination<UserInfo> result = serviceUtil.search(pageInfo, userInfoMapper::getUserList);
return result;
```