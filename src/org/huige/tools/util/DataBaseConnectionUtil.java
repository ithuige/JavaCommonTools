package org.huige.tools.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnectionUtil {
	private final static String MSSQL_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // �����ַ���
	private final static String MSSQL_URL = "jdbc:sqlserver://"; // �����ַ���
	private final static String MSSQL_PORT = "1433"; // �˿ں�
	private final static String MSSQL_USER = "sa"; // ���ݿ��û���

	private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver"; // �����ַ���
	private final static String MYSQL_URL = "jdbc:mysql://"; // �����ַ���
	private final static String MYSQL_PORT = "3306"; // �˿ں�
	private final static String MYSQL_USER = "root"; // ���ݿ��û���

	private static final String ORACLE_URL = "jdbc:oracle:thin:@";// �����ַ���
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";// �����ַ���
	private final static String ORACLE_PORT = "1521"; // �˿ں�
	private final static String ORACLE_USER = "scott"; // ���ݿ��û���
	
	private final static String HOST = "localhost"; // ���ӵ�������IP
	private final static String DBNAME = "fhmis"; // ���ݿ�����
	private final static String PASSWORD = "huige"; // ���ݿ�����

	/**
	 *  ������MS Sql server ���ݿ������
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
	 *  ���������ݿ� Mysql������
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
	 * ������oracle���ݿ������
	 * 
	 *@return
	 *@throws SQLException
	 */
	public static Connection getOracleConnection() throws SQLException {
		try {
			Class.forName(ORACLE_DRIVER);// ��������
		} catch (ClassNotFoundException e) {}
		//jdbc:oracle:thin:@MyDbComputerNameOrIP:1521:ORCL
		return DriverManager.getConnection(ORACLE_URL + HOST+":"+ORACLE_PORT+":"+DBNAME, ORACLE_USER, PASSWORD);
	}
	/**
	 * ���ݸ��£�ɾ�����޸ĵķ���--����ִ�н�����
	 * 
	 * @param sql
	 *            String //ִ�е�SQL���
	 * @return boolean //����ִ�н�����
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
	 * ���ݸ��£�ɾ�����޸ĵķ���--����ִ�н�����
	 * 
	 * @param conn
	 *            Connection //���ݿ�����
	 * @param sql
	 *            String //ִ�е�SQL���
	 * @return boolean //����ִ�н�����
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
	 * ���ݸ��£�ɾ�����޸ĵķ���--���سɹ�����
	 * 
	 * @param sql
	 *            String //ִ�е�SQL���
	 * @return ���سɹ�����
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
	 * ���ݸ��£�ɾ�����޸ĵķ���--���سɹ�����
	 * 
	 * @param sql
	 *            String //ִ�е�SQL���
	 * @param conn
	 *            Connection //���ݿ�����
	 * @return ���سɹ�����
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
