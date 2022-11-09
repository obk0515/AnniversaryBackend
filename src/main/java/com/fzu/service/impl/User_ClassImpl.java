package com.fzu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.entity.User_Class;
import com.fzu.mapper.User_ClassMapper;
import com.fzu.service.User_ClassService;
import org.springframework.stereotype.Service;

@Service
public class User_ClassImpl extends ServiceImpl<User_ClassMapper, User_Class> implements User_ClassService {

    @Override
    public User_Class getBySid(String sid) {
        QueryWrapper<User_Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sid",sid);
        return this.getOne(queryWrapper);
    }
}
