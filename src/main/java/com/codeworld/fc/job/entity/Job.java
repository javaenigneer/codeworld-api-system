package com.codeworld.fc.job.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName Job
 * Description 任务基本类
 * Author Lenovo
 * Date 2020/9/21
 * Version 1.0
**/
@Data
@ApiModel("任务基本类")
public class Job implements Serializable {

    private static final long serialVersionUID = -4112400672553547303L;

    /**
     * 任务调度参数Key
     */
    public static final String
            JOB_PARAM_KEY = "JOB_PARAM_KEY";

    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL("1"),
        /**
         * 暂停
         */
        PAUSE("0");

        private String value;

        ScheduleStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @ApiModelProperty("任务调度主键Id")
    private Long jobId;

    @ApiModelProperty("任务名称")
    private String beanName;

    @ApiModelProperty("参数名称")
    private String methodName;

    @ApiModelProperty("参数")
    private String params;

    @ApiModelProperty("cron表达式")
    private String cronExpression;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
