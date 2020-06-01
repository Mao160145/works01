package com.dc.service.impl;

import com.dc.base.vo.BaseModel;
import com.dc.dao.CmsStatisticsDao;
import com.dc.pojo.CmsStatistics;
import com.dc.service.CmsStatisticsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsStatisticsServiceImpl implements CmsStatisticsService {
    @Autowired private CmsStatisticsDao cmsStatisticsDao;

    //查询根据id
    public BaseModel selectStatisticsAll(Integer statisticsId) {
        BaseModel baseModel= new BaseModel();
        List<CmsStatistics> statistics = cmsStatisticsDao.selectStatisticsAll(statisticsId);

        baseModel.setResultCode(0);
        baseModel.setMessage("查询统计信息成功");
        baseModel.setData(statistics);
        return baseModel;
    }

    //分页查询
    public BaseModel selectStatisticsAllByPage(BaseModel baseModel, Integer page, Integer maxSize,String likeName1) {
        PageHelper.startPage(page, maxSize);
        List<CmsStatistics> statistics = cmsStatisticsDao.selectStatisticsAllByPage(likeName1);
        PageInfo pageInfo = new PageInfo(statistics, 5);
        baseModel.setResultCode(0);
        baseModel.setMessage("分页查询统计信息成功！");
        baseModel.setData(pageInfo);
        return baseModel;
    }
}
