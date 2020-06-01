package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CmsInformation {
    @ApiModelProperty(value = "简历 id")
    private int resume_id;
    @ApiModelProperty(value = "用户id")
    private  int user_id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private int sex;
    @ApiModelProperty(value = "学历")
    private String education;
    @ApiModelProperty(value = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_birth;
    @ApiModelProperty(value = "婚姻状况")
    private String marital_status;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "emil")
    private String emil;
    @ApiModelProperty(value = "期望职业")
    private String expected_career;
    @ApiModelProperty(value = "简介")
    private String brief_introduction;
    @ApiModelProperty(value = "逻辑删除")
    private int is_deleted;

    public int getResume_id() {
        return resume_id;
    }

    public void setResume_id(int resume_id) {
        this.resume_id = resume_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }

    public String getExpected_career() {
        return expected_career;
    }

    public void setExpected_career(String expected_career) {
        this.expected_career = expected_career;
    }

    public String getBrief_introduction() {
        return brief_introduction;
    }

    public void setBrief_introduction(String brief_introduction) {
        this.brief_introduction = brief_introduction;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CmsInformation{");
        sb.append("resume_id=").append(resume_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", sex=").append(sex);
        sb.append(", education='").append(education).append('\'');
        sb.append(", date_birth=").append(date_birth);
        sb.append(", marital_status='").append(marital_status).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", emil='").append(emil).append('\'');
        sb.append(", expected_career='").append(expected_career).append('\'');
        sb.append(", brief_introduction='").append(brief_introduction).append('\'');
        sb.append(", is_deleted=").append(is_deleted);
        sb.append('}');
        return sb.toString();
    }
}
