package com.dc.dao;

import com.dc.pojo.CmsAdmin;
import com.dc.pojo.CmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CmsAdminDao {
    //登录
    CmsAdmin login(String account);
    //修改管理员
    int updateAdmin(CmsAdmin cmsAdmin);
    //查询管理员
    List<CmsAdmin> selectAdminAll(@Param("admin_id") Integer adminId);
}
