package com.dc.service.impl;

import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsMenuDao;
import com.dc.pojo.CmsPosition;
import com.dc.pojo.CmsUser;
import com.dc.pojo.Cmsmenu;
import com.dc.service.CmsMenuServic;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsMenuServiceImpl implements CmsMenuServic {
@Autowired private CmsMenuDao cmsMenuDao;

    public BaseModel insertMenu(Cmsmenu cmsmenu) {
        BaseModel baseModel=new BaseModel();
        int  result=cmsMenuDao.insertMenu(cmsmenu);
        if(result>0){
            baseModel.setResultCode(0);
            baseModel.setMessage("新增分类成功！");
            baseModel.setData(result);
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("新增分类失败");
            baseModel.setData(result);
        }
        return baseModel;
    }


    public BaseModel updateMenu(Cmsmenu cmsmenu) {
        BaseModel baseModel=new BaseModel();
        int result = cmsMenuDao.updateMenu(cmsmenu);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("修改分类成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("修改分类失败");
        }
        return baseModel;
    }

    public BaseModel deleteMenu(String ids, BaseModel baseModel) {
        int result=cmsMenuDao.deleteMenu(ids);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("删除分类成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除分类失败");
        }
        return baseModel;
    }

    public BaseModel selectMenuAllByPage(BaseModel baseModel, Integer page, Integer maxSize, String likeName) {
        PageHelper.startPage(page, maxSize);
        List<Cmsmenu> users = cmsMenuDao.selectMenuAllByPage(likeName);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("一级分类分页成功！");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel selectMenuAllByPage2(BaseModel baseModel, Integer page, Integer maxSize, Integer likeName2) {
        PageHelper.startPage(page, maxSize);
        List<Cmsmenu> users = cmsMenuDao.selectMenuAllByPage2(likeName2);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("二级分类分页成功！");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel selectMenuAllByPage3(BaseModel baseModel, Integer page, Integer maxSize, Integer likeName3) {
        PageHelper.startPage(page, maxSize);
        List<Cmsmenu> users = cmsMenuDao.selectMenuAllByPage3(likeName3);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("三级分页成功！");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel selectCmsmenu(Integer menu_id) {
        BaseModel baseModel= new BaseModel();
        List<Cmsmenu> users =cmsMenuDao.selectCmsmenu(menu_id);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询分类成功");
        baseModel.setData(users);
        return baseModel;
    }
}
