/**
 * 
 */
package org.huige.tools.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author huige 2015年10月12日 上午11:45:41
 *
 */
public class DateUtil {
	private static Calendar calendar = null;
	/**
	 * 根据日期 和 格式得到相应的字符日期
	 * 
	 * @param date
	 *            例如 new Date()
	 * @param format
	 *            例如yyyy-MM-dd HH:mm:ss，必须要保证格式化字符串的有效性
	 * @return 格式化后的日期字符串
	 */
	public static String getDateAsFormatString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 根据日期字符串：20100824113749得到相应的日期格式： 2010-08-24 11:37:49
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFormString(String date) {
		if (date.length() == 14) {
			date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " "
					+ date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
		}
		return date;
	}

	/**
	 * 获取当前系统日期 返回 yyyyMMdd20100101精确到日期
	 */
	public static String getNowDate() {
		SimpleDateFormat Format = new SimpleDateFormat("yyyyMMdd");
		return Format.format(new Date()); // 获取当前时间
	}

	/**
	 * 获取系统当前时间 返回 HH:mm:ss17:38:23精确到秒
	 */
	public static String getNowTime() {
		SimpleDateFormat Format = new SimpleDateFormat("HH:mm:ss");
		return Format.format(new Date()); // 获取当前时间
	}

	/**
	 * 获取系统当前日期时间
	 * 
	 * @return 2010-03-03 17:38:23
	 */
	public static String getNowDateTime() {
		SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return Format.format(new Date()); // 获取当前时间
	}
	
