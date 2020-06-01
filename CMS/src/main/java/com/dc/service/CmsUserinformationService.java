package com.dc.service;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsUserinformation;

public interface CmsUserinformationService {
    //新增应聘信息
    BaseModel insertUserinformation(CmsUserinformation cmsUserinformation);
    BaseModel selectUserinformation(Integer userId);
    BaseModel selectUserinformationAllByPage(BaseModel baseModel, Integer page,Integer maxSize, Integer userId);

}
