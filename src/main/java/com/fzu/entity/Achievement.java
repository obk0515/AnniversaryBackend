package com.fzu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("achievement")
@Data
public class Achievement {
    private String id;
    private String type;
    private String title;
    private String description;
    private String rank;
}
