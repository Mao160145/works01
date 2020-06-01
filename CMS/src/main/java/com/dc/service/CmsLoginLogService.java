package com.dc.service;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsLoginLog;
/**
 * 登陆日志接口
 */
public interface CmsLoginLogService {
    //新增登录日志
    int insertLoginLog(BaseModel baseModel, CmsLoginLog cmsLoginLog);
    //分页查询登录日志
    void selectLoginLogPage(BaseModel baseModel, int page, int maxSize);
}
