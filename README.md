### CodeWorld-vue-system权限管理系统

该权限管理系统在*FC权限管理系统*的基础上进行了改造，原*FC权限管理系统*没有使用前后端分离，全部在一个项目里面，可以前往 [FC权限管理系统](https://github.com/javaenigneer/authority-system)

[线上访问地址](http://123.57.64.9:8000/page/login.html)

学习知识是永远也停不下的，我们学的越多，不知道的就越多

因此我们给我们的权限管理系统进行了大换血，使用了目前流行的前后端分离技术

*CodeWorld-vue-system*使用Vue，SpringBoot，SpringSecurity，MyBatis，Redis等技术来实现的

##### 本地部署账号

| 账号     | 密码   | 权限                               |
| -------- | ------ | ---------------------------------- |
| admin    | 123456 | 超级管理员，拥有所有的权限         |
| register | 123456 | 拥有所有的查看权限，具有增加的权限 |

##### 系统模块

系统模块功能如下：

|—系统管理

​     |—用户管理

​     |—角色管理

​     |—菜单管理

​     |—日志管理

|—任务调度

​     |—任务管理

​     |—任务日志

##### 系统特点

```
1.本系统使用SpringBoot作为快速开发框架，大大的提高了我们的开发效率
2.使用SpringSecurity作为权限认证框架，是一个能够为基于Spring的企业应用系统提供声明式的安全访问控制解决方案的安全框架，配合我们的SpringBoot开发更加方便
3.使用MyBatis作为持久层的开发，能够很好的维护我们的SQL语句
4.前后端请求数据校验
5.前端使用Vue作为开发框架，实现了前后端分离的效果
6.前端项目布局多样化，主题多样化
```

##### 技术选型

###### 后端：

[SpringBoot2.3.2.RELEASE](https://spring.io/projects/spring-boot)

[MyBatis2.1.2](http://www.mybatis.cn/)

[SpringSecurity2.3.2.RELEASE](https://spring.io/projects/spring-security)

[Redis2.3.2.RELEASE](https://spring.io/projects/spring-data-redis)

POM文件：
```
<dependencies>

        <!-- springBoot启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!-- SpringBoot Web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- lombok插件 -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>


        <!-- commons-langs -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.6</version>
        </dependency>

        <!-- 日志log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

        <!-- Hibernate校验 -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>6.1.5.Final</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate.common</groupId>
            <artifactId>hibernate-commons-annotations</artifactId>
            <version>5.0.1.Final</version>
        </dependency>

        <!-- swagger -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>1.5.22</version>
        </dependency>
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-models</artifactId>
            <version>1.5.22</version>
        </dependency>

        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>

        <!-- 分页插件 -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.10</version>
        </dependency>

        <!-- 控制台 SQL日志打印插件 -->
        <dependency>
            <groupId>p6spy</groupId>
            <artifactId>p6spy</artifactId>
            <version>3.8.1</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>2.5.4</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>5.2.5.RELEASE</version>
        </dependency>

        <!-- fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.60</version>
        </dependency>

        <!--Jwt-->
        <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.4.1</version>
        </dependency>

        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.0</version>
        </dependency>

        <!-- Hutool工具类 -->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>4.6.2</version>
        </dependency>

        <!-- IP定位插件 -->
        <dependency>
            <groupId>org.lionsoul</groupId>
            <artifactId>ip2region</artifactId>
            <version>1.7</version>
        </dependency>
        <!-- redis缓存 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <!-- 任务调度 -->

        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.3.2</version>
        </dependency>

        <!-- 权限SpringSecurity -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- Jwt-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>

        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-impl</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-core</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>
    </dependencies>
```

###### 前端
[Vue](https://cn.vuejs.org/v2/guide/)

##### 插件说明
- lombok
```text
使用lombok插件可以大大提高我们的效率，通常我们在创建一个基本对象时，我们需要给属性设置getter和setter方法
这个插件只需要设置一个注解@Data就可以完美解决
但是要想让这个注解生效，引入依赖还不行，还需要我们在IDEA中下载插件
```
##### 项目启动说明
本项目使用了Redis缓存，那么我们在电脑上要先把Redis启动起来
在配置文件中设置Redis地址，主机
```java
spring:
  redis:
   host: 192.168.2.4
   database: 0
```
导入我们的数据库
有两个数据库
- codeworld-vue-system.sql 我们管理系统数据库
- codeworld_quartz.sql 任务调度数据库

##### 项目截图
登录界面
[](/image/login.png)




