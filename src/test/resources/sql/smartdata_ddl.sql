DROP TABLE IF EXISTS tbl_admin;
DROP TABLE IF EXISTS tbl_function;
DROP TABLE IF EXISTS tbl_menu;
DROP TABLE IF EXISTS tbl_permission;
DROP TABLE IF EXISTS tbl_role;
DROP TABLE IF EXISTS tbl_role_permission;
DROP TABLE IF EXISTS tbl_user;
DROP TABLE IF EXISTS tbl_user_role;

DROP TABLE IF EXISTS interval_game_launch_report;
DROP TABLE IF EXISTS interval_interface_report;
DROP TABLE IF EXISTS interval_source_report;
DROP TABLE IF EXISTS daily_composite_report;
DROP TABLE IF EXISTS daily_register_report;
DROP TABLE IF EXISTS daily_recharge_report;
DROP TABLE IF EXISTS daily_loign_game_report;
DROP TABLE IF EXISTS daily_Keep_record_report;
DROP TABLE IF EXISTS month_composite_report;
DROP TABLE IF EXISTS month_register_report;
DROP TABLE IF EXISTS month_recharge_report;
DROP TABLE IF EXISTS month_loign_game_report;

CREATE TABLE `tbl_admin` (
   `ID` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
   `USER_ID` DOUBLE DEFAULT NULL COMMENT '用户ID',
   `NAME` VARCHAR(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
   `EMAIL` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
   `TEL` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
   `DEPART_ID` DOUBLE DEFAULT NULL COMMENT '部门ID(废弃)',
   `JOB_NO` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
   `TYPE` DOUBLE DEFAULT NULL COMMENT '类型',
   PRIMARY KEY (`ID`),
   UNIQUE KEY `tbl_admin_pk` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_function` (
   `ID` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
   `PARENT_ID` DOUBLE DEFAULT NULL COMMENT '父功能点ID',
   `NAME` VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '功能名称',
   `FUNCTION_INDEX` DOUBLE DEFAULT NULL COMMENT '排序索引',
   PRIMARY KEY (`ID`),
   UNIQUE KEY `tbl_function_pk` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='功能点表';

CREATE TABLE `tbl_menu` (
   `ID` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
   `PERMISSION` VARCHAR(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限ID',
   `PARENT_ID` DOUBLE DEFAULT NULL COMMENT '父菜单ID',
   `NAME` VARCHAR(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
   `URL` VARCHAR(800) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单URL',
   `MENU_INDEX` DOUBLE DEFAULT NULL COMMENT '排序索引',
   `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
   PRIMARY KEY (`ID`),
   UNIQUE KEY `tbl_menu_pk` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='菜单表';

CREATE TABLE `tbl_permission` (
   `PERMISSION` VARCHAR(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限',
   `NAME` VARCHAR(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
   `FUNCTION_ID` DOUBLE DEFAULT NULL COMMENT '功能ID',
   `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `tbl_role` (
   `ID` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
   `NAME` VARCHAR(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
   `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
   PRIMARY KEY (`ID`),
   UNIQUE KEY `tbl_role_pk` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `tbl_role_permission` (
   `ID` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
   `ROLE_ID` DOUBLE DEFAULT NULL COMMENT '角色ID',
   `PERMISSION` VARCHAR(400) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限ID',
   PRIMARY KEY (`ID`),
   UNIQUE KEY `tbl_role_permission_pk` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=1953 DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

CREATE TABLE `tbl_user` (
   `USER_ID` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '用户ID',
   `USERNAME` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
   `PASSWORD` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
   `USER_STATUS` VARCHAR(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态',
   `USER_TYPE` VARCHAR(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户类型',
   `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
   `LOCALE` VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '语言（废弃）',
   PRIMARY KEY (`USER_ID`),
   UNIQUE KEY `tbl_user_pk` (`USER_ID`),
   UNIQUE KEY `tbl_user_uk1` (`USERNAME`)
) ENGINE=INNODB AUTO_INCREMENT=302 DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

CREATE TABLE `tbl_user_role` (
   `ID` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
   `USER_ID` DOUBLE DEFAULT NULL COMMENT '用户ID',
   `ROLE_ID` DOUBLE DEFAULT NULL COMMENT '角色ID',
   PRIMARY KEY (`ID`),
   UNIQUE KEY `tbl_user_role_pk` (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=422 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

/*==============================================================*/
/* Table: game_launch_statistic                                 */
/*==============================================================*/
CREATE TABLE interval_game_launch_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '平台实时统计，游戏启动统计',
   statistics_time      VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计时间(半小时)yyyy-MM-dd HH:30:00',
   game_code            VARCHAR(64) NOT NULL DEFAULT '' COMMENT '游戏编码',
   game_name            VARCHAR(50) NOT NULL DEFAULT '' COMMENT '游戏名称',
   launch_count         INT(10) NOT NULL DEFAULT 0 COMMENT '老用户充值次数',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: interface_statistic                                   */
/*==============================================================*/
CREATE TABLE interval_interface_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '平台实时统计，接口统计',
   statistics_time      VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(半小时)yyyy-MM-dd HH:30:00',
   interface_name       VARCHAR(50) NOT NULL DEFAULT '' COMMENT '接口名称',
   operate_type         TINYINT(2) NOT NULL DEFAULT 0 COMMENT '操作类型类型：1、请求，2、执行完成',
   operate_count        INT(10) NOT NULL DEFAULT 0 COMMENT '操作次数',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: source_time_interval_statistics                       */
/*==============================================================*/
CREATE TABLE interval_source_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '平台实时统计，各端实时统计',
   statistics_time      VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(半小时)yyyy-MM-dd HH:30:00',
   source_type          TINYINT(2) NOT NULL DEFAULT 0 COMMENT '源端类型：1、PC  2.H5 3.IOS 4.android',
   register_population  INT(10) NOT NULL DEFAULT 0 COMMENT '注册人数',
   login_population     INT(10) NOT NULL DEFAULT 0 COMMENT '登录人数',
   recharge_population  INT(10) NOT NULL DEFAULT 0 COMMENT '充值人数',
   recharge_count       INT(10) NOT NULL DEFAULT 0 COMMENT '充值次数',
   recharge_amount      DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '充值金额',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: daily_composite_report                                */
/*==============================================================*/
CREATE TABLE daily_composite_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '日报表，综合报表',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   register_population       INT(10) NOT NULL DEFAULT 0 COMMENT '注册人数',
   register_exp_count INT(10) NOT NULL DEFAULT 0 COMMENT '注册体验量',
   real_exp_count INT(10) NOT NULL DEFAULT 0 COMMENT '真体验',
   virtual_exp_count INT(10) NOT NULL DEFAULT 0 COMMENT '假体验',
   new_user_recharge_population INT(10) NOT NULL DEFAULT 0 COMMENT '新用户充值人数',
   new_user_recharge_count INT(10) NOT NULL DEFAULT 0 COMMENT '新用户充值次数',
   new_user_recharge_amount DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '新用户充值金额',
   new_user_login_count INT(10) NOT NULL DEFAULT 0 COMMENT '新用户登录人数',
   new_user_play_count  INT(10) NOT NULL DEFAULT 0 COMMENT '新用户玩游戏数',
   old_user_recharge_population INT(10) NOT NULL DEFAULT 0 COMMENT '老用户充值人数',
   old_user_recharge_count INT(10) NOT NULL DEFAULT 0 COMMENT '老用户充值次数',
   old_user_recharge_amount DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '老用户充值金额',
   old_user_login_count INT(10) NOT NULL DEFAULT 0 COMMENT '老用户登录人数',
   old_user_play_count  INT(10) NOT NULL DEFAULT 0 COMMENT '老用户玩游戏数',
   next_day_stay_count   INT(10) NOT NULL DEFAULT 0 COMMENT '次日留存',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: daily_register_source_report                          */
/*==============================================================*/
create table daily_register_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '日报表，注册来源统计报表',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   pc_page_view         INT(10) NOT NULL DEFAULT 0 COMMENT 'pc页面浏览数pv',
   pc_user_view         INT(10) NOT NULL DEFAULT 0 COMMENT 'pc用户访问数uv',
   hfive_population     INT(10) NOT NULL DEFAULT 0 COMMENT 'h5用户数',
   hfive_page_view      INT(10) NOT NULL DEFAULT 0 COMMENT 'h5 pv',
   hfive_user_view      INT(10) NOT NULL DEFAULT 0 COMMENT 'h5 uv',
   ios_population       INT(10) NOT NULL DEFAULT 0 COMMENT 'ios访问量',
   ios_install_count    INT(10) NOT NULL DEFAULT 0 COMMENT 'ios安装量',
   android_population    INT(10) NOT NULL DEFAULT 0 COMMENT 'android访问量',
   android_install_count INT(10) NOT NULL DEFAULT 0 COMMENT 'android安装量',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: daily_loign_source_report                          */
/*==============================================================*/
create table daily_recharge_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '日报表，充值来源统计报表',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   source_type          TINYINT(2) NOT NULL DEFAULT 0 COMMENT '源端类型：1、PC  2.H5 3.IOS 4.android',
   recharge_population   INT(10) NOT NULL DEFAULT 0 COMMENT '充值人数',
   recharge_amount     DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '充值金额',
   recharge_count     INT(10) NOT NULL DEFAULT 0 COMMENT '充值次数',
   new_recharge_population     INT(10) NOT NULL DEFAULT 0 COMMENT '新用户充值人数',
   old_recharge_population      INT(10) NOT NULL DEFAULT 0 COMMENT '老用户充值人数',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: 登录来源统计                         */
/*==============================================================*/
create table daily_loign_game_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '日报表，充值来源统计报表',
   game_code            VARCHAR(64) NOT NULL DEFAULT '' COMMENT '游戏编码',
   game_name            VARCHAR(50) NOT NULL DEFAULT '' COMMENT '游戏名称',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   source_type          TINYINT(2) NOT NULL DEFAULT 0 COMMENT '源端类型：1、PC  2.H5 3.IOS 4.android',
   login_population     INT(10) NOT NULL DEFAULT 0 COMMENT '登录人数',
   play_population      INT(10) NOT NULL DEFAULT 0 COMMENT '玩游戏人数',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: 留存分析                         */
/*==============================================================*/
create table daily_Keep_record_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '日报表，充值来源统计报表',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   source_type          TINYINT(2) NOT NULL DEFAULT 0 COMMENT '源端类型：1、PC  2.H5 3.IOS 4.android',
   register_count       INT(10) NOT NULL DEFAULT 0 COMMENT '注册人数',
   one       INT(10) NOT NULL DEFAULT 0 COMMENT '1天后留存',
   two       INT(10) NOT NULL DEFAULT 0 COMMENT '2天后留存',
   three       INT(10) NOT NULL DEFAULT 0 COMMENT '3天后留存',
   four       INT(10) NOT NULL DEFAULT 0 COMMENT '4天后留存',
   five       INT(10) NOT NULL DEFAULT 0 COMMENT '5天后留存',
   six       INT(10) NOT NULL DEFAULT 0 COMMENT '6天后留存',
   seven       INT(10) NOT NULL DEFAULT 0 COMMENT '7天后留存',
   fourteen       INT(10) NOT NULL DEFAULT 0 COMMENT '14天后留存',
   thirty       INT(10) NOT NULL DEFAULT 0 COMMENT '30天后留存',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: month_composite_report                                */
/*==============================================================*/
CREATE TABLE month_composite_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '月报表，综合报表',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
   register_population       INT(10) NOT NULL DEFAULT 0 COMMENT '注册人数',
   register_exp_count INT(10) NOT NULL DEFAULT 0 COMMENT '注册体验量',
   real_exp_count INT(10) NOT NULL DEFAULT 0 COMMENT '真体验',
   virtual_exp_count INT(10) NOT NULL DEFAULT 0 COMMENT '假体验',
   new_user_recharge_population INT(10) NOT NULL DEFAULT 0 COMMENT '新用户充值人数',
   new_user_recharge_count INT(10) NOT NULL DEFAULT 0 COMMENT '新用户充值次数',
   new_user_recharge_amount DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '新用户充值金额',
   new_user_login_count INT(10) NOT NULL DEFAULT 0 COMMENT '新用户登录人数',
   new_user_play_count  INT(10) NOT NULL DEFAULT 0 COMMENT '新用户玩游戏数',
   old_user_recharge_population INT(10) NOT NULL DEFAULT 0 COMMENT '老用户充值人数',
   old_user_recharge_count INT(10) NOT NULL DEFAULT 0 COMMENT '老用户充值次数',
   old_user_recharge_amount DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '老用户充值金额',
   old_user_login_count INT(10) NOT NULL DEFAULT 0 COMMENT '老用户登录人数',
   old_user_play_count  INT(10) NOT NULL DEFAULT 0 COMMENT '老用户玩游戏数',
   next_day_stay_count   INT(10) NOT NULL DEFAULT 0 COMMENT '次日留存',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: month_register_source_report                          */
/*==============================================================*/
create table month_register_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '月报表，注册来源统计报表',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
   pc_page_view         INT(10) NOT NULL DEFAULT 0 COMMENT 'pc页面浏览数pv',
   pc_user_view         INT(10) NOT NULL DEFAULT 0 COMMENT 'pc用户访问数uv',
   hfive_population     INT(10) NOT NULL DEFAULT 0 COMMENT 'h5用户数',
   hfive_page_view      INT(10) NOT NULL DEFAULT 0 COMMENT 'h5 pv',
   hfive_user_view      INT(10) NOT NULL DEFAULT 0 COMMENT 'h5 uv',
   ios_population       INT(10) NOT NULL DEFAULT 0 COMMENT 'ios访问量',
   ios_install_count    INT(10) NOT NULL DEFAULT 0 COMMENT 'ios安装量',
   android_population    INT(10) NOT NULL DEFAULT 0 COMMENT 'android访问量',
   android_install_count INT(10) NOT NULL DEFAULT 0 COMMENT 'android安装量',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: month_loign_source_report                          */
/*==============================================================*/
create table month_recharge_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '月报表，充值来源统计报表',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
   source_type          TINYINT(2) NOT NULL DEFAULT 0 COMMENT '源端类型：1、PC  2.H5 3.IOS 4.android',
   recharge_population   INT(10) NOT NULL DEFAULT 0 COMMENT 'PC-充值人数',
   recharge_amount     DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT 'PC-充值金额',
   recharge_count     INT(10) NOT NULL DEFAULT 0 COMMENT 'PC-充值次数',
   new_recharge_population     INT(10) NOT NULL DEFAULT 0 COMMENT 'PC-新用户充值人数',
   old_recharge_population      INT(10) NOT NULL DEFAULT 0 COMMENT 'PC-老用户充值人数',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: 登录来源统计                         */
/*==============================================================*/
create table month_loign_game_report
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '日报表，充值来源统计报表',
   game_code            VARCHAR(64) NOT NULL DEFAULT '' COMMENT '游戏编码',
   game_name            VARCHAR(50) NOT NULL DEFAULT '' COMMENT '游戏名称',
   statistics_day       VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM',
   source_type          TINYINT(2) NOT NULL DEFAULT 0 COMMENT '源端类型：1、PC  2.H5 3.IOS 4.android',
   login_population     INT(10) NOT NULL DEFAULT 0 COMMENT '登录人数',
   play_population      INT(10) NOT NULL DEFAULT 0 COMMENT '玩游戏人数',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


