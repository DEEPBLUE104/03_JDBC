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

		// 1) JDBC 객체 참조변수 선언 + 키보드 입력용 객체 sc 선언
		Connection conn = null;
		PreparedStatement pstmt = null;

		Scanner sc = null;

		try {
			// 2) Connection 객체 생성 (DriverManager를 통해서)
			// 2-1) OracleDriver 메모리에 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2-2) DB 연결정보 작성
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "kh";
			String password = "kh1234";

			conn = DriverManager.getConnection(url, userName, password);
			// 2. SQL 작성 + AutoCommit 끄기
			conn.setAutoCommit(false);

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
			
			// 4. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 5. ?에 알맞은 값 세팅
			pstmt.setString(1, uName);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPw);

			// 6. SQL 수행 후 결과값 반환받기
			// executeQuery() : SELECT 수행 후 ResultSet 반환
			// executeUpdate() : DML 수행 후 결과 행의 갯수 반환 (int)
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
			// 8. 사용 완료한 JDBC 객체 자원 반환
			try {
				if (pstmt != null)
					conn.close();

				if (sc != null)
					sc.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
