/**
 * StringUtils.java
 */

package org.huige.tools.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Utility class to peform common String manipulation algorithms.
 */
public class StringUtil {

	//数字转为编号位数
	private static int BITS = 6;
	/**
	 * @param xmlBody 不带有版本信息头的xml体字符串
	 * @return 返回带有版本信息头的规范的xml字符串
	 */
	public static String addXMLHeader(String xmlBody) {
		StringBuffer buf = new StringBuffer("<?xml version='1.0' encoding='UTF-8'?>\r\n");
		buf.append(xmlBody);
		return buf.toString();

	}

	//判断字符串是否为空指针或空串，如果是，返回true，如果不是，返回false；
	public static boolean isNull(String str) {
		return (str == null || "".equals(str));
	}
	//判断字符串是否为空指针或空串，如果是，返回true，如果不是，返回false；
	public static String toEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	public static String[] explode(String li, String src) {
		StringTokenizer st = new StringTokenizer(src, li);
		int size = st.countTokens();
		String[] list = new String[size];
		int i = 0;
		while (st.hasMoreTokens()) {
			list[i] = st.nextToken();
			i++;
		}
		return list;
	}

	//把日期转换成YYYY-MM-DD HH24-MI-SS格式的字符串
	public static String Date2String(GregorianCalendar date) {
		String rtn = "";
		//System.err.println("date: " + date.toString() + " date.get(Calendar.MONTH): " + date.get(Calendar.MONTH));
		rtn =
			date.get(Calendar.YEAR)
				+ "-"
				+ Single2Double("" + (date.get(Calendar.MONTH) + 1))
				+ "-"
				+ Single2Double("" + date.get(Calendar.DAY_OF_MONTH))
				+ " "
				+ Single2Double("" + date.get(Calendar.HOUR_OF_DAY))
				+ ":"
				+ Single2Double("" + date.get(Calendar.MINUTE))
				+ ":"
				+ Single2Double("" + date.get(Calendar.SECOND));
		return rtn;
	}
	/**
	 * 把月、日、时、分、秒的数字小于10的转换为以“0”开头的
	 * @param srcStr
	 * @return
	 */
	public static String Single2Double(String srcStr) {
		if (srcStr.length() < 2) {
			srcStr = "0" + srcStr;
		}
		return srcStr;
	}
	/**
	 * Initialization lock for the whole class. Init's only happen once per
	 * class load so this shouldn't be a bottleneck.
	 */
	private static Object initLock = new Object();

	/**
	 * Replaces all instances of oldString with newString in line.
	 *
	 * @param line the String to search to perform replacements on
	 * @param oldString the String that should be replaced by newString
	 * @param newString the String that will replace all instances of oldString
	 *
	 * @return a String will all instances of oldString replaced by newString
	 */
	public static final String replace(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * Replaces all instances of oldString with newString in line with the
	 * added feature that matches of newString in oldString ignore case.
	 *
	 * @param line the String to search to perform replacements on
	 * @param oldString the String that should be replaced by newString
	 * @param newString the String that will replace all instances of oldString
	 *
	 * @return a String will all instances of oldString replaced by newString
	 */
	public static final String replaceIgnoreCase(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static final String replace(String line, int begin, int subLen, String newString)
	{
		if(line==null)
		{
			return "";
		}
		int len = line.length();
		if(begin>len)
		{
			return line;
		}
		if(begin<1)
		{
			return "";
		}
		if(subLen==0)
		{
			newString="";
		}
		String str1 = line.substring(0,begin-1);
		String str2 = newString;
		String str3;
		if(begin+subLen>len)
		{
			str3 = "";
		}else{
			str3 = line.substring(begin+subLen-1,len);
		}
		return str1 + str2 + str3;
	}
	/**
	 * Replaces all instances of oldString with newString in line.
	 * The count Integer is updated with number of replaces.
	 *
	 * @param line the String to search to perform replacements on
	 * @param oldString the String that should be replaced by newString
	 * @param newString the String that will replace all instances of oldString
	 *
	 * @return a String will all instances of oldString replaced by newString
	 */
	public static final String replace(
		String line,
		String oldString,
		String newString,
		int[] count) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int counter = 0;
			counter++;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			count[0] = counter;
			return buf.toString();
		}
		return line;
	}

