package com.fzu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fzu.entity.Class;
import com.fzu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //联合查询语句
    @Select("select * from user,class,user_class where user_class.cid=class.cid and user_class.sid=user.sid and user.id= #{id}")
    List<User> getById(@Param("id") String id);
}
