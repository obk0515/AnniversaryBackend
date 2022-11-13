package com.fzu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.entity.User;

public interface UserService extends IService<User> {

    User getByOpenId(String openId);

    User getById(String Id);

}
