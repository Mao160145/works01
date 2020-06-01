package com.dc.dao;

import com.dc.pojo.CmsCompany;
import com.dc.pojo.CmsPosition;
import com.dc.pojo.CmsPositionVo;
import com.dc.pojo.CmsUser;
import com.dc.pojo.Number;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsPositionDao {

    //招聘信息新增
    int insertPositionr(CmsPositionVo cmsPositionVo);
    //支付
    //查询余额
    CmsCompany selectpayment(Integer company_id);
    //支付
    int payment(CmsPositionVo cmsPositionVo);
    //充值
    int addpayment(CmsCompany cmsCompany);
    //统计总金额
    int addTotalsum(CmsCompany cmsCompany);
    //统计流水
    int Flowingwater(CmsPositionVo cmsPositionVo);
    //统计招聘信息
    int StatisticsPosition(CmsPositionVo cmsPositionVo);
    //修改招聘信息
    int updatePosition(CmsPosition cmsPosition);
    //删除招聘信息
    int deletePosition(@Param("ids") String ids);
     //招聘统计

    //查询所有招聘信息/查询单个招聘信息
    List<CmsPosition> selectPositionAll(@Param("user_id") Integer userId,@Param("position_id") Integer position_id);

    List<Number> Number();
    //分页查询所有招聘信息
    List<CmsPosition> selectPositionAllByPage(@Param("likeName") String likeName,@Param("likeName1") String likeName1);
    //根据id分页查询所有此id发布的招聘信息
    List<CmsPosition> selectPositionIdAllByPage(@Param("user_id") Integer userId);
    List<CmsPosition> selectPositionstateAllByPage(@Param("likeName") String likeName);

}
