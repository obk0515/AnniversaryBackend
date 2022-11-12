package com.fzu.controller;


import com.alibaba.fastjson.JSON;
import com.fzu.entity.User;
import com.fzu.entity.UserClass;
import com.fzu.mapper.UserMapper;
import com.fzu.result.ServiceResult;
import com.fzu.service.ClassService;
import com.fzu.service.UserClassService;
import com.fzu.service.UserService;
import com.fzu.vo.UserAddVO;
import com.fzu.vo.UserUpdateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = "个人资料")
public class UserController {

    @Autowired
    private UserService userService;
    //关联数据  cid
    @Autowired
    private ClassService classService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserClassService userClassService;

    @ApiOperation(value = "初始创建")
    @PostMapping("/add")
    public ServiceResult<User> addUser(@Validated @RequestBody UserAddVO userAddVO, BindingResult bindingResult) {
        //参数验证
        if (bindingResult.hasErrors()) {
            return ServiceResult.createByErrorMessage(String.valueOf(bindingResult.getFieldErrors()));
        }
        if (userService.getByOpenId(userAddVO.getOpenId()) != null) {
            return ServiceResult.createByErrorMessage("已存在微信绑定用户");
        }
        User user = new User();
        BeanUtils.copyProperties(userAddVO, user);
        user.setBanFlag("0");
        if (!userService.save(user)) {
            return ServiceResult.createByErrorMessage("创建失败");
        }
        return ServiceResult.createBySuccess(user);
    }

    //个人资料模块展示，通过id查询，，，
    @GetMapping("/data/{id}")
    public ServiceResult<User> getById(@PathVariable String id) {

        return ServiceResult.createBySuccessList(userMapper.getById(id));
    }

    //保存个人信息
    @PostMapping("/save/{id}")
    public ServiceResult<User> updateById(@RequestBody UserUpdateVO userUpdateVO, @PathVariable String id) {
        //获取与之对应的用户信息
        User user = userService.getById(id);
        //获取与之对应的班级信息，是否存在此班级
        String classId = classService.getClassId(JSON.parseObject(userUpdateVO.getClassDetail()));
        if (classId == null) {
            return ServiceResult.createByErrorMessage("不存在此班级");
        }
        //获取U_C表里面对应学号信息
        UserClass uc = userClassService.getBySid(userUpdateVO.getSid());
        //如果us表和user表的cid不等，则修改uc表对应信息
        if (!Objects.equals(uc.getCid(), classId)) {
            uc.setCid(classId);
        }
        //修改user表对应信息
        if (!userService.updateById(user)) {
            return ServiceResult.createByErrorMessage("修改失败");
        }

        return ServiceResult.createBySuccess(user);
    }

}
