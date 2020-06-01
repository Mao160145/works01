package com.dc.base.listener;

import com.dc.base.constants.ConstantsBase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

/**
 *
 * 监听器，获得webapp根目录
 */
public class WebappListener implements ServletContextListener {

    /**
     * 获取项目路径
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        // File.separator  /
        //获取项目路径
        String webAppRootKey = sce.getServletContext().getRealPath(File.separator);
        //系统设置属性   webapp.root（名）  webAppRootKey（值)
        System.setProperty(ConstantsBase.WEBAPP_ROOT, webAppRootKey + File.separator);
        //新建文件
        File file = new File(webAppRootKey);
        System.setProperty(ConstantsBase.PROJECT_NAME, file.getName());
        System.setProperty(ConstantsBase.WEBAPP_CLASSES, webAppRootKey + File.separator + "WEB-INF"
                + File.separator + "classes" + File.separator);
}

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

}
