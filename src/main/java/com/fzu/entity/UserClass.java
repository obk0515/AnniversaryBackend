package com.fzu.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@TableName("user_class")
@ApiModel(value = "UserClass", description = "UserClass")
@Data
public class UserClass {
    private String uid;
    private String sid;
    private String cid;
}
