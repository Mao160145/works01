package com.dc.service;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsUser;

public interface CmsUserService {
    //登录
    BaseModel login(CmsUser cmsUser);
   //新增
   BaseModel insertUser(CmsUser cmsUser);
    //修改
    BaseModel updateUser(CmsUser cmsUser);
    //删除
    BaseModel deleteUsers( String ids, BaseModel baseModel);
    //根据id查询用用户/所有
    BaseModel selectUserAll(Integer userId);
    //分页查找用户（包含条件搜索）
    BaseModel selectUserAllByPage(BaseModel baseModel, Integer page,
                                  Integer maxSize, String likeName,String likeName1);
}
