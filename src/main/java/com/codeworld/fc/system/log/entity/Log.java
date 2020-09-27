package com.codeworld.fc.system.log.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName Log
 * Description 日志基本类
 * Author Lenovo
 * Date 2020/9/20
 * Version 1.0
**/
@Data
@ApiModel("日志基本类")
public class Log {

    @ApiModelProperty("日志Id")
    private Long logId;

    @ApiModelProperty("日志操作人")
    private String logOperationUserName;

    @ApiModelProperty("日志操作描述")
    private String logOperation;

    @ApiModelProperty("日志操作时间")
    private Long logTime;

    @ApiModelProperty("日志操作方法")
    private String logMethod;

    @ApiModelProperty("日志操作方法参数")
    private String logParams;

    @ApiModelProperty("日志操作IP")
    private String logIp;

    @ApiModelProperty("日志操作地点")
    private String logLocation;

    @ApiModelProperty("日志操作时间")
    private Date logCreateTime;
}
