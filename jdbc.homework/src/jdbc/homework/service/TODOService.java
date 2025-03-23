package jdbc.homework.service;

import java.sql.Connection;
import java.util.List;

import jdbc.homework.common.JDBCTemplate;
import jdbc.homework.dao.TODODAO;
import jdbc.homework.dto.TODO;

public class TODOService {

	private TODODAO todoDao = new TODODAO();

	public int insertUser(TODO todouser) throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		int result = todoDao.insertUser(conn, todouser);

		if (result > 0) {
			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);

		}

		return result;
	}

	public List<TODO> selectALL() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<TODO> todoList = todoDao.selectALL(conn);
		
		JDBCTemplate.close(conn);
		
		return todoList;
	}

	public List<TODO> selectName(String keyword) throws Exception {
	
		Connection conn = JDBCTemplate.getConnection();
		
		List<TODO> todoList = todoDao.selectName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return todoList;
	}

	public TODO selectUser(int input) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		TODO todo = todoDao.selectUser(conn, input);
		
		JDBCTemplate.close(conn);
		
		return todo;
	}

}
