package com.fzu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BlessPageVO", description = "BlessPageVO")
public class BlessPageVo {

    @ApiModelProperty(value = "当前页码", name = "pageNo", example = "1")
    private Integer pageNo;

    @ApiModelProperty(value = "每页数量", name = "pageSize", example = "20")
    private Integer pageSize;
}
