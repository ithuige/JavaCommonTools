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
 * 2015��10��12�� ����9:19:00
 * ��ȡ��д��TXT�ļ�������
 */
public class ReadWriteTxtUtil {
	/**
	 *  ��TXT�ı��ļ��ж�ȡ���� 
	 * 
	 *@param filePath ��ȡ�ļ�·��
	 *@return
	 */
	public static String readTxtFile(String filePath) {
		StringBuilder sb=new StringBuilder();
		BufferedReader bufread =null;
		String temp="";
		try {
			File file = new File(filePath);// �õ��ı��ļ���·��
			FileReader fileRead = new FileReader(file);
			bufread = new BufferedReader(fileRead);
			while ((temp=bufread.readLine()) != null) {
				sb.append(temp+"\r\n");
			}
		} catch (Exception e) {
			System.out.println("ReadWriteTxtUtil��readTxtFile������ȡ�ļ�["+filePath+"]���ִ���"+e.toString());
		}finally{
			if(bufread!=null){
				try {
					bufread.close();
				} catch (IOException e) {
					System.out.println("ReadWriteTxtUtil��readTxtFile�����ر�BufferedReader���ִ���"+e.toString());
				}
			}
		}
		return sb.toString(); 
	}

	/**
	 *  ��TXT�ı��ļ���д������ 
	 * 
	 *@param filePath  д���ļ�·��
	 *@param content  д������
	 *@param append ���Ϊ true�����ֽ�д���ļ�ĩβ����������д���ļ���ʼ�� 
	 */
	public static void writeTxtFile(String filePath, String content, boolean append) {
		FileWriter filewriter =null;
		try {
			File writefile = new File(filePath);
			if (writefile.exists() == false) // ����ı��ļ��������򴴽���
			{
				writefile.createNewFile();
				writefile = new File(filePath); //ʵ����
			}
			filewriter = new FileWriter(writefile, append);
			// д���µ��ļ����� 
			filewriter.write(content);
			
			filewriter.flush();
		} catch (Exception e) {
			System.out.println("ReadWriteTxtUtil��writeTxtFile����д�ļ�["+filePath+"]���ִ���"+e.toString());
		}finally{
			if(filewriter!=null){
				try {
					filewriter.close();
				} catch (IOException e) {
					System.out.println("ReadWriteTxtUtil��writeTxtFile�����ر�FileWriter���ִ���"+e.toString());
				}
			}
		}
	}
}
