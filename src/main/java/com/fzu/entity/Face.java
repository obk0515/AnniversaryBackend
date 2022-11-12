package com.fzu.entity;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.fzu.utils.Img2Base64Util;
import org.json.JSONObject;

import java.util.HashMap;

public class Face {
    //设置APPID/AK/SK
    public static final String APP_ID = "28156335";
    public static final String API_KEY = "POBhi7kfUEfn8XXGEuqHlv4a";
    public static final String SECRET_KEY = "36DChfEGp6tAjLuleCpbHuWBC3hfQ53a";

    public static String Face(AipBodyAnalysis client) {
        //传入可选参数调入接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("type", "foreground");
        //参数为本地路径
        String image = "C:\\Users\\何帆\\IdeaProjects\\SpringBoot\\ruangong\\src\\main\\java\\com\\fzu\\face\\image\\2.png";// 图片的路径
        JSONObject res = client.bodySeg(image, options);
        return res.get("foreground").toString();
    }

    void Cut_Face() {
        AipBodyAnalysis c = new AipBodyAnalysis(Face.APP_ID, Face.API_KEY, Face.SECRET_KEY);
        String sam = Face.Face(c);
        System.out.println(sam);
        Img2Base64Util img2Base64Util = new Img2Base64Util();
        img2Base64Util.generateImage(sam, "C:\\Users\\何帆\\IdeaProjects\\SpringBoot\\ruangong\\src\\main\\java\\com\\fzu\\face\\image\\2.png");
    }

}
