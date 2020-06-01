package com.dc.test;

import com.dc.base.vo.BaseModel;
import com.dc.dao.*;
import com.dc.pojo.*;
import com.dc.pojo.Number;
import com.dc.service.CmsCompanyService;
import com.dc.service.CmsUserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;



/**
 * Created  by  dcf on 2018/11/14.<br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-mybatis.xml"})
public class TestDemo {
    //打印错误日志  org.apache.log4j.Logger
    private Logger logger = Logger.getLogger(TestDemo.class);

    //依赖注入Service层
    @Autowired(required = false) private CmsUserService cmsUserService;
    @Autowired private CmsUserDao cmsUserDao;
    @Autowired private CmsAdminDao cmsAdminDao;
    @Autowired private CmsInformationDao cmsInformationDao;
    @Autowired private CmsCompanyDao cmsCompanyDao;
    @Autowired private CmsPositionDao cmsPositionDao;
    /**
     * 替代controller，测试
     */

  /*  @Test
    public void insertUser(){
        CmsUser user = new CmsUser();
        user.setUser_account("admin3");
        user.setLogin_password("333");
        user.setUser_name("张3");
        user.setStyle(0);
        int result = cmsUserDao.insertUser(user);
        System.out.println(result);
    }

    @Test
    public void updateUser(){
        CmsUser user = new CmsUser();
        user.setUser_account("123");
        user.setLogin_password("123");
        user.setUser_name("张三");
        user.setUser_id(9);
        user.setVersion(0);
        int result = cmsUserDao.updateUser(user);
        System.out.println(result);
    }

    @Test
    public void updateAdmin(){
        CmsAdmin Admin = new CmsAdmin();
        Admin.setAdmin_account("123");
        Admin.setAdmin_password("123");
        Admin.setAdmin_name("张三");
        Admin.setAdmin_id(1);
        Admin.setVersion(0);
        int result = cmsAdminDao.updateAdmin(Admin);
        System.out.println(result);
    }
    @Test
    public void deleteUser(){
        String ids = "10,11";
        int result = cmsUserDao.deleteUsers(ids);
        System.out.println(result);
    }
    @Test
    public void selectUser(){
        List<CmsUser> users=cmsUserDao.selectUserAll(1);
        BaseModel baseModel =new BaseModel();
        baseModel.setResultCode(0);
        baseModel.setMessage("用户列表查询成功");
        baseModel.setData(users);
        //系统打印
        System.out.println(baseModel);
        //日志打印
        logger.info(baseModel);
    }

    @Test
    public void Information(){
        CmsInformation user = new CmsInformation();
        user.setName("admin3");
        user.setEmil("333");
        user.setUser_id(3);
        int result = cmsInformationDao.insertInformation(user);
        System.out.println(result);
    }

    @Test
    public void updateInformation(){
        CmsInformation user = new CmsInformation();
        user.setName("admin5");
        user.setEmil("55");
        user.setUser_id(3);
        int result = cmsInformationDao.updateInformation(user);
        System.out.println(result);
    }
    @Test
    public void deleteInformation(){
        String ids = "3";
        int result = cmsInformationDao.deleteInformation(ids);
        System.out.println(result);
    }
   */
    @Test
    public void selectInformation(){
        List<CmsInformation> users=cmsInformationDao.selectInformation(0);
        BaseModel baseModel =new BaseModel();
        baseModel.setResultCode(0);
        baseModel.setMessage("用户列表查询成功");
        baseModel.setData(users);
        //系统打印
        System.out.println(baseModel);
        //日志打印
        logger.info(baseModel);
    }
    @Test
    public void InCompany(){
        CmsCompany user = new CmsCompany();
        user.setCompany_name("阿里");
        user.setCompany_address("杭州");
        user.setUser_id(4);
        int result = cmsCompanyDao.insertCompany(user);
        System.out.println(result);
    }

    @Test
    public void updateCompany(){
        CmsCompany user = new CmsCompany();
        user.setCompany_name("百度");
        user.setCompany_address("杭州");
        user.setCompany_id(1);
        int result = cmsCompanyDao.updateCompany(user);
        System.out.println(result);
    }

    @Test
    public void selectCompany(){
        List<CmsCompany> users=cmsCompanyDao.selectCompany(0,0);
        BaseModel baseModel =new BaseModel();
        baseModel.setResultCode(0);
        baseModel.setMessage("用户列表查询成功");
        baseModel.setData(users);
        //系统打印
        System.out.println(baseModel);
        //日志打印
        logger.info(baseModel);
    }
    @Test
    public void deleteCompany(){
        String ids = "1";
        int result = cmsCompanyDao.deleteCompany(ids);
        System.out.println(result);
    }

   /* @Test
    public void selectPositionMapper(){
        List<CmsPosition> users=cmsPositionDao.selectPositionAll(0);
        BaseModel baseModel =new BaseModel();
        baseModel.setResultCode(0);
        baseModel.setMessage("用户列表查询成功");
        baseModel.setData(users);
        //系统打印
        System.out.println(baseModel);
        //日志打印
        logger.info(baseModel);
    }*/

 /*   @Test
    public void InPosition(){
        CmsPosition user = new CmsPosition();
        user.setPosition_name("gong");

        user.setUser_id(4);
        int result = cmsPositionDao.insertPositionr(user);
        System.out.println(result);
    }
*/
    @Test
    public void deletePosition(){

       List<Number>Number= cmsPositionDao.Number();
        System.out.println(Number);
    }
    /*  @Test
  public void d(){
        BaseModel baseModel=new BaseModel();

       cmsPositionDao.addpayment(6,"10");

        System.out.println(baseModel);
    }*/
}
