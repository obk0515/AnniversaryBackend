package com.fzu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.entity.User;
import com.fzu.mapper.UserMapper;
import com.fzu.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByOpenId(String openId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openId);
        return this.getOne(wrapper);
    }

    @Override
    public User getById(String Id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", Id);
        return this.getOne(wrapper);
    }

}
