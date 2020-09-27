package com.codeworld.fc.common.authority;

import org.springframework.security.core.GrantedAuthority;

/**
 * ClassName GrantedAuthorityImpl
 * Description 权限封装
 * Author Lenovo
 * Date 2020/9/27
 * Version 1.0
**/
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
