package com.dc.controller;

import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsAdmin;
import com.dc.pojo.CmsUser;
import com.dc.service.CmsAdminService;
import com.dc.service.CmsUserService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class CmsAdminController {

    @Autowired
    private CmsAdminService cmsAdminService;
    String pa;

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


    @ApiOperation(value = "管理员登录",notes = "登录",httpMethod = "POST")
    @RequestMapping("/login")
    @ResponseBody
    public BaseModel login(CmsAdmin admin){

        BaseModel baseModel=cmsAdminService.login(admin);
        return baseModel;
    }
    @ApiOperation(value = "管理员修改",notes = "登录",httpMethod = "POST")
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public BaseModel updateAdmin(CmsAdmin cmsAdmin) {
        cmsAdmin.setAdmin_url(pa);
        return cmsAdminService.updateAdmin(cmsAdmin);
    }

    @ApiOperation(value = "管理员查询登录/id差",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectAdminAll")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectAdminAll(@RequestParam(value = "admin_id", required = false) Integer adminId) {

        BaseModel baseModel = new BaseModel();
        if (adminId != null) {
            baseModel = cmsAdminService.selectAdminAll(adminId);
        } else {
            baseModel = cmsAdminService.selectAdminAll(0);
        }

        return baseModel;
    }

}
