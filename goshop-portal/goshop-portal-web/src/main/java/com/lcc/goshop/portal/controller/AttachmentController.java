package com.lcc.goshop.portal.controller;

import com.lcc.goshop.common.attachment.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lcc on 2017/2/14.
 */
@Controller
@RequestMapping("/att")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    public void download(String path,String inline,String name,HttpServletResponse response){
        attachmentService.download(path,name,response);
    }
}
