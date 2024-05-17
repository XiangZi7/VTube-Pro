/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : localhost:3306
 Source Schema         : vtube

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 17/05/2024 17:59:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vt_admin
-- ----------------------------
DROP TABLE IF EXISTS `vt_admin`;
CREATE TABLE `vt_admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员密码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_admin
-- ----------------------------
INSERT INTO `vt_admin` VALUES (1, 'admin1', 'adminpass1', '2022-01-01 18:30:00', NULL);
INSERT INTO `vt_admin` VALUES (2, 'admin2', 'adminpass2', '2022-01-02 17:15:00', NULL);
INSERT INTO `vt_admin` VALUES (3, 'admin3', 'adminpass3', '2022-01-03 22:00:00', NULL);

-- ----------------------------
-- Table structure for vt_balance
-- ----------------------------
DROP TABLE IF EXISTS `vt_balance`;
CREATE TABLE `vt_balance`  (
  `balance_id` int NOT NULL AUTO_INCREMENT COMMENT '余额ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`balance_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `vt_balance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '余额表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_balance
-- ----------------------------
INSERT INTO `vt_balance` VALUES (1, 1, 100.00, '2024-04-24 14:05:59');

-- ----------------------------
-- Table structure for vt_category
-- ----------------------------
DROP TABLE IF EXISTS `vt_category`;
CREATE TABLE `vt_category`  (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `parent_id` int NULL DEFAULT NULL COMMENT '父分类ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  INDEX `fk_parent_category_id`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `fk_parent_category_id` FOREIGN KEY (`parent_id`) REFERENCES `vt_category` (`category_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_category
-- ----------------------------
INSERT INTO `vt_category` VALUES (1, NULL, '动漫', '2022-01-01 17:30:00');
INSERT INTO `vt_category` VALUES (2, NULL, '电影', '2022-01-02 16:15:00');
INSERT INTO `vt_category` VALUES (3, NULL, '音乐', '2022-01-03 21:00:00');
INSERT INTO `vt_category` VALUES (4, NULL, '剧场', '2022-01-04 14:45:00');
INSERT INTO `vt_category` VALUES (5, NULL, 'VLog', '2022-01-05 20:30:00');

