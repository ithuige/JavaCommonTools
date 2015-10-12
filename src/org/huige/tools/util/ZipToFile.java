package org.huige.tools.util;

import java.io.BufferedInputStream;   
import java.io.BufferedOutputStream;   
import java.io.File;   
import java.io.FileInputStream;   
import java.io.FileNotFoundException;
import java.io.FileOutputStream;   
import java.io.IOException;  
import java.io.InputStream;   
import java.io.OutputStream;   
import java.util.ArrayList;
import java.util.Enumeration;   
import java.util.List;
  
import java.util.zip.ZipEntry;   
import java.util.zip.ZipFile;   
import java.util.zip.ZipOutputStream;   
  
public class ZipToFile {   
       
   // public static final String ZIP_FILENAME = "C:\\XJPDA.zip";//��Ҫ��ѹ�����ļ���   
   // public static final String ZIP_DIR = "D:\\����2\\1\\";//��Ҫѹ�����ļ���   
   // public static final String UN_ZIP_DIR = "C:\\";//Ҫ��ѹ���ļ�Ŀ¼   
    public static final int BUFFER = 1024 ;//�����С   
       
    /**  
    * zipѹ�������ļ�.
    *   
    * zipFile�������ļ���·��  ��C:\\text\\text.txt��
    * zipDir��ѹ��������ļ������ơ�myziptext��
    * fileName:ѹ�����ѹ���ļ���  ��text.zip��
    * @throws Exception  
    */  
    public static boolean zipFile(String zipFile,String zipDir,String fileName){  
    	List fileList = new ArrayList();  
    	
    	
    	if(zipFile.indexOf(".")>0){
    		File zf = new File(zipFile);
    		fileList.add(zf);
    	}else{
    		//�����ļ�������ѹ���ļ��м���Ŀ¼�ķ���
    		return zipFiles(zipFile,zipDir,fileName);
    	}  
        ZipOutputStream zos=null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("ѹ������ļ������ڣ�zip�Զ�����");
		}   
//		zos.setEncoding("GBK");     
        ZipEntry ze=null;   
        byte[] buf=new byte[BUFFER];   
        int readLen=0;  
        try {
        for(int i = 0; i <fileList.size(); i++) {  
        	
            File f=(File)fileList.get(i);  
            ze=new ZipEntry(getAidunFileName(zipFile,zipDir, f));
            ze.setSize(f.length());   
            ze.setTime(f.lastModified());      
			zos.putNextEntry(ze);
            InputStream is;
			is = new BufferedInputStream(new FileInputStream(f)); 
            while ((readLen=is.read(buf, 0, BUFFER))!=-1) {   
                zos.write(buf, 0, readLen);   
            }   
            is.close();   
        	 
        }   
			zos.close();
        } catch (IOException e) {
			System.out.print("�ļ���ȡ����");
			return false;
		}  
        return true;
    } 
    
    /**  
     * zipѹ������.  
     * ѹ��baseDir(�ļ���Ŀ¼)�������ļ���������Ŀ¼  
     * @throws Exception  
     */  
    public static boolean zipFiles(String baseDir,String zipDir,String fileName){  
    	List fileList = new ArrayList();  
    	if(baseDir.indexOf(".")>0){
    		File f = new File(baseDir);
    		fileList.add(f);
    	}else{
    		fileList=getSubFiles(new File(baseDir)); 
    	}
          
        ZipOutputStream zos=null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("�ļ�δ�ҵ�");
		}   
