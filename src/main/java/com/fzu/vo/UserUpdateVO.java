package com.fzu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "UserUpdateVO", description = "UserUpdateVO")
public class UserUpdateVO {

    //@ApiModelProperty(value = "编号", example = "1")
    //@NotNull(message = "编号不能为空")
    private String id;

    @ApiModelProperty(value = "学号", example = "123456")
    private String sid;

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "邮箱", example = "12345678@qq.com")
    private String email;

    @ApiModelProperty(value = "宿舍号", example = "32#111")
    private String dormitory;

    @ApiModelProperty(value = "性别", example = "0")
    private String sex;

    @ApiModelProperty(value = "毕业时间", example = "2018-07-01")
    private Date graduationTime;


}
