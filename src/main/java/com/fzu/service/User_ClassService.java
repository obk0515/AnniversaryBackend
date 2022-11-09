package com.fzu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.entity.User_Class;

public interface User_ClassService extends IService<User_Class> {

    User_Class getBySid(String sid);
}
