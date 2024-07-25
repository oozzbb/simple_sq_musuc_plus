package com.sqmusicplus.plug.freemp3.hander;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqmusicplus.base.entity.*;
import com.sqmusicplus.base.service.SqConfigService;
import com.sqmusicplus.plug.base.PlugBrType;
import com.sqmusicplus.plug.base.hander.SearchHanderAbstract;
import com.sqmusicplus.plug.entity.PlugSearchMusicResult;
import com.sqmusicplus.plug.entity.PlugSearchResult;
import com.sqmusicplus.plug.entity.SearchKeyData;
import com.sqmusicplus.plug.freemp3.hander.etity.FreeMp3DownloadEntity;
import com.sqmusicplus.utils.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xm.Similarity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Classname FreeMp3Hander
 * @Description FreeMp3处理器
 * @Version 1.0.0
 * @Date 2024/7/20 14:53
 * @Created by SQ
 */
@Slf4j
@Component("freeMp3Hander")
public class FreeMp3Hander {

    @Autowired
    List<SearchHanderAbstract> searchHanderAbstractList;

    @Autowired
    private SqConfigService configService;


    /**
     * 找出最有可能的专辑名称（可以用但是发现从页面上获取更方便以后做自动整理文件时候使用）
     * @param musicName
     * @param artists
     * @return
     */
    public String getMusicAlbumByMusicNameAndArtistName (String musicName, List<String> artists){
        //如果不匹配找出的最有可能的专辑名称
        String similarityName  = "other";
        //当前找出的相似度
        Double similarityValue = 0.0;

        for (SearchHanderAbstract searchHanderAbstract : searchHanderAbstractList) {
            SearchKeyData searchKeyData = new SearchKeyData();
            searchKeyData.setSearchkey(musicName+" "+ StringUtils.join(artists," "));
            searchKeyData.setSearchType(searchHanderAbstract.getPlugName());
            searchKeyData.setPageIndex(1);
            searchKeyData.setPageSize(10);
            PlugSearchResult<PlugSearchMusicResult> plugSearchMusicResultPlugSearchResult = searchHanderAbstract.querySongByName(searchKeyData);
            if (plugSearchMusicResultPlugSearchResult!=null&&plugSearchMusicResultPlugSearchResult.getRecords().size()>0){
                List<PlugSearchMusicResult> records = plugSearchMusicResultPlugSearchResult.getRecords();
                for (PlugSearchMusicResult record : records) {
                    String name = record.getName();
                    String tempArtist = record.getArtistName();

                    ArrayList<String> tempArtisList = new ArrayList<>();
                    if (tempArtist.contains(",")){
                        String[] split = tempArtist.split(",");
                        for (String s : split) {
                            tempArtisList.add(s);
                        }
                    }else if (tempArtist.contains("/")){
                        String[] split = tempArtist.split("/");
                        for (String s : split) {
                            tempArtisList.add(s);
                        }
                    }else if (tempArtist.contains("&")){
                        String[] split = tempArtist.split("&");
                        for (String s : split) {
                            tempArtisList.add(s);
                        }
                    }else{
                        tempArtisList.add(tempArtist);
                    }
                   if (tempArtisList.size()==artists.size()&&tempArtisList.containsAll(artists)){
                       //对比完歌手对比名称
                       String regExp="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
                       musicName = musicName.replaceAll(regExp,"");
                       name = name.replaceAll(regExp,"");
                       if (name.equals(musicName)){
                           //匹配
                           return record.getAlbumName();
                       }else{
                           //歌手对上了但是名字没对应上计算相似度80%以上的记录下来最终没找到用这个
                           //字面相似度
                           double charBasedSimilarityResult = Similarity.charBasedSimilarity(musicName, name);
                            //拼音相似度
                           double pinyinSimilarityResult = Similarity.pinyinSimilarity(musicName, name);
                           //概念相似度
                           double conceptSimilarityResult = Similarity.conceptSimilarity(musicName, name);

                           if (charBasedSimilarityResult==1.0||pinyinSimilarityResult==1.0||conceptSimilarityResult==1.0){
                               //匹配
                               return record.getAlbumName();
                           }else if (charBasedSimilarityResult>0.7||pinyinSimilarityResult>0.7||conceptSimilarityResult>0.7){
                               //部分匹配的上等马(找出最厉害的上等马)
                               double max = NumberUtil.max(charBasedSimilarityResult, pinyinSimilarityResult, conceptSimilarityResult);
                                if (similarityValue>max){
                                    similarityName = record.getAlbumName() ;
                                    similarityValue =max;
                                }
                           }
                       }
                   }

                }
            }
        }
        return similarityName;
    }


