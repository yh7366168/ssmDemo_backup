package com.yh.util;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类:
 * create by yinhan
 */
public class DateUtil {

    /**
     * 24小时制
     */
    private static final String DATE_FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    /**
     * 12小时制
     */
    private static final String DATE_FORMAT_TWO = "yyyy-MM-dd hh:mm:ss";

    private static final String DATE_FORMAT_THREE = "yyyy-MM-dd";

    /**
     * @return 返回默认当前时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getNow() {
        return new SimpleDateFormat(DATE_FORMAT_ONE).format(new Date());
    }

    /**
     * @return 返回指定格式的当前时间
     */
    public static String getNow(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * @return 返回日期指定格式
     */
    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取本月第一天
     */
    public static String getMonthFirstDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DATE));
        return new SimpleDateFormat(DATE_FORMAT_THREE).format(calendar.getTime());
    }

    /**
     * 获取本月最后一天
     */
    public static String getMonthLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat(DATE_FORMAT_THREE).format(calendar.getTime());
    }

    /**
     * @return string to date
     * */
    public static Date parseDate(String dateStr) {
        Date date = null;
        try {
            SimpleDateFormat sdf = null;
            if (dateStr.length() == 10) {
                sdf = new SimpleDateFormat(DATE_FORMAT_THREE);
            }else{
                sdf = new SimpleDateFormat(DATE_FORMAT_ONE);
            }
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 比较时间 直接使用compareTo
     * @return
     * -2，异常;
     * -1，dateOneStr<dateTwoStr;
     * 0,  dateOneStr=dateTwoStr;
     * 1， dateOneStr>dateTwoStr
     */
    public static int diffDate(String dateOneStr, String dateTwoStr) {
        int result = -2;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ONE);
            Date dateOne = sdf.parse(sdf.format(dateOneStr));
            Date dateTwo = sdf.parse(sdf.format(dateTwoStr));
            result = dateOne.compareTo(dateTwo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
