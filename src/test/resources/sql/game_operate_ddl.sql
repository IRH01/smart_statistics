USE `smartdata`;

-- ----------------------------
-- table structure for gp_tbl_platform_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_platform_info`;
CREATE TABLE `gp_tbl_platform_info` (
  `country` DOUBLE DEFAULT NULL COMMENT '国家标识（0中国 1美国 2泰国 3越南 4韩国 5印尼 6繁体 7新加坡 8马来西亚）',
  `platform_terminal` DOUBLE DEFAULT NULL COMMENT '平台类型（1.pc 2.安卓 3ios 4.h5  5.其他）',
  `country_currency` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '货币',
  `is_oneself` DOUBLE DEFAULT NULL COMMENT '0.我们自己平台  1.外部平台',
  `id` DOUBLE NOT NULL COMMENT '平台标识',
  `name` VARCHAR(128) COLLATE utf8_bin DEFAULT NULL COMMENT '平台名称',
  `type` DOUBLE DEFAULT NULL COMMENT '平台类型',
  `is_game` DOUBLE DEFAULT NULL COMMENT '是否是游戏(0 不是 1 是)',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `developers` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '开发商',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_platform_info` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='平台信息表';

-- ----------------------------
-- table structure for tbl_gp_plat_terminal_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_plat_terminal_info`;
CREATE TABLE `tbl_gp_plat_terminal_info` (
  `id` DOUBLE NOT NULL COMMENT '平台类型（1.pc 2.安卓 3ios 4.h5  5.其他）',
  `name` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '平台名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_tbl_gp_plat_terminal_info` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='平台信息表';

-- ----------------------------
-- table structure for gp_tbl_game_server_config
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_server_config`;
CREATE TABLE `gp_tbl_game_server_config` (
  `id` DOUBLE NOT NULL COMMENT '主键',
  `name` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `area_id` DOUBLE DEFAULT NULL COMMENT '区id',
  `area_name` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL COMMENT '区名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_game_server_config` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='游戏服配置表';

-- ----------------------------
-- table structure for gp_tbl_game_area_config
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_area_config`;
CREATE TABLE `gp_tbl_game_area_config` (
  `id` DOUBLE NOT NULL COMMENT '主键',
  `name` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `platform_name` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL COMMENT '平台名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_game_area_config` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='游戏区配置表';

-- ----------------------------
-- table structure for gp_tbl_country_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_country_info`;
CREATE TABLE `gp_tbl_country_info` (
  `id` INT(11) DEFAULT NULL COMMENT '国家码id',
  `name` CHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '国家描述'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- table structure for gp_tbl_game_total_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_total_daily`;
