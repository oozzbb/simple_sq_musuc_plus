package com.sqmusicplus.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sqmusicplus.base.entity.SqConfig;
import com.sqmusicplus.download.DownloadExcute;
import com.sqmusicplus.plug.netease.hander.NeteaseHander;
import com.sqmusicplus.plug.qqvip.QQvipHander;
import com.sqmusicplus.base.service.SqConfigService;
import com.sqmusicplus.plug.utils.FreeCookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

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
@Order(100)
public class Init implements ApplicationRunner {
    @Autowired
    private SqConfigService configService;
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
        log.info("启动完毕：http://localhost:{}", port);
        if (Boolean.parseBoolean(init_download.getConfigValue())){
            downloadExcute.getDownloadInfo();
        }
        neteaseHander.initPlug();
        qqvipHander.initPlug();
        log.info("当前服务版本->{}", version);
       SqConfig qqopenconfigKey = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.qqvip.open"));
       SqConfig configKey = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.qqvip.baseurl"));
       SqConfig qqconfigKey = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.qqvip.qq"));
        if (qqconfigKey != null && configKey != null && qqopenconfigKey != null && qqopenconfigKey.getConfigValue() != null &&Boolean.parseBoolean(qqopenconfigKey.getConfigValue())&& configKey.getConfigValue() != null && qqconfigKey.getConfigValue() != null) {
            try {
                FreeCookieUtil.refreshCookies(qqconfigKey.getConfigValue(), configKey.getConfigValue());
            } catch (Exception e) {
                configService.update(new UpdateWrapper<SqConfig>().eq("config_key", "plug.qqvip.open").set("config_value", "false"));
                log.error("获取QQvip失败请检查ip和qq是否准确已自动关闭该插件");
            }
        }

    }



}
