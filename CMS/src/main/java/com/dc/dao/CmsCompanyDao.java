package com.dc.dao;

import com.dc.pojo.CmsCompany;
import com.dc.pojo.CmsInformation;
import com.dc.pojo.CmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CmsCompanyDao {
    //新增公司
    int  insertCompany(CmsCompany cmsCompany);
    int statisticsCompany(CmsCompany cmsCompany);
    //修改公司
    int updateCompany(CmsCompany cmsCompany);
    //删除
    int deleteCompany(@Param("ids") String ids);

     //充值余额
    int addpayment(int company_id ,int company_balance);
    //查询所有公司/查询单个公司
    List<CmsCompany> selectCompany(@Param("user_id") Integer userId,@Param("company_id") Integer company_id);

    //分页查询所有公司
    List<CmsCompany> selectCompanyAllByPage(@Param("likeName") String likeName,@Param("likeName1") String likeName1);



}
