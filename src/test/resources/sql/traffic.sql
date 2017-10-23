/*
navicat mysql data transfer

source server         : 192.168.74.160
source server version : 50718
source host           : 192.168.74.160:3306
source database       : traffic

target server type    : mysql
target server version : 50718
file encoding         : 65001

date: 2017-07-24 20:54:48
*/
SET FOREIGN_KEY_CHECKS = 0 ;

-- ----------------------------
-- table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel` ;

CREATE TABLE `channel` (
  `id` DOUBLE NOT NULL COMMENT '记录id  sq_channel',
  `chid` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id,前缀为一个英文字母再加5个数字',
  `chname` VARCHAR (120) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道名称',
  `userid` DOUBLE DEFAULT NULL COMMENT '帐号id',
  `savetime` DATETIME DEFAULT NULL COMMENT '保存时间',
  `settlement` VARCHAR (60) COLLATE utf8_bin DEFAULT NULL COMMENT '结算方式(1:cpa;2:cps;3:cptcpccpmcpd)',
  `price` DECIMAL (10, 2) DEFAULT NULL COMMENT 'cpa单价',
  `promote_content` DOUBLE DEFAULT NULL COMMENT '推广内容',
  `contacts` VARCHAR (20) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `mobile` VARCHAR (20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `qq` VARCHAR (20) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号码',
  `email` VARCHAR (20) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `splitratio` DECIMAL (10, 4) DEFAULT NULL COMMENT 'cps分成比例',
  `mapper` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道映射',
  `chidalias` VARCHAR (60) COLLATE utf8_bin DEFAULT NULL COMMENT 'chid md5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0011731` (`id`),
  UNIQUE KEY `sys_c0011735` (`chid`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '渠道表' ;

-- ----------------------------
-- table structure for channel_regist
-- ----------------------------
DROP TABLE IF EXISTS `channel_regist` ;

CREATE TABLE `channel_regist` (
  `id` DOUBLE NOT NULL COMMENT 'id',
  `channel` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `userid` VARCHAR (20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `savetime` DATETIME DEFAULT NULL COMMENT '保存时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0011718` (`id`),
  KEY `userid_index` (`userid`),
  KEY `channel_index` (`channel`),
  KEY `savetime_index` (`savetime`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '渠道注册表' ;

-- ----------------------------
-- table structure for gp_tbl_country_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_country_info` ;

CREATE TABLE `gp_tbl_country_info` (
  `id` INT (11) DEFAULT NULL COMMENT '国家码id',
  `name` CHAR(255) COLLATE utf8_bin DEFAULT NULL COMMENT '国家描述'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin ;

-- ----------------------------
-- table structure for gp_tbl_game_area_config
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_area_config` ;

CREATE TABLE `gp_tbl_game_area_config` (
  `id` DOUBLE NOT NULL COMMENT '主键',
  `name` VARCHAR (256) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `platform_name` VARCHAR (256) COLLATE utf8_bin DEFAULT NULL COMMENT '平台名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_game_area_config` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏区配置表' ;

-- ----------------------------
-- table structure for gp_tbl_game_channel_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_channel_daily` ;

CREATE TABLE `gp_tbl_game_channel_daily` (
  `stat_date` DATETIME NOT NULL COMMENT '统计时间',
  `game_id` DOUBLE NOT NULL COMMENT '游戏id',
  `channel_id` VARCHAR (10) COLLATE utf8_bin NOT NULL COMMENT '渠道id,前缀为一个英文字母再加5个数字',
  `reg_count` DOUBLE DEFAULT NULL COMMENT '注册人数',
  `recharge_count` DOUBLE DEFAULT NULL COMMENT '付费人数',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '付费金额',
  `reg_total_count` DOUBLE DEFAULT NULL COMMENT '总注册人数',
  `dau` DOUBLE DEFAULT NULL COMMENT 'dau',
  `wau` DOUBLE DEFAULT NULL COMMENT 'wau',
  `mau` DOUBLE DEFAULT NULL COMMENT 'mau',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (
    `stat_date`,
    `channel_id`,
    `game_id`
  ),
  UNIQUE KEY `pk_game_channel_info` (
    `stat_date`,
    `channel_id`,
    `game_id`
  )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏渠道数据统计日报表' ;

-- ----------------------------
-- table structure for gp_tbl_game_platform_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_platform_daily` ;

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
  PRIMARY KEY (`stat_date`, `game_id`),
  UNIQUE KEY `pk_game_platform_info` (`stat_date`, `game_id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏平台数据统计日报表' ;

-- ----------------------------
-- table structure for gp_tbl_game_recharge_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_recharge_daily` ;

CREATE TABLE `gp_tbl_game_recharge_daily` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台标识',
  `server_id` DOUBLE DEFAULT NULL COMMENT '所属服',
  `area_id` DOUBLE DEFAULT NULL COMMENT '所属区',
  `total_amount` DOUBLE DEFAULT NULL COMMENT '订单总金额',
  `receipt_amount` DOUBLE DEFAULT NULL COMMENT '实收金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `currency` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '币种',
  UNIQUE KEY `game_recharge_daily_uk` (
    `stat_date`,
    `currency`,
    `area_id`,
    `server_id`,
    `platform_id`
  )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏充值日报表' ;

-- ----------------------------
-- table structure for gp_tbl_game_server_config
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_server_config` ;

CREATE TABLE `gp_tbl_game_server_config` (
  `id` DOUBLE NOT NULL COMMENT '主键',
  `name` VARCHAR (256) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `area_id` DOUBLE DEFAULT NULL COMMENT '区id',
  `area_name` VARCHAR (256) COLLATE utf8_bin DEFAULT NULL COMMENT '区名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_game_server_config` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏服配置表' ;

-- ----------------------------
-- table structure for gp_tbl_game_total_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_game_total_daily` ;

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
  PRIMARY KEY (`stat_date`, `country`),
  UNIQUE KEY `pk_game_total_daily` (`stat_date`, `country`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏数据统计日报表' ;

-- ----------------------------
-- table structure for gp_tbl_login_track
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_login_track` ;

CREATE TABLE `gp_tbl_login_track` (
  `id` DOUBLE NOT NULL COMMENT 'id',
  `user_name` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '登录用户名',
  `login_result` DOUBLE DEFAULT NULL COMMENT '登录结果 0 失败 1 成功',
  `login_time` DATETIME DEFAULT NULL COMMENT '登录时间',
  `login_ip` VARCHAR (150) COLLATE utf8_bin DEFAULT NULL COMMENT '登录ip地址',
  `os_type` DOUBLE DEFAULT NULL COMMENT ' 来源（1pc2ios3安卓4其他）',
  `version_code` DOUBLE DEFAULT NULL COMMENT '版本编号',
  `version_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '版本名称',
  `platformid` DOUBLE DEFAULT NULL COMMENT '平台id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_gp_tbl_login_track` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户登录日志表' ;

-- ----------------------------
-- table structure for gp_tbl_lyb_record
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_lyb_record` ;

CREATE TABLE `gp_tbl_lyb_record` (
  `opposite_lyborderid` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '(返点记录)对应哪条充值记录',
  `currency` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '货币',
  `orderid` VARCHAR (50) COLLATE utf8_bin NOT NULL COMMENT '流水号 pk',
  `user_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户唯一标识',
  `lyb` DOUBLE DEFAULT NULL COMMENT 'lyb数量',
  `before_cash` DOUBLE DEFAULT NULL COMMENT '变动前账户',
  `after_cash` DOUBLE DEFAULT NULL COMMENT '变动后账户',
  `change_type` DOUBLE DEFAULT NULL COMMENT '变动记录类型(1充值，2兑换  3充值到游戏)',
  `record_time` DATETIME DEFAULT NULL COMMENT '记录时间',
  `opposite_orderid` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '对方平台订单号',
  `opposite_plateformid` DOUBLE DEFAULT NULL COMMENT '平台id',
  `oppositeinfo` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '资金（币）变动(0流进1支出)',
  `trade_type` DOUBLE DEFAULT NULL COMMENT '资金（币）变动(0流进1支出)',
  `remark` VARCHAR (200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `terminals_type` DOUBLE DEFAULT NULL COMMENT '操作终端(1pc2ios3安卓4其他)',
  `gamegold` DOUBLE DEFAULT NULL COMMENT '游戏币',
  `rate` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '每次交易汇率记录',
  `lyb_to_yxb_rate` DOUBLE DEFAULT NULL COMMENT '乐盈币-游戏币兑率',
  `cash_to_lyb_rate` DOUBLE DEFAULT NULL COMMENT '现金-乐盈币兑率',
  `son_plateformid` DOUBLE DEFAULT NULL COMMENT '具体平台下面的区服',
  PRIMARY KEY (`orderid`),
  UNIQUE KEY `pk_gp_tbl_lyb_record` (`orderid`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '会员中心资金(乐盈币)变动记录表' ;

-- ----------------------------
-- table structure for gp_tbl_pay_bussiness_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_pay_bussiness_info` ;

CREATE TABLE `gp_tbl_pay_bussiness_info` (
  `id` DOUBLE NOT NULL COMMENT '主键',
  `app_id` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '请求支付的应用id',
  `app_name` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '请求支付的应用名称',
  `buyer_id` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '买家唯一标识id',
  `out_trade_no` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '商户网站唯一订单号',
  `trade_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `trade_no` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '第三方交易号',
  `trade_status` CHAR(1) COLLATE utf8_bin DEFAULT NULL COMMENT '交易状态',
  `trade_type` CHAR(1) COLLATE utf8_bin DEFAULT NULL COMMENT '交易类型',
  `order_amount` DOUBLE DEFAULT NULL COMMENT '订单金额',
  `total_fee` DOUBLE DEFAULT NULL COMMENT '交易金额',
  `seller_privilege` DOUBLE DEFAULT NULL COMMENT '商家优惠金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '交易创建时间',
  `pay_time` DATETIME DEFAULT NULL COMMENT '支付平台返回的支付时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `payment_type` CHAR(2) COLLATE utf8_bin DEFAULT NULL COMMENT '支付类型',
  `payment_platform` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '支付平台id',
  `business_no` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '支付交易流水号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_pay_bussiness_info` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '支付流水表' ;

-- ----------------------------
-- table structure for gp_tbl_platform_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_platform_info` ;

CREATE TABLE `gp_tbl_platform_info` (
  `country` DOUBLE DEFAULT NULL COMMENT '国家标识（0中国 1美国 2泰国 3越南 4韩国 5印尼 6繁体 7新加坡 8马来西亚）',
  `platform_terminal` DOUBLE DEFAULT NULL COMMENT '平台类型（1.pc 2.安卓 3ios 4.h5  5.其他）',
  `country_currency` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '货币',
  `is_oneself` DOUBLE DEFAULT NULL COMMENT '0.我们自己平台  1.外部平台',
  `id` DOUBLE NOT NULL COMMENT '平台标识',
  `name` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '平台名称',
  `type` DOUBLE DEFAULT NULL COMMENT '平台类型',
  `is_game` DOUBLE DEFAULT NULL COMMENT '是否是游戏(0 不是 1 是)',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `developers` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '开发商',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_platform_info` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '平台信息表' ;

-- ----------------------------
-- table structure for gp_tbl_recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_recharge_record` ;

CREATE TABLE `gp_tbl_recharge_record` (
  `currency` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '币种名称',
  `orderno` VARCHAR (64) COLLATE utf8_bin NOT NULL COMMENT '充值订单号 pk',
  `user_id` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '用户唯一标识',
  `apply_amount` DOUBLE DEFAULT NULL COMMENT '申请充值金额',
  `real_lyb` DOUBLE DEFAULT NULL COMMENT '兑换(用乐盈币支付)时候需传值，其他走第三方 充值时候 （关系真正钱时候） 不用传值',
  `status` DOUBLE DEFAULT NULL COMMENT '第三方充值通知状态(0：处理中1：成功2：失败 3.已关闭)',
  `platform_type` DOUBLE DEFAULT NULL COMMENT '充值渠道标识 1：微信支付 2：支付宝 3：乐盈币兑换 4：银联充值 5：优贝支付6：cc电子钱包 7：cc点卡 9：mol电子钱包 10：mol点卡 11：越南cardchargepay 13 :支付宝, 14:微信 16: 快捷支付 17: 网银支付 18: 易游酷点卡支付',
  `apply_time` DATETIME DEFAULT NULL COMMENT '充值申请时间',
  `handle_time` DATETIME DEFAULT NULL COMMENT '处理时间',
  `platform_order` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '充值平台订单号',
  `handle_type` DOUBLE DEFAULT NULL COMMENT '充值订单处理方式 1, 在线充值 2系统自动充值',
  `source` DOUBLE DEFAULT NULL COMMENT '操作终端（1pc2ios3安卓4其他）',
  `remark` VARCHAR (256) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `operatorid` DOUBLE DEFAULT NULL COMMENT '充值操作人',
  `payment_orderno` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '业务订单号',
  `notify_status` DOUBLE DEFAULT NULL COMMENT '第三方充值成功后通知我们后的 个业务处理操作状态(0：处理中1：成功2：失败)',
  `platform_source` DOUBLE DEFAULT NULL COMMENT '平台来源',
  `change_type` DOUBLE DEFAULT NULL COMMENT '交易类型1.充值,2.兑换(用乐盈币支付),3.充值到子平台,4.系统充值（后台手工充值）5.系统扣除乐盈币 6.转入 7.转出  8.转账',
  `plateform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `son_plateformid` DOUBLE DEFAULT NULL COMMENT '具体平台下面的区服',
  PRIMARY KEY (`orderno`),
  UNIQUE KEY `pk_recharge_record` (`orderno`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '充值记录表' ;

-- ----------------------------
-- table structure for gp_tbl_toplate_errortrade
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_toplate_errortrade` ;

CREATE TABLE `gp_tbl_toplate_errortrade` (
  `order_id` VARCHAR (256) COLLATE utf8_bin DEFAULT NULL COMMENT '对应资金变动表id',
  `user_id` VARCHAR (256) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `creattime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `status` DOUBLE DEFAULT NULL COMMENT '0未处理 1已处理  2 已关闭  3再次处理还是异常',
  `remark` VARCHAR (256) COLLATE utf8_bin DEFAULT NULL COMMENT '标记',
  `sumgamegold` DOUBLE DEFAULT NULL COMMENT '需通知平台的总游戏币',
  `plate_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `hand_time` DATETIME DEFAULT NULL COMMENT '处理时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '异常交易日志表' ;

-- ----------------------------
-- table structure for gp_tbl_user_info
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_user_info` ;

CREATE TABLE `gp_tbl_user_info` (
  `channel_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `user_id` VARCHAR (50) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `email` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `phone` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `head_icon` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `nickname` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `sex` DOUBLE DEFAULT NULL COMMENT '性别 1男 2女',
  `age` DOUBLE DEFAULT NULL COMMENT '年龄',
  `location` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '所在地',
  `status` DOUBLE DEFAULT NULL COMMENT '用户状态 0 禁用 1 未激活 2 已激活',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `last_login_time` DATETIME DEFAULT NULL COMMENT '最后一次登录时间',
  `real_name` VARCHAR (20) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `id_card_no` VARCHAR (20) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号码',
  `address` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '详细地址',
  `qq_number` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号码',
  `phone_status` DOUBLE DEFAULT NULL COMMENT '手机绑定状态（0未有 1绑定）',
  `email_status` DOUBLE DEFAULT NULL COMMENT '邮箱绑定状态（0未有 1绑定）',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `reg_type` DOUBLE DEFAULT NULL COMMENT '注册类型 1 用户名 2 手机 3 邮箱 4 第三方登录',
  `paypassword` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '支付密码',
  `os_type` DOUBLE DEFAULT NULL COMMENT '来源（1pc2ios3安卓4其他）',
  `version_code` DOUBLE DEFAULT NULL COMMENT '版本编号',
  `version_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '版本名称',
  `head_icon_small` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '图像小图',
  `login_type` VARCHAR (2) COLLATE utf8_bin DEFAULT NULL COMMENT '登录类型 0：平台注册登录；1：qq注册登录；2：新浪微博注册登录；3：微信注册登录；4：支付宝注册登录；5：twitter注册登录；6：facebook注册登录；',
  `forbidden_starttime` DATETIME DEFAULT NULL COMMENT '禁用开始时间',
  `forbidden_enttime` DATETIME DEFAULT NULL COMMENT '禁用结束时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `pk_gp_tbl_user_info` (`user_id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ;

-- ----------------------------
-- table structure for gp_tbl_user_recharge_daily
-- ----------------------------
DROP TABLE IF EXISTS `gp_tbl_user_recharge_daily` ;

CREATE TABLE `gp_tbl_user_recharge_daily` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台标识',
  `server_id` DOUBLE DEFAULT NULL COMMENT '所属服',
  `area_id` DOUBLE DEFAULT NULL COMMENT '所属区',
  `user_id` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `total_amount` DOUBLE DEFAULT NULL COMMENT '充值总金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `currency` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '币种',
  UNIQUE KEY `user_recharge_daily_uk` (
    `stat_date`,
    `currency`,
    `user_id`,
    `area_id`,
    `server_id`,
    `platform_id`
  )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户充值日报表' ;

-- ----------------------------
-- table structure for tbl_admin
-- ----------------------------
DROP TABLE IF EXISTS `tbl_admin` ;

CREATE TABLE `tbl_admin` (
  `id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` DOUBLE DEFAULT NULL COMMENT '用户id',
  `name` VARCHAR (80) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `email` VARCHAR (200) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `tel` VARCHAR (60) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `depart_id` DOUBLE DEFAULT NULL COMMENT '部门id(废弃)',
  `job_no` VARCHAR (60) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  `type` DOUBLE DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_admin_pk` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 246 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '【已废弃】' ;

-- ----------------------------
-- table structure for tbl_channel_platform
-- ----------------------------
DROP TABLE IF EXISTS `tbl_channel_platform` ;

CREATE TABLE `tbl_channel_platform` (
  `channel_id` VARCHAR (10) COLLATE utf8_bin NOT NULL COMMENT '渠道id',
  `platform_id` DOUBLE NOT NULL COMMENT '平台id',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`channel_id`, `platform_id`),
  UNIQUE KEY `pk_tbl_channel_platform` (`channel_id`, `platform_id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '渠道平台推广关系表' ;

-- ----------------------------
-- table structure for tbl_cperation_channel_stat
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cperation_channel_stat` ;

CREATE TABLE `tbl_cperation_channel_stat` (
  `dir_code` VARCHAR (50) COLLATE utf8_bin NOT NULL COMMENT '目录代码',
  `stat_date` DATETIME NOT NULL COMMENT '统计日期',
  `channel_id` VARCHAR (100) COLLATE utf8_bin NOT NULL COMMENT '渠道id',
  `platform_id` DOUBLE NOT NULL COMMENT '平台id',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `pay_ucnt` DOUBLE DEFAULT NULL COMMENT '付费用户数',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '付费金额',
  `consume_amount` DOUBLE DEFAULT NULL COMMENT '消费余额',
  `account_balance` DOUBLE DEFAULT NULL COMMENT '账户余额',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (
    `dir_code`,
    `platform_id`,
    `channel_id`,
    `stat_date`
  ),
  UNIQUE KEY `sys_c0013321` (
    `dir_code`,
    `platform_id`,
    `channel_id`,
    `stat_date`
  )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '市场合作渠道数据统计' ;

-- ----------------------------
-- table structure for tbl_dim_game_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dim_game_type` ;

CREATE TABLE `tbl_dim_game_type` (
  `id` DOUBLE NOT NULL COMMENT '主键id',
  `platform_type` DOUBLE DEFAULT NULL COMMENT '平台类型',
  `game_type_name` VARCHAR (60) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏类型',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `update_user` VARCHAR (32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `country` DOUBLE DEFAULT NULL COMMENT '0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `insert_time` DATETIME DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0013427` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏类型' ;

-- ----------------------------
-- table structure for tbl_dim_game_type_con
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dim_game_type_con` ;

CREATE TABLE `tbl_dim_game_type_con` (
  `id` DOUBLE NOT NULL COMMENT '主键id',
  `game_type_id` DOUBLE DEFAULT NULL COMMENT '游戏类型id',
  `game_id` DOUBLE DEFAULT NULL COMMENT '游戏id',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `insert_time` DATETIME DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0013426` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏类型与游戏关联表' ;

-- ----------------------------
-- table structure for tbl_dim_partner_member_con
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dim_partner_member_con` ;

CREATE TABLE `tbl_dim_partner_member_con` (
  `referrer_website_url` VARCHAR (512) COLLATE utf8_bin DEFAULT NULL COMMENT '来源网址',
  `promote_url` VARCHAR (512) COLLATE utf8_bin DEFAULT NULL COMMENT '推广链接',
  `member_id` VARCHAR (50) COLLATE utf8_bin NOT NULL COMMENT '会员的userid',
  `partner_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴的userid',
  `partner_no` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴的代理编码',
  `partner_name` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴的真实姓名',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `sys_c0012451` (`member_id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '合作伙伴所属直属会员关联关系表' ;

-- ----------------------------
-- table structure for tbl_dim_proxy_proportion
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dim_proxy_proportion` ;

CREATE TABLE `tbl_dim_proxy_proportion` (
  `id` DOUBLE NOT NULL COMMENT 'id',
  `level_code` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '层级代码',
  `level_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '层级名称',
  `proportion` DOUBLE DEFAULT NULL COMMENT '返佣比例',
  `create_user` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_user` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `effect_time` VARCHAR (30) COLLATE utf8_bin DEFAULT NULL COMMENT '生效时间',
  `str2` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段2',
  `str3` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段3',
  `country` DOUBLE DEFAULT NULL COMMENT '0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `insert_time` DATETIME DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0013430` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '代理返佣佣金比例' ;

-- ----------------------------
-- table structure for tbl_dim_qualified_member_conf
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dim_qualified_member_conf` ;

CREATE TABLE `tbl_dim_qualified_member_conf` (
  `id` DOUBLE NOT NULL COMMENT '主键id',
  `qualified_type` DOUBLE DEFAULT NULL COMMENT '结算类型：0充值金额 1 有效投注数 2投注总额',
  `amount` DOUBLE DEFAULT NULL COMMENT '数量',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_user` VARCHAR (32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人',
  `game_type_id` DOUBLE DEFAULT NULL COMMENT '游戏类型',
  `record_id` DOUBLE DEFAULT NULL COMMENT '记录id',
  `is_new` DOUBLE DEFAULT NULL COMMENT '是否是最新的记录（0：是最新的，1：不是最新的）',
  `valid_time` DATETIME DEFAULT NULL COMMENT '有效时间',
  `invalid_time` DATETIME DEFAULT NULL COMMENT '失效时间',
  `country` DOUBLE DEFAULT NULL COMMENT '0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `insert_time` DATETIME DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0013428` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '合格会员配置' ;

-- ----------------------------
-- table structure for tbl_dim_rack_back
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dim_rack_back` ;

CREATE TABLE `tbl_dim_rack_back` (
  `insert_time` DATETIME DEFAULT NULL COMMENT '入库时间',
  `id` DOUBLE NOT NULL COMMENT '主键id',
  `game_type_id` DOUBLE DEFAULT NULL COMMENT '游戏类型id',
  `rack_back_model` DOUBLE DEFAULT NULL COMMENT '返佣模式',
  `price_more_then` DOUBLE DEFAULT NULL COMMENT '最小值',
  `price_less_then` DOUBLE DEFAULT NULL COMMENT '最大值',
  `rack_back_percent` DOUBLE DEFAULT NULL COMMENT '返佣比例',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `rack_level` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '佣金等级',
  `member_more_then` DOUBLE DEFAULT NULL COMMENT '最小值',
  `member_less_then` DOUBLE DEFAULT NULL COMMENT '最大值',
  `record_id` DOUBLE DEFAULT NULL COMMENT '记录id',
  `is_new` DOUBLE DEFAULT NULL COMMENT '是否是最新的记录（0：是最新的，1：不是最新的）',
  `valid_time` DATETIME DEFAULT NULL COMMENT '生效时间',
  `invalid_time` DATETIME DEFAULT NULL COMMENT '失效时间',
  `country` DOUBLE DEFAULT NULL COMMENT '0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0013429` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '直属会员返佣比例配置' ;

-- ----------------------------
-- table structure for tbl_dim_user_settlement
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dim_user_settlement` ;

CREATE TABLE `tbl_dim_user_settlement` (
  `id` DOUBLE NOT NULL COMMENT '主键id',
  `user_id` VARCHAR (32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `game_type_id` DOUBLE DEFAULT NULL COMMENT '游戏类型id',
  `commission_type` DOUBLE DEFAULT NULL COMMENT '佣金结算方式 1按输赢总额 2按有效金额 3按充值总额。',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `is_new` DOUBLE DEFAULT NULL COMMENT '是否是最新的记录（0：是最新的，1：不是最新的）',
  `valid_time` DATETIME DEFAULT NULL COMMENT '生效时间',
  `invalid_time` DATETIME DEFAULT NULL COMMENT '失效时间',
  `record_id` DOUBLE DEFAULT NULL COMMENT '记录唯一标识',
  `insert_time` DATETIME DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0013425` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '代理的佣金结算方式' ;

-- ----------------------------
-- table structure for tbl_dm_inner_game_chnl_opt_day
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dm_inner_game_chnl_opt_day` ;

CREATE TABLE `tbl_dm_inner_game_chnl_opt_day` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `channel_id` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `recharge_ucnt` DOUBLE DEFAULT NULL COMMENT '充值用户数',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '内部游戏渠道运营日表' ;

-- ----------------------------
-- table structure for tbl_dm_inner_game_chnl_opt_h
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dm_inner_game_chnl_opt_h` ;

CREATE TABLE `tbl_dm_inner_game_chnl_opt_h` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `channel_id` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `recharge_ucnt` DOUBLE DEFAULT NULL COMMENT '充值用户数',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '内部游戏渠道运营小时表' ;

-- ----------------------------
-- table structure for tbl_dm_partner_mem_bla_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dm_partner_mem_bla_detail` ;

CREATE TABLE `tbl_dm_partner_mem_bla_detail` (
  `month_id` DATETIME DEFAULT NULL COMMENT '月份',
  `partner_user_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴用户id',
  `partner_no` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '推广编码',
  `higher_partner_no` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '上级代理编码',
  `game_type_id` DOUBLE DEFAULT NULL COMMENT '游戏类型id',
  `qual_member_mon_ucnt` DOUBLE DEFAULT NULL COMMENT '本月合格会员数',
  `qual_member_total_ucnt` DOUBLE DEFAULT NULL COMMENT '合格会员数',
  `rack_back_model` DOUBLE DEFAULT NULL COMMENT '返佣模式',
  `commission_level` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '佣金结算等级',
  `commission_percent` DOUBLE DEFAULT NULL COMMENT '佣金结算比例',
  `commission_amount` DOUBLE DEFAULT NULL COMMENT '佣金结算金额',
  `activity_amount` DOUBLE DEFAULT NULL COMMENT '活动金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '合作伙伴会员对账统计明细' ;

-- ----------------------------
-- table structure for tbl_dm_partner_member_bla_stat
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dm_partner_member_bla_stat` ;

CREATE TABLE `tbl_dm_partner_member_bla_stat` (
  `month_id` DATETIME DEFAULT NULL COMMENT '月份',
  `partner_user_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴用户id',
  `partner_level` DOUBLE DEFAULT NULL COMMENT '代理等级',
  `partner_no` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '代理编码',
  `higher_partner_no` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '上级代理编码',
  `qual_member_mon_ucnt` DOUBLE DEFAULT NULL COMMENT '本月合格会员数',
  `qual_member_total_ucnt` DOUBLE DEFAULT NULL COMMENT '合格会员数',
  `sub_agent_ucnt` DOUBLE DEFAULT NULL COMMENT '下级代理数',
  `member_amount` DOUBLE DEFAULT NULL COMMENT '会员佣金',
  `agent_amount` DOUBLE DEFAULT NULL COMMENT '代理佣金',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '合作伙伴会员对账统计' ;

-- ----------------------------
-- table structure for tbl_dm_user_active_remain_day
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dm_user_active_remain_day` ;

CREATE TABLE `tbl_dm_user_active_remain_day` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `channel_id` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
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
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户活跃留存日报表' ;

-- ----------------------------
-- table structure for tbl_dw_mem_act_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dw_mem_act_log` ;

CREATE TABLE `tbl_dw_mem_act_log` (
  `user_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `game_id` DOUBLE DEFAULT NULL COMMENT '游戏id',
  `action_id` DOUBLE DEFAULT NULL COMMENT '行为id(1:充值 2：投注 3：送礼 4:活动)',
  `action_amount` DOUBLE DEFAULT NULL COMMENT '行为金额',
  `action_num` DOUBLE DEFAULT NULL COMMENT '行为数量（一般为0，送礼有礼物数量,投注时则未输赢金额）',
  `action_time` DATETIME DEFAULT NULL COMMENT '行为时间',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '会员行为日志' ;

-- ----------------------------
-- table structure for tbl_dw_member_qual_list_mon
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dw_member_qual_list_mon` ;

CREATE TABLE `tbl_dw_member_qual_list_mon` (
  `month_id` DATETIME DEFAULT NULL COMMENT '月份',
  `partner_user_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '合作伙伴用户id',
  `partner_no` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '推广编码',
  `higher_partner_no` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '上级代理编码',
  `user_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `game_type_id` DOUBLE DEFAULT NULL COMMENT '游戏类型id',
  `source_month_id` DATETIME DEFAULT NULL COMMENT '来源月份（用户来源月已达到合格会员标准，但是用户代理未分成）',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `commission_type` DOUBLE DEFAULT NULL
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '合格会员月清单' ;

-- ----------------------------
-- table structure for tbl_edm_lmdz_betting_month
-- ----------------------------
DROP TABLE IF EXISTS `tbl_edm_lmdz_betting_month` ;

CREATE TABLE `tbl_edm_lmdz_betting_month` (
  `settle_month` DATETIME DEFAULT NULL COMMENT '结算月份',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `user_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `betting_times` DOUBLE DEFAULT NULL COMMENT '投注次数',
  `income_acc_sum` DOUBLE DEFAULT NULL COMMENT '收益金额汇总',
  `bet_acc_sum` DOUBLE DEFAULT NULL COMMENT '投注金额'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '撩妹德州投注汇总月表' ;

-- ----------------------------
-- table structure for tbl_edm_lydj_betting_month
-- ----------------------------
DROP TABLE IF EXISTS `tbl_edm_lydj_betting_month` ;

CREATE TABLE `tbl_edm_lydj_betting_month` (
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `user_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `settle_month` DATETIME DEFAULT NULL COMMENT '结算月份',
  `betting_times` DOUBLE DEFAULT NULL COMMENT '投注次数',
  `settle_acc_sum` DOUBLE DEFAULT NULL COMMENT '结算金额 ',
  `bet_acc_sum` DOUBLE DEFAULT NULL COMMENT '投注金额'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '乐盈电竞投注汇总月表' ;

-- ----------------------------
-- table structure for tbl_function
-- ----------------------------
DROP TABLE IF EXISTS `tbl_function` ;

CREATE TABLE `tbl_function` (
  `id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` DOUBLE DEFAULT NULL COMMENT '父功能点id',
  `name` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '功能名称',
  `function_index` DOUBLE DEFAULT NULL COMMENT '排序索引',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_function_pk` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 421 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '功能点表' ;

-- ----------------------------
-- table structure for tbl_game_consume_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_game_consume_log` ;

CREATE TABLE `tbl_game_consume_log` (
  `dir_code` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '目录代码',
  `user_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `consume_amount` DOUBLE DEFAULT NULL COMMENT '账户余额',
  `consume_type` DOUBLE DEFAULT NULL COMMENT '消费类型',
  `consume_date` DATETIME DEFAULT NULL COMMENT '消费日期',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建日期'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '账户消费流水表' ;

-- ----------------------------
-- table structure for tbl_game_user_account
-- ----------------------------
DROP TABLE IF EXISTS `tbl_game_user_account` ;

CREATE TABLE `tbl_game_user_account` (
  `dir_code` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '目录代码',
  `user_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `account_balance` DOUBLE DEFAULT NULL COMMENT '账户余额',
  `last_update_date` DATETIME DEFAULT NULL COMMENT '最后更新日期',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建日期'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户账户表' ;

-- ----------------------------
-- table structure for tbl_gp_country_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_country_order` ;

CREATE TABLE `tbl_gp_country_order` (
  `country` DOUBLE NOT NULL COMMENT '国家id',
  `user_count` DOUBLE DEFAULT NULL COMMENT '充值用户数',
  `recharge_count` DOUBLE DEFAULT NULL COMMENT '充值次数',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额 ',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`country`, `create_time`),
  UNIQUE KEY `pk_tbl_gp_country_order` (`country`, `create_time`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '各国会员充值汇总表' ;

-- ----------------------------
-- table structure for tbl_gp_country_plateform
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_country_plateform` ;

CREATE TABLE `tbl_gp_country_plateform` (
  `platform_terminal` DOUBLE DEFAULT NULL COMMENT '平台类型（1.pc 2.安卓 3ios 4.h5  5.其他）',
  `platform_id` DOUBLE NOT NULL COMMENT '游戏平台id',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额',
  `user_count` DOUBLE DEFAULT NULL COMMENT '充值用户数',
  `recharge_count` DOUBLE DEFAULT NULL COMMENT '充值次数 ',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `country` DOUBLE NOT NULL COMMENT '国家id',
  PRIMARY KEY (
    `country`,
    `create_time`,
    `platform_id`
  ),
  UNIQUE KEY `pk_tbl_gp_country_plateform` (
    `country`,
    `create_time`,
    `platform_id`
  )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '各国平台充值汇总表' ;

-- ----------------------------
-- table structure for tbl_gp_dim_change_type_market
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_dim_change_type_market` ;

CREATE TABLE `tbl_gp_dim_change_type_market` (
  `change_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '交易类型  pay_status',
  `change_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '交易类型描述'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '交易类型维表 取自recharge_record.change_type 交易类型1.充值,2.兑换(用乐盈币支付),3.充值到子平台,4.系统充值（后台手工充值）5.系统扣除乐盈币 6.转入 7.转出  8.转账' ;

-- ----------------------------
-- table structure for tbl_gp_dim_order_status_market
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_dim_order_status_market` ;

CREATE TABLE `tbl_gp_dim_order_status_market` (
  `status_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号 关联视图的订单状态 pay_status',
  `status_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单类型'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '订单状态维表 取自recharge_record.status 第三方充值通知状态(0：处理中1：成功2：失败 3.已关闭)' ;

-- ----------------------------
-- table structure for tbl_gp_dim_recharge_way_market
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_dim_recharge_way_market` ;

CREATE TABLE `tbl_gp_dim_recharge_way_market` (
  `recharge_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '充值渠道标识。关联recharge_record.platform_type',
  `recharge_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '充值渠道描述'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '充值方式维表 取自recharge_record.platform_type充值方式 充值渠道标识1：微信支付  2：支付宝 3：乐盈币兑换  4：银联充值 5：优贝支付6：cc电子钱包  7：cc点卡 9：mol电子钱包  10：mol点卡 11：越南cardchargepay' ;

-- ----------------------------
-- table structure for tbl_gp_dm_xh_sy_day
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_dm_xh_sy_day` ;

CREATE TABLE `tbl_gp_dm_xh_sy_day` (
  `stat_date` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '统计日期',
  `channel_id` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id=产品',
  `xh_amount` DOUBLE DEFAULT NULL COMMENT '累积消耗金额',
  `fc_rate` DOUBLE DEFAULT NULL COMMENT '分成比例',
  `platform_income` DOUBLE DEFAULT NULL COMMENT '平台收益',
  `fc_income` DOUBLE DEFAULT NULL COMMENT '分成收益',
  `platform_terminal` DOUBLE DEFAULT NULL COMMENT '终端=所属平台'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏消耗收益日报表' ;

-- ----------------------------
-- table structure for tbl_gp_exchangerate_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_exchangerate_config` ;

CREATE TABLE `tbl_gp_exchangerate_config` (
  `id` DOUBLE NOT NULL COMMENT '主键id',
  `country` DOUBLE DEFAULT NULL COMMENT '国家id  （0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来西亚)',
  `lyb_value` DOUBLE DEFAULT NULL COMMENT '乐盈币数值',
  `currency_value` DOUBLE DEFAULT NULL COMMENT '被兑换的货币数值',
  `rate` DOUBLE DEFAULT NULL COMMENT '最后算的汇率（lyb_value/currency_value）',
  `currency` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '币种名称',
  `lastupdate_time` DATETIME DEFAULT NULL COMMENT '上次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0013304` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '运营游戏各国汇率配置表' ;

-- ----------------------------
-- table structure for tbl_gp_game_daily
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_game_daily` ;

CREATE TABLE `tbl_gp_game_daily` (
  `data_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `game_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏名称',
  `game_recharge` DOUBLE DEFAULT NULL COMMENT '游戏充值',
  `lyb_recharge` DOUBLE DEFAULT NULL COMMENT '乐盈币充值',
  `yx_exchange` DOUBLE DEFAULT NULL COMMENT '游戏划账 是指从a游戏划账到b游戏（统计到账游戏b)',
  `all_recharge` DOUBLE DEFAULT NULL COMMENT '总充值 游戏充值+乐盈币充值+游戏划账',
  `recharge_people` DOUBLE DEFAULT NULL COMMENT '充值人数',
  `recharge_times` DOUBLE DEFAULT NULL COMMENT '充值次数',
  `new_register` DOUBLE DEFAULT NULL COMMENT '新增注册'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏日报表' ;

-- ----------------------------
-- table structure for tbl_gp_game_daily_sum
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_game_daily_sum` ;

CREATE TABLE `tbl_gp_game_daily_sum` (
  `data_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `game_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏名称',
  `game_recharge` DOUBLE DEFAULT NULL COMMENT '游戏充值',
  `lyb_recharge` DOUBLE DEFAULT NULL COMMENT '乐盈币充值',
  `yx_exchange` DOUBLE DEFAULT NULL COMMENT '游戏划账 是指从a游戏划账到b游戏（统计到账游戏b)',
  `all_recharge` DOUBLE DEFAULT NULL COMMENT '总充值 游戏充值+乐盈币充值+游戏划账',
  `recharge_people` DOUBLE DEFAULT NULL COMMENT '充值人数',
  `recharge_times` DOUBLE DEFAULT NULL COMMENT '充值次数',
  `new_register` DOUBLE DEFAULT NULL COMMENT '新增注册',
  `arpu` DOUBLE DEFAULT NULL COMMENT 'arpu 总充值/dau',
  `dau` DOUBLE DEFAULT NULL COMMENT '今日登录过的用户 去重',
  `wau` DOUBLE DEFAULT NULL COMMENT '统计当天向前推7天，周登录过一次的用户，去重',
  `mau` DOUBLE DEFAULT NULL COMMENT '统计当天向前推30天，月内登录过一次的用户，去重',
  `remain_rate` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '留存率 昨日新增第二日登录用户数/昨日新增注册*100%',
  `recharge_rate` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '付费率 该日付费用户数量/该日登录用户*100% ',
  `remain_users` DOUBLE DEFAULT NULL COMMENT '留存用户 dau-当日新增注册用户'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏汇总日报表' ;

-- ----------------------------
-- table structure for tbl_gp_game_reatime_report
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_game_reatime_report` ;

CREATE TABLE `tbl_gp_game_reatime_report` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `game_id` DOUBLE DEFAULT NULL COMMENT '游戏id',
  `game_name` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏名称',
  `game_recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额',
  `lyb_recharge_amount` DOUBLE DEFAULT NULL COMMENT '乐盈币充值金额',
  `remit_amount` DOUBLE DEFAULT NULL COMMENT '游戏兑换金额',
  `recharge_ucnt` DOUBLE DEFAULT NULL COMMENT '充值人数',
  `recharge_cnt` DOUBLE DEFAULT NULL COMMENT '充值次数',
  `total_recharge_amount` DOUBLE DEFAULT NULL COMMENT '总充值金额',
  `game_dau` DOUBLE DEFAULT NULL COMMENT '游戏dau',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏实时报表' ;

-- ----------------------------
-- table structure for tbl_gp_gamegold_to_lyq
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_gamegold_to_lyq` ;

CREATE TABLE `tbl_gp_gamegold_to_lyq` (
  `order_no` VARCHAR (50) COLLATE utf8_bin NOT NULL COMMENT '流水号 pk',
  `user_id` VARCHAR (150) COLLATE utf8_bin DEFAULT NULL COMMENT ' 用户id',
  `before_lyqs` DOUBLE DEFAULT NULL COMMENT '用户划乐盈券之前余额',
  `f_platform_id` DOUBLE DEFAULT NULL COMMENT '划出平台id',
  `f_gamegolds` DOUBLE DEFAULT NULL COMMENT '划出游戏币数量',
  `t_lyqs` DOUBLE DEFAULT NULL COMMENT '划入到账乐盈券数量',
  `f_rate` DOUBLE DEFAULT NULL COMMENT ' 划出方汇率',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `terminals_type` DOUBLE DEFAULT NULL COMMENT '操作终端(1:pc,2:ios 3:安卓 4:h5, 5其他)',
  `source_id` DOUBLE DEFAULT NULL COMMENT ' 从那个哪个平台id 跳转过来的',
  `status` DOUBLE DEFAULT NULL COMMENT '状态(0失败 1成功 2处理中)',
  `remark` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_no`),
  UNIQUE KEY `sys_c0014547` (`order_no`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏币划乐盈券记录表' ;

-- ----------------------------
-- table structure for tbl_gp_gamegold_transrecord
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_gamegold_transrecord` ;

CREATE TABLE `tbl_gp_gamegold_transrecord` (
  `order_no` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号 pk',
  `user_id` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
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
  `remark` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `ggtt_orderno` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '转出方订单id',
  `ggtf_orderno` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '转入方订单id',
  `t_service_id` DOUBLE DEFAULT NULL COMMENT '具体平台下面的服id'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '游戏币互划账记录表' ;

-- ----------------------------
-- table structure for tbl_gp_lyq_record
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_lyq_record` ;

CREATE TABLE `tbl_gp_lyq_record` (
  `orderid` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号 pk',
  `user_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户唯一标识',
  `lyq` DOUBLE DEFAULT NULL COMMENT '乐盈券数量',
  `before_lyq` DOUBLE DEFAULT NULL COMMENT '变动前账户',
  `after_lyq` DOUBLE DEFAULT NULL COMMENT '变动后账户',
  `change_type` DOUBLE DEFAULT NULL COMMENT '资金（币）变动(0流进1支出)',
  `record_time` DATETIME DEFAULT NULL COMMENT '记录时间',
  `opposite_orderid` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '对方平台订单号',
  `opposite_plateformid` DOUBLE DEFAULT NULL COMMENT '对方平台id(对接的子系统)',
  `oppositeinfo` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '交易信息(抽奖 游戏  兑换等)',
  `trade_type` DOUBLE DEFAULT NULL COMMENT '资金（币）变动(0流进1支出)',
  `remark` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `terminals_type` DOUBLE DEFAULT NULL COMMENT '操作终端(1:pc,2:安卓3ios4其他)',
  `gamegold` DOUBLE DEFAULT NULL COMMENT '游戏币',
  `rate` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '每次交易汇率记录'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '会员中心资金(乐盈劵)变动记录表' ;

-- ----------------------------
-- table structure for tbl_gp_partner_account
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_partner_account` ;

CREATE TABLE `tbl_gp_partner_account` (
  `user_id` VARCHAR (128) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `partner_no` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '推广编码',
  `real_name` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '代理名称',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `higher_partner_no` VARCHAR (50) COLLATE utf8_bin DEFAULT NULL COMMENT '上级代理编码',
  `country` DOUBLE DEFAULT NULL COMMENT '0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `pk_gp_partner_account` (`user_id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '合作伙伴账号表' ;

-- ----------------------------
-- table structure for tbl_gp_partner_pv_uv_re_hourly
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_partner_pv_uv_re_hourly` ;

CREATE TABLE `tbl_gp_partner_pv_uv_re_hourly` (
  `stat_hour` DATETIME NOT NULL COMMENT '统计时间',
  `promote_url` VARCHAR (512) COLLATE utf8_bin NOT NULL COMMENT '推广链接',
  `pv_count` DOUBLE DEFAULT NULL COMMENT '页面访问量',
  `uv_count` DOUBLE DEFAULT NULL COMMENT '用户访问量',
  `regist_count` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `promote_code` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '推广代码',
  PRIMARY KEY (`stat_hour`, `promote_url`),
  UNIQUE KEY `pk_gp_partner_pv_uv_re_hourly` (`stat_hour`, `promote_url`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '代理推广链接来源报表' ;

-- ----------------------------
-- table structure for tbl_gp_partner_refsite_hourly
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_partner_refsite_hourly` ;

CREATE TABLE `tbl_gp_partner_refsite_hourly` (
  `stat_hour` DATETIME DEFAULT NULL COMMENT '统计时间',
  `referrer_website_url` VARCHAR (512) COLLATE utf8_bin DEFAULT NULL COMMENT '来源地址',
  `promote_url` VARCHAR (512) COLLATE utf8_bin DEFAULT NULL COMMENT '推广链接',
  `promote_code` VARCHAR (512) COLLATE utf8_bin DEFAULT NULL COMMENT '推广代码',
  `pv_count` DOUBLE DEFAULT NULL COMMENT '页面访问量',
  `uv_count` DOUBLE DEFAULT NULL COMMENT '用户访问量',
  `ip_count` DOUBLE DEFAULT NULL COMMENT 'ip数量',
  `total_duration` DOUBLE DEFAULT NULL COMMENT '访问总时长',
  `access_times` DOUBLE DEFAULT NULL COMMENT '访问次数',
  `one_page_access_times` DOUBLE DEFAULT NULL COMMENT '单页面访问次数',
  `regist_count` DOUBLE DEFAULT NULL COMMENT '注册用户数',
  `pay_count` DOUBLE DEFAULT NULL COMMENT '付费用户数',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin ;

-- ----------------------------
-- table structure for tbl_gp_plat_terminal_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_plat_terminal_info` ;

CREATE TABLE `tbl_gp_plat_terminal_info` (
  `id` DOUBLE NOT NULL COMMENT '平台类型（1.pc 2.安卓 3ios 4.h5  5.其他）',
  `name` VARCHAR (64) COLLATE utf8_bin DEFAULT NULL COMMENT '平台名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_tbl_gp_plat_terminal_info` (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '平台信息表' ;

-- ----------------------------
-- table structure for tbl_gp_platform_reatime_report
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_platform_reatime_report` ;

CREATE TABLE `tbl_gp_platform_reatime_report` (
  `stat_date` DATETIME DEFAULT NULL COMMENT '统计日期',
  `platform_terminal` DOUBLE DEFAULT NULL COMMENT '1.pc 2.安卓 3ios 4.h5  5.其他',
  `game_recharge_amount` DOUBLE DEFAULT NULL COMMENT '游戏充值金额',
  `lyb_recharge_amount` DOUBLE DEFAULT NULL COMMENT '乐盈币充值金额',
  `reback_recharge_amount` DOUBLE DEFAULT NULL COMMENT '返利充值金额',
  `total_recharge_amount` DOUBLE DEFAULT NULL COMMENT '总充值金额',
  `recharge_ucnt` DOUBLE DEFAULT NULL COMMENT '充值人数',
  `recharge_cnt` DOUBLE DEFAULT NULL COMMENT '充值次数',
  `game_dau` DOUBLE DEFAULT NULL COMMENT '游戏dau',
  `reg_ucnt` DOUBLE DEFAULT NULL COMMENT '新增注册',
  `platform_dau` DOUBLE DEFAULT NULL COMMENT '平台dau',
  `platform_sy_pv` DOUBLE DEFAULT NULL COMMENT '平台首页pv',
  `platform_sy_ip_cnt` DOUBLE DEFAULT NULL COMMENT '平台首页ip',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '平台实时报表' ;

-- ----------------------------
-- table structure for tbl_gp_user_funds
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gp_user_funds` ;

CREATE TABLE `tbl_gp_user_funds` (
  `user_id` VARCHAR (100) COLLATE utf8_bin NOT NULL COMMENT '用户id pk',
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
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户资金表' ;

-- ----------------------------
-- table structure for tbl_lmdz_channel_daily
-- ----------------------------
DROP TABLE IF EXISTS `tbl_lmdz_channel_daily` ;

CREATE TABLE `tbl_lmdz_channel_daily` (
  `platform_id` DOUBLE NOT NULL COMMENT '平台标识',
  `user_id` VARCHAR (64) COLLATE utf8_bin NOT NULL COMMENT '用户标识',
  `stat_date` DATETIME NOT NULL COMMENT '统计日期',
  `grade` VARCHAR (16) COLLATE utf8_bin DEFAULT NULL COMMENT '用户等级',
  `recharge_amount` DOUBLE DEFAULT NULL COMMENT '充值金额',
  PRIMARY KEY (
    `platform_id`,
    `user_id`,
    `stat_date`
  ),
  UNIQUE KEY `pk_tbl_lmdz_channel_daily` (
    `platform_id`,
    `user_id`,
    `stat_date`
  )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '撩妹德州渠道日报表' ;

-- ----------------------------
-- table structure for tbl_menu
-- ----------------------------
DROP TABLE IF EXISTS `tbl_menu` ;

CREATE TABLE `tbl_menu` (
  `id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission` VARCHAR (400) COLLATE utf8_bin DEFAULT NULL COMMENT '权限id',
  `parent_id` DOUBLE DEFAULT NULL COMMENT '父菜单id',
  `name` VARCHAR (400) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `url` VARCHAR (800) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单url',
  `menu_index` DOUBLE DEFAULT NULL COMMENT '排序索引',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_menu_pk` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 505 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ;

-- ----------------------------
-- table structure for tbl_ods_lmdz_activity_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ods_lmdz_activity_log` ;

CREATE TABLE `tbl_ods_lmdz_activity_log` (
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `activity_time` DATETIME DEFAULT NULL COMMENT '活动时间',
  `user_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `activity_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '活动id',
  `activity_amount` DOUBLE DEFAULT NULL COMMENT '活动金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '撩妹德州活动日志表' ;

-- ----------------------------
-- table structure for tbl_ods_lmdz_betting_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ods_lmdz_betting_log` ;

CREATE TABLE `tbl_ods_lmdz_betting_log` (
  `settle_time` DATETIME DEFAULT NULL COMMENT '结算时间',
  `user_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `bureauno` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '局号',
  `roomno` DOUBLE DEFAULT NULL COMMENT '房间号',
  `game_type` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏类型',
  `income_amount` DOUBLE DEFAULT NULL COMMENT '收益金额',
  `bet_amount` DOUBLE DEFAULT NULL COMMENT '投注金额',
  `board_result` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏结果',
  `card_type` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '牌型',
  `card` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '牌面',
  `create_time` DATETIME DEFAULT NULL
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '撩妹德州投注日志表' ;

-- ----------------------------
-- table structure for tbl_ods_lmdz_present_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ods_lmdz_present_log` ;

CREATE TABLE `tbl_ods_lmdz_present_log` (
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `user_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `present_time` DATETIME DEFAULT NULL COMMENT '送礼时间',
  `anchor_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '主播姓名',
  `present_name` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '礼物名称',
  `present_count` DOUBLE DEFAULT NULL COMMENT '礼物数量',
  `settle_amount` DOUBLE DEFAULT NULL COMMENT '结算金额',
  `anchor_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '主播id',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `type` DOUBLE DEFAULT NULL COMMENT '支付类型（1：金豆 2：金币）',
  `present_orderno` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '礼物单号'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '撩妹德州送礼日志表' ;

-- ----------------------------
-- table structure for tbl_ods_lydj_activity_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ods_lydj_activity_log` ;

CREATE TABLE `tbl_ods_lydj_activity_log` (
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `activity_time` DATETIME DEFAULT NULL COMMENT '活动时间',
  `user_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `activity_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '活动id',
  `activity_amount` DOUBLE DEFAULT NULL COMMENT '活动金额',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '乐盈电竞活动日志表' ;

-- ----------------------------
-- table structure for tbl_ods_lydj_betting_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ods_lydj_betting_log` ;

CREATE TABLE `tbl_ods_lydj_betting_log` (
  `platform_id` DOUBLE DEFAULT NULL COMMENT '平台id',
  `user_id` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `betting_time` DATETIME DEFAULT NULL COMMENT '投注时间',
  `settle_time` DATETIME DEFAULT NULL COMMENT '结算时间',
  `betting_amount` DOUBLE DEFAULT NULL COMMENT '投注金额',
  `betting_orderno` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '投注订单号',
  `currency` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '货币',
  `settle_amount` DOUBLE DEFAULT NULL COMMENT '结算金额 ',
  `create_time` DATETIME DEFAULT NULL
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '乐盈电竞投注日志表' ;

-- ----------------------------
-- table structure for tbl_permission
-- ----------------------------
DROP TABLE IF EXISTS `tbl_permission` ;

CREATE TABLE `tbl_permission` (
  `permission` VARCHAR (400) COLLATE utf8_bin DEFAULT NULL COMMENT '权限',
  `name` VARCHAR (400) COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `function_id` DOUBLE DEFAULT NULL COMMENT '功能id',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '权限表' ;

-- ----------------------------
-- table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role` ;

CREATE TABLE `tbl_role` (
  `id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR (128) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_role_pk` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 206 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ;

-- ----------------------------
-- table structure for tbl_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_permission` ;

CREATE TABLE `tbl_role_permission` (
  `id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` DOUBLE DEFAULT NULL COMMENT '角色id',
  `permission` VARCHAR (400) COLLATE utf8_bin DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_role_permission_pk` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1953 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '角色权限关系表' ;

-- ----------------------------
-- table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user` ;

CREATE TABLE `tbl_user` (
  `user_id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` VARCHAR (200) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `password` VARCHAR (200) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `user_status` VARCHAR (8) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态',
  `user_type` VARCHAR (8) COLLATE utf8_bin DEFAULT NULL COMMENT '用户类型',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `locale` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '语言（废弃）',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `tbl_user_pk` (`user_id`),
  UNIQUE KEY `tbl_user_uk1` (`username`)
) ENGINE = INNODB AUTO_INCREMENT = 246 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户基本信息表' ;

-- ----------------------------
-- table structure for tbl_user_channel
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_channel` ;

CREATE TABLE `tbl_user_channel` (
  `id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` DOUBLE DEFAULT NULL COMMENT '用户id',
  `channel_id` VARCHAR (10) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_c0012754` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 167 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户渠道关系表' ;

-- ----------------------------
-- table structure for tbl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_role` ;

CREATE TABLE `tbl_user_role` (
  `id` DOUBLE NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` DOUBLE DEFAULT NULL COMMENT '用户id',
  `role_id` DOUBLE DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tbl_user_role_pk` (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 422 DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户角色关系表' ;

-- ----------------------------
-- table structure for tbl_vip_pay_recorde_mark
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vip_pay_recorde_mark` ;

CREATE TABLE `tbl_vip_pay_recorde_mark` (
  `handle_date` DATETIME DEFAULT NULL COMMENT '处理日期',
  `user_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '付费金额',
  `pay_date` DATETIME DEFAULT NULL COMMENT '付费日期',
  `pay_platform_id` DOUBLE DEFAULT NULL COMMENT '付费平台id',
  `mark` DOUBLE DEFAULT NULL COMMENT '该时段充值标识：等于1、首次充值 大于1、非首次充值',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `country` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '会员付费记录标识' ;

-- ----------------------------
-- table structure for tbl_vip_reg_channel_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vip_reg_channel_detail` ;

CREATE TABLE `tbl_vip_reg_channel_detail` (
  `user_id` VARCHAR (100) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `pay_first_amount` DOUBLE DEFAULT NULL COMMENT '首冲金额',
  `pay_date` DATETIME DEFAULT NULL COMMENT '付费日期',
  `pay_platform_id` DOUBLE NOT NULL COMMENT '付费平台id',
  `country` VARCHAR (255) COLLATE utf8_bin DEFAULT NULL COMMENT '充值国家',
  `pay_amount` DOUBLE DEFAULT NULL COMMENT '充值总额',
  `is_vip` DOUBLE DEFAULT NULL COMMENT '是否直属会员：1、是 0、否',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`, `pay_platform_id`),
  UNIQUE KEY `sys_c0012785` (`user_id`, `pay_platform_id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '会员注册渠道明细统计' ;

-- ----------------------------
-- table structure for tbl_vip_reg_channel_stat
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vip_reg_channel_stat` ;

CREATE TABLE `tbl_vip_reg_channel_stat` (
  `stat_date` DATETIME NOT NULL COMMENT '统计日期',
  `channel_id` VARCHAR (30) COLLATE utf8_bin NOT NULL COMMENT '渠道id',
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
  PRIMARY KEY (
    `stat_date`,
    `country`,
    `channel_id`
  ),
  UNIQUE KEY `pk_vip_reg_channel_stat` (
    `stat_date`,
    `country`,
    `channel_id`
  )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '会员注册渠道统计' ;

-- ----------------------------
-- table structure for tbl_web_access_fact
-- ----------------------------
DROP TABLE IF EXISTS `tbl_web_access_fact` ;

CREATE TABLE `tbl_web_access_fact` (
  `tbl_web_access_log_id` VARCHAR (100) COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `channel_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '渠道id',
  `user_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `client_ip` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '客户端ip',
  `tbl_access_type_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '访问类型表tbl_access_type',
  `access_url` VARCHAR (2048) COLLATE utf8_bin DEFAULT NULL COMMENT '访问url',
  `referrer_url` VARCHAR (2048) COLLATE utf8_bin DEFAULT NULL COMMENT '来源url',
  `access_token` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '访问标示token',
  `tbl_web_device_info_id` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT 'web设备信息表tbl_web_device_info',
  `access_time` DATETIME DEFAULT NULL COMMENT '访问时间',
  `leave_time` DATETIME DEFAULT NULL COMMENT '离开时间',
  `permanent_token` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '永久cookie',
  `session_token` VARCHAR (100) COLLATE utf8_bin DEFAULT NULL COMMENT '会话级cookie,超时时间30分钟',
  `target_website_domain` VARCHAR (2048) COLLATE utf8_bin DEFAULT NULL COMMENT '目标网站顶级域名，如:http://www.baidu.com,单次会话的该值唯一',
  `referrer_website_domain` VARCHAR (2048) COLLATE utf8_bin DEFAULT NULL COMMENT '来源网站顶级域名，如:http://www.baidu.com,单次会话的该值唯一',
  PRIMARY KEY (`tbl_web_access_log_id`),
  UNIQUE KEY `sys_c0011773` (`tbl_web_access_log_id`),
  KEY `idx_tbl_web_access_fact_act` (`access_time`),
  KEY `idx_tbl_web_access_fact_atk` (`access_token`),
  KEY `idx_tbl_web_access_fact_cid` (`channel_id`),
  KEY `idx_tbl_web_access_fact_cip` (`client_ip`),
  KEY `idx_tbl_web_access_fact_let` (`leave_time`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin ;

-- ----------------------------
-- table structure for tbl_web_channel_daily
-- ----------------------------
DROP TABLE IF EXISTS `tbl_web_channel_daily` ;

CREATE TABLE `tbl_web_channel_daily` (
  `channel_id` VARCHAR (11) COLLATE utf8_bin NOT NULL COMMENT '渠道id',
  `stat_date` DATETIME NOT NULL COMMENT '统计日期',
  `pv_count` DOUBLE DEFAULT NULL COMMENT 'pv',
  `uv_count` DOUBLE DEFAULT NULL COMMENT '访问用户数',
  `ip_count` DOUBLE DEFAULT NULL COMMENT '访问ip数',
  `created_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `access_times` DOUBLE DEFAULT NULL COMMENT '访问次数|会话数',
  `open_user_count` DOUBLE DEFAULT NULL COMMENT '累计用户数,永久cookie的去重总数',
  `total_duration` DOUBLE DEFAULT NULL COMMENT '总访问时长',
  `one_page_access_times` DOUBLE DEFAULT NULL COMMENT '单页面访问次数',
  PRIMARY KEY (`channel_id`, `stat_date`),
  UNIQUE KEY `pk_tbl_web_channel_daily` (`channel_id`, `stat_date`),
  KEY `idx_tbl_web_channel_daily_sd` (`stat_date`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = 'web渠道统计日报表' ;

-- ----------------------------
-- view structure for gp_view_game_recharge_summary
-- ----------------------------
DROP VIEW IF EXISTS `gp_view_game_recharge_summary` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `gp_view_game_recharge_summary` AS
SELECT
  `gp_tbl_game_recharge_daily`.`platform_id` AS `platform_id`,
  `gp_tbl_game_recharge_daily`.`area_id` AS `area_id`,
  `gp_tbl_game_recharge_daily`.`server_id` AS `server_id`,
  SUM(
    `gp_tbl_game_recharge_daily`.`total_amount`
  ) AS `total_amount`,
  SUM(
    `gp_tbl_game_recharge_daily`.`total_amount`
  ) AS `receipt_amount`,
  0 AS `fee`
FROM
  `gp_tbl_game_recharge_daily`
GROUP BY `gp_tbl_game_recharge_daily`.`platform_id`,
  `gp_tbl_game_recharge_daily`.`area_id`,
  `gp_tbl_game_recharge_daily`.`server_id` ;

-- ----------------------------
-- view structure for gp_view_order_detail
-- ----------------------------
DROP VIEW IF EXISTS `gp_view_order_detail` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `gp_view_order_detail` AS
SELECT
  `x0`.`orderno` AS `orderno`,
  IFNULL(`x9`.`status`, `x0`.`status`) AS `pay_status`,
  1 AS `order_type`,
  `x1`.`channel_id` AS `channel_id`,
  `x5`.`chname` AS `channel_name`,
  `x1`.`user_id` AS `user_id`,
  `x1`.`nickname` AS `nickname`,
  `x0`.`platform_type` AS `recharge_way`,
  `x0`.`plateform_id` AS `platform_id`,
  `x0`.`son_plateformid` AS `server_id`,
  `x0`.`apply_amount` AS `amount`,
  `x0`.`apply_amount` AS `receipt_amount`,
  0 AS `fee`,
  `x0`.`apply_time` AS `create_time`,
  `x0`.`handle_time` AS `pay_time`,
  `x8`.`orderid` AS `trade_no`,
  `x2`.`business_no` AS `payment_orderno`,
  `x3`.`developers` AS `developers`
FROM
  (
    (
      (
        (
          (
            (
              (
                (
                  (
                    `traffic`.`gp_tbl_recharge_record` `x0`
                    JOIN `traffic`.`gp_tbl_user_info` `x1`
                      ON ((`x0`.`user_id` = `x1`.`user_id`))
                  )
                  LEFT JOIN `traffic`.`gp_tbl_pay_bussiness_info` `x2`
                    ON (
                      (
                        `x0`.`orderno` = `x2`.`out_trade_no`
                      )
                    )
                )
                JOIN `traffic`.`gp_tbl_platform_info` `x3`
                  ON ((`x0`.`plateform_id` = `x3`.`id`))
              )
              LEFT JOIN `traffic`.`gp_tbl_lyb_record` `x8`
                ON (
                  (
                    `x0`.`orderno` = `x8`.`opposite_orderid`
                  )
                )
            )
            LEFT JOIN `traffic`.`gp_tbl_toplate_errortrade` `x9`
              ON ((`x8`.`orderid` = `x9`.`order_id`))
          )
          LEFT JOIN
            (SELECT DISTINCT
              `traffic`.`channel_regist`.`channel` AS `channel`,
              `traffic`.`channel_regist`.`userid` AS `userid`
            FROM
              `traffic`.`channel_regist`) `x4`
            ON ((`x0`.`user_id` = `x4`.`userid`))
        )
        LEFT JOIN `traffic`.`channel` `x5`
          ON ((`x4`.`channel` = `x5`.`chid`))
      )
      LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x6`
        ON (
          (
            (`x3`.`country` = `x6`.`country`)
            AND (
              `x0`.`currency` = `x6`.`currency`
            )
          )
        )
    )
    LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x7`
      ON (
        (
          (`x3`.`country` = `x7`.`country`)
          AND (
            `x3`.`country_currency` = `x7`.`currency`
          )
        )
      )
  )
WHERE (
    (`x0`.`change_type` = 3)
    AND (`x0`.`platform_type` <= 18)
  )
  UNION
  ALL
  SELECT
    `x0`.`payment_orderno` AS `orderno`,
    IFNULL(`x9`.`status`, `x0`.`status`) AS `pay_status`,
    1 AS `order_type`,
    `x1`.`channel_id` AS `channel_id`,
    `x5`.`chname` AS `channel_name`,
    `x1`.`user_id` AS `user_id`,
    `x1`.`nickname` AS `nickname`,
    `x0`.`platform_type` AS `recharge_way`,
    `x0`.`plateform_id` AS `platform_id`,
    `x0`.`son_plateformid` AS `server_id`,
    `x0`.`apply_amount` AS `amount`,
    `x0`.`apply_amount` AS `receipt_amount`,
    0 AS `fee`,
    `x0`.`apply_time` AS `create_time`,
    `x0`.`handle_time` AS `pay_time`,
    `x8`.`orderid` AS `trade_no`,
    `x0`.`orderno` AS `payment_orderno`,
    `x3`.`developers` AS `developers`
  FROM
    (
      (
        (
          (
            (
              (
                (
                  (
                    (
                      `traffic`.`gp_tbl_recharge_record` `x0`
                      JOIN `traffic`.`gp_tbl_user_info` `x1`
                        ON ((`x0`.`user_id` = `x1`.`user_id`))
                    )
                    LEFT JOIN `traffic`.`gp_tbl_pay_bussiness_info` `x2`
                      ON (
                        (
                          `x0`.`orderno` = `x2`.`out_trade_no`
                        )
                      )
                  )
                  JOIN `traffic`.`gp_tbl_platform_info` `x3`
                    ON ((`x0`.`plateform_id` = `x3`.`id`))
                )
                LEFT JOIN `traffic`.`gp_tbl_lyb_record` `x8`
                  ON (
                    (
                      `x0`.`orderno` = `x8`.`opposite_orderid`
                    )
                  )
              )
              LEFT JOIN `traffic`.`gp_tbl_toplate_errortrade` `x9`
                ON ((`x8`.`orderid` = `x9`.`order_id`))
            )
            LEFT JOIN
              (SELECT DISTINCT
                `traffic`.`channel_regist`.`channel` AS `channel`,
                `traffic`.`channel_regist`.`userid` AS `userid`
              FROM
                `traffic`.`channel_regist`) `x4`
              ON ((`x0`.`user_id` = `x4`.`userid`))
          )
          LEFT JOIN `traffic`.`channel` `x5`
            ON ((`x4`.`channel` = `x5`.`chid`))
        )
        LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x6`
          ON (
            (
              (`x3`.`country` = `x6`.`country`)
              AND (
                `x0`.`currency` = `x6`.`currency`
              )
            )
          )
      )
      LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x7`
        ON (
          (
            (`x3`.`country` = `x7`.`country`)
            AND (
              `x3`.`country_currency` = `x7`.`currency`
            )
          )
        )
    )
  WHERE (
      (`x0`.`change_type` = 3)
      AND (`x0`.`platform_type` IN (19, 20))
    )
    UNION
    ALL
    SELECT
      `x0`.`orderno` AS `orderno`,
      `x0`.`status` AS `pay_status`,
      1 AS `order_type`,
      `x1`.`channel_id` AS `channel_id`,
      `x5`.`chname` AS `channel_name`,
      `x1`.`user_id` AS `user_id`,
      `x1`.`nickname` AS `nickname`,
      `x0`.`platform_type` AS `recharge_way`,
      `x2`.`opposite_plateformid` AS `platform_id`,
      `x2`.`son_plateformid` AS `server_id`,
      (
        `x0`.`real_lyb` / `x2`.`cash_to_lyb_rate`
      ) AS `amount`,
      (
        `x0`.`real_lyb` / `x2`.`cash_to_lyb_rate`
      ) AS `receipt_amount`,
      0 AS `fee`,
      `x0`.`apply_time` AS `create_time`,
      `x0`.`handle_time` AS `pay_time`,
      `x2`.`orderid` AS `trade_no`,
      '' AS `payment_orderno`,
      `x3`.`developers` AS `developers`
    FROM
      (
        (
          (
            (
              (
                (
                  (
                    (
                      `traffic`.`gp_tbl_recharge_record` `x0`
                      JOIN `traffic`.`gp_tbl_user_info` `x1`
                        ON ((`x0`.`user_id` = `x1`.`user_id`))
                    )
                    JOIN `traffic`.`gp_tbl_lyb_record` `x2`
                      ON (
                        (
                          `x0`.`orderno` = `x2`.`opposite_orderid`
                        )
                      )
                  )
                  LEFT JOIN `traffic`.`gp_tbl_platform_info` `x3`
                    ON ((`x0`.`plateform_id` = `x3`.`id`))
                )
                LEFT JOIN `traffic`.`gp_tbl_lyb_record` `x8`
                  ON (
                    (
                      `x0`.`orderno` = `x8`.`opposite_orderid`
                    )
                  )
              )
              LEFT JOIN
                (SELECT DISTINCT
                  `traffic`.`channel_regist`.`channel` AS `channel`,
                  `traffic`.`channel_regist`.`userid` AS `userid`
                FROM
                  `traffic`.`channel_regist`) `x4`
                ON ((`x0`.`user_id` = `x4`.`userid`))
            )
            LEFT JOIN `traffic`.`channel` `x5`
              ON ((`x4`.`channel` = `x5`.`chid`))
          )
          LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x6`
            ON (
              (
                (`x3`.`country` = `x6`.`country`)
                AND (
                  `x0`.`currency` = `x6`.`currency`
                )
              )
            )
        )
        LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x7`
          ON (
            (
              (`x3`.`country` = `x7`.`country`)
              AND (
                `x3`.`country_currency` = `x7`.`currency`
              )
            )
          )
      )
    WHERE (
        (`x0`.`change_type` = 2)
        OR (`x2`.`change_type` = 2)
      )
      UNION
      ALL
      SELECT
        `x0`.`opposite_orderid` AS `orderno`,
        IFNULL(`x8`.`status`, 1) AS `pay_status`,
        2 AS `order_type`,
        `x2`.`channel_id` AS `channel_id`,
        `x4`.`chname` AS `channel_name`,
        `x2`.`user_id` AS `user_id`,
        `x2`.`nickname` AS `nickname`,
        3 AS `recharge_way`,
        `x0`.`opposite_plateformid` AS `platform_id`,
        `x0`.`son_plateformid` AS `server_id`,
        (
          (
            `x0`.`gamegold` / `x0`.`lyb_to_yxb_rate`
          ) / `x0`.`cash_to_lyb_rate`
        ) AS `amount`,
        (
          (
            `x0`.`gamegold` / `x0`.`lyb_to_yxb_rate`
          ) / `x0`.`cash_to_lyb_rate`
        ) AS `receipt_amount`,
        0 AS `fee`,
        `x0`.`record_time` AS `create_time`,
        `x0`.`record_time` AS `pay_time`,
        `x0`.`opposite_orderid` AS `trade_no`,
        '' AS `payment_orderno`,
        `x1`.`developers` AS `developers`
      FROM
        (
          (
            (
              (
                (
                  (
                    (
                      `traffic`.`gp_tbl_lyb_record` `x0`
                      JOIN `traffic`.`gp_tbl_platform_info` `x1`
                        ON (
                          (
                            `x0`.`opposite_plateformid` = `x1`.`id`
                          )
                        )
                    )
                    JOIN `traffic`.`gp_tbl_user_info` `x2`
                      ON ((`x0`.`user_id` = `x2`.`user_id`))
                  )
                  LEFT JOIN `traffic`.`gp_tbl_toplate_errortrade` `x8`
                    ON ((`x0`.`orderid` = `x8`.`order_id`))
                )
                LEFT JOIN
                  (SELECT DISTINCT
                    `traffic`.`channel_regist`.`channel` AS `channel`,
                    `traffic`.`channel_regist`.`userid` AS `userid`
                  FROM
                    `traffic`.`channel_regist`) `x3`
                  ON ((`x0`.`user_id` = `x3`.`userid`))
              )
              LEFT JOIN `traffic`.`channel` `x4`
                ON ((`x3`.`channel` = `x4`.`chid`))
            )
            LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x6`
              ON (
                (
                  (`x1`.`country` = `x6`.`country`)
                  AND (
                    `x0`.`currency` = `x6`.`currency`
                  )
                )
              )
          )
          LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x7`
            ON (
              (
                (`x1`.`country` = `x7`.`country`)
                AND (
                  `x1`.`country_currency` = `x7`.`currency`
                )
              )
            )
        )
      WHERE (`x0`.`change_type` = 9)
        UNION
        ALL
        SELECT
          `x0`.`orderno` AS `orderno`,
          `x0`.`status` AS `pay_status`,
          1 AS `order_type`,
          `x1`.`channel_id` AS `channel_id`,
          `x5`.`chname` AS `channel_name`,
          `x1`.`user_id` AS `user_id`,
          `x1`.`nickname` AS `nickname`,
          `x0`.`platform_type` AS `recharge_way`,
          `x0`.`plateform_id` AS `platform_id`,
          `x0`.`son_plateformid` AS `server_id`,
          `x0`.`apply_amount` AS `amount`,
          `x0`.`apply_amount` AS `receipt_amount`,
          0 AS `fee`,
          `x0`.`apply_time` AS `create_time`,
          `x0`.`handle_time` AS `pay_time`,
          `x8`.`orderid` AS `trade_no`,
          `x2`.`business_no` AS `payment_orderno`,
          `x3`.`developers` AS `developers`
        FROM
          (
            (
              (
                (
                  (
                    (
                      (
                        (
                          `traffic`.`gp_tbl_recharge_record` `x0`
                          JOIN `traffic`.`gp_tbl_user_info` `x1`
                            ON ((`x0`.`user_id` = `x1`.`user_id`))
                        )
                        LEFT JOIN `traffic`.`gp_tbl_pay_bussiness_info` `x2`
                          ON (
                            (
                              `x0`.`orderno` = `x2`.`out_trade_no`
                            )
                          )
                      )
                      JOIN `traffic`.`gp_tbl_platform_info` `x3`
                        ON ((`x0`.`plateform_id` = `x3`.`id`))
                    )
                    LEFT JOIN `traffic`.`gp_tbl_lyb_record` `x8`
                      ON (
                        (
                          `x0`.`orderno` = `x8`.`opposite_orderid`
                        )
                      )
                  )
                  LEFT JOIN
                    (SELECT DISTINCT
                      `traffic`.`channel_regist`.`channel` AS `channel`,
                      `traffic`.`channel_regist`.`userid` AS `userid`
                    FROM
                      `traffic`.`channel_regist`) `x4`
                    ON ((`x0`.`user_id` = `x4`.`userid`))
                )
                LEFT JOIN `traffic`.`channel` `x5`
                  ON ((`x4`.`channel` = `x5`.`chid`))
              )
              LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x6`
                ON (
                  (
                    (`x3`.`country` = `x6`.`country`)
                    AND (
                      `x0`.`currency` = `x6`.`currency`
                    )
                  )
                )
            )
            LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x7`
              ON (
                (
                  (`x3`.`country` = `x7`.`country`)
                  AND (
                    `x3`.`country_currency` = `x7`.`currency`
                  )
                )
              )
          )
        WHERE (
            (`x0`.`change_type` = 1)
            AND (`x0`.`platform_type` <= 18)
          )
          UNION
          ALL
          SELECT
            `x2`.`business_no` AS `orderno`,
            `x0`.`status` AS `pay_status`,
            1 AS `order_type`,
            `x1`.`channel_id` AS `channel_id`,
            `x5`.`chname` AS `channel_name`,
            `x1`.`user_id` AS `user_id`,
            `x1`.`nickname` AS `nickname`,
            `x0`.`platform_type` AS `recharge_way`,
            `x0`.`plateform_id` AS `platform_id`,
            `x0`.`son_plateformid` AS `server_id`,
            `x0`.`apply_amount` AS `amount`,
            `x0`.`apply_amount` AS `receipt_amount`,
            0 AS `fee`,
            `x0`.`apply_time` AS `create_time`,
            `x0`.`handle_time` AS `pay_time`,
            `x8`.`orderid` AS `trade_no`,
            `x0`.`orderno` AS `payment_orderno`,
            `x3`.`developers` AS `developers`
          FROM
            (
              (
                (
                  (
                    (
                      (
                        (
                          (
                            `traffic`.`gp_tbl_recharge_record` `x0`
                            JOIN `traffic`.`gp_tbl_user_info` `x1`
                              ON ((`x0`.`user_id` = `x1`.`user_id`))
                          )
                          LEFT JOIN `traffic`.`gp_tbl_pay_bussiness_info` `x2`
                            ON (
                              (
                                `x0`.`orderno` = `x2`.`out_trade_no`
                              )
                            )
                        )
                        JOIN `traffic`.`gp_tbl_platform_info` `x3`
                          ON ((`x0`.`plateform_id` = `x3`.`id`))
                      )
                      LEFT JOIN `traffic`.`gp_tbl_lyb_record` `x8`
                        ON (
                          (
                            `x0`.`orderno` = `x8`.`opposite_orderid`
                          )
                        )
                    )
                    LEFT JOIN
                      (SELECT DISTINCT
                        `traffic`.`channel_regist`.`channel` AS `channel`,
                        `traffic`.`channel_regist`.`userid` AS `userid`
                      FROM
                        `traffic`.`channel_regist`) `x4`
                      ON ((`x0`.`user_id` = `x4`.`userid`))
                  )
                  LEFT JOIN `traffic`.`channel` `x5`
                    ON ((`x4`.`channel` = `x5`.`chid`))
                )
                LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x6`
                  ON (
                    (
                      (`x3`.`country` = `x6`.`country`)
                      AND (
                        `x0`.`currency` = `x6`.`currency`
                      )
                    )
                  )
              )
              LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x7`
                ON (
                  (
                    (`x3`.`country` = `x7`.`country`)
                    AND (
                      `x3`.`country_currency` = `x7`.`currency`
                    )
                  )
                )
            )
          WHERE (
              (`x0`.`change_type` = 1)
              AND (`x0`.`platform_type` IN (19, 20))
            ) ;

-- ----------------------------
-- view structure for gp_view_user_recharge_summary
-- ----------------------------
DROP VIEW IF EXISTS `gp_view_user_recharge_summary` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `gp_view_user_recharge_summary` AS
SELECT
  `x0`.`platform_id` AS `platform_id`,
  `x0`.`area_id` AS `area_id`,
  `x0`.`server_id` AS `server_id`,
  `x1`.`channel_id` AS `channel_id`,
  `x3`.`chname` AS `channel_name`,
  `x0`.`user_id` AS `user_id`,
  `x1`.`nickname` AS `nickname`,
  `x1`.`phone` AS `phone`,
  `x1`.`email` AS `email`,
  `x1`.`create_time` AS `reg_time`,
  `x0`.`total_amount` AS `total_amount`
FROM
  (
    (
      (
        (
          (SELECT
            `traffic`.`gp_tbl_user_recharge_daily`.`platform_id` AS `platform_id`,
            `traffic`.`gp_tbl_user_recharge_daily`.`area_id` AS `area_id`,
            `traffic`.`gp_tbl_user_recharge_daily`.`server_id` AS `server_id`,
            `traffic`.`gp_tbl_user_recharge_daily`.`user_id` AS `user_id`,
            SUM(
              `traffic`.`gp_tbl_user_recharge_daily`.`total_amount`
            ) AS `total_amount`
          FROM
            `traffic`.`gp_tbl_user_recharge_daily`
          GROUP BY `traffic`.`gp_tbl_user_recharge_daily`.`platform_id`,
            `traffic`.`gp_tbl_user_recharge_daily`.`area_id`,
            `traffic`.`gp_tbl_user_recharge_daily`.`server_id`,
            `traffic`.`gp_tbl_user_recharge_daily`.`user_id`)
        ) `x0`
        LEFT JOIN `traffic`.`gp_tbl_user_info` `x1`
          ON ((`x0`.`user_id` = `x1`.`user_id`))
      )
      LEFT JOIN
        (SELECT DISTINCT
          `traffic`.`channel_regist`.`channel` AS `channel`,
          `traffic`.`channel_regist`.`userid` AS `userid`
        FROM
          `traffic`.`channel_regist`) `x2`
        ON ((`x0`.`user_id` = `x2`.`userid`))
    )
    LEFT JOIN `traffic`.`channel` `x3`
      ON ((`x1`.`channel_id` = `x3`.`chid`))
  ) ;

-- ----------------------------
-- view structure for view_channel_daily_sum
-- ----------------------------
DROP VIEW IF EXISTS `view_channel_daily_sum` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_channel_daily_sum` AS
SELECT
  `re_be`.`recharge_day` AS `recharge_day`,
  `re_be`.`channel_id` AS `channel_id`,
  SUM(`re_be`.`download_times`) AS `download_times`,
  SUM(`re_be`.`regist_users`) AS `regist_users`,
  SUM(`re_be`.`active_users`) AS `active_users`,
  SUM(`re_be`.`recharge_users`) AS `recharge_users`,
  SUM(`re_be`.`recharge_amount`) AS `recharge_amount`,
  SUM(`re_be`.`dz_beting_users`) AS `dz_beting_users`,
  SUM(`re_be`.`dz_bet_acc_sum`) AS `dz_bet_acc_sum`,
  SUM(`re_be`.`dj_betting_users`) AS `dj_betting_users`,
  SUM(`re_be`.`dj_bet_acc_sum`) AS `dj_bet_acc_sum`
FROM
  (SELECT
    DATE_FORMAT(
      DATE_FORMAT(
        `t`.`handle_time`,
        '%y-%m-%d %h:%i:%s'
      ),
      '%y%m%d'
    ) AS `recharge_day`,
    IFNULL(`i`.`channel_id`, 'vnp56ams') AS `channel_id`,
    0 AS `download_times`,
    0 AS `regist_users`,
    0 AS `active_users`,
    COUNT(DISTINCT `t`.`user_id`) AS `recharge_users`,
    SUM(`t`.`apply_amount`) AS `recharge_amount`,
    0 AS `dz_beting_users`,
    0 AS `dz_bet_acc_sum`,
    0 AS `dj_betting_users`,
    0 AS `dj_bet_acc_sum`
  FROM
    (
      `traffic`.`gp_tbl_recharge_record` `t`
      JOIN `traffic`.`gp_tbl_user_info` `i`
    )
  WHERE (
      (`t`.`user_id` = `i`.`user_id`)
      AND (`t`.`change_type` IN (1, 3))
      AND (`t`.`status` = 1)
    )
  GROUP BY DATE_FORMAT(
      DATE_FORMAT(
        `t`.`handle_time`,
        '%y-%m-%d %h:%i:%s'
      ),
      '%y%m%d'
    ),
    IFNULL(`i`.`channel_id`, 'vnp56ams')
    UNION
    ALL
    SELECT
      DATE_FORMAT(
        DATE_FORMAT(
          `t`.`settle_time`,
          '%y-%m-%d %h:%i:%s'
        ),
        '%y%m%d'
      ) AS `recharge_day`,
      IFNULL(`i`.`channel_id`, 'vnp56ams') AS `channel_id`,
      0 AS `download_times`,
      0 AS `regist_users`,
      0 AS `active_users`,
      0 AS `recharge_users`,
      0 AS `recharge_amount`,
      COUNT(DISTINCT `t`.`user_id`) AS `dz_beting_users`,
      SUM(`t`.`bet_amount`) AS `dz_bet_acc_sum`,
      0 AS `dj_betting_users`,
      0 AS `dj_bet_acc_sum`
    FROM
      (
        `traffic`.`tbl_ods_lmdz_betting_log` `t`
        JOIN `traffic`.`gp_tbl_user_info` `i`
      )
    WHERE (`t`.`user_id` = `i`.`user_id`)
    GROUP BY IFNULL(`i`.`channel_id`, 'vnp56ams'),
      DATE_FORMAT(
        DATE_FORMAT(
          `t`.`settle_time`,
          '%y-%m-%d %h:%i:%s'
        ),
        '%y%m%d'
      )
      UNION
      ALL
      SELECT
        DATE_FORMAT(
          DATE_FORMAT(
            `l`.`settle_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m%d'
        ) AS `recharge_day`,
        IFNULL(`f`.`channel_id`, 'vnp56ams') AS `channel_id`,
        0 AS `download_times`,
        0 AS `regist_users`,
        0 AS `active_users`,
        0 AS `recharge_users`,
        0 AS `recharge_amount`,
        0 AS `dz_beting_users`,
        0 AS `dz_bet_acc_sum`,
        COUNT(DISTINCT `l`.`user_id`) AS `dj_betting_users`,
        SUM(`l`.`settle_amount`) AS `dj_bet_acc_sum`
      FROM
        (
          `traffic`.`tbl_ods_lydj_betting_log` `l`
          JOIN `traffic`.`gp_tbl_user_info` `f`
        )
      WHERE (`l`.`user_id` = `f`.`user_id`)
      GROUP BY IFNULL(`f`.`channel_id`, 'vnp56ams'),
        DATE_FORMAT(
          DATE_FORMAT(
            `l`.`settle_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m%d'
        )
        UNION
        ALL
        SELECT
          `vi`.`active_date` AS `recharge_day`,
          `vi`.`channel_id` AS `channel_id`,
          IFNULL(`vi`.`download_times`, 0) AS `download_times`,
          IFNULL(`vi`.`regist_users`, 0) AS `regist_users`,
          IFNULL(`vi`.`active_users`, 0) AS `active_users`,
          0 AS `recharge_users`,
          0 AS `recharge_amount`,
          0 AS `dz_beting_users`,
          0 AS `dz_bet_acc_sum`,
          0 AS `dj_betting_users`,
          0 AS `dj_bet_acc_sum`
        FROM
          `traffic`.`view_regist_active_day` `vi`) `re_be`
      GROUP BY `re_be`.`recharge_day`,
        `re_be`.`channel_id`
      ORDER BY `re_be`.`recharge_day` DESC ;

-- ----------------------------
-- view structure for view_channel_month_allbet_sum
-- ----------------------------
DROP VIEW IF EXISTS `view_channel_month_allbet_sum` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_channel_month_allbet_sum` AS
SELECT
  `re_be`.`recharge_month` AS `recharge_month`,
  `re_be`.`channel_id` AS `channel_id`,
  SUM(`re_be`.`download_times`) AS `download_times`,
  SUM(`re_be`.`regist_users`) AS `regist_users`,
  SUM(`re_be`.`active_users`) AS `active_users`,
  SUM(`re_be`.`recharge_users`) AS `recharge_users`,
  SUM(`re_be`.`recharge_amount`) AS `recharge_amount`,
  SUM(`re_be`.`beting_users`) AS `beting_users`,
  SUM(`re_be`.`bet_acc_sum`) AS `beting_acc_sum`
FROM
  (SELECT
    DATE_FORMAT(
      DATE_FORMAT(
        `t`.`handle_time`,
        '%y-%m-%d %h:%i:%s'
      ),
      '%y%m'
    ) AS `recharge_month`,
    IFNULL(`i`.`channel_id`, 'vnp56ams') AS `channel_id`,
    0 AS `download_times`,
    0 AS `regist_users`,
    0 AS `active_users`,
    COUNT(DISTINCT `t`.`user_id`) AS `recharge_users`,
    SUM(`t`.`apply_amount`) AS `recharge_amount`,
    0 AS `beting_users`,
    0 AS `bet_acc_sum`
  FROM
    (
      `traffic`.`gp_tbl_recharge_record` `t`
      JOIN `traffic`.`gp_tbl_user_info` `i`
    )
  WHERE (
      (`t`.`user_id` = `i`.`user_id`)
      AND (`t`.`status` = 1)
      AND (`t`.`change_type` IN (1, 3))
    )
  GROUP BY DATE_FORMAT(
      DATE_FORMAT(
        `t`.`handle_time`,
        '%y-%m-%d %h:%i:%s'
      ),
      '%y%m'
    ),
    IFNULL(`i`.`channel_id`, 'vnp56ams')
    UNION
    ALL
    SELECT
      `allbet`.`settle_month` AS `settle_month`,
      `allbet`.`channel_id` AS `channel_id`,
      0 AS `download_times`,
      0 AS `regist_users`,
      0 AS `active_users`,
      0 AS `recharge_users`,
      0 AS `recharge_amount`,
      COUNT(DISTINCT `allbet`.`user_id`) AS `beting_users`,
      SUM(`allbet`.`bet_acc_sum`) AS `bet_acc_sum`
    FROM
      (SELECT
        IFNULL(`i`.`channel_id`, 'vnp56ams') AS `channel_id`,
        DATE_FORMAT(
          DATE_FORMAT(
            `t`.`settle_month`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m'
        ) AS `settle_month`,
        `t`.`user_id` AS `user_id`,
        SUM(`t`.`bet_acc_sum`) AS `bet_acc_sum`
      FROM
        (
          `traffic`.`tbl_edm_lmdz_betting_month` `t`
          JOIN `traffic`.`gp_tbl_user_info` `i`
        )
      WHERE (`t`.`user_id` = `i`.`user_id`)
      GROUP BY IFNULL(`i`.`channel_id`, 'vnp56ams'),
        `t`.`settle_month`,
        `t`.`user_id`
      UNION
      ALL
      SELECT
        IFNULL(`f`.`channel_id`, 'vnp56ams') AS `channel_id`,
        DATE_FORMAT(
          DATE_FORMAT(
            `l`.`settle_month`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m'
        ) AS `settle_month`,
        `l`.`user_id` AS `user_id`,
        SUM(`l`.`settle_acc_sum`) AS `bet_acc_sum`
      FROM
        (
          `traffic`.`tbl_edm_lydj_betting_month` `l`
          JOIN `traffic`.`gp_tbl_user_info` `f`
        )
      WHERE (`l`.`user_id` = `f`.`user_id`)
      GROUP BY IFNULL(`f`.`channel_id`, 'vnp56ams'),
        `l`.`settle_month`,
        `l`.`user_id`) `allbet`
    GROUP BY `allbet`.`channel_id`,
      `allbet`.`settle_month`
    UNION
    ALL
    SELECT
      `vi`.`active_month` AS `recharge_month`,
      `vi`.`channel_id` AS `channel_id`,
      IFNULL(`vi`.`download_times`, 0) AS `download_times`,
      IFNULL(`vi`.`regist_users`, 0) AS `regist_users`,
      IFNULL(`vi`.`active_users`, 0) AS `active_users`,
      0 AS `recharge_users`,
      0 AS `recharge_amount`,
      0 AS `beting_users`,
      0 AS `beting_acc_sum`
    FROM
      `traffic`.`view_regist_active_month` `vi`) `re_be`
  WHERE (
      `re_be`.`recharge_month` IS NOT NULL
    )
  GROUP BY `re_be`.`recharge_month`,
    `re_be`.`channel_id`
  ORDER BY `re_be`.`recharge_month` DESC ;

-- ----------------------------
-- view structure for view_channel_month_sum
-- ----------------------------
DROP VIEW IF EXISTS `view_channel_month_sum` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_channel_month_sum` AS
SELECT
  `re_be`.`recharge_month` AS `recharge_month`,
  `re_be`.`channel_id` AS `channel_id`,
  SUM(`re_be`.`download_times`) AS `download_times`,
  SUM(`re_be`.`regist_users`) AS `regist_users`,
  SUM(`re_be`.`active_users`) AS `active_users`,
  SUM(`re_be`.`recharge_users`) AS `recharge_users`,
  SUM(`re_be`.`recharge_amount`) AS `recharge_amount`,
  SUM(`re_be`.`lmdz_beting_users`) AS `dz_beting_users`,
  SUM(`re_be`.`lmdz_bet_acc_sum`) AS `dz_bet_acc_sum`,
  SUM(`re_be`.`lydj_beting_users`) AS `dj_betting_users`,
  SUM(`re_be`.`lydj_bet_acc_sum`) AS `dj_bet_acc_sum`
FROM
  (SELECT
    DATE_FORMAT(
      DATE_FORMAT(
        `t`.`handle_time`,
        '%y-%m-%d %h:%i:%s'
      ),
      '%y%m'
    ) AS `recharge_month`,
    IFNULL(`i`.`channel_id`, 'vnp56ams') AS `channel_id`,
    0 AS `download_times`,
    0 AS `regist_users`,
    0 AS `active_users`,
    COUNT(DISTINCT `t`.`user_id`) AS `recharge_users`,
    SUM(`t`.`apply_amount`) AS `recharge_amount`,
    0 AS `lmdz_beting_users`,
    0 AS `lmdz_bet_acc_sum`,
    0 AS `lydj_beting_users`,
    0 AS `lydj_bet_acc_sum`
  FROM
    (
      `traffic`.`gp_tbl_recharge_record` `t`
      JOIN `traffic`.`gp_tbl_user_info` `i`
    )
  WHERE (
      (`t`.`user_id` = `i`.`user_id`)
      AND (`t`.`status` = 1)
      AND (`t`.`change_type` IN (1, 3))
    )
  GROUP BY IFNULL(`i`.`channel_id`, 'vnp56ams'),
    DATE_FORMAT(
      DATE_FORMAT(
        `t`.`handle_time`,
        '%y-%m-%d %h:%i:%s'
      ),
      '%y%m'
    )
    UNION
    ALL
    SELECT
      DATE_FORMAT(
        DATE_FORMAT(
          `t`.`settle_month`,
          '%y-%m-%d %h:%i:%s'
        ),
        '%y%m'
      ) AS `recharge_month`,
      IFNULL(`i`.`channel_id`, 'vnp56ams') AS `channel_id`,
      0 AS `download_times`,
      0 AS `regist_users`,
      0 AS `active_users`,
      0 AS `recharge_users`,
      0 AS `recharge_amount`,
      COUNT(DISTINCT `t`.`user_id`) AS `lmdz_beting_users`,
      SUM(`t`.`bet_acc_sum`) AS `lmdz_bet_acc_sum`,
      0 AS `lydj_beting_users`,
      0 AS `lydj_bet_acc_sum`
    FROM
      (
        `traffic`.`tbl_edm_lmdz_betting_month` `t`
        JOIN `traffic`.`gp_tbl_user_info` `i`
      )
    WHERE (`t`.`user_id` = `i`.`user_id`)
    GROUP BY IFNULL(`i`.`channel_id`, 'vnp56ams'),
      DATE_FORMAT(
        DATE_FORMAT(
          `t`.`settle_month`,
          '%y-%m-%d %h:%i:%s'
        ),
        '%y%m'
      )
      UNION
      ALL
      SELECT
        DATE_FORMAT(
          DATE_FORMAT(
            `l`.`settle_month`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m'
        ) AS `recharge_month`,
        IFNULL(`f`.`channel_id`, 'vnp56ams') AS `channel_id`,
        0 AS `download_times`,
        0 AS `regist_users`,
        0 AS `active_users`,
        0 AS `recharge_users`,
        0 AS `recharge_amount`,
        0 AS `lmdz_beting_users`,
        0 AS `lmdz_bet_acc_sum`,
        COUNT(DISTINCT `l`.`user_id`) AS `lydj_beting_users`,
        SUM(`l`.`settle_acc_sum`) AS `lydj_bet_acc_sum`
      FROM
        (
          `traffic`.`tbl_edm_lydj_betting_month` `l`
          JOIN `traffic`.`gp_tbl_user_info` `f`
        )
      WHERE (`l`.`user_id` = `f`.`user_id`)
      GROUP BY IFNULL(`f`.`channel_id`, 'vnp56ams'),
        DATE_FORMAT(
          DATE_FORMAT(
            `l`.`settle_month`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m'
        )
        UNION
        ALL
        SELECT
          `vi`.`active_month` AS `recharge_month`,
          `vi`.`channel_id` AS `channel_id`,
          `vi`.`download_times` AS `download_times`,
          `vi`.`regist_users` AS `regist_users`,
          `vi`.`active_users` AS `active_users`,
          0 AS `recharge_users`,
          0 AS `recharge_amount`,
          0 AS `lmdz_beting_users`,
          0 AS `lmdz_bet_acc_sum`,
          0 AS `lydj_beting_users`,
          0 AS `lydj_bet_acc_sum`
        FROM
          `traffic`.`view_regist_active_month` `vi`) `re_be`
      GROUP BY `re_be`.`recharge_month`,
        `re_be`.`channel_id` ;

-- ----------------------------
-- view structure for view_cosume_detail
-- ----------------------------
DROP VIEW IF EXISTS `view_cosume_detail` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_cosume_detail` AS
SELECT
  `t1`.`orderno` AS `orderno`,
  `t1`.`user_id` AS `user_id`,
  `t2`.`nickname` AS `nickname`,
  `t1`.`apply_time` AS `apply_time`,
  `t1`.`handle_time` AS `handle_time`,
  '乐盈币' AS `recharge_type`,
  IFNULL(`t4`.`before_cash`, 0) AS `before_cash`,
  IFNULL(
    IFNULL(
      (
        CASE
          WHEN (`t1`.`change_type` = 1)
          THEN `t4`.`lyb`
          ELSE (0 - `t4`.`lyb`)
        END
      ),
      `t1`.`real_lyb`
    ),
    0
  ) AS `lyb`,
  IFNULL(`t4`.`after_cash`, 0) AS `after_cash`,
  `t2`.`channel_id` AS `channel_id`,
  `t3`.`name` AS `platform_name`,
  IFNULL(`t5`.`name`, '-') AS `server_name`,
  (
    CASE
      WHEN (`t1`.`status` = 0)
      THEN '失败'
      WHEN (
        (`t1`.`status` = 1)
        AND (
          ISNULL(`xx`.`order_id`)
          OR (`xx`.`status` = 1)
        )
      )
      THEN '成功'
      WHEN (
        (`t1`.`status` = 1)
        AND (`xx`.`status` = 1)
      )
      THEN '失败'
      WHEN (`t1`.`status` = 2)
      THEN '处理中'
      WHEN (`t1`.`status` = 3)
      THEN '已关闭'
      ELSE `t1`.`status`
    END
  ) AS `status`
FROM
  (
    (
      (
        (
          (
            `gp_tbl_recharge_record` `t1`
            JOIN `gp_tbl_user_info` `t2`
              ON (
                (
                  (`t1`.`user_id` = `t2`.`user_id`)
                  AND (`t1`.`change_type` IN (1, 2))
                )
              )
          )
          JOIN `gp_tbl_platform_info` `t3`
            ON ((`t1`.`plateform_id` = `t3`.`id`))
        )
        LEFT JOIN `gp_tbl_lyb_record` `t4`
          ON (
            (
              `t1`.`orderno` = `t4`.`opposite_orderid`
            )
          )
      )
      LEFT JOIN `gp_tbl_toplate_errortrade` `xx`
        ON ((`t4`.`orderid` = `xx`.`order_id`))
    )
    LEFT JOIN `gp_tbl_game_server_config` `t5`
      ON (
        (
          `t1`.`son_plateformid` = `t5`.`id`
        )
      )
  )
  UNION
  ALL
  SELECT
    `t1`.`order_no` AS `order_no`,
    `t1`.`user_id` AS `user_id`,
    `t2`.`nickname` AS `nickname`,
    `t1`.`create_time` AS `apply_time`,
    `t1`.`create_time` AS `handle_time`,
    '乐盈券' AS `recharge_type`,
    `t4`.`before_lyq` AS `before_lyq`,
    `t4`.`lyq` AS `lyq`,
    `t4`.`after_lyq` AS `after_lyq`,
    `t2`.`channel_id` AS `channel_id`,
    `t3`.`name` AS `platform_name`,
    '-' AS `server_name`,
    (
      CASE
        WHEN (`t1`.`status` = 0)
        THEN '失败'
        WHEN (`t1`.`status` = 1)
        THEN '成功'
        WHEN (`t1`.`status` = 2)
        THEN '处理中'
        WHEN (`t1`.`status` = 3)
        THEN '已关闭'
        ELSE `t1`.`status`
      END
    ) AS `status`
  FROM
    (
      (
        (
          `tbl_gp_gamegold_to_lyq` `t1`
          JOIN `gp_tbl_user_info` `t2`
            ON ((`t1`.`user_id` = `t2`.`user_id`))
        )
        JOIN `gp_tbl_platform_info` `t3`
          ON ((`t1`.`f_platform_id` = `t3`.`id`))
      )
      JOIN `tbl_gp_lyq_record` `t4`
        ON (
          (
            `t1`.`order_no` = `t4`.`opposite_orderid`
          )
        )
    )
    UNION
    ALL
    SELECT
      IFNULL(
        `t1`.`opposite_orderid`,
        `t1`.`orderid`
      ) AS `ifnull(t1.opposite_orderid,t1.orderid)`,
      `t1`.`user_id` AS `user_id`,
      `t2`.`nickname` AS `nickname`,
      `t1`.`record_time` AS `apply_time`,
      `t1`.`record_time` AS `handle_time`,
      '乐盈券' AS `recharge_type`,
      `t1`.`before_lyq` AS `before_lyq`,
      (0 - `t1`.`lyq`) AS `0-t1.lyq`,
      `t1`.`after_lyq` AS `after_lyq`,
      `t2`.`channel_id` AS `channel_id`,
      '商城' AS `platform_name`,
      '-' AS `server_name`,
      '成功' AS `status`
    FROM
      (
        `tbl_gp_lyq_record` `t1`
        JOIN `gp_tbl_user_info` `t2`
          ON ((`t1`.`user_id` = `t2`.`user_id`))
      )
    WHERE (`t1`.`change_type` = 1) ;

-- ----------------------------
-- view structure for view_exchange_list
-- ----------------------------
DROP VIEW IF EXISTS `view_exchange_list` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_exchange_list` AS
SELECT
  `t`.`order_no` AS `order_no`,
  (
    CASE
      WHEN (`t`.`status` = 0)
      THEN '失败'
      WHEN (`t`.`status` = 1)
      THEN '成功'
      WHEN (`t`.`status` = 2)
      THEN '处理中'
      ELSE IFNULL(`t`.`status`, 99)
    END
  ) AS `exchange_status`,
  `t`.`user_id` AS `user_id`,
  `i`.`nickname` AS `nickname`,
  `p`.`name` AS `exchange_f_game`,
  `t`.`f_gamegolds` AS `exchange_amount`,
  `t`.`create_time` AS `commit_time`,
  `t`.`create_time` AS `trade_time`
FROM
  (
    (
      `tbl_gp_gamegold_to_lyq` `t`
      JOIN `gp_tbl_user_info` `i`
        ON ((`t`.`user_id` = `i`.`user_id`))
    )
    LEFT JOIN `gp_tbl_platform_info` `p`
      ON ((`t`.`f_platform_id` = `p`.`id`))
  )
ORDER BY `t`.`create_time` DESC ;

-- ----------------------------
-- view structure for view_game_exchange_list
-- ----------------------------
DROP VIEW IF EXISTS `view_game_exchange_list` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_game_exchange_list` AS
SELECT
  `d`.`orderno` AS `order_no`,
  `r`.`opposite_orderid` AS `from_order`,
  `r`.`orderid` AS `to_order`,
  (
    CASE
      WHEN (`d`.`status` = 0)
      THEN '失败'
      WHEN (`d`.`status` = 1)
      THEN '成功'
      WHEN (`d`.`status` = 2)
      THEN '处理中'
      ELSE IFNULL(`d`.`status`, 99)
    END
  ) AS `exchange_status`,
  `r`.`user_id` AS `user_id`,
  `t2`.`nickname` AS `nickname`,
  '玩一下' AS `exchange_f_game`,
  `r`.`lyb` AS `exchange_amount`,
  `r`.`after_cash` AS `overage_amount`,
  `r`.`record_time` AS `commit_time`,
  `d`.`handle_time` AS `trade_time`,
  `p`.`name` AS `exchange_to_game`,
  IFNULL(`t6`.`name`, '--') AS `server_id`,
  '--' AS `exchange_to_amount`
FROM
  (
    (
      (
        (
          (
            `gp_tbl_lyb_record` `r`
            JOIN `gp_tbl_user_info` `t2`
              ON ((`r`.`user_id` = `t2`.`user_id`))
          )
          JOIN `gp_tbl_recharge_record` `d`
            ON (
              (
                `r`.`opposite_orderid` = `d`.`orderno`
              )
            )
        )
        LEFT JOIN `gp_tbl_platform_info` `p`
          ON (
            (
              `r`.`opposite_plateformid` = `p`.`id`
            )
          )
      )
      LEFT JOIN `gp_tbl_toplate_errortrade` `t5`
        ON ((`d`.`orderno` = `t5`.`order_id`))
    )
    LEFT JOIN `gp_tbl_game_server_config` `t6`
      ON (
        (
          `d`.`son_plateformid` = `t6`.`id`
        )
      )
  )
WHERE (`d`.`change_type` = 2)
  UNION
  ALL
  SELECT
    `f1`.`order_no` AS `order_no`,
    `f1`.`ggtf_orderno` AS `from_order`,
    `f1`.`ggtt_orderno` AS `to_order`,
    (
      CASE
        WHEN (`f1`.`status` = 0)
        THEN '失败'
        WHEN (`f1`.`status` = 1)
        THEN '成功'
        WHEN (`f1`.`status` = 2)
        THEN '处理中'
        ELSE IFNULL(`f1`.`status`, 99)
      END
    ) AS `exchange_status`,
    `f1`.`user_id` AS `user_id`,
    `f2`.`nickname` AS `nickname`,
    `info`.`name` AS `exchange_f_game`,
    `f1`.`f_gamegolds` AS `exchange_amount`,
    0 AS `overage_amount`,
    `f1`.`create_time` AS `commit_time`,
    `f1`.`create_time` AS `trade_time`,
    `f4`.`name` AS `exchange_to_game`,
    IFNULL(`f6`.`name`, '--') AS `server_id`,
    '--' AS `exchange_to_amount`
  FROM
    (
      (
        (
          (
            `tbl_gp_gamegold_transrecord` `f1`
            JOIN `gp_tbl_user_info` `f2`
              ON ((`f1`.`user_id` = `f2`.`user_id`))
          )
          LEFT JOIN `gp_tbl_platform_info` `f4`
            ON ((`f1`.`t_platform_id` = `f4`.`id`))
        )
        LEFT JOIN `gp_tbl_platform_info` `info`
          ON (
            (
              `f1`.`f_platform_id` = `info`.`id`
            )
          )
      )
      LEFT JOIN `gp_tbl_game_server_config` `f6`
        ON ((`f1`.`t_service_id` = `f6`.`id`))
    )
  ORDER BY `commit_time` DESC ;

-- ----------------------------
-- view structure for view_gp_all_recharge_sum
-- ----------------------------
DROP VIEW IF EXISTS `view_gp_all_recharge_sum` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_gp_all_recharge_sum` AS
SELECT
  `a`.`country` AS `country`,
  `a`.`rate` AS `rate`,
  `a`.`order_amount` AS `order_amount`,
  `a`.`receipt_amount` AS `receipt_amount`,
  `a`.`commissions` AS `commissions`,
  IFNULL(`b`.`amount`, 0) AS `amount`,
  IFNULL(`c`.`lybto_yx_amt`, 0) AS `lyb_2_yx_amt`
FROM
  (
    (
      (
        (SELECT
          `g`.`country` AS `country`,
          `g`.`rate` AS `rate`,
          SUM(`s`.`total_amount`) AS `order_amount`,
          SUM(`s`.`receipt_amount`) AS `receipt_amount`,
          SUM(`s`.`fee`) AS `commissions`
        FROM
          (
            (
              `traffic`.`gp_view_game_recharge_summary` `s`
              LEFT JOIN `traffic`.`gp_tbl_platform_info` `p`
                ON ((`p`.`id` = `s`.`platform_id`))
            )
            LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `g`
              ON (
                (
                  (`p`.`country` = `g`.`country`)
                  AND (
                    `p`.`country_currency` = `g`.`currency`
                  )
                )
              )
          )
        WHERE (`g`.`country` IS NOT NULL)
        GROUP BY `g`.`country`,
          `g`.`rate`)
      ) `a`
      LEFT JOIN
        (SELECT
          `g`.`country` AS `country`,
          `g`.`rate` AS `rate`,
          SUM(`x0`.`apply_amount`) AS `amount`
        FROM
          (
            (
              (
                (
                  (
                    (
                      `traffic`.`gp_tbl_recharge_record` `x0`
                      JOIN `traffic`.`gp_tbl_user_info` `x1`
                        ON ((`x0`.`user_id` = `x1`.`user_id`))
                    )
                    JOIN `traffic`.`gp_tbl_pay_bussiness_info` `x2`
                      ON (
                        (
                          `x0`.`orderno` = `x2`.`out_trade_no`
                        )
                      )
                  )
                  JOIN `traffic`.`gp_tbl_platform_info` `x3`
                    ON ((`x0`.`plateform_id` = `x3`.`id`))
                )
                LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `g`
                  ON ((`x3`.`country` = `g`.`country`))
              )
              LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x6`
                ON (
                  (
                    (`x3`.`country` = `x6`.`country`)
                    AND (
                      `x0`.`currency` = `x6`.`currency`
                    )
                  )
                )
            )
            LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x7`
              ON (
                (
                  (`x3`.`country` = `x7`.`country`)
                  AND (
                    `x3`.`country_currency` = `x7`.`currency`
                  )
                )
              )
          )
        WHERE (
            (`x0`.`change_type` = '1')
            AND (`x0`.`status` = 1)
            AND (`g`.`country` IS NOT NULL)
            AND (
              NOT (
                EXISTS
                (SELECT
                  NULL
                FROM
                  (
                    `traffic`.`gp_tbl_lyb_record` `x4`
                    JOIN `traffic`.`gp_tbl_toplate_errortrade` `x5`
                      ON ((`x4`.`orderid` = `x5`.`order_id`))
                  )
                WHERE (
                    (
                      `x4`.`opposite_orderid` = `x0`.`orderno`
                    )
                    AND (`x5`.`status` <> 1)
                  ))
              )
            )
          )
        GROUP BY `g`.`country`,
          `g`.`rate`) `b`
        ON (
          (
            (`a`.`country` = `b`.`country`)
            AND (`a`.`rate` = `b`.`rate`)
          )
        )
    )
    LEFT JOIN
      (SELECT
        `g`.`country` AS `country`,
        `g`.`rate` AS `rate`,
        SUM(
          (
            `x0`.`real_lyb` / `x2`.`cash_to_lyb_rate`
          )
        ) AS `lybto_yx_amt`
      FROM
        (
          (
            (
              (
                (
                  (
                    `traffic`.`gp_tbl_recharge_record` `x0`
                    JOIN `traffic`.`gp_tbl_user_info` `x1`
                      ON ((`x0`.`user_id` = `x1`.`user_id`))
                  )
                  JOIN `traffic`.`gp_tbl_lyb_record` `x2`
                    ON (
                      (
                        `x0`.`orderno` = `x2`.`opposite_orderid`
                      )
                    )
                )
                JOIN `traffic`.`gp_tbl_platform_info` `x3`
                  ON ((`x0`.`plateform_id` = `x3`.`id`))
              )
              LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `g`
                ON ((`x3`.`country` = `g`.`country`))
            )
            LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x6`
              ON (
                (
                  (`x3`.`country` = `x6`.`country`)
                  AND (
                    `x0`.`currency` = `x6`.`currency`
                  )
                )
              )
          )
          LEFT JOIN `traffic`.`tbl_gp_exchangerate_config` `x7`
            ON (
              (
                (`x3`.`country` = `x7`.`country`)
                AND (
                  `x3`.`country_currency` = `x7`.`currency`
                )
              )
            )
        )
      WHERE (
          (`x0`.`change_type` = 2)
          AND (`x0`.`status` = 1)
          AND (`g`.`country` IS NOT NULL)
          AND (
            NOT (
              EXISTS
              (SELECT
                NULL
              FROM
                (
                  `traffic`.`gp_tbl_lyb_record` `x4`
                  JOIN `traffic`.`gp_tbl_toplate_errortrade` `x5`
                    ON ((`x4`.`orderid` = `x5`.`order_id`))
                )
              WHERE (
                  (
                    `x4`.`opposite_orderid` = `x0`.`orderno`
                  )
                  AND (`x5`.`status` <> 1)
                ))
            )
          )
        )
      GROUP BY `g`.`country`,
        `g`.`rate`) `c`
      ON (
        (
          (`a`.`country` = `c`.`country`)
          AND (`a`.`rate` = `c`.`rate`)
        )
      )
  ) ;

-- ----------------------------
-- view structure for view_gp_channel_operation_day
-- ----------------------------
DROP VIEW IF EXISTS `view_gp_channel_operation_day` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_gp_channel_operation_day` AS
SELECT
  `mid`.`stat_date` AS `stat_date`,
  `mid`.`channel_id` AS `channel_id`,
  `m`.`name` AS `product`,
  `mid`.`new_vister` AS `new_vister`,
  `mid`.`regist_user` AS `regist_user`,
  `mid`.`active_user` AS `active_user`,
  `mid`.`recharge_user` AS `recharge_user`,
  `mid`.`recharge_amount` AS `recharge_amount`,
  `mid`.`xh_amount` AS `xh_amount`,
  `mid`.`fc_rate` AS `fc_rate`,
  `mid`.`platform_income` AS `platform_income`,
  `mid`.`fc_income` AS `fc_income`,
  `p`.`name` AS `platform_terminal`
FROM
  (
    (
      (
        (
          (SELECT
            `mi`.`stat_date` AS `stat_date`,
            `mi`.`channel_id` AS `channel_id`,
            `mi`.`product` AS `product`,
            SUM(`mi`.`new_vister`) AS `new_vister`,
            SUM(`mi`.`regist_user`) AS `regist_user`,
            SUM(`mi`.`active_user`) AS `active_user`,
            SUM(`mi`.`recharge_user`) AS `recharge_user`,
            SUM(`mi`.`recharge_amount`) AS `recharge_amount`,
            SUM(`mi`.`xh_amount`) AS `xh_amount`,
            `mi`.`fc_rate` AS `fc_rate`,
            SUM(`mi`.`platform_income`) AS `platform_income`,
            SUM(`mi`.`fc_income`) AS `fc_income`,
            `mi`.`platform_terminal` AS `platform_terminal`
          FROM
            (SELECT
              `t`.`stat_date` AS `stat_date`,
              `t`.`channel_id` AS `channel_id`,
              `t`.`platform_id` AS `product`,
              0 AS `new_vister`,
              IFNULL(`t`.`reg_ucnt`, 0) AS `regist_user`,
              0 AS `active_user`,
              IFNULL(`t`.`recharge_ucnt`, 0) AS `recharge_user`,
              IFNULL(`t`.`recharge_amount`, 0) AS `recharge_amount`,
              0 AS `xh_amount`,
              IFNULL(`t`.`fc_rate`, 0) AS `fc_rate`,
              0 AS `platform_income`,
              0 AS `fc_income`,
              `t`.`platform_terminal` AS `platform_terminal`
            FROM
              (SELECT
                `e`.`stat_date` AS `stat_date`,
                `e`.`channel_id` AS `channel_id`,
                `e`.`platform_id` AS `platform_id`,
                `e`.`reg_ucnt` AS `reg_ucnt`,
                `e`.`recharge_ucnt` AS `recharge_ucnt`,
                `e`.`recharge_amount` AS `recharge_amount`,
                `e`.`active_ucnt` AS `active_ucnt`,
                `e`.`total_active_ucnt` AS `total_active_ucnt`,
                `e`.`active_rate` AS `active_rate`,
                `i`.`platform_terminal` AS `platform_terminal`,
                `l`.`splitratio` AS `fc_rate`
              FROM
                (
                  (
                    `traffic`.`view_inner_game_chnl_opt_day` `e`
                    LEFT JOIN `traffic`.`gp_tbl_platform_info` `i`
                      ON ((`e`.`platform_id` = `i`.`id`))
                  )
                  LEFT JOIN `traffic`.`channel` `l`
                    ON ((`e`.`channel_id` = `l`.`chid`))
                )
              WHERE (
                  (`e`.`channel_id` <> '-1')
                  AND (`e`.`platform_id` <> '-1')
                )) `t`
            UNION
            ALL
            SELECT
              DATE_FORMAT(`d`.`stat_date`, '%y%m%d') AS `stat_date`,
              `d`.`channel_id` AS `channel_id`,
              `d`.`platform_id` AS `product`,
              0 AS `new_vister`,
              0 AS `regist_user`,
              0 AS `active_user`,
              0 AS `recharge_user`,
              0 AS `recharge_amount`,
              IFNULL(`d`.`xh_amount`, 0) AS `xh_amount`,
              IFNULL(`d`.`fc_rate`, 0) AS `fc_rate`,
              IFNULL(`d`.`platform_income`, 0) AS `platform_income`,
              IFNULL(`d`.`fc_income`, 0) AS `fc_income`,
              `d`.`platform_terminal` AS `platform_terminal`
            FROM
              `traffic`.`tbl_gp_dm_xh_sy_day` `d`) `mi`
          GROUP BY `mi`.`stat_date`,
            `mi`.`channel_id`,
            `mi`.`fc_rate`,
            `mi`.`product`,
            `mi`.`platform_terminal`)
        ) `mid`
        LEFT JOIN `traffic`.`view_gp_dim_product_market1` `m`
          ON ((`mid`.`product` = `m`.`id`))
      )
      LEFT JOIN `traffic`.`tbl_gp_plat_terminal_info` `p`
        ON (
          (
            `mid`.`platform_terminal` = `p`.`id`
          )
        )
    )
    JOIN `traffic`.`channel` `ch`
      ON ((`mid`.`channel_id` = `ch`.`chid`))
  )
WHERE (`mid`.`channel_id` <> 'vnp56ams') ;

-- ----------------------------
-- view structure for view_gp_country_order
-- ----------------------------
DROP VIEW IF EXISTS `view_gp_country_order` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_gp_country_order` AS
SELECT
  `c`.`country` AS `country`,
  SUM(`c`.`regist_count`) AS `regist_count`,
  SUM(`c`.`user_count`) AS `user_count`,
  SUM(`c`.`recharge_count`) AS `recharge_count`,
  SUM(`c`.`recharge_amount`) AS `recharge_amount`,
  `c`.`create_time` AS `create_time`
FROM
  (SELECT
    `o`.`country` AS `country`,
    0 AS `regist_count`,
    `o`.`user_count` AS `user_count`,
    `o`.`recharge_count` AS `recharge_count`,
    `o`.`recharge_amount` AS `recharge_amount`,
    `o`.`create_time` AS `create_time`
  FROM
    `traffic`.`tbl_gp_country_order` `o`
  UNION
  ALL
  SELECT
    `p`.`country` AS `country`,
    `p`.`regist_count` AS `regist_count`,
    0 AS `user_count`,
    0 AS `recharge_count`,
    0 AS `recharge_amount`,
    `p`.`regist_time` AS `create_time`
  FROM
    (SELECT
      COUNT(`x1`.`user_id`) AS `regist_count`,
      `i`.`country` AS `country`,
      DATE_FORMAT(`x1`.`create_time`, '%y/%m/%d') AS `regist_time`
    FROM
      (
        `traffic`.`gp_tbl_user_info` `x1`
        JOIN `traffic`.`gp_tbl_platform_info` `i`
      )
    WHERE (`x1`.`platform_id` = `i`.`id`)
    GROUP BY DATE_FORMAT(`x1`.`create_time`, '%y/%m/%d'),
      `i`.`country`) `p`) `c`
GROUP BY `c`.`country`,
  `c`.`create_time`
ORDER BY `c`.`create_time` DESC ;

-- ----------------------------
-- view structure for view_gp_country_plateform
-- ----------------------------
DROP VIEW IF EXISTS `view_gp_country_plateform` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_gp_country_plateform` AS
SELECT
  `c`.`platform_terminal` AS `platform_terminal`,
  `c`.`platform_id` AS `platform_id`,
  SUM(`c`.`recharge_amount`) AS `recharge_amount`,
  SUM(`c`.`user_count`) AS `user_count`,
  SUM(`c`.`recharge_count`) AS `recharge_count`,
  `c`.`create_time` AS `create_time`,
  `c`.`country` AS `country`,
  SUM(`c`.`online_count`) AS `online_count`
FROM
  (SELECT
    `p`.`platform_terminal` AS `platform_terminal`,
    `p`.`platform_id` AS `platform_id`,
    IFNULL(`p`.`recharge_amount`, 0) AS `recharge_amount`,
    IFNULL(`p`.`user_count`, 0) AS `user_count`,
    IFNULL(`p`.`recharge_count`, 0) AS `recharge_count`,
    `p`.`create_time` AS `create_time`,
    `p`.`country` AS `country`,
    0 AS `online_count`
  FROM
    `traffic`.`tbl_gp_country_plateform` `p`
  UNION
  ALL
  SELECT
    `t`.`device_type` AS `platform_terminal`,
    `t`.`platform_id` AS `platform_id`,
    0 AS `recharge_amount`,
    0 AS `user_count`,
    0 AS `recharge_count`,
    `t`.`login_time` AS `create_time`,
    `t`.`country` AS `country`,
    IFNULL(`t`.`online_count`, 0) AS `online_count`
  FROM
    (SELECT
      `k`.`platformid` AS `platform_id`,
      `k`.`os_type` AS `device_type`,
      `i`.`country` AS `country`,
      DATE_FORMAT(`k`.`login_time`, '%y/%m/%d') AS `login_time`,
      COUNT(DISTINCT `k`.`user_name`) AS `online_count`
    FROM
      (
        `traffic`.`gp_tbl_login_track` `k`
        LEFT JOIN `traffic`.`gp_tbl_platform_info` `i`
          ON ((`k`.`platformid` = `i`.`id`))
      )
    WHERE (
        (`k`.`login_result` = 1)
        AND (`k`.`user_name` IS NOT NULL)
        AND (`k`.`platformid` IS NOT NULL)
      )
    GROUP BY `k`.`platformid`,
      `k`.`os_type`,
      `i`.`country`,
      DATE_FORMAT(`k`.`login_time`, '%y/%m/%d')) `t`) `c`
GROUP BY `c`.`platform_terminal`,
  `c`.`platform_id`,
  `c`.`create_time`,
  `c`.`country`
ORDER BY `c`.`create_time` DESC ;

-- ----------------------------
-- view structure for view_gp_dim_product_market
-- ----------------------------
DROP VIEW IF EXISTS `view_gp_dim_product_market` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_gp_dim_product_market` AS
SELECT
  `i`.`id` AS `id`,
  `i`.`name` AS `name`,
  `i`.`type` AS `type`,
  `i`.`is_game` AS `is_game`,
  `i`.`create_time` AS `create_time`,
  `i`.`update_time` AS `update_time`,
  `i`.`developers` AS `developers`,
  `i`.`country` AS `country`,
  `i`.`platform_terminal` AS `platform_terminal`,
  `i`.`country_currency` AS `country_currency`
FROM
  `gp_tbl_platform_info` `i`
WHERE (
    (
      (`i`.`name` LIKE '%电竞%')
      OR (`i`.`name` LIKE '%撩妹%')
      OR (`i`.`name` LIKE '%玩一下%')
    )
    AND (`i`.`country` = 0)
    AND (`i`.`platform_terminal` = 1)
  ) ;

-- ----------------------------
-- view structure for view_gp_dim_product_market1
-- ----------------------------
DROP VIEW IF EXISTS `view_gp_dim_product_market1` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_gp_dim_product_market1` AS
SELECT
  `i`.`id` AS `id`,
  `i`.`name` AS `name`,
  `i`.`type` AS `type`,
  `i`.`is_game` AS `is_game`,
  `i`.`create_time` AS `create_time`,
  `i`.`update_time` AS `update_time`,
  `i`.`developers` AS `developers`,
  `i`.`country` AS `country`,
  `i`.`platform_terminal` AS `platform_terminal`,
  `i`.`country_currency` AS `country_currency`
FROM
  `gp_tbl_platform_info` `i`
WHERE (
    (
      (`i`.`name` LIKE '%电竞%')
      OR (`i`.`name` LIKE '%撩妹%')
      OR (`i`.`name` LIKE '%玩一下%')
    )
    AND (`i`.`country` = 0)
  ) ;

-- ----------------------------
-- view structure for view_gp_order_detail_market
-- ----------------------------
DROP VIEW IF EXISTS `view_gp_order_detail_market` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_gp_order_detail_market` AS
SELECT
  `x2`.`business_no` AS `payment_orderno`,
  IFNULL(
    `x4`.`change_name`,
    `x0`.`change_type`
  ) AS `order_type`,
  IFNULL(
    `x3`.`name`,
    `x0`.`plateform_id`
  ) AS `platform_id`,
  `x0`.`son_plateformid` AS `server_id`,
  `x0`.`apply_amount` AS `amount`,
  `x0`.`apply_time` AS `create_time`,
  `x0`.`handle_time` AS `pay_time`,
  IFNULL(
    `x5`.`status_name`,
    `x0`.`status`
  ) AS `pay_status`,
  IFNULL(
    `x8`.`recharge_name`,
    `x0`.`platform_type`
  ) AS `recharge_way`,
  `x1`.`user_id` AS `user_id`,
  `x1`.`channel_id` AS `channel_id`,
  IFNULL(
    `x9`.`name`,
    `x3`.`platform_terminal`
  ) AS `name`
FROM
  (
    (
      (
        (
          (
            (
              (
                (
                  (
                    `gp_tbl_recharge_record` `x0`
                    JOIN `gp_tbl_user_info` `x1`
                      ON ((`x0`.`user_id` = `x1`.`user_id`))
                  )
                  JOIN `gp_tbl_pay_bussiness_info` `x2`
                    ON (
                      (
                        `x0`.`orderno` = `x2`.`out_trade_no`
                      )
                    )
                )
                JOIN `view_gp_dim_product_market1` `x3`
                  ON ((`x0`.`plateform_id` = `x3`.`id`))
              )
              LEFT JOIN `tbl_gp_plat_terminal_info` `x9`
                ON (
                  (
                    `x3`.`platform_terminal` = `x9`.`id`
                  )
                )
            )
            LEFT JOIN `tbl_gp_exchangerate_config` `x6`
              ON (
                (
                  (`x3`.`country` = `x6`.`country`)
                  AND (
                    `x0`.`currency` = `x6`.`currency`
                  )
                )
              )
          )
          LEFT JOIN `tbl_gp_exchangerate_config` `x7`
            ON (
              (
                (`x3`.`country` = `x7`.`country`)
                AND (
                  `x3`.`country_currency` = `x7`.`currency`
                )
              )
            )
        )
        LEFT JOIN `tbl_gp_dim_order_status_market` `x5`
          ON ((`x0`.`status` = `x5`.`status_id`))
      )
      LEFT JOIN `tbl_gp_dim_change_type_market` `x4`
        ON (
          (
            `x0`.`change_type` = `x4`.`change_id`
          )
        )
    )
    LEFT JOIN `tbl_gp_dim_recharge_way_market` `x8`
      ON (
        (
          `x0`.`platform_type` = `x8`.`recharge_id`
        )
      )
  )
WHERE (`x0`.`change_type` = 3)
  UNION
  ALL
  SELECT
    `x2`.`business_no` AS `payment_orderno`,
    IFNULL(
      `x4`.`change_name`,
      `x0`.`change_type`
    ) AS `order_type`,
    IFNULL(
      `x3`.`name`,
      `x0`.`plateform_id`
    ) AS `platform_id`,
    `x0`.`son_plateformid` AS `server_id`,
    `x0`.`apply_amount` AS `amount`,
    `x0`.`apply_time` AS `create_time`,
    `x0`.`handle_time` AS `pay_time`,
    IFNULL(
      `x5`.`status_name`,
      `x0`.`status`
    ) AS `pay_status`,
    IFNULL(
      `x8`.`recharge_name`,
      `x0`.`platform_type`
    ) AS `recharge_way`,
    `x1`.`user_id` AS `user_id`,
    `x1`.`channel_id` AS `channel_id`,
    IFNULL(
      `x9`.`name`,
      `x3`.`platform_terminal`
    ) AS `name`
  FROM
    (
      (
        (
          (
            (
              (
                (
                  (
                    (
                      `gp_tbl_recharge_record` `x0`
                      JOIN `gp_tbl_user_info` `x1`
                        ON ((`x0`.`user_id` = `x1`.`user_id`))
                    )
                    JOIN `gp_tbl_pay_bussiness_info` `x2`
                      ON (
                        (
                          `x0`.`orderno` = `x2`.`out_trade_no`
                        )
                      )
                  )
                  JOIN `view_gp_dim_product_market1` `x3`
                    ON ((`x0`.`plateform_id` = `x3`.`id`))
                )
                LEFT JOIN `tbl_gp_plat_terminal_info` `x9`
                  ON (
                    (
                      `x3`.`platform_terminal` = `x9`.`id`
                    )
                  )
              )
              LEFT JOIN `tbl_gp_exchangerate_config` `x6`
                ON (
                  (
                    (`x3`.`country` = `x6`.`country`)
                    AND (
                      `x0`.`currency` = `x6`.`currency`
                    )
                  )
                )
            )
            LEFT JOIN `tbl_gp_exchangerate_config` `x7`
              ON (
                (
                  (`x3`.`country` = `x7`.`country`)
                  AND (
                    `x3`.`country_currency` = `x7`.`currency`
                  )
                )
              )
          )
          LEFT JOIN `tbl_gp_dim_order_status_market` `x5`
            ON ((`x0`.`status` = `x5`.`status_id`))
        )
        LEFT JOIN `tbl_gp_dim_change_type_market` `x4`
          ON (
            (
              `x0`.`change_type` = `x4`.`change_id`
            )
          )
      )
      LEFT JOIN `tbl_gp_dim_recharge_way_market` `x8`
        ON (
          (
            `x0`.`platform_type` = `x8`.`recharge_id`
          )
        )
    )
  WHERE (`x0`.`change_type` = 1) ;

-- ----------------------------
-- view structure for view_inner_game_chnl_opt_day
-- ----------------------------
DROP VIEW IF EXISTS `view_inner_game_chnl_opt_day` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_inner_game_chnl_opt_day` AS
SELECT
  `t`.`stat_date` AS `stat_date`,
  `t`.`channel_id` AS `channel_id`,
  `t`.`platform_id` AS `platform_id`,
  `t`.`reg_ucnt` AS `reg_ucnt`,
  `t`.`recharge_ucnt` AS `recharge_ucnt`,
  `t`.`recharge_amount` AS `recharge_amount`,
  0 AS `active_ucnt`,
  0 AS `total_active_ucnt`,
  0 AS `active_rate`
FROM
  `tbl_dm_inner_game_chnl_opt_day` `t` ;

-- ----------------------------
-- view structure for view_inner_game_chnl_opt_h
-- ----------------------------
DROP VIEW IF EXISTS `view_inner_game_chnl_opt_h` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_inner_game_chnl_opt_h` AS
SELECT
  `t`.`stat_date` AS `stat_date`,
  `t`.`channel_id` AS `channel_id`,
  `t`.`platform_id` AS `platform_id`,
  `t`.`reg_ucnt` AS `reg_ucnt`,
  `t`.`recharge_ucnt` AS `recharge_ucnt`,
  `t`.`recharge_amount` AS `recharge_amount`,
  0 AS `active_ucnt`,
  0 AS `total_active_ucnt`,
  0 AS `active_rate`
FROM
  `tbl_dm_inner_game_chnl_opt_h` `t` ;

-- ----------------------------
-- view structure for view_lmdz_channel_daily
-- ----------------------------
DROP VIEW IF EXISTS `view_lmdz_channel_daily` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_lmdz_channel_daily` AS
SELECT
  `r1`.`channel_id` AS `channel_id`,
  `r1`.`os_type` AS `os_type`,
  `r2`.`name` AS `os_name`,
  `r1`.`reg_date` AS `reg_date`,
  `r1`.`user_id` AS `user_id`,
  `r1`.`grade` AS `grade`,
  `r1`.`recharge_amount` AS `recharge_amount`,
  `r1`.`inning` AS `inning`
FROM
  (
    (
      (SELECT
        `x1`.`channel_id` AS `channel_id`,
        `x1`.`os_type` AS `os_type`,
        `x0`.`user_id` AS `user_id`,
        DATE_FORMAT(`x1`.`create_time`, '%y%m%d') AS `reg_date`,
        `x0`.`grade` AS `grade`,
        SUM(`x0`.`recharge_amount`) AS `recharge_amount`,
        COUNT(DISTINCT `x2`.`bureauno`) AS `inning`
      FROM
        (
          (
            `traffic`.`tbl_lmdz_channel_daily` `x0`
            LEFT JOIN `traffic`.`gp_tbl_user_info` `x1`
              ON ((`x0`.`user_id` = `x1`.`user_id`))
          )
          LEFT JOIN `traffic`.`tbl_ods_lmdz_betting_log` `x2`
            ON ((`x0`.`user_id` = `x2`.`user_id`))
        )
      GROUP BY `x1`.`channel_id`,
        `x1`.`os_type`,
        `x0`.`user_id`,
        DATE_FORMAT(`x1`.`create_time`, '%y%m%d'),
        `x0`.`grade`)
    ) `r1`
    LEFT JOIN `traffic`.`tbl_gp_plat_terminal_info` `r2`
      ON ((`r1`.`os_type` = `r2`.`id`))
  ) ;

-- ----------------------------
-- view structure for view_lmdz_channel_daily1
-- ----------------------------
DROP VIEW IF EXISTS `view_lmdz_channel_daily1` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_lmdz_channel_daily1` AS
SELECT
  `r1`.`channel_id` AS `channel_id`,
  `r1`.`os_type` AS `os_type`,
  `r2`.`name` AS `os_name`,
  `r1`.`reg_date` AS `reg_date`,
  `r1`.`user_id` AS `user_id`,
  `r1`.`grade` AS `grade`,
  `r1`.`recharge_amount` AS `recharge_amount`,
  `r1`.`inning` AS `inning`
FROM
  (
    (
      (SELECT
        `x1`.`channel_id` AS `channel_id`,
        `x1`.`os_type` AS `os_type`,
        `x0`.`user_id` AS `user_id`,
        DATE_FORMAT(`x1`.`create_time`, '%y%m%d') AS `reg_date`,
        `x0`.`grade` AS `grade`,
        SUM(`x0`.`recharge_amount`) AS `recharge_amount`,
        COUNT(DISTINCT `x2`.`bureauno`) AS `inning`
      FROM
        (
          (
            `traffic`.`tbl_lmdz_channel_daily` `x0`
            LEFT JOIN `traffic`.`gp_tbl_user_info` `x1`
              ON ((`x0`.`user_id` = `x1`.`user_id`))
          )
          LEFT JOIN `traffic`.`tbl_ods_lmdz_betting_log` `x2`
            ON ((`x0`.`user_id` = `x2`.`user_id`))
        )
      GROUP BY `x1`.`channel_id`,
        `x1`.`os_type`,
        `x0`.`user_id`,
        DATE_FORMAT(`x1`.`create_time`, '%y%m%d'),
        `x0`.`grade`)
    ) `r1`
    LEFT JOIN `traffic`.`tbl_gp_plat_terminal_info` `r2`
      ON ((`r1`.`os_type` = `r2`.`id`))
  ) ;

-- ----------------------------
-- view structure for view_regist_active_day
-- ----------------------------
DROP VIEW IF EXISTS `view_regist_active_day` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_regist_active_day` AS
SELECT
  `ac`.`active_date` AS `active_date`,
  `ac`.`channel_id` AS `channel_id`,
  0 AS `download_times`,
  IFNULL(`re`.`regist_users`, 0) AS `regist_users`,
  `ac`.`active_users` AS `active_users`
FROM
  (
    (
      (SELECT
        DATE_FORMAT(
          DATE_FORMAT(
            `t`.`login_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m%d'
        ) AS `active_date`,
        IFNULL(`f`.`channel_id`, 'vnp56ams') AS `channel_id`,
        COUNT(DISTINCT `t`.`user_name`) AS `active_users`
      FROM
        (
          `traffic`.`gp_tbl_login_track` `t`
          LEFT JOIN `traffic`.`gp_tbl_user_info` `f`
            ON ((`t`.`user_name` = `f`.`user_id`))
        )
      GROUP BY DATE_FORMAT(
          DATE_FORMAT(
            `t`.`login_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m%d'
        ),
        IFNULL(`f`.`channel_id`, 'vnp56ams'))
    ) `ac`
    LEFT JOIN
      (SELECT
        DATE_FORMAT(
          DATE_FORMAT(
            `i`.`create_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m%d'
        ) AS `regist_date`,
        IFNULL(`i`.`channel_id`, 'vnp56ams') AS `channel_id`,
        COUNT(DISTINCT `i`.`user_id`) AS `regist_users`
      FROM
        `traffic`.`gp_tbl_user_info` `i`
      GROUP BY DATE_FORMAT(
          DATE_FORMAT(
            `i`.`create_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m%d'
        ),
        IFNULL(`i`.`channel_id`, 'vnp56ams')) `re`
      ON (
        (
          (
            `ac`.`active_date` = `re`.`regist_date`
          )
          AND (
            `ac`.`channel_id` = `re`.`channel_id`
          )
        )
      )
  ) ;

-- ----------------------------
-- view structure for view_regist_active_month
-- ----------------------------
DROP VIEW IF EXISTS `view_regist_active_month` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_regist_active_month` AS
SELECT
  `ac`.`active_month` AS `active_month`,
  `ac`.`channel_id` AS `channel_id`,
  0 AS `download_times`,
  IFNULL(`re`.`regist_users`, 0) AS `regist_users`,
  `ac`.`active_users` AS `active_users`
FROM
  (
    (
      (SELECT
        DATE_FORMAT(
          DATE_FORMAT(
            `t`.`login_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m'
        ) AS `active_month`,
        IFNULL(`f`.`channel_id`, 'vnp56ams') AS `channel_id`,
        COUNT(DISTINCT `t`.`user_name`) AS `active_users`
      FROM
        (
          `traffic`.`gp_tbl_login_track` `t`
          LEFT JOIN `traffic`.`gp_tbl_user_info` `f`
            ON ((`t`.`user_name` = `f`.`user_id`))
        )
      GROUP BY DATE_FORMAT(
          DATE_FORMAT(
            `t`.`login_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m'
        ),
        IFNULL(`f`.`channel_id`, 'vnp56ams'))
    ) `ac`
    LEFT JOIN
      (SELECT
        DATE_FORMAT(
          DATE_FORMAT(
            `i`.`create_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m'
        ) AS `regist_month`,
        IFNULL(`i`.`channel_id`, 'vnp56ams') AS `channel_id`,
        COUNT(DISTINCT `i`.`user_id`) AS `regist_users`
      FROM
        `traffic`.`gp_tbl_user_info` `i`
      GROUP BY DATE_FORMAT(
          DATE_FORMAT(
            `i`.`create_time`,
            '%y-%m-%d %h:%i:%s'
          ),
          '%y%m'
        ),
        IFNULL(`i`.`channel_id`, 'vnp56ams')) `re`
      ON (
        (
          (
            `ac`.`active_month` = `re`.`regist_month`
          )
          AND (
            `ac`.`channel_id` = `re`.`channel_id`
          )
        )
      )
  ) ;

-- ----------------------------
-- view structure for view_user_account_info
-- ----------------------------
DROP VIEW IF EXISTS `view_user_account_info` ;

CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`%` SQL SECURITY DEFINER VIEW `view_user_account_info` AS
SELECT
  `t1`.`user_id` AS `user_id`,
  `t1`.`nickname` AS `nickname`,
  IFNULL(`t2`.`cash`, 0) AS `lyb_cnt`,
  IFNULL(`t2`.`lyq`, 0) AS `lyq_cnt`,
  IFNULL(`t3`.`djb_cnt`, 0) AS `djb_cnt`,
  IFNULL(`t3`.`dzb_cnt`, 0) AS `dzb_cnt`
FROM
  (
    (
      `traffic`.`gp_tbl_user_info` `t1`
      LEFT JOIN `traffic`.`tbl_gp_user_funds` `t2`
        ON ((`t1`.`user_id` = `t2`.`user_id`))
    )
    LEFT JOIN
      (SELECT
        `tn1`.`user_id` AS `user_id`,
        SUM(
          IFNULL(
            (
              CASE
                WHEN (
                  `tn1`.`dir_code` IN ('lydj', 'lydj_data')
                )
                THEN `tn1`.`account_balance`
              END
            ),
            0
          )
        ) AS `djb_cnt`,
        SUM(
          IFNULL(
            (
              CASE
                WHEN (
                  `tn1`.`dir_code` IN ('hyzb', 'hyzb_data')
                )
                THEN `tn1`.`account_balance`
              END
            ),
            0
          )
        ) AS `dzb_cnt`
      FROM
        (
          `traffic`.`tbl_game_user_account` `tn1`
          JOIN
            (SELECT
              `tn2`.`dir_code` AS `dir_code`,
              `tn2`.`user_id` AS `user_id`,
              MAX(`tn2`.`create_date`) AS `create_date`
            FROM
              `traffic`.`tbl_game_user_account` `tn2`
            GROUP BY `tn2`.`dir_code`,
              `tn2`.`user_id`) `tn3`
            ON (
              (
                (
                  `tn1`.`dir_code` = `tn3`.`dir_code`
                )
                AND (
                  `tn1`.`user_id` = `tn3`.`user_id`
                )
                AND (
                  `tn1`.`create_date` = `tn3`.`create_date`
                )
              )
            )
        )
      GROUP BY `tn1`.`user_id`) `t3`
      ON ((`t1`.`user_id` = `t3`.`user_id`))
  ) ;

