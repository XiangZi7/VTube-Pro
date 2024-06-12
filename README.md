# VTube

## 项目简介

VTube 是一个类似于B站的在线视频分享平台，致力于为用户提供高质量的视频分享和观看体验。项目包括视频管理、用户管理、系统管理等功能，并支持PC端和移动端。

## 项目背景

近年来，在线视频分享平台的需求不断增长，用户不再局限于被动观看视频，更希望能上传和分享自己喜欢的视频内容。VTube打算通过这一项目满足这一需求，提供完善的视频管理、评论互动等功能模块，让用户不仅能观看，也能分享和交流。

## 项目结构

### VTube-Admin (进度70%)

**技术栈**

- Vue 3.4
- vue-cropper
- vue-router
- element-plus
- TypeScript
- axios
- eslint
- pinia
- sass
- tailwindcss
- vite5

**功能模块**

- **视频管理**
    - 视频列表：管理所有视频信息，支持视频的增删改查。
    - 周番更新管理：对周番更新进行管理，支持更新周期、更新内容的增删改查。
    - 视频审核：所有在PC端上传的视频需要经过审核，确保内容合规。
- **系统管理**
    - 用户管理：管理平台所有用户信息，支持用户的增删改查。
    - 菜单管理：管理后台系统菜单，支持菜单的增删改查。
    - 角色管理：管理系统角色，支持角色的权限分配。
    - 系统设置：进行系统基本设置，如站点名称、Logo等。
- **....**
### VTube-Java （进度50%）

**技术栈**

- Java
- Spring Boot
- MyBatis Plus
- Sa-Token
- Netty
- Alibaba Druid
- Hutool
- Fastjson
- Redisson
- MinIO
- Log4j
- Captcha

**功能模块**

- 用户认证与授权：采用Sa-Token进行用户认证和授权。
- 实时通讯：使用Netty实现即时通讯功能，支持实时评论和消息通知。
- 数据库访问：使用MyBatis Plus进行数据库操作，提供高效的数据持久层。
- 文件存储：使用MinIO实现分布式文件存储，支持大文件上传。
- 缓存管理：使用Redisson进行分布式缓存管理，提高系统性能。
- ......
### VTube-PC （进度60%）

**技术栈**

- Vue 3.4
- vue-cropper
- vue-router
- element-plus
- TypeScript
- axios
- eslint
- pinia
- sass
- tailwindcss
- vite5

**功能模块**

- 用户登录、注册：支持用户使用邮箱或手机号进行注册和登录。
- 新番和周番资源获取：每天推送最新的新番和周番资源，让用户第一时间获取最新内容。
- 视频上传：用户可以上传自己创作或喜欢的视频，与其他用户分享。
- 视频评论：用户可以对视频进行评论互动。
- ......

### VTube-Uni-App（进度0%）

**技术栈**

- [待定] 

**功能模块**

- 多端支持：计划支持iOS和Android平台，提供一致的用户体验。
- 离线缓存：支持视频离线缓存，方便用户在无网络环境下观看视频。
- 通知推送：及时推送新番更新和评论通知。
- ......

### 数据库

**技术栈**

- MySQL

**数据库设计**

- 用户表：存储用户基本信息，如用户名、密码、邮箱等。
- 视频表：存储视频基本信息，如视频标题、描述、上传时间等。
- 评论表：存储用户对视频的评论内容和时间。
- 角色权限表：存储系统角色和对应的权限。
- ........

## 部署与开发环境设置

### 前提条件

- Node.js 18.x 及以上
- npm 或 yarn
- JDK 17 及以上
- MySQL 8.x 及以上

### 安装步骤

1. 克隆仓库
    ```bash
    git clone https://github.com/XiangZi7/VTube-Pro.git
    ```

2. 后端服务部署

   进入 `VTube-Java` 目录，构建并启动后端服务：
    ```bash
    cd VTube-Java
    ./mvnw clean install
    ./mvnw spring-boot
   -boot:run
    ```

3. 前端服务部署

   进入 `VTube-Admin` 和 `VTube-Pc` 目录，分别构建并启动前端服务：
    ```bash
    # for Admin Panel (推荐使用 yarn OR pnpm
    cd VTube-Admin
    npm install
    npm run dev

    # for PC Client (推荐使用 yarn OR pnpm
    cd VTube-Pc
    npm install
    npm run dev
    ```

   在浏览器中访问 `http://localhost:3377/` 查看PC客户端。

   在浏览器中访问 `http://localhost:3378/` 查看管理后台。

4. 数据库配置

   确保MySQL服务已经启动并运行，然后在 `application-dev.yml` 配置文件中设置数据库连接参数：
    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/vtube
        username: your_username
        password: your_password
    ```

   执行数据库初始化脚本，创建所需的表结构：
   详情见根目录db文件夹刷库
    
## 项目进度

- VTube-Admin: 70%
- VTube-Java: 50%
- VTube-Pc: 60%
- VTube-Uni-App: 0%
