package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <b>项目名称</b>：lanrenxiyi<br>
 * <b>类名称</b>：DateUtils<br>
 * <b>类描述</b>：日期时间工具类。<br>
 * <b>创建人</b>：SAM QZL<br>
 * <b>创建时间</b>：2017-1-24 下午6:08:23<br>
 * <b>修改人</b>：SAM QZL<br>
 * <b>修改时间</b>：2017-1-24 下午6:08:23<br>
 * <b>修改备注</b>：<br>
 * @author SAM QZL<br>
 * @version
 * 
 */
public class DateUtils {


    /**
     * 获得某一天之前的n天的年月日
     *
     * @author: yy
     * @Date: 2019/1/28
     * @param d 当前日期
     * @param day 相差天数
     * @return
     */
    public static String getDateBefore(Date d, int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar no = Calendar.getInstance();
        no.setTime(d);
        no.set(Calendar.DATE, no.get(Calendar.DATE) - day);
        String date = df.format(no.getTime());
        return date;
    }

    /**
     * @功能说明:获取当前日期形如（20150603）。
     * @return 20150603
     * @返回类型:String
     * @方法名称:getCurrentDateToNum
     * @类名称:DateUtils
     * @文件名称:DateUtils.java
     * @所属包名:com.lanrenxiyi.util
     * @项目名称:lanrenxiyi
     * @创建时间:2017-1-24 下午5:57:44
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String getCurrentDateToNum() {

        /** 实例化格式工具 **/
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        /** 返回结果 **/
        return format.format(new Date());
    }

    /**
     * <b>转换Date类型至String类型,<br>
     * pattern:yyyy-MM-dd HH:mm:ss</b><br>
     * <br>
     * <b>date_time:<br>
     * 2014-12-15 上午10:41:54</b>
     * 
     * @author Sam
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {

        /** 实例化格式工具 **/
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /** 返回结果 **/
        return format.format(date);
    }

    public static String formatDate(Date date, String formatS) {

        /** 实例化格式工具 **/
        SimpleDateFormat format = new SimpleDateFormat(formatS);
        /** 返回结果 **/
        return format.format(date);
    }

    /**
     * <b>转换Date类型至String类型,<br>
     * pattern:yyyy-MM-dd<br>
     * </b><br>
     * <br>
     * <b>date_time:<br>
     * 2014-12-15 上午10:43:08</b>
     * 
     * @author Sam
     * @param date
     * @return String
     */
    public static String formateDateWithoutTime(Date date) {

        /** 实例化格式工具 **/
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        /** 返回结果 **/
        return format.format(date);
    }

    /**
     * <b>formatStringToDate</b>：(String 类型Date转Date类型)<br>
     * @param<br>
     * @param<br>
     * @return Date<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public static Date formatStringToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(str);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * <b>formatStringToDate</b>：(String 类型Date转Date类型)<br>
     * @param<br>
     * @param<br>
     * @return Date<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public static Date formatStringToDate(String str, String formater) {

        SimpleDateFormat format = new SimpleDateFormat(formater);
        try {
            return format.parse(str);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * <b>getNowDateTime</b>：(获取当前日期与时间的String格式)<br>
     * @demo 2015-05-02 14:25:32
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public static String getNowDateTime() {

        return formatDate(new Date());
    }

    /**
     * @功能说明:获取当天日期。
     * @param dateformat
     *            格式对象
     * @return 当天日期
     * @返回类型:String
     * @方法名称:getNowTime
     * @类名称:DateUtils
     * @文件名称:DateUtils.java
     * @所属包名:com.lanrenxiyi.util
     * @项目名称:lanrenxiyi
     * @创建时间:2017-1-24 下午6:01:16
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String getNowTime(String dateformat) {

        /** 获取当天日期 **/
        Date now = new Date();
        /** 实例化格式工具 **/
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
        /** 格式化 **/
        String hehe = dateFormat.format(now);
        /** 返回结果 **/
        return hehe;
    }

    /**
     * @功能说明:获取当天日期。（格式如：yyyy-MM-dd HH:mm:ss）
     * @return 当天日期
     * @返回类型:String
     * @方法名称:getNowTime
     * @类名称:DateUtils
     * @文件名称:DateUtils.java
     * @所属包名:com.lanrenxiyi.util
     * @项目名称:lanrenxiyi
     * @创建时间:2017-1-24 下午6:01:16
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String getNowtime() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    @Deprecated
    public static String getNewdateBygs(String famt) {

        SimpleDateFormat formatter = new SimpleDateFormat(famt);
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * @功能说明:计算两个日期时间的分钟数之差。
     * @param date1
     *            较小时间
     * @param date2
     *            较大时间
     * @return 分钟之差
     * @返回类型:Integer
     * @方法名称:getMinutes
     * @类名称:DateUtils
     * @文件名称:DateUtils.java
     * @所属包名:com.lanrenxiyi.util
     * @项目名称:lanrenxiyi
     * @创建时间:2017-1-24 下午6:06:27
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static Integer getMinutes(String date1, String date2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer minutes = 0;
        try {
            Date dedate = sdf.parse(date1);
            Date arrdate = sdf.parse(date2);
            minutes = Integer.parseInt((arrdate.getTime() - dedate.getTime()) / 60 / 1000 + "");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return minutes;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取某月的最后一天
     * @Title:getLastDayOfMonth
     * @Description:
     * @param:@param year
     * @param:@param month
     * @param:@return
     * @return:String
     * @throws
     */
    public static String getLastDayOfMonth(int year, int month) {

        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * <b>getParamDateAfterNMonthDate</b>：(获取参数日期的后几个月的当天日期)<br>
     * @param currentDate
     *            当前日期<br>
     * @param N
     *            几个月<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public static String getParamDateAfterNMonthDate(String currentDate, int N) {

        Calendar calender = Calendar.getInstance();
        Date date = formatStringToDate(currentDate);
        calender.setTime(date);
        calender.add(Calendar.MONTH, N);
        return formatDate(calender.getTime());
    }

    /**
     * <b>getParamDateAfterNYearDate</b>：(获取参数日期N年后的当前日期时间)<br>
     * @return String<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public static String getParamDateAfterNYearDate(String currentDate, int N) {

        Calendar calender = Calendar.getInstance();
        Date date = formatStringToDate(currentDate);
        calender.setTime(date);
        calender.add(Calendar.YEAR, N);
        return formatDate(calender.getTime());
    }

    /**
     * @功能说明:获取当前日期N日后的日期。
     * @param currentDate
     *            当前日期
     * @param N
     *            日
     * @return 日期时间
     * @返回类型:String
     * @方法名称:getParamDateAfterNDays
     * @类名称:DateUtils
     * @文件名称:DateUtils.java
     * @所属包名:com.lanrenxiyi.util
     * @项目名称:lanrenxiyi
     * @创建时间:2017-1-19 下午1:49:27
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String getParamDateAfterNDays(String currentDate, int N) {

        /** 实例化日历对象 **/
        Calendar calender = Calendar.getInstance();
        /** 转换为Date类型 **/
        Date date = formatStringToDate(currentDate);
        /** 设置当前日期 **/
        calender.setTime(date);
        /** 计算N日以后的当前日期 **/
        calender.add(Calendar.DATE, N);
        /** 返回结果 **/
        return formatDate(calender.getTime());
    }

    /**
     * @功能说明:获取当前日期的组成部分。
     * @param type
     *            （Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.
     *            HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND）
     * @return 组成部分。
     * @返回类型:int
     * @方法名称:getOneOfCurrentDateTime
     * @类名称:DateUtils
     * @文件名称:DateUtils.java
     * @所属包名:com.lanrenxiyi.util
     * @项目名称:lanrenxiyi
     * @创建时间:2017-1-24 下午6:13:01
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static int getOneOfCurrentDateTime(int type) {

        /** 当前日期时间 **/
        Calendar currentDateTime = Calendar.getInstance();
        /** 判断 **/
        if (type == Calendar.MONTH) {
            /** 月份需加一 **/
            return currentDateTime.get(type) + 1;
        }
        else {
            /** 直接返回 **/
            return currentDateTime.get(type);
        }
    }
    public static String getStartTime(){
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startStr = simpleDateFormat.format(dayStart);

        return startStr;
    }

    public static String getEndTime(){
        //一天的结束时间 yyyy:MM:dd 23:59:59
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date dayEnd = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endStr = simpleDateFormat.format(dayEnd);
        return endStr;
    }
    public static void main(String[] args) throws Exception {

        String startTime = getStartTime();
        System.out.println(startTime);
        String endTime = getEndTime();
        System.out.println(endTime);
    }
}
