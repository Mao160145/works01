package com.dc.service;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsUser;
import com.dc.pojo.Cmsmenu;

public interface CmsMenuServic {

    //新增
    BaseModel insertMenu(Cmsmenu cmsmenu);
    //修改
    BaseModel updateMenu(Cmsmenu cmsmenu);
    //删除
    BaseModel deleteMenu( String ids, BaseModel baseModel);
    //分页查找（包含条件搜索）一级分类
    BaseModel selectMenuAllByPage(BaseModel baseModel, Integer page,Integer maxSize, String likeName);
    //分页查找（包含条件搜索）二级分类
    BaseModel selectMenuAllByPage2(BaseModel baseModel, Integer page,Integer maxSize, Integer likeName2);
    //分页查找（包含条件搜索）三级分类
    BaseModel selectMenuAllByPage3(BaseModel baseModel, Integer page,Integer maxSize, Integer likeName3);

    BaseModel selectCmsmenu(Integer menu_id);

}
