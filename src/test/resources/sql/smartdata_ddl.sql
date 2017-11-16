USE `smartdata`;

DROP TABLE IF EXISTS `daily_composite_report`;

CREATE TABLE `daily_composite_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日报表，综合报表',
   `statistics_day` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   `total_register_population` INT(10) NOT NULL DEFAULT '0' COMMENT '总注册人数',
   `register_population` INT(10) NOT NULL DEFAULT '0' COMMENT '当日注册人数',
   `register_exp_count` INT(10) NOT NULL DEFAULT '0' COMMENT '注册体验量',
   `real_exp_count` INT(10) NOT NULL DEFAULT '0' COMMENT '真体验',
   `virtual_exp_count` INT(10) NOT NULL DEFAULT '0' COMMENT '假体验',
   `new_user_recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户充值人数',
   `new_user_recharge_count` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户充值次数',
   `new_user_recharge_amount` DECIMAL(14,4) NOT NULL DEFAULT '0.0000' COMMENT '新用户充值金额',
   `new_user_login_count` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户登录人数',
   `new_user_play_count` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户玩游戏数',
   `old_user_recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户充值人数',
   `old_user_recharge_count` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户充值次数',
   `old_user_recharge_amount` DECIMAL(14,4) NOT NULL DEFAULT '0.0000' COMMENT '老用户充值金额',
   `old_user_login_count` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户登录人数',
   `old_user_play_count` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户玩游戏数',
   `next_day_stay_count` INT(10) NOT NULL DEFAULT '0' COMMENT '次日留存',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_statistics_day` (`statistics_day`)
) ENGINE=INNODB AUTO_INCREMENT=88676 DEFAULT CHARSET=utf8 COMMENT='日报表，综合报表';

/*Table structure for table `daily_keep_record_report` */

DROP TABLE IF EXISTS `daily_keep_record_report`;

CREATE TABLE `daily_keep_record_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日报表，充值来源统计报表',
   `statistics_day` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   `source_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '源端类型：1、PC 2.android 3.IOS 4.H5 0.总的，不分平台',
   `register_count` INT(10) NOT NULL DEFAULT '0' COMMENT '注册人数',
   `one_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '1天后留存',
   `two_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '2天后留存',
   `three_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '3天后留存',
   `four_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '4天后留存',
   `five_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '5天后留存',
   `six_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '6天后留存',
   `seven_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '7天后留存',
   `fourteen_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '14天后留存',
   `thirty_remain` INT(10) NOT NULL DEFAULT '0' COMMENT '30天后留存',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_day_source` (`statistics_day`,`source_type`)
) ENGINE=INNODB AUTO_INCREMENT=199076 DEFAULT CHARSET=utf8 COMMENT='日报表，充值来源统计报表';

/*Table structure for table `daily_login_report` */

DROP TABLE IF EXISTS `daily_login_report`;

CREATE TABLE `daily_login_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日报表，游戏登录统计报表',
   `statistics_day` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   `platform_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '游戏编码',
   `source_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '源端类型：1、PC 2.android 3.IOS 4.H5',
   `platform_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '游戏名称',
   `login_population` INT(10) NOT NULL DEFAULT '0' COMMENT '登录人数',
   `play_population` INT(10) NOT NULL DEFAULT '0' COMMENT '玩游戏人数',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_day_plat_source` (`statistics_day`,`platform_code`,`source_type`)
) ENGINE=INNODB AUTO_INCREMENT=389679 DEFAULT CHARSET=utf8 COMMENT='日报表，充值来源统计报表';

/*Table structure for table `daily_recharge_report` */

DROP TABLE IF EXISTS `daily_recharge_report`;

CREATE TABLE `daily_recharge_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日报表，充值来源统计报表',
   `statistics_day` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   `source_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '源端类型：1、PC 2.android 3.IOS 4.H5',
   `login_population` INT(10) NOT NULL DEFAULT '0' COMMENT '用户登录人数。',
   `recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT '充值人数',
   `recharge_amount` DECIMAL(14,4) NOT NULL DEFAULT '0.0000' COMMENT '充值金额',
   `recharge_count` INT(10) NOT NULL DEFAULT '0' COMMENT '充值次数',
   `new_recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户充值人数',
   `old_recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户充值人数',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_day_source` (`statistics_day`,`source_type`)
) ENGINE=INNODB AUTO_INCREMENT=183958 DEFAULT CHARSET=utf8 COMMENT='日报表，充值来源统计报表';

/*Table structure for table `daily_register_report` */

DROP TABLE IF EXISTS `daily_register_report`;

