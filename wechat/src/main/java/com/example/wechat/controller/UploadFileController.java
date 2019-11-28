package com.example.wechat.controller;

import com.common.dto.response.ResponseData;
import com.example.wechat.entity.mongodb.CustomerInfo;
import com.example.wechat.service.mongodb.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 文件存储实现.
 *
 * @author ONEC
 */
@Slf4j
@RestController
@RequestMapping(value = "uploadFile")
public class UploadFileController {

    @Resource
    UploadFileService uploadFileService;

    /**
     * 单个图片上传bankinfo。
     *
     * @param file
     * @return
     */
    @PostMapping("/bankInfo")
    @ResponseBody
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public ResponseData bankInfo(@RequestParam(value = "bankInfo") MultipartFile file) {
        log.info("文件上传：");
        if (file.isEmpty()) {
            return ResponseData.error("请选择上传的图片");
        }
        try {
            return uploadFileService.uploadBankInfo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseData.error();
    }

    /**
     * 单个图片上传customerInfo。
     *
     * @param file
     * @return
     */
    @PostMapping("/customerInfo")
    @ResponseBody
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public ResponseData customerInfo(@RequestParam(value = "customerInfo") MultipartFile file) {
        log.info("文件上传：");
        if (file.isEmpty()) {
            return ResponseData.error("请选择上传的图片");
        }
        try {
            return uploadFileService.uploadCustomerInfo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseData.error();
    }

    /**
     * 获取单张图片.
     *
     * @param id
     * @return
     */
    @RequestMapping("/imageInfo/{id}")
    @ResponseBody
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    public byte[] imageInfo(@PathVariable("id") String id) {
        log.info("获取图片的id：{}", id);
        CustomerInfo image = uploadFileService.imageInfo(id);
        return image.getContent().getData();
    }
}
