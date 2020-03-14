package com.microservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.avalonframework.core.beans.MessageConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * 视图
 *
 * @author 白超
 */
@RequestMapping("/view")
@Controller
public class ViewController {

    /**
     * 没有权限页面
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/noAuthority")
    public String noAuthority(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("message", MessageConstant.EXCEPTION_NO_AUTHENTICATION_MESSAGE);
        return "error/noAuthority";
    }

    /**
     * 登录
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws Exception {
//        request.setAttribute("loginUrl", ShiroConfig.LOGIN_POST_URL);
//        request.setAttribute("usernameParam", ShiroConfig.LOGIN_USER_NAME_PARAM);
//        request.setAttribute("passwordParam", ShiroConfig.LOGIN_PASS_WORD_PARAM);
        return "login/index";
    }

    /**
     * 首页
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/index")
    public String home(HttpServletRequest request, Model model) throws Exception {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        model.addAttribute("basePath", basePath);
        return "view/index";
    }

    /**
     * 模块管理
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/module")
    public String moduleManager(HttpServletRequest request, Model model) throws Exception {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        model.addAttribute("basePath", basePath);
        return "view/module";
    }

}
