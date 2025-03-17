package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {

	public static void main(String[] args) {

		// 아이디, 비밀번호, 이름을 입력받아
		// 아이디, 비밀번호가 일치하는 사용자의
		// 이름을 수정(UPDATE)

		// PreparedStatement 이용하기
		// COMMIT, ROLLBACK 처리하기
		// 성공 시 "수정 성공" 출력
		// 실패 시 "아이디 또는 비밀번호 불일치" 출력

		Connection conn = null;
		PreparedStatement pstmt = null;

		Scanner sc = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "kh";
			String password = "kh1234";

			conn = DriverManager.getConnection(url, userName, password);

			sc = new Scanner(System.in);

			System.out.print("아이디 : ");
			String userId = sc.nextLine();

			System.out.print("비밀번호 : ");
			String userPw = sc.nextLine();

			System.out.print("이름 : ");
			String uName = sc.nextLine();

			String sql = """
					UPDATE TB_USER SET USER_NAME = ?
					WHERE USER_ID = ?
					AND USER_PW = ?
					""";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, uName);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPw);

			conn.setAutoCommit(false);

			int result = pstmt.executeUpdate();

			if (result != 0) {
				System.out.println("수정 성공");
				conn.commit();
			} else {
				System.out.println("아이디 또는 비밀번호 불일치");
				conn.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

				if (sc != null)
					sc.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
