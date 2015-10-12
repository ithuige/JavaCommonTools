/**
 * 
 */
package org.huige.tools.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author huige
 * 2015年10月12日 下午5:34:32
 * MD5和SHA加密算法
 */
public class MD5SHAUtil {
	/** 
	* 将摘要信息转换为相应的编码 
	* @param code 编码类型 
	* @param message 摘要信息 
	* @return 相应的编码字符串 
	*/  
	private static String Encode(String code,String message){  
	  MessageDigest md;  
	  String encode = null;  
	  try {  
	      md = MessageDigest.getInstance(code);  
	      byte[] byteArray = message.getBytes("UTF-8");
	      byte[] byteMsg = md.digest(byteArray);
	      StringBuffer hexValue = new StringBuffer();
	      for (int i = 0; i < byteMsg.length; i++) {
	          int val = ((int) byteMsg[i]) & 0xff;
	          if (val < 16) { 
	              hexValue.append("0");
	           }
	          hexValue.append(Integer.toHexString(val));
	       }
	      return hexValue.toString();

	  } catch (NoSuchAlgorithmException e) {  
	      e.printStackTrace();  
	  } catch (UnsupportedEncodingException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}  
	  return encode;  
	}
	/** 
	 * 将摘要信息转换成MD5编码 
	 * @param message 摘要信息 
	 * @return MD5编码之后的字符串 
	 */  
	public static String md5Encode(String message){  
	    return Encode("MD5",message);  
	}  
	/** 
	 * 将摘要信息转换成SHA编码 
	 * @param message 摘要信息 
	 * @return SHA编码之后的字符串 
	 */  
	public static String shaEncode(String message){  
	    return Encode("SHA",message);  
	}  
	/** 
	 * 将摘要信息转换成SHA-256编码 
	 * @param message 摘要信息 
	 * @return SHA-256编码之后的字符串 
	 */  
	public static String sha256Encode(String message){  
	    return Encode("SHA-256",message);  
	}  
	/** 
	 * 将摘要信息转换成SHA-512编码 
	 * @param message 摘要信息 
	 * @return SHA-512编码之后的字符串 
	 */  
	public static String sha512Encode(String message){  
	    return Encode("SHA-512",message);  
	}  
}