//		zos.setEncoding("GBK");     
        ZipEntry ze=null;   
        byte[] buf=new byte[BUFFER];   
        int readLen=0;  
        try {
        for(int i = 0; i <fileList.size(); i++) {  
        	
            File f=(File)fileList.get(i);  
            ze=new ZipEntry(getAbsFileName(baseDir,zipDir, f));
            ze.setSize(f.length());   
            ze.setTime(f.lastModified());      
			zos.putNextEntry(ze);
            InputStream is;
			is = new BufferedInputStream(new FileInputStream(f)); 
            while ((readLen=is.read(buf, 0, BUFFER))!=-1) {   
                zos.write(buf, 0, readLen);   
            }   
            is.close();   
        	 
        }   
			zos.close();
        } catch (IOException e) {
			System.out.print("�ļ���ȡ����");
			return false;
		}  
        return true;
    }
       
    /**  
    * ������Ŀ¼��������һ���ļ��������·��������zip�ļ��е�·��.  
    * @param baseDir java.lang.String ��Ŀ¼  
    * @param realFileName java.io.File ʵ�ʵ��ļ���  
    * @return ����ļ���  
    */  
    private static String getAidunFileName(String baseDir,String zipDir, File realFileName){   
        File real=realFileName;   
        File base=new File(baseDir);   
        String ret=real.getName();   
        return zipDir+"/"+ret;   //Ϊ����posѹ���ر����õ�
    }   
    
    private static String getAbsFileName(String baseDir,String zipDir, File realFileName){   
        File real=realFileName;   
        File base=new File(baseDir);   
        String ret=real.getName();   
        while (true) {   
            real=real.getParentFile();   
            if(real==null)    
                break;   
            if(real.equals(base))    
                break;   
            else  
                ret=real.getName()+"/"+ret;   
        } 
        return ret;   
    } 
       
    /**  
    * ȡ��ָ��Ŀ¼�µ������ļ��б�������Ŀ¼.  
    * @param baseDir File ָ����Ŀ¼  
    * @return ����java.io.File��List  
    */  
    private static List getSubFiles(File baseDir){   
        List ret=new ArrayList();   
        File[] tmp=baseDir.listFiles();   
        for (int i = 0; i <tmp.length; i++) {   
            if(tmp[i].isFile())  {
            	System.out.print(tmp[i].getName());
            	//tmp[i].getName();
                ret.add(tmp[i]);
            }
            if(tmp[i].isDirectory())   
                ret.addAll(getSubFiles(tmp[i]));   
        }   
        return ret;   
    }   
       
    /**  
    * ��ѹ������.  
    * ��ZIP_FILENAME�ļ���ѹ��ZIP_DIRĿ¼��.  
    * @throws Exception  
    */  
/*    public static void upZipFile() throws Exception{   
        ZipFile zfile=new ZipFile(ZIP_FILENAME);   
        Enumeration zList=zfile.getEntries();   
        ZipEntry ze=null;   
        byte[] buf=new byte[1024];   
        while(zList.hasMoreElements()){   
            ze=(ZipEntry)zList.nextElement();          
            if(ze.isDirectory()){   
                File f=new File(ZIP_DIR+ze.getName());   
                f.mkdir();   
                continue;   
            }   
            OutputStream os=new BufferedOutputStream(new FileOutputStream(getRealFileName(ZIP_DIR, ze.getName())));   
            InputStream is=new BufferedInputStream(zfile.getInputStream(ze));   
            int readLen=0;   
            while ((readLen=is.read(buf, 0, 1024))!=-1) {   
                os.write(buf, 0, readLen);   
            }   
            is.close();   
            os.close();    
        }   
        zfile.close();   
    }*/   
  
    /**  
    * ������Ŀ¼������һ�����·������Ӧ��ʵ���ļ���.  
    * @param baseDir ָ����Ŀ¼  
    * @param absFileName ���·������������ZipEntry�е�name  
    * @return java.io.File ʵ�ʵ��ļ�  
    */  
    public static File getRealFileName(String baseDir, String absFileName){   
        String[] dirs=absFileName.split("/");   
        File ret=new File(baseDir);   
        if(dirs.length>1){   
            for (int i = 0; i < dirs.length-1;i++) {   
                ret=new File(ret, dirs[i]);   
            }   
            if(!ret.exists())   
                ret.mkdirs();   
            ret=new File(ret, dirs[dirs.length-1]);   
            return ret;   
        }   
        return ret;   
    }   
}