CREATE TABLE `daily_register_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日报表，注册来源统计报表',
   `statistics_day` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM-dd',
   `pc_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'pc用户数',
   `pc_page_view` BIGINT(20) NOT NULL DEFAULT '0' COMMENT 'pc页面浏览数pv',
   `pc_user_view` INT(10) NOT NULL DEFAULT '0' COMMENT 'pc用户访问数uv',
   `h5_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'h5用户数',
   `h5_page_view` BIGINT(20) NOT NULL DEFAULT '0' COMMENT 'h5 pv',
   `h5_user_view` INT(10) NOT NULL DEFAULT '0' COMMENT 'h5 uv',
   `ios_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'ios用户数',
   `ios_install_count` INT(10) NOT NULL DEFAULT '0' COMMENT 'ios安装量',
   `android_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'android用户数',
   `android_install_count` INT(10) NOT NULL DEFAULT '0' COMMENT 'android安装量',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_statistics_day` (`statistics_day`)
) ENGINE=INNODB AUTO_INCREMENT=82960 DEFAULT CHARSET=utf8 COMMENT='日报表，注册来源统计报表';

/*Table structure for table `interval_game_launch_report` */

DROP TABLE IF EXISTS `interval_game_launch_report`;

CREATE TABLE `interval_game_launch_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '平台实时统计，游戏启动统计',
   `statistics_time` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计时间(半小时)yyyy-MM-dd HH:30:00',
   `interval_time` INT(5) NOT NULL DEFAULT '0' COMMENT '统计间隔时间。单位分钟',
   `source_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '源端类型：1、PC 2.android 3.IOS 4.H5',
   `platform_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '平台或游戏code',
   `platform_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '平台名称或游戏名称',
   `launch_count` INT(10) NOT NULL DEFAULT '0' COMMENT '游戏启动次数',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_stat_time_se_type_pl_id` (`statistics_time`,`source_type`,`platform_code`)
) ENGINE=INNODB AUTO_INCREMENT=68228 DEFAULT CHARSET=utf8 COMMENT='平台实时统计，游戏启动统计';

/*Table structure for table `interval_interface_report` */

DROP TABLE IF EXISTS `interval_interface_report`;

CREATE TABLE `interval_interface_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '平台实时统计，接口统计',
   `statistics_time` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(半小时)yyyy-MM-dd HH:30:00',
   `interval_time` INT(5) DEFAULT '0' COMMENT '统计时间间隔。单位分钟。',
   `interface_code` INT(10) NOT NULL DEFAULT '0' COMMENT '接口编号，1.注册，2充值',
   `interface_name` VARCHAR(50) NOT NULL COMMENT '接口名称',
   `operate_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '操作类型类型：1、请求，2、执行完成',
   `operate_count` INT(10) NOT NULL DEFAULT '0' COMMENT '操作次数',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_statistics_time_inter_code_op_type` (`statistics_time`,`operate_type`,`interface_code`)
) ENGINE=INNODB AUTO_INCREMENT=19078 DEFAULT CHARSET=utf8 COMMENT='平台实时统计，接口统计';

/*Table structure for table `interval_source_report` */

DROP TABLE IF EXISTS `interval_source_report`;

CREATE TABLE `interval_source_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '平台实时统计，各端实时统计',
   `statistics_time` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(半小时)yyyy-MM-dd HH:30:00',
   `interval_time` INT(5) NOT NULL DEFAULT '0' COMMENT '统计间隔时间，单位分钟',
   `source_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '源端类型：1、PC 2.android 3.IOS 4.H5',
   `register_population` INT(10) NOT NULL DEFAULT '0' COMMENT '当日注册人数',
   `login_population` INT(10) NOT NULL DEFAULT '0' COMMENT '登录人数',
   `recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT '充值人数',
   `recharge_count` INT(10) NOT NULL DEFAULT '0' COMMENT '充值次数',
   `recharge_amount` DECIMAL(14,4) NOT NULL DEFAULT '0.0000' COMMENT '充值金额',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_statistics_time_source_type` (`statistics_time`,`source_type`)
) ENGINE=INNODB AUTO_INCREMENT=32249 DEFAULT CHARSET=utf8 COMMENT='平台实时统计，各端实时统计';

/*Table structure for table `month_composite_report` */

DROP TABLE IF EXISTS `month_composite_report`;

