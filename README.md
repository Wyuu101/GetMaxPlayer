# GetMaxPlayer
Minecraft服务器集群下用于获取各后端服务器最大人数并注册共享的Placeholder变量


## 一、技术原理
将该插件部署到服务器集群中的每个后端服务器，其在加载时会自动读取服务器配置的最大人数值，并通过Redis共享这些信息，实现每个后端服务器可以通过Placeholder轻松解析并获取其他服务器的人数上限。



## 二、配置文件及解释
```yml
server-name: lobby

redis:
  host: localhost
  port: 6379
  password: ""
```

server-name为当前服务器名称，可自定义，则其他服务器可以通过 `/papi parse me %maxplayer_<server-name>%`获取到本服的最大允许人数值。


## 三、注意事项
- 所有后端服务器运行本插件并且需要连接到同一个redis
- 本插件没有注册任何命令
