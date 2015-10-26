/**
 * 
 */
package org.huige.tools.test.threaddownloadfile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author huige
 * 2015��10��25�� ����11:12:46
 * д��ͱ����ļ�
 * �������Ҫ������򱾵ص�ָ���ļ�ָ�����ʼд���ļ��������ص�ǰд���ļ��ĳ��ȣ��ļ�ָ�룩������ཫ���̵߳��ã��ļ����ֳɶ�Ӧ�Ŀ�󣬽����̵߳��á�ÿ���̶߳�����������������ļ������д�롣
 */
public class SaveItemFile {
	//�洢�ļ�
    private RandomAccessFile itemFile;
    
    public SaveItemFile() throws IOException {
        this("", 0);
    }
    
    /**
     * @param name �ļ�·��������
     * @param pos д���λ�� position
     * @throws IOException
     */
    public SaveItemFile(String name, long pos) throws IOException {
        itemFile = new RandomAccessFile(name, "rw");
        //��ָ����posλ�ÿ�ʼд������
        itemFile.seek(pos);
    }
    
    /**
     * <b>function:</b> ͬ������д���ļ�
     * @author hoojo
     * @createDate 2011-9-26 ����12:21:22
     * @param buff ��������
     * @param start ��ʼλ��
     * @param length ����
     * @return
     */
    public synchronized int write(byte[] buff, int start, int length) {
        int i = -1;
        try {
            itemFile.write(buff, start, length);
            i = length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public void close() throws IOException {
        if (itemFile != null) {
            itemFile.close();
        }
    }
}
