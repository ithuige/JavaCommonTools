/**
 * 
 */
package org.huige.tools.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.huige.tools.util.DataBaseConnectionUtil;
import org.huige.tools.util.DateUtil;
import org.huige.tools.util.ExcelUtil;
import org.huige.tools.util.LunarCalendarUtil;
import org.huige.tools.util.RandomUtil;
import org.huige.tools.util.SortUtil;
import org.huige.tools.util.StringUtil;

/**
 * @author huige
 * 2015年10月11日 下午11:20:50
 *
 */
public class Demo {

	/**
	 * 
	 *@param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO 自动生成的方法存根
		int data[]={1,232,53,3,2,4,243,5,3,4,3,5,3,4,5,3,2,4,6543,2122,4,3};
//		SortUtil.getDefaultSort(data);
//		for(int i=0;i<data.length;i++){
//			System.out.print(data[i]+"  ");
//		}
		SortUtil.getSort(data,SortUtil.BUBBLE);
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+"  ");
		}
		System.out.println();
		System.out.println(RandomUtil.getRandomId(32));
		
		System.out.println(DateUtil.getNowDate());
		System.out.println(DateUtil.getNowTime());
		System.out.println(DateUtil.getNowDateTime());
		System.out.println(DateUtil.getNowDateTimeMillisecond());
		System.out.println(DateUtil.getDateAsFormatString(new Date(), "yyyy-MM-dd hh:mm:ss"));
		System.out.println(DateUtil.getDateFormString("20151012105021"));
		
		String excelPath="F:\\Book1.xls";
		try{
			List datas=ExcelUtil.ReadExcel(excelPath, 0, 0, 0);
			System.out.println(datas.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		String str = "!!!!!!!!!!";
//		String newStr = StringUtils.replace(str,1,2,"@@");
//		System.out.println(newStr);
		BigDecimal val = new BigDecimal(0);
		BigDecimal val1 = new BigDecimal(-0.00);
		BigDecimal val2 = new BigDecimal(-2);
		System.out.println(StringUtil.formatBigDecimal(val));
		System.out.println(StringUtil.formatBigDecimal(val2));
		
		/*	   String basePath="C\\dat.pos";
		   String fileName="D:\\测试2\\1\\POS传输.ZIP";
		   try {
			System.out.print(zipFile(basePath,"POS传输",fileName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(LunarCalendarUtil.getLunar(new Date()));
		
		Connection con = DataBaseConnectionUtil.getConnectionToMsSql();
		if (con != null) {
			System.out.println("mssql数据库连接成功！");
		} else {
			System.out.println("mssql数据库连接失败！");
		}

		Connection con2 = DataBaseConnectionUtil.getConnectionToMySql();
		if (con2 != null) {
			System.out.println("mysql数据库连接成功！");
		} else {
			System.out.println("mysql数据库连接失败！");
		}
	}

}
