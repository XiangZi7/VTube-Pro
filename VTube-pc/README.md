# VTube-PC

## 项目简介

VTube-PC 作为 VTube 项目的 PC 端应用，主要用于用户在PC上浏览、观看和管理视频内容。该项目使用现代化的前端技术栈，提供丰富的功能和良好的用户体验。

## 技术栈

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
- .....

## 功能模块

### 视频浏览

- 视频推荐：基于用户兴趣和历史观看记录推荐视频。
- 视频分类：按类别浏览视频，支持多层级分类。

### 视频播放

- 高清视频播放：支持720p、1080p等高清晰度视频播放。
- 弹幕评论功能：用户可以实时发送和查看弹幕评论。

### 用户管理

- 用户注册与登录：支持邮箱注册、第三方社交账号登录。
- 用户中心：支持查看和编辑个人信息、历史观看记录等。

### 社交互动

- 视频评论：用户可以对视频进行评论、点赞和回复。
- 消息通知：实时接收评论、点赞等互动消息。

## 项目进度

- VTube-PC: 60%

## 部署与开发环境设置

### 前提条件

- Node.js 18.x 及以上
- npm 或 yarn

### 安装步骤

1. 克隆仓库

    ```bash
    git clone https://github.com/XiangZi7/VTube-Pro.git
    cd vtube-pc
    ```

2. 安装依赖

    ```bash
    npm install
    ```

3. 启动开发服务器

    ```bash
    npm run serve
    ```

   在浏览器中访问 `http://localhost:3377` 查看效果。

4. 构建生产版本

    ```bash
    npm run build
    ```

   生成的静态文件位于 `dist` 目录中。


## 常见问题解答 (FAQ)

### 1. 为什么项目无法启动？

- 确认所有依赖已正确安装。可以尝试删除 `node_modules` 文件夹并重新安装依赖：

    ```bash
    rm -rf node_modules
    npm install
    ```

### 2. 如何解决跨域问题？

- 跨域问题通常由后端服务器处理。确保后端服务器配置了正确的 CORS 规则。

### 3. 如何确保数据安全？

- 确保在前后端通信中使用 HTTPS。
- 不在前端代码中暴露敏感信息，如 API 密钥。