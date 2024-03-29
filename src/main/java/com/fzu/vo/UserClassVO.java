package com.fzu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserClassVO", description = "UserClassVO")
public class UserClassVO {
    @ApiModelProperty(value = "用户id", name = "Uid", example = "2")
    private String uid;

    @ApiModelProperty(value = "学生学号", name = "Sid", example = "032002509")
    private String sid;

    @ApiModelProperty(value = "学历", name = "stage", example = "本科")
    private String stage;

    @ApiModelProperty(value = "学院", name = "academy", example = "计算机与大数据学院")
    private String academy;

    @ApiModelProperty(value = "专业", name = "major", example = "计算机类")
    private String major;

    @ApiModelProperty(value = "年级", name = "grade", example = "2020")
    private String grade;

    @ApiModelProperty(value = "班级", name = "class_no", example = "1")
    private String classNo;

    @ApiModelProperty(value = "班级id", name = "cid", example = "112233")
    private String cid;
}
