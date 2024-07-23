package com.sqmusicplus.plug.freemp3.hander.etity;

import lombok.Data;

import java.io.InputStream;
import java.util.List;

/**
 * @Classname FreeMp3DownloadEntity
 * @Description 下载与准备实体
 * @Version 1.0.0
 * @Date 2024/7/22 9:24
 * @Created by SQ
 */

@Data
public class FreeMp3DownloadEntity {

    /**
     * music下载地址
     */
    private String downloadMusicUrl;
    /**
     * music歌词下载地址如果Lyrc为空则用词url再次下载
     */

    private String downloadLyricUrl;
    /**
     * music封面下载地址如果封面为空则用词url再次下载
     */

    private String downloadImageUrl;
    /**
     * music名称(不带歌手的)
     */

    private String musicName;
    /**
     * music歌手
     */
    private List<String> musicArtist;

    /**
     * music专辑
     */
    private String musicAlbum;
//
//    /**
//     * music歌词
//     */
//    private String musicLyrc;
//
//    /**
//     * 图片
//     */
//
//    private InputStream musicImage;

}
