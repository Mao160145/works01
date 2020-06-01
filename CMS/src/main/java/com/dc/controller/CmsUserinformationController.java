package com.dc.controller;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsPosition;
import com.dc.pojo.CmsUserinformation;
import com.dc.service.CmsUserinformationService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/formation")
public class CmsUserinformationController {
   @Autowired private CmsUserinformationService cmsUserinformationService;

    @ApiOperation(value = "新增应聘信息",notes = "登录",httpMethod = "POST")
    @RequestMapping("/Userinformation")
    @ResponseBody
    public BaseModel insertUserinformation(BaseModel baseModel,int user_id,String user_name,int position_id,String position_name,int company_id){
         CmsUserinformation cmsUserinformation=new CmsUserinformation();
         cmsUserinformation.setPosition_id(position_id);
         cmsUserinformation.setUser_name(user_name);
         cmsUserinformation.setUser_id(user_id);
         cmsUserinformation.setPosition_name(position_name);
         cmsUserinformation.setCompany_id(company_id);
        baseModel = cmsUserinformationService.insertUserinformation(cmsUserinformation);
        return baseModel;
    }


    @ApiOperation(value = "查找所有应聘信息/根据id查单个",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectUserinformation")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectUserinformation(@RequestParam(value = "userInformation_id", required = false) Integer userId) {
        BaseModel baseModel = new BaseModel();
        if (userId != null) {
            baseModel = cmsUserinformationService.selectUserinformation(userId);
        } else {
            baseModel = cmsUserinformationService.selectUserinformation(0);
        }
        return baseModel;
    }

    @ApiOperation(value = "根据公司id分页应聘信息",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectUserinformationAllByPage")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectUserinformationAllByPage(BaseModel baseModel,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "maxSize", defaultValue = "5") Integer maxSize,
                                               @RequestParam(value = "company_id", required = false) Integer userId) {
        return cmsUserinformationService.selectUserinformationAllByPage(baseModel, page, maxSize, userId);
    }

}
