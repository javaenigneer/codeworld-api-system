package com.codeworld.fc.common.authority;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * ClassName JWTToken
 * Description JWTToken基本类
 * Author Lenovo
 * Date 2020/9/15
 * Version 1.0
**/
@Data
@ApiModel("JWTToken基本类")
public class JWTToken implements AuthenticationToken {

    @ApiModelProperty("Token认证")
    private String token;

    @ApiModelProperty("过期时间")
    private String expireAt;


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String expireAt) {
        this.token = token;
        this.expireAt = expireAt;
    }
}
