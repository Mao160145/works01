package com.dc.service;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsInformation;

public interface CmsInformationService {
    //新增简历
    BaseModel  insertInformation(CmsInformation cmsInformation);
   //修改简历信息
   BaseModel updateInformation(CmsInformation cmsInformation);
    //删除
   BaseModel deleteInformation( String ids, BaseModel baseModel);
    //根据id查询用用户/所有
    BaseModel selectInformation(Integer userId);

}
