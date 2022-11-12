package com.fzu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.entity.User;
import com.fzu.entity.UserClass;
import com.fzu.utils.Page;
import com.fzu.vo.UserClassPageVO;

public interface UserClassService extends IService<UserClass> {

    UserClass getBySid(String sid);

    public Page<User> findPage(Page<User> page, UserClassPageVO pageVO);
}
