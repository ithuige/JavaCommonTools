package org.huige.tools.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/**
 * ��ȡ��дExcel�Ĺ��ð�
 * @author huige
 *
 */
public class ExcelUtil {
	/**
	 * ��ȡExcelָ���п�ʼ��ָ���н�������Ϣ
	 * ע�⣺����Ҫ֪��Ҫ��ȡ��Excel�ĸ�ʽ��Ҫ��ȡ���е�ֵ�ķֲ����
	 * @param excelPath:Excel��·��
	 * @param beginReadLine����ʼ��ȡ���к�
	 * @param endReadLine����ȡ�������к�,�����Ҫ�������ʹ�0
	 * @param row��Ҫ��ȡ������
	 * @return �ַ������飬�������������飬ÿһ������ʱһ�е�����
	 */
	public static List ReadExcel(String excelPath, int beginReadLine,
			int endReadLine, int row) throws Exception {
		List datas = new ArrayList();// ���Excel������Ϣ������
		List tsDatas=new ArrayList();//��ʱ��ȡ��Excel���ݶ�ȡ��ֵ�����ܰ�����ֵ��
		Workbook book = null;
		Sheet sheet = null;
		Cell cell[] = new Cell[row];// ����һ��row�е�Cell����
		try {
			// ���ļ�,���ص�JXL��Workbook��
			book = Workbook.getWorkbook(new File(excelPath));
			// ��ȡ������Sheet��ʾ����Excel����·���Sheet(Excelͨ������3��Sheet)
			sheet = book.getSheet(0);
			if (endReadLine == 0){
				// �õ�������
				endReadLine = sheet.getRows();
			}
			if(row==0){
				cell=new Cell[sheet.getColumns()];
			}
			// ѭ����ȡ��Ԫ���������֪��Excelÿһ�е�����
			for (int i = beginReadLine; i < endReadLine; i++) {
				List list = new ArrayList();
				for (int j = 0; j < cell.length; j++) {
					cell[j] = sheet.getCell(j, i);// ��ȡ��i�е�j�е�ֵ����
					list.add(cell[j].getContents().toString().trim());
				}
				tsDatas.add(list);
			}
			//ȥ���������ݶ�Ϊ�յ���
			for(int m=0;m<tsDatas.size();m++){
				List list = (ArrayList)tsDatas.get(m);
				boolean isNull=true;
				for(int n=0;n<list.size();n++){
					if(list.get(n).toString().trim()!=null && list.get(n).toString().trim().length()>0){
						isNull=false;
						break;
					}
				}
				if(!isNull){
					datas.add(list);
				}
			}
			
		} catch (FileNotFoundException e) {
			throw new Exception("�ļ�û���ҵ���" + e);
		} catch (IOException e) {
			throw new Exception("��ȡ�ļ�ʱ����IO��������" + e);
		} catch (BiffException e) {
			throw new Exception("��ȡ�ļ�ʱ��������" + e);
		} finally {
			if (book != null)
				book.close();
		}
		return datas;
	}
	/**
	 * дExcel
	 * �����������ʾ����û���޸ģ�Ҫʹ�������޸�
	 */
	public static void WriteExcel(){
		String targetFile = "F:\\Work\\demo.xls"; // �����excel�ļ���·��
		String workSheet = "testjxl"; // �����excel�ļ���������,���·���Sheet����
		String[] title = { "aaa", "bbb", "ccc" }; // excel������ı���

		WritableWorkbook workbook;// ������д���excel����book
		try {

			System.out.println("begin");

			OutputStream os = new FileOutputStream(targetFile);//�ַ���
			workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet(workSheet, 0); // ��ӵ�һ��������
			//����ӵڶ�������

			jxl.write.Label label;
			for (int i = 0; i < title.length; i++) {
				// Label(�к�,�к� ,���� )
				label = new jxl.write.Label(i, 0, title[i]); // put the title in
																// row1
				sheet.addCell(label);
			}

			// ������ӵĶ�����ȵ����þ�����ͨ���������ο���

			// �������Number(�к�,�к�,����)
			jxl.write.Number number = new jxl.write.Number(0, 1, 3.14159); // put
			sheet.addCell(number);

			// ��Ӵ�������Formatting�Ķ���
			jxl.write.WritableFont wf = new jxl.write.WritableFont(
					WritableFont.TIMES, 10, WritableFont.BOLD, true);
			jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(
					wf);
			jxl.write.Label labelCF = new jxl.write.Label(4, 4, "�ı�", wcfF);
			sheet.addCell(labelCF);

			// ��Ӵ���������ɫ,��������ɫ Formatting�Ķ���
			jxl.write.WritableFont wfc = new jxl.write.WritableFont(
					WritableFont.ARIAL, 10, WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.RED);
			jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
					wfc);
			wcfFC.setBackground(jxl.format.Colour.BLUE);
			jxl.write.Label labelCFC = new jxl.write.Label(1, 5, "����ɫ", wcfFC);
			sheet.addCell(labelCFC);

			// ��Ӵ���formatting��Number����
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");
			jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(
					nf);
			jxl.write.Number labelNF = new jxl.write.Number(1, 8, 3.1415926,
					wcfN);
			sheet.addCell(labelNF);

			// 3.���Boolean����
			jxl.write.Boolean labelB = new jxl.write.Boolean(0, 2, false);
			sheet.addCell(labelB);

			// 4.���DateTime����
			jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3,
					new java.util.Date());
			sheet.addCell(labelDT);

			// ��Ӵ���formatting��DateFormat����
			jxl.write.DateFormat df = new jxl.write.DateFormat(
					"ddMMyyyyhh:mm:ss");
			jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(
					df);
			jxl.write.DateTime labelDTF = new jxl.write.DateTime(1, 3,
					new java.util.Date(), wcfDF);
			sheet.addCell(labelDTF);

			// �ϲ���Ԫ��
			// sheet.mergeCells(int col1,int row1,int col2,int row2);//���Ͻǵ����½�
			sheet.mergeCells(4, 5, 8, 10); // ���Ͻǵ����½�(4,5),(8,10)�ֱ�Ϊ���ϽǺ����½ǵ�����
			wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 40,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.GREEN);
			jxl.write.WritableCellFormat wchB = new jxl.write.WritableCellFormat(
					wfc);
			wchB.setAlignment(jxl.format.Alignment.CENTRE);
			labelCFC = new jxl.write.Label(4, 5, "��Ԫ�ϲ�", wchB);
			sheet.addCell(labelCFC); //

			// ���ñ߿�
			jxl.write.WritableCellFormat wcsB = new jxl.write.WritableCellFormat();
			wcsB.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THICK);
			labelCFC = new jxl.write.Label(0, 6, "�߿�����", wcsB);
			sheet.addCell(labelCFC);
			
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
