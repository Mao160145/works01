package com.dc.service.impl;

import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsAdminDao;
import com.dc.pojo.CmsAdmin;
import com.dc.pojo.CmsUser;
import com.dc.service.CmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsAdminServiceImpl implements CmsAdminService {
   @Autowired private CmsAdminDao cmsAdminDao;

   public BaseModel login(CmsAdmin cmsAdmin) {
           CmsAdmin AdminList= cmsAdminDao.login(cmsAdmin.getAdmin_account());
       BaseModel baseModel=new BaseModel();
       String pass=cmsAdmin.getAdmin_password();
       if (AdminList!=null){
           if(AdminList.getAdmin_password().equals(pass)){
               baseModel.setResultCode(0);
               baseModel.setMessage("用户登录成功");
               baseModel.setData(AdminList);
           }else{
               baseModel.setResultCode(0);
               baseModel.setMessage("用户登录失败");
           }
       }else{
           baseModel.setResultCode(-1);
           baseModel.setMessage("账号不存在，请先去注册");
       }
       return baseModel;

    }

    public BaseModel updateAdmin(CmsAdmin cmsAdmin) {
        BaseModel baseModel=new BaseModel();
        int result = cmsAdminDao.updateAdmin(cmsAdmin);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("修改用户成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("修改用户失败");
        }
        return baseModel;
    }

    public BaseModel selectAdminAll(Integer adminId) {

            BaseModel baseModel= new BaseModel();
            List<CmsAdmin> admins = cmsAdminDao.selectAdminAll(adminId);

            baseModel.setResultCode(0);
            baseModel.setMessage("查询用户成功");
            baseModel.setData(admins);
            return baseModel;

    }

}
