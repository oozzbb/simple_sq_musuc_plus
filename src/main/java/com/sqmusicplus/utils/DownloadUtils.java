package com.sqmusicplus.utils;

import com.ejlchina.okhttps.Download;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.Process;
import okhttp3.OkHttpClient;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Classname DownloadUtils
 * @Description 下载工具类
 * @Version 1.0.0
 * @Date 2022/5/31 15:14
 * @Created by SQ
 */

public class DownloadUtils {

   public static void download(String url, String path, String fileName, Consumer<Process> onProcess,Consumer<File> onSuccess) {
       HTTP http = HTTP.builder()
               .config((OkHttpClient.Builder builder) -> {
                   // 连接超时时间（默认10秒）
                   builder.connectTimeout(7, TimeUnit.DAYS);
                   // 写入超时时间（默认10秒）
                   builder.writeTimeout(7, TimeUnit.DAYS);
                   // 读取超时时间（默认10秒）
                   builder.readTimeout(7, TimeUnit.DAYS);
               })
               .build();
        HttpResult.Body body = http.async(url)
                .get()
                .getResult()
                .getBody();
        if (onProcess != null) {
            body.setOnProcess(onProcess);
        }
       Download download;
        if(fileName == null){
            download= body.toFile(path + fileName);
        }else{
            download = body.toFolder(path);
        }
        if (onSuccess != null) {
            download.setOnSuccess(onSuccess);
        }
        download.start();
    }

    public static void download(String url, File file, Consumer<Process> onProcess,Consumer<File> onSuccess,Consumer<Download.Failure> onFailure) {
        HTTP http = HTTP.builder()
                .config((OkHttpClient.Builder builder) -> {
                    // 连接超时时间（默认10秒）
                    builder.connectTimeout(7, TimeUnit.DAYS);
                    // 写入超时时间（默认10秒）
                    builder.writeTimeout(7, TimeUnit.DAYS);
                    // 读取超时时间（默认10秒）
                    builder.readTimeout(7, TimeUnit.DAYS);
                })
                .build();
       HttpResult.Body body = http.async(url)
                .get()
                .getResult()
                .getBody();
        if (onProcess != null) {
            body.setOnProcess(onProcess);
        }
        Download download = body.toFile(file);
        if (onSuccess != null) {
            download.setOnSuccess(onSuccess);
        }
        if (onFailure!=null){
            download.setOnFailure(onFailure);
        }
        download.start();
    }

    public static void download(String url, String path, String fileName,Consumer<File> onSuccess) {
        download(url,path,fileName,null,onSuccess);
    }
    public static void download(String url, String path, String fileName) {
        download(url,path,fileName,null,null);
    }
    public static void download(String url, String path, Consumer<File> onSuccess) {
        download(url,path,null,null,onSuccess);
    }
    public static void download(String url, File file,Consumer<File> onSuccess ,Consumer<Download.Failure> onFailure) {
        download(url,file,null,onSuccess,onFailure);
    }


//    downloadImage(){
//
//    }

}
