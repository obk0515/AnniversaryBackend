package com.fzu.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user_class")
@Data
public class User_Class {
    private String uid;
    private String sid;
    private String cid;
}
