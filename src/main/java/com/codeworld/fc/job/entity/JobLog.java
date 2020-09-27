package com.codeworld.fc.job.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName JobLog
 * Description 任务日志类
 * Author Lenovo
 * Date 2020/9/24
 * Version 1.0
**/
@Data
@ApiModel("任务日志类")
public class JobLog {

    @ApiModelProperty("任务日志主键Id")
    private Long jobLogId;

    @ApiModelProperty("任务Id")
    private Long jobId;

    @ApiModelProperty("任务Bean名称")
    private String jobBeanName;

    @ApiModelProperty("任务方法名称")
    private String jobMethodName;

    @ApiModelProperty("任务参数")
    private String jobParams;

    @ApiModelProperty("任务状态")
    private Integer jobStatus;

    @ApiModelProperty("错误信息")
    private String jobLogError;

    @ApiModelProperty("任务耗时")
    private Long jobLogTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date jobLogCreateTime;
}
