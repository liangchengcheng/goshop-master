package com.lcc.goshop.context;

import com.lcc.goshop.commons.utils.SerializeUtils;
import com.lcc.goshop.redis.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sun.xml.internal.fastinfoset.vocab.Vocabulary.PREFIX;

/**
 * Created by lcc on 2017/2/9.
 */
public class RedisLocaleChangeInterceptor implements HandlerInterceptor {

    public static final String DEFAULT_PARM_NAME = "locale";
    private String paramName = "locale";

    @Autowired
    JedisClient jedisClient;

    private String PREFIX = "LOCALE_REDIS_";

    public RedisLocaleChangeInterceptor(){}

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String newLocale = httpServletRequest.getParameter(this.paramName);
        if (newLocale == null){
            newLocale = this.getLocale(httpServletRequest);
        }

        if (StringUtils.hasText(newLocale)){
            this.setLocale(httpServletRequest,httpServletResponse,newLocale);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    private void setLocaleResolver(HttpServletRequest request, HttpServletResponse response,String locale){
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if(localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }

        localeResolver.setLocale(request, response, StringUtils.parseLocaleString(locale));
    }

    private String getLocale(HttpServletRequest request){
        byte[] k = SerializeUtils.serialize(PREFIX + request.getSession().getId());
        byte[] rawValue = jedisClient.get(k);
        return (String) SerializeUtils.deserialize(rawValue);
    }

    public void setLocale(HttpServletRequest request,HttpServletResponse response,String locale) {
        this.setLocaleResolver(request, response, locale);
        jedisClient.set(SerializeUtils.serialize(PREFIX + request.getSession().getId()), SerializeUtils.serialize(locale));
    }
}
