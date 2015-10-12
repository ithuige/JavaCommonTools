/**
 * 
 */
package org.huige.tools.util;

import java.util.regex.Pattern;
/**
 * @author huige
 * 2015年10月12日 下午4:09:28
 *	正则表达式验证常用检验数据
 */
public class RegularUtil {
	/**
     * 正则表达式：验证用户名
     */
    private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
 
    /**
     * 正则表达式：验证手机号
     */
    private static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
 
    /**
     * 正则表达式：验证邮箱
     */
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
 
    /**
     * 正则表达式：验证汉字
     */
    private static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
 
    /**
     * 正则表达式：验证IP地址
     */
    private static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
 
    /** 
     * Integer正则表达式 ^-?(([1-9]\d*$)|0) 
     */
    private static final String  REGEX_INTEGER = "^-?(([1-9]\\d*$)|0)";  
    /** 
     * 正整数正则表达式 >=0 ^[1-9]\d*|0$ 
     */
    private static final String  REGEX_INTEGER_NEGATIVE = "^[1-9]\\d*|0$";  
    /** 
     * 负整数正则表达式 <=0 ^-[1-9]\d*|0$ 
     */
    private static final String  REGEX_INTEGER_POSITIVE = "^-[1-9]\\d*|0$";      
    /** 
     * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$ 
     */
    private static final String  REGEX_DOUBLE ="^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";  
    /** 
     * 正Double正则表达式 >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　 
     */
    private static final String  REGEX_DOUBLE_NEGATIVE ="^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";  
    /** 
     * 负Double正则表达式 <= 0  ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$ 
     */
    private static final String  REGEX_DOUBLE_POSITIVE ="^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";   
    /** 
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁 
     */
    private static final String  REGEX_AGE="^(?:[1-9][0-9]?|1[01][0-9]|120)$";  
    /** 
     * 邮编正则表达式  [0-9]\d{5}(?!\d) 国内6位邮编 
     */
    private static final String  REGEX_CODE="[0-9]\\d{5}(?!\\d)";    
    /** 
     * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$ 
     */
    private static final String REGEX_STR_ENG_NUM_="^\\w+$";  
    /** 
     * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$  
     */
    private static final String REGEX_STR_ENG_NUM="^[A-Za-z0-9]+";  
    /** 
     * 匹配由26个英文字母组成的字符串  ^[A-Za-z]+$ 
     */
    private static final String REGEX_STR_ENG="^[A-Za-z]+$";  
    /** 
     * 过滤特殊字符串正则 
     * regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
     */
    private static final String REGEX_STR_SPECIAL="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
    /*** 
     * 日期正则 支持： 
     *  YYYY-MM-DD  
     *  YYYY/MM/DD  
     *  YYYY_MM_DD  
     *  YYYYMMDD 
     *  YYYY.MM.DD的形式 
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
     * 日期正则 支持： 
     *  YYYY-MM-DD  
     */
    private static final String REGEX_DATE_FORMAT="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
     
    /** 
     * URL正则表达式 
      * 匹配 http www ftp 
     */
    private static final String REGEX_URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" +  
                                    "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" +  
                                    "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" +  
                                    "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";   
  
    /** 
     * 身份证正则表达式 
     */
    private static final String REGEX_IDCARD="((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +  
                                        "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +  
                                        "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
     
    /**
     * 机构代码
     */
    private static final String REGEX_MECHANISM_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";
     
    /**
     * 匹配数字组成的字符串  ^[0-9]+$ 
     */
    private static final String REGEX_STR_NUM = "^[0-9]+$";  
    /**
     * 校验用户名
     * 
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
 
 
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
 
    /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
 
    /**
     * 校验汉字
     * 
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }
 
    /**
     * 校验身份证
     * 
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_IDCARD, idCard);
    }
 
    /**
     * 校验URL
     * 
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
 
    /**
     * 校验IP地址
     * 
     * @param ipAddr
     * @return校验通过返回true，否则返回false
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
 
    /**
     * Integer正则表达式 ^-?(([1-9]\d*$)|0) 
     * @param REGEX_INTEGER
     * @return校验通过返回true，否则返回false
     */
    public static boolean isInteger(String integer) {
        return Pattern.matches(REGEX_INTEGER, integer);
    }
    
    /**
     * 正整数正则表达式 >=0 ^[1-9]\d*|0$ 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isIntegerNegative(String integerNegative) {
        return Pattern.matches(REGEX_INTEGER_NEGATIVE, integerNegative);
    }
    
    /**
     * 负整数正则表达式 <=0 ^-[1-9]\d*|0$ 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isIntegerPositive(String integerPositive) {
        return Pattern.matches(REGEX_INTEGER_POSITIVE, integerPositive);
    }
    
    /**
     * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$ 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isDouble(String doubleStr) {
        return Pattern.matches(REGEX_DOUBLE, doubleStr);
    }
    
    /**
     * 正Double正则表达式 >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isDoubleNegative(String doubleNegative) {
        return Pattern.matches(REGEX_DOUBLE_NEGATIVE, doubleNegative);
    }
    
    
    /**
     * 负Double正则表达式 <= 0  ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$ 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isDoublePositive(String doublePositive) {
        return Pattern.matches(REGEX_DOUBLE_POSITIVE, doublePositive);
    }
    
    /**
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isAge(String age) {
        return Pattern.matches(REGEX_AGE, age);
    }
    
    /**
     * 邮编正则表达式  [0-9]\d{5}(?!\d) 国内6位邮编 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isCode(String code) {
        return Pattern.matches(REGEX_CODE, code);
    }
    
    /**
     * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$ 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isStrEngNum_(String strEngNum_) {
        return Pattern.matches(REGEX_STR_ENG_NUM_, strEngNum_);
    }
    
    /**
     * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$  
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isStrEngNum(String strEngNum) {
        return Pattern.matches(REGEX_STR_ENG_NUM, strEngNum);
    }
    
    /**
     * 匹配由26个英文字母组成的字符串  ^[A-Za-z]+$ 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isStrEng(String strEng) {
        return Pattern.matches(REGEX_STR_ENG, strEng);
    }
    
    /**
     * 过滤特殊字符串正则
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isStrSpecial(String strSpecial) {
        return Pattern.matches(REGEX_STR_SPECIAL, strSpecial);
    }
    /**
     * 日期正则 支持： 
     *  YYYY-MM-DD  
     *  YYYY/MM/DD  
     *  YYYY_MM_DD  
     *  YYYYMMDD 
     *  YYYY.MM.DD的形式
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isDateAll(String dateAll) {
        return Pattern.matches(REGEX_DATE_ALL, dateAll);
    }
    /**
     * 日期正则 支持： YYYY-MM-DD  
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isDateFormat(String dateFormat) {
        return Pattern.matches(REGEX_DATE_FORMAT, dateFormat);
    }
    /**
     *  机构代码
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isMechanismCode(String mechanismCode) {
        return Pattern.matches(REGEX_MECHANISM_CODE, mechanismCode);
    }
    /**
     * 匹配数字组成的字符串  ^[0-9]+$ 
     * @param 
     * @return校验通过返回true，否则返回false
     */
    public static boolean isStrNum(String strNum) {
        return Pattern.matches(REGEX_STR_NUM, strNum);
    }
}
