package com.codeworld.fc.common.enums;

/**
 * 响应信息
 */
public class HttpMsg {


    /**
     * 用户相关
     */
    public enum user {

        USER_STATUS_SUCCESS("用户状态修改成功"),

        USER_DELETE_SUCCESS("用户删除成功"),

        USER_ID_ERROR("用户ID错误"),

        USER_LOGIN_SUCCESS("登录成功"),

        USER_LOGIN_ERROR("用户未登录"),

        USER_AUTH_SUCCESS("认证成功"),

        USER_AUTH_ERROR("认证失败"),

        USER_ADD_SUCCESS("用户添加成功"),

        USER_EXIST("用户名存在"),

        USER_NO_EXIST("用户不存在"),

        USER_NAME_RIGHT("用户名可用"),

        USER_UPDATE_SUCCESS("用户信息修改成功"),

        USER_DISABLE("用户被禁用");

        private String msg;

        user(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 菜单相关
     */
    public enum menu {

        MENU_GET_SUCCESS("菜单查询成功"),

        MENU_UPDATE_SUCCESS("菜单修改成功"),

        MENU_ADD_SUCCESS("菜单添加成功"),

        MENU_DELETE_SUCCESS("菜单删除成功"),

        MENU_PARAM_ERROR("参数错误");

        private String msg;

        menu(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 角色相关
     */
    public enum role {

        ROLE_DATA_EMPTY("角色数据为空"),

        ROLE_DATA_SUCCESS("角色查询成功"),

        ROLE_PARAM_ERROR("角色参数错误"),

        ROLE_MENU_DATA_SUCCESS("角色菜单查询成功"),

        ROLE_UPDATE_SUCCESS("角色修改成功"),

        ROLE_MENU_ADD_SUCCESS("角色菜单设置成功"),

        ROLE_ADD_SUCCESS("角色添加成功"),

        ROLE_DELETE_SUCCESS("角色删除成功");

        private String msg;

        role(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 日志相关
     */
    public enum log {

        LOG_DATA_SUCCESS("日志查询成功"),

        LOG_DATA_EMPTY("日志数据为空");

        private String msg;

        log(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 任务相关
     */
    public enum job {

        JOB_DATA_EMPTY("任务数据为空"),

        JOB_DATA_SUCCESS("任务查询成功"),

        JOB_PARAMS_ERROR("任务参数错误"),

        JOB_STATUS_SUCCESS("任务状态已更新"),

        JOB_DELETE_SUCCESS("任务删除成功"),

        JOB_ADD_SUCCESS("任务添加成功");

        private String msg;

        job(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 任务日志相关
     */
    public enum jobLog {

        JOB_LOG_DATA_EMPTY("任务日志数据为空"),
        JOB_LOG_DATA_SUCCESS("任务日志查询成功");

        private String msg;

        jobLog(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }
}
