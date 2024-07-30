package com.sqmusicplus.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ejlchina.data.Mapper;
import com.ejlchina.okhttps.HTTP;
import com.sqmusicplus.base.entity.SqConfig;
import com.sqmusicplus.download.DownloadExcute;
import com.sqmusicplus.plug.netease.config.NeteaseConfig;
import com.sqmusicplus.plug.netease.hander.NeteaseHander;
import com.sqmusicplus.plug.qqvip.QQvipHander;
import com.sqmusicplus.plug.subsonic.SubsonicHander;
import com.sqmusicplus.plug.subsonic.config.NowPlayList;
import com.sqmusicplus.plug.subsonic.entity.SubsonicPlayList;
import com.sqmusicplus.base.service.SqConfigService;
import com.sqmusicplus.utils.DownloadUtils;
import com.sqmusicplus.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import top.yumbo.util.music.MusicEnum;

import java.util.ArrayList;

/**
 * @Classname Init
 * @Description 初始化
 * @Version 1.0.0
 * @Date 2022/8/3 16:52
 * @Created by SQ
 */
@Slf4j
@Configuration
public class Init implements ApplicationRunner {
    @Autowired
    private SqConfigService configService;
    @Autowired
    private SubsonicHander subsonicHander;
    @Autowired
    private QQvipHander qqvipHander;
    @Value("${server.port}")
    private String port;
    @Value("${version}")
    private String version;
    @Autowired
    private DownloadExcute downloadExcute;
    @Autowired
    private NeteaseHander neteaseHander;

    @Value("${qqvip.baseUrl:null}")
    private String qqVipBaseUrl;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        SqConfig init_download = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.init.download"));
        SqConfig subsonic = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.subsonic.start"));
        if (Boolean.getBoolean(subsonic.getConfigValue())) {
            Boolean aBoolean = subsonicHander.checkLoginInfo();
            if (aBoolean) {
                log.info("subsonic服务连接正常");
            } else {
                configService.update(new UpdateWrapper<SqConfig>().eq("config_key", "plug.subsonic.start").set("config_key", "false"));
            }
            ArrayList<SubsonicPlayList> subsonicPlayList = subsonicHander.getSubsonicPlayList();
            for (SubsonicPlayList playList : subsonicPlayList) {
                NowPlayList.NOW_PLAYLIST.put(playList.getName(), playList);
                log.info("subsonic加载歌单-{}", playList.getName());
            }
        }
        log.info("启动完毕：http://localhost:{}", port);
        if (Boolean.valueOf(init_download.getConfigValue())){
            downloadExcute.getDownloadInfo();
        }
        neteaseHander.initPlug();
        qqvipHander.initPlug();
        log.info("当前服务版本->{}", version);
        if (StringUtils.isNotBlank(qqVipBaseUrl)&& !qqVipBaseUrl.equals("null")) {
           //如果最后有/则去掉
            qqVipBaseUrl = qqVipBaseUrl.replaceAll("/$", "");
            SqConfig configKey = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.qqvip.baseurl"));
            if (configKey!=null&&!configKey.equals(qqVipBaseUrl)) {
                log.info("发现qqvip基础地址与数据库不同更新为配置地址->{}", qqVipBaseUrl);

                configService.update(new UpdateWrapper<SqConfig>().eq("config_key", "plug.qqvip.baseurl").set("config_value", qqVipBaseUrl));
            }else {
                SqConfig sqConfig = new SqConfig();
                sqConfig.setConfigKey("plug.qqvip.baseurl");
                sqConfig.setConfigValue(qqVipBaseUrl);
                sqConfig.setConfigName("QQvip基础url");
                sqConfig.setType("input");
                configService.save(sqConfig);
                log.info("新增QQvip配置->{}", qqVipBaseUrl);
            }
        }




    }


}
