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

 Date: 10/28/2017 00:45:39 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
