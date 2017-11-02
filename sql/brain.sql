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

 Date: 11/02/2017 17:49:55 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `brain_energy`
-- ----------------------------
DROP TABLE IF EXISTS `brain_energy`;
CREATE TABLE `brain_energy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `english` varchar(200) DEFAULT '' COMMENT '英文',
  `chinese` varchar(200) DEFAULT '' COMMENT '中文',
  `voice` varchar(100) DEFAULT '' COMMENT '读音文件',
  `source` varchar(100) DEFAULT NULL COMMENT '来源',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `brain_means`
-- ----------------------------
DROP TABLE IF EXISTS `brain_means`;
CREATE TABLE `brain_means` (
  `wordID` int(20) NOT NULL,
  `posID` int(2) NOT NULL,
  `means` varchar(1000) DEFAULT NULL,
  KEY `fk_means_1_idx` (`posID`),
  KEY `fk_means_1_idx1` (`wordID`),
  CONSTRAINT `fk_means_1` FOREIGN KEY (`wordID`) REFERENCES `brain_words` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_means_2` FOREIGN KEY (`posID`) REFERENCES `brain_pos` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `brain_pos`
-- ----------------------------
DROP TABLE IF EXISTS `brain_pos`;
CREATE TABLE `brain_pos` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `means` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=549 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

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
) ENGINE=InnoDB AUTO_INCREMENT=523263 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `brain_user`
-- ----------------------------
DROP TABLE IF EXISTS `brain_user`;
CREATE TABLE `brain_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `open_id` varchar(100) NOT NULL DEFAULT '-' COMMENT 'openid',
  `nick_name` varchar(50) NOT NULL DEFAULT '-' COMMENT '用户昵称',
  `gender` int(4) NOT NULL DEFAULT '0' COMMENT '性别',
  `city` varchar(40) NOT NULL DEFAULT '-' COMMENT '城市',
  `province` varchar(20) NOT NULL DEFAULT '-' COMMENT '省',
  `country` varchar(20) NOT NULL DEFAULT '-' COMMENT '国家',
  `avatar_url` varchar(200) NOT NULL DEFAULT '-' COMMENT '用户头像',
  `union_id` varchar(100) NOT NULL DEFAULT '-' COMMENT '开放平台唯一id',
  `phone` varchar(20) NOT NULL DEFAULT '-' COMMENT '电话',
  `create_time` datetime NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '创建日期',
  `update_time` datetime NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '更新日期',
  `session_key` varchar(100) DEFAULT NULL COMMENT '用户的登录session',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openId` (`open_id`) COMMENT 'openId索引',
  KEY `unionId` (`union_id`) COMMENT 'unionId索引',
  KEY `phone` (`phone`) COMMENT '电话号码索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `brain_word_record`
-- ----------------------------
DROP TABLE IF EXISTS `brain_word_record`;
CREATE TABLE `brain_word_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户学习天数记录id',
  `open_id` varchar(100) NOT NULL COMMENT '用户的openId',
  `day_index` int(11) NOT NULL DEFAULT '1' COMMENT '当前用户是第几天',
  `now_day` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `openId` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `brain_words`
-- ----------------------------
DROP TABLE IF EXISTS `brain_words`;
CREATE TABLE `brain_words` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `word` varchar(100) NOT NULL,
  `exchange` varchar(1000) DEFAULT NULL,
  `voice` varchar(1000) DEFAULT NULL,
  `times` int(20) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=135121 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `missing`
-- ----------------------------
DROP TABLE IF EXISTS `missing`;
CREATE TABLE `missing` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `word` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=214459 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
