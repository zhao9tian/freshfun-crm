package com.quxin.freshfun.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	   /**
     * 获取所在日的0点的时间戳（秒值）
     * 
     * @param day Long 时间
     * @return long
     */
    public static long getTodayStart(long day) {
        long firstDay = 0L;
        Calendar cal = Calendar.getInstance();
        if (("" + day).length() > 10) {
            cal.setTime(new Date(day));
        } else {
            cal.setTime(new Date(day * 1000));
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        firstDay = cal.getTimeInMillis() / 1000;
        return firstDay;
    }

    /**
     * 获取所在日的0点的时间戳
     * @param day Long 时间
     * @param scope String 级别<br>"MS"：毫秒级<br>"S":秒级
     * @return
     */
    public static long getTodayStart(long day,String scope) {
    	if(scope.equals("MS")){
    		return getTodayStart(day)*1000;
    	}else if(scope.equals("S")){
    		return getTodayStart(day);
    	}else{
    		return getTodayStart(day);
    	}
    }

    /**
     * 获取下一天时间的0点的时间戳（秒值）
     * @param day Long 时间
     * @return long
     */
    public static long getNextDayStart(long day) {
        long daySpanMill = 86400000L;
        long nextDay = 0L;
        Calendar cal = Calendar.getInstance();
        if (("" + day).length() > 10) {
            cal.setTime(new Date(day));
        } else {
            cal.setTime(new Date(day * 1000));
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        nextDay = (cal.getTimeInMillis() + daySpanMill) / 1000;
        return nextDay;
    }
    
    /**
     * 获取下一天时间的0点的时间戳
     * @param day Long 时间
     * @param scope String 级别<br>"MS"：毫秒级<br>"S":秒级
     * @return
     */
    public static long getNextDayStart(long day,String scope) {
    	if(scope.equals("MS")){
    		return getNextDayStart(day)*1000;
    	}else if(scope.equals("S")){
    		return getNextDayStart(day);
    	}else{
    		return getNextDayStart(day);
    	}
    }
    

    /**
     * 根据某个日期时间戳秒值，获取所在月的第一天的0点的时间戳秒值.
     * 
     * @param day
     * @return
     */
    public static long getMonthFirst(long day) {
        long firstDay = 0L;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(day * 1000));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        firstDay = cal.getTimeInMillis() / 1000;
        return firstDay;
    }

    /**
     * 根据某个日期时间戳秒值，获取所在月，月份值按实际理解，1代表一月，依此类推.
     * 
     * @param day
     * @return
     */
    public static int getMonth(long day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(day * 1000));
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据某个日期时间戳秒值，获取所在年。
     * 
     * @author yumo.lck
     */
    public static int getYear(long day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(day * 1000));
        return cal.get(Calendar.YEAR);
    }

    /**
     * 根据某个日期时间戳秒值，获取所在周的周一的0点的时间戳秒值.
     * 
     * @param day
     * @return
     */
    public static long getWeekFirst(long day) {
        long firstDay = 0L;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(day * 1000));
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        firstDay = cal.getTimeInMillis() / 1000;
        return firstDay;
    }

    /**
     * 根据某个日期时间戳秒值，获取所在周在一年中是第几周.
     * 
     * @param day
     * @return
     */
    public static int getWeekOfYear(long day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(day * 1000));
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 根据字符串取得昨天日期返回String
     * 
     * @param day 时间字符串
     * @param inFormat 传入正则
     * @param outFormat 传出正则
     * @return String
     * @throws ParseException
     */
    public static String getYesterdayByString(String day, String inFormat, String outFormat){
        try {
			SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
			Date date = sdf.parse(day);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int calendarDay = calendar.get(Calendar.DATE);
			calendar.set(Calendar.DATE, calendarDay - 1);
			String dayBefore = new SimpleDateFormat(outFormat).format(calendar.getTime());
			return dayBefore;
		} catch (ParseException e) {
			return null;
		}
    }

    /**
     * 根据字符串取得明天日期返回String
     * 
     * @param day 时间字符串
     * @param inFormat 传入正则
     * @param outFormat 传出正则
     * @return String
     * @throws ParseException
     */
    public static String getTomorrowByString(String day, String inFormat, String outFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
        Date date = sdf.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int calendarDay = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, calendarDay + 1);
        String dayBefore = new SimpleDateFormat(outFormat).format(calendar.getTime());
        return dayBefore;
    }
    
    /**
     * 根据字符串取得明天日期
     * 
     * @param day 时间字符串
     * @param inFormat 传入正则
     * @param outFormat 传出正则
     * @return Date
     * @throws ParseException
     */
    public static Date getTomorrowByDate(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int calendarDay = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, calendarDay + 1);
        return calendar.getTime();
    }

    /**
     * 根据字符串取得30天前的日期返回String
     *
     * @param day 时间字符串
     * @param inFormat 传入正则
     * @param outFormat 传出正则
     * @return String
     * @throws ParseException
     */
    public static String get30DaysBeforeByString(String day, String inFormat, String outFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
        Date date = sdf.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int calendarDay = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, calendarDay - 30);
        String dayBefore = new SimpleDateFormat(outFormat).format(calendar.getTime());
        return dayBefore;
    }
    
    /**
     * 根据字符串取得30天后的日期返回String
     *
     * @param day 时间字符串
     * @param inFormat 传入正则
     * @param outFormat 传出正则
     * @return String
     * @throws ParseException
     */
    public static String get30DaysLaterByString(String day, String inFormat, String outFormat) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
    	Date date = sdf.parse(day);
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	int calendarDay = calendar.get(Calendar.DATE);
    	calendar.set(Calendar.DATE, calendarDay + 30);
    	String dayBefore = new SimpleDateFormat(outFormat).format(calendar.getTime());
    	return dayBefore;
    }


    /**
     * 字符串日期格式转换
     * 
     * @param day 时间字符串
     * @param inFormat 传入正则
     * @param outFormat 传出正则
     * @return String
     * @throws ParseException
     */
    public static String getDateStrTOFormat(String day, String inFormat, String outFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
        Date date = sdf.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dayBefore = new SimpleDateFormat(outFormat).format(calendar.getTime());
        return dayBefore;
    }
    
    public static long getDateMillTOFormat(String day, String inFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
        Date date = sdf.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis()/1000;
    }
    public static long getDateMillTOFormatMS(String day, String inFormat) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
    	Date date = sdf.parse(day);
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return calendar.getTimeInMillis();
    }

    /**
     * 根据年和月获取这个月的第一天的秒值
     * 
     * @author yumo.lck
     * @param year
     * @param month
     * @return
     */
    public static long getFirstDay4Month(int year, int month) {
        long firstDay = 0L;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        // 上一个月的最后一天的下一天：这个月的第一天
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        firstDay = cal.getTimeInMillis() / 1000;
        return firstDay;
    }

    /**
     * 根据年和月获取这个月的最后一天的秒值
     * 
     * @author yumo.lck
     * @param year
     * @param month
     * @return
     */
    public static long getLastDay4Month(int year, int month) {
        long lastDay = 0L;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        // 1表示下个月第一天的零点，可以看做这个月的最有一天结束,但是数据表就最后一天零点就行
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        lastDay = cal.getTimeInMillis() / 1000;
        return lastDay;
    }

    /**
     * 根据某个日期时间戳秒值，获取上个月第一天零点的值的秒值
     * 
     * @author yumo.lck
     * @param chooseFirstDay的boolean值为true时返回上个月第一天的秒值 ，否则返回上个月最后一天秒值 day 的单位是 s/秒
     */

    public static long getBeforeMonthDay(long day, boolean chooseFirstDay) {
        long chooseDay = 0L;
        int currentMonth = getMonth(day);
        int currentYear = getYear(day);
        if (currentMonth > 1) {
            currentMonth--;
        } else {
            currentYear--;
            currentMonth = 12;
        }
        if (chooseFirstDay) {
            chooseDay = getFirstDay4Month(currentYear, currentMonth);
            return chooseDay;
        } else {
            chooseDay = getLastDay4Month(currentYear, currentMonth);
            return chooseDay;
        }

    }

    /**
     * 今日
     * 
     * @return long
     */
    public static long getMillByOneDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * 昨日
     * 
     * @return long
     */
    public static long getMillByYesDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * 一周前
     * 
     * @return
     */
    public static long getMillByLastWeekDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }
    
   /**
    * 取得秒级Long型时间
    * @param severalDays 需要计算的天数
    * @param condition  条件 
    * <br> "-":当前时间点以前的时间(当前时间-需要计算的天数) 
    * <br> "+":当前时间点以后的时间(当前时间+需要计算的天数)
    * <br> null/其他值：为当天
    * @return long
    */
    public static long getMillByDay(int severalDays,String condition) {
    	int dateT=0;
        Calendar cal = Calendar.getInstance();
    	if(condition==null){
    		return getMillToDay(cal,dateT);
    	}
        if(condition.equals("-")){
        	dateT = (cal.get(Calendar.DATE) - severalDays);
        	return getMillToDay(cal,dateT);
        }
        if(condition.equals("+")){
        	dateT = (cal.get(Calendar.DATE) + severalDays);
        	return getMillToDay(cal,dateT);
        }
		return getMillToDay(cal,dateT);
  }
    
    /**
     * 取得毫秒级Long型时间
     * @param severalDays 需要计算的天数
     * @param condition  条件 
     * <br> "-":当前时间点以前的时间(当前时间-需要计算的天数) 
     * <br> "+":当前时间点以后的时间(当前时间+需要计算的天数)
     * <br> null/其他值：为当天
     * @return long
     */
    public static long getStampByDay(int severalDays,String condition) {
    	int dateT=0;
    	Calendar cal = Calendar.getInstance();
    	if(condition==null){
    		return getStampToDay(cal,dateT);
    	}
    	if(condition.equals("-")){
    		dateT = (cal.get(Calendar.DATE) - severalDays);
    		return getStampToDay(cal,dateT);
    	}
    	if(condition.equals("+")){
    		dateT = (cal.get(Calendar.DATE) + severalDays);
    		return getStampToDay(cal,dateT);
    	}
    	return getStampToDay(cal,dateT);
    }
    /**
     * 取得当天秒级Long型数据
     * @return long
     */
    public static long getMillByDay(){
		return getMillByDay(0,null);
    }
    
    /**
     * Calendar 型对日期进行计算
     * @param cal  Calendar
     * @param dateT Integer 
     * @return  long
     */
    public static long getMillToDay(Calendar cal,int dateT){
		   if(dateT!=0){
			   cal.set(Calendar.DATE, dateT);
		   }
	       cal.set(Calendar.HOUR_OF_DAY, 0);
	       cal.set(Calendar.MINUTE, 0);
	       cal.set(Calendar.SECOND, 0);
	       cal.set(Calendar.MILLISECOND, 0);
	       return cal.getTimeInMillis()/1000;
	}
    
    /**
     * Calendar 型对日期进行计算
     * @param cal  Calendar
     * @param dateT Integer 
     * @return  long
     */
    public static long getStampToDay(Calendar cal,int dateT){
    	if(dateT!=0){
    		cal.set(Calendar.DATE, dateT);
    	}
    	return cal.getTimeInMillis();
    }

    public static String getToday() {
        Calendar cal = Calendar.getInstance();
        return cal.get(1) + "年" + cal.get(2) + "月" + cal.get(3) + "日";
    }

    /**
     * 根据秒或者毫秒获取当天的日期
     * 
     * @param day 秒
     * @return 格式化后的日期
     */
    public static String getDate(long day, String format) {
        Calendar cal = Calendar.getInstance();
        if (("" + day).length() > 10) {
            cal.setTime(new Date(day));
        } else {
            cal.setTime(new Date(day * 1000));
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(cal.getTime());
    }
    
    /**
     * 获得指定格式的日期字符串
     * 
     * @param Date date
     * @return 格式化后的日期
     */
    public static String getDate(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }
    
    
    /**
     * String型时间戳转为long型
     * 
     * @param day 时间
     * @param format 格式化
     * @return long
     * @throws ParseException 
     */
    public static long stringToLong(String day, String format) throws ParseException {
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        long Date = dateFormat.parse(day).getTime();
    	return Date;
    }
    
    /**
     * String型转Date型
     * @param day 时间
     * @param format 格式化
     * @return Date
     * @throws ParseException
     */
    public static Date stringToDate(String day, String format)  {
    	try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			 Date Date = dateFormat.parse(day);
			return Date;
		} catch (ParseException e) {
			return new Date();
		}
    }
    
    
    /**
     * long型时间戳转为String型
     * 
     * @param day 秒
     * @return 格式化后的日期
     * @throws ParseException 
     */
    public static String longToString(long day, String format) throws ParseException {
    	if (("" + day).length() <= 10)
    		day=day*1000;
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	    String Date = dateFormat.format(new Date(day));
    	return Date;
    }

    /**
     * 根据秒获取给定日期的前xx天的秒值
     * 
     * @param day 秒
     * @param minusDay 需要减掉的天数
     * @return 秒
     */
    public static int getMinusDate(int day, int minusDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(day * 1000));
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - minusDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) cal.getTimeInMillis() / 1000;
    }

    /**
     * 取得当前系统时间long型
     * 
     * @return long
     */
    public static long getMillByNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getTimeInMillis();
    }

	public static int getWeeksBetweenTwoDates(long startDay, long endDay) {
		int week = getWeekOfYear(endDay) - getWeekOfYear(startDay) + 1;
		if(week<1){
			week = getWeekOfYear(endDay) + getMaxWeekOfYear(startDay) - getWeekOfYear(startDay) + 1;
		}
		return week;
	}

	public static int getMaxWeekOfYear(long startDay) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(startDay * 1000));
        return cal.getMaximum(Calendar.WEEK_OF_YEAR);
	}
	
	public static int getMonthsBetweenTwoDates(long startDay, long endDay) {
		int month = DateUtils.getMonth(endDay) - DateUtils.getMonth(startDay) + 1;
		if(month<1){
			month = getMonth(endDay) + 12 - getMonth(startDay) +1;
		}
		return month;
	}
	
	public static Date parseDate(String dateStr, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
     * 获取传入时间分钟的时间戳（秒值）
     * 
     * @param day Long 时间
     * @return long
     */
    public static long getMinuteStart(long time) {
        long firstDay = 0L;
        Calendar cal = Calendar.getInstance();
        if (("" + time).length() > 10) {
            cal.setTime(new Date(time));
        } else {
            cal.setTime(new Date(time * 1000));
        }
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        firstDay = cal.getTimeInMillis() / 1000;
        return firstDay;
    }
    
    /**
     * 获取传入时间小时的时间戳（秒值）
     * 
     * @param day Long 时间
     * @return long
     */
    public static long getHourStart(long time) {
        long firstDay = 0L;
        Calendar cal = Calendar.getInstance();
        if (("" + time).length() > 10) {
            cal.setTime(new Date(time));
        } else {
            cal.setTime(new Date(time * 1000));
        }
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        firstDay = cal.getTimeInMillis() / 1000;
        return firstDay;
    }
    
    public static void main(String[] args) {
//		try {
//			long bb = 1451908800000l-1451905200000l;
//			System.out.println(bb);
//			String aa = DateUtil.longToString(1451905200000l, "yyyy-MM-dd HH:mm:ss");
//			System.out.println(aa);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	System.out.println(System.currentTimeMillis());
	}
}
