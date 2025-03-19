package jdbc.homework.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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
}
