package com.lcc.goshop.manager.controller;

import com.lcc.goshop.manager.utils.Jump;
import com.lcc.goshop.shiro.service.ShiroConfig;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lcc on 2017/2/12.
 */
@Controller
public class LoginController {

    /**
     * 用户登录
     */
    public String login(Model model, HttpServletRequest request) {
        //要是登录失败从request中获取认证异常信息,shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute(ShiroConfig.shiroLoginFailure);
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if (StringUtils.hasText(exceptionClassName)) {
            String returnUrl = request.getContextPath() + "/login";
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                return Jump.get(returnUrl, "账号不存在!");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                return Jump.get(returnUrl, "用户名/密码错误!");
            } else if ("randomCodeError".equals(exceptionClassName)) {
                return Jump.get(returnUrl, "验证码错误!");
            } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
                return Jump.get(returnUrl, "账号已被锁定，请与系统管理员联系!");
            } else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
                return Jump.get(returnUrl, "您没有授权！");
            } else {
                return Jump.get(returnUrl, "未知异常！");
            }
        }

        return "login";
    }

    /**
     * 拒绝访问
     */
    @RequestMapping("refuse")
    public String refuse() {
        return "refuse";
    }
}
