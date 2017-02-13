package com.lcc.goshop.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lcc on 2017/2/12.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    /**
     * 打开首页
     */
    @RequestMapping
    public String showIndex() {
        return "framework";
    }

    @RequestMapping("index")
    public String index() {
        return "home/index";
    }

    /**
     * 欢迎页面
     */
    @RequestMapping("welcome")
    public String welcome() {
        return "home/welcome";
    }
}
