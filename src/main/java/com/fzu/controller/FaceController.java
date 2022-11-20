package com.fzu.controller;

import com.fzu.entity.Face;
import com.fzu.result.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("")
@Api(value = "FaceController", tags = "抠图模块")
public class FaceController {

    @Autowired
    private Face face;
    //    void Cut_Face() {
//        AipBodyAnalysis c = new AipBodyAnalysis(Face.APP_ID, Face.API_KEY, Face.SECRET_KEY);
//        String sam = Face.Face(c);
//        System.out.println(sam);
//        Img2Base64Util img2Base64Util = new Img2Base64Util();
//        img2Base64Util.generateImage(sam, "C:\\Users\\何帆\\IdeaProjects\\SpringBoot\\ruangong\\src\\main\\java\\com\\fzu\\face\\image\\2.png");
//    }

    @ApiOperation(value = "获取抠图结果(字符串)")
    @PostMapping("/getface")
    public ServiceResult<String> getFace(@ApiParam(value = "上传的图片", required = true) MultipartFile file) {
        if(file == null) {
            return ServiceResult.createByErrorMessage("上传图像失败");
        }
        String cutResult = face.cutFace(file);
        return ServiceResult.createBySuccess(cutResult);
    }
}
