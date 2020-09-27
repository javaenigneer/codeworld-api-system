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

