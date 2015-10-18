/**
 * 
 */
package org.huige.tools.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author huige
 * 2015年10月12日 下午9:19:00
 * 读取和写入TXT文件工具类
 */
public class ReadWriteTxtUtil {
	/**
	 *  从TXT文本文件中读取内容 
	 * 
	 *@param filePath 读取文件路径
	 *@return
	 */
	public static String readTxtFile(String filePath) {
		StringBuilder sb=new StringBuilder();
		BufferedReader bufread =null;
		String temp="";
		try {
			File file = new File(filePath);// 得到文本文件的路径
			FileReader fileRead = new FileReader(file);
			bufread = new BufferedReader(fileRead);
			while ((temp=bufread.readLine()) != null) {
				sb.append(temp+"\r\n");
			}
		} catch (Exception e) {
			System.out.println("ReadWriteTxtUtil类readTxtFile方法读取文件["+filePath+"]出现错误！"+e.toString());
		}finally{
			if(bufread!=null){
				try {
					bufread.close();
				} catch (IOException e) {
					System.out.println("ReadWriteTxtUtil类readTxtFile方法关闭BufferedReader出现错误！"+e.toString());
				}
			}
		}
		return sb.toString(); 
	}

	/**
	 *  向TXT文本文件中写入内容 
	 * 
	 *@param filePath  写入文件路径
	 *@param content  写入内容
	 *@param append 如果为 true，则将字节写入文件末尾处，而不是写入文件开始处 
	 */
	public static void writeTxtFile(String filePath, String content, boolean append) {
		FileWriter filewriter =null;
		try {
			File writefile = new File(filePath);
			if (writefile.exists() == false) // 如果文本文件不存在则创建它
			{
				writefile.createNewFile();
				writefile = new File(filePath); //实例化
			}
			filewriter = new FileWriter(writefile, append);
			// 写入新的文件内容 
			filewriter.write(content);
			
			filewriter.flush();
		} catch (Exception e) {
			System.out.println("ReadWriteTxtUtil类writeTxtFile方法写文件["+filePath+"]出现错误！"+e.toString());
		}finally{
			if(filewriter!=null){
				try {
					filewriter.close();
				} catch (IOException e) {
					System.out.println("ReadWriteTxtUtil类writeTxtFile方法关闭FileWriter出现错误！"+e.toString());
				}
			}
		}
	}
}
