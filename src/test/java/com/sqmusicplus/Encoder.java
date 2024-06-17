package com.sqmusicplus;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

public class Encoder {
    static String  Es = "pW8jg/mke6cO1F4CTuaiswhZfQGzMyq5NJRLPVIvDxlA7=E3YrSUoH0b2BXKn9td+";
    static String  是是是 = "4b9qrOXu305U5Ex5U1yYv69jZO5EbznZq9nWaY5e5NW2GImw27aEBjL4OgW01Tpy";


    public static void main(String[] args) {

       Map<String, Object> data = new LinkedHashMap<>();
        data.put("type", "YQM");
        data.put("text", "jay");
        data.put("page", 1);
        data.put("v", "beta");
        data.put("_t", 1706099652039L);
//        String e = URLEncodeUtil.encodeFragment(JSONObject.toJSONString(data));
        String jsonString = mapToJsonString(data);
        String e = encodeURIComponent(jsonString);
        System.out.println(e);


//        String t = "yGz4n9XE9xYy2Oj5Ub7E6u9a5p5aIWZYe53Orq5wE5UgnjbWq0410WTvmLBO1Z2N";
        String t = "4b9qrOXu305U5Ex5U1yYv69jZO5EbznZq9nWaY5e5NW2GImw27aEBjL4OgW01Tpy";
        int[] qss = qs(t.toString(), e.toString());


        String bs = bs(qss);
        System.out.println(bs);

//        String s = SecureUtil.md5(qss);
//        String s1 = "20230327." + s;
//        System.out.println(qss);
    }


    private static String mapToJsonString(Map<String, Object> map) {
        return JSONObject.toJSONString(map);
    }

    private static String encodeURIComponent(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("%21", "!")
                    .replaceAll("%27", "'")
                    .replaceAll("%28", "(")
                    .replaceAll("%29", ")")
                    .replaceAll("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static int Fs(String e, Integer t) {
        // 确保t为非负数，如果是负数或有其他特定范围需求，请在此处进行处理
        int index = (int) (Math.floorMod((long) t, e.length()));
        char randomChar = e.charAt(index);
        return(int)randomChar;  // Java中字符本质上就是其Unicode编码值，可以直接转换为i
    }

//    public static String qs(String e, String t) {
//        char[] chars = t.toCharArray();
//        StringBuilder resultBuilder = new StringBuilder(chars.length);
//
//        for(int i = 0; i < chars.length; i++) {
//            char encryptedChar = (char) (chars[i] ^ Fs(e, i + 1));
//            resultBuilder.append(encryptedChar);
//        }
//
//        return resultBuilder.toString();
//    }
public  static  int[] qs(String e, String t) {
    char[] chars = t.toCharArray();
    int[] result = new int[chars.length];

    for(int i = 0; i < chars.length; i++) {
        result[i] = (int) chars[i] ^ Fs(e, i );
    }

    return result;
}

    public static String bs(int[] input) {
        int s = 0, o= 0, i= 0, a= 0, l= 0, r= 0, c= 0, u= 0, f = 0, v;
        StringBuilder h = new StringBuilder();
        String[] y = "3|0|4|1|2".split("\\|");

        while (f < input.length) {
            for (String step : y) {
                switch (step) {
                    case "0":
                        s = input[f++];
                        break;
                    case "1":
                        r = (u >> 6) & 63;
                        break;
                    case "2":
                        i = input[f++];
                        break;
                    case "3":
                        h.append(Es.charAt(a)).append(Es.charAt(l)).append(Es.charAt(r)).append(Es.charAt(c));
                        break;
                    case "4":
                        u = ((s << 16) | (o << 8)) | i;
                        break;
                    case "5":
                        l = (u >> 12) & 63;
                        break;
                    case "6":
                        o = input[f++];
                        break;
                    case "7":
                        a = (u >> 18) & 63;
                        break;
                    case "8":
                        c = u & 63;
                        break;
                }
            }
        }

        v = input.length % 3;
        return h + "===".substring(v >= 3 ? 3 : v);
    }
}
