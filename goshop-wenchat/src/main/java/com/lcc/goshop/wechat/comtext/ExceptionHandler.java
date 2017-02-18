package com.lcc.goshop.wechat.comtext;

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
 * Created by lcc on 2017/2/18.
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    @Autowired
    MessageService messageService;

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        e.printStackTrace();
        if (e instanceof PageException || e instanceof IllegalArgumentException) {
            // 不是ajax请求
            if (!"XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
                MessageInfo info = new MessageInfo();
                info.setMessage(e.getMessage());
                messageService.set(request.getSession().getId(),info);
                ModelAndView mav = new ModelAndView();
                mav.setViewName("redirect:/msg");
                return mav;
            }else {
                //要是是ajax请求的话的返回
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                String result = null;
                String type = "";
                if (request.getParameterMap().containsKey("format")){
                    type = request.getParameter("format");
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
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }else {
                    ErrorMessage message = new ErrorMessage();
                    message.setCode(-1);
                    message.setMessage(e.getMessage());
                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        result = objectMapper.writeValueAsString(message);
                    } catch (JsonProcessingException ej) {
                        ej.printStackTrace();
                    }
                }

                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(result);
                    writer.flush();
                    writer.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }
}
