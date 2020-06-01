package com.dc.service.impl;

import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsLoginLogDao;
import com.dc.pojo.CmsLoginLog;
import com.dc.service.CmsLoginLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 */
@Service
public class CmsLoginLogImpl implements CmsLoginLogService {
    @Autowired
    private CmsLoginLogDao loginLogDao;

    public int insertLoginLog(BaseModel baseModel, CmsLoginLog cmsLoginLog) {
        int count = loginLogDao.insertLoginLog(cmsLoginLog);
        if (count>0){
            baseModel.setResultCode(0);
            baseModel.setMessage("新增登录日志成功");
            baseModel.setData(0);
        }else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("新增登录日志失败");
            baseModel.setData(0);
        }
        return count;
    }
    /*
  * 分页查询所有登录日志
  * @param baseModel 返回值
  * @param page      当前页码
  * @param maxSize   当前页最大条数
 */
    public void selectLoginLogPage(BaseModel baseModel, int page, int maxSize) {

        PageHelper.startPage(page,maxSize);
        List<CmsLoginLog> loginLogList = loginLogDao.selectLoginLog();
        PageInfo pageInfo = new PageInfo(loginLogList);
        if (loginLogList !=null){
            baseModel.setResultCode(0);
            baseModel.setMessage("分页查询登录日志成功");
            baseModel.setData(pageInfo);
        }else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("分页查询登录日志失败");
            baseModel.setData(0);
        }
    }
}
