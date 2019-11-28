package com.example.loader.utils.lambda;

import java.time.*;

/**
 * java 8： 处理时间和日期：
 *
 * @author ONEC
 */
public class DateTime {


    public static void main(String[] s) {

        /**
         * 测试当前时间
         */
        localTime();
        //--------------时区方法测试----------------------------
        zonedTime();
    }

    /**
     * 处理localtime 时间
     */
    public static void localTime() {

        /**
         * 获取当前的时间
         */
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("获取到的当前日期时间：" + localDateTime);

        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println("localdate：" + localDate);

        // ----------------

        Month month = localDateTime.getMonth();
        Integer day = localDateTime.getDayOfMonth();
        Integer second = localDateTime.getSecond();
        System.out.println("年：" + month + "月" + day + "日" + second);


        // -------------------

        LocalDateTime date2 = localDateTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);


    }

    /**
     * 处理时区时间说明
     */
    public static void zonedTime() {

        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }

}
