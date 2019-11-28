package com.example.wechat.service.mongodb.impl;

import com.common.dto.response.ResponseData;
import com.common.exception.BaseException;
import com.common.utils.DateUtils;
import com.example.wechat.entity.mongodb.BankInfo;
import com.example.wechat.entity.mongodb.CustomerInfo;
import com.example.wechat.service.mongodb.UploadFileService;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * @author ONEC
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {


    @Resource
    MongoTemplate mongoTemplate;

    /**
     * 上传银行图片信息
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public ResponseData uploadBankInfo(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        BankInfo uploadFile = new BankInfo();
        uploadFile.setBankName(fileName);
        uploadFile.setCreatedTime(DateUtils.getBusinsessDate());
        uploadFile.setContent(new Binary(file.getBytes()));
        uploadFile.setContentType(file.getContentType());
        uploadFile.setSize(file.getSize());

        BankInfo bankInfo = mongoTemplate.save(uploadFile);

        return ResponseData.ok(bankInfo.getId());
    }

    @Override
    public ResponseData uploadCustomerInfo(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setImageName(fileName);
        customerInfo.setCreatedTime(DateUtils.getBusinsessDate());
        customerInfo.setContent(new Binary(file.getBytes()));
        customerInfo.setContentType(file.getContentType());
        customerInfo.setSize(file.getSize());
        CustomerInfo customerInfo1 = mongoTemplate.save(customerInfo);
        return ResponseData.ok(customerInfo1.getId());
    }

    @Override
    public CustomerInfo imageInfo(String id) {
        CustomerInfo customerInfo = mongoTemplate.findById(id, CustomerInfo.class);
        if (null == customerInfo) {
            throw new BaseException(20005, "暂未图片");
        }
        return customerInfo;
    }
}
