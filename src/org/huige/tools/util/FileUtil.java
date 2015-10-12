package org.huige.tools.util;
import java.io.File;   
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;   

/**  
 * 文件操作类  
 * 包括产生临时文件夹，删除临时文件夹等操作方法  
 * @author poplar  
 *  
 */  
public class FileUtil {   
  
    /**
	 * 新建文件夹目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错 ");
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public void newFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

		} catch (Exception e) {
			System.out.println("新建目录操作出错 ");
			e.printStackTrace();

		}

	}
	
    /**  
     * 判断文件是否存在  
     * @param fileName  
     * @return  
     */  
    public static boolean isExistFile(String folder, String fileName) {   
        boolean bool = false;   
        bool = new File(folder, fileName).exists();   
        return bool;   
    }   
       
    /**  
     * 测试文件夹是否存在  
     * @param folder  
     * @return  
     */  
    public static boolean isExistFolder(String folder){   
        File file = new File(folder);   
        if (file.isDirectory()){   
            return true;   
        }else {   
            return false;   
        }   
    } 
    
    /**  
     * 删除文件夹目录下所有文件
     * @param folder 临时文件夹路径  
     * @return true为删除成功，false为失败  
     */  
    public static boolean deleteFolder(String folder) {   
        boolean bool = false;   
        try {   
            deleteAllFile(folder);   
            String filePath = folder;   
            filePath = filePath.toString();   
            File f = new File(filePath);   
            f.delete();   
            bool = true;   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return bool;   
    }   
    /** 
	 * 删除文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	   boolean flag = false; 
	   File file;
	   String[] paths=sPath.split(",");
	   for(int i=0;i<paths.length;i++){
		    file = new File(paths[i]);  
		    // 路径为文件且不为空则进行删除  
		    if (file.isFile() && file.exists()) {
		        file.delete();  
		        flag = true;  
		    }
	   }
	    return flag;
	}

    /**  
     * 删除文件夹下所有内容  
     * @param folederPath 文件夹完整路径  
     * @return  
     */  
    public static boolean deleteAllFile(String folederPath) {   
        boolean bool = false;   
        String filePath = null;   
        File file = new File(folederPath);   
        if (!file.exists()) {   
            return bool;   
        }   
        if (!file.isDirectory()) {   
            return bool;   
        }   
        String[] tempList = file.list();   
        File temp = null;   
        for (int i = 0; i < tempList.length; i++) {   
            if (folederPath.endsWith(File.separator)) {   
                temp = new File(folederPath + tempList[i]);   
                filePath = temp.getPath();   
            } else {   
                temp = new File(folederPath + File.separator + tempList[i]);   
            }   
            if (temp.isFile()) {   
                boolean b = temp.delete();   
                if (!b) {   
                    String msg = filePath + "文件删除失败";   
                    bool = false;   
                }   
            }   
            if (temp.isDirectory()) {   
                deleteAllFile(folederPath + "/" + tempList[i]); //删除文件夹里面的文件   
  
                deleteFolder(folederPath + "/" + tempList[i]);  //在删除空文件夹   
  
            }   
        }   
        return bool;   
    }   
  
      
  
    /**  
     * 对文件路径进行处理,主要是在传递过来的路径下创建一个文件夹  
     *  
     * @param folder  
     * @return 返回新的路径名  
     */  
    public static String fileDir(String folder, String folderName) {   
        String path = null;   
        if (!FileUtil.isExistFile(folder, folderName)) {   
            String fullPath = folder + folderName;   
            File f = new File(fullPath);   
            f.mkdir();   
            path = f.getPath();   
        }   
        return path;   
    }   
  
    /**  
     * 遍历文件夹  
     * @param file  
     */  
    public ArrayList refreshFileList(String strPath) {   
        ArrayList filelist = new ArrayList();   
        File dir = new File(strPath);   
        File[] files = dir.listFiles();   
        if (files == null) {   
            filelist = null;   
        }   
        for (int i = 0; i < files.length; i++) {   
            if (files[i].isDirectory()) {   
                refreshFileList(files[i].getAbsolutePath());   
            } else {   
                String strFileName = files[i].getAbsolutePath().toLowerCase();   
                filelist.add(files[i].getAbsolutePath());   
                return filelist;   
            }   
        }   
        return filelist;   
    }   
    
    /**
     * 遍历文件夹里面的文件，返回所以文件名的列表
     * @param f
     * @return
     */
    public static ArrayList<String> getFileName(String filePath){
		File file = new File(filePath);
		File[] fs = file.listFiles();
		ArrayList<String> array=new ArrayList<String>();
		for (int i = 0; i < fs.length; i++) {
			if (".svn".equals(fs[i].getName())) {
				continue;
			}
			if (fs[i].isFile()) {
				array.add(fs[i].getName().toString());
			}
		}
		return array;
	}
    
    /**
	 * 检查指定图片目录下是否含有该图片
	 * @param picName:图片名称
	 * @param request
	 * @return
	 */
	public boolean getIsHavePicFile(String path){
		boolean flag=false;
		try {
			File file=new File(path); 
			flag=file.exists(); 
		} catch (Exception e) {} 
		return flag;
	}

	public static long getFileSizes(String filePath) throws Exception{//取得文件大小
        long s=0;
        File f = new File(filePath);
       if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
           s= fis.available();
           fis.close();
           fis=null;
        } else {
           //f.createNewFile();
            System.out.println("文件不存在");
        }
       f=null;
        return s;
    }
	
	
	
