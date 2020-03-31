package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : cwj
 * @describe : 时间工具类
 * @date : 2020-3-30
 */
public abstract class DateUtil {

    /**
     * Chinese date time formate
     */
    public static final String CHINESE_DATE_TIME = "yyyy年MM月dd日 HH:mm";
    /**
     * Chinese date format
     */
    public static final String CHINESE_DATE = "yyyy年MM月dd日";
    /**
     * 月日
     */
    public static final String CHINESE_MONTH_DATE = "MM月dd日";

    /**
     * 月日 时分
     */
    public static final String CHINESE_MONTH_TIME = "MM月dd日 HH:mm";

    /**
     * Default date time format
     */
    public static final String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * Default date time format
     */
    public static final String DEFAULT_DATE_TIME_SECOND = "yyyy-MM-dd HH:mm";

    /**
     * Default date time format
     */
    public static final String DEFAULT_DATE_TIME_HOUR = "yyyy-MM-dd HH";

    /**
     * Default date format
     */
    public static final String DEFAULT_DATE = "yyyy-MM-dd";
    /**
     * Default time format
     */
    public static final String DEFAULT_TIME = "HH:mm:ss";
    /**
     * For log and file's date format
     */
    public static final String LOG_DATE_TIME = "yyyyMMddHHmmssSSS";

    public static final String MANAGE_DATA_TIME = "yyyyMMddHHmmss";


    public static  final String FB_DEFAULT_TIME="MM-dd HH:mm" ;

    /**
     * 一天的表示形式
     */
    public static final long DURATION = 1000L * 60 * 60 * 24;//一天

    public static final long DURATION_HOUR = 1000L * 60 * 60;//一小时

    public static final String[] weeks = new String[]{"周日","周一", "周二", "周三", "周四", "周五", "周六"};

    public static String toOracleTime(Date aDate){
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_TIME);
        String stb = format.format(aDate);

        return stb;
    }

}
