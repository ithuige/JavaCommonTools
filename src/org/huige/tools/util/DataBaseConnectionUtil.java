package org.huige.tools.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnectionUtil {
	private final static String MSSQL_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 驱动字符串
	private final static String MSSQL_URL = "jdbc:sqlserver://"; // 连接字符串
	private final static String MSSQL_PORT = "1433"; // 端口号
	private final static String MSSQL_USER = "sa"; // 数据库用户名

	private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver"; // 驱动字符串
	private final static String MYSQL_URL = "jdbc:mysql://"; // 连接字符串
	private final static String MYSQL_PORT = "3306"; // 端口号
	private final static String MYSQL_USER = "root"; // 数据库用户名

	private static final String ORACLE_URL = "jdbc:oracle:thin:@";// 连接字符串
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";// 驱动字符串
	private final static String ORACLE_PORT = "1521"; // 端口号
	private final static String ORACLE_USER = "scott"; // 数据库用户名
	
	private final static String HOST = "localhost"; // 连接到的主机IP
	private final static String DBNAME = "fhmis"; // 数据库名称
	private final static String PASSWORD = "huige"; // 数据库密码

	/**
	 *  建立与MS Sql server 数据库的连接
	 *@return
	 *@throws SQLException
	 */
	public static Connection getMsSqlConnection() throws SQLException {
		try {
			Class.forName(MSSQL_DRIVER).newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
		}
		return DriverManager.getConnection(MSSQL_URL + HOST + ":" + MSSQL_PORT + ";databaseName=" + DBNAME, MSSQL_USER,
				PASSWORD);
	}

	/**
	 *  建立与数据库 Mysql的连接
	 * 
	 *@return
	 *@throws SQLException
	 */
	public static Connection getMySqlConnection() throws SQLException {
		try {
			Class.forName(MYSQL_DRIVER).newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
		}
		return DriverManager.getConnection(MYSQL_URL + HOST + ":" + MYSQL_PORT + "/" + DBNAME, MYSQL_USER, PASSWORD);
	}
	/**
	 * 建立与oracle数据库的连接
	 * 
	 *@return
	 *@throws SQLException
	 */
	public static Connection getOracleConnection() throws SQLException {
		try {
			Class.forName(ORACLE_DRIVER);// 加载驱动
		} catch (ClassNotFoundException e) {}
		//jdbc:oracle:thin:@MyDbComputerNameOrIP:1521:ORCL
		return DriverManager.getConnection(ORACLE_URL + HOST+":"+ORACLE_PORT+":"+DBNAME, ORACLE_USER, PASSWORD);
	}
	/**
	 * 数据更新，删除，修改的方法--返回执行结果真假
	 * 
	 * @param sql
	 *            String //执行的SQL语句
	 * @return boolean //返回执行结果真假
	 * @throws java.lang.Exception
	 */
	public boolean setUpdate(String sql) throws Exception {
		Connection conn = getMySqlConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		if (pstmt != null) {
			pstmt.close();
			pstmt = null;
		}
		if (conn != null) {
			conn.close();
			conn = null;
		}
		return true;
	}

	/**
	 * 数据更新，删除，修改的方法--返回执行结果真假
	 * 
	 * @param conn
	 *            Connection //数据库链接
	 * @param sql
	 *            String //执行的SQL语句
	 * @return boolean //返回执行结果真假
	 * @throws java.lang.Exception
	 */
	public boolean setUpdate(String sql, Connection conn) throws Exception {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		if (pstmt != null) {
			pstmt.close();
			pstmt = null;
		}
		return true;
	}

	/**
	 * 数据更新，删除，修改的方法--返回成功条数
	 * 
	 * @param sql
	 *            String //执行的SQL语句
	 * @return 返回成功条数
	 */
	public int setUpdateCount(String sql) throws Exception {
		Connection conn = getMySqlConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int count = pstmt.executeUpdate();
		pstmt.executeUpdate();
		if (pstmt != null) {
			pstmt.close();
			pstmt = null;
		}
		if (conn != null) {
			conn.close();
			conn = null;
		}
		return count;
	}

	/**
	 * 数据更新，删除，修改的方法--返回成功条数
	 * 
	 * @param sql
	 *            String //执行的SQL语句
	 * @param conn
	 *            Connection //数据库链接
	 * @return 返回成功条数
	 * @throws java.lang.Exception
	 */
	public int setUpdateCount(String sql, Connection conn) throws Exception {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int count = pstmt.executeUpdate();
		if (pstmt != null) {
			pstmt.close();
			pstmt = null;
		}
		return count;
	}
}
