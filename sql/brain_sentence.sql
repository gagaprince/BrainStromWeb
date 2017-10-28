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

 Date: 10/28/2017 00:45:24 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `brain_sentence`
-- ----------------------------
DROP TABLE IF EXISTS `brain_sentence`;
CREATE TABLE `brain_sentence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '例句id',
  `key_word` varchar(20) DEFAULT NULL COMMENT '关键词',
  `english` varchar(500) DEFAULT NULL COMMENT '例句',
  `source` varchar(100) DEFAULT NULL COMMENT '来源',
  `voice` varchar(200) DEFAULT NULL COMMENT '发音',
  `chinese` varchar(200) DEFAULT NULL COMMENT '中文翻译',
  PRIMARY KEY (`id`),
  KEY `keyword` (`key_word`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
