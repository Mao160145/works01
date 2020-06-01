package com.dc.controller;

import com.dc.base.vo.BaseModel;
import com.dc.service.CmsStatisticsService;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//标注控制层
@RequestMapping("/Statistics")
public class CmsStatisticsController {
    //依赖注入Service层
    @Autowired
    private CmsStatisticsService cmsStatisticsService;

    /**
     * 查询所有用户(或查询单个用户)
     *
     * @return
     */
    @ApiOperation(value = "查找所有统计信息/根据id查单个",notes = "统计",httpMethod = "POST")
    @RequestMapping("/selectStatisticsAll")
    @ResponseBody()//转换成json或者xml数据
    public BaseModel selectStatisticsAll(@RequestParam(value = "Statistics_id", required = false)Integer statisticsId){
        BaseModel baseModel = new BaseModel();
        if (statisticsId!=null){
            baseModel= cmsStatisticsService.selectStatisticsAll(statisticsId);
        } else {
        baseModel = cmsStatisticsService.selectStatisticsAll(0);
    }
    return baseModel;
    }
    /**
     * 分页查询所有用户
     */
    @ApiOperation(value = "分页查询统计信息",notes = "统计",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "maxSize",value = "一页最大条数", dataType = "Integer", defaultValue = "10")
    })
    @RequestMapping("/selectStatisticsAllByPage")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectStatisticsAllByPage(BaseModel baseModel,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "maxSize", defaultValue = "10") Integer maxSize,
                                               @RequestParam(value = "likeName1", required = false)String likeName1){
        return cmsStatisticsService.selectStatisticsAllByPage(baseModel,page,maxSize,likeName1);
    }

}
