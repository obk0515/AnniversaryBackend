package com.fzu.entity;

import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.fzu.utils.PictureUtil;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Component
public class Face {
    //设置APPID/AK/SK
    public static final String APP_ID = "28156335";
    public static final String API_KEY = "POBhi7kfUEfn8XXGEuqHlv4a";
    public static final String SECRET_KEY = "36DChfEGp6tAjLuleCpbHuWBC3hfQ53a";

    public static String face(AipBodyAnalysis client, MultipartFile faceFile) {
        //传入可选参数调入接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("type", "foreground");
        //参数为本地路径
        byte[] image = new byte[0];
        try {
            image = faceFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject res = client.bodySeg(image, options);
        return res.get("foreground").toString();
    }


    public String cutFace(MultipartFile faceFile) {
        AipBodyAnalysis client = new AipBodyAnalysis(Face.APP_ID, Face.API_KEY, Face.SECRET_KEY);
//        String sam = Face.Face(c);
//        System.out.println(sam);
//        Img2Base64Util img2Base64Util = new Img2Base64Util();
//        img2Base64Util.generateImage(sam, "C:\\Users\\何帆\\IdeaProjects\\SpringBoot\\ruangong\\src\\main\\java\\com\\fzu\\face\\image\\2.png");
        String cutResult = Face.face(client,faceFile);
        return cutResult;
    }

}