	/**
	 * 获取当前系统时间 返回 yyyyMMddHHmmssmm精确到毫秒
	 */
	public static String getNowDateTimeMillisecond() {
		SimpleDateFormat Format = new SimpleDateFormat("yyyyMMddHHmmssmmm");
		return Format.format(new Date()); // 获取当前时间
	}
	/**
	 * 功能描述：返回年份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 获得某两个日期之间的所有日期 例startDate ：起止日期=="20101001";endDate ：截止日期="20101005"
	 * 则返回数组[20101001, 20101002, 20101003, 20101004, 20101005]
	 * 
	 * @param startDate
	 *            ：起止日期
	 * @param endDate
	 *            ：截止日期
	 * @throws Exception
	 */
	public static List findDates(String startDate, String endDate) throws Exception {
		List<Date> lDate = new ArrayList<Date>();
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dBegin = sdf.parse(startDate);
		Date dEnd = sdf.parse(endDate);
		lDate.add(dBegin);

		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(dBegin);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (dEnd.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(dEnd);
		// 组织返回的日期
		for (Date date : lDate) {
			list.add(sdf.format(date));
		}
		if (list.size() == 2) {
			String begin = list.get(0);
			String end = list.get(1);
			if (begin.equals(end)) {
				list.clear();
				list.add(begin);
			}
		}

		return list;
	}
	
	/**
	 * 获取输入日期的下 n 天 返回 8位 like 20050101
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static String getNextDay(String day, int n) {
		if (day == null || "".equals(day) || day.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		try {
			String sYear = day.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = day.substring(4, 6);
			int month = Integer.parseInt(sMonth);
			String sDay = day.substring(6, 8);
			int dday = Integer.parseInt(sDay);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, dday);
			cal.add(Calendar.DATE, n);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			return df.format(cal.getTime());

		} catch (Exception e) {
			throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);

		}
	}
	/** 获取输入日期的下一天 返回 8位 like 20050101
	 * 
	 * @param today
	 * 
	 * @return
	 */
	public static String getNextDay(String day) {
		return getNextDay(day, 1);
	}
	public static String getNextDateByMonth(String date, int n) {
		if (date == null || "".equals(date) || date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		try {
			String sYear = date.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = date.substring(4, 6);
			int month = Integer.parseInt(sMonth);
			String sDay = date.substring(6, 8);
			int day = Integer.parseInt(sDay);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day);
			cal.add(Calendar.MONTH, n);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			return df.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException("根据月份变换进行日期计算是输入得参数不符合系统规格." + e);
		}
	}

	public static String getNextDateByYear(String date, int n) {
		if (date == null || "".equals(date) || date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		try {
			String sYear = date.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = date.substring(4, 6);
			int month = Integer.parseInt(sMonth);
			String sDay = date.substring(6, 8);
			int day = Integer.parseInt(sDay);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day);
			cal.add(Calendar.YEAR, n);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			return df.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException("根据年份变换进行日期计算是输入得参数不符合系统规格." + e);
		}
	}

	public static Date getDateTime(String date, String time) {
		if (date == null || date.length() != 8 || time == null
				|| time.length() != 6) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的换算.");
		}
		String sYear = date.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = date.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = date.substring(6, 8);
		int dday = Integer.parseInt(sDay);
		String sHour = time.substring(0, 2);
		int hour = Integer.parseInt(sHour);
		String sMinutes = time.substring(2, 4);
		int minute = Integer.parseInt(sMinutes);
		String sSecond = time.substring(4, 6);
		int second = Integer.parseInt(sSecond);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday, hour, minute, second);
		return cal.getTime();
	}
	
	/**
	 * 获取输入 月份的下 n 月份 返回 6位 like 200501
	 * 
	 * @param month
	 *            like 200404
	 * @param n
	 * @return
	 */
	public static String getNextMonth(String month, int n) {
		if (month == null || "".equals(month) || month.length() != 6) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.");
		}
		try {
			String sYear = month.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = month.substring(4, 6);
			int mon = Integer.parseInt(sMonth);
			Calendar cal = Calendar.getInstance();
			cal.set(year, mon - 1, 1);
			cal.add(Calendar.MARCH, n);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
			return df.format(cal.getTime());

		} catch (Exception e) {
			throw new RuntimeException("进行月份运算时输入得参数不符合系统规格." + e);

		}
	}
	/**
	 * 返回星期 0 星期天 6 星期陆
	 * 
	 * @param date
	 *            20040101
	 * @return
	 */
	public static int getWeekday(String date) {
		if (date == null || date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.");
		}
		String sYear = date.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = date.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = date.substring(6, 8);
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekday;

	}


	public static Date getDate(String date) {
		if (date == null || date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.");
		}
		String sYear = date.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = date.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = date.substring(6, 8);
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		return cal.getTime();
	}

	/**
	 * 获取输入 月份的前 n 月份 返回 6位 like 200501
	 * 
	 * @param month
	 * @param n
	 * @return
	 */
	public static String getPreviousMonth(String month, int n) {
		return getNextMonth(month, -n);
	}

	/**
	 * 获取输入日期的嵌 n 天 返回 8位 like 20050101
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static String getPreviousDay(String day, int n) {
		return getNextDay(day, -n);
	}
	/**
	 * 得到指定时间的指定类型的n值后的时间
	 * 
	 * @param cur_date
	 *            :yyyymmdd
	 * @param cur_time
	 *            :hhmmss
	 * @param flag
	 *            :1:second,2:minute,3:hour,4:day,5:month,6:year
	 * @param n
	 *            :
	 * @return yyyymmdd hhmmss
	 */
	public static String getNextTime(String cur_date, String cur_time,
			int flag, int n) {
		if (cur_date == null || "".equals(cur_date) || cur_date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		if (cur_time == null || "".equals(cur_time) || cur_time.length() != 6) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		try {
			String sYear = cur_date.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = cur_date.substring(4, 6);
			int month = Integer.parseInt(sMonth);
			String sDay = cur_date.substring(6, 8);
			int day = Integer.parseInt(sDay);
			String sHour = cur_time.substring(0, 2);
			int hour = Integer.parseInt(sHour);
			String sMin = cur_time.substring(2, 4);
			int min = Integer.parseInt(sMin);
			String sSec = cur_time.substring(4, 6);
			int sec = Integer.parseInt(sSec);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day, hour, min, sec);
			switch (flag) {
			case 1:
				cal.add(Calendar.SECOND, n);
				break;
			case 2:
				cal.add(Calendar.MINUTE, n);
				break;
			case 3:
				cal.add(Calendar.HOUR, n);
				break;
			case 4:
				cal.add(Calendar.DATE, n);
				break;
			case 5:
				cal.add(Calendar.MONTH, n);
				break;
			case 6:
				cal.add(Calendar.YEAR, n);
				break;
			default:
				break;
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
			return df.format(cal.getTime());

		} catch (Exception e) {
			throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);

		}

	}

	/*
	 * 查询两个日期之间按月进行拆分的情况， 输入：20080906-20090118 输出结果：List<String> 20080906
	 * 20080930 20081031 20081130 20081231 20090118
	 */
	public static List<String> getDataSplMon(String beginDate, String endDate) {
		List<String> list = new ArrayList<String>();
		if ((beginDate == null || beginDate.length() != 8)
				&& (endDate == null || endDate.length() != 8)) {
			list.add(beginDate);
			list.add(endDate);
			return list;
		}
		int end = Integer.parseInt(endDate);
		String sYear = beginDate.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = beginDate.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = beginDate.substring(6, 8);
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon, 0);// 取得月末的日期
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		int newDate;
		list.add(beginDate);
		int i = 0;
		do {
			cal.set(year, mon + i, 0);
			newDate = Integer.parseInt(df.format(cal.getTime()));
			if (newDate >= end) {
				list.add(endDate);
				break;
			} else {
				list.add(String.valueOf(newDate));
			}
			i++;
		} while (true);
		return list;
	}
	/*
	 * 查询两个日期之间按月进行拆分的情况， 输入：20080906-20090118 输出结果：List<String> 20080906
	 * 20080930 20081031 20081130 20081231 20090118
	 */
	public static List<String> getDataSplYear(String beginDate, String endDate) {
		List<String> list = new ArrayList<String>();
		if ((beginDate == null || beginDate.length() != 8)
				&& (endDate == null || endDate.length() != 8)) {
			list.add(beginDate);
			list.add(endDate);
			return list;
		}
		int end = Integer.parseInt(endDate);
		String sYear = beginDate.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = beginDate.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = beginDate.substring(6, 8);
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, 0, 0);// 取得年末的日期
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		int newDate;
		list.add(beginDate);
		int i = 1;
		do {
			cal.set(year+i, 0, 0);
			newDate = Integer.parseInt(df.format(cal.getTime()));
			if (newDate >= end) {
				list.add(endDate);
				break;
			} else {
				list.add(String.valueOf(newDate));
			}
			i++;
		} while (true);
		return list;
	}
	/*
	 * 查询两个日期之间按天进行拆分的情况， 输入：20080906-20080910 输出结果：List<String> 20080906
	 * 20080907 20080908 20080909 20080910
	 */
	public static List<String> getDataSplDay(String beginDate, String endDate) {
		List<String> list = new ArrayList<String>();
		if ((beginDate == null || beginDate.length() != 8)
				&& (endDate == null || endDate.length() != 8)) {
			return list;
		}
		int i = getDaysBetween(beginDate, endDate);
		list.add(beginDate);
		while (i>0) {
			beginDate=getNextDay(beginDate);
			list.add(beginDate);
			i--;
		}
		return list;
	}
	
	/**   
	    * 计算两个日期之间相差的月数   ,算法是从起始日期的下一个月开始到截止日期那个月月末的月份数
	    * 例如 20110123-20110803；返回7（2,3,4,5,6,7,8月）
	    * @param beginDate   
	    * @param endDate   
	    * @return   
	    */    
	   public static int getDateToDateMonths(String beginDate, String endDate){     
	       int iMonth = 0;     
	       int flag = 0;     
	       try{     
	    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    	   beginDate=getNextMonth(beginDate.substring(0,6), 1)+"01";
	    	   endDate=getNextMonth(endDate.substring(0,6), 1)+"01";
	    	   
	    	   Date dBegin =sdf.parse(beginDate);
		   	   Date dEnd =sdf.parse(endDate);
	   		
	           Calendar objCalendarDate1 = Calendar.getInstance();     
	           objCalendarDate1.setTime(dBegin);     
	    
	           Calendar objCalendarDate2 = Calendar.getInstance();     
	           objCalendarDate2.setTime(dEnd);     
	    
	           if (objCalendarDate2.equals(objCalendarDate1))     
	               return 0;     
	           if (objCalendarDate1.after(objCalendarDate2)){     
	               Calendar temp = objCalendarDate1;     
	               objCalendarDate1 = objCalendarDate2;     
	               objCalendarDate2 = temp;     
	           }     
	           if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))     
	               flag = 1;     
	    
	           if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))     
	               iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))     
	                       * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)     
	                       - objCalendarDate1.get(Calendar.MONTH);     
	           else    
	               iMonth = objCalendarDate2.get(Calendar.MONTH)     
	                       - objCalendarDate1.get(Calendar.MONTH) - flag;     
	    
	       } catch (Exception e){     
	    	   throw new RuntimeException("计算两个日期之间相差的月数 时输入得参数不符合系统规格." + e);
	       }     
	       return iMonth;     
	   }  
	   

	// 获取两个日期之间的天数
	public static int getDaysBetween(String beginDate, String endDate) {
		int day = 0;
		if (beginDate == null || "".equals(beginDate)|| beginDate.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		if (endDate == null || "".equals(endDate) || endDate.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar c_b = Calendar.getInstance();
		Calendar c_e = Calendar.getInstance();
		try {
			c_b.setTime(df.parse(beginDate));
			c_e.setTime(df.parse(endDate));
			if(beginDate.compareTo(endDate)<0){//开始日期小于结束日期
				while (c_b.before(c_e)) {
					day++;
					c_b.add(Calendar.DAY_OF_YEAR, 1);
				}
			}else if(beginDate.compareTo(endDate)>0){//开始日期大于结束日期
				while (c_e.before(c_b)) {
					day--;
					c_e.add(Calendar.DAY_OF_YEAR, 1);
				}
			}else{
				day=0;
			}
		} catch (Exception e) {
			throw new RuntimeException("计算两个日期之间的天数出错,传入日期参数不和规格",e);
		}

		return day;

	}
	/**
     * 获得传入月份的 第n个月的 月初日期和月末日期
     * @param month--查询的月份
     * @param n--前或者后几个月，例如month=201103,n=0，返回20110301,20110331
     * 例如month=201103,n=-2，返回20110101,20110131
     * 例如month=201103,n=6，返回20110901,20110930
     * @return 月初日期和月末日期
     * @throws ParseException 
     */
    public static ArrayList<String> getMonthStartEndDate(String month,int n){
    	String _month=DateUtil.getNextMonth(month, n);
    	Calendar rightNow = Calendar.getInstance();
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
    	try{
    	rightNow.setTime(df.parse(_month));
    	}catch(ParseException e){}
    	int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
    	ArrayList<String> dateArr=new ArrayList<String>();
    	dateArr.add(_month+"01");//月初日期
    	dateArr.add(_month+days);//月末日期
    	return dateArr;
    }
    /**
	 * 比较2个日期时间段是否有交集
	 * @param beginDateTime1：格式如20101001122334
	 * @param endDateTime1
	 * @param beginDateTime2
	 * @param endDateTime2
	 * @return true:有交集
	 * @throws Exception
	 */
	public static boolean comparDateTime(String beginDateTime1, String endDateTime1, String beginDateTime2,
            String endDateTime2) throws Exception {
        boolean flag=false;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date dt1 = df.parse(beginDateTime1);
            Date dt2 = df.parse(endDateTime1);
            Date dt3 = df.parse(beginDateTime2);
            Date dt4 = df.parse(endDateTime2);
            //dt3在dt1和dt2之间
            if (dt1.getTime() < dt3.getTime() && dt3.getTime() < dt2.getTime()) {
                flag=true;
            }
            if (dt1.getTime() < dt4.getTime() && dt3.getTime() < dt2.getTime()) {
            	flag=true;//重合
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
        return flag;
    }
	/**
	 * 判断当前时间是否在某规定时间的之前多少分钟内或者之后多少分钟
	 * 例当前时间09:20:30 ；规定时间09:30:00；之前分钟数240，之后分钟数30--返回true
	 * 例当前时间11:50:30 ；规定时间09:30:00；之前分钟数240，之后分钟数30--返回false
	 * @param time-格式如090812,或13:34:25
	 * @param beforeMinute--之前分钟数，如120
	 * @param afterMinute--之后分钟数，如30
	 * @return
	 */
	public static boolean checkTimeToNowTime(String time,int beforeMinute,int afterMinute){
		boolean flag=false;
		time=time.replaceAll(":", "");
		int second=0,nowsecond=0;
		if(time.length()==6){
			second=Integer.parseInt(time.substring(0,2))*3600+Integer.parseInt(time.substring(2,4))*60+Integer.parseInt(time.substring(4,6));
		}
		String nowTime=DateUtil.getNowTime().replaceAll(":", "");
		nowsecond=Integer.parseInt(nowTime.substring(0,2))*3600+Integer.parseInt(nowTime.substring(2,4))*60+Integer.parseInt(nowTime.substring(4,6));
		if((second-beforeMinute*60)<=nowsecond && nowsecond<=(second+afterMinute*60)){
			flag=true; 
		}
		return flag;
	}
	/**
	 * 比较2个时间的大小
	 * @param time-格式如090812,或13:34:25
	 * @return true--时间1<时间2
	 */
	public static boolean CompareTime(String time1,String time2){
		time1=time1.replaceAll(":", "");
		time2=time2.replaceAll(":", "");
		int second1=0,second2=0;
		if(time1.length()==6){
			second1= Integer.parseInt(time1.substring(0,2))*3600+Integer.parseInt(time1.substring(2,4))*60+Integer.parseInt(time1.substring(4,6));
		}
		if(time2.length()==6){
			second2= Integer.parseInt(time2.substring(0,2))*3600+Integer.parseInt(time2.substring(2,4))*60+Integer.parseInt(time2.substring(4,6));
		}
		if(second1<second2){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 比较2个时间的差是否在某个分钟数以内
	 * 前提条件 时间1<时间2，如果不是，返回false
	 * @param time-格式如090812,或09:34:25
	 * minute--分钟数，如 15,30
	 * 例如time1=090812，time2=093000,minute=15,return:false(超出15分钟)
	 * @return true--在检查的范围内
	 */
	public static boolean CompareTimeMinute(String time1,String time2,int minute){
		time1=time1.replaceAll(":", "");
		time2=time2.replaceAll(":", "");
		int second1=0,second2=0;
		if(time1.length()==6){
			second1= Integer.parseInt(time1.substring(0,2))*3600+Integer.parseInt(time1.substring(2,4))*60+Integer.parseInt(time1.substring(4,6));
		}
		if(time2.length()==6){
			second2= Integer.parseInt(time2.substring(0,2))*3600+Integer.parseInt(time2.substring(2,4))*60+Integer.parseInt(time2.substring(4,6));
		}
		if(second1<second2){
			int num=second2-second1;
			int minuteSecond=minute*60;
			if(num<=minuteSecond){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * 比较某时间是不是在2个时间之内
	 * 前提条件 时间1<时间2，如果不是，返回false
	 * @param comTime，time1,time2-格式如090812,或09:34:25
	 * minute1--在时间1的基础上加减的分钟数，如 -15,30
	 * minute2--在时间2的基础上加减的分钟数，如 15,-30
	 * 例如comTime=093000,time1=140000，time2=180000,minute1=-10,minute2=10;return:false
	 * 9:30不在[13:50-18:10]之间
	 * @return true--在检查的范围内
	 */
	public static boolean CompareTimeInPeriod(String comTime,String time1,int minute1,String time2,int minute2){
		time1=time1.replaceAll(":", "");
		time2=time2.replaceAll(":", "");
		comTime=comTime.replaceAll(":", "");
		int second=0,second1=0,second2=0;
		if(time1.length()==6){
			second1= Integer.parseInt(time1.substring(0,2))*3600+Integer.parseInt(time1.substring(2,4))*60+Integer.parseInt(time1.substring(4,6));
			second1=second1+minute1*60;
		}
		if(time2.length()==6){
			second2= Integer.parseInt(time2.substring(0,2))*3600+Integer.parseInt(time2.substring(2,4))*60+Integer.parseInt(time2.substring(4,6));
			second2=second2+minute2*60;
		}
		if(comTime.length()==6){
			second= Integer.parseInt(comTime.substring(0,2))*3600+Integer.parseInt(comTime.substring(2,4))*60+Integer.parseInt(comTime.substring(4,6));
		}
		if(second1<second2){
			if(second1<=second && second<=second2){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
