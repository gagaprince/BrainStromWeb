/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.24 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `blog`;

/*Table structure for table `daily` */

DROP TABLE IF EXISTS `daily`;

CREATE TABLE `daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '日志的标题',
  `content` longtext NOT NULL,
  `cate` varchar(20) NOT NULL COMMENT '分类',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `pic` varchar(100) DEFAULT NULL,
  `bigcate` varchar(20) DEFAULT NULL,
  `tag` varchar(200) DEFAULT NULL COMMENT '关键字',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `fe` */

DROP TABLE IF EXISTS `fe`;

CREATE TABLE `fe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '特效名',
  `desc` varchar(100) DEFAULT NULL COMMENT '特效描述',
  `cover` varchar(100) DEFAULT NULL COMMENT '特效缩略图',
  `url` varchar(100) DEFAULT NULL COMMENT '特效演示地址',
  `download_url` varchar(100) DEFAULT NULL COMMENT '特效打包下载地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `fontlink` */

DROP TABLE IF EXISTS `fontlink`;

CREATE TABLE `fontlink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL COMMENT '描述文字',
  `link` varchar(50) DEFAULT NULL COMMENT '链接',
  `cover` varchar(100) DEFAULT NULL COMMENT '封面图片',
  `bigcate` varchar(20) DEFAULT NULL COMMENT '大分类 栏目更新 图文并茂 热门点击 摄影作品',
  `smallcate` varchar(20) DEFAULT NULL COMMENT '小标签',
  `create_time` datetime DEFAULT NULL COMMENT '加入时间',
  `rank` bigint(20) DEFAULT NULL COMMENT '权重 排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `friendlink` */

DROP TABLE IF EXISTS `friendlink`;

CREATE TABLE `friendlink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(100) NOT NULL COMMENT '外链链接',
  `desc` varchar(30) NOT NULL COMMENT '外链描述',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `music` */

DROP TABLE IF EXISTS `music`;

CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '歌曲名',
  `singer` varchar(20) DEFAULT NULL COMMENT '歌手名',
  `album` varchar(20) DEFAULT NULL COMMENT '专辑',
  `bg_img` varchar(100) DEFAULT NULL COMMENT '背景图',
  `src` varchar(100) NOT NULL COMMENT 'music的资源链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `photo` */

DROP TABLE IF EXISTS `photo`;

CREATE TABLE `photo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `pic_url` varchar(200) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `photo_folder_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5969 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `photofolder` */

DROP TABLE IF EXISTS `photofolder`;

CREATE TABLE `photofolder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '相册名',
  `cover` varchar(200) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `rank` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=805 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `shares` */

DROP TABLE IF EXISTS `shares`;

CREATE TABLE `shares` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `code_all` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2818 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `shares_history` */

DROP TABLE IF EXISTS `shares_history`;

CREATE TABLE `shares_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_all` varchar(10) DEFAULT NULL,
  `open_price` float DEFAULT NULL,
  `high_price` float DEFAULT NULL,
  `low_price` float DEFAULT NULL,
  `close_price` float DEFAULT NULL,
  `volume` float DEFAULT NULL,
  `share_date` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODEDATE` (`code_all`,`share_date`)
) ENGINE=InnoDB AUTO_INCREMENT=4272714 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `suggest` */

DROP TABLE IF EXISTS `suggest`;

CREATE TABLE `suggest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '提示语标题',
  `content` varchar(500) DEFAULT NULL COMMENT '提示语内容',
  `bg` varchar(100) NOT NULL COMMENT '提示语背景图',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '视频名称',
  `desc` varchar(200) DEFAULT NULL COMMENT '视频描述',
  `src` varchar(200) DEFAULT NULL COMMENT '视频地址',
  `cate` varchar(50) DEFAULT NULL COMMENT '视频分类',
  `create_time` datetime DEFAULT NULL COMMENT '视频加入时间',
  `cover` varchar(50) DEFAULT NULL COMMENT '封面图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
