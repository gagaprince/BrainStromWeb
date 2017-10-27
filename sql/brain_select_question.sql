/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : brain

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 10/26/2017 18:38:48 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `brain_select_question`
-- ----------------------------
DROP TABLE IF EXISTS `brain_select_question`;
CREATE TABLE `brain_select_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question` varchar(300) NOT NULL DEFAULT '没有问题' COMMENT '问题',
  `answers` varchar(500) NOT NULL DEFAULT '没有答案' COMMENT '答案选项',
  `answer` int(11) NOT NULL DEFAULT '-1' COMMENT '选项在answers中的位置 0，1，2，3',
  `cate` varchar(50) NOT NULL DEFAULT '百科' COMMENT '分类',
  `difficult` int(11) NOT NULL DEFAULT '0' COMMENT '难度系数',
  `create_time` datetime NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `cate` (`cate`) USING BTREE COMMENT '以分类索引'
) ENGINE=InnoDB AUTO_INCREMENT=275 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
