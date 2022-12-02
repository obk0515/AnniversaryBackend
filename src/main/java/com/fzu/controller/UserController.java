package com.fzu.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.entity.User;
import com.fzu.mapper.UserMapper;
import com.fzu.result.ServiceResult;
import com.fzu.service.ClassService;
import com.fzu.service.UserClassService;
import com.fzu.service.UserService;
import com.fzu.vo.UserAddVO;
import com.fzu.vo.UserUpdateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = "个人资料")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${wechat.AppID}")
    private String appId;

    @Value("${wechat.AppSecret}")
    private String appSecret;

    @ApiOperation(value = "初始创建")
    @PostMapping("/add")
    public ServiceResult<User> addUser(@Validated @RequestBody UserAddVO userAddVO, BindingResult bindingResult) {
        //参数验证
        if (bindingResult.hasErrors()) {
            return ServiceResult.createByErrorMessage(String.valueOf(bindingResult.getFieldErrors()));
        }
        if (userService.getByOpenId(userAddVO.getOpenId()) != null) {
            User user = userService.getByOpenId(userAddVO.getOpenId());
            return ServiceResult.createBySuccess(user);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddVO, user);
        user.setBanFlag("0");
        if (!userService.save(user)) {
            return ServiceResult.createByErrorMessage("创建失败");
        }
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("open_id",userAddVO.getOpenId());
        user=userService.getOne(wrapper);
        return ServiceResult.createBySuccess(user);
    }

    //个人资料模块展示，通过id查询，，，
    @GetMapping("/data/{id}")
    public ServiceResult<User> getById(@PathVariable String id) {
        //失败
        if (userService.getById(id) == null) {
            return ServiceResult.createByErrorMessage("没有此用户");
        }
        return ServiceResult.createBySuccess(userService.getById(id));
    }

    //保存个人信息
    @PostMapping("/save")
    public ServiceResult<User> updateById(@RequestBody UserUpdateVO userUpdateVO) {
        if (userService.getById(userUpdateVO.getId()) == null) {
            return ServiceResult.createByErrorMessage("没有此用户");
        }
        //获取与之对应的用户信息
        User user = new User();
        //直接复制数据
        BeanUtils.copyProperties(userUpdateVO, user);
        //修改user表对应信息
        if (!userService.updateById(user)) {
            return ServiceResult.createByErrorMessage("修改失败");
        }
        return ServiceResult.createBySuccess(user);
    }


    @PostMapping("/getMessage")
    public ServiceResult<List<String>> getMessage() {
        List<String> list=new ArrayList<>(2);
        list.add(appId);
        list.add(appSecret);
        return ServiceResult.createBySuccess(list);
    }

    @GetMapping("/getOpenId/{code}")
    public JSONObject getOpenId(@PathVariable String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = null;
        try {
            //构建一个Client
            HttpClient client = HttpClientBuilder.create().build();
            //构建一个GET请求
            HttpGet get = new HttpGet(url);
            //提交GET请求
            HttpResponse response = client.execute(get);
            //拿到返回的HttpResponse的"实体"
            HttpEntity result = response.getEntity();
            String content = EntityUtils.toString(result);
            //把信息封装为json
            jsonObject = JSONObject.parseObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
