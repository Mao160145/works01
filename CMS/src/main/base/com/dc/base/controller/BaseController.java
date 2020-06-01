package com.dc.base.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 创建基础的baseModel
 */
@Controller
@ResponseBody
public class BaseController {
    private static Logger log = Logger.getLogger(BaseController.class);
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @InitBinder
    public void initHttpParams(HttpServletRequest req, HttpServletResponse res) {
        request = req;
        response = res;
        session = req.getSession();
    }

    /**
     * @return void
     * @title:<h3> baseModel参数添加前缀 <h3>
     * @author: Enzo
     * @date: 2018-8-16 17:07
     * @params [binder]
     **/
    @InitBinder("baseModel")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("baseModel.");
    }

}
