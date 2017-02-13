package com.lcc.goshop.manager.controller;

import com.lcc.goshop.common.attachment.AttachmentService;
import com.lcc.goshop.commons.pojo.KindEditorMessage;
import com.lcc.goshop.commons.utils.JsonUtils;
import com.lcc.goshop.commons.utils.ResponseMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lcc on 2017/2/12.
 */
@Controller
@RequestMapping(value = "/ke")
public class KindEditorController {

    @Autowired
    AttachmentService attachmentService;

    public void upload(@RequestParam(value = "imgFile", required = false) MultipartFile[] files,
                       HttpServletRequest request, HttpServletResponse response) {
        KindEditorMessage message = new KindEditorMessage();
        for (MultipartFile file : files) {
            try {
                String path = attachmentService.upload(file);
                message.setError(0);
                message.setUrl(request.getContextPath() + "/att?path=" + path);
            } catch (Exception e) {
                message.setError(1);
                message.setUrl(e.getMessage());
            }
        }

        ResponseMessageUtils.textPlainResponse(response, JsonUtils.objectToJson(message));
    }
}
