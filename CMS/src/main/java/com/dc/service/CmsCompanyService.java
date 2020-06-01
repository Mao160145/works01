package com.dc.service;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsCompany;


public interface CmsCompanyService {
    //新增公司
    BaseModel insertCompany(CmsCompany cmsCompany);
    BaseModel statisticsCompany(Integer user_id,String company_name);
    //修改公司信息
    BaseModel updateCompany(CmsCompany cmsCompany);
    //删除
    BaseModel deleteCompany( String ids, BaseModel baseModel);
    //根据用户id查询用公司/所有
    BaseModel selectCompany(Integer userId,Integer company_id);
    //充值余额
    BaseModel addpayment(int company_id ,int company_balance);
    //分页查询公司
    BaseModel selectCompanyAllByPage(BaseModel baseModel, Integer page,
                                  Integer maxSize, String likeName,String likeName1);
}
