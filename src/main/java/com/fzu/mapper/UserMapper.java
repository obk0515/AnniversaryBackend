package com.fzu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fzu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
