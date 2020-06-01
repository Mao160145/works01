package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CmsPosition {
    @ApiModelProperty(value = "招聘id")
    private int position_id;
    @ApiModelProperty(value = "公司名字")
    private  String  company_name;
    @ApiModelProperty(value = "岗位名称")
    private  String  position_name;
    @ApiModelProperty(value = "岗位类型")
    private  String  position_type;
    @ApiModelProperty(value = "招聘人数")
    private  String  position_people;
    @ApiModelProperty(value = "招聘薪资")
    private  String  salary;
    @ApiModelProperty(value = "学历要求")
    private  String  education;
    @ApiModelProperty(value = "简介")
    private  String  position_introduce;
    @ApiModelProperty(value = "逻辑删除")
    private  String  is_deleted;
    @ApiModelProperty(value = "发布时间")
    private  String  release_time;
    @ApiModelProperty(value = "用户id")
    private  int  user_id;
    @ApiModelProperty(value = "审核状态")
    private  int  state;
    @ApiModelProperty(value = "工作地址")
    private  String  work_address;
    @ApiModelProperty(value = "公司id")
    private  int company_id;

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getWork_address() {
        return work_address;
    }

    public void setWork_address(String work_address) {
        this.work_address = work_address;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getPosition_type() {
        return position_type;
    }

    public void setPosition_type(String position_type) {
        this.position_type = position_type;
    }

    public String getPosition_people() {
        return position_people;
    }

    public void setPosition_people(String position_people) {
        this.position_people = position_people;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPosition_introduce() {
        return position_introduce;
    }

    public void setPosition_introduce(String position_introduce) {
        this.position_introduce = position_introduce;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CmsPosition{");
        sb.append("position_id=").append(position_id);
        sb.append(", company_name='").append(company_name).append('\'');
        sb.append(", position_name='").append(position_name).append('\'');
        sb.append(", position_type='").append(position_type).append('\'');
        sb.append(", position_people='").append(position_people).append('\'');
        sb.append(", salary='").append(salary).append('\'');
        sb.append(", education='").append(education).append('\'');
        sb.append(", position_introduce='").append(position_introduce).append('\'');
        sb.append(", is_deleted='").append(is_deleted).append('\'');
        sb.append(", release_time='").append(release_time).append('\'');
        sb.append(", user_id=").append(user_id);
        sb.append(", state=").append(state);
        sb.append(", work_address='").append(work_address).append('\'');
        sb.append(", company_id=").append(company_id);
        sb.append('}');
        return sb.toString();
    }
}
