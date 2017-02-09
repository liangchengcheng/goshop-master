package com.lcc.goshop.common.attachment;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lcc on 2017/2/9.
 */
public interface AttachmentService {
    /**
     * 通过MultipartFile对象保存文件，返回文件名称
     */
    String upload(MultipartFile multipartFile) throws IOException;

    /**
     * 通过InputStream对象保存文件，返回文件名称
     */
    String upload(InputStream inputStream, String type) throws Exception;

    /**
     * 获取文件路径
     */
    String getPath();

    /**
     * 通过id获取文件
     */
    File download(String id);

    void download(String path, String fileName,HttpServletResponse response);

    String upload(File file,String type) throws Exception;

    void delete(String id);
}
