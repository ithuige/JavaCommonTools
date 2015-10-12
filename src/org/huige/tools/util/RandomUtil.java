/**
 * 
 */
package org.huige.tools.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author huige
 * 2015年10月12日 上午11:38:19
 * 随机数生成工具类
 */
public class RandomUtil {
	private static final String Zero20 = "00000000000000000000";
	private static Random random = new Random();
	/**
	 * 生成指定位数的随机数，所有位都随机产生
	 * 
	 *@param length
	 *@return
	 */
	public static String getRandomId(int length) {
		String val = "";
		if (length <= 10) {
			val = String.valueOf(Math.abs(random.nextInt()));
		} else if (length <= 19) {
			val = String.valueOf(Math.abs(random.nextLong()));
		} else if (length <= 29) {
			val =
				String.valueOf(Math.abs(random.nextLong()))
					+ String.valueOf(Math.abs(random.nextInt()));
		} else {
			val =
				String.valueOf(Math.abs(random.nextLong()))
					+ String.valueOf(Math.abs(random.nextLong()));
		}
		if (length < val.length()) {
			val = val.substring(0, length);
		} else {
			val = val + Zero20;
			val = val.substring(0, length);
		}
		return val;
	}
	
	/**
	 * 生成给定位数的随机数，前16位是当前时间（精确到毫秒），后面则是随机数。
	 * 一般用作不重复的主键
	 * @param length 不要超过76位
	 * @return
	 */
	public static String getTimeRandomId(int length) {
		String val = "";
		String pre = getCurrentTimeMillisAsString();
		if(length<=16)
		{
			val = pre;
		} else {
			int i = length-16;
			String aft = getRandomId(i);
			val = pre + aft;
		}	
		return val;
	}
	/**
	 * 获取当前系统时间 返回 yyyyMMddHHmmssmm精确到毫秒
	 * 
	 * @return
	 */
	private static String getCurrentTimeMillisAsString() {
		long currTimeM = getCurrentTimeMillis();
		return getTimeMillisAsString(currTimeM, "yyyyMMddHHmmssmm");
	}
	/**
	 * 
	 * 获取当前系统时间 返回 12:12:12
	 * 
	 * @return
	 */
	private static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	/**
	 * @param ctm
	 *            long 系统时间
	 * @param format
	 * @return
	 */
	private static String getTimeMillisAsString(long ctm, String format) {
		Date date = new Date(ctm);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}    
}
