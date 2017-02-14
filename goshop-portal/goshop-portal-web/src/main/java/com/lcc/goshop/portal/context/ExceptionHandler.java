package com.lcc.goshop.portal.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcc.goshop.commons.exception.PageException;
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
 * Created by lcc on 2017/2/14.
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    @Autowired
    MessageService messageService;

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //控制台打印错误
        e.printStackTrace();
        if (e instanceof PageException || e instanceof IllegalArgumentException){
            if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))){
                MessageInfo info =new MessageInfo();
                info.setMessage(e.getMessage());
                messageService.set(httpServletRequest.getSession().getId(),info);;
                ModelAndView mav = new ModelAndView();
                mav.setViewName("redirect:/msg.html");
                return mav;
            }else {
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");
                String result = null;
                String type = "";
                //判断请求是否包含参数
                if (httpServletRequest.getParameterMap().containsKey("format")){
                    type = httpServletRequest.getParameter("format");
                }

                if (type.equals("xml")) {
                    ErrorData error =new ErrorData();
                    ErrorMessage message = new ErrorMessage();
                    message.setCode(-1);
                    message.setMessage(e.getMessage());
                    error.getAction().add(message);

                    try {
                        JAXBContext context = JAXBContext.newInstance(ErrorData.class);
                        Marshaller marshaller = context.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");

                        StringWriter writer = new StringWriter();
                        marshaller.marshal(error, writer);
                        result = writer.toString();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    ErrorMessage message = new ErrorMessage();
                    message.setCode(-1);
                    message.setMessage(e.getMessage());
                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        result = objectMapper.writeValueAsString(message);
                    } catch (JsonProcessingException ea) {
                        ea.printStackTrace();
                    }
                }

                try{
                    if (result != null){
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(result);
                        writer.flush();
                        writer.close();
                    }
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }
}