-- ----------------------------
-- Table structure for vt_comment
-- ----------------------------
DROP TABLE IF EXISTS `vt_comment`;
CREATE TABLE `vt_comment`  (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `video_id` int NULL DEFAULT NULL COMMENT '视频ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `vt_comment_ibfk_1`(`video_id` ASC) USING BTREE,
  CONSTRAINT `vt_comment_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `vt_video` (`video_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `vt_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_comment
-- ----------------------------

-- ----------------------------
-- Table structure for vt_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `vt_dictionary`;
CREATE TABLE `vt_dictionary`  (
  `dictionary_id` int NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典类型',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  PRIMARY KEY (`dictionary_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_dictionary
-- ----------------------------
INSERT INTO `vt_dictionary` VALUES (1, 'gender', '1', 'Male');
INSERT INTO `vt_dictionary` VALUES (2, 'gender', '2', 'Female');
INSERT INTO `vt_dictionary` VALUES (3, 'status', 'A', 'Active');
INSERT INTO `vt_dictionary` VALUES (4, 'status', 'I', 'Inactive');
INSERT INTO `vt_dictionary` VALUES (5, 'role', '1', 'Admin');
INSERT INTO `vt_dictionary` VALUES (6, 'role', '2', 'User');
INSERT INTO `vt_dictionary` VALUES (7, 'role', '3', 'Moderator');

-- ----------------------------
-- Table structure for vt_favorite
-- ----------------------------
DROP TABLE IF EXISTS `vt_favorite`;
CREATE TABLE `vt_favorite`  (
  `favorite_id` int NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `video_id` int NULL DEFAULT NULL COMMENT '视频ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`favorite_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `vt_favorite_ibfk_2`(`video_id` ASC) USING BTREE,
  CONSTRAINT `vt_favorite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vt_favorite_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `vt_video` (`video_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_favorite
-- ----------------------------

-- ----------------------------
-- Table structure for vt_follow
-- ----------------------------
DROP TABLE IF EXISTS `vt_follow`;
CREATE TABLE `vt_follow`  (
  `follow_id` int NOT NULL AUTO_INCREMENT COMMENT '关注ID',
  `follower_id` int NULL DEFAULT NULL COMMENT '粉丝用户ID',
  `followed_id` int NULL DEFAULT NULL COMMENT '被关注用户ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`follow_id`) USING BTREE,
  INDEX `follower_id`(`follower_id` ASC) USING BTREE,
  INDEX `followed_id`(`followed_id` ASC) USING BTREE,
  CONSTRAINT `vt_follow_ibfk_1` FOREIGN KEY (`follower_id`) REFERENCES `vt_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vt_follow_ibfk_2` FOREIGN KEY (`followed_id`) REFERENCES `vt_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_follow
-- ----------------------------
INSERT INTO `vt_follow` VALUES (1, 1, 2, '2022-01-01 15:30:00');
INSERT INTO `vt_follow` VALUES (2, 2, 3, '2022-01-02 14:20:00');
INSERT INTO `vt_follow` VALUES (3, 3, 4, '2022-01-03 19:00:00');
INSERT INTO `vt_follow` VALUES (4, 4, 5, '2022-01-04 12:45:00');
INSERT INTO `vt_follow` VALUES (5, 5, 1, '2022-01-05 18:30:00');
INSERT INTO `vt_follow` VALUES (6, 1, 2, '2022-01-01 15:30:00');
INSERT INTO `vt_follow` VALUES (7, 2, 3, '2022-01-02 14:20:00');
INSERT INTO `vt_follow` VALUES (8, 3, 4, '2022-01-03 19:00:00');
INSERT INTO `vt_follow` VALUES (9, 4, 5, '2022-01-04 12:45:00');
INSERT INTO `vt_follow` VALUES (10, 5, 1, '2022-01-05 18:30:00');

-- ----------------------------
-- Table structure for vt_like
-- ----------------------------
DROP TABLE IF EXISTS `vt_like`;
CREATE TABLE `vt_like`  (
  `like_id` int NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `video_id` int NULL DEFAULT NULL COMMENT '视频ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`like_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `vt_like_ibfk_2`(`video_id` ASC) USING BTREE,
  CONSTRAINT `vt_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vt_like_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `vt_video` (`video_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_like
-- ----------------------------

-- ----------------------------
-- Table structure for vt_permission
-- ----------------------------
DROP TABLE IF EXISTS `vt_permission`;
CREATE TABLE `vt_permission`  (
  `permission_id` int NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限代码',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_permission
-- ----------------------------
INSERT INTO `vt_permission` VALUES (1, 'Permission 1', 'P1');
INSERT INTO `vt_permission` VALUES (2, 'Permission 2', 'P2');
INSERT INTO `vt_permission` VALUES (3, 'Permission 3', 'P3');

-- ----------------------------
-- Table structure for vt_role
-- ----------------------------
DROP TABLE IF EXISTS `vt_role`;
CREATE TABLE `vt_role`  (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_role
-- ----------------------------
INSERT INTO `vt_role` VALUES (1, 'Admin');
INSERT INTO `vt_role` VALUES (2, 'User');
INSERT INTO `vt_role` VALUES (3, 'Moderator');

-- ----------------------------
-- Table structure for vt_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `vt_role_permission`;
CREATE TABLE `vt_role_permission`  (
  `role_id` int NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `permission_id`(`permission_id` ASC) USING BTREE,
  CONSTRAINT `vt_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `vt_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vt_role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `vt_permission` (`permission_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_role_permission
-- ----------------------------
INSERT INTO `vt_role_permission` VALUES (1, 1);
INSERT INTO `vt_role_permission` VALUES (3, 1);
INSERT INTO `vt_role_permission` VALUES (1, 2);
INSERT INTO `vt_role_permission` VALUES (2, 2);
INSERT INTO `vt_role_permission` VALUES (2, 3);
INSERT INTO `vt_role_permission` VALUES (3, 3);

-- ----------------------------
-- Table structure for vt_user
-- ----------------------------
DROP TABLE IF EXISTS `vt_user`;
CREATE TABLE `vt_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_user
-- ----------------------------
INSERT INTO `vt_user` VALUES (1, 'user1', 'password1', '1234567890', 'user1@example.com', '2022-01-01 12:00:00', NULL);
INSERT INTO `vt_user` VALUES (2, 'user2', 'password2', '0987654321', 'user2@example.com', '2022-01-02 10:30:00', NULL);
INSERT INTO `vt_user` VALUES (3, 'user3', 'password3', '1357924680', 'user3@example.com', '2022-01-03 15:45:00', NULL);
INSERT INTO `vt_user` VALUES (4, 'user4', 'password4', '2468135790', 'user4@example.com', '2022-01-04 08:20:00', NULL);
INSERT INTO `vt_user` VALUES (5, 'user5', 'password5', '9876543210', 'user5@example.com', '2022-01-05 14:10:00', NULL);
INSERT INTO `vt_user` VALUES (6, 'user1', 'password1', '1234567890', 'user1@example.com', '2022-01-01 12:00:00', NULL);
INSERT INTO `vt_user` VALUES (7, 'user2', 'password2', '0987654321', 'user2@example.com', '2022-01-02 10:30:00', NULL);
INSERT INTO `vt_user` VALUES (8, 'user3', 'password3', '1357924680', 'user3@example.com', '2022-01-03 15:45:00', NULL);
INSERT INTO `vt_user` VALUES (9, 'user4', 'password4', '2468135790', 'user4@example.com', '2022-01-04 08:20:00', NULL);
INSERT INTO `vt_user` VALUES (10, 'user5', 'password5', '9876543210', 'user5@example.com', '2022-01-05 14:10:00', NULL);

-- ----------------------------
-- Table structure for vt_user_role
-- ----------------------------
DROP TABLE IF EXISTS `vt_user_role`;
CREATE TABLE `vt_user_role`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `vt_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vt_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `vt_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_user_role
-- ----------------------------
INSERT INTO `vt_user_role` VALUES (1, 1);
INSERT INTO `vt_user_role` VALUES (2, 2);
INSERT INTO `vt_user_role` VALUES (3, 2);
INSERT INTO `vt_user_role` VALUES (4, 3);

-- ----------------------------
-- Table structure for vt_video
-- ----------------------------
DROP TABLE IF EXISTS `vt_video`;
CREATE TABLE `vt_video`  (
  `video_id` int NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '视频描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `video_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频地址',
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面地址',
  PRIMARY KEY (`video_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_video
-- ----------------------------
INSERT INTO `vt_video` VALUES (1, '妖精的秘密', 'You cannot save people, you can just love them. After comparing data, the window shows the                    ', '2010-02-12 08:11:55', 'SBycATKioa', 'Pc7eOHToPM');
INSERT INTO `vt_video` VALUES (2, '橘子星球', 'It provides strong authentication and secure encrypted communications between two hosts, known                ', '2005-10-14 00:29:10', '4K8fSQphw0', '7Wx9P8x0wD');
INSERT INTO `vt_video` VALUES (3, '天使之泪', 'With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create,             ', '2020-12-14 06:12:37', 'rqmyvyRnt2', 'Vx994n0HZ8');
INSERT INTO `vt_video` VALUES (4, '魔法少女萌音', 'Secure SHell (SSH) is a program to log in into another computer over a network, execute commands              ', '2015-07-15 00:30:20', 'kfLzM9C5my', 'klYXIfjsfd');
INSERT INTO `vt_video` VALUES (5, '银魂之歌', 'The past has no power over the present moment. It can also manage cloud databases such as Amazon              ', '2002-08-10 05:50:29', 'yDUF1CrOUs', 'fDvIXSBldp');
INSERT INTO `vt_video` VALUES (6, '银河传说', 'All the Navicat Cloud objects are located under different projects. You can share the project                 ', '2009-09-11 09:55:03', 'erQwhgouG0', 'jF6cNaUYno');
INSERT INTO `vt_video` VALUES (7, '彩虹岛屿', 'Remember that failure is an event, not a person. I destroy my enemies when I make them my friends.            ', '2008-12-03 21:30:38', 'iZdwW39L7Z', 'KeNDYAN0IW');
INSERT INTO `vt_video` VALUES (8, '幻想花园', 'Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more.               ', '2007-03-15 18:45:22', 'qZz43NwReh', 'bwcbz08btH');
INSERT INTO `vt_video` VALUES (9, '奇幻之旅', 'If opportunity doesn’t knock, build a door. What you get by achieving your goals is not as                  ', '2005-11-25 12:13:27', 'M2kVBvQvEi', '8u7GStkyQt');
INSERT INTO `vt_video` VALUES (10, '课怕喵', 'Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases                  ', '2003-09-21 22:32:13', 'n0oJaX4KMR', 'Wb6uZpQ6PM');
INSERT INTO `vt_video` VALUES (11, '天使之梦', 'In a Telnet session, all communications, including username and password, are transmitted in                  ', '2023-03-12 04:18:35', 'HQjYOTvWzR', 'jAovE5xAYM');
INSERT INTO `vt_video` VALUES (12, '幻想花园', 'I destroy my enemies when I make them my friends. Navicat is a multi-connections Database Administration      ', '2015-10-01 05:30:48', 'Z8r0zPkeDT', 'W83qhjcbGq');
INSERT INTO `vt_video` VALUES (13, '天香斗牛', 'Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed                  ', '2009-03-19 23:26:11', 'htlDlH3hkR', 'gWodbMRrn8');
INSERT INTO `vt_video` VALUES (14, '星际之心', 'In a Telnet session, all communications, including username and password, are transmitted in                  ', '2004-09-02 03:22:45', 'UVNwW9FspM', 'NzYtpXks3s');
INSERT INTO `vt_video` VALUES (15, '魔法学院', 'Navicat provides a wide range advanced features, such as compelling code editing capabilities,                ', '2020-11-15 18:14:38', 'q298kzyjVV', 'YgwErOH8vp');
INSERT INTO `vt_video` VALUES (16, '魔法少女萌音', 'In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons                ', '2018-07-10 15:38:50', 'ogYRPMeWc4', 'zDEFRCRaQX');
INSERT INTO `vt_video` VALUES (17, '机械之心', 'Navicat Data Modeler is a powerful and cost-effective database design tool which helps you                    ', '2008-10-06 17:45:59', 'zstonRlW2e', 'Cx1hn1cygJ');
INSERT INTO `vt_video` VALUES (18, '天使之梦', 'Navicat Cloud provides a cloud service for synchronizing connections, queries, model files                    ', '2002-03-20 20:08:44', '0NexYfLure', 'ZaZEjfmSqE');
INSERT INTO `vt_video` VALUES (19, '琉璃的境界', 'Champions keep playing until they get it right. It collects process metrics such as CPU load,                 ', '2018-07-03 23:36:46', 'mnDAWbHVqG', 'tRN0dTKVbM');
INSERT INTO `vt_video` VALUES (20, '奇幻森林之歌', 'In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons                ', '2008-05-15 01:12:57', 'VwcvPoMOxC', 'cU67iCZN65');
INSERT INTO `vt_video` VALUES (21, '琉璃的境界', 'Optimism is the one quality more associated with success and happiness than any other.                        ', '2000-05-06 17:55:58', 'k67VhsU6G7', 'Oz1r3CVc6P');
INSERT INTO `vt_video` VALUES (22, '天使之泪', 'I may not have gone where I intended to go, but I think I have ended up where I needed to be.                 ', '2016-08-13 07:02:59', '04CiUjbqZB', 'JX5YXoslXl');
INSERT INTO `vt_video` VALUES (23, '梦幻乐园', 'If opportunity doesn’t knock, build a door. What you get by achieving your goals is not as                  ', '2013-04-04 19:33:38', 'oi8rEMb6r7', 'hKd9AnOXv7');
INSERT INTO `vt_video` VALUES (24, '星河航行', 'To successfully establish a new connection to local/remote server - no matter via SSL, SSH                    ', '2000-11-09 22:09:53', 'TbfhE90Puf', 'OW8eA9Q79q');
INSERT INTO `vt_video` VALUES (25, '航海王之歌', 'To successfully establish a new connection to local/remote server - no matter via SSL or SSH,                 ', '2021-03-29 15:30:44', 'Px0PlVvIne', 'T5RFjK7yQh');
INSERT INTO `vt_video` VALUES (26, '幻想乐园', 'You can select any connections, objects or projects, and then select the corresponding buttons                ', '2014-08-16 07:40:08', 'oEK8KGX7Ye', 'ILn2tXcrXn');
INSERT INTO `vt_video` VALUES (27, '星空漫步', 'Navicat 15 has added support for the system-wide dark mode. To open a query using an external                 ', '2015-03-30 01:39:51', 'elyrIbVl9F', 'kzsh27j3Q5');
INSERT INTO `vt_video` VALUES (28, '黑暗之光', 'A comfort zone is a beautiful place, but nothing ever grows there. Remember that failure is                   ', '2005-01-11 18:22:54', '2CiPbngKT7', 'V5q5NxDzDo');
INSERT INTO `vt_video` VALUES (29, '星之旅程', 'Navicat Cloud could not connect and access your databases. By which it means, it could only                   ', '2016-02-07 00:25:46', '92jrzUB739', 'ZE7LcVajzP');
INSERT INTO `vt_video` VALUES (30, '银魂之歌', 'There is no way to happiness. Happiness is the way. A comfort zone is a beautiful place, but                  ', '2012-06-27 21:24:32', 'aoI8GCYDCV', 'zMFXHJm7mw');
INSERT INTO `vt_video` VALUES (31, '彩虹之桥', 'I may not have gone where I intended to go, but I think I have ended up where I needed to be.                 ', '2016-06-23 19:15:24', 'lyAEdroW2j', 'yue1dtsUzf');
INSERT INTO `vt_video` VALUES (32, '幻想乐园', 'If you wait, all that happens is you get older. If opportunity doesn’t knock, build a door.                 ', '2018-12-14 21:09:28', 'BnFXfGM7Z0', 'K9E4UguBKH');
INSERT INTO `vt_video` VALUES (33, '机械之心', 'Navicat Monitor can be installed on any local computer or virtual machine and does not require                ', '2009-08-07 03:42:15', '5U2gMH8Ij2', '1VZSo5GuNh');
INSERT INTO `vt_video` VALUES (34, '幻想花园', 'If your Internet Service Provider (ISP) does not provide direct access to its server, Secure                  ', '2001-07-03 04:11:01', 'OY9cwAendL', 'mtJ2GGAidA');
INSERT INTO `vt_video` VALUES (35, '幻想乐园', 'The first step is as good as half over. I will greet this day with love in my heart.                          ', '2004-05-25 08:33:57', 'NNrHVf9Z4K', '5e5yjd5vNP');
INSERT INTO `vt_video` VALUES (36, '奇幻森林之歌', 'The past has no power over the present moment. Anyone who has ever made anything of importance                ', '2007-04-10 06:16:50', 'RzccLaI0aU', 'TojYzK8SuM');
INSERT INTO `vt_video` VALUES (37, '星空漫步', 'If you wait, all that happens is you get older. Genius is an infinite capacity for taking pains.              ', '2015-12-29 07:46:34', 'vQLI0YPvQg', 'FHTwfaILCq');
INSERT INTO `vt_video` VALUES (38, '天香斗牛', 'If it scares you, it might be a good thing to try. Such sessions are also susceptible to session              ', '2016-10-24 08:18:31', 'L8vtuuEmBa', 'uHyhVPZWhZ');
INSERT INTO `vt_video` VALUES (39, '境界的彼方', 'Navicat authorizes you to make connection to remote servers running on different platforms                    ', '2008-04-06 05:59:38', 'hfgyQswAyY', 'zTDijpQJ2C');
INSERT INTO `vt_video` VALUES (40, '彩虹岛屿', 'A man’s best friends are his ten fingers. Champions keep playing until they get it right.                   ', '2013-03-30 22:34:40', 'NtpcwuXuzk', 'QkKqLRou10');
INSERT INTO `vt_video` VALUES (41, '梦幻乐园', 'How we spend our days is, of course, how we spend our lives. In a Telnet session, all communications,         ', '2021-07-19 22:59:48', 'BHgK7Hi5mG', 'rCeZTpVQqm');
INSERT INTO `vt_video` VALUES (42, '星际之心', 'Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases                  ', '2024-04-18 12:12:56', 'GMVOXLVdGz', 'f7Ts9Dnl6q');
INSERT INTO `vt_video` VALUES (43, '原子能少女', 'I destroy my enemies when I make them my friends. What you get by achieving your goals is not                 ', '2010-09-27 17:24:27', 'R0tEFF9ucC', '02An06j6yi');
INSERT INTO `vt_video` VALUES (44, '魔法少女萌音', 'I destroy my enemies when I make them my friends. The Navigation pane employs tree structure                  ', '2006-10-17 18:15:56', 'KWacXZoLIG', 'YptuDTdBJI');
INSERT INTO `vt_video` VALUES (45, '星之旅程', 'Navicat authorizes you to make connection to remote servers running on different platforms                    ', '2003-11-04 07:49:31', 'fbQvAd5eWc', 'xRbRBYKyaS');
INSERT INTO `vt_video` VALUES (46, '神秘宝藏', 'A query is used to extract data from the database in a readable format according to the user\'s request.       ', '2016-08-16 09:25:12', '9vGh4BMDCA', 'L6ffNMzLGm');
INSERT INTO `vt_video` VALUES (47, '课怕喵', 'Such sessions are also susceptible to session hijacking, where a malicious user takes over                    ', '2020-05-31 19:24:02', 'qUIC51hFCU', 'VpPQZ2d03X');
INSERT INTO `vt_video` VALUES (48, '龙珠传奇', 'To open a query using an external editor, control-click it and select Open with External Editor.              ', '2004-12-23 16:55:59', '1lpAhFxXMo', 'rMVqUX2QSN');
INSERT INTO `vt_video` VALUES (49, '天使之梦', 'The Information Pane shows the detailed object information, project activities, the DDL of                    ', '2021-06-28 19:10:12', 'rHKAp2P3Df', 'S7tP2zy3w8');
INSERT INTO `vt_video` VALUES (50, '星际迷航之梦', 'In other words, Navicat provides the ability for data in different databases and/or schemas                   ', '2020-01-02 09:46:29', 'GZ0yH53hH4', 'DUyIiMdaTW');

-- ----------------------------
-- Table structure for vt_video_category
-- ----------------------------
DROP TABLE IF EXISTS `vt_video_category`;
CREATE TABLE `vt_video_category`  (
  `video_id` int NOT NULL COMMENT '视频ID',
  `category_id` int NULL DEFAULT NULL COMMENT '分类ID',
  PRIMARY KEY (`video_id`) USING BTREE,
  INDEX `vt_video_category_ibfk_2`(`category_id` ASC) USING BTREE,
  CONSTRAINT `vt_video_category_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `vt_video` (`video_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `vt_video_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `vt_category` (`category_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_video_category
-- ----------------------------
INSERT INTO `vt_video_category` VALUES (2, 1);
INSERT INTO `vt_video_category` VALUES (6, 1);
INSERT INTO `vt_video_category` VALUES (7, 1);
INSERT INTO `vt_video_category` VALUES (8, 1);
INSERT INTO `vt_video_category` VALUES (10, 1);
INSERT INTO `vt_video_category` VALUES (14, 1);
INSERT INTO `vt_video_category` VALUES (15, 1);
INSERT INTO `vt_video_category` VALUES (17, 1);
INSERT INTO `vt_video_category` VALUES (19, 1);
INSERT INTO `vt_video_category` VALUES (20, 1);
INSERT INTO `vt_video_category` VALUES (22, 1);
INSERT INTO `vt_video_category` VALUES (27, 1);
INSERT INTO `vt_video_category` VALUES (35, 1);
INSERT INTO `vt_video_category` VALUES (40, 1);
INSERT INTO `vt_video_category` VALUES (5, 2);
INSERT INTO `vt_video_category` VALUES (18, 2);
INSERT INTO `vt_video_category` VALUES (21, 2);
INSERT INTO `vt_video_category` VALUES (32, 2);
INSERT INTO `vt_video_category` VALUES (33, 2);
INSERT INTO `vt_video_category` VALUES (34, 2);
INSERT INTO `vt_video_category` VALUES (46, 2);
INSERT INTO `vt_video_category` VALUES (47, 2);
INSERT INTO `vt_video_category` VALUES (48, 2);
INSERT INTO `vt_video_category` VALUES (1, 3);
INSERT INTO `vt_video_category` VALUES (12, 3);
INSERT INTO `vt_video_category` VALUES (25, 3);
INSERT INTO `vt_video_category` VALUES (28, 3);
INSERT INTO `vt_video_category` VALUES (29, 3);
INSERT INTO `vt_video_category` VALUES (30, 3);
INSERT INTO `vt_video_category` VALUES (36, 3);
INSERT INTO `vt_video_category` VALUES (45, 3);
INSERT INTO `vt_video_category` VALUES (50, 3);
INSERT INTO `vt_video_category` VALUES (9, 4);
INSERT INTO `vt_video_category` VALUES (16, 4);
INSERT INTO `vt_video_category` VALUES (23, 4);
INSERT INTO `vt_video_category` VALUES (24, 4);
INSERT INTO `vt_video_category` VALUES (31, 4);
INSERT INTO `vt_video_category` VALUES (3, 5);
INSERT INTO `vt_video_category` VALUES (4, 5);
INSERT INTO `vt_video_category` VALUES (11, 5);
INSERT INTO `vt_video_category` VALUES (13, 5);
INSERT INTO `vt_video_category` VALUES (26, 5);
INSERT INTO `vt_video_category` VALUES (37, 5);
INSERT INTO `vt_video_category` VALUES (38, 5);
INSERT INTO `vt_video_category` VALUES (39, 5);
INSERT INTO `vt_video_category` VALUES (41, 5);
INSERT INTO `vt_video_category` VALUES (42, 5);
INSERT INTO `vt_video_category` VALUES (43, 5);
INSERT INTO `vt_video_category` VALUES (44, 5);
INSERT INTO `vt_video_category` VALUES (49, 5);

-- ----------------------------
-- Table structure for vt_video_data
-- ----------------------------
DROP TABLE IF EXISTS `vt_video_data`;
CREATE TABLE `vt_video_data`  (
  `video_id` int NOT NULL,
  `user_id` int NOT NULL,
  `views` int NULL DEFAULT 0 COMMENT '播放量',
  `likes` int NULL DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`video_id`) USING BTREE,
  INDEX `vt_video_data_ibfk_2`(`user_id` ASC) USING BTREE,
  CONSTRAINT `vt_video_data_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `vt_video` (`video_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `vt_video_data_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_video_data
-- ----------------------------
INSERT INTO `vt_video_data` VALUES (1, 8, 103, 525);
INSERT INTO `vt_video_data` VALUES (2, 4, 263, 609);
INSERT INTO `vt_video_data` VALUES (3, 3, 82, 773);
INSERT INTO `vt_video_data` VALUES (4, 9, 912, 234);
INSERT INTO `vt_video_data` VALUES (5, 2, 781, 106);
INSERT INTO `vt_video_data` VALUES (6, 2, 870, 641);
INSERT INTO `vt_video_data` VALUES (7, 3, 156, 564);
INSERT INTO `vt_video_data` VALUES (8, 9, 854, 307);
INSERT INTO `vt_video_data` VALUES (9, 2, 421, 649);
INSERT INTO `vt_video_data` VALUES (10, 1, 371, 688);
INSERT INTO `vt_video_data` VALUES (11, 9, 43, 358);
INSERT INTO `vt_video_data` VALUES (12, 3, 622, 277);
INSERT INTO `vt_video_data` VALUES (13, 8, 695, 982);
INSERT INTO `vt_video_data` VALUES (14, 6, 674, 223);
INSERT INTO `vt_video_data` VALUES (15, 2, 840, 71);
INSERT INTO `vt_video_data` VALUES (16, 8, 774, 266);
INSERT INTO `vt_video_data` VALUES (17, 6, 677, 970);
INSERT INTO `vt_video_data` VALUES (18, 2, 721, 867);
INSERT INTO `vt_video_data` VALUES (19, 1, 891, 584);
INSERT INTO `vt_video_data` VALUES (20, 6, 32, 217);
INSERT INTO `vt_video_data` VALUES (21, 6, 779, 187);
INSERT INTO `vt_video_data` VALUES (22, 10, 412, 657);
INSERT INTO `vt_video_data` VALUES (23, 5, 884, 11);
INSERT INTO `vt_video_data` VALUES (24, 8, 538, 147);
INSERT INTO `vt_video_data` VALUES (25, 9, 375, 155);
INSERT INTO `vt_video_data` VALUES (26, 10, 777, 645);
INSERT INTO `vt_video_data` VALUES (27, 1, 233, 955);
INSERT INTO `vt_video_data` VALUES (28, 10, 440, 958);
INSERT INTO `vt_video_data` VALUES (29, 4, 827, 56);
INSERT INTO `vt_video_data` VALUES (30, 5, 783, 718);
INSERT INTO `vt_video_data` VALUES (31, 2, 50, 449);
INSERT INTO `vt_video_data` VALUES (32, 8, 788, 533);
INSERT INTO `vt_video_data` VALUES (33, 3, 487, 293);
INSERT INTO `vt_video_data` VALUES (34, 6, 0, 71);
INSERT INTO `vt_video_data` VALUES (35, 4, 475, 46);
INSERT INTO `vt_video_data` VALUES (36, 6, 361, 970);
INSERT INTO `vt_video_data` VALUES (37, 10, 148, 655);
INSERT INTO `vt_video_data` VALUES (38, 4, 610, 266);
INSERT INTO `vt_video_data` VALUES (39, 5, 588, 347);
INSERT INTO `vt_video_data` VALUES (40, 6, 875, 1000);
INSERT INTO `vt_video_data` VALUES (41, 10, 962, 752);
INSERT INTO `vt_video_data` VALUES (42, 7, 60, 610);
INSERT INTO `vt_video_data` VALUES (43, 9, 208, 543);
INSERT INTO `vt_video_data` VALUES (44, 7, 146, 118);
INSERT INTO `vt_video_data` VALUES (45, 7, 470, 364);
INSERT INTO `vt_video_data` VALUES (46, 3, 731, 414);
INSERT INTO `vt_video_data` VALUES (47, 2, 131, 938);
INSERT INTO `vt_video_data` VALUES (48, 8, 878, 141);
INSERT INTO `vt_video_data` VALUES (49, 10, 549, 639);
INSERT INTO `vt_video_data` VALUES (50, 9, 776, 827);

-- ----------------------------
-- Table structure for vt_vip
-- ----------------------------
DROP TABLE IF EXISTS `vt_vip`;
CREATE TABLE `vt_vip`  (
  `vip_id` int NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `expire_date` date NULL DEFAULT NULL COMMENT '到期日期',
  `is_vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否大会员',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`vip_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `vt_vip_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_vip
-- ----------------------------
INSERT INTO `vt_vip` VALUES (1, 1, '2022-12-31', 1, '2024-04-24 14:05:59');

SET FOREIGN_KEY_CHECKS = 1;
