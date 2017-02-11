package com.lcc.goshop.shiro.service;

import com.lcc.goshop.context.ValidationCodeServlet;
import com.lcc.goshop.manager.i.UserService;
import com.lcc.goshop.manager.pojo.User;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lcc on 2017/2/11.
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    UserService userService;

    @Autowired
    ValidationCodeServlet validationCodeServlet;

    //原FormAuthenticationFilter的认证方法
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        //在这里进行验证码的校验
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!validationCodeServlet.isCaptcha(httpServletRequest)){
            //要是校验失败,讲验证码错误失败信息通过shiro LoginFailure 设置到request
            httpServletRequest.setAttribute(ShiroConfig.shiroLoginFailure,"randomCodeError");
            //拒绝访问,不再校验账号和密码
            return true;
        }
        return super.onAccessDenied(request,response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        User user = (User) subject.getPrincipal();
        String ip = request.getRemoteAddr();
        userService.updateLoginInfo(user,ip);

        //删除上次的链接
        Session session = subject.getSession();
        session.removeAttribute("shiroSavedRequest");
        this.issueSuccessRedirect(request,response);
        return false;
    }
}
