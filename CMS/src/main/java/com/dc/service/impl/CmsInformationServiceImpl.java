package com.dc.service.impl;

import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsInformationDao;
import com.dc.pojo.CmsInformation;
import com.dc.pojo.CmsUser;
import com.dc.service.CmsInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CmsInformationServiceImpl implements CmsInformationService {
    @Autowired private CmsInformationDao cmsInformationDao;

     public BaseModel insertInformation(CmsInformation cmsInformation){
         BaseModel baseModel=new BaseModel();
         int  result=cmsInformationDao.insertInformation(cmsInformation);
         if(result>0){
             baseModel.setResultCode(0);
             baseModel.setMessage("用户简历添加成功");
             baseModel.setData(result);
         }else{
             baseModel.setResultCode(-1);
             baseModel.setMessage("用户简历添加失败");
             baseModel.setData(result);
         }
        return baseModel;
    }

    public BaseModel updateInformation(CmsInformation cmsInformation) {

         BaseModel baseModel=new BaseModel();
        int result = cmsInformationDao.updateInformation(cmsInformation);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("修改简历成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("修改简历失败");
        }
        return baseModel;

    }

    public BaseModel deleteInformation(String ids, BaseModel baseModel) {
        int result=cmsInformationDao.deleteInformation(ids);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("删除简历成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除简历失败");
        }
        return baseModel;
    }

    public BaseModel selectInformation(Integer userId) {
        BaseModel baseModel=new BaseModel();
         List<CmsInformation> users = cmsInformationDao.selectInformation(userId);
        if (users.size()>0){
            baseModel.setResultCode(0);
            baseModel.setMessage("用户简历查询成功");
            baseModel.setData(users);
        }else if(users.size()==0){
            baseModel.setResultCode(-1);
            baseModel.setMessage("你还没有简历");
            baseModel.setData(users);
        }else{
            baseModel.setResultCode(-2);
            baseModel.setMessage("未知错误");
            baseModel.setData(users);
        }

        return baseModel;
    }


}