CREATE TABLE `gp_tbl_game_total_daily` (
  `country` DOUBLE NOT NULL COMMENT '国家',
  `stat_date` DATETIME NOT NULL COMMENT '统计时间',
  `reg_count` DOUBLE DEFAULT NULL COMMENT '注册人数',
  `recharge_count` DOUBLE DEFAULT NULL COMMENT '付费人数',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '付费金额',
  `reg_total_count` DOUBLE DEFAULT NULL COMMENT '总注册人数',
  `dau` DOUBLE DEFAULT NULL COMMENT 'dau',
  `wau` DOUBLE DEFAULT NULL COMMENT 'wau',
  `mau` DOUBLE DEFAULT NULL COMMENT 'mau',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`stat_date`,`country`),
  UNIQUE KEY `pk_game_total_daily` (`stat_date`,`country`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='游戏数据统计日报表';

-- ----------------------------
-- table structure for gp_tbl_game_platform_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_platform_daily`;
CREATE TABLE `gp_tbl_game_platform_daily` (
  `stat_date` DATETIME NOT NULL COMMENT '统计时间',
  `game_id` DOUBLE NOT NULL COMMENT '游戏id',
  `reg_count` DOUBLE DEFAULT NULL COMMENT '注册人数',
  `recharge_count` DOUBLE DEFAULT NULL COMMENT '付费人数',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '付费金额',
  `reg_total_count` DOUBLE DEFAULT NULL COMMENT '总注册人数',
  `dau` DOUBLE DEFAULT NULL COMMENT 'dau',
  `wau` DOUBLE DEFAULT NULL COMMENT 'wau',
  `mau` DOUBLE DEFAULT NULL COMMENT 'mau',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`stat_date`,`game_id`),
  UNIQUE KEY `pk_game_platform_info` (`stat_date`,`game_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='游戏平台数据统计日报表';

-- ----------------------------
-- table structure for gp_tbl_game_channel_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_channel_daily`;
CREATE TABLE `gp_tbl_game_channel_daily` (
  `stat_date` DATETIME NOT NULL COMMENT '统计时间',
  `game_id` DOUBLE NOT NULL COMMENT '游戏id',
  `channel_id` VARCHAR(10) COLLATE utf8_bin NOT NULL COMMENT '渠道id,前缀为一个英文字母再加5个数字',
  `reg_count` DOUBLE DEFAULT NULL COMMENT '注册人数',
  `recharge_count` DOUBLE DEFAULT NULL COMMENT '付费人数',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '付费金额',
  `reg_total_count` DOUBLE DEFAULT NULL COMMENT '总注册人数',
  `dau` DOUBLE DEFAULT NULL COMMENT 'dau',
  `wau` DOUBLE DEFAULT NULL COMMENT 'wau',
  `mau` DOUBLE DEFAULT NULL COMMENT 'mau',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`stat_date`,`channel_id`,`game_id`),
  UNIQUE KEY `pk_game_channel_info` (`stat_date`,`channel_id`,`game_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='游戏渠道数据统计日报表';

-- ----------------------------
-- table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `id` DOUBLE NOT NULL COMMENT '记录id  sq_channel',
  `chid` VARCHAR(10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id,前缀为一个英文字母再加5个数字',
  `chname` VARCHAR(120) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道名称',
  `userid` DOUBLE DEFAULT NULL COMMENT '帐号id',
  `savetime` DATETIME DEFAULT NULL COMMENT '保存时间',
  `settlement` VARCHAR(60) COLLATE utf8_bin DEFAULT NULL COMMENT '结算方式(1:cpa;2:cps;3:cptcpccpmcpd)',
  `price` DECIMAL(10,2) DEFAULT NULL COMMENT 'cpa单价',
  `promote_content` DOUBLE DEFAULT NULL COMMENT '推广内容',
  `contacts` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `mobile` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `qq` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号码',
  `email` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `splitratio` DECIMAL(10,4) DEFAULT NULL COMMENT 'cps分成比例',
  `mapper` VARCHAR(10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道映射',
  `chidalias` VARCHAR(60) COLLATE utf8_bin DEFAULT NULL COMMENT 'chid md5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0011731` (`id`),
  UNIQUE KEY `sys_c0011735` (`chid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='渠道表';

-- ----------------------------
-- table structure for tbl_vip_reg_channel_stat
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vip_reg_channel_stat`;
CREATE TABLE `tbl_vip_reg_channel_stat` (
  `stat_date` DATETIME NOT NULL COMMENT '统计日期',
  `channel_id` VARCHAR(30) COLLATE utf8_bin NOT NULL COMMENT '渠道id',
  `pv_cnt` DOUBLE DEFAULT NULL COMMENT 'pv',
  `uv_cnt` DOUBLE DEFAULT NULL COMMENT 'uv',
  `ip_cnt` DOUBLE DEFAULT NULL COMMENT 'ip数',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '总注册用户数',
  `pay_ucnt` DOUBLE DEFAULT NULL COMMENT '付费用户数',
  `pay_first_amount` DOUBLE DEFAULT NULL COMMENT '首冲金额',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '充值总额',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '修改时间',
  `country` DOUBLE NOT NULL COMMENT '国家',
  PRIMARY KEY (`stat_date`,`country`,`channel_id`),
  UNIQUE KEY `pk_vip_reg_channel_stat` (`stat_date`,`country`,`channel_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员注册渠道统计';

-- ----------------------------
-- table structure for tbl_vip_reg_channel_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vip_reg_channel_detail`;
CREATE TABLE `tbl_vip_reg_channel_detail` (
  `user_id` VARCHAR(100) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `pay_first_amount` DOUBLE DEFAULT NULL COMMENT '首冲金额',
  `pay_date` DATETIME DEFAULT NULL COMMENT '付费日期',
  `pay_platform_id` DOUBLE NOT NULL COMMENT '付费平台id',
  `country` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '充值国家',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '充值总额',
  `is_vip` DOUBLE DEFAULT NULL COMMENT '是否直属会员：1、是 0、否',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`,`pay_platform_id`),
  UNIQUE KEY `sys_c0012785` (`user_id`,`pay_platform_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员注册渠道明细统计';

-- ----------------------------
-- table structure for tbl_cperation_channel_stat
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cperation_channel_stat`;
CREATE TABLE `tbl_cperation_channel_stat` (
  `dir_code` VARCHAR(50) COLLATE utf8_bin NOT NULL COMMENT '目录代码',
  `stat_date` DATETIME NOT NULL COMMENT '统计日期',
  `channel_id` VARCHAR(100) COLLATE utf8_bin NOT NULL COMMENT '渠道id',
  `platform_id` DOUBLE NOT NULL COMMENT '平台id',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `pay_ucnt` DOUBLE DEFAULT NULL COMMENT '付费用户数',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '付费金额',
  `consume_amount` DOUBLE DEFAULT NULL COMMENT '消费余额',
  `account_balance` DOUBLE DEFAULT NULL COMMENT '账户余额',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`dir_code`,`platform_id`,`channel_id`,`stat_date`),
  UNIQUE KEY `sys_c0013321` (`dir_code`,`platform_id`,`channel_id`,`stat_date`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='市场合作渠道数据统计';

-- ----------------------------
-- table structure for tbl_dim_partner_member_con
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dim_partner_member_con`;
CREATE TABLE `tbl_dim_partner_member_con` (
  `referrer_website_url` VARCHAR(512) COLLATE utf8_bin DEFAULT NULL COMMENT '来源网址',
  `promote_url` VARCHAR(512) COLLATE utf8_bin DEFAULT NULL COMMENT '推广链接',
  `member_id` VARCHAR(50) COLLATE utf8_bin NOT NULL COMMENT '会员的userid',
  `partner_id` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴的userid',
  `partner_no` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴的代理编码',
  `partner_name` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴的真实姓名',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `sys_c0012451` (`member_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='合作伙伴所属直属会员关联关系表';

-- ----------------------------
-- table structure for tbl_user_channel
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_channel`;
CREATE TABLE `tbl_user_channel` (
  `id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` DOUBLE DEFAULT NULL COMMENT '用户id',
  `channel_id` VARCHAR(10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0012754` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户渠道关系表';

-- ----------------------------
-- table structure for tbl_gp_dim_order_status_market
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_dim_order_status_market`;
CREATE TABLE `tbl_gp_dim_order_status_market` (
  `status_id` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号 关联视图的订单状态 pay_status',
  `status_name` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单类型'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单状态维表 取自recharge_record.status 第三方充值通知状态(0：处理中1：成功2：失败 3.已关闭)';

-- ----------------------------
-- table structure for tbl_dm_user_active_remain_day
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dm_user_active_remain_day`;
CREATE TABLE `tbl_dm_user_active_remain_day` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `channel_id` VARCHAR(10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `add_uv` DOUBLE DEFAULT NULL COMMENT '新增uv',
  `remain_1_ucnt` DOUBLE DEFAULT NULL COMMENT '1天后留存用户数',
  `remain_1_rate` DOUBLE DEFAULT NULL COMMENT '1天后留存率',
  `remain_2_ucnt` DOUBLE DEFAULT NULL COMMENT '2天后留存用户数',
  `remain_2_rate` DOUBLE DEFAULT NULL COMMENT '2天后留存率',
  `remain_3_ucnt` DOUBLE DEFAULT NULL COMMENT '3天后留存用户数',
  `remain_3_rate` DOUBLE DEFAULT NULL COMMENT '3天后留存率',
  `remain_4_ucnt` DOUBLE DEFAULT NULL COMMENT '4天后留存用户数',
  `remain_4_rate` DOUBLE DEFAULT NULL COMMENT '4天后留存率',
  `remain_5_ucnt` DOUBLE DEFAULT NULL COMMENT '5天后留存用户数',
  `remain_5_rate` DOUBLE DEFAULT NULL COMMENT '5天后留存率',
  `remain_6_ucnt` DOUBLE DEFAULT NULL COMMENT '6天后留存用户数',
  `remain_6_rate` DOUBLE DEFAULT NULL COMMENT '6天后留存率',
  `remain_7_ucnt` DOUBLE DEFAULT NULL COMMENT '7天后留存用户数',
  `remain_7_rate` DOUBLE DEFAULT NULL COMMENT '7天后留存率',
  `remain_14_ucnt` DOUBLE DEFAULT NULL COMMENT '14天后留存用户数',
  `remain_14_rate` DOUBLE DEFAULT NULL COMMENT '14天后留存率',
  `remain_30_ucnt` DOUBLE DEFAULT NULL COMMENT '30天后留存用户数',
  `remain_30_rate` DOUBLE DEFAULT NULL COMMENT '30天后留存率',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户活跃留存日报表';

###################
-- ----------------------------
-- table structure for tbl_dm_inner_game_chnl_opt_h
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dm_inner_game_chnl_opt_h`;
CREATE TABLE `tbl_dm_inner_game_chnl_opt_h` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `channel_id` VARCHAR(10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `recharge_ucnt` DOUBLE DEFAULT NULL COMMENT '充值用户数',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='内部游戏渠道运营小时表';

-- ----------------------------
-- table structure for tbl_dm_inner_game_chnl_opt_day
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dm_inner_game_chnl_opt_day`;
CREATE TABLE `tbl_dm_inner_game_chnl_opt_day` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `channel_id` VARCHAR(10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `recharge_ucnt` DOUBLE DEFAULT NULL COMMENT '充值用户数',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='内部游戏渠道运营日表';

-- ----------------------------
-- table structure for gp_tbl_recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_recharge_record`;
CREATE TABLE `gp_tbl_recharge_record` (
  `currency` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '币种名称',
  `orderno` VARCHAR(64) COLLATE utf8_bin NOT NULL COMMENT '充值订单号 pk',
  `user_id` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '用户唯一标识',
  `apply_amount` DOUBLE DEFAULT NULL COMMENT '申请充值金额',
  `real_lyb` DOUBLE DEFAULT NULL COMMENT '兑换(用乐盈币支付)时候需传值，其他走第三方 充值时候 （关系真正钱时候） 不用传值',
  `status` DOUBLE DEFAULT NULL COMMENT '第三方充值通知状态(0：处理中1：成功2：失败 3.已关闭)',
  `platform_type` DOUBLE DEFAULT NULL COMMENT '充值渠道标识 1：微信支付 2：支付宝 3：乐盈币兑换 4：银联充值 5：优贝支付6：cc电子钱包 7：cc点卡 9：mol电子钱包 10：mol点卡 11：越南cardchargepay 13 :支付宝, 14:微信 16: 快捷支付 17: 网银支付 18: 易游酷点卡支付',
  `apply_time` DATETIME DEFAULT NULL COMMENT '充值申请时间',
  `handle_time` DATETIME DEFAULT NULL COMMENT '处理时间',
  `platform_order` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '充值平台订单号',
  `handle_type` DOUBLE DEFAULT NULL COMMENT '充值订单处理方式 1, 在线充值 2系统自动充值',
  `source` DOUBLE DEFAULT NULL COMMENT '操作终端（1pc2ios3安卓4其他）',
  `remark` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `operatorid` DOUBLE DEFAULT NULL COMMENT '充值操作人',
  `payment_orderno` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '业务订单号',
  `notify_status` DOUBLE DEFAULT NULL COMMENT '第三方充值成功后通知我们后的 个业务处理操作状态(0：处理中1：成功2：失败)',
  `platform_source` DOUBLE DEFAULT NULL COMMENT '平台来源',
  `change_type` DOUBLE DEFAULT NULL COMMENT '交易类型1.充值,2.兑换(用乐盈币支付),3.充值到子平台,4.系统充值（后台手工充值）5.系统扣除乐盈币 6.转入 7.转出  8.转账',
  `plateform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `son_plateformid` DOUBLE DEFAULT NULL COMMENT '具体平台下面的区服',
  PRIMARY KEY (`orderno`),
  UNIQUE KEY `pk_recharge_record` (`orderno`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='充值记录表';

-- ----------------------------
-- table structure for gp_tbl_user_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_user_info`;
CREATE TABLE `gp_tbl_user_info` (
  `channel_id` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `user_id` VARCHAR(50) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `email` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `phone` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `head_icon` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `nickname` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `sex` DOUBLE DEFAULT NULL COMMENT '性别 1男 2女',
  `age` DOUBLE DEFAULT NULL COMMENT '年龄',
  `location` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '所在地',
  `status` DOUBLE DEFAULT NULL COMMENT '用户状态 0 禁用 1 未激活 2 已激活',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `last_login_time` DATETIME DEFAULT NULL COMMENT '最后一次登录时间',
  `real_name` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `id_card_no` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号码',
  `address` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '详细地址',
  `qq_number` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号码',
  `phone_status` DOUBLE DEFAULT NULL COMMENT '手机绑定状态（0未有 1绑定）',
  `email_status` DOUBLE DEFAULT NULL COMMENT '邮箱绑定状态（0未有 1绑定）',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `reg_type` DOUBLE DEFAULT NULL COMMENT '注册类型 1 用户名 2 手机 3 邮箱 4 第三方登录',
  `paypassword` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '支付密码',
  `os_type` DOUBLE DEFAULT NULL COMMENT '来源（1pc2ios3安卓4其他）',
  `version_code` DOUBLE DEFAULT NULL COMMENT '版本编号',
  `version_name` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '版本名称',
  `head_icon_small` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图像小图',
  `login_type` VARCHAR(2) COLLATE utf8_bin DEFAULT NULL COMMENT '登录类型 0：平台注册登录；1：qq注册登录；2：新浪微博注册登录；3：微信注册登录；4：支付宝注册登录；5：twitter注册登录；6：facebook注册登录；',
  `forbidden_starttime` DATETIME DEFAULT NULL COMMENT '禁用开始时间',
  `forbidden_enttime` DATETIME DEFAULT NULL COMMENT '禁用结束时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `pk_gp_tbl_user_info` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';

-- ----------------------------
-- table structure for tbl_edm_lmdz_betting_month
-- ----------------------------
DROP TABLE IF EXISTS `tbl_edm_lmdz_betting_month`;
CREATE TABLE `tbl_edm_lmdz_betting_month` (
  `settle_month` DATETIME DEFAULT NULL COMMENT '结算月份',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `user_id` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `betting_times` DOUBLE DEFAULT NULL COMMENT '投注次数',
  `income_acc_sum` DOUBLE DEFAULT NULL COMMENT '收益金额汇总',
  `bet_acc_sum` DOUBLE DEFAULT NULL COMMENT '投注金额'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='撩妹德州投注汇总月表';

-- ----------------------------
-- table structure for tbl_edm_lydj_betting_month
-- ----------------------------
DROP TABLE IF EXISTS `tbl_edm_lydj_betting_month`;
CREATE TABLE `tbl_edm_lydj_betting_month` (
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `user_id` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `settle_month` DATETIME DEFAULT NULL COMMENT '结算月份',
  `betting_times` DOUBLE DEFAULT NULL COMMENT '投注次数',
  `settle_acc_sum` DOUBLE DEFAULT NULL COMMENT '结算金额 ',
  `bet_acc_sum` DOUBLE DEFAULT NULL COMMENT '投注金额'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='乐盈电竞投注汇总月表';

-- ----------------------------
-- table structure for gp_tbl_user_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_user_info`;
CREATE TABLE `gp_tbl_user_info` (
  `channel_id` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `user_id` VARCHAR(50) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `email` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `phone` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `head_icon` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `nickname` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `sex` DOUBLE DEFAULT NULL COMMENT '性别 1男 2女',
  `age` DOUBLE DEFAULT NULL COMMENT '年龄',
  `location` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '所在地',
  `status` DOUBLE DEFAULT NULL COMMENT '用户状态 0 禁用 1 未激活 2 已激活',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `last_login_time` DATETIME DEFAULT NULL COMMENT '最后一次登录时间',
  `real_name` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `id_card_no` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号码',
  `address` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '详细地址',
  `qq_number` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号码',
  `phone_status` DOUBLE DEFAULT NULL COMMENT '手机绑定状态（0未有 1绑定）',
  `email_status` DOUBLE DEFAULT NULL COMMENT '邮箱绑定状态（0未有 1绑定）',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `reg_type` DOUBLE DEFAULT NULL COMMENT '注册类型 1 用户名 2 手机 3 邮箱 4 第三方登录',
  `paypassword` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '支付密码',
  `os_type` DOUBLE DEFAULT NULL COMMENT '来源（1pc2ios3安卓4其他）',
  `version_code` DOUBLE DEFAULT NULL COMMENT '版本编号',
  `version_name` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '版本名称',
  `head_icon_small` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图像小图',
  `login_type` VARCHAR(2) COLLATE utf8_bin DEFAULT NULL COMMENT '登录类型 0：平台注册登录；1：qq注册登录；2：新浪微博注册登录；3：微信注册登录；4：支付宝注册登录；5：twitter注册登录；6：facebook注册登录；',
  `forbidden_starttime` DATETIME DEFAULT NULL COMMENT '禁用开始时间',
  `forbidden_enttime` DATETIME DEFAULT NULL COMMENT '禁用结束时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `pk_gp_tbl_user_info` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';

-- ----------------------------
-- table structure for gp_tbl_login_track
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_login_track`;
CREATE TABLE `gp_tbl_login_track` (
  `id` DOUBLE NOT NULL COMMENT 'id',
  `user_name` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '登录用户名',
  `login_result` DOUBLE DEFAULT NULL COMMENT '登录结果 0 失败 1 成功',
  `login_time` DATETIME DEFAULT NULL COMMENT '登录时间',
  `login_ip` VARCHAR(150) COLLATE utf8_bin DEFAULT NULL COMMENT '登录ip地址',
  `os_type` DOUBLE DEFAULT NULL COMMENT ' 来源（1pc2ios3安卓4其他）',
  `version_code` DOUBLE DEFAULT NULL COMMENT '版本编号',
  `version_name` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '版本名称',
  `platformid` DOUBLE DEFAULT NULL COMMENT '平台id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_gp_tbl_login_track` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户登录日志表';

-- ----------------------------
-- table structure for tbl_gp_country_plateform
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_country_plateform`;
CREATE TABLE `tbl_gp_country_plateform` (
  `platform_terminal` DOUBLE DEFAULT NULL COMMENT '平台类型（1.pc 2.安卓 3ios 4.h5  5.其他）',
  `platform_id` DOUBLE NOT NULL COMMENT '游戏平台id',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额',
  `user_count` DOUBLE DEFAULT NULL COMMENT '充值用户数',
  `recharge_count` DOUBLE DEFAULT NULL COMMENT '充值次数 ',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `country` DOUBLE NOT NULL COMMENT '国家id',
  PRIMARY KEY (`country`,`create_time`,`platform_id`),
  UNIQUE KEY `pk_tbl_gp_country_plateform` (`country`,`create_time`,`platform_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='各国平台充值汇总表';

-- ----------------------------
-- table structure for tbl_ods_lmdz_betting_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ods_lmdz_betting_log`;
CREATE TABLE `tbl_ods_lmdz_betting_log` (
  `settle_time` DATETIME DEFAULT NULL COMMENT '结算时间',
  `user_id` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `bureauno` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '局号',
  `roomno` DOUBLE DEFAULT NULL COMMENT '房间号',
  `game_type` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏类型',
  `income_amount` DOUBLE DEFAULT NULL COMMENT '收益金额',
  `bet_amount` DOUBLE DEFAULT NULL COMMENT '投注金额',
  `board_result` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏结果',
  `card_type` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '牌型',
  `card` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '牌面',
  `create_time` DATETIME DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='撩妹德州投注日志表';

-- ----------------------------
-- table structure for tbl_ods_lydj_betting_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ods_lydj_betting_log`;
CREATE TABLE `tbl_ods_lydj_betting_log` (
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `user_id` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `betting_time` DATETIME DEFAULT NULL COMMENT '投注时间',
  `settle_time` DATETIME DEFAULT NULL COMMENT '结算时间',
  `betting_amount` DOUBLE DEFAULT NULL COMMENT '投注金额',
  `betting_orderno` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '投注订单号',
  `currency` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '货币',
  `settle_amount` DOUBLE DEFAULT NULL COMMENT '结算金额 ',
  `create_time` DATETIME DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='乐盈电竞投注日志表';

-- ----------------------------
-- table structure for gp_tbl_pay_bussiness_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_pay_bussiness_info`;
CREATE TABLE `gp_tbl_pay_bussiness_info` (
  `id` DOUBLE NOT NULL COMMENT '主键',
  `app_id` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '请求支付的应用id',
  `app_name` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '请求支付的应用名称',
  `buyer_id` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '买家唯一标识id',
  `out_trade_no` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '商户网站唯一订单号',
  `trade_name` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `trade_no` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '第三方交易号',
  `trade_status` CHAR(1) COLLATE utf8_bin DEFAULT NULL COMMENT '交易状态',
  `trade_type` CHAR(1) COLLATE utf8_bin DEFAULT NULL COMMENT '交易类型',
  `order_amount` DOUBLE DEFAULT NULL COMMENT '订单金额',
  `total_fee` DOUBLE DEFAULT NULL COMMENT '交易金额',
  `seller_privilege` DOUBLE DEFAULT NULL COMMENT '商家优惠金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '交易创建时间',
  `pay_time` DATETIME DEFAULT NULL COMMENT '支付平台返回的支付时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `payment_type` CHAR(2) COLLATE utf8_bin DEFAULT NULL COMMENT '支付类型',
  `payment_platform` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '支付平台id',
  `business_no` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '支付交易流水号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_pay_bussiness_info` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='支付流水表';

-- ----------------------------
-- table structure for tbl_gp_exchangerate_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_exchangerate_config`;
CREATE TABLE `tbl_gp_exchangerate_config` (
  `id` DOUBLE NOT NULL COMMENT '主键id',
  `country` DOUBLE DEFAULT NULL COMMENT '国家id  （0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来西亚)',
  `lyb_value` DOUBLE DEFAULT NULL COMMENT '乐盈币数值',
  `currency_value` DOUBLE DEFAULT NULL COMMENT '被兑换的货币数值',
  `rate` DOUBLE DEFAULT NULL COMMENT '最后算的汇率（lyb_value/currency_value）',
  `currency` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '币种名称',
  `lastupdate_time` DATETIME DEFAULT NULL COMMENT '上次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0013304` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='运营游戏各国汇率配置表';

-- ----------------------------
-- table structure for tbl_gp_dim_change_type_market
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_dim_change_type_market`;
CREATE TABLE `tbl_gp_dim_change_type_market` (
  `change_id` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '交易类型  pay_status',
  `change_name` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '交易类型描述'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='交易类型维表 取自recharge_record.change_type 交易类型1.充值,2.兑换(用乐盈币支付),3.充值到子平台,4.系统充值（后台手工充值）5.系统扣除乐盈币 6.转入 7.转出  8.转账';

-- ----------------------------
-- table structure for tbl_gp_dim_recharge_way_market
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_dim_recharge_way_market`;
CREATE TABLE `tbl_gp_dim_recharge_way_market` (
  `recharge_id` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '充值渠道标识。关联recharge_record.platform_type',
  `recharge_name` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '充值渠道描述'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='充值方式维表 取自recharge_record.platform_type充值方式 充值渠道标识1：微信支付  2：支付宝 3：乐盈币兑换  4：银联充值 5：优贝支付6：cc电子钱包  7：cc点卡 9：mol电子钱包  10：mol点卡 11：越南cardchargepay';

-- ----------------------------
-- table structure for tbl_gp_country_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_country_order`;
CREATE TABLE `tbl_gp_country_order` (
  `country` DOUBLE NOT NULL COMMENT '国家id',
  `user_count` DOUBLE DEFAULT NULL COMMENT '充值用户数',
  `recharge_count` DOUBLE DEFAULT NULL COMMENT '充值次数',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额 ',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`country`,`create_time`),
  UNIQUE KEY `pk_tbl_gp_country_order` (`country`,`create_time`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='各国会员充值汇总表';

-- ----------------------------
-- table structure for gp_tbl_lyb_record
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_lyb_record`;
CREATE TABLE `gp_tbl_lyb_record` (
  `opposite_lyborderid` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '(返点记录)对应哪条充值记录',
  `currency` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '货币',
  `orderid` VARCHAR(50) COLLATE utf8_bin NOT NULL COMMENT '流水号 pk',
  `user_id` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户唯一标识',
  `lyb` DOUBLE DEFAULT NULL COMMENT 'lyb数量',
  `before_cash` DOUBLE DEFAULT NULL COMMENT '变动前账户',
  `after_cash` DOUBLE DEFAULT NULL COMMENT '变动后账户',
  `change_type` DOUBLE DEFAULT NULL COMMENT '变动记录类型(1充值，2兑换  3充值到游戏)',
  `record_time` DATETIME DEFAULT NULL COMMENT '记录时间',
  `opposite_orderid` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '对方平台订单号',
  `opposite_plateformid` DOUBLE DEFAULT NULL COMMENT '平台id',
  `oppositeinfo` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资金（币）变动(0流进1支出)',
  `trade_type` DOUBLE DEFAULT NULL COMMENT '资金（币）变动(0流进1支出)',
  `remark` VARCHAR(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `terminals_type` DOUBLE DEFAULT NULL COMMENT '操作终端(1pc2ios3安卓4其他)',
  `gamegold` DOUBLE DEFAULT NULL COMMENT '游戏币',
  `rate` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '每次交易汇率记录',
  `lyb_to_yxb_rate` DOUBLE DEFAULT NULL COMMENT '乐盈币-游戏币兑率',
  `cash_to_lyb_rate` DOUBLE DEFAULT NULL COMMENT '现金-乐盈币兑率',
  `son_plateformid` DOUBLE DEFAULT NULL COMMENT '具体平台下面的区服',
  PRIMARY KEY (`orderid`),
  UNIQUE KEY `pk_gp_tbl_lyb_record` (`orderid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员中心资金(乐盈币)变动记录表';

-- ----------------------------
-- table structure for gp_tbl_toplate_errortrade
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_toplate_errortrade`;
CREATE TABLE `gp_tbl_toplate_errortrade` (
  `order_id` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL COMMENT '对应资金变动表id',
  `user_id` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `creattime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `status` DOUBLE DEFAULT NULL COMMENT '0未处理 1已处理  2 已关闭  3再次处理还是异常',
  `remark` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL COMMENT '标记',
  `sumgamegold` DOUBLE DEFAULT NULL COMMENT '需通知平台的总游戏币',
  `plate_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `hand_time` DATETIME DEFAULT NULL COMMENT '处理时间'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='异常交易日志表';

-- ----------------------------
-- table structure for channel_regist
-- ----------------------------
DROP TABLE IF EXISTS `channel_regist`;
CREATE TABLE `channel_regist` (
  `id` DOUBLE NOT NULL COMMENT 'id',
  `channel` VARCHAR(10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `userid` VARCHAR(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `savetime` DATETIME DEFAULT NULL COMMENT '保存时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0011718` (`id`),
  KEY `userid_index` (`userid`),
  KEY `channel_index` (`channel`),
  KEY `savetime_index` (`savetime`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='渠道注册表';

-- ----------------------------
-- table structure for tbl_gp_user_funds
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_user_funds`;
CREATE TABLE `tbl_gp_user_funds` (
  `user_id` VARCHAR(100) COLLATE utf8_bin NOT NULL COMMENT '用户id pk',
  `cash` DOUBLE DEFAULT NULL COMMENT '账户余币',
  `free_cash` DOUBLE DEFAULT NULL COMMENT '无期限限制账户币',
  `sumpay_cash` DOUBLE DEFAULT NULL COMMENT '支付总币',
  `congealpay_cash` DOUBLE DEFAULT NULL COMMENT '支付总冻结币',
  `sumrecharge_money` DOUBLE DEFAULT NULL COMMENT '总充值金额',
  `sumrecharge_lyb` DOUBLE DEFAULT NULL COMMENT '总充值币',
  `singledaypayed_lyb` DOUBLE DEFAULT NULL COMMENT '单日已投资支付币',
  `daypay_lyb` DOUBLE DEFAULT NULL COMMENT '单日支付限额',
  `safepay_lyb` DOUBLE DEFAULT NULL COMMENT '安全支付限额(超过需要手机验证码)',
  `safepayswitch_status` DOUBLE DEFAULT NULL COMMENT '支付限额开关 1-开，2-关',
  `payed_time` DATETIME DEFAULT NULL COMMENT '最近支付时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '账户更新时间',
  `version` DOUBLE DEFAULT NULL COMMENT '版本号(用户对数据进行模拟加锁)',
  `istoday` DATETIME DEFAULT NULL COMMENT '是否是单日支付时间',
  `point` DOUBLE DEFAULT NULL COMMENT '积分',
  `sumrecharge_gamegold` DOUBLE DEFAULT NULL COMMENT '总充值游戏币',
  `lyq` DOUBLE DEFAULT NULL COMMENT '乐盈券',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `sys_c0014563` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户资金表';

-- ----------------------------
-- table structure for tbl_game_user_account
-- ----------------------------
DROP TABLE IF EXISTS `tbl_game_user_account`;
CREATE TABLE `tbl_game_user_account` (
  `dir_code` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '目录代码',
  `user_id` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `account_balance` DOUBLE DEFAULT NULL COMMENT '账户余额',
  `last_update_date` DATETIME DEFAULT NULL COMMENT '最后更新日期',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建日期'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户账户表';

-- ----------------------------
-- table structure for tbl_gp_gamegold_to_lyq
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_gamegold_to_lyq`;
CREATE TABLE `tbl_gp_gamegold_to_lyq` (
  `order_no` VARCHAR(50) COLLATE utf8_bin NOT NULL COMMENT '流水号 pk',
  `user_id` VARCHAR(150) COLLATE utf8_bin DEFAULT NULL COMMENT ' 用户id',
  `before_lyqs` DOUBLE DEFAULT NULL COMMENT '用户划乐盈券之前余额',
  `f_platform_id` DOUBLE DEFAULT NULL COMMENT '划出平台id',
  `f_gamegolds` DOUBLE DEFAULT NULL COMMENT '划出游戏币数量',
  `t_lyqs` DOUBLE DEFAULT NULL COMMENT '划入到账乐盈券数量',
  `f_rate` DOUBLE DEFAULT NULL COMMENT ' 划出方汇率',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `terminals_type` DOUBLE DEFAULT NULL COMMENT '操作终端(1:pc,2:ios 3:安卓 4:h5, 5其他)',
  `source_id` DOUBLE DEFAULT NULL COMMENT ' 从那个哪个平台id 跳转过来的',
  `status` DOUBLE DEFAULT NULL COMMENT '状态(0失败 1成功 2处理中)',
  `remark` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_no`),
  UNIQUE KEY `sys_c0014547` (`order_no`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='游戏币划乐盈券记录表';

-- ----------------------------
-- table structure for tbl_gp_lyq_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_lyq_record`;
CREATE TABLE `tbl_gp_lyq_record` (
  `orderid` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号 pk',
  `user_id` VARCHAR(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户唯一标识',
  `lyq` DOUBLE DEFAULT NULL COMMENT '乐盈券数量',
  `before_lyq` DOUBLE DEFAULT NULL COMMENT '变动前账户',
  `after_lyq` DOUBLE DEFAULT NULL COMMENT '变动后账户',
  `change_type` DOUBLE DEFAULT NULL COMMENT '资金（币）变动(0流进1支出)',
  `record_time` DATETIME DEFAULT NULL COMMENT '记录时间',
  `opposite_orderid` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '对方平台订单号',
  `opposite_plateformid` DOUBLE DEFAULT NULL COMMENT '对方平台id(对接的子系统)',
  `oppositeinfo` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '交易信息(抽奖 游戏  兑换等)',
  `trade_type` DOUBLE DEFAULT NULL COMMENT '资金（币）变动(0流进1支出)',
  `remark` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `terminals_type` DOUBLE DEFAULT NULL COMMENT '操作终端(1:pc,2:安卓3ios4其他)',
  `gamegold` DOUBLE DEFAULT NULL COMMENT '游戏币',
  `rate` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '每次交易汇率记录'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='会员中心资金(乐盈劵)变动记录表';

-- ----------------------------
-- table structure for tbl_gp_gamegold_transrecord
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_gamegold_transrecord`;
CREATE TABLE `tbl_gp_gamegold_transrecord` (
  `order_no` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号 pk',
  `user_id` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `f_platform_id` DOUBLE DEFAULT NULL COMMENT '划出平台id',
  `t_platform_id` DOUBLE DEFAULT NULL COMMENT '划入平台id',
  `f_gamegolds` DOUBLE DEFAULT NULL COMMENT ' 划出游戏币数量',
  `t_gamegolds` DOUBLE DEFAULT NULL COMMENT '划入到账游戏币数量',
  `f_rate` DOUBLE DEFAULT NULL COMMENT '划出方汇率',
  `t_rate` DOUBLE DEFAULT NULL COMMENT '划入方汇率',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `terminals_type` DOUBLE DEFAULT NULL COMMENT '操作终端(1:pc,2:ios 3:安卓 4:h5, 5其他)',
  `source_id` DOUBLE DEFAULT NULL COMMENT '从那个哪个平台id 跳转过来的',
  `status` DOUBLE DEFAULT NULL COMMENT '状态(0失败 1成功 2处理中)',
  `remark` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `ggtt_orderno` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '转出方订单id',
  `ggtf_orderno` VARCHAR(50) COLLATE utf8_bin DEFAULT NULL COMMENT '转入方订单id',
  `t_service_id` DOUBLE DEFAULT NULL COMMENT '具体平台下面的服id'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='游戏币互划账记录表';

-- ----------------------------
-- table structure for gp_tbl_user_recharge_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_user_recharge_daily`;
CREATE TABLE `gp_tbl_user_recharge_daily` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台标识',
  `server_id` DOUBLE DEFAULT NULL COMMENT '所属服',
  `area_id` DOUBLE DEFAULT NULL COMMENT '所属区',
  `user_id` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `total_amount` DOUBLE DEFAULT NULL COMMENT '充值总金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `currency` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '币种',
  UNIQUE KEY `user_recharge_daily_uk` (`stat_date`,`currency`,`user_id`,`area_id`,`server_id`,`platform_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户充值日报表';