package com.dc.controller;

import com.dc.base.vo.BaseModel;
import com.dc.service.CmsLoginLogService;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/Log")
public class CmsLoginLogController {
    @Autowired
    private CmsLoginLogService loginLogService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "maxSize",value = "一页最大条数", dataType = "Integer", defaultValue = "10")
    })
    @RequestMapping(value = "/selectLoginLogPage")
    @ResponseBody
    public BaseModel selectLoginLogPage(BaseModel baseModel, int page, int maxSize){
        loginLogService.selectLoginLogPage(baseModel,page,maxSize);
        return baseModel;
    }




}
