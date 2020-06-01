package com.dc.service.impl;

import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsPositionDao;
import com.dc.pojo.CmsCompany;
import com.dc.pojo.CmsPosition;
import com.dc.pojo.CmsPositionVo;
import com.dc.pojo.Number;
import com.dc.service.CmsPositionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CmsPositionServiceImpl implements CmsPositionService {
    @Autowired private CmsPositionDao cmsPositionDao;

    @Transactional
    public BaseModel insertPosition(CmsPositionVo cmsPositionVo) {
        BaseModel baseModel=new BaseModel();
            int result= cmsPositionDao.insertPositionr(cmsPositionVo);
            int re= cmsPositionDao.payment(cmsPositionVo);
        if(result>0&&re>0){
             cmsPositionDao.Flowingwater(cmsPositionVo);
             cmsPositionDao.StatisticsPosition(cmsPositionVo);
            baseModel.setResultCode(0);
            baseModel.setMessage("新增招聘信息成功,请耐心等待审核！");
            baseModel.setData(result);
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("新增招聘信息失败");
            baseModel.setData(result);
        }
        return baseModel;
    }



    public BaseModel updatePosition(CmsPosition cmsPosition) {
        BaseModel baseModel=new BaseModel();
        int result = cmsPositionDao.updatePosition(cmsPosition);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("招聘信息修改成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("招聘信息修改失败");
        }
        return baseModel;
    }

    public BaseModel deletePosition(String ids, BaseModel baseModel) {
        int result=cmsPositionDao.deletePosition(ids);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("删除招聘信息成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除招聘信息失败");
        }
        return baseModel;
    }


    public BaseModel selectpayment(CmsPositionVo cmsPositionVo) {
        BaseModel baseModel=new BaseModel();

        Integer company_id=cmsPositionVo.getCompany_id();
        Integer money=cmsPositionVo.getCompany_balance();
        CmsCompany userList= cmsPositionDao.selectpayment(company_id);
        int money1=Integer.parseInt(userList.getCompany_balance());
        if(money1 >= money){
            baseModel.setResultCode(0);
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("余额不足");
        }
        return baseModel;
    }

    public BaseModel payment(CmsPositionVo cmsPositionVo) {
        return null;
    }

    public BaseModel addpayment(CmsCompany cmsCompany) {
              BaseModel baseModel=new BaseModel();
              int result=cmsPositionDao.addpayment(cmsCompany);
        if (result > 0) {
            cmsPositionDao.addTotalsum(cmsCompany);
            baseModel.setResultCode(0);
            baseModel.setMessage("余额充值成功成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("余额充值成功失败");
        }
        return baseModel;
    }

    public BaseModel Number() {
      BaseModel baseModel=new BaseModel();
       List<Number> Num=cmsPositionDao.Number();
        baseModel.setResultCode(0);
        baseModel.setMessage("金额统计图查询成功");
        baseModel.setData(Num);
        return baseModel;
    }

    public BaseModel addTotalsum(CmsCompany cmsCompany) {
        return null;
    }

    public BaseModel Flowingwater(CmsPositionVo cmsPositionVo) {
        return null;
    }

    public BaseModel StatisticsPosition(CmsPositionVo cmsPositionVo) {
        return null;
    }

    public BaseModel selectPositionAll(Integer userId,Integer position_id) {
        BaseModel baseModel=new BaseModel();
        List<CmsPosition> users = cmsPositionDao.selectPositionAll(userId,position_id);
        if (users.size()>0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("查询招聘信息成功");
            baseModel.setData(users);
        } else if (users.size()==0){
            baseModel.setResultCode(-1);
            baseModel.setMessage("没有查询到招聘信息");
            baseModel.setData(users);
        }else{
            baseModel.setResultCode(-2);
            baseModel.setMessage("未知错误");
            baseModel.setData(users);
        }

        return baseModel;
    }

    public BaseModel selectPositionAllByPage(BaseModel baseModel, Integer page, Integer maxSize, String likeName, String likeName1) {
        PageHelper.startPage(page, maxSize);
        List<CmsPosition> users = cmsPositionDao.selectPositionAllByPage(likeName,likeName1);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("根据公司名或职位名分页查询招聘信息成功！");
        baseModel.setData(pageInfo);
        return baseModel;
    }
    public BaseModel selectPositionstateAllByPage(BaseModel baseModel, Integer page, Integer maxSize, String likeName) {
        PageHelper.startPage(page, maxSize);
        List<CmsPosition> users = cmsPositionDao.selectPositionstateAllByPage(likeName);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询本公司招聘信息！");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    public BaseModel selectPositionIdAllByPage(BaseModel baseModel, Integer page, Integer maxSize, Integer userId) {
        PageHelper.startPage(page, maxSize);
        List<CmsPosition> users = cmsPositionDao.selectPositionIdAllByPage(userId);
        PageInfo pageInfo = new PageInfo(users, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("根据id分页查询招聘信息成功！");
        baseModel.setData(pageInfo);
        return baseModel;

    }
}
