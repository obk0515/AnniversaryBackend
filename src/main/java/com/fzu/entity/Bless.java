package com.fzu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 云祝福的类
 */

@TableName("bless")
@Data
public class Bless {

    @TableField(fill = FieldFill.INSERT)
    private String id;

    private String bless;
    private String status;

    @TableField("create_by")
    private String createBy;
    @TableField("create_time")
    private Date createTime;

}
