/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : cola_sample

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 24/09/2021 11:08:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sample_credit
-- ----------------------------
DROP TABLE IF EXISTS `sample_credit`;
CREATE TABLE `sample_credit`  (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime(6) NOT NULL,
  `creator` bigint(20) NOT NULL,
  `deleted` int(11) NOT NULL,
  `modified_time` datetime(6) NOT NULL,
  `modifier` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `balance` double NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sample_credit
-- ----------------------------
INSERT INTO `sample_credit` VALUES (1400000000000000001, '2021-09-11 23:59:10.000000', 0, 0, '2021-09-17 11:48:51.394000', 10000000000000000, 0, 0, 10000000000000000);

-- ----------------------------
-- Table structure for sample_grade_credit_rule
-- ----------------------------
DROP TABLE IF EXISTS `sample_grade_credit_rule`;
CREATE TABLE `sample_grade_credit_rule`  (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime(6) NOT NULL,
  `creator` bigint(20) NOT NULL,
  `deleted` int(11) NOT NULL,
  `modified_time` datetime(6) NOT NULL,
  `modifier` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `credit_deduct_quota` double NOT NULL,
  `plain_text` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `upgrade_limit_credit` double NOT NULL,
  `grade_order` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sample_grade_credit_rule
-- ----------------------------
INSERT INTO `sample_grade_credit_rule` VALUES (1400000000000000001, '2021-09-01 22:45:48.000000', 0, 0, '2021-09-01 22:46:07.000000', 0, 0, 'vip_1', 5000, 'VIP 1', 0, 0);
INSERT INTO `sample_grade_credit_rule` VALUES (1400000000000000002, '2021-09-01 22:48:12.000000', 0, 0, '2021-09-01 22:48:20.000000', 0, 0, 'vip_2', 1000, 'VIP 2', 10000, 1);
INSERT INTO `sample_grade_credit_rule` VALUES (1400000000000000003, '2021-09-01 22:48:46.000000', 0, 0, '2021-09-01 22:48:52.000000', 0, 0, 'vip_3', 100, 'VIP 3', 100000, 2);

-- ----------------------------
-- Table structure for sample_role
-- ----------------------------
DROP TABLE IF EXISTS `sample_role`;
CREATE TABLE `sample_role`  (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime(6) NOT NULL,
  `creator` bigint(20) NOT NULL,
  `deleted` int(11) NOT NULL,
  `modified_time` datetime(6) NOT NULL,
  `modifier` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sample_role
-- ----------------------------
INSERT INTO `sample_role` VALUES (1400000000000000001, '2021-09-10 20:52:27.000000', 0, 0, '2021-09-10 20:52:41.000000', 0, 0, 'role_admin', '管理员');
INSERT INTO `sample_role` VALUES (1400000000000000002, '2021-09-10 20:53:08.000000', 0, 0, '2021-09-10 20:53:14.000000', 0, 0, 'role_user', '用户');

-- ----------------------------
-- Table structure for sample_user
-- ----------------------------
DROP TABLE IF EXISTS `sample_user`;
CREATE TABLE `sample_user`  (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime(6) NOT NULL,
  `creator` bigint(20) NOT NULL,
  `deleted` int(11) NOT NULL,
  `modified_time` datetime(6) NOT NULL,
  `modifier` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `grade_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`, `mobile`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sample_user
-- ----------------------------
INSERT INTO `sample_user` VALUES (10000000000000000, '2021-09-10 21:59:38.000000', 0, 0, '2021-09-17 11:48:51.526000', 10000000000000000, 0, 'vip_1', '10000000000', 'Admin', '$2a$10$cxhVDWkCENxZvilXtpGdDeSXHBptr1TrkSGoRL0V3lOGYAeHyLySi', 0);

-- ----------------------------
-- Table structure for sample_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sample_user_role`;
CREATE TABLE `sample_user_role`  (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime(6) NOT NULL,
  `creator` bigint(20) NOT NULL,
  `deleted` int(11) NOT NULL,
  `modified_time` datetime(6) NOT NULL,
  `modifier` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `role_codes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sample_user_role
-- ----------------------------
INSERT INTO `sample_user_role` VALUES (1400000000000000001, '2021-09-10 22:09:11.000000', 0, 0, '2021-09-10 22:09:19.000000', 0, 0, 'role_admin', 10000000000000000);

SET FOREIGN_KEY_CHECKS = 1;
