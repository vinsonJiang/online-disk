/*
Navicat MySQL Data Transfer

Source Server         : localMysql
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : sky_drive

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-05-16 17:34:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL,
  `title` varchar(256) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------

-- ----------------------------
-- Table structure for t_chat_group
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_group`;
CREATE TABLE `t_chat_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `introduction` varchar(1024) DEFAULT NULL,
  `member_num` int(11) NOT NULL DEFAULT '0' COMMENT '成员数量',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_chat_group
-- ----------------------------
INSERT INTO `t_chat_group` VALUES ('1', '1', '群组1', 'aa', '1', '2019-05-16 15:47:21', '2019-05-16 15:47:24');

-- ----------------------------
-- Table structure for t_chat_group_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_group_relation`;
CREATE TABLE `t_chat_group_relation` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属用户id',
  `chat_group_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属用户id',
  `point` int(11) NOT NULL DEFAULT '0',
  `nickname` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`,`chat_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_chat_group_relation
-- ----------------------------
INSERT INTO `t_chat_group_relation` VALUES ('1', '1', '1', 'abc', '2019-05-16 15:47:40', '2019-05-16 15:47:42');
INSERT INTO `t_chat_group_relation` VALUES ('2', '1', '1', 'aa', '2019-05-16 15:56:05', '2019-05-16 15:56:07');

-- ----------------------------
-- Table structure for t_chat_message
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_message`;
CREATE TABLE `t_chat_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `from` int(11) NOT NULL,
  `to` int(11) NOT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `is_read` bit(1) NOT NULL DEFAULT b'0',
  `is_transport` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_chat_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_directory
-- ----------------------------
DROP TABLE IF EXISTS `t_directory`;
CREATE TABLE `t_directory` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `parent_id` int(11) DEFAULT '0' COMMENT '父目录id',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属用户id',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_directory
-- ----------------------------
INSERT INTO `t_directory` VALUES ('26', '', '0', '1', '2019-05-10 17:00:36', '2019-05-10 17:00:36');
INSERT INTO `t_directory` VALUES ('27', '新建文件夹', '26', '1', '2019-05-10 17:00:38', '2019-05-10 17:00:38');
INSERT INTO `t_directory` VALUES ('28', 'test', '27', '1', '2019-05-10 17:00:48', '2019-05-10 17:00:48');
INSERT INTO `t_directory` VALUES ('30', '', '0', '1', '2019-05-10 17:03:02', '2019-05-10 17:03:02');
INSERT INTO `t_directory` VALUES ('31', '新建文件夹(1)', '26', '1', '2019-05-15 14:09:16', '2019-05-15 14:09:16');
INSERT INTO `t_directory` VALUES ('32', '新建文件夹(1)(1)', '26', '1', '2019-05-15 14:34:30', '2019-05-15 14:34:30');

-- ----------------------------
-- Table structure for t_file_record
-- ----------------------------
DROP TABLE IF EXISTS `t_file_record`;
CREATE TABLE `t_file_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `md5` char(32) NOT NULL,
  `size` bigint(11) NOT NULL,
  `file_system_id` int(11) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `md5_index` (`md5`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file_record
-- ----------------------------
INSERT INTO `t_file_record` VALUES ('22', '7719f612bac2ad7be60aa8997579b2b3', '129734694', '1', '/upload/7719f612bac2ad7be60aa8997579b2b3', '2019-05-15 19:42:53', '2019-05-15 19:42:53');
INSERT INTO `t_file_record` VALUES ('23', '190c8f5b344cfe7ecf4aa6f80c9f517f', '217342912', '1', '/upload/190c8f5b344cfe7ecf4aa6f80c9f517f', '2019-05-15 19:49:29', '2019-05-15 19:49:29');

-- ----------------------------
-- Table structure for t_file_system
-- ----------------------------
DROP TABLE IF EXISTS `t_file_system`;
CREATE TABLE `t_file_system` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL COMMENT '文件系统类型（普通文件系统，hdfs等）',
  `host` varchar(50) NOT NULL COMMENT '主机ip',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file_system
-- ----------------------------

-- ----------------------------
-- Table structure for t_friend_group
-- ----------------------------
DROP TABLE IF EXISTS `t_friend_group`;
CREATE TABLE `t_friend_group` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_friend_group
-- ----------------------------

-- ----------------------------
-- Table structure for t_friend_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_friend_relation`;
CREATE TABLE `t_friend_relation` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属用户id',
  `friend_id` int(11) NOT NULL,
  `intimacy` int(11) NOT NULL DEFAULT '0' COMMENT '亲密度',
  `friend_group_id` int(11) DEFAULT '0',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_friend_relation
-- ----------------------------
INSERT INTO `t_friend_relation` VALUES ('1', '2', '0', '0', '2019-05-16 15:57:21', '2019-05-16 15:57:23');
INSERT INTO `t_friend_relation` VALUES ('2', '1', '0', '0', '2019-05-16 15:57:21', '2019-05-16 15:57:23');

-- ----------------------------
-- Table structure for t_share_file
-- ----------------------------
DROP TABLE IF EXISTS `t_share_file`;
CREATE TABLE `t_share_file` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属用户id',
  `is_directory` bit(1) NOT NULL DEFAULT b'0',
  `directory_id` int(11) NOT NULL DEFAULT '0',
  `upload_file_id` int(11) NOT NULL DEFAULT '0',
  `share_type` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_share_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_upload_file
-- ----------------------------
DROP TABLE IF EXISTS `t_upload_file`;
CREATE TABLE `t_upload_file` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `file_record_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属用户id',
  `directory_id` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '文件类型',
  `size` bigint(11) NOT NULL DEFAULT '0' COMMENT '文件大小',
  `name` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_upload_file
-- ----------------------------
INSERT INTO `t_upload_file` VALUES ('35', '22', '1', '26', '0', '129734694', 'Apache_OpenOffice_4.1.5_Win_x86_install_zh-CN.exe', '2019-05-15 19:49:31', '2019-05-15 19:49:31');
INSERT INTO `t_upload_file` VALUES ('36', '23', '1', '26', '0', '217342912', 'jdk-8u171-windows-x64.exe', '2019-05-15 19:49:29', '2019-05-15 19:49:29');
INSERT INTO `t_upload_file` VALUES ('37', '23', '1', '26', '0', '217342912', 'jdk-8u171-windows-x64.exe', '2019-05-15 19:49:36', '2019-05-15 19:49:36');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `password_md5` char(32) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `total_memory` bigint(11) NOT NULL DEFAULT '0' COMMENT '总共可用空间',
  `used_memory` bigint(11) NOT NULL DEFAULT '0' COMMENT '已使用空间',
  `state` int(11) DEFAULT '0',
  `online_status` int(11) NOT NULL DEFAULT '0' COMMENT '在线状态 0：离线，1：在线，2：离开',
  `login_count` int(11) NOT NULL DEFAULT '0',
  `last_login_ip` varchar(255) DEFAULT NULL,
  `last_login_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '123456', '123456', '1', '1', '0', '0', '0', '1', '0', '', '2019-05-16 11:14:08', '2019-05-16 11:14:10', '2019-05-16 11:14:13');
INSERT INTO `t_user` VALUES ('2', 'hello', 'hello', '123456', '123456', '1', '1', '0', '0', '0', '0', '0', '', '2019-05-16 11:14:08', '2019-05-16 11:14:10', '2019-05-16 11:14:13');
