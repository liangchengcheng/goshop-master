package com.lcc.goshop.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lcc on 2017/2/18.
 */
@Controller
@RequestMapping
public class HomeController {
    /**
     * 打开首页
     */
    @RequestMapping(value =  "/home")
    public String showIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "home/index";
    }


}
