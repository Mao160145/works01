package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CmsUser {
    @ApiModelProperty(value = "用户id")
    private int user_id;
    @ApiModelProperty(value = "用户姓名")
    private String user_name;
    @ApiModelProperty(value = "用户账号")
    private String user_account;
    @ApiModelProperty(value = "用户密码")
    private String login_password;
    @ApiModelProperty(value = "手机号")
    private String user_phone;
    @ApiModelProperty(value = "注册时间")
    private Date user_regtime;
    @ApiModelProperty(value = "修改时间")
    private Date user_updetatime;
    @ApiModelProperty(value = "用户状态")
    private int user_status;
    @ApiModelProperty(value = "用户年龄")
    private int user_age;
    @ApiModelProperty(value = "用户性别")
    private int user_sex;
    @ApiModelProperty(value = "逻辑删除")
    private int is_deleted;
    @ApiModelProperty(value = "用户头像")
    private String user_url;
    @ApiModelProperty(value = "乐观锁")
    private  int version;
    @ApiModelProperty(value = "用户类型0是用户1是公司负责人")
     private  Integer style;

    public Integer getStyle() {
        return style;
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

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public Date getUser_regtime() {
        return user_regtime;
    }

    public void setUser_regtime(Date user_regtime) {
        this.user_regtime = user_regtime;
    }

    public Date getUser_updetatime() {
        return user_updetatime;
    }

    public void setUser_updetatime(Date user_updetatime) {
        this.user_updetatime = user_updetatime;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getUser_url() {
        return user_url;
    }

    public void setUser_url(String user_url) {
        this.user_url = user_url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CmsUser{");
        sb.append("user_id=").append(user_id);
        sb.append(", user_name='").append(user_name).append('\'');
        sb.append(", user_account='").append(user_account).append('\'');
        sb.append(", login_password='").append(login_password).append('\'');
        sb.append(", user_phone='").append(user_phone).append('\'');
        sb.append(", user_regtime=").append(user_regtime);
        sb.append(", user_updetatime=").append(user_updetatime);
        sb.append(", user_status=").append(user_status);
        sb.append(", user_age=").append(user_age);
        sb.append(", user_sex=").append(user_sex);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", user_url='").append(user_url).append('\'');
        sb.append(", version=").append(version);
        sb.append(", style=").append(style);
        sb.append('}');
        return sb.toString();
    }
}
