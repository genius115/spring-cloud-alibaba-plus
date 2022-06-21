/*
 Navicat MySQL Data Transfer

 Source Server         : 135-root
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : db0

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 21/06/2022 13:51:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_card
-- ----------------------------
DROP TABLE IF EXISTS `tb_card`;
CREATE TABLE `tb_card`  (
  `card_id` int(11) NOT NULL,
  `card_num` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`card_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_card
-- ----------------------------
INSERT INTO `tb_card` VALUES (123, '123456789', '张三地址');
INSERT INTO `tb_card` VALUES (234, '234567891', '李四地址');

-- ----------------------------
-- Table structure for tb_mobile_phone
-- ----------------------------
DROP TABLE IF EXISTS `tb_mobile_phone`;
CREATE TABLE `tb_mobile_phone`  (
  `mobile_phone_id` int(11) NOT NULL,
  `brand` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`mobile_phone_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_mobile_phone
-- ----------------------------
INSERT INTO `tb_mobile_phone` VALUES (1001, 'huawei P40', 5000.50, 1);
INSERT INTO `tb_mobile_phone` VALUES (1002, 'xiaomi Pro 11', 3582.56, 1);
INSERT INTO `tb_mobile_phone` VALUES (1003, 'oppo 10', 4253.63, 1);
INSERT INTO `tb_mobile_phone` VALUES (1004, 'vivo 8', 2000.36, 2);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `card_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '张三', 18, 123);
INSERT INTO `tb_user` VALUES (2, '李四', 20, 234);

SET FOREIGN_KEY_CHECKS = 1;
