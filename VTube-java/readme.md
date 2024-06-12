## 项目简介

VTube-Java 作为 VTube 项目的后端服务，使用 Spring Boot 框架，提供强大的服务支持和高性能的数据处理。

## 技术栈

- Java 17
- Spring Boot 3x
- sa-token
- MyBatis plus
- Redis
- MySQL
- minio

## 功能模块

### 用户管理

- 用户注册与登录：基于 sa-token 实现用户认证和授权。
- 角色权限控制：支持多角色权限管理。

### 视频管理

- 视频上传与存储：MinIO作为文件存储服务。
- 视频信息管理：通过 MyBatis plus映射视频信息到数据库，支持视频信息的增删改查。
- 视频审核：管理员可以审核用户上传的视频，确保内容合规。

### 消息推送

- 实时通讯：基于 Netty 实现实时聊天和消息推送。
- 消息队列：使用 RabbitMQ 处理异步消息，提高系统性能。

### 缓存管理

- 使用 Redis 进行缓存，提高系统查询性能。

### 其他功能 (待开发)

- 系统监控：集成 Spring Boot Actuator 实现系统健康监控。
- 日志管理：使用 Logback 记录系统日志，并集成 ELK (Elasticsearch, Logstash, Kibana) 进行日志分析和展示。

## 项目进度

- VTube-Java: 50%

## 部署与开发环境设置

### 安装步骤

1. 克隆仓库

    ```bash
    git clone https://github.com/XiangZi7/VTube-Pro.git
    cd vtube-java
    ```

2. 配置数据库连接

   修改 `src/main/resources/application.yml` 文件中的数据库连接信息：

    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/vtube
        username: your_username
        password: your_password
      redis:
        host: localhost
        port: 6379
      rabbitmq:
        host: localhost
        port: 5672
        username: guest
        password: guest
    ```

3. 初始化数据库

   执行 SQL 初始化脚本，创建数据库和表结构：

    ```sql
    -- 初始化数据库的 SQL 脚本，创建所需的表 项目根目录db文件夹
    ```

4. 启动应用

    ```bash
    mvn spring-boot:run
    ```

## 常见问题解答 (FAQ)

### 1. 为什么项目无法启动？

- 确认数据库连接信息配置无误，并且数据库服务已启动。
- 检查 Redis 和 RabbitMQ 服务是否已启动。
- 检查 Maven 依赖是否正确下载，可以尝试重新构建：

    ```bash
    mvn clean install
    ```

### 2. 如何查看日志信息？

- 默认情况下，日志输出到控制台和 `logs` 文件夹中的日志文件中。可以修改 `src/main/resources/logback-spring.xml` 文件进行配置。

### 3. 如何确保数据的安全性？

- 确保数据库连接配置使用安全的用户名和密码。
- 使用 HTTPS 配置 Spring Boot 应用，确保数据传输加密。

## 项目贡献

欢迎对本项目进行贡献，请遵循以下步骤：

1. Fork 此仓库
2. 创建一个分支 (`git checkout -b feature/new-feature`)
3. 提交你的修改 (`git commit -am 'Add some feature'`)
4. 推送到远程分支 (`git push origin feature/new-feature`)
5. 创建一个 Pull Request
