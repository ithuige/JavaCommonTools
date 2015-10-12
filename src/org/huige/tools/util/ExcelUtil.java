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
 * 读取和写Excel的公用包
 * @author huige
 *
 */
public class ExcelUtil {
	/**
	 * 读取Excel指定行开始，指定行结束的信息
	 * 注意：必须要知道要读取的Excel的格式，要读取的行的值的分布情况
	 * @param excelPath:Excel的路径
	 * @param beginReadLine：开始读取的行号
	 * @param endReadLine：读取结束的行号,如果需要读到最后就传0
	 * @param row：要读取的列数
	 * @return 字符串数组，数组里面套数组，每一个数组时一行的数据
	 */
	public static List ReadExcel(String excelPath, int beginReadLine,
			int endReadLine, int row) throws Exception {
		List datas = new ArrayList();// 存放Excel数据信息的数组
		List tsDatas=new ArrayList();//暂时存取的Excel数据读取的值，可能包含空值行
		Workbook book = null;
		Sheet sheet = null;
		Cell cell[] = new Cell[row];// 定义一个row列的Cell数组
		try {
			// 打开文件,加载到JXL包Workbook中
			book = Workbook.getWorkbook(new File(excelPath));
			// 获取工作表，Sheet表示的是Excel表格下方的Sheet(Excel通常会有3个Sheet)
			sheet = book.getSheet(0);
			if (endReadLine == 0){
				// 得到总行数
				endReadLine = sheet.getRows();
			}
			if(row==0){
				cell=new Cell[sheet.getColumns()];
			}
			// 循环读取单元，这里必须知道Excel每一行的列数
			for (int i = beginReadLine; i < endReadLine; i++) {
				List list = new ArrayList();
				for (int j = 0; j < cell.length; j++) {
					cell[j] = sheet.getCell(j, i);// 获取第i行第j列的值对象
					list.add(cell[j].getContents().toString().trim());
				}
				tsDatas.add(list);
			}
			//去掉所有数据都为空的行
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
			throw new Exception("文件没有找到：" + e);
		} catch (IOException e) {
			throw new Exception("读取文件时发生IO操作错误：" + e);
		} catch (BiffException e) {
			throw new Exception("读取文件时发生错误：" + e);
		} finally {
			if (book != null)
				book.close();
		}
		return datas;
	}
	/**
	 * 写Excel
	 * 这个方法还是示例，没有修改，要使用自行修改
	 */
	public static void WriteExcel(){
		String targetFile = "F:\\Work\\demo.xls"; // 输出的excel文件的路径
		String workSheet = "testjxl"; // 输出的excel文件工作表名,即下方的Sheet名称
		String[] title = { "aaa", "bbb", "ccc" }; // excel工作表的标题

		WritableWorkbook workbook;// 创建可写入的excel工作book
		try {

			System.out.println("begin");

			OutputStream os = new FileOutputStream(targetFile);//字符流
			workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet(workSheet, 0); // 添加第一个工作表
			//可添加第二个工作

			jxl.write.Label label;
			for (int i = 0; i < title.length; i++) {
				// Label(列号,行号 ,内容 )
				label = new jxl.write.Label(i, 0, title[i]); // put the title in
																// row1
				sheet.addCell(label);
			}

			// 下列添加的对字体等的设置均调试通过，可作参考用

			// 添加数字Number(列号,行号,内容)
			jxl.write.Number number = new jxl.write.Number(0, 1, 3.14159); // put
			sheet.addCell(number);

			// 添加带有字型Formatting的对象
			jxl.write.WritableFont wf = new jxl.write.WritableFont(
					WritableFont.TIMES, 10, WritableFont.BOLD, true);
			jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(
					wf);
			jxl.write.Label labelCF = new jxl.write.Label(4, 4, "文本", wcfF);
			sheet.addCell(labelCF);

			// 添加带有字体颜色,带背景颜色 Formatting的对象
			jxl.write.WritableFont wfc = new jxl.write.WritableFont(
					WritableFont.ARIAL, 10, WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.RED);
			jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
					wfc);
			wcfFC.setBackground(jxl.format.Colour.BLUE);
			jxl.write.Label labelCFC = new jxl.write.Label(1, 5, "带颜色", wcfFC);
			sheet.addCell(labelCFC);

			// 添加带有formatting的Number对象
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");
			jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(
					nf);
			jxl.write.Number labelNF = new jxl.write.Number(1, 8, 3.1415926,
					wcfN);
			sheet.addCell(labelNF);

			// 3.添加Boolean对象
			jxl.write.Boolean labelB = new jxl.write.Boolean(0, 2, false);
			sheet.addCell(labelB);

			// 4.添加DateTime对象
			jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3,
					new java.util.Date());
			sheet.addCell(labelDT);

			// 添加带有formatting的DateFormat对象
			jxl.write.DateFormat df = new jxl.write.DateFormat(
					"ddMMyyyyhh:mm:ss");
			jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(
					df);
			jxl.write.DateTime labelDTF = new jxl.write.DateTime(1, 3,
					new java.util.Date(), wcfDF);
			sheet.addCell(labelDTF);

			// 合并单元格
			// sheet.mergeCells(int col1,int row1,int col2,int row2);//左上角到右下角
			sheet.mergeCells(4, 5, 8, 10); // 左上角到右下角(4,5),(8,10)分别为左上角和右下角的坐标
			wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 40,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.GREEN);
			jxl.write.WritableCellFormat wchB = new jxl.write.WritableCellFormat(
					wfc);
			wchB.setAlignment(jxl.format.Alignment.CENTRE);
			labelCFC = new jxl.write.Label(4, 5, "单元合并", wchB);
			sheet.addCell(labelCFC); //

			// 设置边框
			jxl.write.WritableCellFormat wcsB = new jxl.write.WritableCellFormat();
			wcsB.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THICK);
			labelCFC = new jxl.write.Label(0, 6, "边框设置", wcsB);
			sheet.addCell(labelCFC);
			
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
