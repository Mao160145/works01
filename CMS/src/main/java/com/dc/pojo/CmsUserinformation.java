package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CmsUserinformation {
    @ApiModelProperty(value = "招聘简历中间表id")
    private int userInformation_id;
    @ApiModelProperty(value = "应聘职位")
    private String position_name;
    @ApiModelProperty(value = "应聘人id")
    private int user_id;
    @ApiModelProperty(value = "应聘人姓名")
    private String user_name;
    @ApiModelProperty(value = "招聘表id")
    private int position_id;
    @ApiModelProperty(value = "公司id")
    private int company_id;

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getUserInformation_id() {
        return userInformation_id;
    }

    public void setUserInformation_id(int userInformation_id) {
        this.userInformation_id = userInformation_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
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

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CmsUserinformation{");
        sb.append("userInformation_id=").append(userInformation_id);
        sb.append(", position_name='").append(position_name).append('\'');
        sb.append(", user_id=").append(user_id);
        sb.append(", user_name='").append(user_name).append('\'');
        sb.append(", position_id=").append(position_id);
        sb.append(", company_id=").append(company_id);
        sb.append('}');
        return sb.toString();
    }
}
