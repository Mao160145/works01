package com.dc.dao;

import com.dc.pojo.CmsLoginLog;

import java.util.List;

/**
 * 登录日志
 */
public interface CmsLoginLogDao {
   //新增登录日志
    int insertLoginLog(CmsLoginLog loginLog);
   //查询登录日志
    List<CmsLoginLog> selectLoginLog();
}
