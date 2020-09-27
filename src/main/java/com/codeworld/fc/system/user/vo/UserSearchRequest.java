package com.codeworld.fc.system.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ClassName UserSearchRequest
 * Description 用户搜索Request
 * Author Lenovo
 * Date 2020/8/23
 * Version 1.0
 **/
@Data
@ApiModel("用户搜索Request")
public class UserSearchRequest {

    @ApiModelProperty("页数")
    private Integer page;

    @ApiModelProperty("数量")
    private Integer limit;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户邮箱")
    private String userEmail;

    @ApiModelProperty("用户手机")
    private String userPhone;

    @ApiModelProperty("用户状态")
    private Integer userStatus;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", locale = "zh")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", locale = "zh")
    private Date updateTime;

}
