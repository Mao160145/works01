package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CmsCompany {
    @ApiModelProperty(value = "公司 id")
    private int company_id;//公司代号
    @ApiModelProperty(value = "用户id")
    private  int user_id;
    @ApiModelProperty(value = "公司姓名")
    private String company_name;
    @ApiModelProperty(value = "用户别名")
    private String company_alias;
    @ApiModelProperty(value = "公司地址")
    private String company_address;//公司地址
    @ApiModelProperty(value = "公司商标")
    private String company_logo ;//公司商标
    @ApiModelProperty(value = "公司类型")
    private String companyType_id;//公司类型
    @ApiModelProperty(value = "公司规模")
    private String company_scale;//公司规模
    @ApiModelProperty(value = "公司产业")
    private String industry;//公司产业
    @ApiModelProperty(value = "公司简介")
    private String company_introduce;//公司简介
    @ApiModelProperty(value = "公司余额")
    private String company_balance;//公司余额
    @ApiModelProperty(value = "逻辑删除")
    private int is_deleted;//逻辑删除

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_alias() {
        return company_alias;
    }

    public void setCompany_alias(String company_alias) {
        this.company_alias = company_alias;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getCompanyType_id() {
        return companyType_id;
    }

    public void setCompanyType_id(String companyType_id) {
        this.companyType_id = companyType_id;
    }

    public String getCompany_scale() {
        return company_scale;
    }

    public void setCompany_scale(String company_scale) {
        this.company_scale = company_scale;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompany_introduce() {
        return company_introduce;
    }

    public void setCompany_introduce(String company_introduce) {
        this.company_introduce = company_introduce;
    }

    public String getCompany_balance() {
        return company_balance;
    }

    public void setCompany_balance(String company_balance) {
        this.company_balance = company_balance;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CmsCompany{");
        sb.append("company_id=").append(company_id);
        sb.append(", user_id=").append(user_id);
        sb.append(", company_name='").append(company_name).append('\'');
        sb.append(", company_alias='").append(company_alias).append('\'');
        sb.append(", company_address='").append(company_address).append('\'');
        sb.append(", company_logo='").append(company_logo).append('\'');
        sb.append(", companyType_id='").append(companyType_id).append('\'');
        sb.append(", company_scale='").append(company_scale).append('\'');
        sb.append(", industry='").append(industry).append('\'');
        sb.append(", company_introduce='").append(company_introduce).append('\'');
        sb.append(", company_balance='").append(company_balance).append('\'');
        sb.append(", is_deleted=").append(is_deleted);
        sb.append('}');
        return sb.toString();
    }
}
