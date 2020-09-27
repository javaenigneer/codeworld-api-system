package com.codeworld.fc.job.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassName JobRequestDTO
 * Description 任务请求DTO
 * Author Lenovo
 * Date 2020/9/25
 * Version 1.0
**/
@Data
@ApiModel("任务请求DTO")
public class JobRequestDTO {

    @ApiModelProperty("任务名称")
    @NotNull(message = "任务名称为空")
    private String beanName;

    @ApiModelProperty("参数名称")
    @NotNull(message = "参数名称为空")
    private String methodName;

    @ApiModelProperty("参数")
    @NotNull(message = "参数为空")
    private String params;

    @ApiModelProperty("cron表达式")
    @NotNull(message = "Cron表达式为空")
    private String cronExpression;

    @ApiModelProperty("状态")
    @NotNull(message = "状态错误")
    private String status;

    @ApiModelProperty("备注")
    @NotNull(message = "备注为空")
    private String remark;
}
