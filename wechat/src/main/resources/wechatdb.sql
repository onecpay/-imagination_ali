/*
 Navicat Premium Data Transfer

 Source Server         : 本地测试数据库-----SSST
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : wechatdb

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 20/08/2019 15:26:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_customer_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_permission`;
CREATE TABLE `t_customer_permission`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户id 主键',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限主键id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_customer102415611321516`(`customer_id`) USING BTREE,
  INDEX `FK_permission102415611321516`(`permission_id`) USING BTREE,
  CONSTRAINT `FK_customer102415611321516` FOREIGN KEY (`customer_id`) REFERENCES `t_wechat_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_permission102415611321516` FOREIGN KEY (`permission_id`) REFERENCES `t_wechat_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wechat_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_customer`;
CREATE TABLE `t_wechat_customer`  (
  `id` bigint(10) NOT NULL COMMENT 'id',
  `version` int(10) DEFAULT NULL COMMENT '版本控制',
  `customer_no` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户编号',
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
  `status` enum('AVAVILABLE','FREEZE') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'AVAVILABLE' COMMENT '用户状态',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) DEFAULT NULL COMMENT '变更日期',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wechat_log
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_log`;
CREATE TABLE `t_wechat_log`  (
  `id` bigint(12) NOT NULL COMMENT '主键',
  `version` int(10) DEFAULT NULL,
  `method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求方法',
  `ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求IP',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务地址',
  `request_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求参数',
  `response_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '返回参数',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注信息'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wechat_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_permission`;
CREATE TABLE `t_wechat_permission`  (
  `id` bigint(20) NOT NULL,
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `name` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
