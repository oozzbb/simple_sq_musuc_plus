package com.sqmusicplus.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ejlchina.data.ListMap;
import com.ejlchina.data.Mapper;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.sqmusicplus.base.entity.SqConfig;
import com.sqmusicplus.download.DownloadExcute;
import com.sqmusicplus.plug.netease.config.NeteaseConfig;
import com.sqmusicplus.plug.netease.hander.NeteaseHander;
import com.sqmusicplus.plug.qqvip.QQvipHander;
import com.sqmusicplus.plug.subsonic.SubsonicHander;
import com.sqmusicplus.plug.subsonic.config.NowPlayList;
import com.sqmusicplus.plug.subsonic.entity.SubsonicPlayList;
import com.sqmusicplus.base.service.SqConfigService;
import com.sqmusicplus.plug.utils.FreeCookieUtil;
import com.sqmusicplus.utils.DownloadUtils;
import com.sqmusicplus.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
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



    @Override
    public void run(ApplicationArguments args) throws Exception {
        SqConfig init_download = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.init.download"));
        SqConfig subsonic = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.subsonic.start"));
        if (subsonic!=null&&Boolean.parseBoolean(subsonic.getConfigValue())) {
            Boolean aBoolean = subsonicHander.checkLoginInfo();
            if (aBoolean) {
                log.info("subsonic服务连接正常");
            } else {
                configService.update(new UpdateWrapper<SqConfig>().eq("config_key", "plug.subsonic.start").set("config_value", "false"));
            }
            ArrayList<SubsonicPlayList> subsonicPlayList = subsonicHander.getSubsonicPlayList();
            for (SubsonicPlayList playList : subsonicPlayList) {
                NowPlayList.NOW_PLAYLIST.put(playList.getName(), playList);
                log.info("subsonic加载歌单-{}", playList.getName());
            }
        }
        log.info("启动完毕：http://localhost:{}", port);
        if (Boolean.parseBoolean(init_download.getConfigValue())){
            downloadExcute.getDownloadInfo();
        }
        neteaseHander.initPlug();
        qqvipHander.initPlug();
        log.info("当前服务版本->{}", version);
            SqConfig qqopenconfigKey = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.qqvip.open"));
                if (qqopenconfigKey==null){
                    SqConfig sqConfig = new SqConfig();
                    sqConfig.setConfigKey("plug.qqvip.open");
                    sqConfig.setConfigValue("false");
                    sqConfig.setConfigName("QQvip插件是否启用开关");
                    sqConfig.setType("switch");
                    configService.save(sqConfig);
                    SqConfig syncconfig = new SqConfig();
                    syncconfig.setConfigKey("plug.qqvip.sync");
                    syncconfig.setConfigValue("false");
                    syncconfig.setConfigName("QQVIP插件同步开关（开启后才能开启其他同步功能）");
                    syncconfig.setType("switch");
                    configService.save(syncconfig);
                    SqConfig synclikesong = new SqConfig();
                    synclikesong.setConfigKey("plug.qqvip.synclikesong");
                    synclikesong.setConfigValue("false");
                    synclikesong.setConfigName("QQVIP插件同步我喜欢的歌曲");
                    synclikesong.setType("switch");
                    configService.save(synclikesong);
                    SqConfig synclikealbum = new SqConfig();
                    synclikealbum.setConfigKey("plug.qqvip.synclikealbum");
                    synclikealbum.setConfigValue("false");
                    synclikealbum.setConfigName("QQVIP插件同步我喜欢的专辑");
                    synclikealbum.setType("switch");
                    configService.save(synclikealbum);
                    SqConfig syncplaylist = new SqConfig();
                    syncplaylist.setConfigKey("plug.qqvip.syncplaylist");
                    syncplaylist.setConfigValue("false");
                    syncplaylist.setConfigName("QQVIP插件同步收藏和自建歌单");
                    syncplaylist.setType("switch");
                    configService.save(syncplaylist);
                    SqConfig syncplaylistexclude = new SqConfig();
                    syncplaylistexclude.setConfigKey("plug.qqvip.syncplaylistexclude");
                    syncplaylistexclude.setConfigValue("多个,分割");
                    syncplaylistexclude.setConfigName("QQVIP插件排除指定名称的歌单多个,（半角,）分割（不会同步的歌单名称）");
                    syncplaylistexclude.setType("input");
                    configService.save(syncplaylistexclude);

                }
                SqConfig configKey = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.qqvip.baseurl"));
                if (configKey==null){
                    SqConfig sqConfig = new SqConfig();
                    sqConfig.setConfigKey("plug.qqvip.baseurl");
                    sqConfig.setConfigValue("http://127.0.0.1");
                    sqConfig.setConfigName("QQvip基础url");
                    sqConfig.setType("input");
                    configService.save(sqConfig);
                    log.info("新增QQvip基础url：{}", sqConfig.getConfigValue());
                }
                SqConfig qqconfigKey = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.qqvip.qq"));
                if (qqconfigKey==null){
                    SqConfig qqsqConfig = new SqConfig();
                    qqsqConfig.setConfigKey("plug.qqvip.qq");
                    qqsqConfig.setConfigValue("123456");
                    qqsqConfig.setConfigName("QQ号");
                    qqsqConfig.setType("input");
                    configService.save(qqsqConfig);
                    log.info("新增QQ号：{}", qqsqConfig.getConfigValue());
                }


        if (qqconfigKey != null && configKey != null && qqopenconfigKey != null && qqopenconfigKey.getConfigValue() != null && configKey.getConfigValue() != null && qqconfigKey.getConfigValue() != null) {
            try {
                FreeCookieUtil.refreshCookies(qqconfigKey.getConfigValue(), configKey.getConfigValue());
            } catch (Exception e) {
                configService.update(new UpdateWrapper<SqConfig>().eq("config_key", "plug.qqvip.open").set("config_value", "false"));
                log.error("获取QQvip失败请检查ip和qq是否准确已自动关闭该插件");
            }
        }

    }



}
