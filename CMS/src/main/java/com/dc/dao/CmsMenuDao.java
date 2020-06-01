package com.dc.dao;


import com.dc.pojo.CmsUser;
import com.dc.pojo.Cmsmenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsMenuDao {
    //用户新增
    int insertMenu(Cmsmenu cmsmenu);
    //修改用户
    int updateMenu(Cmsmenu cmsmenu);
    //删除用户
    int deleteMenu(@Param("ids") String ids);

    //分页查询所一级分类
    List<Cmsmenu> selectMenuAllByPage(@Param("likeName") String likeName);
    //分页查询所二级分类
    List<Cmsmenu> selectMenuAllByPage2(@Param("likeName2") Integer likeName2);
    //分页查询所三级分类
    List<Cmsmenu> selectMenuAllByPage3(@Param("likeName3") Integer likeName3);
    //查询所有分类（单个）
    List<Cmsmenu> selectCmsmenu(@Param("menu_id") Integer menu_id);

}
