package com.fzu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@ApiModel(value = "UserClassPageV0", description = "UserClassPageV0")
public class UserClassPageVO {
    @Min(value = 1, message = "当前页码必须为整数或者大于等于1")
    @ApiModelProperty(value = "当前页码", name = "pageNo", example = "1")
    private Integer pageNo;

    @Min(value = 1, message = "每页数量必须为整数或者大于等于1")
    @ApiModelProperty(value = "每页数量", name = "pageSize", example = "20")
    private Integer pageSize;

    @ApiModelProperty(value = "用户id", name = "Uid", example = "2")
    private String Uid;

    @ApiModelProperty(value = "学生学号", name = "Sid", example = "032002509")
    private String Sid;

    @ApiModelProperty(value = "学历", name = "stage", example = "本科")
    private String stage;

    @ApiModelProperty(value = "专业", name = "major", example = "计算机类")
    private String major;

    @ApiModelProperty(value = "年级", name = "grade", example = "2020")
    private String grade;

    @ApiModelProperty(value = "班级", name = "class_no", example = "1")
    private String class_no;

    @ApiModelProperty(value = "班级id", name = "cid", example = "112233")
    private String Cid;

}
