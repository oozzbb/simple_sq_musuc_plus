package com.sqmusicplus.base.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sqmusicplus.base.entity.DownloadInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Classname DownloadInfoSearch
 * @Description 下载搜索
 * @Version 1.0.0
 * @Date 2024/8/15 16:01
 * @Created by SQ
 */
@Getter
@Setter
@Accessors(chain = true)
public class DownloadInfoSearch extends DownloadInfo {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date downloadTimeStart ;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date downloadTimeEnd ;


    private  Integer pageSize;
    private  Integer pageIndex;
}
