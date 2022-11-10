package com.fzu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("program")
@Data
public class Program {
    //节目ID，节目名称，节目类型，节目优先级

    private String id;
    private String name;
    private String type;
    private Integer priority;
}

