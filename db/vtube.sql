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

 Date: 24/05/2024 17:50:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典编码',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典值',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  PRIMARY KEY (`dictionary_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

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
INSERT INTO `vt_dictionary` VALUES (8, 'video_type', '1', '转载');
INSERT INTO `vt_dictionary` VALUES (9, 'video_type', '2', '自制');

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
-- Table structure for vt_files
-- ----------------------------
DROP TABLE IF EXISTS `vt_files`;
CREATE TABLE `vt_files`  (
  `file_id` int NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID，标识文件属于哪个用户',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_size` float NULL DEFAULT NULL COMMENT '文件大小，单位为KB或MB',
  `file_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型，如txt、jpg、png等',
  `upload_date` timestamp NULL DEFAULT NULL COMMENT '上传日期时间',
  PRIMARY KEY (`file_id`) USING BTREE,
  INDEX `fk_user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '上传文件信息表，存储每个用户上传的文件信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_files
-- ----------------------------
INSERT INTO `vt_files` VALUES (51, 1, '680376763f8b43939d48ab3a55884bd8.png', '4711de7b34e4b4b00cb321ebdf68ce82.png', 0.139652, 'png', '2024-05-21 13:44:38');
INSERT INTO `vt_files` VALUES (52, 1, '680376763f8b43939d48ab3a55884bd8.png', '780513f0ac2cf245f428a04bbf48dbd7.png', 0.143419, 'png', '2024-05-21 13:51:48');
INSERT INTO `vt_files` VALUES (53, 1, 'testvidio.mp4', '39dc083001ced2b10f6a54d6f89e5f65.mp4', 35.4417, 'mp4', '2024-05-21 15:09:35');
INSERT INTO `vt_files` VALUES (54, 1, 'testvidio.mp4', 'adc60166884b2737f86689beb12d2426.mp4', 35.4417, 'mp4', '2024-05-21 15:16:07');
INSERT INTO `vt_files` VALUES (55, 1, 'production_id_5174040 (2160p).mp4', 'f3db19c87094642dfc8ec8c3e7369a75.mp4', 115.671, 'mp4', '2024-05-21 15:17:04');
INSERT INTO `vt_files` VALUES (56, 1, 'production_id_5174040 (2160p).mp4', '18914bafe1325a838809df28c609b6ec.mp4', 115.671, 'mp4', '2024-05-21 15:17:14');
INSERT INTO `vt_files` VALUES (57, 1, 'production_id_5174040 (2160p).mp4', '43a6f4d27132ab08c3fef1e38630f6e9.mp4', 115.671, 'mp4', '2024-05-21 15:21:27');
INSERT INTO `vt_files` VALUES (58, 1, 'production_id_5174040 (2160p).mp4', '8fcd46638a739312ac020b84f53aa86e.mp4', 115.671, 'mp4', '2024-05-21 15:24:58');
INSERT INTO `vt_files` VALUES (59, 1, 'production_id_5174040 (2160p).mp4', '8aea59a8c6ab4ca556623be665d66851.mp4', 115.671, 'mp4', '2024-05-21 15:25:46');
INSERT INTO `vt_files` VALUES (60, 1, 'production_id_5174040 (2160p).mp4', '10b00826170b4cee7bc7f5e7f9775aa0.mp4', 115.671, 'mp4', '2024-05-21 15:25:57');
INSERT INTO `vt_files` VALUES (61, 1, 'production_id_5174040 (2160p).mp4', 'a3e1533c03fc1b2f9c946a9b0ec4bdab.mp4', 115.671, 'mp4', '2024-05-21 15:34:14');
INSERT INTO `vt_files` VALUES (62, 1, 'production_id_5174040 (2160p).mp4', '671728d400a63d9e814f501f7118490e.mp4', 115.671, 'mp4', '2024-05-21 15:35:12');
INSERT INTO `vt_files` VALUES (63, 1, 'production_id_5174040 (2160p).mp4', 'c2c2b4b8afbb77e419090afff5436c4e.mp4', 115.671, 'mp4', '2024-05-21 15:39:07');
INSERT INTO `vt_files` VALUES (64, 1, 'production_id_5174040 (2160p).mp4', '0a550c5e985e144d508cbc6d3a1251fb.mp4', 115.671, 'mp4', '2024-05-21 15:39:27');
INSERT INTO `vt_files` VALUES (65, 1, 'production_id_5174040 (2160p).mp4', '3ab095ee5a8a8f52bc39c92c11aca818.mp4', 115.671, 'mp4', '2024-05-21 15:39:33');
INSERT INTO `vt_files` VALUES (66, 1, 'production_id_5174040 (2160p).mp4', '77aee70d0f9c0dfdeece9f79316e92ca.mp4', 115.671, 'mp4', '2024-05-21 15:43:50');
INSERT INTO `vt_files` VALUES (67, 1, 'production_id_5174040 (2160p).mp4', '170d68c77d358efc79171f77416111e2.mp4', 115.671, 'mp4', '2024-05-21 15:45:31');
INSERT INTO `vt_files` VALUES (68, 1, 'production_id_5174040 (2160p).mp4', 'a561ea58093503277b2a45970806598e.mp4', 115.671, 'mp4', '2024-05-21 15:46:50');
INSERT INTO `vt_files` VALUES (69, 1, 'testvidio.mp4', '7ac54418d2e58e181d5b52cb317a2a4e.mp4', 35.4417, 'mp4', '2024-05-21 15:47:23');
INSERT INTO `vt_files` VALUES (70, 1, 'bg1.mp4', '7296d11eb4e715e8b4e399d2cffef001.mp4', 3.11843, 'mp4', '2024-05-21 15:56:01');
INSERT INTO `vt_files` VALUES (71, 1, 'bg1.mp4', 'a2f4a189d319b48ded0eab4313a8864f.mp4', 3.11843, 'mp4', '2024-05-21 15:56:04');
INSERT INTO `vt_files` VALUES (72, 1, 'bg1.mp4', '37972831d86f46ad4d9e67fa109a1f1d.mp4', 3.11843, 'mp4', '2024-05-21 15:56:06');
INSERT INTO `vt_files` VALUES (73, 1, 'bg1.mp4', 'a431e2973c492efddeda39b79eec5333.mp4', 3.11843, 'mp4', '2024-05-21 15:56:08');
INSERT INTO `vt_files` VALUES (74, 1, 'production_id_5174040 (2160p).mp4', '6bbd6db97b402ec1439ee8b2a266c0c4.mp4', 115.671, 'mp4', '2024-05-21 15:57:49');
INSERT INTO `vt_files` VALUES (75, 1, 'bg2.mp4', '25cbe6ef88ec032f4052ab8e13fd8654.mp4', 5.49759, 'mp4', '2024-05-21 15:57:50');
INSERT INTO `vt_files` VALUES (76, 1, 'bg2.mp4', '7414374e7f0cf08713f0d1e00a6f0e48.mp4', 5.49759, 'mp4', '2024-05-21 15:59:11');
INSERT INTO `vt_files` VALUES (77, 1, 'bg2.mp4', 'd81f5891d1a0d034627b8d2ae6e76aff.mp4', 5.49759, 'mp4', '2024-05-21 15:59:14');
INSERT INTO `vt_files` VALUES (78, 1, 'bg2.mp4', '1fd6e3bd96af30299ee80d6833d6c416.mp4', 5.49759, 'mp4', '2024-05-21 15:59:16');
INSERT INTO `vt_files` VALUES (79, 1, 'bg2.mp4', 'f76b5bb374a14f43f0f1ba7dfb34975e.mp4', 5.49759, 'mp4', '2024-05-21 15:59:18');
INSERT INTO `vt_files` VALUES (80, 1, 'bg2.mp4', '028c44925a916ae4e18e21c658149ced.mp4', 5.49759, 'mp4', '2024-05-21 15:59:20');
INSERT INTO `vt_files` VALUES (81, 1, 'bg2.mp4', '5a6d677165a7d49b05eb6609371fd33c.mp4', 5.49759, 'mp4', '2024-05-21 15:59:22');
INSERT INTO `vt_files` VALUES (82, 1, 'bg2.mp4', 'e28c8c15c0b39842d7980311257fe944.mp4', 5.49759, 'mp4', '2024-05-21 15:59:24');
INSERT INTO `vt_files` VALUES (83, 1, 'bg2.mp4', '4eccb8f351b47ff2b79e0b7e6d2fdb3f.mp4', 5.49759, 'mp4', '2024-05-21 15:59:26');
INSERT INTO `vt_files` VALUES (84, 1, 'bg2.mp4', 'a83ccdc124fcf076b10946980ef93d58.mp4', 5.49759, 'mp4', '2024-05-21 16:01:20');
INSERT INTO `vt_files` VALUES (85, 1, 'bg2.mp4', 'aafb47a3f93647e08009fe737575bd5f.mp4', 5.49759, 'mp4', '2024-05-21 16:02:33');
INSERT INTO `vt_files` VALUES (86, 1, 'bg2.mp4', 'a80a4ccb5c133ebad3ff7c7d06b5e066.mp4', 5.49759, 'mp4', '2024-05-21 16:08:09');
INSERT INTO `vt_files` VALUES (87, 1, 'bg2.mp4', 'b05a46c650cee29dbff0ce86b3b18a3e.mp4', 5.49759, 'mp4', '2024-05-21 16:09:02');
INSERT INTO `vt_files` VALUES (88, 1, 'bg2.mp4', '5d0be21fea5dfb09e03e37d595d3119c.mp4', 5.49759, 'mp4', '2024-05-21 16:10:16');
INSERT INTO `vt_files` VALUES (89, 1, 'bg2.mp4', '3385fe0c18ba8948288f93b63a7d9ee7.mp4', 5.49759, 'mp4', '2024-05-21 16:35:57');
INSERT INTO `vt_files` VALUES (90, 1, '680376763f8b43939d48ab3a55884bd8.png', '5a9787a4186801a3c2a110519cd7e7ee.png', 0.139652, 'png', '2024-05-21 16:36:04');
INSERT INTO `vt_files` VALUES (91, 1, 'bg2.mp4', 'f021c38ca99018af51debcdf1284c76d.mp4', 5.49759, 'mp4', '2024-05-21 16:41:34');
INSERT INTO `vt_files` VALUES (92, 1, 'bg2.mp4', '250f6d7cebddfa72d465d86f75835a08.mp4', 5.49759, 'mp4', '2024-05-21 16:46:32');
INSERT INTO `vt_files` VALUES (93, 1, 'bg2.mp4', 'f7ec9df0be5a047e9788196e252874b7.mp4', 5.49759, 'mp4', '2024-05-21 17:21:27');
INSERT INTO `vt_files` VALUES (94, 1, 'bg2.mp4', '38427390a193d607290dde728061bbd3.mp4', 5.49759, 'mp4', '2024-05-21 17:37:01');
INSERT INTO `vt_files` VALUES (95, 1, 'card2.jpg', 'e220bb0b2c21631a47970ae4797aeb90.jpg', 0.102098, 'jpg', '2024-05-22 08:47:31');
INSERT INTO `vt_files` VALUES (96, 1, '680376763f8b43939d48ab3a55884bd8.png', '90f7b9bc3a7083302122add527d69b78.png', 0.139652, 'png', '2024-05-23 08:46:57');
INSERT INTO `vt_files` VALUES (97, 1, 'u=78519444,4047732764&amp.jfif', '3aa5b3492b9d21ae9c385d0088970707.jfif', 0.14083, 'jfif', '2024-05-23 08:56:53');
INSERT INTO `vt_files` VALUES (98, 1, 'u=78519444,4047732764&amp.jfif', 'd3c9495f66f9afef606dcacf35bb2ea7.jfif', 0.14083, 'jfif', '2024-05-23 08:57:18');
INSERT INTO `vt_files` VALUES (99, 1, '006MDjU7gy1g0dyvfih99j30eg0kf0wv.jpg', '0b68812773eaa90d2f435c422a2ba06a.jpg', 0.152343, 'jpg', '2024-05-23 08:57:28');
INSERT INTO `vt_files` VALUES (100, 1, 'u=78519444,4047732764&amp.jfif', 'd43b592ac87bdcea88bbf73d811ae7c1.jfif', 0.14083, 'jfif', '2024-05-23 09:01:13');
INSERT INTO `vt_files` VALUES (101, 1, 'u=78519444,4047732764&amp.jfif', 'b3e3498161778f1f640b4dda6b9ceb4d.jfif', 0.14083, 'jfif', '2024-05-23 09:12:22');
INSERT INTO `vt_files` VALUES (102, 1, 'u=78519444,4047732764&amp.jfif', '6c4e16395780ea04644e64a93d5121eb.jfif', 0.14083, 'jfif', '2024-05-23 09:18:43');
INSERT INTO `vt_files` VALUES (103, 1, 'u=78519444,4047732764&amp.jpg', '37c5986027141a926f712a239f7cb400.jpg', 0.14083, 'jpg', '2024-05-23 09:19:43');
INSERT INTO `vt_files` VALUES (104, 1, '006MDjU7gy1g0dyvfih99j30eg0kf0wv.jpg', 'ff9c5cb665ac9fd8a2c4d4aa14a90aa8.jpg', 0.152343, 'jpg', '2024-05-23 09:45:46');
INSERT INTO `vt_files` VALUES (105, 1, '006MDjU7gy1g0dyvfyfbzj30fk0m0767.jpg', '939396a02cdff9643f8a4ee9f60252d9.jpg', 0.102288, 'jpg', '2024-05-23 14:07:30');
INSERT INTO `vt_files` VALUES (106, 1, '006MDjU7gy1g0dyvfyfbzj30fk0m0767.jpg', '7eb36018a8d3b9aaaf5ab175fdc44b5f.jpg', 0.102288, 'jpg', '2024-05-23 14:12:35');
INSERT INTO `vt_files` VALUES (107, 1, '680376763f8b43939d48ab3a55884bd8.png', 'fe716b099bd4855114dff8e2b8a2d1c2.png', 0.18649, 'png', '2024-05-23 14:23:57');
INSERT INTO `vt_files` VALUES (108, 1, 'testvidio.mp4', '1ea32c198b39eac7b5322c3558fc4a34.mp4', 35.4417, 'mp4', '2024-05-23 17:04:48');
INSERT INTO `vt_files` VALUES (109, 1, 'testvidio.mp4', 'd7942eae0ccd2543f77f86bd870178f6.mp4', 35.4417, 'mp4', '2024-05-23 17:10:46');
INSERT INTO `vt_files` VALUES (110, 1, 'testvidio.mp4', '999eeebcc18899f35f9f025f13e7f4de.mp4', 35.4417, 'mp4', '2024-05-23 17:11:00');
INSERT INTO `vt_files` VALUES (111, 1, 'testvidio.mp4', 'd4e57d450946489bb8c0373eca07460f.mp4', 35.4417, 'mp4', '2024-05-23 17:11:30');
INSERT INTO `vt_files` VALUES (112, 1, 'testvidio.mp4', '519fb54d93ea1580a3d75959d9987b55.mp4', 35.4417, 'mp4', '2024-05-23 17:11:42');
INSERT INTO `vt_files` VALUES (113, 1, 'testvidio.mp4', 'c8428d46f90d8cf1198da354ad6cd62b.mp4', 35.4417, 'mp4', '2024-05-23 17:12:17');
INSERT INTO `vt_files` VALUES (114, 1, 'testvidio.mp4', '191689f402400ad6490c129e091d9bd5.mp4', 35.4417, 'mp4', '2024-05-23 17:12:47');
INSERT INTO `vt_files` VALUES (115, 1, 'production_id_5174040 (2160p).mp4', '69660167fdb1075bb45fa3813c8dafb0.mp4', 115.671, 'mp4', '2024-05-23 17:38:03');
INSERT INTO `vt_files` VALUES (116, 1, 'production_id_5174040 (2160p).mp4', '8d565a93cc35238568eeca5d6f037fcb.mp4', 115.671, 'mp4', '2024-05-23 17:41:32');

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
  INDEX `followed_id`(`followed_id` ASC) USING BTREE,
  INDEX `vt_follow_ibfk_1`(`follower_id` ASC) USING BTREE,
  CONSTRAINT `vt_follow_ibfk_1` FOREIGN KEY (`follower_id`) REFERENCES `vt_user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_follow
-- ----------------------------
INSERT INTO `vt_follow` VALUES (1, 1, 2, '2022-01-01 15:30:00');
INSERT INTO `vt_follow` VALUES (6, 1, 2, '2022-01-01 15:30:00');

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
-- Table structure for vt_menu
-- ----------------------------
DROP TABLE IF EXISTS `vt_menu`;
CREATE TABLE `vt_menu`  (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `parent_id` int NULL DEFAULT NULL COMMENT '父菜单ID，顶级菜单为NULL',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单路由路径',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由name',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端Vue组件路径',
  `order_num` int NULL DEFAULT 0 COMMENT '菜单排序顺序',
  `visible` tinyint(1) NULL DEFAULT 1 COMMENT '菜单是否可见, 1为可见，0为不可见',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识，用于权限控制',
  `is_keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '当前路由是否缓存 0：否 1：是',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由重定向地址',
  `is_full` tinyint(1) NULL DEFAULT NULL COMMENT '是否全屏',
  PRIMARY KEY (`menu_id`) USING BTREE COMMENT '主键'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_menu
-- ----------------------------
INSERT INTO `vt_menu` VALUES (1, NULL, '仪表盘', 'ant-design:dashboard-outlined', '/dashboard', 'dashboard', 'dashboard/index', 0, 1, NULL, 1, NULL, 0);
INSERT INTO `vt_menu` VALUES (2, NULL, '视频管理', 'material-symbols:settings-video-camera-outline-rounded', '/video', 'video', NULL, 0, 1, NULL, NULL, '/video/list', 0);
INSERT INTO `vt_menu` VALUES (3, 2, '视频列表', 'lucide:film', '/video/list', 'list', 'video/index', 0, 1, NULL, 1, NULL, 0);
INSERT INTO `vt_menu` VALUES (4, NULL, '系统管理', 'tdesign:system-3', '/system', 'system', NULL, 0, 1, NULL, NULL, '/system/setting', 0);
INSERT INTO `vt_menu` VALUES (5, 4, '设置', 'ri:settings-line', '/system/setting', 'setting', 'setting/index', 0, 1, NULL, 1, NULL, 0);
INSERT INTO `vt_menu` VALUES (6, 3, '我是测试', 'ri:settings-line', '/video/list/test', 'test', 'video/imtest', 0, 1, NULL, 1, NULL, 0);

-- ----------------------------
-- Table structure for vt_role
-- ----------------------------
DROP TABLE IF EXISTS `vt_role`;
CREATE TABLE `vt_role`  (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_role
-- ----------------------------
INSERT INTO `vt_role` VALUES (1, 'Super-Admin');
INSERT INTO `vt_role` VALUES (2, 'Admin');
INSERT INTO `vt_role` VALUES (3, 'Moderator');
INSERT INTO `vt_role` VALUES (4, 'User');

-- ----------------------------
-- Table structure for vt_role_menu_permissions
-- ----------------------------
DROP TABLE IF EXISTS `vt_role_menu_permissions`;
CREATE TABLE `vt_role_menu_permissions`  (
  `role_id` int NOT NULL COMMENT '角色ID',
  `menu_id` int NOT NULL COMMENT '菜单ID',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识码',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE COMMENT '复合主键',
  INDEX `fk_role_menu_menu_id`(`menu_id` ASC) USING BTREE,
  CONSTRAINT `fk_role_menu_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `vt_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_menu_role_id` FOREIGN KEY (`role_id`) REFERENCES `vt_role` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色与菜单权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_role_menu_permissions
-- ----------------------------
INSERT INTO `vt_role_menu_permissions` VALUES (1, 1, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (1, 2, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (1, 3, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (1, 4, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (1, 5, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (1, 6, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (2, 1, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (2, 5, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (4, 1, NULL);
INSERT INTO `vt_role_menu_permissions` VALUES (4, 5, NULL);

-- ----------------------------
-- Table structure for vt_user
-- ----------------------------
DROP TABLE IF EXISTS `vt_user`;
CREATE TABLE `vt_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'M(男) or F(女)',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_user
-- ----------------------------
INSERT INTO `vt_user` VALUES (1, 'admin', '栗山未来', '眼镜娘', '123456', '1234567890', 'user1@example.com', '123', '2022-01-01 12:00:00', 'M');
INSERT INTO `vt_user` VALUES (2, '郭震南', 'Tang Ka Keung', 'Tang Ka Keung', 'I7N5nnAlBx', '199-4555-3066', 'zguo@mail.com', 'DrEDjGx21w', '2004-11-17 18:27:22', 'M');
INSERT INTO `vt_user` VALUES (3, '罗嘉伦', 'Deborah Romero', 'Deborah Romero', 'gqC0PGwgek', '(20) 4866 5660', 'jialulu3@outlook.com', '7qjA45JNKi', '2021-07-27 10:23:47', 'M');
INSERT INTO `vt_user` VALUES (4, '魏杰宏', 'Ng Sau Man', 'Ng Sau Man', 'W98vO2N15B', '(161) 923 4351', 'wjieh9@gmail.com', 'ZKqATISbOS', '2007-12-31 20:33:41', 'M');
INSERT INTO `vt_user` VALUES (5, '大塚樹', 'Yam Kwok Kuen', 'Yam Kwok Kuen', 'XhTzo8nfIv', '718-679-9348', 'otsukait90@yahoo.com', 'OErol2D6MJ', '2006-10-26 11:05:33', 'M');
INSERT INTO `vt_user` VALUES (6, '夏杰宏', 'Frederick Ramos', 'Frederick Ramos', 'fzPrIdydPQ', '74-527-7415', 'jiehong71@outlook.com', 'D1a5VHpgIj', '2020-12-13 08:09:15', 'M');
INSERT INTO `vt_user` VALUES (7, '中野陽太', 'Tang Xiaoming', 'Tang Xiaoming', 'NSjuyp0fWG', '90-3237-7838', 'yotnakano@hotmail.com', 'qNNvEYWMRA', '2010-07-01 01:13:26', 'M');
INSERT INTO `vt_user` VALUES (8, '段子韬', 'Choi Tak Wah', 'Choi Tak Wah', 'CgTdSn8hvh', '(161) 369 9532', 'duanzi@outlook.com', 'owVFaXgqsn', '2018-09-25 03:06:04', 'M');
INSERT INTO `vt_user` VALUES (9, '中村美羽', 'Han Ka Ming', 'Han Ka Ming', 'V9WJ59Zdww', '(116) 863 7372', 'nakamiu@gmail.com', 'AsatoOLbGz', '2002-01-03 17:26:12', 'F');
INSERT INTO `vt_user` VALUES (10, '刘云熙', 'Lam Suk Yee', 'Lam Suk Yee', 'O6EL5VYSV7', '5807 600489', 'liuy@mail.com', 'ZV8lc2oD33', '2012-07-08 20:22:43', 'M');
INSERT INTO `vt_user` VALUES (11, '木村和真', 'Danielle Cooper', 'Danielle Cooper', 'JLysDbhVEd', '161-9762-5471', 'kazumak517@gmail.com', '9qsm1VKbN2', '2000-08-16 20:45:37', 'M');
INSERT INTO `vt_user` VALUES (12, '馮俊宇', 'Ng Wing Sze', 'Ng Wing Sze', '3OablmvwCA', '7366 771002', 'cyfung@gmail.com', 'VcnSlqvypT', '2021-09-10 02:36:37', 'M');
INSERT INTO `vt_user` VALUES (13, '吳力申', 'Mak Sze Kwan', 'Mak Sze Kwan', 'or9b35vRyg', '769-492-9721', 'likng4@outlook.com', 'AxdQSvuviK', '2009-04-07 13:58:36', 'M');
INSERT INTO `vt_user` VALUES (14, '張榮發', 'Ricky Green', 'Ricky Green', 'K6V7ccQpvX', '163-8280-3659', 'wingfat19@gmail.com', 'LXBT4H8TCt', '2005-12-03 10:19:21', 'M');
INSERT INTO `vt_user` VALUES (15, '阎震南', 'Ichikawa Aoshi', 'Ichikawa Aoshi', 'iWsqyStbay', '21-3372-8737', 'zhenya93@outlook.com', 'kWb676Qn6x', '2010-02-04 16:49:33', 'M');
INSERT INTO `vt_user` VALUES (16, '盧惠敏', 'Sugawara Sakura', 'Sugawara Sakura', 'Gd1iGPep0h', '3-8497-0285', 'waiman4@yahoo.com', 'EORHZmu3t3', '2005-06-24 14:51:42', 'F');
INSERT INTO `vt_user` VALUES (17, '駱仲賢', 'Ding Lu', 'Ding Lu', 'RBNQDI5uzI', '66-046-5647', 'lok6@yahoo.com', 'WEvk1q2jWC', '2007-01-17 09:35:52', 'M');
INSERT INTO `vt_user` VALUES (18, '廖云熙', 'Li Zhiyuan', 'Li Zhiyuan', 'z9eYIxRsrF', '312-535-7467', 'lyunx2019@gmail.com', 'uF4XZPzMbG', '2016-03-31 06:43:00', 'M');
INSERT INTO `vt_user` VALUES (19, '韓仲賢', 'Tong Ka Fai', 'Tong Ka Fai', 'OocTBB6VTU', '838-839-9422', 'hanchungyin@yahoo.com', 'mERBASmTeO', '2003-02-07 06:55:49', 'M');
INSERT INTO `vt_user` VALUES (20, '馮玲玲', 'Tang Xiaoming', 'Tang Xiaoming', 'P6i60pWoPY', '212-574-6672', 'fung62@gmail.com', 'lLsK1dDe0s', '2021-04-27 22:43:19', 'F');
INSERT INTO `vt_user` VALUES (21, '贺秀英', 'Kinoshita Ayato', 'Kinoshita Ayato', 'EwwtQYCXUo', '213-285-6531', 'hexiuying@mail.com', 'kJ4NEOB3F8', '2008-11-30 07:18:26', 'F');
INSERT INTO `vt_user` VALUES (22, '蔡志遠', 'Mak Sum Wing', 'Mak Sum Wing', 'hDfngni9kX', '330-815-9316', 'chiyuenchoi@icloud.com', 'um51kM240V', '2008-11-20 08:10:48', 'M');
INSERT INTO `vt_user` VALUES (23, '山本蓮', 'Laura Vasquez', 'Laura Vasquez', 'pqOdNI9ujy', '66-816-6410', 'renya@yahoo.com', 'gCh1gnUNbd', '2016-12-18 11:02:52', 'F');
INSERT INTO `vt_user` VALUES (24, '陶天樂', 'Liao Lu', 'Liao Lu', 'ksiyHVqn0K', '212-740-5106', 'taotinlok7@gmail.com', 'TW7hqiiFY0', '2020-07-18 18:58:07', 'M');
INSERT INTO `vt_user` VALUES (25, '程震南', 'Manuel Owens', 'Manuel Owens', 'UVzZSGbLqQ', '138-8908-7449', 'zhennan53@mail.com', 'wLCksPJ9CI', '2009-05-09 18:32:59', 'M');
INSERT INTO `vt_user` VALUES (26, '孔致远', 'Shirley West', 'Shirley West', '0X9bijtQDG', '330-257-1378', 'zkong5@hotmail.com', '52JK92qFFB', '2002-01-19 07:13:57', 'M');
INSERT INTO `vt_user` VALUES (27, '葉梓晴', 'Kaneko Airi', 'Kaneko Airi', 'XgyR2Tcjge', '838-323-5712', 'ytszching@outlook.com', 'FRzcNQV5Lv', '2007-05-21 04:14:07', 'F');
INSERT INTO `vt_user` VALUES (28, '井上愛梨', 'Matthew Payne', 'Matthew Payne', 'W4vkPse2Zw', '312-048-1234', 'ai00@hotmail.com', 'IHEqZ0gsIn', '2009-11-13 01:10:51', 'F');
INSERT INTO `vt_user` VALUES (29, '赵詩涵', 'Randy Moore', 'Randy Moore', 'kFlQnAhu6m', '5964 446581', 'zhaos@mail.com', '5H518qXMsR', '2007-05-13 23:38:48', 'F');
INSERT INTO `vt_user` VALUES (30, '梅家輝', 'So Ting Fung', 'So Ting Fung', 'hdIe8KNeN4', '(121) 125 6435', 'kfmui@gmail.com', 'QU3gtVZTfC', '2014-01-15 01:21:37', 'M');
INSERT INTO `vt_user` VALUES (31, '餘天樂', 'Iwasaki Ayato', 'Iwasaki Ayato', 'qqtqHvyNrT', '718-462-0106', 'tly8@hotmail.com', '1NORKEuAJW', '2003-09-23 02:39:46', 'M');
INSERT INTO `vt_user` VALUES (32, '丁子韬', 'Fu Fu Shing', 'Fu Fu Shing', 'o3c5zXvb1n', '80-3876-7559', 'dingzitao@hotmail.com', '4BOOILyKkT', '2010-04-22 22:56:29', 'M');
INSERT INTO `vt_user` VALUES (33, '中島百花', 'Shi Ziyi', 'Shi Ziyi', 'yNKHI252G0', '153-0073-5748', 'nmomok@hotmail.com', 'zXhHuaalmH', '2018-02-27 01:29:10', 'F');
INSERT INTO `vt_user` VALUES (34, '薛霆鋒', 'Cheung Yun Fat', 'Cheung Yun Fat', 'NGLNvdmljL', '312-045-5749', 'tingfsit728@hotmail.com', 'DQOVsmLIsK', '2014-12-24 04:00:52', 'M');
INSERT INTO `vt_user` VALUES (35, '傅杰宏', 'Meng Yuning', 'Meng Yuning', 'M5pLlObAzF', '20-9285-0382', 'fujieho@gmail.com', 'llB1dymQCr', '2012-06-23 20:05:06', 'M');
INSERT INTO `vt_user` VALUES (36, '马岚', 'Chiang Ling Ling', 'Chiang Ling Ling', 'BPsUAaTFTy', '838-930-4882', 'lanma@gmail.com', 'JfMGYJzG4C', '2017-10-23 05:26:47', 'F');
INSERT INTO `vt_user` VALUES (37, '岡田大地', 'Zou Zhennan', 'Zou Zhennan', 'gBfkmzAUdR', '312-513-5643', 'okada831@gmail.com', 'M0qLeBKxzt', '2015-01-14 09:41:41', 'M');
INSERT INTO `vt_user` VALUES (38, '孙睿', 'Nakajima Ryota', 'Nakajima Ryota', 'KSVHqM5dni', '755-1836-0139', 'ruisun1992@mail.com', 'ndUi6XAQnP', '2018-10-18 19:42:41', 'M');
INSERT INTO `vt_user` VALUES (39, '朱宇宁', 'Shi Rui', 'Shi Rui', 'UuMN8RiziZ', '154-4254-3354', 'zhuyu206@outlook.com', 'rUkRXLz57k', '2015-01-07 13:51:27', 'M');
INSERT INTO `vt_user` VALUES (40, '陈璐', 'Tam Ling Ling', 'Tam Ling Ling', 'jdT9XoKuKD', '193-0394-5521', 'clu@outlook.com', '5mCcpvbyWW', '2002-10-03 17:19:07', 'F');
INSERT INTO `vt_user` VALUES (41, '高田明菜', 'Watanabe Aoi', 'Watanabe Aoi', 'U54esDaLOz', '(1865) 22 6225', 'takada111@gmail.com', '21QM3H0mo3', '2009-11-24 10:05:09', 'F');
INSERT INTO `vt_user` VALUES (42, '原健太', 'Sheh Siu Wai', 'Sheh Siu Wai', 'ztUkz6mMBy', '90-1025-3743', 'harakenta@icloud.com', 'jaX2rSs6Yi', '2012-12-02 06:58:01', 'M');
INSERT INTO `vt_user` VALUES (43, '羅家輝', 'Wei Xiuying', 'Wei Xiuying', 'c7yKIcibtM', '614-865-2594', 'kafai1007@icloud.com', 'iIX77x6GE3', '2021-06-10 15:36:32', 'M');
INSERT INTO `vt_user` VALUES (44, '佐藤翼', 'Tsang Wai Yee', 'Tsang Wai Yee', '9bPPaL8HZm', '5129 753011', 'satotsubasa@icloud.com', '7d2Pp3eTvs', '2021-12-25 05:57:19', 'M');
INSERT INTO `vt_user` VALUES (45, '井上彩乃', 'Yuen Chi Ming', 'Yuen Chi Ming', 'yKOwVJPWVd', '755-335-3915', 'inoueayano@outlook.com', 'TOGUhc6zTK', '2007-03-04 04:44:10', 'F');
INSERT INTO `vt_user` VALUES (46, '今井葉月', 'Yuen Sum Wing', 'Yuen Sum Wing', 'KOGP15WF2W', '838-541-8426', 'hazukiimai@mail.com', 'foffRiEWmo', '2020-12-28 04:18:25', 'F');
INSERT INTO `vt_user` VALUES (47, '向頴璇', 'Aoki Hikaru', 'Aoki Hikaru', 'Aex32jyGdz', '52-918-2078', 'heungws@outlook.com', 'ymVC72xVKx', '2006-11-15 12:00:27', 'F');
INSERT INTO `vt_user` VALUES (48, '江安琪', 'Ono Sara', 'Ono Sara', 'ynPcXW7sAJ', '80-0418-0830', 'jiang1975@outlook.com', 'mjVkE5RcjN', '2002-02-25 20:01:36', 'F');
INSERT INTO `vt_user` VALUES (49, '毛詠詩', 'Kinoshita Ryota', 'Kinoshita Ryota', 'ZSy6T76Oy0', '769-192-7285', 'mowingsze@outlook.com', 'vqECzLasLI', '2017-08-15 04:38:09', 'F');
INSERT INTO `vt_user` VALUES (50, '呂志遠', 'Au Hok Yau', 'Au Hok Yau', 'T8O0bAjYFc', '90-2528-3089', 'cylu7@icloud.com', 'dUBwrP9tt6', '2004-12-30 07:39:56', 'M');
INSERT INTO `vt_user` VALUES (51, '斎藤光莉', 'Yu Yunxi', 'Yu Yunxi', 'ZKdU9xTgJ1', '212-316-8110', 'saito70@mail.com', 'msjsbJK1wY', '2011-08-27 22:34:38', 'F');

-- ----------------------------
-- Table structure for vt_user_role
-- ----------------------------
DROP TABLE IF EXISTS `vt_user_role`;
CREATE TABLE `vt_user_role`  (
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `vt_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `vt_user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `vt_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `vt_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_user_role
-- ----------------------------
INSERT INTO `vt_user_role` VALUES (1, 1);
INSERT INTO `vt_user_role` VALUES (2, 4);

-- ----------------------------
-- Table structure for vt_video
-- ----------------------------
DROP TABLE IF EXISTS `vt_video`;
CREATE TABLE `vt_video`  (
  `video_id` int NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '视频描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面地址',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频标签',
  `type` enum('1','2') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频类型',
  `uploader` enum('OFFICIAL','USER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'USER' COMMENT '上传者类型',
  PRIMARY KEY (`video_id`) USING BTREE,
  INDEX `idx_uploader`(`uploader` ASC) USING BTREE,
  INDEX `idx_video_id`(`video_id` ASC) USING BTREE,
  INDEX `idx_type`(`type` ASC) USING BTREE,
  INDEX `idx_title`(`title` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_video
-- ----------------------------
INSERT INTO `vt_video` VALUES (1, '妖精的秘密', 'You cannot save people, you can just love them. After comparing data, the window shows the                    ', '2010-02-12 08:11:55', 'ff9c5cb665ac9fd8a2c4d4aa14a90aa8.jpg', '冒险,动漫,轻松', NULL, 'OFFICIAL');
INSERT INTO `vt_video` VALUES (2, '橘子星球', 'It provides strong authentication and secure encrypted communications between two hosts, known                ', '2005-10-14 00:29:10', 'fe716b099bd4855114dff8e2b8a2d1c2.png', '冒险,动漫,治愈,热血', NULL, 'OFFICIAL');
INSERT INTO `vt_video` VALUES (3, '天使之泪', 'With its well-designed Graphical User Interface(GUI), Navicat lets you quickly and easily create,             ', '2020-12-14 06:12:37', '06e3c391980fa0797cfe357fb69693cb.png', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (4, '魔法少女萌音', 'Secure SHell (SSH) is a program to log in into another computer over a network, execute commands              ', '2015-07-15 00:30:20', 'klYXIfjsfd', NULL, NULL, 'OFFICIAL');
INSERT INTO `vt_video` VALUES (5, '银魂之歌', 'The past has no power over the present moment. It can also manage cloud databases such as Amazon              ', '2002-08-10 05:50:29', 'fDvIXSBldp', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (6, '银河传说', 'All the Navicat Cloud objects are located under different projects. You can share the project                 ', '2009-09-11 09:55:03', 'jF6cNaUYno', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (7, '彩虹岛屿', 'Remember that failure is an event, not a person. I destroy my enemies when I make them my friends.            ', '2008-12-03 21:30:38', 'KeNDYAN0IW', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (8, '幻想花园', 'Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more.               ', '2007-03-15 18:45:22', 'bwcbz08btH', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (9, '奇幻之旅', 'If opportunity doesn’t knock, build a door. What you get by achieving your goals is not as                  ', '2005-11-25 12:13:27', '8u7GStkyQt', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (10, '课怕喵', 'Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases                  ', '2003-09-21 22:32:13', 'Wb6uZpQ6PM', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (12, '幻想花园', 'I destroy my enemies when I make them my friends. Navicat is a multi-connections Database Administration      ', '2015-10-01 05:30:48', 'W83qhjcbGq', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (13, '天香斗牛', 'Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed                  ', '2009-03-19 23:26:11', 'gWodbMRrn8', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (14, '星际之心', 'In a Telnet session, all communications, including username and password, are transmitted in                  ', '2004-09-02 03:22:45', 'NzYtpXks3s', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (15, '魔法学院', 'Navicat provides a wide range advanced features, such as compelling code editing capabilities,                ', '2020-11-15 18:14:38', 'c5e41696b550fa5cd4d4b2ca2d6fe68c.png', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (16, '魔法少女萌音', 'In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons                ', '2018-07-10 15:38:50', 'zDEFRCRaQX', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (17, '机械之心', 'Navicat Data Modeler is a powerful and cost-effective database design tool which helps you                    ', '2008-10-06 17:45:59', 'Cx1hn1cygJ', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (18, '天使之梦', 'Navicat Cloud provides a cloud service for synchronizing connections, queries, model files                    ', '2002-03-20 20:08:44', 'ZaZEjfmSqE', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (19, '琉璃的境界', 'Champions keep playing until they get it right. It collects process metrics such as CPU load,                 ', '2018-07-03 23:36:46', 'tRN0dTKVbM', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (20, '奇幻森林之歌', 'In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons                ', '2008-05-15 01:12:57', 'cU67iCZN65', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (21, '琉璃的境界', 'Optimism is the one quality more associated with success and happiness than any other.                        ', '2000-05-06 17:55:58', 'Oz1r3CVc6P', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (22, '天使之泪', 'I may not have gone where I intended to go, but I think I have ended up where I needed to be.                 ', '2016-08-13 07:02:59', 'JX5YXoslXl', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (23, '梦幻乐园', 'If opportunity doesn’t knock, build a door. What you get by achieving your goals is not as                  ', '2013-04-04 19:33:38', 'hKd9AnOXv7', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (24, '星河航行', 'To successfully establish a new connection to local/remote server - no matter via SSL, SSH                    ', '2000-11-09 22:09:53', 'OW8eA9Q79q', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (25, '航海王之歌', 'To successfully establish a new connection to local/remote server - no matter via SSL or SSH,                 ', '2021-03-29 15:30:44', '1f5336210c7d876e28bd9746b4ff15fb.jpg', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (26, '幻想乐园', 'You can select any connections, objects or projects, and then select the corresponding buttons                ', '2014-08-16 07:40:08', 'ILn2tXcrXn', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (27, '星空漫步', 'Navicat 15 has added support for the system-wide dark mode. To open a query using an external                 ', '2015-03-30 01:39:51', 'kzsh27j3Q5', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (28, '黑暗之光', 'A comfort zone is a beautiful place, but nothing ever grows there. Remember that failure is                   ', '2005-01-11 18:22:54', 'V5q5NxDzDo', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (29, '星之旅程', 'Navicat Cloud could not connect and access your databases. By which it means, it could only                   ', '2016-02-07 00:25:46', 'ZE7LcVajzP', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (30, '银魂之歌', 'There is no way to happiness. Happiness is the way. A comfort zone is a beautiful place, but                  ', '2012-06-27 21:24:32', 'zMFXHJm7mw', '热血,动漫', '1', 'USER');
INSERT INTO `vt_video` VALUES (31, '彩虹之桥', 'I may not have gone where I intended to go, but I think I have ended up where I needed to be.                 ', '2016-06-23 19:15:24', 'yue1dtsUzf', '热血,动漫,治愈', '2', 'USER');
INSERT INTO `vt_video` VALUES (33, '机械之心', 'Navicat Monitor can be installed on any local computer or virtual machine and does not require                ', '2009-08-07 03:42:15', '1VZSo5GuNh', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (34, '幻想花园', 'If your Internet Service Provider (ISP) does not provide direct access to its server, Secure                  ', '2001-07-03 04:11:01', 'mtJ2GGAidA', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (35, '幻想乐园', 'The first step is as good as half over. I will greet this day with love in my heart.                          ', '2004-05-25 08:33:57', '5e5yjd5vNP', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (36, '奇幻森林之歌', 'The past has no power over the present moment. Anyone who has ever made anything of importance                ', '2007-04-10 06:16:50', 'TojYzK8SuM', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (37, '星空漫步', 'If you wait, all that happens is you get older. Genius is an infinite capacity for taking pains.              ', '2015-12-29 07:46:34', 'FHTwfaILCq', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (38, '天香斗牛', 'If it scares you, it might be a good thing to try. Such sessions are also susceptible to session              ', '2016-10-24 08:18:31', 'uHyhVPZWhZ', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (39, '境界的彼方', 'Navicat authorizes you to make connection to remote servers running on different platforms                    ', '2008-04-06 05:59:38', 'zTDijpQJ2C', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (40, '彩虹岛屿', 'A man’s best friends are his ten fingers. Champions keep playing until they get it right.                   ', '2013-03-30 22:34:40', 'QkKqLRou10', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (42, '星际之心', 'Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases                  ', '2024-04-18 12:12:56', 'e220bb0b2c21631a47970ae4797aeb90.jpg', '热血,动漫,治愈', '2', 'USER');
INSERT INTO `vt_video` VALUES (43, '原子能少女', 'I destroy my enemies when I make them my friends. What you get by achieving your goals is not                 ', '2010-09-27 17:24:27', '02An06j6yi', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (44, '魔法少女萌音', 'I destroy my enemies when I make them my friends. The Navigation pane employs tree structure                  ', '2006-10-17 18:15:56', 'YptuDTdBJI', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (45, '星之旅程', 'Navicat authorizes you to make connection to remote servers running on different platforms                    ', '2003-11-04 07:49:31', 'xRbRBYKyaS', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (46, '神秘宝藏', 'A query is used to extract data from the database in a readable format according to the user\'s request.       ', '2016-08-16 09:25:12', 'L6ffNMzLGm', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (47, '课怕喵', 'Such sessions are also susceptible to session hijacking, where a malicious user takes over                    ', '2020-05-31 19:24:02', '31db1d2fe932524b72957e13a838ae3a.png', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (48, '龙珠传奇', 'To open a query using an external editor, control-click it and select Open with External Editor.              ', '2004-12-23 16:55:59', 'rMVqUX2QSN', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (49, '天使之梦', 'The Information Pane shows the detailed object information, project activities, the DDL of                    ', '2021-06-28 19:10:12', 'b50d2fd2a4b1dc4648a8be37507e1bc2.png', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (50, '星际迷航之梦', 'In other words, Navicat provides the ability for data in different databases and/or schemas                   ', '2020-01-02 09:46:29', 'b03c4b5813989e5015bd82d8d128a45b.png', NULL, NULL, 'USER');
INSERT INTO `vt_video` VALUES (73, '境界的彼方', '电视动画片《境界的彼方》改编自鸟居奈古梦著作的同名轻小说作品，由《日常》原班制作人马打造。2013年4月3日，在KA ESUMA 文库官网正式宣布动画化企划进行中的消息。动画官网于2013年8月1日上线，并且每天更新一张带有数字的图片进行倒计时，直到8月5日倒计时完毕，动画官网方正式公开，此时《境界的彼方》正式确认将以TV动画的形式与观众见面。', '2024-05-23 00:00:00', '37c5986027141a926f712a239f7cb400.jpg', '眼镜娘', '2', 'OFFICIAL');
INSERT INTO `vt_video` VALUES (77, '劇場版 境界の彼方 I’LL BE HERE 未来篇', '电视动画片《境界的彼方》改编自鸟居奈古梦著作的同名轻小说作品，由《日常》原班制作人马打造。2013年4月3日，在KA ESUMA 文库官网正式宣布动画化企划进行中的消息。动画官网于2013年8月1日上线，并且每天更新一张带有数字的图片进行倒计时，直到8月5日倒计时完毕，动画官网方正式公开，此时《境界的彼方》正式确认将以TV动画的形式与观众见面。', '2024-05-23 00:00:00', '7eb36018a8d3b9aaaf5ab175fdc44b5f.jpg', '境界的彼方', '2', 'OFFICIAL');

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
INSERT INTO `vt_video_category` VALUES (73, 1);
INSERT INTO `vt_video_category` VALUES (77, 1);
INSERT INTO `vt_video_category` VALUES (5, 2);
INSERT INTO `vt_video_category` VALUES (18, 2);
INSERT INTO `vt_video_category` VALUES (21, 2);
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
INSERT INTO `vt_video_category` VALUES (13, 5);
INSERT INTO `vt_video_category` VALUES (26, 5);
INSERT INTO `vt_video_category` VALUES (37, 5);
INSERT INTO `vt_video_category` VALUES (38, 5);
INSERT INTO `vt_video_category` VALUES (39, 5);
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
INSERT INTO `vt_video_data` VALUES (1, 22, 858, 499);
INSERT INTO `vt_video_data` VALUES (2, 3, 293, 926);
INSERT INTO `vt_video_data` VALUES (3, 1, 906, 942);
INSERT INTO `vt_video_data` VALUES (4, 7, 646, 759);
INSERT INTO `vt_video_data` VALUES (5, 3, 212, 885);
INSERT INTO `vt_video_data` VALUES (6, 9, 210, 30);
INSERT INTO `vt_video_data` VALUES (7, 4, 966, 559);
INSERT INTO `vt_video_data` VALUES (8, 29, 994, 911);
INSERT INTO `vt_video_data` VALUES (9, 26, 198, 462);
INSERT INTO `vt_video_data` VALUES (10, 13, 315, 88);
INSERT INTO `vt_video_data` VALUES (42, 10, 50, 152);
INSERT INTO `vt_video_data` VALUES (73, 1, 121, 12);
INSERT INTO `vt_video_data` VALUES (77, 1, 123, 12);

-- ----------------------------
-- Table structure for vt_video_episode
-- ----------------------------
DROP TABLE IF EXISTS `vt_video_episode`;
CREATE TABLE `vt_video_episode`  (
  `episode_id` int NOT NULL AUTO_INCREMENT COMMENT '选集ID',
  `video_id` int NOT NULL COMMENT '视频ID',
  `episode_number` int NOT NULL COMMENT '选集编号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选集标题',
  `video_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频地址',
  PRIMARY KEY (`episode_id`) USING BTREE,
  INDEX `vt_video_episode_ibfk_1`(`video_id` ASC) USING BTREE,
  CONSTRAINT `vt_video_episode_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `vt_video` (`video_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频选集表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_video_episode
-- ----------------------------
INSERT INTO `vt_video_episode` VALUES (2, 42, 2, '我是第一集', '250f6d7cebddfa72d465d86f75835a08.mp4');
INSERT INTO `vt_video_episode` VALUES (3, 42, 1, '我是第二季', 'f7ec9df0be5a047e9788196e252874b7.mp4');
INSERT INTO `vt_video_episode` VALUES (10, 73, 1, 'testa ', '191689f402400ad6490c129e091d9bd5.mp4');
INSERT INTO `vt_video_episode` VALUES (15, 77, 1, 'testVideo', 'd4e57d450946489bb8c0373eca07460f.mp4');
INSERT INTO `vt_video_episode` VALUES (16, 77, 2, 'test22', '8d565a93cc35238568eeca5d6f037fcb.mp4');

-- ----------------------------
-- Table structure for vt_video_update
-- ----------------------------
DROP TABLE IF EXISTS `vt_video_update`;
CREATE TABLE `vt_video_update`  (
  `update_id` int NOT NULL AUTO_INCREMENT COMMENT '更新ID',
  `week_day` enum('1','2','3','4','5','6','7') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '星期几',
  `video_id` int NOT NULL COMMENT '视频ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`update_id`) USING BTREE,
  INDEX `fk_video_id`(`video_id` ASC) USING BTREE,
  CONSTRAINT `fk_video_id` FOREIGN KEY (`video_id`) REFERENCES `vt_video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '番剧更新表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vt_video_update
-- ----------------------------
INSERT INTO `vt_video_update` VALUES (1, '1', 1, NULL);
INSERT INTO `vt_video_update` VALUES (2, '2', 2, NULL);
INSERT INTO `vt_video_update` VALUES (3, '3', 3, NULL);
INSERT INTO `vt_video_update` VALUES (4, '4', 4, NULL);
INSERT INTO `vt_video_update` VALUES (5, '5', 5, NULL);
INSERT INTO `vt_video_update` VALUES (6, '6', 6, NULL);
INSERT INTO `vt_video_update` VALUES (7, '2', 8, NULL);
INSERT INTO `vt_video_update` VALUES (9, '1', 73, '2024-05-23 09:38:53');
INSERT INTO `vt_video_update` VALUES (10, '2', 77, NULL);
INSERT INTO `vt_video_update` VALUES (11, '2', 10, NULL);
INSERT INTO `vt_video_update` VALUES (12, '2', 12, NULL);
INSERT INTO `vt_video_update` VALUES (13, '2', 16, NULL);
INSERT INTO `vt_video_update` VALUES (14, '2', 13, NULL);
INSERT INTO `vt_video_update` VALUES (15, '2', 25, NULL);
INSERT INTO `vt_video_update` VALUES (16, '2', 26, NULL);

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
