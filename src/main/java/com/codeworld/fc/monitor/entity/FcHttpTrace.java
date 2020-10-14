package com.codeworld.fc.monitor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.net.URI;

/**
 * ClassName FcHttpTrace
 * Description 请求追踪基本类
 * Author Lenovo
 * Date 2020/10/9
 * Version 1.0
**/
@Data
@ApiModel("请求追踪基本类")
public class FcHttpTrace {

    @ApiModelProperty("请求时间")
    private String requestTime;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("请求Url")
    private URI url;

    @ApiModelProperty("响应状态")
    private int status;

    @ApiModelProperty("请求耗时")
    private Long timeTaken;
}
