package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 */
public class CmsLoginLog implements Serializable {
    @ApiModelProperty(value = "登陆日志id")
    private int login_log_id;
    @ApiModelProperty(value = "用户id")
    private int user_id;
    @ApiModelProperty(value = " 用户姓名")
    private String user_name;
    @ApiModelProperty(value = " 登陆时间 ")
    private Date login_time;

    public int getLogin_log_id() {
        return login_log_id;
    }

    public void setLogin_log_id(int login_log_id) {
        this.login_log_id = login_log_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }
}
