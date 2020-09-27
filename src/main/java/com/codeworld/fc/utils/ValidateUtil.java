package com.codeworld.fc.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 信息校验工具类
 */
public class ValidateUtil {

    /**
     * 邮箱校验规则
     */
    private static final String emailRegex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 校验参数有效性
     * @param params
     * @return
     */
    public static boolean validateParams(String params){

        if (StringUtils.isBlank(params)){

            return false;
        }

        return true;
    }

    /**
     * 校验邮箱合法性
     * @param email
     * @return
     */
    public static boolean validateEmailRegex(String email){

        // 校验合格
        if (Pattern.matches(emailRegex,email)){

            return true;
        }

        // 校验不合格
        return false;
    }


    /**
     * 判断是否包含中文
     *
     * @param value 内容
     * @return 结果
     */
    public static boolean containChinese(String value) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(value);
        return m.find();
    }
}
