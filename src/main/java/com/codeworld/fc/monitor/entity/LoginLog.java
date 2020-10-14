package com.codeworld.fc.monitor.entity;

import com.codeworld.fc.utils.HttpContextUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * ClassName LoginLog
 * Description 登录日志
 * Author Lenovo
 * Date 2020/10/10
 * Version 1.0
 **/
@Data
@ApiModel("登录日志")
public class LoginLog {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginLog.class);

    @ApiModelProperty("主键Id")
    private Long loginLogId;

    @ApiModelProperty("登录名")
    private String loginLogName;

    @ApiModelProperty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginLogTime;

    @ApiModelProperty("登录地点")
    private String loginLogLocation;

    @ApiModelProperty("登录Ip")
    private String loginLogIp;

    @ApiModelProperty("登录系统")
    private String loginLogSystem;

    @ApiModelProperty("登录浏览器")
    private String loginLogBrowser;

    public void setSystemBrowserInfo() {
        try {
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

            StringBuilder userAgent = new StringBuilder("[");
            userAgent.append(request.getHeader("User-Agent"));
            userAgent.append("]");
            int indexOfMac = userAgent.indexOf("Mac OS X");
            int indexOfWindows = userAgent.indexOf("Windows NT");
            int indexOfIE = userAgent.indexOf("MSIE");
            int indexOfIE11 = userAgent.indexOf("rv:");
            int indexOfFF = userAgent.indexOf("Firefox");
            int indexOfSogou = userAgent.indexOf("MetaSr");
            int indexOfChrome = userAgent.indexOf("Chrome");
            int indexOfSafari = userAgent.indexOf("Safari");
            boolean isMac = indexOfMac > 0;
            boolean isWindows = indexOfWindows > 0;
            boolean isLinux = userAgent.indexOf("Linux") > 0;
            boolean containIE = indexOfIE > 0 || (isWindows && (indexOfIE11 > 0));
            boolean containFF = indexOfFF > 0;
            boolean containSogou = indexOfSogou > 0;
            boolean containChrome = indexOfChrome > 0;
            boolean containSafari = indexOfSafari > 0;
            String browser = "";
            if (containSogou) {
                if (containIE) {
                    browser = "搜狗" + userAgent.substring(indexOfIE, indexOfIE + "IE x.x".length());
                } else if (containChrome) {
                    browser = "搜狗" + userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
                }
            } else if (containChrome) {
                browser = userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
            } else if (containSafari) {
                int indexOfSafariVersion = userAgent.indexOf("Version");
                browser = "Safari "
                        + userAgent.substring(indexOfSafariVersion, indexOfSafariVersion + "Version/x.x.x.x".length());
            } else if (containFF) {
                browser = userAgent.substring(indexOfFF, indexOfFF + "Firefox/xx".length());
            } else if (containIE) {
                if (indexOfIE11 > 0) {
                    browser = "IE 11";
                } else {
                    browser = userAgent.substring(indexOfIE, indexOfIE + "IE x.x".length());
                }
            }
            String os = "";
            if (isMac) {
                os = userAgent.substring(indexOfMac, indexOfMac + "MacOS X xxxxxxxx".length());
            } else if (isLinux) {
                os = "Linux";
            } else if (isWindows) {
                os = "Windows ";
                String version = userAgent.substring(indexOfWindows + "Windows NT".length(), indexOfWindows
                        + "Windows NTx.x".length());
                switch (version.trim()) {
                    case "5.0":
                        os += "2000";
                        break;
                    case "5.1":
                        os += "XP";
                        break;
                    case "5.2":
                        os += "2003";
                        break;
                    case "6.0":
                        os += "Vista";
                        break;
                    case "6.1":
                        os += "7";
                        break;
                    case "6.2":
                        os += "8";
                        break;
                    case "6.3":
                        os += "8.1";
                        break;
                    case "10":
                        os += "10";
                        break;
                }
            }
            this.loginLogSystem = os;
            this.loginLogBrowser = StringUtils.replace(browser, "/", " ");
        } catch (Exception e) {
            LOGGER.error("获取登录信息失败：{}", e.getMessage());
            this.loginLogSystem = "";
            this.loginLogBrowser = "";
        }
    }
}
