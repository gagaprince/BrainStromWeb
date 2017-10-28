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

 Date: 10/28/2017 00:44:56 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
