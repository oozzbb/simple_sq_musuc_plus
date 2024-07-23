package com.sqmusicplus.plug.qqvip.config;

import com.sqmusicplus.plug.base.config.PlugConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname QQConfig
 * @Description qq配置
 * @Version 1.0.0
 * @Date 2023/8/24 16:52
 * @Created by Administrator
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "qqvip")
public class QQVipConfig extends PlugConfig {

    //接口地址
    private String baseUrl;
    //会员的cookie 最好是QQ的能自动刷新
    private String baseCookie;






    @Override
    public String getStringPlugSetName() {
        return "qqvip";
    }
}
