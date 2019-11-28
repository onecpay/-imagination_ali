package com.example.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.common.exception.BaseException;
import com.common.uid.UidGenerator;
import com.common.utils.DateUtils;
import com.common.utils.StringUtils;
import com.example.wechat.client.product.ProductClient;
import com.example.wechat.dao.customer.CustomerExtraMapper;
import com.example.wechat.dao.master.CustomerMapper;
import com.example.wechat.dao.master.CustomerPermissionMapper;
import com.example.wechat.dao.master.PermissionMapper;
import com.example.wechat.dto.request.CustomerDto;
import com.example.wechat.entity.customer.CustomerExtra;
import com.example.wechat.entity.security.Customer;
import com.example.wechat.entity.security.CustomerPermission;
import com.example.wechat.entity.security.Permission;
import com.example.wechat.enums.StatusEnum;
import com.example.wechat.exception.CustomerException;
import com.example.wechat.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author ONEC
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    UidGenerator uidGenerator;

    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private CustomerPermissionMapper customerPermissionMapper;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private CustomerExtraMapper customerExtraMapper;

    @Resource
    private ProductClient productClient;

    @Override
    public Customer saveCustomer(CustomerDto customerDto) {

        RequestData requestData = new RequestData();
        requestData.setBizContent(JSONObject.toJSONString(customerDto));
        ResponseData responseData = checkSmsCode(requestData);
        if (10000 != responseData.getSubCode()) {
            throw new CustomerException(20001, JSONObject.toJSONString(responseData.getData()));
        }
        if (StringUtils.isAnyEmpty(customerDto.getPhone(), customerDto.getPassword())) {
            throw new CustomerException(20001, "注册参数不完整");
        }
        Optional<Customer> optional = Optional.ofNullable(customerMapper.findCustomerPhone(customerDto.getPhone()));
        if (optional.isPresent()) {
            throw new CustomerException(20002, "手机号已经注册");
        }

        Customer customer = new Customer();
        customer.setVersion(1);
        customer.setPhone(customerDto.getPhone());
        customer.setStatus(StatusEnum.AVAILABLE);

        String password = bCryptPasswordEncoder.encode(customerDto.getPassword());
        customer.setPassword(password);

        customer.setCreateTime(DateUtils.getBusinsessDate());
        customer.setId(uidGenerator.getUid());
        customer.setUpdateTime(DateUtils.getBusinsessDate());
        customerMapper.insert(customer);
        log.info("用户信息添加完成：{}", customerDto.getPhone());

        // 赋值权限:所有：也可以赋值少许权限
        List<Permission> permissions = permissionMapper.selectAll();
        for (Permission permission : permissions) {
            CustomerPermission customerPermission = new CustomerPermission(uidGenerator.getUid(), customer.getId(), permission.getId(), "客户配置权限");
            customerPermissionMapper.insert(customerPermission);
        }
        log.info("用户权限赋值成功：{}", customerDto.getPhone());
        return customer;
    }

    /**
     * 用户信息补充.
     *
     * @param customerExtra
     * @return
     */
    @Override
    public CustomerExtra saveCustomerExtra(CustomerExtra customerExtra) {

        customerExtra.setCustomerUrl(JSONObject.toJSONString(customerExtra.getImageIds()));

        if (StringUtils.isAnyEmpty(customerExtra.getCardNo(), customerExtra.getPersonId(),
                customerExtra.getPersonName(), customerExtra.getCustomerUrl())) {
            throw new CustomerException(20001, "补充参数不完整");
        }

        customerExtra.setId(uidGenerator.getUid());
        customerExtra.setVersion(1);
        customerExtra.setCreateTime(DateUtils.getBusinsessDate());
        customerExtra.setUpdateTime(DateUtils.getBusinsessDate());
        customerExtraMapper.insert(customerExtra);
        return customerExtra;
    }

    @Override
    public CustomerExtra customerInfo(CustomerDto customerDto) {
        CustomerExtra customerExtra = customerExtraMapper.selectByPhone(customerDto.getPhone());
        if (null == customerExtra) {
            throw new BaseException(20002, "请补充信息");
        }
        return customerExtra;
    }


    /**
     * 用户数据获取短信验证码
     *
     * @param customerDto
     * @return
     */
    @Override
    public ResponseData requestCode(CustomerDto customerDto) {
        String bizData = JSONObject.toJSONString(customerDto);
        RequestData requestData = new RequestData();
        requestData.setBizContent(bizData);
        String result = productClient.requestCode(requestData);
        return JSONObject.parseObject(result, ResponseData.class);
    }


    /**
     * 验证短信
     *
     * @param requestData
     * @return
     */
    public ResponseData checkSmsCode(RequestData requestData) {
        // 验证短信验证码：
        String result = productClient.checkSmsCode(requestData);
        return JSONObject.parseObject(result, ResponseData.class);
    }
}
