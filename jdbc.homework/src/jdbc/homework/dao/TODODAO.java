package jdbc.homework.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.homework.common.JDBCTemplate;
import jdbc.homework.dto.TODO;

public class TODODAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int insertUser(Connection conn, TODO todouser) throws Exception {
		
		int result = 0;
		
		try {
			String sql = """
					INSERT INTO TB_MEMBER
					VALUES(MEMBER_NO.NEXTVAL, ?, ?, ?, DEFAULT )
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, todouser.getUserId());
			pstmt.setString(2, todouser.getUserPw());
			pstmt.setString(3, todouser.getUserName());
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
			
		}
		
		return result;
	}

	public List<TODO> selectALL(Connection conn) throws Exception {
		
		List<TODO> todoList = new ArrayList<TODO>();
		
		try {
			String sql = """
						SELECT MEMBER_NO, MEMBER_ID, MEMBER_PW, MEMBER_NAME,
						TO_CHAR(ENROLL_DATE,'YYYY"년" MM"월" DD"일"') ENROLL_DATE
						FROM TB_MEMBER
						ORDER BY MEMBER_NO
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int userNo = rs.getInt("MEMBER_NO");
				String userId = rs.getString("MEMBER_ID");
				String userPw = rs.getString("MEMBER_PW");
				String userName = rs.getString("MEMBER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				TODO todo = new TODO(userNo,userId,userPw,userName,enrollDate);
				
				todoList.add(todo);
			}
			
			
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return todoList;
	}

	public List<TODO> selectName(Connection conn, String keyword) throws Exception {

		List<TODO> todoList = new ArrayList<TODO>();
		
		try {
			
			String sql = """
					SELECT MEMBER_NO, MEMBER_ID, MEMBER_PW, MEMBER_NAME,
					TO_CHAR(ENROLL_DATE,'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_MEMBER
					WHERE MEMBER_NAME LIKE '%' || ? || '%'
					ORDER BY MEMBER_NO
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int userNo = rs.getInt("MEMBER_NO");
				String userId = rs.getString("MEMBER_ID");
				String userPw = rs.getString("MEMBER_PW");
				String userName = rs.getString("MEMBER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");

				TODO todo = new TODO(userNo, userId, userPw, userName, enrollDate);
			
				todoList.add(todo);
			}
			
			}finally {
			
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
		}
		
		return todoList;
	}

	public TODO selectUser(Connection conn, int input) throws Exception{
	
		TODO todo = null;
			
		try {
			
			String sql = """
					SELECT MEMBER_NO, MEMBER_ID, MEMBER_PW, MEMBER_NAME,
					TO_CHAR(ENROLL_DATE,'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_MEMBER
					WHERE MEMBER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int userNo = rs.getInt("MEMBER_NO");
				String userId = rs.getString("MEMBER_ID");
				String userPw = rs.getString("MEMBER_PW");
				String userName = rs.getString("MEMBER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");

				todo = new TODO(userNo, userId, userPw, userName, enrollDate);
				
			}
			
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return todo;
	}

}
