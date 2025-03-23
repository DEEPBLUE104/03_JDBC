package jdbc.homework.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	private static Connection conn = null;
	// static 메서드에서 사용할 static 필드 선언

	public static Connection getConnection() {

		try {

			if (conn != null && !conn.isClosed()) {
				return conn;
			}

			Properties prop = new Properties();

			String filePath = "driver.xml";

			prop.loadFromXML(new FileInputStream(filePath));

			Class.forName(prop.getProperty("driver"));

			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("userName"),
					prop.getProperty("password"));

			conn.setAutoCommit(false);

		} catch (Exception e) {
			System.out.println("커넥션 생성 중 예외 발생");
			e.printStackTrace();
		}

		return conn;

	}

	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
			
			
		}catch(Exception e) {
			System.out.println("커밋 중 예외 발생");
			e.printStackTrace();
		}
	}

	
	public static void rollback(Connection conn) {
			
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		}catch(Exception e) {
			System.out.println("롤백 중 예외발생");
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
			
		}catch(Exception e) {
			System.out.println("커넥션 close() 중 예외발생");
			e.printStackTrace();
		}
		
	}

	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed())
				stmt.close();
			
		}catch(Exception e) {
			System.out.println("Statement close() 중 예외 발생");
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rs) {
		
		try {
			if(rs != null && !rs.isClosed())
				rs.close();
			
		}catch(Exception e) {
			System.out.println("ResultSet close()중 예외 발생");
			e.printStackTrace();
		}
		
		
	}

}
