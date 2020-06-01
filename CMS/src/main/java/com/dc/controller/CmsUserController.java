package com.dc.controller;


import com.dc.base.vo.BaseModel;
import com.dc.pojo.CmsUser;
import com.dc.service.CmsUserService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.net.www.content.image.png;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class CmsUserController {

    @Autowired
    private CmsUserService cmsUserService;


    @ApiOperation(value = "登录",notes = "登录",httpMethod = "POST")
    @RequestMapping("/login")
    @ResponseBody
    public BaseModel login(CmsUser user){
        BaseModel baseModel=cmsUserService.login(user);

        return baseModel;
    }

    String pa;
    @RequestMapping("/upload")
    public void upload(HttpServletRequest req)throws Exception{
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
    }

    @ApiOperation(value = "添加用户",notes = "登录",httpMethod = "POST")
    @RequestMapping("/insertUser")
    @ResponseBody
    public BaseModel insertUser(BaseModel baseModel, CmsUser cmsUser, HttpServletRequest request){
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
        cmsUser.setUser_url(pa);
        baseModel = cmsUserService.insertUser(cmsUser);
        return baseModel;
    }
    @ApiOperation(value = "修改用户",notes = "登录",httpMethod = "POST")
    @RequestMapping("/updateUser")
    @ResponseBody
    public BaseModel updateUser(CmsUser cmsUser) {

        cmsUser.setUser_url(pa);
        return cmsUserService.updateUser(cmsUser);
    }

    @ApiOperation(value = "删除用户",notes = "登录",httpMethod = "POST")
    @RequestMapping("/deleteUsers")
    @ResponseBody
    public BaseModel removeUser(@RequestParam(value = "ids") String ids, BaseModel baseModel) {
        System.out.println(ids);
        baseModel = cmsUserService.deleteUsers(ids, baseModel);
        return baseModel;
    }

    @ApiOperation(value = "查找所有用户/根据id查单个",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectUserAll")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectUserAll(@RequestParam(value = "user_id", required = false) Integer userId) {

        BaseModel baseModel = new BaseModel();
        if (userId != null) {
            baseModel = cmsUserService.selectUserAll(userId);
        } else {
            baseModel = cmsUserService.selectUserAll(0);
        }

        return baseModel;
    }

    @ApiOperation(value = "分页查询用户",notes = "登录",httpMethod = "POST")
    @RequestMapping("/selectUserAllByPage")
    @ResponseBody//转换成json或者xml数据
    public BaseModel selectUserAllByPage(BaseModel baseModel,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "maxSize", defaultValue = "10") Integer maxSize,
                                         @RequestParam(value = "likeName", required = false) String likeName,
                                         @RequestParam(value = "likeName1", required = false) String likeName1) {
        return cmsUserService.selectUserAllByPage(baseModel, page, maxSize, likeName,likeName1);
    }

}
