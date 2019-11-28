package com.example.wechat.entity.mongodb;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

/**
 * 文件处理实体，mongodb
 *
 * @author ONEC
 */
@Data
@Document(value = "bank_info")
public class BankInfo {

    /**
     * 文件id
     */
    @Id
    private String id;

    /**
     * 銀行簡稱
     */
    private String bankNo;
    /**
     * 文件名
     */
    private String bankName;
    /**
     * 文件创建时间
     */
    private Date createdTime;
    /**
     * 文件内容
     */
    private Binary content;

    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 文件大小
     */
    private long size;

}
