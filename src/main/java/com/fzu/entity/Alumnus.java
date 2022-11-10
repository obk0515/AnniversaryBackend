package com.fzu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("alumnus")
@Data
public class Alumnus {
    //校友id，校友名字，校友概述，校友照片

    private String id;
    private String name;
    private String brief;
    private String photo;
}
