package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CmsAdmin {
    @ApiModelProperty(value = "管理员id")
    private int admin_id;
    @ApiModelProperty(value = "管理员账号")
    private String admin_account;
    @ApiModelProperty(value = "管理员密码")
    private String admin_password;
    @ApiModelProperty(value = "管理员姓名")
    private String admin_name;
    @ApiModelProperty(value = "管理员职称")
    private String admin_title;
    @ApiModelProperty(value = "管理员性别")
    private String admin_sex;
    @ApiModelProperty(value = "管理员电话")
    private String admin_phone;
    @ApiModelProperty(value = "出生年月")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date admin_birthday;
    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date admin_hiredate;
    @ApiModelProperty(value = "乐观锁")
    private int version;
    @ApiModelProperty(value = "管理员头像")
    private String admin_url;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_account() {
        return admin_account;
    }

    public void setAdmin_account(String admin_account) {
        this.admin_account = admin_account;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_title() {
        return admin_title;
    }

    public void setAdmin_title(String admin_title) {
        this.admin_title = admin_title;
    }

    public String getAdmin_sex() {
        return admin_sex;
    }

    public void setAdmin_sex(String admin_sex) {
        this.admin_sex = admin_sex;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
    }

    public Date getAdmin_birthday() {
        return admin_birthday;
    }

    public void setAdmin_birthday(Date admin_birthday) {
        this.admin_birthday = admin_birthday;
    }

    public Date getAdmin_hiredate() {
        return admin_hiredate;
    }

    public void setAdmin_hiredate(Date admin_hiredate) {
        this.admin_hiredate = admin_hiredate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getAdmin_url() {
        return admin_url;
    }

    public void setAdmin_url(String admin_url) {
        this.admin_url = admin_url;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CmsAdmin{");
        sb.append("admin_id=").append(admin_id);
        sb.append(", admin_account='").append(admin_account).append('\'');
        sb.append(", admin_password='").append(admin_password).append('\'');
        sb.append(", admin_name='").append(admin_name).append('\'');
        sb.append(", admin_title='").append(admin_title).append('\'');
        sb.append(", admin_sex='").append(admin_sex).append('\'');
        sb.append(", admin_phone='").append(admin_phone).append('\'');
        sb.append(", admin_birthday=").append(admin_birthday);
        sb.append(", admin_hiredate=").append(admin_hiredate);
        sb.append(", version=").append(version);
        sb.append(", admin_url='").append(admin_url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
