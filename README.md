# smartdata

#预生产需要提交的脚本。
ALTER TABLE `smartdata`.`data_interface_invoke` ADD COLUMN `platform_code` VARCHAR(50) DEFAULT '' NOT NULL COMMENT '平台游戏编码' AFTER `user_id`; 
ALTER TABLE `smartdata`.`data_game_start` ADD COLUMN `platform_code` VARCHAR(50) DEFAULT '' NOT NULL COMMENT '平台游戏编码' AFTER `platform_terminal`; 


#测试环境需要提交的脚本。
ALTER TABLE `smartdata`.`interval_game_launch_report` CHANGE `platform_id` `platform_code` VARCHAR(50) DEFAULT '' NOT NULL COMMENT '平台或游戏code'; 
ALTER TABLE `smartdata`.`month_login_report` CHANGE `platform_id` `platform_code` VARCHAR(50) DEFAULT '0' NOT NULL COMMENT '平台id或者游戏id', CHANGE `platform_name` `platform_name` VARCHAR(50) CHARSET utf8 COLLATE utf8_general_ci DEFAULT '' NOT NULL COMMENT '平台或游戏名称'; 
ALTER TABLE `smartdata`.`daily_login_report` CHANGE `platform_id` `platform_code` VARCHAR(50) DEFAULT '' NOT NULL COMMENT '游戏编码', CHANGE `platform_name` `platform_name` VARCHAR(50) CHARSET utf8 COLLATE utf8_general_ci DEFAULT '' NOT NULL COMMENT '游戏名称'; 

ALTER TABLE `smartdata`.`data_view` ADD COLUMN `platform_code` VARCHAR(50) DEFAULT '' NOT NULL COMMENT '平台游戏编码' AFTER `create_time`, ADD COLUMN `platform_id` INT(11) DEFAULT 0 NOT NULL COMMENT '平台Id:默认为玩一下平台;PC=1,移动端为31111' AFTER `platform_code`, ADD COLUMN `platform_name` VARCHAR(100) DEFAULT '' NOT NULL COMMENT '平台名称' AFTER `platform_id`; 
ALTER TABLE `smartdata`.`daily_recharge_report` ADD COLUMN `login_population` INT(10) DEFAULT 0 NOT NULL COMMENT '用户登录人数。' AFTER `source_type`; 