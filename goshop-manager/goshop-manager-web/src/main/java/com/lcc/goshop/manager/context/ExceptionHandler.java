package com.lcc.goshop.manager.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcc.goshop.commons.exception.AjaxException;
import com.lcc.goshop.commons.exception.GoShopException;
import com.lcc.goshop.commons.pojo.ErrorData;
import com.lcc.goshop.commons.pojo.ErrorMessage;
import com.lcc.goshop.context.MessageInfo;
import com.lcc.goshop.context.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常拦截器,用异常的统一拦截区分,ajax请求和普通的请求
 * Created by lcc on 2017/2/12.
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    @Autowired
    MessageService messageService;

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //控制台打印错误
        e.printStackTrace();
        if (e instanceof GoShopException || e instanceof IllegalArgumentException){
            if ("XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))
                    ||e instanceof AjaxException){
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");

                String result = null;
                String type = "";
                if (httpServletRequest.getParameterMap().containsKey("format")){
                    type = httpServletRequest.getParameter("format");
                }

                if (type.equals("xml")){
                    ErrorData error = new ErrorData();
                    ErrorMessage message = new ErrorMessage();
                    message.setCode(-1);
                    message.setMessage(e.getMessage());
                    error.getAction().add(message);

                    try{
                        JAXBContext context = JAXBContext.newInstance(ErrorData.class);

                        Marshaller marshaller = context.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

                        StringWriter writer = new StringWriter();
                        marshaller.marshal(error, writer);
                        result = writer.toString();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }else {
                    ErrorMessage message = new ErrorMessage();
                    message.setCode(-1);
                    message.setMessage(e.getMessage());
                    ObjectMapper objectMapper = new ObjectMapper();
                    try{
                        result = objectMapper.writeValueAsString(message);
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }

                try{
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.write(result);
                    writer.flush();
                    writer.close();
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }else {
                MessageInfo info = new MessageInfo();
                info.setMessage(e.getMessage());

                messageService.set(httpServletRequest.getSession().getId(),info);
                ModelAndView mav = new ModelAndView();
                mav.setViewName("redirect:/msg");
                return mav;
            }
        }
        return null;
    }
}
