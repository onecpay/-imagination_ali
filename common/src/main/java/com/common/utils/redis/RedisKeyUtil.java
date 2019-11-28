package com.common.utils.redis;

import java.util.Calendar;

/**
 * 生成redis key util
 *
 * @author ONEC
 */
public class RedisKeyUtil {

    // 时间：1天
    public static final Integer SIZE_DAY = 86400;
    // 服务：wechat
    public static final String SERVICE_NAME_WECHAT = "wechat";
    // 服务：wechat
    public static final String SERVICE_NAME_PRODUCT = "product";

    /**
     * @param code 服务
     * @param code 表民
     * @param code 唯一标识
     * @return
     */
    public static String getKey(String service, String dataTable, String code) {
        StringBuilder builder = new StringBuilder();
        builder.append(service).append(":");
        builder.append(dataTable).append(":");
        builder.append(code);
        return builder.toString();
    }


    /**
     * 获取redis 过期时间。
     * 凌晨12点。
     *
     * @return
     */
    public static Long getExiperTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }
}
