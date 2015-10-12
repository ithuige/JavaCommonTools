/**
 * 
 */
package org.huige.tools.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author huige
 * 2015��10��12�� ����11:38:19
 * ��������ɹ�����
 */
public class RandomUtil {
	private static final String Zero20 = "00000000000000000000";
	private static Random random = new Random();
	/**
	 * ����ָ��λ���������������λ���������
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
	 * ���ɸ���λ�����������ǰ16λ�ǵ�ǰʱ�䣨��ȷ�����룩�����������������
	 * һ���������ظ�������
	 * @param length ��Ҫ����76λ
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
	 * ��ȡ��ǰϵͳʱ�� ���� yyyyMMddHHmmssmm��ȷ������
	 * 
	 * @return
	 */
	private static String getCurrentTimeMillisAsString() {
		long currTimeM = getCurrentTimeMillis();
		return getTimeMillisAsString(currTimeM, "yyyyMMddHHmmssmm");
	}
	/**
	 * 
	 * ��ȡ��ǰϵͳʱ�� ���� 12:12:12
	 * 
	 * @return
	 */
	private static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	/**
	 * @param ctm
	 *            long ϵͳʱ��
	 * @param format
	 * @return
	 */
	private static String getTimeMillisAsString(long ctm, String format) {
		Date date = new Date(ctm);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}    
}
