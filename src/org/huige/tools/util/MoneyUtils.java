/**
 * 
 */
package org.huige.tools.util;

import java.text.NumberFormat;
import java.util.HashMap;

/**
 * @author huige
 * 2015��10��19�� ����11:04:24
 *���ת�������������ֵĽ��ת�����й���ͳ����ʽ�磺����1011����>��һǧ��һʰһԪ���������
 */
public class MoneyUtils {
	public static final String EMPTY = "";
	  public static final String ZERO = "��";
	  public static final String ONE = "Ҽ";
	  public static final String TWO = "��";
	  public static final String THREE = "��";
	  public static final String FOUR = "��";
	  public static final String FIVE = "��";
	  public static final String SIX = "½";
	  public static final String SEVEN = "��";
	  public static final String EIGHT = "��";
	  public static final String NINE = "��";
	  public static final String TEN = "ʰ";
	  public static final String HUNDRED = "��";
	  public static final String THOUSAND = "Ǫ";
	  public static final String TEN_THOUSAND = "��";
	  public static final String HUNDRED_MILLION = "��";
	  public static final String YUAN = "Ԫ";
	  public static final String JIAO = "��";
	  public static final String FEN = "��";
	  public static final String DOT = ".";
	  private static MoneyUtils formatter = null;
	  private HashMap chineseNumberMap = new HashMap();
	  private HashMap chineseMoneyPattern = new HashMap();
	  private NumberFormat numberFormat = NumberFormat.getInstance();
	  private MoneyUtils() {
	    numberFormat.setMaximumFractionDigits(4);
	    numberFormat.setMinimumFractionDigits(2);
	    numberFormat.setGroupingUsed(false);
	    chineseNumberMap.put("0", ZERO);
	    chineseNumberMap.put("1", ONE);
	    chineseNumberMap.put("2", TWO);
	    chineseNumberMap.put("3", THREE);
	    chineseNumberMap.put("4", FOUR);
	    chineseNumberMap.put("5", FIVE);
	    chineseNumberMap.put("6", SIX);
	    chineseNumberMap.put("7", SEVEN);
	    chineseNumberMap.put("8", EIGHT);
	    chineseNumberMap.put("9", NINE);
	    chineseNumberMap.put(DOT, DOT);
	    chineseMoneyPattern.put("1", TEN);
	    chineseMoneyPattern.put("2", HUNDRED);
	    chineseMoneyPattern.put("3", THOUSAND);
	    chineseMoneyPattern.put("4", TEN_THOUSAND);
	    chineseMoneyPattern.put("5", TEN);
	    chineseMoneyPattern.put("6", HUNDRED);
	    chineseMoneyPattern.put("7", THOUSAND);
	    chineseMoneyPattern.put("8", HUNDRED_MILLION);
	  }
	  public static MoneyUtils getInstance() {
	    if (formatter == null)
	      formatter = new MoneyUtils();
	    return formatter;
	  }
	  public String format(String moneyStr) {
//	    checkPrecision(moneyStr);
	    String result;
	    result = convertToChineseNumber(moneyStr);
	    result = addUnitsToChineseMoneyString(result);
	    return result;
	  }
	  public String format(double moneyDouble) {
	    return format(numberFormat.format(moneyDouble));
	  }
	  public String format(int moneyInt) {
	    return format(numberFormat.format(moneyInt));
	  }
	  public String format(long moneyLong) {
	    return format(numberFormat.format(moneyLong));
	  }
	  public String format(Number moneyNum) {
	    return format(numberFormat.format(moneyNum));
	  }
	  private String convertToChineseNumber(String moneyStr) {
	    String result;
	    StringBuffer cMoneyStringBuffer = new StringBuffer();
	    for (int i = 0; i < moneyStr.length(); i++) {
	      cMoneyStringBuffer.append(chineseNumberMap.get(moneyStr.substring(i, i + 1)));
	    }
	    //ʰ��Ǫ���ڵȶ��Ǻ���������еĵ�λ����������
	    int indexOfDot = cMoneyStringBuffer.indexOf(DOT);
	    int moneyPatternCursor = 1;
	    for (int i = indexOfDot - 1; i > 0; i--) {
	      cMoneyStringBuffer.insert(i, chineseMoneyPattern.get(EMPTY + moneyPatternCursor));
	      moneyPatternCursor = moneyPatternCursor == 8 ? 1 : moneyPatternCursor + 1;
	    }
	    String fractionPart = cMoneyStringBuffer.substring(cMoneyStringBuffer.indexOf("."));
	    cMoneyStringBuffer.delete(cMoneyStringBuffer.indexOf("."), cMoneyStringBuffer.length());
	    while (cMoneyStringBuffer.indexOf("��ʰ") != -1) {
	      cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("��ʰ"), cMoneyStringBuffer.indexOf("��ʰ") + 2, ZERO);
	    }
	    while (cMoneyStringBuffer.indexOf("���") != -1) {
	      cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("���"), cMoneyStringBuffer.indexOf("���") + 2, ZERO);
	    }
	    while (cMoneyStringBuffer.indexOf("��Ǫ") != -1) {
	      cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("��Ǫ"), cMoneyStringBuffer.indexOf("��Ǫ") + 2, ZERO);
	    }
	    while (cMoneyStringBuffer.indexOf("����") != -1) {
	      cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("����"), cMoneyStringBuffer.indexOf("����") + 2, TEN_THOUSAND);
	    }
	    while (cMoneyStringBuffer.indexOf("����") != -1) {
	      cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("����"), cMoneyStringBuffer.indexOf("����") + 2, HUNDRED_MILLION);
	    }
	    while (cMoneyStringBuffer.indexOf("����") != -1) {
	      cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("����"), cMoneyStringBuffer.indexOf("����") + 2, ZERO);
	    }
	    if (cMoneyStringBuffer.lastIndexOf(ZERO) == cMoneyStringBuffer.length() - 1)
	      cMoneyStringBuffer.delete(cMoneyStringBuffer.length() - 1, cMoneyStringBuffer.length());
	    cMoneyStringBuffer.append(fractionPart);
	    result = cMoneyStringBuffer.toString();
	    return result;
	  }

	  private String addUnitsToChineseMoneyString(String moneyStr) {
	    StringBuffer cMoneyStringBuffer = new StringBuffer(moneyStr);
	    int indexOfDot = cMoneyStringBuffer.indexOf(DOT);
	    cMoneyStringBuffer.replace(indexOfDot, indexOfDot + 1, YUAN);
	    return cMoneyStringBuffer.toString();
	  }
}
