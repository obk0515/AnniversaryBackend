package com.fzu.controller;

import com.fzu.entity.Face;
import com.fzu.result.ServiceResult;
import com.fzu.utils.PictureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@RequestMapping("")
@Api(value = "FaceController", tags = "抠图模块")
public class FaceController {

    @Autowired
    private Face face;

    @Autowired
    private PictureUtil pictureUtil;

    @ApiOperation(value = "获取抠图结果(字符串)")
    @PostMapping("/getface")
    public ServiceResult<String> getFace(@ApiParam(value = "上传的图片", required = true) MultipartFile file,@ApiParam @RequestParam String openId) {
        if(file == null) {
            return ServiceResult.createByErrorMessage("上传图像失败");
        }
        String cutResult = face.cutFace(file);
        byte[] bytes = Base64.getDecoder().decode(cutResult);
        try {
            pictureUtil.sshSftp(bytes,openId + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServiceResult.createBySuccess("https://www.prxdong.top/images/" + openId + ".jpg");
    }

    @ApiOperation(value = "删除抠图结果")
    @PostMapping("/delFace")
    public ServiceResult<String> delFace(@ApiParam @RequestParam String openId) {
        try {
            pictureUtil.delPhoto(openId + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServiceResult.createBySuccess("https://www.prxdong.top/images/" + openId + ".jpg");
    }
}
