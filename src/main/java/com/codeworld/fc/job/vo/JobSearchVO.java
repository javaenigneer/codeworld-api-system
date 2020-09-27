package com.codeworld.fc.job.vo;

import com.codeworld.fc.common.base.PageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName JobSearchVO
 * Description 任务搜索基本类
 * Author Lenovo
 * Date 2020/9/21
 * Version 1.0
**/
@Data
@ApiModel("任务搜索基本类")
public class JobSearchVO extends PageQuery {

    @ApiModelProperty("任务名称")
    private String beanName;

    @ApiModelProperty("参数名称")
    private String methodName;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
