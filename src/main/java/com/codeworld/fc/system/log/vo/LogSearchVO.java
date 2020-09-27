package com.codeworld.fc.system.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName LogSearchVO
 * Description 日志搜索信息
 * Author Lenovo
 * Date 2020/9/20
 * Version 1.0
**/
@Data
@ApiModel("日志搜索信息")
public class LogSearchVO {

    @ApiModelProperty("页数")
    private Integer page;

    @ApiModelProperty("页数")
    private Integer limit;
}
