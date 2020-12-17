import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 主要是日期的工具类，用来格式化日期，用于DAO操作
 * @Author malin
 * @Date 2020/4/23
 **/
@Slf4j
public class DateUtils {
  /**
   * 时间格式："yyyy-MM-dd HH:mm:ss"
   */
  public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final String BEGIN_TIME = " 00:00:00";
  public static final String END_TIME = " 23:59:59";

  /**
   * Date转换为LocalDateTime
   *
   * @param date
   */
  public static LocalDateTime date2LocalDateTime(Date date) {
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
    return localDateTime;
  }

  /**
   * LocalDateTime转换为Date
   *
   * @param localDateTime
   */
  public static Date localDateTime2Date(LocalDateTime localDateTime) {
    ZoneId zoneId = ZoneId.systemDefault();
    ZonedDateTime zdt = localDateTime.atZone(zoneId);
    Date date = Date.from(zdt.toInstant());
    return date;
  }
  /***
   * 将日期字符串转化为日期。失败返回null。
   * @param dateStr
   * @return
   */
  public static Date stringToDateTime(String dateStr) {
    Date myDate = null;
    if (StringUtils.isNotBlank(dateStr)) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);
        myDate = localDateTime2Date(localDateTime);
    }
    return myDate;
  }

  /***
   * 返回日期字符串
   * @param date
   * @return
   * @throws ParseException
   */
  public static String formatDate(Date date) {
    LocalDateTime localDateTime = date2LocalDateTime(date);
    String dateStr = localDateTime.format(dateFormatter);
    return dateStr;
  }
  public static String formatDateTime(Date date) {
    LocalDateTime localDateTime = date2LocalDateTime(date);
    String dateStr = localDateTime.format(dateTimeFormatter);
    return dateStr;
  }
  /***
   * 字符串转换为 基准日期，格式化到天
   * @param strDate
   * @return
   */
  public static String getBaseDate(String strDate) {
    if (StringUtils.isNotBlank(strDate)) {
      Date date = stringToDateTime(strDate);
      LocalDateTime localDateTime = date2LocalDateTime(date);
      String dateStr = localDateTime.format(dateFormatter);
      return dateStr;
    }
    return strDate;
  }
  /****
   * 获取开始日期，一天的开始日期
   * @param baseDate
   * @return
   */
  public static Date getBeginTime(String baseDate) {
    String beginDateTime = "";
    if (StringUtils.isNotBlank(baseDate)) {
      beginDateTime = baseDate.concat(BEGIN_TIME);
    }
    Date beginDate = null;
    beginDate = stringToDateTime(beginDateTime);
    return beginDate;
  }

  /****
   * 获取一天当中的结束日期
   * @param baseDate
   * @return
   */
  public static Date getEndTime(String baseDate) {
    String endDateTime = "";
    if (StringUtils.isNotBlank(baseDate)) {
      endDateTime = baseDate.concat(END_TIME);
    }
    Date endDate = null;
    endDate = stringToDateTime(endDateTime);

    return endDate;
  }

  /***
   * 日期的计算
   * @param date
   * @param amount
   * @return
   */
  public static Date addInteger(Date date, int amount) {
    Date myDate = null;
    if (date != null) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DAY_OF_MONTH, amount);
      myDate = calendar.getTime();
    }
    return myDate;
  }

  /****
   * 获取三天前的日期格式
   * @return
   */
  public static Date getThreeDaysAgoDateTime() {
    Date todayDate = new Date();
    Date preThreeday = addInteger(todayDate, -3);
    LocalDateTime localDateTime = date2LocalDateTime(preThreeday);
    String preThreedayStr = localDateTime.format(dateTimeFormatter);
    String baseDate = getBaseDate(preThreedayStr);
    Date proThreedayBegin = getBeginTime(baseDate);
    return proThreedayBegin;
  }

  /**
   * 获取当前日期后缀：如0801
   * @return
   */
  public static String getDateSuf() {
    LocalDate now = LocalDate.now();
    int monthValue = now.getMonthValue();
    int dayOfMonth = now.getDayOfMonth();
    String monthStr = getDataStr(monthValue);
    String dayStr = getDataStr(dayOfMonth);
    return monthStr + dayStr;
  }

  private static String getDataStr(int dayValue) {
    return dayValue >= 10 ? (dayValue + "") : ("0" + dayValue);
  }

}
