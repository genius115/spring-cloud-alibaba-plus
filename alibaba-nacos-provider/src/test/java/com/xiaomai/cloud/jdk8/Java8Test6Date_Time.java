package com.xiaomai.cloud.jdk8;

import java.time.*;
import java.util.concurrent.TimeUnit;

/**
 *  Jdk8 借鉴Joda-Time类库
 *
 *
 *  java.util.Date 是非线程安全的
 *  java.util和java.sql的包中都有日期类
 *  java.util.Calendar和java.util.TimeZone类   时区处理
 *
 * Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API： *
 *     Local(本地) − 简化了日期时间的处理，没有时区的问题。 LocalDate/LocalTime 和 LocalDateTime 类可以在处理时区不是必须的情况
 *     Zoned(时区) − 通过制定的时区处理日期时间。
 * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作
 *
 *  clock类
 *  Duration类
 *
 * @author Madison
 * @date 2021/1/17
 */
public class Java8Test6Date_Time {
    public static void main(String args[]) {
        Java8Test6Date_Time java8tester = new Java8Test6Date_Time();
        System.out.println("***LocalDateTime使用***");
        java8tester.testLocalDateTime();
        System.out.println("***ZoneDateTime使用***");
        java8tester.testZonedDateTime();

        System.out.println("***Clock使用***");
        Clock clock = Clock.systemDefaultZone(); ////获得一个原始钟表，以格林威治标准时间为准
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        Clock c1 = Clock.tick(clock, Duration.ofSeconds(5));//获得一个嘀嗒间隔5秒的tickClock钟表
        System.out.println(c1);

        for (int i = 0; i < 15 ; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000L);//每隔1秒取样一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("---");
            System.out.println(clock.instant());//原始钟表打印时间戳
            System.out.println(c1.instant()+ " tick钟表");//tickClock钟表打印时间戳
        }

    }

    public void testLocalDateTime(){

        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
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

    public void testZonedDateTime(){

        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZonedDateTime date2 = ZonedDateTime.now();
        System.out.println("date2: " + date2);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
