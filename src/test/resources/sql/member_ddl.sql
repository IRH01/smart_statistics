USE `member`;

/*Table structure for table `tbl_login_track` */

DROP TABLE IF EXISTS `tbl_login_track`;

CREATE TABLE `tbl_login_track` (
  `id` bigint(38) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL COMMENT '登录用户名',
  `login_result` int(11) DEFAULT '1' COMMENT '登录结果 0 失败 1 成功',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(150) DEFAULT NULL COMMENT '登录ip地址',
  `os_type` int(38) DEFAULT NULL,
  `version_code` int(11) DEFAULT NULL COMMENT '版本编号',
  `version_name` varchar(255) DEFAULT NULL COMMENT '版本名称',
  `platformid` int(11) DEFAULT NULL COMMENT '平台id',
  `gamearea_id` int(11) DEFAULT NULL COMMENT '具体平台下面的区（目前此字段保留 没意义）',
  `service_id` decimal(10,0) DEFAULT NULL COMMENT '服表 主键 id',
  `login_ip_area` varchar(200) DEFAULT NULL,
  `is_third` int(2) DEFAULT '0' COMMENT '布尔型：是否独立账户登录(1是|0否)',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=308109 DEFAULT CHARSET=utf8 COMMENT='登录日志';

/*Table structure for table `tbl_recharge_record` */

DROP TABLE IF EXISTS `tbl_recharge_record`;

CREATE TABLE `tbl_recharge_record` (
  `orderno` varchar(50) NOT NULL COMMENT '充值订单号 pk',
  `user_id` varchar(50) NOT NULL COMMENT '用户唯一标识',
  `apply_amount` decimal(38,4) DEFAULT NULL COMMENT '充值金额',
  `real_lyb` double(38,4) DEFAULT NULL COMMENT '（暂且不用）兑换(用乐盈币支付)时候需传值，其他走第三方 充值时候 （关系真正钱时候） 不用传值',
  `status` int(38) DEFAULT NULL COMMENT '第三方充值通知状态(0：处理中1：成功2：失败 3关闭)',
  `platform_type` int(38) DEFAULT NULL COMMENT '充值渠道标识1：微信支付  2：支付宝 3：乐盈币兑换  4：银联充值 5：优贝支付6：cc电子钱包  7：cc点卡 9：mol电子钱包  10：mol点卡 11：越南cardchargepay-->改为苹果内购',
  `apply_time` datetime DEFAULT NULL COMMENT '充值申请时间',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `platform_order` varchar(50) DEFAULT NULL COMMENT '充值平台订单号',
  `handle_type` int(38) unsigned DEFAULT NULL COMMENT '充值订单处理方式 1, 在线充值 2系统自动充值',
  `source` int(38) unsigned DEFAULT NULL COMMENT '操作终端（1pc  2安卓  3ios  4h5 5其他）',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `operatorid` int(38) DEFAULT NULL COMMENT '充值操作人',
  `payment_orderno` varchar(50) DEFAULT NULL COMMENT '业务订单号',
  `notify_status` int(38) DEFAULT NULL COMMENT '支付通知(0：处理中1：成功2：失败)',
  `platform_source` decimal(10,0) DEFAULT NULL COMMENT '平台来源(从哪跳过来充值的)',
  `change_type` int(38) DEFAULT NULL COMMENT '暂且不用',
  `plateform_id` bigint(10) DEFAULT NULL COMMENT '平台id',
  `son_plateformid` bigint(10) DEFAULT NULL COMMENT '具体平台下面的区服',
  `service_id` bigint(10) DEFAULT NULL COMMENT '具体平台下面的服id',
  `currency` varchar(255) DEFAULT NULL COMMENT '币种名称',
  `gold` double(10,4) DEFAULT '0.0000' COMMENT '对应的金币',
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`orderno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值记录表';

/*Table structure for table `tbl_user_info` */

DROP TABLE IF EXISTS `tbl_user_info`;

CREATE TABLE `tbl_user_info` (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `head_icon` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像',
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `sex` int(1) DEFAULT NULL COMMENT '性别 1男 2女',
  `age` int(1) DEFAULT NULL COMMENT '年龄',
  `location` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '所在地',
  `status` int(10) DEFAULT NULL COMMENT '0 禁用 1 未激活 2 已激活',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `real_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '真实姓名',
  `id_card_no` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证号码',
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '详细地址',
  `qq_number` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT 'qq号码',
  `phone_status` int(5) DEFAULT '0' COMMENT '手机绑定状态（0未有 1绑定）',
  `email_status` int(5) DEFAULT '0' COMMENT '邮箱绑定状态（0未有 1绑定）',
  `platform_id` int(5) DEFAULT NULL COMMENT '平台id',
  `reg_type` int(5) DEFAULT '1' COMMENT '注册类型 1 用户名 2 手机 3 邮箱 4 第三方登录 5游客身份注册 6活动创建用户',
  `paypassword` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '支付密码',
  `os_type` int(5) DEFAULT '1' COMMENT '来源（1pc2ios3安卓4其他）',
  `version_code` int(10) DEFAULT NULL COMMENT '版本编号',
  `version_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '版本名称',
  `head_icon_small` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '小图像',
  `login_type` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录注册类型 0：平台注册登录；1：qq注册登录；2：新浪微博注册登录；3：微信注册登录；4：支付宝注册登录；5：twitter注册登录；6：facebook注册登录；',
  `is_adult` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否是成年人（0未成年 1成年人）',
  `forbidden_starttime` datetime DEFAULT NULL COMMENT '禁用开始时间',
  `forbidden_enttime` datetime DEFAULT NULL COMMENT '禁用结束时间',
  `country` int(2) DEFAULT '0' COMMENT '0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `last_login_ip` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后登录ip',
  `register_ip` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '注册ip',
  `forbidden_remak` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '禁用备注',
  `invite_code` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '邀请码',
  `channelid` varchar(255) CHARACTER SET utf8 DEFAULT 'vnp56ams' COMMENT '来源渠道',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';
