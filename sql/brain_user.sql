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

 Date: 10/26/2017 18:50:43 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `openId` (`open_id`) COMMENT 'openId索引',
  KEY `unionId` (`union_id`) COMMENT 'unionId索引',
  KEY `phone` (`phone`) COMMENT '电话号码索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
