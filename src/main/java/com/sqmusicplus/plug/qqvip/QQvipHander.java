package com.sqmusicplus.plug.qqvip;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.data.Mapper;
import com.ejlchina.okhttps.OkHttps;
import com.sqmusicplus.base.entity.*;
import com.sqmusicplus.plug.base.PlugBrType;
import com.sqmusicplus.plug.base.hander.SearchHanderAbstract;
import com.sqmusicplus.plug.entity.*;
import com.sqmusicplus.plug.qq.config.QQConfig;
import com.sqmusicplus.plug.qq.entity.QQSearchEntity;
import com.sqmusicplus.plug.qq.enums.QQSearchType;
import com.sqmusicplus.plug.qq.hander.QQHander;
import com.sqmusicplus.plug.qqvip.config.QQVipConfig;
import com.sqmusicplus.plug.qqvip.entity.QQVipSearchEntity;
import com.sqmusicplus.utils.DownloadUtils;
import com.sqmusicplus.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yumbo.util.music.MusicEnum;
import top.yumbo.util.music.musicImpl.qq.QQMusicInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @Classname QQvipHander
 * @Description TODO
 * @Version 1.0.0
 * @Date 2024/6/27 16:29
 * @Created by Administrator
 */
@Slf4j
@Component("qqvipHander")
public class QQvipHander extends SearchHanderAbstract {

    @Autowired
    private QQVipConfig qqvipConfig;
    public QQMusicInfo qqMusicInfo=new QQMusicInfo();

    @Autowired
    private QQHander qqHander;


    public void initPlug(){
        QQVipSearchEntity qqSearchEntity = new QQVipSearchEntity();
        qqSearchEntity.setPlugName("qqvip");
        qqHander.setQqSearchEntity(qqSearchEntity);
        // 设置网易云音乐的地址
        MusicEnum.setBASE_URL_QQMusic(qqvipConfig.getBaseUrl());
//        if (StringUtils.isEmpty(qqvipConfig.getBaseCookie())){
//          //直接关闭该功能禁止使用
//        }else{
//            if (StringUtils.isNotEmpty(qqvipConfig.getBaseCookie())){
//                qqMusicInfo.setCookieString(qqvipConfig.getBaseCookie());
//            }
//        }

    }


    @Override
    public QQVipConfig getConfig() {
        return qqvipConfig;
    }

    @Override
    public String getPlugName() {
        return "qqvip";
    }

    @Override
    public PlugSearchResult<PlugSearchMusicResult> querySongByName(SearchKeyData searchKeyData) {

//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("key", searchKeyData.getSearchkey());
//        jsonObject.put("pageNo", searchKeyData.getPageIndex().toString());
//        jsonObject.put("pageSize", searchKeyData.getPageSize().toString());
//
//
//        JSONObject mapper = qqMusicInfo.search(jsonObject);
//
//        int result = mapper.getIntValue("result");
//        if (result != 100) {
//            PlugSearchResult<PlugSearchMusicResult> plugSearchResult = new PlugSearchResult<>();
//            //返回错误
//            return plugSearchResult;
//        }
//        JSONObject data = mapper.getJSONObject("data");
//        JSONArray list = data.getJSONArray("list");
//        ArrayList<PlugSearchMusicResult> plugSearchMusicResults = new ArrayList<>();
//
//
//
//        list.forEach(e->{
//            JSONObject songsDTO = JSONObject.parseObject(e.toString());
//            PlugSearchMusicResult plugSearchMusicResult = new PlugSearchMusicResult();
//
//            JSONArray singer = songsDTO.getJSONArray("singer");
//            String artistName = "";
//            ArrayList<String> strings = new ArrayList<>();
//            AtomicReference<String> artistid = new AtomicReference<>("");
//            singer.forEach(e1->{
//                JSONObject stringsDto = JSONObject.parseObject(e1.toString());
//                strings.add(stringsDto.getString("name"));
//                if (StringUtils.isEmpty(artistid.get())){
//                    artistid.set(stringsDto.getString("mid"));
//                }
//            });
//            String albumImageconfig = qqHander.getConfig().getAlbumImage();
//            String albumImage = albumImageconfig.replaceAll("#\\{pmid}", songsDTO.getString("albummid"));
//            String lyricResult = qqHander.getqqSearchEntity().toPlugLyricResult(songsDTO.getString("songmid"),qqHander.getConfig());
//            artistName=String.join(",",strings);
//            plugSearchMusicResult.setSearchType(getPlugName());
//            plugSearchMusicResult.setId(songsDTO.getString("songmid")+","+songsDTO.getString("strMediaMid"));
//            plugSearchMusicResult.setName(songsDTO.getString("songname"));
//            plugSearchMusicResult.setArtistName(artistName);
//            plugSearchMusicResult.setArtistid(artistid.get());
//            plugSearchMusicResult.setPic(albumImage);
//            plugSearchMusicResult.setAlbumName(songsDTO.getString("albumname"));
//            plugSearchMusicResult.setAlbumid(songsDTO.getString("albummid"));
//            plugSearchMusicResult.setLyricId(songsDTO.getString("songmid"));
//            plugSearchMusicResult.setLyric(lyricResult);
//            plugSearchMusicResults.add(plugSearchMusicResult);
//        });
//
//
//
//
//        plugSearchResult.setSearchIndex(data.getInteger("pageNo"));
//        plugSearchResult.setSearchSize(data.getInteger("pageSize"));
//        plugSearchResult.setSearchTotal(data.getInteger("total"));
//        plugSearchResult.setSearchKeyWork(data.getString("key"));
//        return plugSearchResult;

        return qqHander.querySongByName(searchKeyData);
    }

