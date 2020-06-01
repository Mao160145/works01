package com.dc.service;

import com.dc.base.vo.BaseModel;

public interface CmsStatisticsService {
    //根据id查询用户/所有
    BaseModel selectStatisticsAll(Integer statisticsId);

    //分页查询登录日志
    BaseModel selectStatisticsAllByPage(BaseModel baseModel, Integer page, Integer maxSize,String likeName1);
}
