package com.common.content;

/**
 * 正则表达式类
 *
 * @author ONEC
 */
public class PatternUtil {

    /**
     * 正则邮箱
     */
    public static final String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 正则手机
     */
    public static final String phone = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
}
