package com.dc.controller;



import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsCompany;
import com.dc.pojo.CmsPosition;
import com.dc.pojo.CmsPositionVo;
import com.dc.service.CmsPositionService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Position")
public class CmsPositionController {
    @Autowired
    private CmsPositionService cmsPositionService;

    @ApiOperation(value = "招聘信息修改",notes = "登录",httpMethod = "POST")
    @RequestMapping("/updatePosition")
    @ResponseBody
    public BaseModel updatePosition(CmsPosition cmsPosition) {
        BaseModel baseModel= cmsPositionService.updatePosition(cmsPosition);
        return baseModel;
    }
    @ApiOperation(value = "查询余额",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectpayment")
    @ResponseBody
    public BaseModel selectpayment(CmsPositionVo cmsPositionVo) {

        BaseModel baseModel1= cmsPositionService.selectpayment(cmsPositionVo);

        return baseModel1;
    }
    @ApiOperation(value = "余额充值",notes = "登录",httpMethod = "POST")
    @RequestMapping("/addpayment")
    @ResponseBody
    public BaseModel addpayment(CmsCompany cmsCompany) {

        BaseModel baseModel1= cmsPositionService.addpayment(cmsCompany);

        return baseModel1;
    }

    @ApiOperation(value = "新增招聘信息",notes = "登录",httpMethod = "POST")
    @RequestMapping("/insertPosition")
    @ResponseBody
    public BaseModel insertPosition(BaseModel baseModel, CmsPositionVo cmsPositionVo, HttpServletRequest request){
        //防止表单重复提交
        HttpSession session = request.getSession();
        String RToken = (String) session.getAttribute("token");
        String token = baseModel.getToken();
        if (token != null && token.equals(RToken)) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("请不要重复提交");
            return baseModel;
        }
        session.setAttribute("token",token);
        baseModel = cmsPositionService.insertPosition(cmsPositionVo);
        return baseModel;
    }


    @ApiOperation(value = "删除招聘信息",notes = "登录",httpMethod = "POST")
    @RequestMapping("/deletePosition")
    @ResponseBody
    public BaseModel deletePosition(@RequestParam(value = "ids") String ids, BaseModel baseModel) {
        System.out.println(ids);
        baseModel = cmsPositionService.deletePosition(ids, baseModel);
        return baseModel;
    }
    @ApiOperation(value = "查询招聘信息",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectPosition")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectPosition(@RequestParam(value = "user_id", required = false) Integer userId,@RequestParam(value = "position_id", required = false) Integer position_id) {

        BaseModel baseModel = new BaseModel();
        if (userId != null || position_id!=null) {
            baseModel = cmsPositionService.selectPositionAll(userId,position_id);
        } else {
            baseModel = cmsPositionService.selectPositionAll(0,0);
        }

        return baseModel;
    }

    @ApiOperation(value = "分页招聘信息",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectPositionAllByPage")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectPositionAllByPage(BaseModel baseModel,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "maxSize", defaultValue = "10") Integer maxSize,
                                         @RequestParam(value = "likeName", required = false) String likeName,
    @RequestParam(value = "likeName1", required = false) String likeName1) {
        return cmsPositionService.selectPositionAllByPage(baseModel, page, maxSize, likeName,likeName1);
    }
    @ApiOperation(value = "每天金额统计",notes = "登录",httpMethod = "POST")
    @RequestMapping("/Number")
    @ResponseBody//转换成json或者xml数据
    public BaseModel Number() {
        return cmsPositionService.Number();
    }
    @ApiOperation(value = "按审核状态分页招聘信息",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectPositionstateAllByPage")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectPositionstateAllByPage(BaseModel baseModel,
                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "maxSize", defaultValue = "3") Integer maxSize,
                                             @RequestParam(value = "likeName", required = false) String likeName) {
        return cmsPositionService.selectPositionstateAllByPage(baseModel, page, maxSize, likeName);
    }


    @ApiOperation(value = "根据id分页招聘信息",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectPositionIdAllByPage")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectPositionIdAllByPage(BaseModel baseModel,
                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "maxSize", defaultValue = "10") Integer maxSize,
                                               @RequestParam(value = "user_id", required = false) Integer userId) {
        return cmsPositionService.selectPositionIdAllByPage(baseModel, page, maxSize, userId);
    }

}
