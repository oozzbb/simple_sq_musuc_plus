package com.sqmusicplus.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author sq
 * @since 2023-08-23
 */
@Getter
@Setter
@TableName("download_info")
@Accessors(chain = true)
public class DownloadInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("download_gid")
    private String downloadGid;

    @TableField("download_type")
    private String downloadType;

    @TableField("download_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date downloadTime;

    @TableField("download_file")
    private String downloadFile;

    @TableField("download_url")
    private String downloadUrl;

    @TableField("download_music_id")
    private String downloadMusicId;

    @TableField("download_br_type")
    private String downloadBrType;

    @TableField("download_musicname")
    private String downloadMusicname;

    @TableField("download_artistname")
    private String downloadArtistname;

    @TableField("download_albumname")
    private String downloadAlbumname;

    @TableField("download_msg")
    private String downloadMsg;

    @Version
    private String version;

    @TableField("status")
    private String status;

    @TableField("spring_name")
    private String springName;

    @TableField("audio_book")
    private String audioBook;

    @TableField("add_subsonic_playList_name")
    private String addSubsonicPlayListName;






}
