/**
 * 
 */
package org.huige.tools.util;

import java.util.regex.Pattern;
/**
 * @author huige
 * 2015��10��12�� ����4:09:28
 *	������ʽ��֤���ü�������
 */
public class RegularUtil {
	/**
     * ������ʽ����֤�û���
     */
    private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
 
    /**
     * ������ʽ����֤�ֻ���
     */
    private static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
 
    /**
     * ������ʽ����֤����
     */
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
 
    /**
     * ������ʽ����֤����
     */
    private static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
 
    /**
     * ������ʽ����֤IP��ַ
     */
    private static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
 
    /** 
     * Integer������ʽ ^-?(([1-9]\d*$)|0) 
     */
    private static final String  REGEX_INTEGER = "^-?(([1-9]\\d*$)|0)";  
    /** 
     * ������������ʽ >=0 ^[1-9]\d*|0$ 
     */
    private static final String  REGEX_INTEGER_NEGATIVE = "^[1-9]\\d*|0$";  
    /** 
     * ������������ʽ <=0 ^-[1-9]\d*|0$ 
     */
    private static final String  REGEX_INTEGER_POSITIVE = "^-[1-9]\\d*|0$";      
    /** 
     * Double������ʽ ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$ 
     */
    private static final String  REGEX_DOUBLE ="^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";  
    /** 
     * ��Double������ʽ >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$�� 
     */
    private static final String  REGEX_DOUBLE_NEGATIVE ="^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";  
    /** 
     * ��Double������ʽ <= 0  ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$ 
     */
    private static final String  REGEX_DOUBLE_POSITIVE ="^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";   
    /** 
     * ����������ʽ ^(?:[1-9][0-9]?|1[01][0-9]|120)$ ƥ��0-120�� 
     */
    private static final String  REGEX_AGE="^(?:[1-9][0-9]?|1[01][0-9]|120)$";  
    /** 
     * �ʱ�������ʽ  [0-9]\d{5}(?!\d) ����6λ�ʱ� 
     */
    private static final String  REGEX_CODE="[0-9]\\d{5}(?!\\d)";    
    /** 
     * ƥ�������֡�26��Ӣ����ĸ�����»�����ɵ��ַ��� ^\w+$ 
     */
    private static final String REGEX_STR_ENG_NUM_="^\\w+$";  
    /** 
     * ƥ�������ֺ�26��Ӣ����ĸ��ɵ��ַ��� ^[A-Za-z0-9]+$  
     */
    private static final String REGEX_STR_ENG_NUM="^[A-Za-z0-9]+";  
    /** 
     * ƥ����26��Ӣ����ĸ��ɵ��ַ���  ^[A-Za-z]+$ 
     */
    private static final String REGEX_STR_ENG="^[A-Za-z]+$";  
    /** 
     * ���������ַ������� 
     * regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";  
     */
    private static final String REGEX_STR_SPECIAL="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";  
    /*** 
     * �������� ֧�֣� 
     *  YYYY-MM-DD  
     *  YYYY/MM/DD  
     *  YYYY_MM_DD  
     *  YYYYMMDD 
     *  YYYY.MM.DD����ʽ 
     */
    private static final String REGEX_DATE_ALL="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)" +  
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)" +  
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)" +  
            "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)" +  
            "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)" +  
            "(0?2)([-\\/\\._]?)(29)$)" +  
            "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)" +  
            "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|" +  
            "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";  
    /*** 
     * �������� ֧�֣� 
     *  YYYY-MM-DD  
     */
    private static final String REGEX_DATE_FORMAT="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
     
    /** 
     * URL������ʽ 
      * ƥ�� http www ftp 
     */
    private static final String REGEX_URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" +  
                                    "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" +  
                                    "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" +  
                                    "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";   
  
    /** 
     * ���֤������ʽ 
     */
    private static final String REGEX_IDCARD="((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +  
                                        "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +  
                                        "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
     
    /**
     * ��������
     */
    private static final String REGEX_MECHANISM_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";
     
    /**
     * ƥ��������ɵ��ַ���  ^[0-9]+$ 
     */
    private static final String REGEX_STR_NUM = "^[0-9]+$";  
    /**
     * У���û���
     * 
     * @param username
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
 
 
    /**
     * У���ֻ���
     * 
     * @param mobile
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
 
    /**
     * У������
     * 
     * @param email
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
 
    /**
     * У�麺��
     * 
     * @param chinese
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }
 
    /**
     * У�����֤
     * 
     * @param idCard
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_IDCARD, idCard);
    }
 
    /**
     * У��URL
     * 
     * @param url
     * @return У��ͨ������true�����򷵻�false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
 
    /**
     * У��IP��ַ
     * 
     * @param ipAddr
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
 
    /**
     * Integer������ʽ ^-?(([1-9]\d*$)|0) 
     * @param REGEX_INTEGER
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isInteger(String integer) {
        return Pattern.matches(REGEX_INTEGER, integer);
    }
    
    /**
     * ������������ʽ >=0 ^[1-9]\d*|0$ 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isIntegerNegative(String integerNegative) {
        return Pattern.matches(REGEX_INTEGER_NEGATIVE, integerNegative);
    }
    
    /**
     * ������������ʽ <=0 ^-[1-9]\d*|0$ 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isIntegerPositive(String integerPositive) {
        return Pattern.matches(REGEX_INTEGER_POSITIVE, integerPositive);
    }
    
    /**
     * Double������ʽ ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$ 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isDouble(String doubleStr) {
        return Pattern.matches(REGEX_DOUBLE, doubleStr);
    }
    
    /**
     * ��Double������ʽ >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$��
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isDoubleNegative(String doubleNegative) {
        return Pattern.matches(REGEX_DOUBLE_NEGATIVE, doubleNegative);
    }
    
    
    /**
     * ��Double������ʽ <= 0  ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$ 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isDoublePositive(String doublePositive) {
        return Pattern.matches(REGEX_DOUBLE_POSITIVE, doublePositive);
    }
    
    /**
     * ����������ʽ ^(?:[1-9][0-9]?|1[01][0-9]|120)$ ƥ��0-120�� 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isAge(String age) {
        return Pattern.matches(REGEX_AGE, age);
    }
    
    /**
     * �ʱ�������ʽ  [0-9]\d{5}(?!\d) ����6λ�ʱ� 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isCode(String code) {
        return Pattern.matches(REGEX_CODE, code);
    }
    
    /**
     * ƥ�������֡�26��Ӣ����ĸ�����»�����ɵ��ַ��� ^\w+$ 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isStrEngNum_(String strEngNum_) {
        return Pattern.matches(REGEX_STR_ENG_NUM_, strEngNum_);
    }
    
    /**
     * ƥ�������ֺ�26��Ӣ����ĸ��ɵ��ַ��� ^[A-Za-z0-9]+$  
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isStrEngNum(String strEngNum) {
        return Pattern.matches(REGEX_STR_ENG_NUM, strEngNum);
    }
    
    /**
     * ƥ����26��Ӣ����ĸ��ɵ��ַ���  ^[A-Za-z]+$ 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isStrEng(String strEng) {
        return Pattern.matches(REGEX_STR_ENG, strEng);
    }
    
    /**
     * ���������ַ�������
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isStrSpecial(String strSpecial) {
        return Pattern.matches(REGEX_STR_SPECIAL, strSpecial);
    }
    /**
     * �������� ֧�֣� 
     *  YYYY-MM-DD  
     *  YYYY/MM/DD  
     *  YYYY_MM_DD  
     *  YYYYMMDD 
     *  YYYY.MM.DD����ʽ
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isDateAll(String dateAll) {
        return Pattern.matches(REGEX_DATE_ALL, dateAll);
    }
    /**
     * �������� ֧�֣� YYYY-MM-DD  
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isDateFormat(String dateFormat) {
        return Pattern.matches(REGEX_DATE_FORMAT, dateFormat);
    }
    /**
     *  ��������
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isMechanismCode(String mechanismCode) {
        return Pattern.matches(REGEX_MECHANISM_CODE, mechanismCode);
    }
    /**
     * ƥ��������ɵ��ַ���  ^[0-9]+$ 
     * @param 
     * @returnУ��ͨ������true�����򷵻�false
     */
    public static boolean isStrNum(String strNum) {
        return Pattern.matches(REGEX_STR_NUM, strNum);
    }
}
