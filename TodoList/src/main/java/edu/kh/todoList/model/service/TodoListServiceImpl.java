package edu.kh.todoList.model.service;

import static edu.kh.todoList.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dao.TodoListDAO;
import edu.kh.todoList.model.dao.TodoListDAOImpl;
import edu.kh.todoList.model.dto.Todo;

public class TodoListServiceImpl implements TodoListService {

	private TodoListDAO dao = new TodoListDAOImpl();
	
	@Override
	public Map<String, Object> todoListFullView() {
		
		// 커넥션 생성
		Connection conn = getConnection();
		
		// dao 호출 및 반환받기
		// 1) 할 일 목록 얻어오기
		List<Todo> TodoList = dao.todoListFullView(conn);
		
		
		// 2) 완료된 할 일 개수 카운드
		int completeCount = dao.getCompleteCount(conn);

		
		
		return null;
	}

}
