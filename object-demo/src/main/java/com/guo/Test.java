package com.guo;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author : fengbo.guo
 * @date : 2024-08-27 14:51
 * @Description :
 */
@Slf4j
public class Test {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<SimpleDateFormat> threadDateTime = new ThreadLocal<SimpleDateFormat>();

    public static void main(String[] args) {

        boolean b = checkNowInTimePeriod(Collections.singletonList("17:40-18:50"));
        System.out.println("===========>" + b);
    }

    private static SimpleDateFormat DateTimeInstance() {
        SimpleDateFormat df = threadDateTime.get();
        if (df == null) {
            df = new SimpleDateFormat(DATETIME_FORMAT);
            threadDateTime.set(df);
        }
        return df;
    }

    public static boolean checkNowInTimePeriod(List<String> timePeriodList) {

        // 获取当前时间
        LocalTime now = LocalTime.now();

        for (String timePeriod : timePeriodList) {

            try {
                String[] split = timePeriod.split("-");
                String startTimeStr = split[0].trim();
                String endTimeStr = split[1].trim();
                // 将字符串转换成LocalTime对象
                LocalTime startTime = LocalTime.of(Integer.parseInt(startTimeStr.split(":")[0]), Integer.parseInt(startTimeStr.split(":")[1]));
                LocalTime endTime = LocalTime.of(Integer.parseInt(endTimeStr.split(":")[0]), Integer.parseInt(endTimeStr.split(":")[1]));

                if ((now.isAfter(startTime) && now.isBefore(endTime))) {
                    return true;
                }
            } catch (Exception e) {
                log.error("解析时间格式异常,请关注", e);
                return false;
            }
        }
        return false;
    }

    private static boolean between(String startTime, String endTime, Date date) throws ParseException {
        return between(dateTime(startTime), dateTime(endTime), date);
    }

    private static boolean between(Date startTime, Date endTime, Date date) {
        return date.after(startTime) && date.before(endTime);
    }

    private static Date dateTime(String datestr) throws ParseException {
        return DateTimeInstance().parse(datestr);
    }
}
