package com.dc.dao;

import com.dc.pojo.CmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CmsUserDao {
    //登录
    CmsUser login(String account);
    //用户新增
    int insertUser(CmsUser cmsUser);
    //修改用户
    int updateUser(CmsUser cmsUser);
    //删除用户
    int deleteUsers(@Param("ids") String ids);

    //查询所有用户/查询单个用户
    List<CmsUser> selectUserAll(@Param("user_id") Integer userId);

    //分页查询所有用户
    List<CmsUser> selectUserAllByPage(@Param("likeName") String likeName,@Param("likeName1") String likeName1);

}