CREATE TABLE `month_composite_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '月报表，综合报表',
   `statistics_month` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
   `register_population` INT(10) NOT NULL DEFAULT '0' COMMENT '注册人数',
   `register_exp_count` INT(10) NOT NULL DEFAULT '0' COMMENT '注册体验量',
   `real_exp_count` INT(10) NOT NULL DEFAULT '0' COMMENT '真体验',
   `virtual_exp_count` INT(10) NOT NULL DEFAULT '0' COMMENT '假体验',
   `new_user_recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户充值人数',
   `new_user_recharge_count` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户充值次数',
   `new_user_recharge_amount` DECIMAL(14,4) NOT NULL DEFAULT '0.0000' COMMENT '新用户充值金额',
   `new_user_login_count` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户登录人数',
   `new_user_play_count` INT(10) NOT NULL DEFAULT '0' COMMENT '新用户玩游戏数',
   `total_register_population` INT(10) NOT NULL DEFAULT '0' COMMENT '注册总人数。',
   `old_user_recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户充值人数',
   `old_user_recharge_count` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户充值次数',
   `old_user_recharge_amount` DECIMAL(14,4) NOT NULL DEFAULT '0.0000' COMMENT '老用户充值金额',
   `old_user_login_count` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户登录人数',
   `old_user_play_count` INT(10) NOT NULL DEFAULT '0' COMMENT '老用户玩游戏数',
   `next_day_stay_count` INT(10) NOT NULL DEFAULT '0' COMMENT '次日留存',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_statistics_month` (`statistics_month`)
) ENGINE=INNODB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COMMENT='月报表，综合报表';

/*Table structure for table `month_login_report` */

DROP TABLE IF EXISTS `month_login_report`;

CREATE TABLE `month_login_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日报表，游戏登录统计报表',
   `statistics_month` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '统计日期(日)yyyy-MM',
   `platform_code` VARCHAR(50) NOT NULL DEFAULT '0' COMMENT '平台id或者游戏id',
   `platform_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '平台或游戏名称',
   `source_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '源端类型：1、PC 2.android 3.IOS 4.H5',
   `login_population` INT(10) NOT NULL DEFAULT '0' COMMENT '登录人数',
   `play_population` INT(10) NOT NULL DEFAULT '0' COMMENT '玩游戏人数',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_statistics_month` (`statistics_month`,`platform_code`,`source_type`)
) ENGINE=INNODB AUTO_INCREMENT=2245 DEFAULT CHARSET=utf8 COMMENT='日报表，充值来源统计报表';

/*Table structure for table `month_recharge_report` */

DROP TABLE IF EXISTS `month_recharge_report`;

CREATE TABLE `month_recharge_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '月报表，充值来源统计报表',
   `statistics_month` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
   `source_type` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '源端类型：1、PC 2.android 3.IOS 4.H5',
   `login_population` INT(10) NOT NULL DEFAULT '0' COMMENT '用户登录人数。',
   `recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'PC-充值人数',
   `recharge_amount` DECIMAL(14,4) NOT NULL DEFAULT '0.0000' COMMENT 'PC-充值金额',
   `recharge_count` INT(10) NOT NULL DEFAULT '0' COMMENT 'PC-充值次数',
   `new_recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'PC-新用户充值人数',
   `old_recharge_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'PC-老用户充值人数',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_month_source` (`statistics_month`,`source_type`)
) ENGINE=INNODB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8 COMMENT='月报表，充值来源统计报表';

/*Table structure for table `month_register_report` */

DROP TABLE IF EXISTS `month_register_report`;

CREATE TABLE `month_register_report` (
   `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '月报表，注册来源统计报表',
   `statistics_month` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '统计日期(月)yyyy-MM',
   `pc_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'pc用户数',
   `pc_page_view` BIGINT(20) NOT NULL DEFAULT '0' COMMENT 'pc页面浏览数pv',
   `pc_user_view` INT(10) NOT NULL DEFAULT '0' COMMENT 'pc用户访问数uv',
   `h5_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'h5用户数',
   `h5_page_view` BIGINT(20) NOT NULL DEFAULT '0' COMMENT 'h5 pv',
   `h5_user_view` INT(10) NOT NULL DEFAULT '0' COMMENT 'h5 uv',
   `ios_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'ios访问量',
   `ios_install_count` INT(10) NOT NULL DEFAULT '0' COMMENT 'ios安装量',
   `android_population` INT(10) NOT NULL DEFAULT '0' COMMENT 'android访问量',
   `android_install_count` INT(10) NOT NULL DEFAULT '0' COMMENT 'android安装量',
   `execute_time` TIMESTAMP NULL DEFAULT NULL COMMENT '统计执行日期',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_statistics_month` (`statistics_month`)
) ENGINE=INNODB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='月报表，注册来源统计报表';

