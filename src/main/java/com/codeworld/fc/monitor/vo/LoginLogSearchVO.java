package com.codeworld.fc.monitor.vo;

import com.codeworld.fc.common.base.PageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName LoginLogSearchVO
 * Description 登录日志搜索
 * Author Lenovo
 * Date 2020/10/10
 * Version 1.0
**/
@Data
@ApiModel("登录日志搜索")
public class LoginLogSearchVO extends PageQuery {

    @ApiModelProperty("登录名")
    private String loginLogName;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
}
