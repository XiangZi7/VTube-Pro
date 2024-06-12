# VTube-Admin

VTube-Admin 是 VTube 项目的管理后台，提供视频管理、用户管理、系统管理等功能，帮助管理员高效地管理平台内容和用户。


## 项目简介
VTube-Admin 作为 VTube 项目的管理后台，使用现代化的前端框架和工具，提供友好且高效的管理界面。

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

## 项目截图
![图片描述](../images/admin_img%20(1).png)
![图片描述](../images/admin_img%20(5).png)
![图片描述](../images/admin_img%20(6).png)
![图片描述](../images/admin_img%20(7).png)
![图片描述](../images/admin_img%20(8).png)
![图片描述](../images/admin_img%20(9).png)
## 功能模块

### 视频管理

- 视频列表：管理所有视频信息，支持视频的增删改查。
- 周番更新管理：对周番更新进行管理，支持更新周期、更新内容的增删改查。
- 视频审核：所有在PC端上传的视频需要经过审核，确保内容合规。

### 系统管理

- 用户管理：管理平台所有用户信息，支持用户的增删改查。
- 菜单管理：管理后台系统菜单，支持菜单的增删改查。
- 角色管理：管理系统角色，支持角色的权限分配。
- 系统设置：进行系统基本设置，如站点名称、Logo等。

## 项目进度

- VTube-Admin: 70%

## 部署与开发环境设置

### 前提条件

- Node.js 18.x 及以上
- npm 或 yarn

### 安装步骤

1. 克隆仓库
    ```bash
    git clone https://github.com/XiangZi7/VTube-Pro.git
    cd vtube-admin
    ```

2. 安装依赖
    ```bash
    npm install
    ```

3. 启动开发服务器
    ```bash
    npm run dev
    ```

   在浏览器中访问 `http://localhost:3378` 查看效果。

## 常见问题解答 (FAQ)

### 1. 为什么我运行项目时遇到启动失败？

- 确认所有依赖已正确安装。可以尝试删除 `node_modules` 文件夹并重新安装依赖：
    ```bash
    rm -rf node_modules
    npm install
    ```

## 使用方法

### 登录管理后台

1. 使用管理员账号和密码登录管理后台。
2. 登录后，可以通过左侧导航栏访问视频管理、系统管理等功能模块。

### 视频管理操作

1. 在“视频管理”页面可以查看所有上传的视频。
2. 对视频进行编辑、删除操作。
3. 通过审核或驳回用户上传的视频。

### 系统管理操作

1. 在“系统管理”页面，管理员可以管理用户、角色、菜单等系统信息。
2. 在“用户管理”页面，可以添加或删除用户，查看用户的详细信息。
3. 在“菜单管理”页面，可以增删改查系统菜单，确保菜单结构的合理性。

## 项目贡献

欢迎对本项目进行贡献，请遵循以下步骤：

1. Fork 此仓库
2. 创建一个分支 (`git checkout -b feature/new-feature`)
3. 提交你的修改 (`git commit -am 'Add some feature'`)
4. 推送到远程分支 (`git push origin feature/new-feature`)
5. 创建一个 Pull Request

[//]: # (## 许可证)

[//]: # (此项目采用 [MIT 许可证]&#40;LICENSE&#41; 进行许可。)