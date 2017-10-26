USE `smartdata`;

DROP TABLE IF EXISTS auth_admin;
DROP TABLE IF EXISTS auth_function;
DROP TABLE IF EXISTS auth_menu;
DROP TABLE IF EXISTS auth_permission;
DROP TABLE IF EXISTS auth_role;
DROP TABLE IF EXISTS auth_role_permission;
DROP TABLE IF EXISTS auth_user;
DROP TABLE IF EXISTS auth_user_role;

CREATE TABLE `auth_admin` (
   `id` DOUBLE unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `user_id` DOUBLE DEFAULT NULL COMMENT '用户id',
   `admin_name` VARCHAR(80)  DEFAULT NULL COMMENT '名称',
   `email` VARCHAR(200)  DEFAULT NULL COMMENT '邮箱',
   `tel` VARCHAR(60)  DEFAULT NULL COMMENT '电话',
   `depart_id` DOUBLE DEFAULT NULL COMMENT '部门id(废弃)',
   `job_no` VARCHAR(60)  DEFAULT NULL COMMENT '工号',
   `role_type` DOUBLE DEFAULT NULL COMMENT '类型:1,其他人员, 2,管理员 3,商务 4,运营人员',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_admin_pk` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='管理员表';

CREATE TABLE `auth_function` (
   `id` DOUBLE unsigned  NOT NULL AUTO_INCREMENT COMMENT '主键',
   `parent_id` DOUBLE DEFAULT NULL COMMENT '父功能点id',
   `function_name` VARCHAR(128)  DEFAULT NULL COMMENT '功能名称',
   `function_index` DOUBLE DEFAULT NULL COMMENT '排序索引',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_function_pk` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='功能点表';

CREATE TABLE `auth_menu` (
   `id` DOUBLE unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `permission` VARCHAR(400)  DEFAULT NULL COMMENT '权限id',
   `parent_id` DOUBLE DEFAULT NULL COMMENT '父菜单id',
   `menu_name` VARCHAR(400)  DEFAULT NULL COMMENT '菜单名称',
   `url` VARCHAR(800)  DEFAULT NULL COMMENT '菜单url',
   `menu_index` DOUBLE DEFAULT NULL COMMENT '排序索引',
   `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_menu_pk` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='菜单表';

CREATE TABLE `auth_permission` (
   `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `permission` VARCHAR(400)  DEFAULT NULL COMMENT '权限',
   `permission_name` VARCHAR(400)  DEFAULT NULL COMMENT '权限名称',
   `function_id` DOUBLE DEFAULT NULL COMMENT '功能id',
   `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `auth_role` (
   `id` DOUBLE unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `role_name` VARCHAR(128)  DEFAULT NULL COMMENT '角色名称',
   `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_role_pk` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `auth_role_permission` (
   `id` DOUBLE unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `role_id` DOUBLE DEFAULT NULL COMMENT '角色id',
   `permission` VARCHAR(400)  DEFAULT NULL COMMENT '权限id',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_role_permission_pk` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

CREATE TABLE `auth_user` (
   `id` DOUBLE unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
   `username` VARCHAR(200)  DEFAULT NULL COMMENT '用户名称',
   `passwd` VARCHAR(200)  DEFAULT NULL COMMENT '密码',
   `user_status` VARCHAR(8)  DEFAULT NULL COMMENT '用户状态',
   `user_type` VARCHAR(8)  DEFAULT NULL COMMENT '用户类型',
   `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
   `locale` VARCHAR(10)  DEFAULT NULL COMMENT '语言（废弃）',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_user_uk1` (`username`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

CREATE TABLE `auth_user_role` (
   `id` DOUBLE unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `user_id` DOUBLE DEFAULT NULL COMMENT '用户id',
   `role_id` DOUBLE DEFAULT NULL COMMENT '角色id',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_user_role_pk` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';