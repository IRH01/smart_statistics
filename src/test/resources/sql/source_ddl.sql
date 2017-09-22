DROP TABLE IF EXISTS `tbl_data_game_start`;
DROP TABLE IF EXISTS `tbl_data_installs`;
DROP TABLE IF EXISTS `tbl_data_interface_invoke`;
DROP TABLE IF EXISTS `tbl_data_platform_start`;
DROP TABLE IF EXISTS `tbl_data_view`;

CREATE TABLE `tbl_data_game_start` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '点击平台按钮‘开始游戏’',
  `country` int(11) DEFAULT NULL COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `client_identity` varchar(100) DEFAULT NULL COMMENT '客户端标识,识别游客访问',
  `user_id` varchar(50) DEFAULT NULL COMMENT '账户（userId）',
  `platform_terminal` int(11) DEFAULT NULL COMMENT '终端:1.pc 2.安卓 3ios 4.h5 5.其他',
  `platform_id` int(11) DEFAULT NULL COMMENT '平台Id:默认为玩一下平台;PC=1,移动端为31111',
  `website_url` varchar(200) DEFAULT NULL COMMENT '域名(网站地址)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点击平台按钮‘开始游戏’';

/*Table structure for table `tbl_data_installs` */
CREATE TABLE `tbl_data_installs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '手机安装量',
  `unique_no` varchar(100) DEFAULT NULL COMMENT '手机唯一号（MAC地址/imei）',
  `platform_terminal` int(11) DEFAULT NULL COMMENT '终端:2.安卓 3.ios',
  `country` int(11) DEFAULT NULL COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `longitude` double(14,5) DEFAULT NULL COMMENT '经度',
  `latitude` double(14,5) DEFAULT NULL COMMENT '纬度',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_no` (`unique_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手机安装量';

/*Table structure for table `tbl_data_interface_invoke` */
CREATE TABLE `tbl_data_interface_invoke` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '接口调用',
  `country` int(11) DEFAULT NULL COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `user_id` varchar(50) DEFAULT NULL COMMENT '账户（userId）',
  `platform_id` int(11) DEFAULT NULL COMMENT '平台Id:默认为玩一下平台;PC=1,移动端为31111',
  `platform_terminal` int(11) DEFAULT NULL COMMENT '终端:1.pc 2.安卓 3ios 4.h5 5.其他',
  `interface_type` int(11) DEFAULT NULL COMMENT '接口类型:1.注册接口 2.充值接口',
  `website_url` varchar(200) DEFAULT NULL COMMENT '域名(网站地址)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口调用';

/*Table structure for table `tbl_data_platform_start` */
CREATE TABLE `tbl_data_platform_start` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '平台App启动日志',
  `country` int(11) DEFAULT NULL COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `unique_no` varchar(100) DEFAULT NULL COMMENT '手机唯一号（MAC地址/imei）',
  `user_id` varchar(50) DEFAULT NULL COMMENT '账户(userId)',
  `platform_terminal` int(11) DEFAULT NULL COMMENT '终端:2.安卓 3.ios',
  `longitude` double(14,5) DEFAULT NULL COMMENT '经度',
  `latitude` double(14,5) DEFAULT NULL COMMENT '纬度',
  `ip` varchar(20) DEFAULT NULL COMMENT 'Ip地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台App启动日志,每次启动平台就记录一条日志';

/*Table structure for table `tbl_data_view` */
CREATE TABLE `tbl_data_view` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PV/UV',
  `country` int(11) DEFAULT NULL COMMENT '国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚',
  `client_identity` varchar(100) DEFAULT NULL COMMENT '客户端标识,识别游客访问(区分PV 和UV)',
  `user_id` varchar(50) DEFAULT NULL COMMENT '账户（userId）',
  `platform_terminal` int(11) DEFAULT NULL COMMENT '终端:1.pc 2.安卓 3ios 4.h5 5.其他',
  `website_url` varchar(200) DEFAULT NULL COMMENT '域名(网站地址)',
  `page_view` int(11) DEFAULT NULL COMMENT '浏览页面: 1.首页 2.注册 3.充值',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='PV/UV';

