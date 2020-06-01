package com.dc.controller;


import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsCompany;
import com.dc.pojo.CmsInformation;
import com.dc.service.CmsCompanyService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/Company")
public class CmsCompanyController {
    @Autowired private CmsCompanyService cmsCompanyService;
    //取头像地址存放进数据库
    String pa;
    @ApiOperation(value = "公司信息修改",notes = "登录",httpMethod = "POST")
    @RequestMapping("/updateCompany")
    @ResponseBody
    public BaseModel updateCompany(CmsCompany cmsCompany) {
        cmsCompany.setCompany_logo(pa);
        BaseModel baseModel= cmsCompanyService.updateCompany(cmsCompany);
        return baseModel;
    }

    @RequestMapping("/upload")
    public String upload(HttpServletRequest req)throws Exception{
        MultipartHttpServletRequest mreq=(MultipartHttpServletRequest)req;
        //获取文件
        MultipartFile file=mreq.getFile("file");
        //获取文件名字
        String fileName=file.getOriginalFilename();
        //用时间来给重复的文件按时间来重命名
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        //获取文件的存放路径
        String path=req.getSession().getServletContext().getRealPath("/")+"upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.'));
        pa="/upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.'));
        //创建文件夹
        File f=new File(path);
        //判断文件夹是否创建
        if(!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        //创建文件流
        FileOutputStream fos=new FileOutputStream(path);
        //传入文件
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        return path;
    }

    @ApiOperation(value = "公司添加",notes = "登录",httpMethod = "POST")
    @RequestMapping("/insertCompany")
    @ResponseBody
    public BaseModel insertCompany(BaseModel baseModel, CmsCompany cmsCompany, HttpServletRequest request){
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
        cmsCompany.setCompany_logo(pa);
        baseModel = cmsCompanyService.insertCompany(cmsCompany);
        return baseModel;
    }
    @ApiOperation(value = "删除公司",notes = "登录",httpMethod = "POST")
    @RequestMapping("/deleteCompany")
    @ResponseBody
    public BaseModel deleteICompany(@RequestParam(value = "ids") String ids, BaseModel baseModel) {
        System.out.println(ids);
        baseModel = cmsCompanyService.deleteCompany(ids, baseModel);
        return baseModel;
    }
    @ApiOperation(value = "查询公司",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectCompany")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectCompany(@RequestParam(value = "user_id", required = false) Integer userId,@RequestParam(value = "company_id", required = false) Integer company_id) {

        BaseModel baseModel = new BaseModel();
        if (userId != null||company_id!=null) {
            baseModel = cmsCompanyService.selectCompany(userId,company_id);
        } else {
            baseModel = cmsCompanyService.selectCompany(0,0);
        }

        return baseModel;
    }

    @ApiOperation(value = "分页查询公司",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectCompanyAllByPage")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectUserAllByPage(BaseModel baseModel,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "maxSize", defaultValue = "10") Integer maxSize,
                                         @RequestParam(value = "likeName", required = false) String likeName,
                                           @RequestParam(value = "likeName1", required = false) String likeName1) {
        return cmsCompanyService.selectCompanyAllByPage(baseModel, page, maxSize, likeName,likeName1);
    }
}