    public DownloadEntity download(String musicName, String artistName, String albumName ,String lyric, String image,String musicUrl){
        ArrayList<String> artists = new ArrayList<>();
        if (artistName.contains(",")){
            String[] split = artistName.split(",");
            for (String s : split) {
                artists.add(s);
            }
        }else if (artistName.contains("/")){
            String[] split = artistName.split("/");
            for (String s : split) {
                artists.add(s);
            }
        }else if (artistName.contains("&")){
            String[] split = artistName.split("&");
            for (String s : split) {
                artists.add(s);
            }
        }else{
            artists.add(artistName);
        }
        //搜不到专辑时候 可以用一下
//        String albumName = getMusicAlbumByMusicNameAndArtistName(musicName, artists);


        FreeMp3DownloadEntity freeMp3DownloadEntity = new FreeMp3DownloadEntity();
        freeMp3DownloadEntity.setDownloadImageUrl(image);
        freeMp3DownloadEntity.setDownloadLyricUrl(lyric);
        freeMp3DownloadEntity.setDownloadMusicUrl(musicUrl);
        freeMp3DownloadEntity.setMusicName(musicName);
        freeMp3DownloadEntity.setMusicAlbum(albumName);
        freeMp3DownloadEntity.setMusicArtist(artists);
        String jsonString = JSONObject.toJSONString(freeMp3DownloadEntity);
        DownloadEntity downloadEntity = new DownloadEntity("freeMp3Hander", jsonString, PlugBrType.Free_Download_2000, musicName, artistName, albumName);
        return downloadEntity;

    }

    public String queryLyric(String lyricurl){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(lyricurl)
                .get()
                .addHeader("accept", "*/*")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .addHeader("origin", "https://tool.liumingye.cn")
                .addHeader("priority", "u=1, i")
                .addHeader("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"")
  .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "";
        }

    }

    public InputStream downloadImage(String imageUrl){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(imageUrl)
                .get()
                .addHeader("accept", "*/*")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .addHeader("origin", "https://tool.liumingye.cn")
                .addHeader("priority", "u=1, i")
                .addHeader("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().byteStream();
        } catch (IOException e) {
            return null;
        }

    }



