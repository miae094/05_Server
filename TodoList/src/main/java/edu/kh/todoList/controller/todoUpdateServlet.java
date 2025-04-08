package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/update")
public class todoUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			
			// 할 일 수정
			
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			
			
			TodoListService service = new TodoListServiceImpl();
			Todo todo = service.todoDetail(todoNo);
			
			int result = service.todoUpdate(todo);
			
			HttpSession session = req.getSession();
			
			// 성공시 redirect
			if(result > 0) {
				session.setAttribute("message", "할 일이 수정되었습니다!");
				resp.sendRedirect("todo/detail?todoNo=" + todoNo);
				
				return;
			}
			
			session.setAttribute("message", "수정에 실패했습니다.");
			resp.sendRedirect("/todo/detail?todoNo=" + todoNo);
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
