/**
 * 
 */
package org.huige.tools.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author huige
 * 2015��10��12�� ����5:34:32
 * MD5��SHA�����㷨
 */
public class MD5SHAUtil {
	/** 
	* ��ժҪ��Ϣת��Ϊ��Ӧ�ı��� 
	* @param code �������� 
	* @param message ժҪ��Ϣ 
	* @return ��Ӧ�ı����ַ��� 
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
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}  
	  return encode;  
	}
	/** 
	 * ��ժҪ��Ϣת����MD5���� 
	 * @param message ժҪ��Ϣ 
	 * @return MD5����֮����ַ��� 
	 */  
	public static String md5Encode(String message){  
	    return Encode("MD5",message);  
	}  
	/** 
	 * ��ժҪ��Ϣת����SHA���� 
	 * @param message ժҪ��Ϣ 
	 * @return SHA����֮����ַ��� 
	 */  
	public static String shaEncode(String message){  
	    return Encode("SHA",message);  
	}  
	/** 
	 * ��ժҪ��Ϣת����SHA-256���� 
	 * @param message ժҪ��Ϣ 
	 * @return SHA-256����֮����ַ��� 
	 */  
	public static String sha256Encode(String message){  
	    return Encode("SHA-256",message);  
	}  
	/** 
	 * ��ժҪ��Ϣת����SHA-512���� 
	 * @param message ժҪ��Ϣ 
	 * @return SHA-512����֮����ַ��� 
	 */  
	public static String sha512Encode(String message){  
	    return Encode("SHA-512",message);  
	}  
}
