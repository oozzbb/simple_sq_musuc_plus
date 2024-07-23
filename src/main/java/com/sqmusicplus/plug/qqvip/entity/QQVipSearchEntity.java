package com.sqmusicplus.plug.qqvip.entity;

import com.alibaba.fastjson.JSONObject;
import com.ejlchina.data.Mapper;
import com.sqmusicplus.base.entity.Music;
import com.sqmusicplus.plug.qq.config.QQConfig;
import com.sqmusicplus.plug.qq.entity.QQSearchEntity;
import com.sqmusicplus.utils.StringUtils;

/**
 * @Classname QQVipSearchEntity
 * @Description QQVIP的实体转化
 * @Version 1.0.0
 * @Date 2024/7/3 17:27
 * @Created by SQ
 */
public class QQVipSearchEntity extends QQSearchEntity {
    @Override
    public Music songInfoToMusic(Mapper mapper, QQConfig qqConfig) {
        Mapper mapper1 = mapper.getMapper("songinfo").getMapper("data").getMapper("track_info");
        String name = mapper1.getString("name");
        String mid = mapper1.getString("mid");
        String albumid = mapper1.getMapper("album").getString("mid");
        String albumname = mapper1.getMapper("album").getString("name");
        String albumpmid = mapper1.getMapper("album").getString("pmid");
        String albumImageconfig = qqConfig.getAlbumImage();
        String albumImage = albumImageconfig.replaceAll("#\\{pmid}", albumpmid);
        String artistId = mapper1.getArray("singer").getMapper(0).getString("mid");
        String artistname = mapper1.getArray("singer").getMapper(0).getString("name");
        Mapper filemapper = mapper1.getMapper("file");
        String mediaMid = filemapper.getString("media_mid");
        String lyricResult = toPlugLyricResult(mid,qqConfig);
        if (StringUtils.isNotBlank(mediaMid)) {
            mid  = mid+","+mediaMid;
        }
        Music music = new Music().setId(mid)
                .setMusicImage(albumImage)
                .setMusicLyric(lyricResult)
                .setMusicAlbum(albumname)
                .setMusicArtists(artistname)
                .setMusicName(name).
                setOther(JSONObject.parseObject(mapper1.toString()))
                .setAlbumId(albumid)
                .setArtistsId(artistId);
        return  music;
    }
}
