CREATE TABLE `download_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `download_gid` varchar(255) NULL,
  `download_type` varchar(255) NULL,
  `download_time` datetime NULL,
  `download_file` varchar(255) NULL,
  `download_url` varchar(255) NULL,
  `download_music_id` varchar(255) NULL,
  `download_plug_name` varchar(255) NULL,
  `download_br_type` varchar(255) NULL,
  `download_musicname` varchar(255) NULL,
  `download_artistname` varchar(255) NULL,
  `download_albumname` varchar(255) NULL,
  `download_msg` varchar(255) NULL,
  `version` varchar(255) NULL,
  `status` varchar(255) NULL,
  `spring_name` varchar(255) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `sq_config`  (
  `config_id` int NOT NULL AUTO_INCREMENT,
  `config_name` varchar(255) NULL COMMENT '配置名称',
  `config_value` longtext NULL COMMENT '配置值',
  `config_key` varchar(255) NULL COMMENT '配置表示',
  `type` varchar(255) NULL COMMENT '类型',
  `config_show` varchar(255) NULL COMMENT '是否显示Y显示N不显示',
  PRIMARY KEY (`config_id`)
);

CREATE TABLE `sq_subsonic_playlist`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  `songCount` varchar(255) NULL,
  `duration` varchar(255) NULL,
  `public` varchar(255) NULL,
  `owner` varchar(255) NULL,
  `created` varchar(255) NULL,
  `changed` varchar(255) NULL,
  PRIMARY KEY (`id`)
);

