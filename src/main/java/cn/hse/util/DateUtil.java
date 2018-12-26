package cn.hse.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.hse.controller.CheckListController;

/**
 * 常用日期工具类
 */
public final class DateUtil {
	
	private static final Logger logger=LogManager.getLogger(CheckListController.class);

    /**
     * Private Constructor
     **/
    private DateUtil() {

    }

    /**
     * 将Date类型转换成String类型
     *
     * @param date Date对象
     * @return 形如:"yyyy-MM-dd HH:mm:ss"
     */
    public static String date2String(Date date) {
        return date2String(date, DatePattern.YYYY_MM_DD_HH_MM_SS.getContext());
    }

    /**
     * 将Date类型转换成String类型
     *
     * @param date Date对象
     * @return 形如:"YYYYMMDDHHMMSS"
     */
    public static String date2String2(Date date) {
        return date2String(date, DatePattern.YYYYMMDDHHMMSS.getContext());
    }
    
    
    
    /**
     * 将yyyy-MM-dd时间转化成字符串
     * @param date
     * @return
     */
    public static String date2String3(Date date) {
        return date2String(date, DatePattern.YYYY_MM_DD.getContext());
    }
    
    

    /**
     * 将Date按格式转化成String
     *
     * @param date    Date对象
     * @param pattern 日期类型
     * @return String
     */
    public static String date2String(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将String类型转换成Date类型
     *
     * @param date Date对象
     * @return
     */
    public static Date string2Date(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DatePattern.YYYY_MM_DD_HH_MM_SS.getContext());
        try {
            return format.parse(date);
        } catch (ParseException e) {
        	logger.error("日期转换出错"+e);
            return null;
        }
    }
    
    /**
     * 将String类型转换成Date类型
     *
     * @param date Date对象
     * @return
     */
    public static Date string2Date2(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DatePattern.YYYY_MM_DD.getContext());
        try {
            return format.parse(date);
        } catch (ParseException e) {
        	logger.error("日期转换出错", e);
            return null;
        }
    }

    public enum DatePattern {
        HHMMSS("HHmmss"),
        HH_MM_SS("HH:mm:ss"),
        YYYYMMDD("yyyyMMdd"),
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
        YYYYMMDDHHMMSSSSS("yyyyMMddHHmmssSSS"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss");
        private String context;

        private DatePattern(String context) {
            this.context = context;
        }

        private String getContext() {
            return this.context;
        }
    }
    
   /* public static void main(String[] args) {
    	System.out.println(string2Date2("2018-12-13"));
	}*/
}
