package com.dc.controller;


import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsUser;
import com.dc.pojo.Cmsmenu;
import com.dc.service.CmsMenuServic;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Menu")
public class CmsMenuController {
    @Autowired
   private CmsMenuServic cmsMenuServic;
    @ApiOperation(value = "查询分类",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectCmsmenu")
    @ResponseBody
    public BaseModel selectCmsmenu(@Param("menu_id") Integer menu_id ){
     BaseModel baseModel=new BaseModel();
        if (menu_id != null) {
            baseModel =cmsMenuServic.selectCmsmenu(menu_id);
        } else {
            baseModel =cmsMenuServic.selectCmsmenu(null);
        }
        return baseModel;

    }

    @ApiOperation(value = "修改分类",notes = "登录",httpMethod = "POST")
    @RequestMapping("/updateCmsmenu")
    @ResponseBody
    public BaseModel updateCmsmenu(Cmsmenu cmsmenu) {

        return cmsMenuServic.updateMenu(cmsmenu);
    }

    @ApiOperation(value = "添加分类",notes = "登录",httpMethod = "POST")
    @RequestMapping("/insertCmsmenu")
    @ResponseBody
    public BaseModel insertCmsmenu(BaseModel baseModel, Cmsmenu cmsmenu){

        baseModel = cmsMenuServic.insertMenu(cmsmenu);
        return baseModel;
    }

    @ApiOperation(value = "删除分类",notes = "登录",httpMethod = "POST")
    @RequestMapping("/deleteCmsmenu")
    @ResponseBody
    public BaseModel removeCmsmenu(@RequestParam(value = "ids") String ids, BaseModel baseModel) {

        baseModel = cmsMenuServic.deleteMenu(ids, baseModel);
        return baseModel;
    }

    @ApiOperation(value = "分页查询一级分类",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectmenuAllByPage")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectmenuAllByPage(BaseModel baseModel,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "maxSize", defaultValue = "10") Integer maxSize,
                                         @RequestParam(value = "likeName", required = false) String likeName) {
        return cmsMenuServic.selectMenuAllByPage(baseModel, page, maxSize, likeName);
    }
    @ApiOperation(value = "分页查询二级分类",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectmenuAllByPage2")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectmenuAllByPage2(BaseModel baseModel,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "maxSize", defaultValue = "10") Integer maxSize,
                                         @RequestParam(value = "likeName2", required = false) Integer likeName2) {

        return cmsMenuServic.selectMenuAllByPage2(baseModel, page, maxSize, likeName2);
    }
    @ApiOperation(value = "分页查询三级分类",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectmenuAllByPage3")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectmenuAllByPage3(BaseModel baseModel,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "maxSize", defaultValue = "10") Integer maxSize,
                                         @RequestParam(value = "likeName3", required = false) Integer likeName3) {
        return cmsMenuServic.selectMenuAllByPage3(baseModel, page, maxSize, likeName3);
    }

}