    /**
     * 获取下载（播放连接）
     *
     * @param musicurl 歌曲下载地址
     * @return {
     * url：连接，
     * }
     */
    String getDownloadUrl(String musicurl){
        //判断是否是蓝奏地址还是其他地址
        if (musicurl.contains("lanzou")){
            //转直连再下载

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://lz.qaiu.top/json/parser?url="+musicurl)
                    .get()
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                JSONObject jsonObject = JSONObject.parseObject(string);
                Integer code = jsonObject.getInteger("code");
                if (code==200){
                    String url = jsonObject.getString("data");
                    return url;
                }else{
                    return null;
                }
            } catch (IOException e) {
              return null;
            }


        }else{
            //musicId
            return musicurl;
        }

    }

    /**
     * 保存歌曲到文件并写入标签(下载歌曲)
     *
     * @param downloadEntity 下载对象
     */
    public void dnonloadAndSaveToFile(DownloadEntity downloadEntity, Object searchHander){
        String musicid = downloadEntity.getMusicid();
        FreeMp3DownloadEntity freeMp3DownloadEntity = JSONObject.parseObject(musicid, FreeMp3DownloadEntity.class);
        FreeMp3Hander freeMp3Hander = (FreeMp3Hander)searchHander;

        String musicPath = configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.download.path")).getConfigValue();
        File file = new File(musicPath);
        String musicName = downloadEntity.getMusicname();
        String albumname = downloadEntity.getAlbumname();
        String artistname = downloadEntity.getArtistname();
        String lyricStr = freeMp3Hander.queryLyric(freeMp3DownloadEntity.getDownloadLyricUrl());
        String musicUrl = freeMp3Hander.getDownloadUrl(freeMp3DownloadEntity.getDownloadMusicUrl());
    if (StringUtils.isEmpty(musicUrl))
    {
        return;
    }
        String downloadImageUrl = freeMp3DownloadEntity.getDownloadImageUrl();

        //过滤非法字符
        musicName = musicName.replaceAll("<[^>]*>", "");
        if (StringUtils.isEmpty(albumname)) {
            albumname = "other";
        }else{
            albumname = albumname.replaceAll("<[^>]*>", "");
        }
        ArrayList<String> artists = new ArrayList<>();
        if (artistname.contains(",")){
            String[] split = artistname.split(",");
            for (String s : split) {
                artists.add(s);
            }
        }else if (artistname.contains("/")){
            String[] split = artistname.split("/");
            for (String s : split) {
                artists.add(s);
            }
        }else if (artistname.contains("&")){
            String[] split = artistname.split("&");
            for (String s : split) {
                artists.add(s);
            }
        }else{
            artists.add(artistname);
        }
        String basepath =StringUtils.join(artists,"&") + File.separator +albumname.trim() + File.separator;
        System.out.println(file.getPath()+ File.separator +basepath + musicName.trim() + " - " + StringUtils.join(artists,"&"));
        File type  =  FileUtils.findFile(file+ File.separator +basepath , musicName.trim() + " - " + StringUtils.join(artists,"&"));
        if (type==null){
             type = new File(file.getPath()+ File.separator +basepath + musicName.trim() + " - " + StringUtils.join(artists,"&"));
        }
        //创建任务
        if (Boolean.valueOf(configService.getOne(new QueryWrapper<SqConfig>().eq("config_key", "music.override.download")).getConfigValue())) {
            if (type.exists()) {
                log.info("歌曲{}---->已存在不下载",musicName.trim());
            }
        }
        log.debug("开始下载---->{}", musicName);
        if (StringUtils.isEmpty(musicUrl)) {
            log.debug("下载失败{}", musicName);
            throw new RuntimeException("下载失败(找不到下载地址):" + musicName);
        }
        //下载位置
        String finalMusicName = musicName;
        String finalAlbumname = albumname;
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

//        boolean download = DownloadUtils.download(musicUrl, type);
//        if (download) {
//            log.debug("下载成功---->{}", musicName);
//
//        } else {
//            log.debug("下载失败---->{}", musicName);
//        }





        DownloadUtils.download(musicUrl, type, stringStringHashMap,onSuccess->{


            //放弃歌手图片以后再说
            //       是否下载专辑图片
            Boolean downloadalubimage = true;
            if (StringUtils.isEmpty(downloadImageUrl)) {
                downloadalubimage = false;
            }
            String artistsimagepath = musicPath + File.separator + StringUtils.join(artists,"&").trim();

            //人物图片
            File Artistsfile = FileUtils.findFile(artistsimagepath + File.separator ,"cover");
            //计算文件位置
            String imagePath = musicPath + File.separator + StringUtils.join(artists,"&") + File.separator + finalAlbumname;
            if (StringUtils.isEmpty(finalAlbumname) && finalAlbumname.trim().equals("other")) {
                String suffix = FileTypeUtil.getType(Artistsfile);
                boolean empty = FileUtil.exist(Artistsfile);
                if (empty){
                    FileUtil.copy(Artistsfile, new File(imagePath + File.separator + "cover."+suffix), true);

                }
            }
            AtomicReference<File> albumfile = new AtomicReference<>(FileUtils.findFile(imagePath + File.separator, "cover"));
            if (albumfile.get() ==null){
                albumfile.set(new File(imagePath + File.separator, "cover.jpg"));
            }


            //专辑图片下载与标签写入
            if (!albumfile.get().exists() && downloadalubimage){

                try {
                    DownloadUtils.download(downloadImageUrl, imagePath, onAlbumImg -> {
                        String cover = null;
                        try {
                            String suffix = FileTypeUtil.getType(onAlbumImg);
                            if(suffix.contains("webp")){
                                cover = onAlbumImg.getAbsolutePath();
                                File jpgimage = new File(onAlbumImg.getAbsolutePath().replace(".webp", ".jpg"));
                                onAlbumImg = ImageIOUtils.convertWebpToJpeg(onAlbumImg, jpgimage);
                            }
                            if (!FileTypeUtil.getType(onAlbumImg).contains("webp")){
                                FileUtil.del(cover);
                            }
                            suffix = FileTypeUtil.getType(onAlbumImg);
                            onAlbumImg= FileUtil.rename(onAlbumImg, "cover." + suffix, true);
//                            File copy = FileUtil.copy(onAlbumImg, new File(imagePath + File.separator + "cover." + suffix), true);
                            FileUtil.copy(onAlbumImg, new File(imagePath + File.separator + "album."+suffix), true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            FileUtil.del(onAlbumImg);
                        }finally {
                            try {
                                File parentFile = onAlbumImg.getParentFile();
                                boolean dirEmpty = FileUtil.isDirEmpty(parentFile);
                                if (dirEmpty) {
                                    FileUtil.del(parentFile);
                                }
                            } catch (IORuntimeException e) {
                                e.printStackTrace();
                            }
                        }

                        //写入标签
                        extracted(finalMusicName, finalAlbumname, StringUtils.join(artists,"&"), lyricStr,onSuccess, onAlbumImg);
                        //根据文件获取自己的后缀
                        String ext = FileTypeUtils.checkType(onSuccess);
                        String prefix = FileUtil.getPrefix(onSuccess);
                        //修改文件后缀
                        FileUtil.rename(onSuccess, prefix + ext, false, true);
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    //写入标签
                    extracted(finalMusicName, finalAlbumname, StringUtils.join(artists,"&"), lyricStr,onSuccess, null);
                    //根据文件获取自己的后缀
                    String ext = FileTypeUtils.checkType(onSuccess);
                    String prefix = FileUtil.getPrefix(onSuccess);
                    //修改文件后缀
                    FileUtil.rename(onSuccess, prefix + ext, false, true);
                }
            }else{

                //写入标签
                extracted(finalMusicName, finalAlbumname, StringUtils.join(artists,"&"), lyricStr,onSuccess, albumfile.get());
                //根据文件获取自己的后缀
                String ext = FileTypeUtils.checkType(onSuccess);
                String prefix = FileUtil.getPrefix(onSuccess);
                //修改文件后缀
                FileUtil.rename(onSuccess, prefix + ext, false, true);
            }

        },onFailure -> {
            onFailure.getException().printStackTrace();
            log.debug("下载失败{}", finalMusicName);
            throw new RuntimeException("下载失败(下载中出现问题):" + finalMusicName);
        });




    }



    /**
     * 根据歌曲情况写入到歌曲标签
     *
     */
    private  synchronized void extracted(String musicname, String albumName ,String artistName , String lyric,File musicFile, File albumfile) {
        //创建歌词
        try {
            if (StringUtils.isNotEmpty(lyric)) {
                String name = FileUtil.getPrefix(musicFile);
                log.debug("lrc地址{}", musicFile.getParentFile() + File.separator + name + ".lrc");
                FileUtil.writeBytes(lyric.getBytes(), musicFile.getParentFile() + File.separator + name + ".lrc");
            }
        } catch (IORuntimeException e) {
            log.error(e.getMessage());
        }
        //修改文件
        try {
            MusicUtils.setMediaFileInfo(musicFile, musicname, albumName,artistName, "SQ-FreeMp3", lyric, albumfile);
            log.debug("下载成功{}", musicname);
        } catch (Exception e) {
            log.debug("下载错误{}  ----------> {}", musicname, e.getMessage());
            log.error(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("下载失败:" + musicname + "------->" + e.getMessage());
        }
    }

}