	 public static String FormetFileSize(long fileS) {//转换文件大小
	        DecimalFormat df = new DecimalFormat("#.00");
	        String fileSizeString = "";
	        if (fileS < 1024) {
	            fileSizeString = df.format((double) fileS) + "B";
	        } else if (fileS < 1048576) {
	            fileSizeString = df.format((double) fileS / 1024) + "K";
	        } else if (fileS < 1073741824) {
	            fileSizeString = df.format((double) fileS / 1048576) + "M";
	        } else {
	            fileSizeString = df.format((double) fileS / 1073741824) + "G";
	        }
	        return fileSizeString;
	    }
	
	 /**
		 * 复制单个文件
		 * 
		 * @param oldPath
		 *            String 原文件路径 如：c:/fqf.txt
		 * @param newPath
		 *            String 复制后路径 如：f:/fqf.txt
		 * @return boolean
		 */
		public static void copyFile(String oldPath, String newPath) {
			try {
				int bytesum = 0;
				int byteread = 0;
				File oldfile = new File(oldPath);
				if (oldfile.exists()) { // 文件存在时
					InputStream inStream = new FileInputStream(oldPath); // 读入原文件
					FileOutputStream fs = new FileOutputStream(newPath);
					byte[] buffer = new byte[1444];
					int length;
					while ((byteread = inStream.read(buffer)) != -1) {
						bytesum += byteread; // 字节数 文件大小
						System.out.println(bytesum);
						fs.write(buffer, 0, byteread);
					}
					inStream.close();
				}
			} catch (Exception e) {
				System.out.println("复制单个文件操作出错 ");
				e.printStackTrace();

			}

		}

		/**
		 * 复制整个文件夹内容
		 * 
		 * @param oldPath
		 *            String 原文件路径 如：c:/fqf
		 * @param newPath
		 *            String 复制后路径 如：f:/fqf/ff
		 * @return boolean
		 */
		public void copyFolder(String oldPath, String newPath) {

			try {
				(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
				File a = new File(oldPath);
				String[] file = a.list();
				File temp = null;
				for (int i = 0; i < file.length; i++) {
					if (oldPath.endsWith(File.separator)) {
						temp = new File(oldPath + file[i]);
					} else {
						temp = new File(oldPath + File.separator + file[i]);
					}

					if (temp.isFile()) {
						FileInputStream input = new FileInputStream(temp);
						FileOutputStream output = new FileOutputStream(newPath
								+ "/ " + (temp.getName()).toString());
						byte[] b = new byte[1024 * 5];
						int len;
						while ((len = input.read(b)) != -1) {
							output.write(b, 0, len);
						}
						output.flush();
						output.close();
						input.close();
					}
					if (temp.isDirectory()) {// 如果是子文件夹
						copyFolder(oldPath + "/ " + file[i], newPath + "/ "
								+ file[i]);
					}
				}
			} catch (Exception e) {
				System.out.println("复制整个文件夹内容操作出错 ");
				e.printStackTrace();

			}

		}

		/**
		 * 移动文件到指定目录
		 * 
		 * @param oldPath
		 *            String 如：c:/fqf.txt
		 * @param newPath
		 *            String 如：d:/fqf.txt
		 */
		public void moveFile(String oldPath, String newPath) {
			copyFile(oldPath, newPath);
			deleteFile(oldPath);

		}

		/**
		 * 移动文件夹到指定目录
		 * 
		 * @param oldPath
		 *            String 如：c:/fqf.txt
		 * @param newPath
		 *            String 如：d:/fqf.txt
		 */
		public void moveFolder(String oldPath, String newPath) {
			copyFolder(oldPath, newPath);
			deleteFolder(oldPath);

		}
} 