    @Override
    public PlugSearchResult<PlugSearchArtistResult> queryArtistByName(SearchKeyData searchKeyData) {
        return qqHander.queryArtistByName(searchKeyData);
    }

    @Override
    public PlugSearchResult<PlugSearchAlbumResult> queryAlbumByName(SearchKeyData searchKeyData) {
        return qqHander.queryAlbumByName(searchKeyData);
    }

    @Override
    public Music querySongById(String SongId) {
        if (SongId.contains(",")) {
            SongId = SongId.split(",")[0];
        }
        String searchUrl = qqHander.getConfig().getSearchUrl();
        String s = qqHander.getqqSearchEntity().musicInfoRequestParam(SongId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        return qqHander.getqqSearchEntity().songInfoToMusic(mapper, qqHander.getConfig());


    }

    @Override
    public Artists queryArtistById(String artistId) {
        return qqHander.queryArtistById(artistId);
    }

    @Override
    public Album queryAlbumById(String albumId) {
        return qqHander.queryAlbumById(albumId);
    }

    @Override
    public String queryLyric(String SongId) {
        return qqHander.queryLyric(SongId);
    }

    @Override
    public List<Album> getAlbumsByArtist(String artistId, Integer pageIndex, Integer pageSize) {
        return qqHander.getAlbumsByArtist(artistId,pageIndex,pageSize);
    }

    @Override
    public List<Music> getAlbumSongByAlbumsId(String albumsId) {
        return qqHander.getAlbumSongByAlbumsId(albumsId);
    }

    @Override
    public HashMap<String, String> getDownloadUrl(String musicId, PlugBrType brType) {
        String type = brType.getValue();
        JSONObject jsonObject = new JSONObject();
        String[] split = musicId.split(",");

        if (musicId.contains(",")){
            jsonObject.put("id", split[0]);
            jsonObject.put("mediaId", split[1]);
        }else{
            jsonObject.put("id", musicId);
        }
        jsonObject.put("type", type);
        String baseUrlQqMusic = MusicEnum.BASE_URL_QQMusic;
        baseUrlQqMusic+="song/url";
        System.out.println(baseUrlQqMusic);
        JSONObject jsonObject1 = qqMusicInfo.songUrl(jsonObject);
        Integer code = jsonObject1.getInteger("result");
        if (code!=null&&code==100){
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("url", jsonObject1.getString("data"));
            stringStringHashMap.put("type", brType.getType());
            stringStringHashMap.put("bit", brType.getBit().toString());
            return stringStringHashMap;
        }
        return null;
    }

    @Override
    public DownloadEntity downloadSong(String musicid, PlugBrType brType, String musicname, String artistname, String albumname, Boolean isAudioBook, String addSubsonicPlayListName) {
        Music music = querySongById(musicid);
        DownloadEntity downloadEntity = new DownloadEntity("qqvipHander",musicid, brType, music.getMusicName(), music.getMusicArtists(), music.getMusicAlbum(), isAudioBook, isAudioBook?addSubsonicPlayListName:null);
        return downloadEntity;
    }

    @Override
    public DownloadEntity downloadSong(Music music, PlugBrType brType, Boolean isAudioBook, String addSubsonicPlayListName) {
        DownloadEntity downloadEntity = new DownloadEntity("qqvipHander",music.getId(), brType, music.getMusicName(), music.getMusicArtists(), music.getMusicAlbum(), isAudioBook, isAudioBook?addSubsonicPlayListName:null);
        return downloadEntity;
    }

    @Override
    public DownloadEntity downloadSong(Music music, PlugBrType brType, String addSubsonicPlayListName) {
        DownloadEntity downloadEntity = new DownloadEntity("qqvipHander",music.getId(), brType, music.getMusicName(), music.getMusicArtists(), music.getMusicAlbum(), false, addSubsonicPlayListName);
        return downloadEntity;
    }

    @Override
    public ArrayList<DownloadEntity> downloadAlbum(String albumsId, PlugBrType brType, String addSubsonicPlayListName, String artist, Boolean isAudioBook, String albumName) {
        List<Music> musiclist = getAlbumSongByAlbumsId(albumsId);
        AtomicReference<String> change = new AtomicReference<>(artist);
        ArrayList<DownloadEntity> downloadEntities = new ArrayList<>();

        SqConfig accompaniment = getConfigService().getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.ignore.accompaniment"));
        SqConfig matchAlbumSinger = getConfigService().getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.strong.match.album.singer"));
        SqConfig albumSingerUnity = getConfigService().getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.album.singer.unity"));

        musiclist.forEach(md -> {
            if (Boolean.getBoolean(accompaniment.getConfigValue())) {
                if (md.getMusicName().contains("(伴奏)") || md.getMusicName().contains("(试听版)") || md.getMusicName().contains("片段")) {
                    return;
                }
            }
            if (Boolean.getBoolean(matchAlbumSinger.getConfigValue()) && !isAudioBook) {
                if (!md.getMusicArtists().contains(change.get())) {
                    return;
                }
            }
            if (!Boolean.getBoolean(albumSingerUnity.getConfigValue()) && !isAudioBook) {
                change.set(md.getMusicArtists());
            }
            if (isAudioBook) {
                downloadEntities.add(new DownloadEntity("qqvipHander",md.getId(), brType, md.getMusicName(), artist, albumName, isAudioBook));
            } else {
                //添加到缓存
                downloadEntities.add(new DownloadEntity("qqvipHander",md.getId(), brType, md.getMusicName(), change.get(), md.getMusicAlbum()));
            }

        });
        return downloadEntities;
    }

    @Override
    public List<DownloadEntity> downloadArtistAllSong(String artistId, PlugBrType brType, String addSubsonicPlayListName) {
        String searchUrl = qqHander.getConfig().getSearchUrl();
        String s = qqHander.getqqSearchEntity().artistsTransferAlbumParam(artistId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        List<Album> albums = qqHander.getqqSearchEntity().artistsTransferAlbum(mapper, qqHander.getConfig());
        ArrayList<DownloadEntity> downloadEntitys = new ArrayList<>();
        for (Album album : albums) {
            ArrayList<DownloadEntity> downloadEntities = downloadAlbum(album.getAlbumId(), brType, addSubsonicPlayListName, album.getAlbumArtists(), false, album.getAlbumName());
            downloadEntitys.addAll(downloadEntities);
        }
        return downloadEntitys;
    }

    @Override
    public List<DownloadEntity> downloadArtistAllAlbum(String artistId, PlugBrType brType, String addSubsonicPlayListName) {
        String searchUrl = qqHander.getConfig().getSearchUrl();
        String s = qqHander.getqqSearchEntity().artistsTransferAlbumParam(artistId);
        Mapper mapper = DownloadUtils.getHttp().sync(searchUrl).setBodyPara(s).post().getBody().toMapper();
        List<Album> albums = qqHander.getqqSearchEntity().artistsTransferAlbum(mapper,  qqHander.getConfig());
        ArrayList<DownloadEntity> downloadEntitys = new ArrayList<>();
        for (Album album : albums) {
            ArrayList<DownloadEntity> downloadEntities = downloadAlbum(album.getAlbumId(), brType, addSubsonicPlayListName, album.getAlbumArtists(), false, album.getAlbumName());
            downloadEntitys.addAll(downloadEntities);
        }
        return downloadEntitys;
    }
}
