package com.dc.dao;

import com.dc.pojo.CmsInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsInformationDao {
    //新增简历
     int  insertInformation(CmsInformation cmsInformation);
    //修改简历
    int updateInformation(CmsInformation cmsInformation);
    //删除
    int deleteInformation(@Param("ids") String ids);

    //查询所有用户/查询单个用户
    List<CmsInformation> selectInformation(@Param("user_id") Integer userId);
}
