package com.example.manage.controller.file;

import com.common.utils.DateUtils;
import com.example.manage.dto.ResponseLayui;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author ONEC
 */
@Slf4j
@RestController
@SessionAttributes("status")
@RequestMapping(value = "upload")
public class UploadVideoController {

    @RequestMapping("/video")
    public ResponseLayui upLoad(HttpServletRequest request) throws IllegalStateException, IOException {
        log.info("开始上传视频文件：{}", DateUtils.getBusinsessDate());
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            String localPath = "/home/admin";
            File path = new File(localPath);
            if (!path.exists()) {
                path.mkdir();
            }
            int i = 1;
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String filepath = localPath + File.separator + file.getOriginalFilename();
                    file.transferTo(new File(filepath));
                    log.info("文件上传完成：{},上传数：{}", DateUtils.getBusinsessDate(), i);
                    i++;
                }
            }
        }
        return ResponseLayui.ok("uploadVideo", 1);
    }

}
