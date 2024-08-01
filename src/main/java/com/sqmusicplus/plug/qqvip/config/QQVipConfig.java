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
public class QQVipConfig extends PlugConfig {


    


    @Override
    public String getStringPlugSetName() {
        return "qqvip";
    }
}
