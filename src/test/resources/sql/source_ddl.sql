USE `smartdata`;

/*Table structure for table `data_game_start` */

DROP TABLE IF EXISTS `data_game_start`;

CREATE TABLE `data_game_start` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '点击平台按钮‘开始游戏’',
  `country` INT(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `client_identity` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户端标识,识别游客访问',
  `user_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '账户（userId）',
  `platform_terminal` INT(11) NOT NULL DEFAULT '0' COMMENT '终端:1.pc 2.安卓 3ios 4.h5 5.其他',
  `platform_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '平台游戏编码',
  `platform_id` INT(11) NOT NULL DEFAULT '0' COMMENT '平台Id:默认为玩一下平台;PC=1,移动端为31111',
  `platform_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '平台名称',
  `website_domain` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '域名',
  `url_path` VARCHAR(300) NOT NULL DEFAULT '' COMMENT '网站路径，不带参数,不计域名',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=759 DEFAULT CHARSET=utf8 COMMENT='点击平台按钮‘开始游戏’';

/*Table structure for table `data_installs` */

DROP TABLE IF EXISTS `data_installs`;

CREATE TABLE `data_installs` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '手机安装量',
  `unique_no` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '手机唯一号（MAC地址/imei）',
  `platform_terminal` INT(11) NOT NULL DEFAULT '0' COMMENT '终端:2.安卓 3.ios',
  `country` INT(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `longitude` DOUBLE(14,5) NOT NULL DEFAULT '0.00000' COMMENT '经度',
  `latitude` DOUBLE(14,5) NOT NULL DEFAULT '0.00000' COMMENT '纬度',
  `ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_no` (`unique_no`)
) ENGINE=INNODB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='手机安装量';

/*Table structure for table `data_interface_invoke` */

DROP TABLE IF EXISTS `data_interface_invoke`;

CREATE TABLE `data_interface_invoke` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '接口调用',
  `country` INT(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `user_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '账户（userId,手机号，邮箱形式）',
  `platform_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '平台游戏编码',
  `platform_id` INT(11) NOT NULL DEFAULT '0' COMMENT '平台Id:默认为玩一下平台;PC=1,移动端为31111',
  `platform_terminal` INT(11) NOT NULL DEFAULT '0' COMMENT '终端:1.pc 2.安卓 3ios 4.h5 5.其他',
  `interface_type` INT(11) NOT NULL DEFAULT '0' COMMENT '接口类型:1.注册接口 2.充值接口',
  `website_domain` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '域名',
  `url_path` VARCHAR(300) NOT NULL DEFAULT '' COMMENT '网站路径，不带参数,不计域名',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=677 DEFAULT CHARSET=utf8 COMMENT='接口调用';

/*Table structure for table `data_platform_start` */

DROP TABLE IF EXISTS `data_platform_start`;

CREATE TABLE `data_platform_start` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '平台App启动日志',
  `country` INT(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `unique_no` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '手机唯一号（MAC地址/imei）',
  `user_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '账户(userId)',
  `platform_terminal` INT(11) NOT NULL DEFAULT '0' COMMENT '终端:2.安卓 3.ios',
  `longitude` DOUBLE(14,5) NOT NULL DEFAULT '0.00000' COMMENT '经度',
  `latitude` DOUBLE(14,5) NOT NULL DEFAULT '0.00000' COMMENT '纬度',
  `ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT 'Ip地址',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8 COMMENT='平台App启动日志,每次启动平台就记录一条日志';

/*Table structure for table `data_view` */

DROP TABLE IF EXISTS `data_view`;

CREATE TABLE `data_view` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PV/UV',
  `country` INT(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `client_identity` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户端标识,识别游客访问(游客的用户依据字段表示。)',
  `user_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '账户（userId），游客的这个字段为空。',
  `platform_terminal` INT(11) NOT NULL DEFAULT '0' COMMENT '终端:1.pc 4.h5 5.其他',
  `website_domain` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '域名',
  `page_view` INT(11) NOT NULL DEFAULT '0' COMMENT '浏览页面: 1.首页 2.注册 3.充值',
  `url_path` VARCHAR(300) NOT NULL DEFAULT '' COMMENT '网站路径，不带参数,不计域名',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `platform_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '平台游戏编码',
  `platform_id` INT(11) NOT NULL DEFAULT '0' COMMENT '平台Id:默认为玩一下平台;PC=1,移动端为31111',
  `platform_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '平台名称',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5813 DEFAULT CHARSET=utf8 COMMENT='PV/UV';