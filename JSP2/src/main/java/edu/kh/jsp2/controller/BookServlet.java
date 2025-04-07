package edu.kh.jsp2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jsp2.dto.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/book/list")
public class BookServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 요청 처리
		
		// Book 으로 타입이 제한된 List 생성
		List<Book> bookList = new ArrayList<Book>();
		
		// bookList 애 데이터 추가(Service <-> DAO <-> DB)
		bookList.add(new Book("자바란 무엇인가", "둘리", 10000));
		bookList.add(new Book("HTML이란 무엇인가", "또치", 13000));
		bookList.add(new Book("발성의 기초", "욱", 18900));
		bookList.add(new Book("올바른 자세의 중요성", "김뫄뫄", 16000));
		bookList.add(new Book("당신이 계속 조는 이유", "매매", 17500));
		bookList.add(new Book("피곤, 당신과 평생 함께간다", "이솨솨", 12800));
		
		// bookList를 요청 위임된 JSP에서도 유지하여 사용할 수 있도록
		// request scope 객체에 속성으로 추가(세팅)
		req.setAttribute("bookList", bookList);
		
		// 응답 처리
		// JSP 요청 위임(webapp 폴더 기준)
		req.getRequestDispatcher("/WEB-INF/views/book/bookList.jsp").forward(req, resp);
	
	}

}
