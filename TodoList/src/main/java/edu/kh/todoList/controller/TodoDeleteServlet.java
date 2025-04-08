package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/delete")
public class TodoDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));
			TodoListService service = new TodoListServiceImpl();
			
			int result = service.todoDelete(todoNo);
			
			HttpSession session = req.getSession();
			if(result > 0) {
				session.setAttribute("message", "할일이 삭제되었습니다");
				resp.sendRedirect("/");
				return;
			}
			
			session.setAttribute("message", "삭제에 실패했습니다");
			resp.sendRedirect("/todo/detail?todoNo=" + todoNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
