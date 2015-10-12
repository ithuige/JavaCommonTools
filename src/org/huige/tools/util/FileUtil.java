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
 * �ļ�������  
 * ����������ʱ�ļ��У�ɾ����ʱ�ļ��еȲ�������  
 * @author poplar  
 *  
 */  
public class FileUtil {   
  
    /**
	 * �½��ļ���Ŀ¼
	 * 
	 * @param folderPath
	 *            String �� c:/fqf
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
			System.out.println("�½�Ŀ¼�������� ");
			e.printStackTrace();
		}
	}

	/**
	 * �½��ļ�
	 * 
	 * @param filePathAndName
	 *            String �ļ�·�������� ��c:/fqf.txt
	 * @param fileContent
	 *            String �ļ�����
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
			System.out.println("�½�Ŀ¼�������� ");
			e.printStackTrace();

		}

	}
	
    /**  
     * �ж��ļ��Ƿ����  
     * @param fileName  
     * @return  
     */  
    public static boolean isExistFile(String folder, String fileName) {   
        boolean bool = false;   
        bool = new File(folder, fileName).exists();   
        return bool;   
    }   
       
    /**  
     * �����ļ����Ƿ����  
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
     * ɾ���ļ���Ŀ¼�������ļ�
     * @param folder ��ʱ�ļ���·��  
     * @return trueΪɾ���ɹ���falseΪʧ��  
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
	 * ɾ���ļ� 
	 * @param   sPath    ��ɾ���ļ����ļ��� 
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
	 */  
	public static boolean deleteFile(String sPath) {  
	   boolean flag = false; 
	   File file;
	   String[] paths=sPath.split(",");
	   for(int i=0;i<paths.length;i++){
		    file = new File(paths[i]);  
		    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
		    if (file.isFile() && file.exists()) {
		        file.delete();  
		        flag = true;  
		    }
	   }
	    return flag;
	}

    /**  
     * ɾ���ļ�������������  
     * @param folederPath �ļ�������·��  
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
                    String msg = filePath + "�ļ�ɾ��ʧ��";   
                    bool = false;   
                }   
            }   
            if (temp.isDirectory()) {   
                deleteAllFile(folederPath + "/" + tempList[i]); //ɾ���ļ���������ļ�   
  
                deleteFolder(folederPath + "/" + tempList[i]);  //��ɾ�����ļ���   
  
            }   
        }   
        return bool;   
    }   
  
      
  
    /**  
     * ���ļ�·�����д���,��Ҫ���ڴ��ݹ�����·���´���һ���ļ���  
     *  
     * @param folder  
     * @return �����µ�·����  
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
     * �����ļ���  
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
     * �����ļ���������ļ������������ļ������б�
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
	 * ���ָ��ͼƬĿ¼���Ƿ��и�ͼƬ
	 * @param picName:ͼƬ����
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

	public static long getFileSizes(String filePath) throws Exception{//ȡ���ļ���С
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
            System.out.println("�ļ�������");
        }
       f=null;
        return s;
    }
	
	
	
	 public static String FormetFileSize(long fileS) {//ת���ļ���С
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
		 * ���Ƶ����ļ�
		 * 
		 * @param oldPath
		 *            String ԭ�ļ�·�� �磺c:/fqf.txt
		 * @param newPath
		 *            String ���ƺ�·�� �磺f:/fqf.txt
		 * @return boolean
		 */
		public static void copyFile(String oldPath, String newPath) {
			try {
				int bytesum = 0;
				int byteread = 0;
				File oldfile = new File(oldPath);
				if (oldfile.exists()) { // �ļ�����ʱ
					InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
					FileOutputStream fs = new FileOutputStream(newPath);
					byte[] buffer = new byte[1444];
					int length;
					while ((byteread = inStream.read(buffer)) != -1) {
						bytesum += byteread; // �ֽ��� �ļ���С
						System.out.println(bytesum);
						fs.write(buffer, 0, byteread);
					}
					inStream.close();
				}
			} catch (Exception e) {
				System.out.println("���Ƶ����ļ��������� ");
				e.printStackTrace();

			}

		}

		/**
		 * ���������ļ�������
		 * 
		 * @param oldPath
		 *            String ԭ�ļ�·�� �磺c:/fqf
		 * @param newPath
		 *            String ���ƺ�·�� �磺f:/fqf/ff
		 * @return boolean
		 */
		public void copyFolder(String oldPath, String newPath) {

			try {
				(new File(newPath)).mkdirs(); // ����ļ��в����� �������ļ���
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
					if (temp.isDirectory()) {// ��������ļ���
						copyFolder(oldPath + "/ " + file[i], newPath + "/ "
								+ file[i]);
					}
				}
			} catch (Exception e) {
				System.out.println("���������ļ������ݲ������� ");
				e.printStackTrace();

			}

		}

		/**
		 * �ƶ��ļ���ָ��Ŀ¼
		 * 
		 * @param oldPath
		 *            String �磺c:/fqf.txt
		 * @param newPath
		 *            String �磺d:/fqf.txt
		 */
		public void moveFile(String oldPath, String newPath) {
			copyFile(oldPath, newPath);
			deleteFile(oldPath);

		}

		/**
		 * �ƶ��ļ��е�ָ��Ŀ¼
		 * 
		 * @param oldPath
		 *            String �磺c:/fqf.txt
		 * @param newPath
		 *            String �磺d:/fqf.txt
		 */
		public void moveFolder(String oldPath, String newPath) {
			copyFolder(oldPath, newPath);
			deleteFolder(oldPath);

		}
} 