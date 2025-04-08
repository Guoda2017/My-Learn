package lock.guo.object;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @Description:
 * Instant : 瞬时实例
 * LocalDate : 本地日期 不包含具体时间 例如 2014-01-14 可以用来记录生日、纪念日、加盟日
 * LocalTime : 本地时间 不包含日期
 * LocalDateTime : 组合了日期和时间 但不包含时差和时区信息
 * ZonedDateTime : 最完整的日期时间 包含时区和相对UTC或者格林威治的时差
 * @author: guofengbo
 * @date: 2020-05-30 15:01
 **/
public class LocalDateDemo {

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.of(2018, 8, 8);

        //1 获取今日的日期
        System.out.println(today);

        //2 获取年月日
        System.out.println(
                String.format("Year : %d Month : %d day : %d t %n", LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                        LocalDate.now().getDayOfMonth()));

        //3 处理特定的日期
        System.out.println(LocalDate.of(2020, 12, 20));

        //4 判断两个日期是否一致
        System.out.println(LocalDate.now().equals(LocalDate.now()));

        //5 判断周期性的日期  例如每月每一天
        LocalDate dataOfBirth = LocalDate.of(1993, 12, 18);
        MonthDay birthday = MonthDay.of(dataOfBirth.getMonth(), dataOfBirth.getDayOfMonth());
        MonthDay currentMontDay = MonthDay.from(today);
        System.out.println(birthday);
        System.out.println(currentMontDay);
        System.out.println(birthday.equals(currentMontDay));

        //6 获取当前时间
        LocalTime nowTime = LocalTime.now();
        System.out.println(nowTime);

        //7 在现有的时间上增加小时数
        System.out.println(nowTime.plusHours(2));

        //8 计算一个星期之后的日期
        System.out.println(today.plus(1, ChronoUnit.WEEKS));

        //9 计算一年前或一年后的日期
        System.out.println("date before one year : " + today.minus(1, ChronoUnit.YEARS));
        System.out.println("date after one year : " + today.plus(1, ChronoUnit.YEARS));

        //10 Clock时钟类 获取系统时间 并设置成UTC格式
        Clock clockUTC = Clock.systemUTC();
        System.out.println(clockUTC);

        //11 判断日期是早于还是晚于另一个日期
        System.out.println(date.isAfter(today));
        System.out.println(date.isBefore(today));

        // 检查闰年
        System.out.println(today.isLeapYear());

        //计算两个时间的日数 或者月数 差
        System.out.println(Period.between(today, date).getDays());
        System.out.println(Period.between(today, date).getMonths());
    }

}
