package com.example.wechat.dao.customer;

import com.example.wechat.entity.customer.CustomerExtra;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ONEC
 */
@Repository
public interface CustomerExtraMapper extends Mapper<CustomerExtra> {

    /**
     * 手机号获取数据.
     *
     * @param customerNo
     * @return
     */
    CustomerExtra selectByPhone(@Param("customerNo") String customerNo);

}