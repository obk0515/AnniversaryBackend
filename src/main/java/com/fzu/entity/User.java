package com.fzu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data
public class User {
    private String id;
    @TableField("open_id")
    private String openId;
    private String sid;
    private String name;
    private String email;
    private String dormitory;
    private String sex;
    @TableField("graduation_time")
    private Date graduationTime;
    @TableField("ban_flag")
    private String banFlag;

}
