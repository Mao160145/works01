package com.dc.pojo;

//统计实体类
public class CmsStatistics {
    private int Statistics_id;//统计id
    private int company_id;//公司id
    private String company_name;//公司名称
    private float Statistics_amount;//统计总金额
    private float Statistics_turnover;//营业额（金额流水）
    private int Statistics_recruit;//统计招聘信息总数
    private int is_deleted;//审核状态（逻辑删除）


    public int getStatistics_id() {
        return Statistics_id;
    }

    public void setStatistics_id(int statistics_id) {
        Statistics_id = statistics_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public float getStatistics_amount() {
        return Statistics_amount;
    }

    public void setStatistics_amount(float statistics_amount) {
        Statistics_amount = statistics_amount;
    }

    public float getStatistics_turnover() {
        return Statistics_turnover;
    }

    public void setStatistics_turnover(float statistics_turnover) {
        Statistics_turnover = statistics_turnover;
    }

    public int getStatistics_recruit() {
        return Statistics_recruit;
    }

    public void setStatistics_recruit(int statistics_recruit) {
        Statistics_recruit = statistics_recruit;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CmsStatistics{");
        sb.append("Statistics_id=").append(Statistics_id);
        sb.append(", company_id=").append(company_id);
        sb.append(", company_name='").append(company_name).append('\'');
        sb.append(", Statistics_amount=").append(Statistics_amount);
        sb.append(", Statistics_turnover=").append(Statistics_turnover);
        sb.append(", Statistics_recruit=").append(Statistics_recruit);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append('}');
        return sb.toString();
    }
}
