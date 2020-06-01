package com.dc.service.impl;


import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsUserinformationDao;
import com.dc.pojo.CmsPosition;
import com.dc.pojo.CmsUser;
import com.dc.pojo.CmsUserinformation;
import com.dc.service.CmsUserinformationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsUserinformationImpl implements CmsUserinformationService {
    @Autowired private CmsUserinformationDao cmsUserinformationDao;

    public BaseModel insertUserinformation(CmsUserinformation cmsUserinformation) {
        BaseModel baseModel=new BaseModel();
        int  result=cmsUserinformationDao.insertUserinformation(cmsUserinformation);
        if(result>0){
            baseModel.setResultCode(0);
            baseModel.setMessage("简历已发送到对应公司，耐心等待公司审核吧，祝你求职成功！");
            baseModel.setData(result);
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("应聘失败");
            baseModel.setData(result);
        }
        return baseModel;

    }

    public BaseModel selectUserinformation(Integer userId) {
        BaseModel baseModel= new BaseModel();
        List<CmsUserinformation> users = cmsUserinformationDao.selectUserinformation(userId);

        baseModel.setResultCode(0);
        baseModel.setMessage("查询简历成功");
        baseModel.setData(users);
        return baseModel;
    }

    public BaseModel selectUserinformationAllByPage(BaseModel baseModel, Integer page, Integer maxSize, Integer userId) {
        PageHelper.startPage(page, maxSize);
        List<CmsUserinformation> users = cmsUserinformationDao.selectUserinformationAllByPage(userId);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("分页查询应聘信息成功！");
        baseModel.setData(pageInfo);
        return baseModel;
    }
}
