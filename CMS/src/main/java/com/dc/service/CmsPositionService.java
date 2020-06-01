package com.dc.service;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsCompany;
import com.dc.pojo.CmsPosition;
import com.dc.pojo.CmsPositionVo;
import com.dc.pojo.CmsUser;

public interface CmsPositionService {
    //新增
    BaseModel insertPosition(CmsPositionVo cmsPositionVo);
    //支付
    BaseModel selectpayment(CmsPositionVo cmsPositionVo);
    BaseModel payment(CmsPositionVo cmsPositionVo);
    BaseModel addpayment(CmsCompany cmsCompany);
    BaseModel Number();
    BaseModel addTotalsum(CmsCompany cmsCompany);
    BaseModel Flowingwater(CmsPositionVo cmsPositionVo);
    BaseModel StatisticsPosition(CmsPositionVo cmsPositionVo);
    //修改
    BaseModel updatePosition(CmsPosition cmsPosition);
    //删除
    BaseModel deletePosition( String ids, BaseModel baseModel);
    //根据id查询用用户/所有
    BaseModel selectPositionAll(Integer userId,Integer position_id);
    //分页查找用户发布的招聘信息（包含条件搜索）
    BaseModel selectPositionAllByPage(BaseModel baseModel, Integer page,Integer maxSize, String likeName, String likeName1);
    //分页查找单个用户发布的招聘信息（包含条件搜索）
    BaseModel selectPositionIdAllByPage(BaseModel baseModel, Integer page,Integer maxSize, Integer userId);
    BaseModel selectPositionstateAllByPage(BaseModel baseModel, Integer page,Integer maxSize, String likeName);
}
