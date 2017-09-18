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

/*==============================================================*/
/* Table: game_launch_statistic                                 */
/*==============================================================*/
CREATE TABLE interval_game_launch_report 
(
   id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '平台实时统计，游戏启动统计',
   statistics_time      VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计时间(半小时)yyyy-MM-dd HH:30:00',
   game_code            VARCHAR(10) NOT NULL DEFAULT '' COMMENT '游戏编码',
   game_name            VARCHAR(10) NOT NULL DEFAULT '' COMMENT '游戏名称',
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
   statistics_time      VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(半小时)yyyy-MM-dd HH:30:00',
   interface_name       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '接口名称',
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
   statistics_time      VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(半小时)yyyy-MM-dd HH:30:00',
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
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
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
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
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
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
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
   game_code            VARCHAR(10) NOT NULL DEFAULT '' COMMENT '游戏编码',
   game_name            VARCHAR(10) NOT NULL DEFAULT '' COMMENT '游戏名称',
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
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
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
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
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
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
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
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
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
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
   game_code            VARCHAR(10) NOT NULL DEFAULT '' COMMENT '游戏编码',
   game_name            VARCHAR(10) NOT NULL DEFAULT '' COMMENT '游戏名称',
   statistics_day       VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM',
   source_type          TINYINT(2) NOT NULL DEFAULT 0 COMMENT '源端类型：1、PC  2.H5 3.IOS 4.android',
   login_population     INT(10) NOT NULL DEFAULT 0 COMMENT '登录人数',
   play_population      INT(10) NOT NULL DEFAULT 0 COMMENT '玩游戏人数',
   execute_time         DATETIME DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