	/**
	 * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
	 * &lt;table&gt;, etc) and converts the '&lt'' and '&gt;' characters to
	 * their HTML escape sequences.
	 *
	 * @param input the text to be converted.
	 * @return the input string with the characters '&lt;' and '&gt;' replaced
	 *  with their HTML escape sequences.
	 */
	public static final String escapeHTMLTags(String input) {
		//Check if the string is null or zero length -- if so, return
		//what was sent in.
		if (input == null || input.length() == 0) {
			return input;
		}
		//Use a StringBuffer in lieu of String concatenation -- it is
		//much more efficient this way.
		StringBuffer buf = new StringBuffer(input.length());
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '<') {
				buf.append("&lt;");
			} else if (ch == '>') {
				buf.append("&gt;");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;

	/**
	 * Hashes a String using the Md5 algorithm and returns the result as a
	 * String of hexadecimal numbers. This method is synchronized to avoid
	 * excessive MessageDigest object creation. If calling this method becomes
	 * a bottleneck in your code, you may wish to maintain a pool of
	 * MessageDigest objects instead of using this method.
	 * <p>
	 * A hash is a one-way function -- that is, given an
	 * input, an output is easily computed. However, given the output, the
	 * input is almost impossible to compute. This is useful for passwords
	 * since we can store the hash and a hacker will then have a very hard time
	 * determining the original password.
	 * <p>
	 * In Jive, every time a user logs in, we simply
	 * take their plain text password, compute the hash, and compare the
	 * generated hash to the stored hash. Since it is almost impossible that
	 * two passwords will generate the same hash, we know if the user gave us
	 * the correct password or not. The only negative to this system is that
	 * password recovery is basically impossible. Therefore, a reset password
	 * method is used instead.
	 *
	 * @param data the String to compute the hash of.
	 * @return a hashed version of the passed-in String
	 */
	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println(
					"Failed to load the MD5 MessageDigest. "
						+ "Jive will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		//Now, compute hash.
		digest.update(data.getBytes());
		return toHex(digest.digest());
	}

	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number.
	 * <p>
	 * Method by Santeri Paavolainen, Helsinki Finland 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 *
	 * @param hash an rray of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static final String toHex(byte hash[]) {
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if (((int) hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) hash[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Converts a line of text into an array of lower case words. Words are
	 * delimited by the following characters: , .\r\n:/\+
	 * <p>
	 * In the future, this method should be changed to use a
	 * BreakIterator.wordInstance(). That class offers much more fexibility.
	 *
	 * @param text a String of text to convert into an array of words
	 * @return text broken up into an array of words.
	 */
	public static final String[] toLowerCaseWordArray(String text) {
		if (text == null || text.length() == 0) {
			return new String[0];
		}
		StringTokenizer tokens = new StringTokenizer(text, " ,\r\n.:/\\+");
		String[] words = new String[tokens.countTokens()];
		for (int i = 0; i < words.length; i++) {
			words[i] = tokens.nextToken().toLowerCase();
		}
		return words;
	}

	/**
	 * A list of some of the most common words. For searching and indexing, we
	 * often want to filter out these words since they just confuse searches.
	 * The list was not created scientifically so may be incomplete :)
	 */
	private static final String[] commonWords =
		new String[] {
			"a",
			"and",
			"as",
			"at",
			"be",
			"do",
			"i",
			"if",
			"in",
			"is",
			"it",
			"so",
			"the",
			"to" };
	private static Map commonWordsMap = null;

	/**
	 * Returns a new String array with some of the most common English words
	 * removed. The specific words removed are: a, and, as, at, be, do, i, if,
	 * in, is, it, so, the, to
	 */
	public static final String[] removeCommonWords(String[] words) {
		//See if common words map has been initialized. We don't statically
		//initialize it to save some memory. Even though this a small savings,
		//it adds up with hundreds of classes being loaded.
		if (commonWordsMap == null) {
			synchronized (initLock) {
				if (commonWordsMap == null) {
					commonWordsMap = new HashMap();
					for (int i = 0; i < commonWords.length; i++) {
						commonWordsMap.put(commonWords[i], commonWords[i]);
					}
				}
			}
		}
		//Now, add all words that aren't in the common map to results
		ArrayList results = new ArrayList(words.length);
		for (int i = 0; i < words.length; i++) {
			if (!commonWordsMap.containsKey(words[i])) {
				results.add(words[i]);
			}
		}
		return (String[]) results.toArray(new String[results.size()]);
	}

	/**
	 * Pseudo-random number generator object for use with randomString().
	 * The Random class is not considered to be cryptographically secure, so
	 * only use these random Strings for low to medium security applications.
	 */
	private static Random randGen = null;

	/**
	 * Array of numbers and letters of mixed case. Numbers appear in the list
	 * twice so that there is a more equal chance that a number will be picked.
	 * We can use the array to get a random number or letter by picking a random
	 * array index.
	 */
	private static char[] numbersAndLetters = null;

	/**
	 * Returns a random String of numbers and letters of the specified length.
	 * The method uses the Random class that is built-in to Java which is
	 * suitable for low to medium grade security uses. This means that the
	 * output is only pseudo random, i.e., each number is mathematically
	 * generated so is not truly random.<p>
	 *
	 * For every character in the returned String, there is an equal chance that
	 * it will be a letter or number. If a letter, there is an equal chance
	 * that it will be lower or upper case.<p>
	 *
	 * The specified length must be at least one. If not, the method will return
	 * null.
	 *
	 * @param length the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		//Init of pseudo random number generator.
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					//Also initialize the numbersAndLetters array
					numbersAndLetters =
						("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
							.toCharArray();
				}
			}
		}
		//Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * Intelligently chops a String at a word boundary (whitespace) that occurs
	 * at the specified index in the argument or before. However, if there is a
	 * newline character before <code>length</code>, the String will be chopped
	 * there. If no newline or whitespace is found in <code>string</code> up to
	 * the index <code>length</code>, the String will chopped at <code>length</code>.
	 * <p>
	 * For example, chopAtWord("This is a nice String", 10) will return
	 * "This is a" which is the first word boundary less than or equal to 10
	 * characters into the original String.
	 *
	 * @param string the String to chop.
	 * @param length the index in <code>string</code> to start looking for a
	 *       whitespace boundary at.
	 * @return a substring of <code>string</code> whose length is less than or
	 *       equal to <code>length</code>, and that is chopped at whitespace.
	 */
	public static final String chopAtWord(String string, int length) {
		if (string == null) {
			return string;
		}

		char[] charArray = string.toCharArray();
		int sLength = string.length();
		if (length < sLength) {
			sLength = length;
		}

		//First check if there is a newline character before length; if so,
		//chop word there.
		for (int i = 0; i < sLength - 1; i++) {
			//Windows
			if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
				return string.substring(0, i);
			}
			//Unix
			else if (charArray[i] == '\n') {
				return string.substring(0, i);
			}
		}
		//Also check boundary case of Unix newline
		if (charArray[sLength - 1] == '\n') {
			return string.substring(0, sLength - 1);
		}

		//Done checking for newline, now see if the total string is less than
		//the specified chop point.
		if (string.length() < length) {
			return string;
		}

		//No newline, so chop at the first whitespace.
		for (int i = length - 1; i > 0; i--) {
			if (charArray[i] == ' ') {
				return string.substring(0, i).trim();
			}
		}

		//Did not find word boundary so return original String chopped at
		//specified length.
		return string.substring(0, length);
	}

	/**
	 * Highlights words in a string. Words matching ignores case. The actual
	 * higlighting method is specified with the start and end higlight tags.
	 * Those might be beginning and ending HTML bold tags, or anything else.
	 *
	 * @param string the String to highlight words in.
	 * @param words an array of words that should be highlighted in the string.
	 * @param startHighlight the tag that should be inserted to start highlighting.
	 * @param endHighlight the tag that should be inserted to end highlighting.
	 * @return a new String with the specified words highlighted.
	 */
	public static final String highlightWords(
		String string,
		String[] words,
		String startHighlight,
		String endHighlight) {
		if (string == null || words == null || startHighlight == null || endHighlight == null) {
			return null;
		}

		//Iterate through each word.
		for (int x = 0; x < words.length; x++) {
			//we want to ignore case.
			String lcString = string.toLowerCase();
			//using a char [] is more efficient
			char[] string2 = string.toCharArray();
			String word = words[x].toLowerCase();

			//perform specialized replace logic
			int i = 0;
			if ((i = lcString.indexOf(word, i)) >= 0) {
				int oLength = word.length();
				StringBuffer buf = new StringBuffer(string2.length);

				//we only want to highlight distinct words and not parts of
				//larger words. The method used below mostly solves this. There
				//are a few cases where it doesn't, but it's close enough.
				boolean startSpace = false;
				char startChar = ' ';
				if (i - 1 > 0) {
					startChar = string2[i - 1];
					if (!Character.isLetter(startChar)) {
						startSpace = true;
					}
				}
				boolean endSpace = false;
				char endChar = ' ';
				if (i + oLength < string2.length) {
					endChar = string2[i + oLength];
					if (!Character.isLetter(endChar)) {
						endSpace = true;
					}
				}
				if ((startSpace && endSpace) || (i == 0 && endSpace)) {
					buf.append(string2, 0, i);
					if (startSpace && startChar == ' ') {
						buf.append(startChar);
					}
					buf.append(startHighlight);
					buf.append(string2, i, oLength).append(endHighlight);
					if (endSpace && endChar == ' ') {
						buf.append(endChar);
					}
				} else {
					buf.append(string2, 0, i);
					buf.append(string2, i, oLength);
				}

				i += oLength;
				int j = i;
				while ((i = lcString.indexOf(word, i)) > 0) {
					startSpace = false;
					startChar = string2[i - 1];
					if (!Character.isLetter(startChar)) {
						startSpace = true;
					}

					endSpace = false;
					if (i + oLength < string2.length) {
						endChar = string2[i + oLength];
						if (!Character.isLetter(endChar)) {
							endSpace = true;
						}
					}
					if ((startSpace && endSpace) || i + oLength == string2.length) {
						buf.append(string2, j, i - j);
						if (startSpace && startChar == ' ') {
							buf.append(startChar);
						}
						buf.append(startHighlight);
						buf.append(string2, i, oLength).append(endHighlight);
						if (endSpace && endChar == ' ') {
							buf.append(endChar);
						}
					} else {
						buf.append(string2, j, i - j);
						buf.append(string2, i, oLength);
					}
					i += oLength;
					j = i;
				}
				buf.append(string2, j, string2.length - j);
				string = buf.toString();
			}
		}
		return string;
	}

	/**
	 * Escapes all necessary characters in the String so that it can be used
	 * in an XML doc.
	 *
	 * @param string the string to escape.
	 * @return the string with appropriate characters escaped.
	 */
	public static final String escapeForXML(String string) {
		//Check if the string is null or zero length -- if so, return
		//what was sent in.
		if (string == null || string.length() == 0) {
			return string;
		}
		char[] sArray = string.toCharArray();
		StringBuffer buf = new StringBuffer(sArray.length);
		char ch;
		for (int i = 0; i < sArray.length; i++) {
			ch = sArray[i];
			if (ch == '<') {
				buf.append("&lt;");
			} else if (ch == '>') {
				buf.append("&gt;");
			} else if (ch == '"') {
				buf.append("&quot;");
			} else if (ch == '&') {
				buf.append("&amp;");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	/**
	 * Unescapes the String by converting XML escape sequences back into normal
	 * characters.
	 *
	 * @param string the string to unescape.
	 * @return the string with appropriate characters unescaped.
	 */
	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&amp;", "&");
		return replace(string, "&quot;", "\"");

	}
	/**
	 * 将汉字字符进行转换
	 * */
	public static final String stringFromCh(String string) {
		byte[] tmpbyte;
		if (string != null) {
			try {
				tmpbyte = string.getBytes("ISO8859_1");
			} catch (UnsupportedEncodingException ex) {
				return "";
			}
			return new String(tmpbyte);
		} else {
			return null;
		}
	}
	/**
	 * 将汉字字符进行反向转换
	 * */
	public static final String stringToCh(String string) {
		byte[] tmpbyte;
		if (string != null) {
			try {
				tmpbyte = string.getBytes("GBK");
				return new String(tmpbyte, "ISO-8859-1");
			} catch (UnsupportedEncodingException ex) {
				return "";
			}
		} else {
			return null;
		}
	}

	/**
		 * @param dateStr "yyyy-MM-dd HH:mm:ss"
		 * @return
		 */
	public static Date strToDate(String dateStr) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = dateFormat.parse(dateStr);
			return date;
		} catch (ParseException e) {
			System.out.println("未知错误"+e.getMessage());
			throw e;
		}

	}

	public static String formatBigDecimal(BigDecimal val)
	{
		if(val==null)
			return "";
		
		if(val.doubleValue()==0.00)
			return "";
		else{
			return val.toString();
		}
	}
	public static String formatInt(int val)
	{
		if(val==0)
			return "";
		else
			return String.valueOf(val);
	}
	
	public static String getString(Object str) {
		if (str == null)
			return "";
		else
			return (String) str;
	}

	public static String toSerialStr(int number) {
		String str = Integer.toString(number);
		int numOf0 = BITS - str.length();
		for (int i = 0; i < numOf0; i++)
			str = "0" + str;
		return str;
	}

	public static int toNumber(String serialStr) {
		return Integer.parseInt(serialStr);
	}

	public static String dateToString(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String pdt = "" + year + "-";
		if (month < 10)
			pdt += "0";
		pdt += month + "-";
		if (day < 10)
			pdt += "0";
		pdt += day + " ";
		if (hour < 10)
			pdt += "0";
		pdt += hour + ":";
		if (minute < 10)
			pdt += "0";
		pdt += minute + ":";
		if (second < 10)
			pdt += "0";
		pdt += second;
		return pdt;
	}
	/**
	 * 打印输出数组数据
	 * @param data 要打印的数组
	 */
	public static String printArray(Object[] data) {
		StringBuffer result = new StringBuffer("{").append(data[0].toString()).append("}");
		for (int i = 1; i < data.length; i++) {
			result.append(", {");
			result.append(data[i].toString());
			result.append("}");
		}
		return result.toString();
	}
	/**
	 * 对HTML代码进行解码
	 */
	public static String htmlDecoding(String encodedHtml) {
		String result = encodedHtml;
		result = StringUtil.replace(result, "&lt;", "<");
		result = StringUtil.replace(result, "&gt;", ">");
		return result;
	}

	/**
	 * 对HTML代码进行编码
	 */
	public static String htmlEncoding(String strHtml) {
		String result = strHtml;
		result = StringUtil.replace(result, "<", "&lt;");
		result = StringUtil.replace(result, ">", "&gt;");
		return result;
	}
	/**
	 * 获得文件名
	 * */
	public static String getFileName(String filePath) {
		String allFileName = "";
		if (filePath.indexOf("/") != -1) {
			allFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		} else if (filePath.indexOf("\\") != -1) {
			allFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		} else {
			System.out.println("文件路径不正确。");
			return "";
		}
		String fileName = allFileName.substring(0, allFileName.indexOf("."));
		return fileName;
	}

	/**
		 * 获得文件名
		 * */
	public static String getFileExtentionName(String filePath) {
		String allFileName = getFullFileName(filePath);
		String fileName = allFileName.substring(allFileName.indexOf(".") + 1);
		return fileName;
	}

	/**
		 * 获得文件全名，包括扩展名
		 * */
	public static String getFullFileName(String filePath) {
		String allFileName = "";
		if (filePath.indexOf("/") != -1) {
			allFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		} else if (filePath.indexOf("\\") != -1) {
			allFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		} else {
			System.out.println("文件路径不正确。");
			return "";
		}

		return allFileName;
	}

	/**
		 * 获得文件夹
		 * */
	public static String getFileDir(String filePath) {
		String dir = "";
		if (filePath.indexOf("/") != -1) {
			dir = filePath.substring(0, filePath.lastIndexOf("/"));
		} else if (filePath.indexOf("\\") != -1) {
			dir = filePath.substring(0, filePath.lastIndexOf("\\"));
		} else {
			System.out.println("文件路径不正确。");
			return "";
		}
		//String fileName = allFileName.substring(0, allFileName.indexOf("."));
		return dir;
	}

	/**
	 * 将以特殊符号分割的字符串，重新组合成List返回
	 * */
	public static List StrDivToList(String str, String sChar) {
		StringTokenizer sValues = new StringTokenizer(str, sChar);
		List list = new ArrayList();
		while (sValues.hasMoreTokens()) {
			String sValue = sValues.nextToken();
			list.add(sValue);
		}
		return list;
	}

	/**
	 * 生成唯一ID串，根据随机数、系统当前时间和IP生成
	 * @return String
	 */
	public static String generateID() throws UnknownHostException {
		String hostIp = InetAddress.getLocalHost().getHostAddress();
		UID uid = new UID();
		StringBuffer sb = new StringBuffer();
		sb.append(uid.toString());
		sb.append("@");
		sb.append(hostIp);
		return sb.toString();
	}

	/**
	 * 生成唯一ID串，根据系统当前时间生成,再加上随机生成的0－999，再加上序数
	 * @return String
	 */
	private static int count = 0;
	public static String generateShortID() {
		long time = System.currentTimeMillis();
		int i = (int) (Math.random() * 1000);
		String longs = Long.toString(time, 16);
		return longs.substring(4, longs.length())
			+ Integer.toString(i, 16)
			+ Integer.toString(count++, 16);
	}
	
	/**
	 * 用fillStr填充字符串
	 * @param line 被填充的字符串
	 * @param fillStr 填充的char
	 * @param index 从第几位开始填充
	 * @param len 填充后的总长度
	 * @return
	 */
	public static String fillAllWithStr(String line,char ch,int index,int len)
	{
		String returnStr="";
		if(line!=null)
		{
			int length = line.length();
			if(length>=len)
			{
				returnStr = line;
			}else{
				int fillLen = len-length;
				char[] array = new char[fillLen];
				for(int i=0;i<fillLen;i++)
				{
					array[i]=ch;
				}
				String fillStr = String.valueOf(array);
				if(index>length)
				{
					returnStr = line;
				}else{
					String pre = line.substring(0,index);
					String las = line.substring(index,length);
					returnStr = pre + fillStr + las;								
				}
			}
		}
		return returnStr;
	}
	/**
	 * 判断是否有手机号码
	 * @param phone 手机号码
	 * @return true，是手机号，false:不是手机号
	 * 支持13开头，15开头，189开头的手机号码验证
	 */
	public static boolean checkPhone(String phone){
        Pattern pattern = Pattern.compile("^13\\d{9}||15\\d{9}||18\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
	/**
	 * 把gbk编码的String转化为UTF-8的字符
	 * @param chenese
	 * @return string
	 */
	public static String gbk2utf8(String chenese){
    	StringBuffer a =new StringBuffer();
    	 char c[] = chenese.toCharArray();
	       for(int i = 0; i < c.length; i++){
	    	   if ((c[i] >= 0x4e00)&&(c[i] <= 0x9fbb)){  
	    		   a.append(gbk2utf82(c[i]));
	    	   }else{
	    		   a.append(c[i]);
	    	   }
	       }
	       return a.toString();
    }
    public static String gbk2utf82(char c) {
        byte[] fullByte = new byte[3];
        if ((c >= 0x4e00)&&(c <= 0x9fbb)){  
        		 
		            // Step 3-1：将字符的ASCII编码转换成2进制值
		            int m = (int) c;
		            String word = Integer.toBinaryString(m);
		          //  System.out.println(word);
	
		            // Step 3-2：将2进制值补足16位(2个字节的长度) 
		            StringBuffer sb = new StringBuffer();
		            int len = 16 - word.length();
		            for (int j = 0; j < len; j++) {
		                sb.append("0");
		            }
		            // Step 3-3：得到该字符最终的2进制GBK编码
		            // 形似：1000 0010 0111 1010
		            sb.append(word);
		            
		            // Step 3-4：最关键的步骤，根据UTF-8的汉字编码规则，首字节
		            // 以1110开头，次字节以10开头，第3字节以10开头。在原始的2进制
		            // 字符串中插入标志位。最终的长度从16--->16+3+2+2=24。
		            sb.insert(0, "1110");
		            sb.insert(8, "10");
		            sb.insert(16, "10");
		           // System.out.println(sb.toString());
	
		            // Step 3-5：将新的字符串进行分段截取，截为3个字节
		            String s1 = sb.substring(0, 8);
		            String s2 = sb.substring(8, 16);
		            String s3 = sb.substring(16);
	
		            // Step 3-6：最后的步骤，把代表3个字节的字符串按2进制的方式
		            // 进行转换，变成2进制的整数，再转换成16进制值
		            byte b0 = Integer.valueOf(s1, 2).byteValue();
		            byte b1 = Integer.valueOf(s2, 2).byteValue();
		            byte b2 = Integer.valueOf(s3, 2).byteValue();
		            
		            // Step 3-7：把转换后的3个字节按顺序存放到字节数组的对应位置
		            fullByte[0] = b0;
		            fullByte[1] = b1;
		            fullByte[2] = b2;
        }
        try {
			return new String(fullByte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
    }
}