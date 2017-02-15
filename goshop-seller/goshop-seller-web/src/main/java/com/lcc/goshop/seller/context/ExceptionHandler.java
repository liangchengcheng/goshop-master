package com.lcc.goshop.seller.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcc.goshop.commons.exception.PageException;
import com.lcc.goshop.commons.pojo.ErrorMessage;
import com.lcc.goshop.context.MessageInfo;
import com.lcc.goshop.context.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.lcc.goshop.commons.pojo.ErrorData;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by lcc on 2017/2/15.
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    @Autowired
    MessageService messageService;

    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        //控制台打印错误
        ex.printStackTrace();
        if (ex instanceof PageException ||ex instanceof IllegalArgumentException) {
            if (!"XMLHttpRequest".equalsIgnoreCase(request
                    .getHeader("X-Requested-With"))) {
                // 不是ajax请求
				/*RedirectAttributesEx rae=new RedirectAttributesEx(request);
				rae.setAttribute("ERROR_MESSAGE", message);*/
                MessageInfo info = new MessageInfo();
                info.setMessage(ex.getMessage());
                messageService.set(request.getSession().getId(),info);
                ModelAndView mav=new ModelAndView();
                mav.setViewName("redirect:/msg");
                return mav;
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                String result = null;
                String type = "";
                if (request.getParameterMap().containsKey("format")) {
                    type = request.getParameter("format");
                }
                if (type.equals("xml")) {
                    ErrorData error =new ErrorData();
                    ErrorMessage message = new ErrorMessage();
                    message.setCode(-1);
                    message.setMessage(ex.getMessage());
                    error.getAction().add(message);
                    try {
                        JAXBContext context = JAXBContext.newInstance(ErrorData.class);
                        Marshaller marshaller = context.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

                        StringWriter writer = new StringWriter();
                        marshaller.marshal(error, writer);
                        result = writer.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ErrorMessage message = new ErrorMessage();
                    message.setCode(-1);
                    message.setMessage(ex.getMessage());
                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        result = objectMapper.writeValueAsString(message);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(result);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
