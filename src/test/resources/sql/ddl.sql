/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - smart_data
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smart_data` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smart_data`;

/*Table structure for table `tbl_admin` */

DROP TABLE IF EXISTS `tbl_admin`;

CREATE TABLE `tbl_admin` (
  `ID` double NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` double DEFAULT NULL COMMENT '用户ID',
  `NAME` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `EMAIL` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `TEL` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `DEPART_ID` double DEFAULT NULL COMMENT '部门ID(废弃)',
  `JOB_NO` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  `TYPE` double DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tbl_admin_pk` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='【已废弃】';

/*Table structure for table `tbl_channel_platform` */

DROP TABLE IF EXISTS `tbl_channel_platform`;

CREATE TABLE `tbl_channel_platform` (
  `CHANNEL_ID` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '渠道ID',
  `PLATFORM_ID` double NOT NULL COMMENT '平台ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`CHANNEL_ID`,`PLATFORM_ID`),
  UNIQUE KEY `pk_tbl_channel_platform` (`CHANNEL_ID`,`PLATFORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='渠道平台推广关系表';

/*Table structure for table `tbl_function` */

DROP TABLE IF EXISTS `tbl_function`;

CREATE TABLE `tbl_function` (
  `ID` double NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PARENT_ID` double DEFAULT NULL COMMENT '父功能点ID',
  `NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '功能名称',
  `FUNCTION_INDEX` double DEFAULT NULL COMMENT '排序索引',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tbl_function_pk` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=418 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='功能点表';

/*Table structure for table `tbl_menu` */

DROP TABLE IF EXISTS `tbl_menu`;

CREATE TABLE `tbl_menu` (
  `ID` double NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PERMISSION` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '权限ID',
  `PARENT_ID` double DEFAULT NULL COMMENT '父菜单ID',
  `NAME` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `URL` varchar(800) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单URL',
  `MENU_INDEX` double DEFAULT NULL COMMENT '排序索引',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tbl_menu_pk` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=503 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

/*Table structure for table `tbl_permission` */

DROP TABLE IF EXISTS `tbl_permission`;

CREATE TABLE `tbl_permission` (
  `PERMISSION` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '权限',
  `NAME` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `FUNCTION_ID` double DEFAULT NULL COMMENT '功能ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

/*Table structure for table `tbl_role` */

DROP TABLE IF EXISTS `tbl_role`;

CREATE TABLE `tbl_role` (
  `ID` double NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tbl_role_pk` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

/*Table structure for table `tbl_role_permission` */

DROP TABLE IF EXISTS `tbl_role_permission`;

CREATE TABLE `tbl_role_permission` (
  `ID` double NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` double DEFAULT NULL COMMENT '角色ID',
  `PERMISSION` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tbl_role_permission_pk` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1953 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限关系表';

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `USER_ID` double NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `PASSWORD` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `USER_STATUS` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态',
  `USER_TYPE` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '用户类型',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LOCALE` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '语言（废弃）',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `tbl_user_pk` (`USER_ID`),
  UNIQUE KEY `tbl_user_uk1` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=302 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户基本信息表';

/*Table structure for table `tbl_user_channel` */

DROP TABLE IF EXISTS `tbl_user_channel`;

CREATE TABLE `tbl_user_channel` (
  `ID` double NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` double DEFAULT NULL COMMENT '用户ID',
  `CHANNEL_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `sys_c0012754` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户渠道关系表';

/*Table structure for table `tbl_user_role` */

DROP TABLE IF EXISTS `tbl_user_role`;

CREATE TABLE `tbl_user_role` (
  `ID` double NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` double DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` double DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tbl_user_role_pk` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=422 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
