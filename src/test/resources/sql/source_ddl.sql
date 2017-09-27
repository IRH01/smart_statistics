DROP TABLE IF EXISTS `data_game_start`;
DROP TABLE IF EXISTS `data_installs`;
DROP TABLE IF EXISTS `data_interface_invoke`;
DROP TABLE IF EXISTS `data_platform_start`;
DROP TABLE IF EXISTS `data_view`;

CREATE TABLE `data_game_start` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '点击平台按钮‘开始游戏’',
  `country` int(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `client_identity` varchar(100) NOT NULL DEFAULT '' COMMENT '客户端标识,识别游客访问',
  `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '账户（userId）',
  `platform_terminal` int(11) NOT NULL DEFAULT '0' COMMENT '终端:1.pc 2.安卓 3ios 4.h5 5.其他',
  `platform_id` int(11) NOT NULL DEFAULT '0' COMMENT '平台Id:默认为玩一下平台;PC=1,移动端为31111',
  `website_domain` varchar(50) NOT NULL DEFAULT '' COMMENT '域名',
  `url_path` varchar(300) NOT NULL DEFAULT '' COMMENT '网站路径，不带参数,不计域名',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点击平台按钮‘开始游戏’';

CREATE TABLE `data_installs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '手机安装量',
  `unique_no` varchar(100) NOT NULL DEFAULT '' COMMENT '手机唯一号（MAC地址/imei）',
  `platform_terminal` int(11) NOT NULL DEFAULT '0' COMMENT '终端:2.安卓 3.ios',
  `country` int(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `longitude` double(14,5) NOT NULL DEFAULT '0.00000' COMMENT '经度',
  `latitude` double(14,5) NOT NULL DEFAULT '0.00000' COMMENT '纬度',
  `ip` varchar(20) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_no` (`unique_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手机安装量';

CREATE TABLE `data_interface_invoke` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '接口调用',
  `country` int(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '账户（userId）',
  `platform_id` int(11) NOT NULL DEFAULT '0' COMMENT '平台Id:默认为玩一下平台;PC=1,移动端为31111',
  `platform_terminal` int(11) NOT NULL DEFAULT '0' COMMENT '终端:1.pc 2.安卓 3ios 4.h5 5.其他',
  `interface_type` int(11) NOT NULL DEFAULT '0' COMMENT '接口类型:1.注册接口 2.充值接口',
  `website_domain` varchar(50) NOT NULL DEFAULT '' COMMENT '域名',
  `url_path` varchar(300) NOT NULL DEFAULT '' COMMENT '网站路径，不带参数,不计域名',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口调用';

CREATE TABLE `data_platform_start` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '平台App启动日志',
  `country` int(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `unique_no` varchar(100) NOT NULL DEFAULT '' COMMENT '手机唯一号（MAC地址/imei）',
  `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '账户(userId)',
  `platform_terminal` int(11) NOT NULL DEFAULT '0' COMMENT '终端:2.安卓 3.ios',
  `longitude` double(14,5) NOT NULL DEFAULT '0.00000' COMMENT '经度',
  `latitude` double(14,5) NOT NULL DEFAULT '0.00000' COMMENT '纬度',
  `ip` varchar(20) NOT NULL DEFAULT '' COMMENT 'Ip地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台App启动日志,每次启动平台就记录一条日志';

CREATE TABLE `data_view` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PV/UV',
  `country` int(11) NOT NULL DEFAULT '0' COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `client_identity` varchar(100) NOT NULL DEFAULT '' COMMENT '客户端标识,识别游客访问(区分PV 和UV)',
  `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '账户（userId）',
  `platform_terminal` int(11) NOT NULL DEFAULT '0' COMMENT '终端:1.pc 4.h5 5.其他',
  `website_domain` varchar(50) NOT NULL DEFAULT '' COMMENT '域名',
  `page_view` int(11) NOT NULL DEFAULT '0' COMMENT '浏览页面: 1.首页 2.注册 3.充值',
  `url_path` varchar(300) NOT NULL DEFAULT '' COMMENT '网站路径，不带参数,不计域名',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='PV/UV';

