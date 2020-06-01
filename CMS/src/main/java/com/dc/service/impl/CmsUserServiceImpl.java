package com.dc.service.impl;


import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsLoginLogDao;
import com.dc.dao.CmsUserDao;
import com.dc.pojo.CmsLoginLog;
import com.dc.pojo.CmsUser;
import com.dc.service.CmsUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsUserServiceImpl implements CmsUserService {
    //登录
    @Autowired private CmsUserDao cmsUserDao;
   //登录日志
   @Autowired private CmsLoginLogDao cmsLoginLogDao;
    public BaseModel login(CmsUser cmsUser) {
        CmsUser userList= cmsUserDao.login(cmsUser.getUser_account());
        BaseModel baseModel=new BaseModel();
        CmsLoginLog loginLog=new CmsLoginLog();
        String pass=cmsUser.getLogin_password();
        if (userList!=null){
            if(userList.getLogin_password().equals(pass)){
                //添加登录日志
                loginLog.setUser_id(userList.getUser_id());
                loginLog.setUser_name(userList.getUser_name());
                cmsLoginLogDao.insertLoginLog(loginLog);

                baseModel.setResultCode(0);
                baseModel.setMessage("用户登录成功");
                baseModel.setData(userList);
            }else{
                baseModel.setResultCode(-1);
                baseModel.setMessage("用户密码错误");
            }
        }else{
            baseModel.setResultCode(-2);
            baseModel.setMessage("账号不存在，请先去注册");
        }
        return baseModel;
    }

    public BaseModel insertUser(CmsUser cmsUser) {
        BaseModel baseModel=new BaseModel();
        CmsUser userList= cmsUserDao.login(cmsUser.getUser_account());
        int result=0;
        if(userList==null){
         result=cmsUserDao.insertUser(cmsUser);
            if(result>0){
                baseModel.setResultCode(0);
                baseModel.setMessage("用户注册成功");
                baseModel.setData(userList);
            }else{
                baseModel.setResultCode(-1);
                baseModel.setMessage("用户注册失败");
                baseModel.setData(userList);
            }

        }else{
            baseModel.setResultCode(-2);
            baseModel.setMessage("账号已存在，不能重复，请更换账号！");
            baseModel.setData(userList);
        }
        return baseModel;
    }

    public BaseModel updateUser(CmsUser cmsUser) {
        BaseModel baseModel=new BaseModel();
        int result = cmsUserDao.updateUser(cmsUser);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("修改用户成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("修改用户失败");
        }
        return baseModel;
    }

    public BaseModel deleteUsers(String ids, BaseModel baseModel) {
          /*String[] userIds=ids.split(",");*/
          int result=cmsUserDao.deleteUsers(ids);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("删除用户成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除用户失败");
        }
        return baseModel;
    }

    public BaseModel selectUserAll(Integer userId) {
        BaseModel baseModel= new BaseModel();
        List<CmsUser> users = cmsUserDao.selectUserAll(userId);

            baseModel.setResultCode(0);
            baseModel.setMessage("查询用户成功");
            baseModel.setData(users);
        return baseModel;
    }

    public BaseModel selectUserAllByPage(BaseModel baseModel, Integer page, Integer maxSize, String likeName,String likeName1) {
        PageHelper.startPage(page, maxSize);
        List<CmsUser> users = cmsUserDao.selectUserAllByPage(likeName,likeName1);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("分页查询用户成功！");
        baseModel.setData(pageInfo);
        return baseModel;
    }


}
