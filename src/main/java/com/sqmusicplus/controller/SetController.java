package com.sqmusicplus.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ejlchina.data.Mapper;
import com.ejlchina.okhttps.HTTP;
import com.sqmusicplus.config.AjaxResult;
import com.sqmusicplus.base.entity.SqConfig;
import com.sqmusicplus.plug.base.PlugBrType;
import com.sqmusicplus.base.service.SqConfigService;
import com.sqmusicplus.plug.utils.FreeCookieUtil;
import com.sqmusicplus.utils.DownloadUtils;
import com.sqmusicplus.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname SetController
 * @Description 全局设置
 * @Version 1.0.0
 * @Date 2022/10/21 14:03
 * @Created by SQ
 */
@Slf4j
@RestController
@RequestMapping("/set")
public class SetController {
    @Autowired
    private SqConfigService configService;
    @Value("${version}")
    private String version;

    /**
     * 查询全部设置
     *
     * @return
     */
    @GetMapping("/getSetList/")
    public AjaxResult getSetList() {
        List<SqConfig> list = configService.list();
        return AjaxResult.success("成功", list);
    }

    @SaCheckLogin
    @PostMapping("/modify")
    public AjaxResult modify(@RequestBody SqConfig config) {
        if (config.getConfigKey().equals("plug.subsonic.url")) {
            if (config.getConfigValue().endsWith("/")) {
                String configValue = config.getConfigValue();
                String substring = configValue.substring(0, configValue.length() - 1);
                config.setConfigValue(substring);
            }

        }

        if (config.getConfigKey().equals("plug.qqvip.baseurl")) {
            if (config.getConfigValue().endsWith("/")) {
                String configValue = config.getConfigValue();
                String substring = configValue.substring(0, configValue.length() - 1);
                config.setConfigValue(substring);
            }

            SqConfig qqconfig = configService.getOne(new QueryWrapper<SqConfig>().eq(SqConfig.COL_CONFIG_KEY, "plug.qqvip.qq"));
            try {
                FreeCookieUtil.refreshCookies(qqconfig.getConfigValue(), config.getConfigValue());
            } catch (Exception e) {
                configService.update(new UpdateWrapper<SqConfig>().eq("config_key", "plug.qqvip.open").set("config_value", "false"));
                log.error("获取QQvip失败请检查ip和qq是否准确已自动关闭该插件");
            }

        }
        if (config.getConfigKey().equals("plug.qqvip.qq")) {
            SqConfig urlconfig = configService.getOne(new QueryWrapper<SqConfig>().eq(SqConfig.COL_CONFIG_KEY, "plug.qqvip.baseurl"));
            try {
                FreeCookieUtil.refreshCookies(config.getConfigValue(), urlconfig.getConfigValue());
            } catch (Exception e) {
                configService.update(new UpdateWrapper<SqConfig>().eq("config_key", "plug.qqvip.open").set("config_value", "false"));
                log.error("获取QQvip失败请检查ip和qq是否准确已自动关闭该插件");
            }
        }


        if (config.getConfigKey().equals("plug.qqvip.open")&&config.getConfigValue().equals("true")) {
            SqConfig urlconfig = configService.getOne(new QueryWrapper<SqConfig>().eq(SqConfig.COL_CONFIG_KEY, "plug.qqvip.baseurl"));
            SqConfig qqconfig = configService.getOne(new QueryWrapper<SqConfig>().eq(SqConfig.COL_CONFIG_KEY, "plug.qqvip.qq"));
            try {
                FreeCookieUtil.refreshCookies(qqconfig.getConfigValue(), urlconfig.getConfigValue());
            } catch (Exception e) {
                configService.update(new UpdateWrapper<SqConfig>().eq("config_key", "plug.qqvip.open").set("config_value", "false"));
                log.error("获取QQvip失败请检查ip和qq是否准确已自动关闭该插件");
            }
        }

        boolean b = false;
        if (config.getConfigId()==null){
            UpdateWrapper<SqConfig> sqConfigUpdateWrapper = new UpdateWrapper<>();
            sqConfigUpdateWrapper.eq(SqConfig.COL_CONFIG_KEY, config.getConfigKey()).set(SqConfig.COL_CONFIG_VALUE, config.getConfigValue());
             b = configService.update(sqConfigUpdateWrapper);
        }else{
             b = configService.updateById(config);
        }
        return AjaxResult.success("成功", b);
    }

    @SaCheckLogin
    @PostMapping("/refreshQQvipCookie")
    public AjaxResult refreshQQvipCookies(){
        SqConfig qqconfig = configService.getOne(new QueryWrapper<SqConfig>().eq(SqConfig.COL_CONFIG_KEY, "plug.qqvip.qq"));
        SqConfig urlconfig = configService.getOne(new QueryWrapper<SqConfig>().eq(SqConfig.COL_CONFIG_KEY, "plug.qqvip.baseurl"));
        ArrayList<String> strings = FreeCookieUtil.refreshCookies(qqconfig.getConfigValue(), urlconfig.getConfigValue());
        return AjaxResult.success("成功", strings);
    }

    @GetMapping("/getSearchType")
    public AjaxResult getSearchType(){
        PlugBrType[] values = PlugBrType.values();
        Map<String, String> collect = Arrays.stream(values).collect(Collectors.toMap(PlugBrType::getPlugName, PlugBrType::getValue));
        JSONArray objects = new JSONArray();
        Set<Map.Entry<String, String>> entries = collect.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(entry.getKey(),entry.getValue());
            objects.add(jsonObject);
        }
        return  AjaxResult.success("成功", collect);
    }
    @GetMapping("/getSearchTypeBrType")
    public AjaxResult getSearchTypeBrType(){
        PlugBrType[] values = PlugBrType.values();
        return  AjaxResult.success("成功", values);
    }

    @GetMapping("selectOption")
    public AjaxResult selectOption(){
        SqConfig qqopenconfigKey = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "plug.qqvip.open"));

        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
        if (qqopenconfigKey!=null&&Boolean.parseBoolean(qqopenconfigKey.getConfigValue())){
            HashMap<String, String> QQVIPoption = new HashMap<>();
            QQVIPoption.put("value","qqvip");
            QQVIPoption.put("label","鹅厂VIP下载（自动同步喜欢的去设置开启）");
            hashMaps.add(QQVIPoption);
        }
        HashMap<String, String> kwoption = new HashMap<>();
        kwoption.put("value","kw");
        kwoption.put("label","某我");
        HashMap<String, String> QQoption = new HashMap<>();
        QQoption.put("value","qq");
        QQoption.put("label","鹅厂(不要太过频繁否则无法下载)");
        HashMap<String, String> MGoption = new HashMap<>();
        MGoption.put("value","mg");
        MGoption.put("label","10086(有问题暂停使用)");
        MGoption.put("disabled","true");
        HashMap<String, String> neteaseoption = new HashMap<>();
        neteaseoption.put("value","netease");
        neteaseoption.put("label","猪厂");
        hashMaps.add(kwoption);
        hashMaps.add(QQoption);
        hashMaps.add(MGoption);
        hashMaps.add(neteaseoption);

        return AjaxResult.success(hashMaps);
    }
    @GetMapping("version")
    public AjaxResult getVersion(){
        return AjaxResult.success("成功", version);
    }


}
