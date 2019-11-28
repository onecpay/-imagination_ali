package com.example.product.service.code.impl;

import com.common.exception.BaseException;
import com.common.utils.RandomStringGenerator;
import com.common.utils.redis.RedisKeyUtil;
import com.example.product.content.SmsCode;
import com.example.product.dto.SmsCodeDto;
import com.example.product.exception.ProductException;
import com.example.product.service.code.SmsCodeService;
import com.example.product.utils.KeyUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Duration;

/**
 * 实现个服务商的短信.
 *
 * @author ONEC
 */
@Slf4j
@Service
public class SmsCodeServiceImpl implements SmsCodeService {


//    @Autowired
////    private JedisCluster jedisCluster;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送腾讯短信.
     *
     * @param
     * @param
     * @return
     */
    @Override
    public boolean tencentSend(SmsCodeDto smsCodeDto) {
        if (StringUtils.isEmpty(smsCodeDto.getPhone())) {
            throw new BaseException(20001, "手机号有误");
        }
        Integer templateId = SmsCode.templateId.get(smsCodeDto.getType());
        if (null == templateId || 0 == templateId) {
            throw new BaseException(20001, "短信验证码类型有误");
        }

        String smsCode = this.createSmsCode(smsCodeDto.getType(), smsCodeDto.getPhone());
        String[] params = {smsCode};

        SmsSingleSender sender = new SmsSingleSender(SmsCode.APP_ID, SmsCode.APP_KEY);
        SmsSingleSenderResult result = null;
        try {
            result = sender.sendWithParam("86", smsCodeDto.getPhone(),
                    templateId, params, SmsCode.smsSign, "", "");
        } catch (HTTPException | IOException e) {
            e.printStackTrace();
            return false;
        }
        log.info("短信请求返回数据：{}", result);
        assert result != null;
        return 0 == result.result;
    }


    /**
     * 生成随机验证码。保存到redis中。
     *
     * @return
     */
    String createSmsCode(String type, String phone) {
        String smsCode = RandomStringGenerator.getRandomNumByLength(6);
        String redisKey = RedisKeyUtil.getKey(KeyUtil.CODE, type, phone);

        stringRedisTemplate.opsForValue().set(redisKey, smsCode, Duration.ofMinutes(5));
        log.info("生成的短信验证码为：{}", smsCode);
        return smsCode;
    }


    /**
     * 验证短信码的正确性.
     *
     * @param phone
     * @param smsCode
     * @return
     */
    @Override
    public boolean checkCode(String type, String phone, String smsCode) {

        String redisKey = RedisKeyUtil.getKey(KeyUtil.CODE, type, phone);
        String loginCode = stringRedisTemplate.opsForValue().get(redisKey);

        if (null == smsCode) {
            throw new ProductException(20001, "请填写短信验证码");
        }
        if (null == loginCode) {
            throw new ProductException(20001, "短信验证码已过期");
        }
        if (!StringUtils.equals(loginCode, smsCode)) {
            throw new ProductException(20001, "短信验证码有误");
        }
        //jedisCluster.del(redisKey);
        stringRedisTemplate.delete(redisKey);
        return true;
    }
}
