package com.example.wechat.service.mongodb;

import com.common.dto.response.ResponseData;
import com.example.wechat.entity.mongodb.CustomerInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ONEC
 */
public interface UploadFileService {

    /**
     * 插入银行文件.
     *
     * @param file
     * @return ResponseData
     * @throws IOException 文件异常
     */
    ResponseData uploadBankInfo(MultipartFile file) throws IOException;


    /**
     * 插入y用户文件.
     *
     * @param file
     * @return ResponseData
     * @throws IOException 文件异常
     */
    ResponseData uploadCustomerInfo(MultipartFile file) throws IOException;


    CustomerInfo imageInfo(String id);

}
