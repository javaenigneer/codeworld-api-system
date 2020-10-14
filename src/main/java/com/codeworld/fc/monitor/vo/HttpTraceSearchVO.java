package com.codeworld.fc.monitor.vo;

import com.codeworld.fc.common.base.PageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName HttpTraceSearchVO
 * Description 请求追踪搜索VO
 * Author Lenovo
 * Date 2020/10/10
 * Version 1.0
**/
@Data
@ApiModel("请求追踪搜索VO")
public class HttpTraceSearchVO extends PageQuery {

    @ApiModelProperty("请求地址")
    private String url;

    @ApiModelProperty("请求方式")
    private String method;
}
