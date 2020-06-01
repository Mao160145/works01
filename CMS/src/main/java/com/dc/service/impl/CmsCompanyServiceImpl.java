package com.dc.service.impl;

import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsCompanyDao;
import com.dc.pojo.CmsCompany;
import com.dc.pojo.CmsInformation;
import com.dc.pojo.CmsUser;
import com.dc.service.CmsCompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsCompanyServiceImpl implements CmsCompanyService {
    @Autowired private CmsCompanyDao cmsCompanyDao;

    public BaseModel insertCompany(CmsCompany cmsCompany) {
        BaseModel baseModel=new BaseModel();
        int  result=cmsCompanyDao.insertCompany(cmsCompany);
        Integer user_id=cmsCompany.getUser_id();
        String company_name=cmsCompany.getCompany_name();
        if(result>0){
            cmsCompanyDao.statisticsCompany(cmsCompany);
            baseModel.setResultCode(0);
            baseModel.setMessage("公司添加成功");
            baseModel.setData(result);
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("公司添加失败");
            baseModel.setData(result);
        }
        return baseModel;
    }

    public BaseModel statisticsCompany(Integer user_id, String company_name) {
        return null;
    }

    public BaseModel updateCompany(CmsCompany cmsCompany) {
        BaseModel baseModel=new BaseModel();
        int result = cmsCompanyDao.updateCompany(cmsCompany);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("公司修改成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("公司修改失败");
        }
        return baseModel;

    }

    public BaseModel deleteCompany(String ids, BaseModel baseModel) {
        int result=cmsCompanyDao.deleteCompany(ids);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("删除公司成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除公司失败");
        }
        return baseModel;
    }

    public BaseModel selectCompany(Integer userId,Integer company_id) {
        BaseModel baseModel=new BaseModel();
        List<CmsCompany> users = cmsCompanyDao.selectCompany(userId,company_id);
        if (users.size()>0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("查询公司成功");
            baseModel.setData(users);
        } else if (users.size()==0){
            baseModel.setResultCode(-1);
            baseModel.setMessage("没有查询到公司");
            baseModel.setData(users);
        }else{
            baseModel.setResultCode(-2);
            baseModel.setMessage("未知错误");
            baseModel.setData(users);
        }

        return baseModel;
    }




    public BaseModel addpayment(int company_id, int company_balance) {
        BaseModel baseModel=new BaseModel();
        int  result=cmsCompanyDao.addpayment(company_id,company_balance);

        if(result>0){
            baseModel.setResultCode(0);
            baseModel.setMessage("余额充值成功");
            baseModel.setData(result);
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("余额充值失败");
            baseModel.setData(result);
        }
        return baseModel;
    }

    public BaseModel selectCompanyAllByPage(BaseModel baseModel, Integer page, Integer maxSize, String likeName, String likeName1) {
        PageHelper.startPage(page, maxSize);
        List<CmsCompany> users = cmsCompanyDao.selectCompanyAllByPage(likeName,likeName1);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("分页查询公司成功！");
        baseModel.setData(pageInfo);
        return baseModel;
    }
}
