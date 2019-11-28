package com.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * java 8： 日期处理及转换
 *
 * @author ONEC
 */
public class DateTimePattern {

    /**
     * java 8 时间日期转换
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static String receiveTime(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(dateTime);
    }


}
