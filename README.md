# VTube
## 项目简介

VTube 是一个类似于B站的在线视频分享平台，致力于为用户提供高质量的视频分享和观看体验。项目包括视频管理、用户管理、系统管理等功能，并支持PC端和移动端。

## 项目背景

近年来，在线视频分享平台的需求不断增长，用户不再局限于被动观看视频，更希望能上传和分享自己喜欢的视频内容。VTube打算通过这一项目满足这一需求，提供完善的视频管理、评论互动等功能模块，让用户不仅能观看，也能分享和交流。

## 项目进度

- VTube-Admin: 70%
- VTube-Java: 50%
- VTube-Pc: 60%
- VTube-Uni-App: 0%

## 项目部分截图
### VTube-admin
![img.png](images/img.png)
### VTube-pc
![img_1.png](images/img_1.png)
![img_2.png](images/img_2.png)
![img_3.png](images/img_3.png)

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