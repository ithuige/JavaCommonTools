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
       
   // public static final String ZIP_FILENAME = "C:\\XJPDA.zip";//需要解压缩的文件名   
   // public static final String ZIP_DIR = "D:\\测试2\\1\\";//需要压缩的文件夹   
   // public static final String UN_ZIP_DIR = "C:\\";//要解压的文件目录   
    public static final int BUFFER = 1024 ;//缓存大小   
       
    /**  
    * zip压缩单个文件.
    *   
    * zipFile：单个文件的路径  “C:\\text\\text.txt”
    * zipDir：压缩包里的文件夹名称“myziptext”
    * fileName:压缩后的压缩文件名  “text.zip”
    * @throws Exception  
    */  
    public static boolean zipFile(String zipFile,String zipDir,String fileName){  
    	List fileList = new ArrayList();  
    	
    	
    	if(zipFile.indexOf(".")>0){
    		File zf = new File(zipFile);
    		fileList.add(zf);
    	}else{
    		//不是文件，调用压缩文件夹及子目录的方法
    		return zipFiles(zipFile,zipDir,fileName);
    	}  
        ZipOutputStream zos=null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("压缩后的文件不存在，zip自动创建");
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
			System.out.print("文件读取错误");
			return false;
		}  
        return true;
    } 
    
    /**  
     * zip压缩功能.  
     * 压缩baseDir(文件夹目录)下所有文件，包括子目录  
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
			System.out.println("文件未找到");
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
			System.out.print("文件读取错误");
			return false;
		}  
        return true;
    }
       
    /**  
    * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.  
    * @param baseDir java.lang.String 根目录  
    * @param realFileName java.io.File 实际的文件名  
    * @return 相对文件名  
    */  
    private static String getAidunFileName(String baseDir,String zipDir, File realFileName){   
        File real=realFileName;   
        File base=new File(baseDir);   
        String ret=real.getName();   
        return zipDir+"/"+ret;   //为艾顿pos压缩特别设置的
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
    * 取得指定目录下的所有文件列表，包括子目录.  
    * @param baseDir File 指定的目录  
    * @return 包含java.io.File的List  
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
    * 解压缩功能.  
    * 将ZIP_FILENAME文件解压到ZIP_DIR目录下.  
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
    * 给定根目录，返回一个相对路径所对应的实际文件名.  
    * @param baseDir 指定根目录  
    * @param absFileName 相对路径名，来自于ZipEntry中的name  
    * @return java.io.File 实际的文件  
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