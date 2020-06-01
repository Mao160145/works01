package com.dc.dao;

import com.dc.pojo.CmsStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//统计功能
public interface CmsStatisticsDao {
    //查询所有统计信息/查询单个统计信息
    List<CmsStatistics> selectStatisticsAll(@Param("Statistics_id") Integer statisticsId);
    //分页查询所有统计信息
    List<CmsStatistics> selectStatisticsAllByPage(@Param("likeName1") String likeName1);


}

