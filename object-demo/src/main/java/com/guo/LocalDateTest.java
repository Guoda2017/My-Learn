package com.guo;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : fengbo.guo
 * @date : 2025-02-14 13:49
 * @Description :
 */
@Slf4j
public class LocalDateTest {

    public static void main(String[] args) {

        DateTimeFormatter HH_MM_SS_SSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("HH:mm");

        // 解析输入的时间字符串为 LocalTime 对象
        LocalTime localTime = LocalTime.parse("2024-05-09 16:09:59.999", HH_MM_SS_SSS);
        // 处理时间进位，将秒和毫秒部分舍去并进位到分钟
        LocalTime processedTime = localTime.withSecond(0).withNano(0).plusMinutes(1);
        // 格式化处理后的时间为字符串
        log.info(processedTime.format(HH_MM));

    }

}
