package com.dc.controller;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsInformation;
import com.dc.pojo.CmsUser;
import com.dc.service.CmsInformationService;
import com.dc.service.CmsUserService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private CmsInformationService cmsInformationService;

    @ApiOperation(value = "简历修改",notes = "登录",httpMethod = "POST")
    @RequestMapping("/updateInformation")
    @ResponseBody
    public BaseModel updateUser(CmsInformation cmsInformation) {
       BaseModel baseModel= cmsInformationService.updateInformation(cmsInformation);
        return baseModel;
    }

    @ApiOperation(value = "新增简历",notes = "登录",httpMethod = "POST")
    @RequestMapping("/insertInformation")
    @ResponseBody
    public BaseModel insertInformation(BaseModel baseModel, CmsInformation cmsInformation){

        baseModel = cmsInformationService.insertInformation(cmsInformation);
        return baseModel;
    }
    @ApiOperation(value = "删除简历",notes = "登录",httpMethod = "POST")
    @RequestMapping("/deleteInformation")
    @ResponseBody
    public BaseModel deleteInformation(@RequestParam(value = "ids") String ids, BaseModel baseModel) {
        System.out.println(ids);
        baseModel = cmsInformationService.deleteInformation(ids, baseModel);
        return baseModel;
    }
    @ApiOperation(value = "查询简历",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectInformation")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectInformation(@RequestParam(value = "user_id", required = false) Integer userId) {

        BaseModel baseModel = new BaseModel();
        if (userId != null) {
            baseModel = cmsInformationService.selectInformation(userId);
        } else {
            baseModel = cmsInformationService.selectInformation(0);
        }

        return baseModel;
    }


}
