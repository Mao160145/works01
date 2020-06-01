package com.dc.dao;

import com.dc.pojo.CmsUserinformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsUserinformationDao {

    int  insertUserinformation(CmsUserinformation cmsUserinformation);
    List<CmsUserinformation> selectUserinformation(@Param("userInformation_id") Integer userId);
    List<CmsUserinformation> selectUserinformationAllByPage(@Param("company_id") Integer userId);
}
