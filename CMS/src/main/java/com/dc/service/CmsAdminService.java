package com.dc.service;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsAdmin;



public interface CmsAdminService {
    //登录
    BaseModel login(CmsAdmin cmsAdmin);
    //修改管理员
    BaseModel updateAdmin(CmsAdmin cmsAdmin);
    //id查询管理员
    BaseModel selectAdminAll(Integer adminId);
}
