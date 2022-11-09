package com.fzu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@TableName("user")
@ApiModel(value = "Class", description = "Class")
@Data
public class Class {
    private String id;
    private String cid;
    private String academy;
    private String stage;
    private String grade;
    private String major;
    @TableField("class_no")
    private Integer classNo;

}
