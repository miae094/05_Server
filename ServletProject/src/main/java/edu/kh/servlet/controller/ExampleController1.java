package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * jakarta.servlet.http.HttpServlet
 *
 * - Http 프로토콜 서비스를 지원하는 추상 클래스
 * -> 상속 받아서 사용
 * 
 * [Servlet 등록 절차]
 * 1. web.xml 에 직접 작성하는 방법
 * 2. @WebServlet() 어노테이션 이용 방법
 * 
 * */

// "/example1" 이라는 클라이언트의 요청을 받아서 처리한 후 응답을 해줄
// 서블릿 클래스를 만든다

// Controller : 요청(Request)에 따라 어떤 Service 를 호출할 지 "제어"하고,
//				어떻게 응답(Response)을 할지 "제어"하는 역할.
public class ExampleController1 extends HttpServlet {
	
	// doGet()메서드
	// -> Get 방식 요청을 처리하는 메서드
	// HttpServlet 의 메서드를 오버라이딩하여 하용
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// HttpServletRequest
		// - 클라이언트 요청 시 생성되는 객체
		// - 요청 시 전달된 데이터, 요청한 클라이언트의 정보,
		// 유연한 요청 처리를 하기 위한 객체를 제공함
		
		// HttpServletResponse
		// - 클라이언트가 요청시 생성되는 객체 (request와 같이생김)
		// 서버가 클라이언트에게 응답하기 위한 방법을 제공함
		// 서버 -> 클라이언트로 연결된 출력용 스트링을 제공(PrintWriter)
		
		
		System.out.println("--- 이름, 나이를 입력받아 처리하는 서블릿 코드 ---");

		// 요청시 입력된 이름, 나이를 전달받아오기
		
		// Parameter : 매개변수 == 다른곳의 값을 전달받아올 때 사용 
		
		// req.getParameter("name 속성값");
		// -> 요청시 전달된 데이터 중
		// name 속성값이 일치하는 데이터의 value 를 얻어와
		// String 형태로 반환
		
		// !!!!!!! HTML 에서 얻어오는 모든 값은 String !!!!!!!
		
		String name = req.getParameter("inputName");
		String age = req.getParameter("inputAge");
		
		System.out.println("입력받은 이름 : " + name);
		System.out.println("입력받은 나이 : " + age);
		// http://localhost:8080/example1?inputName=김뫄뫄&inputAge=23
		
		// -------------------------------------------
		
		// 서버 -> 클라이언트 응답처리
		
		// 1) 서버에서 HTML 코드를 만들어 
		// 클라이언트에게 전달한다 == 응답(response)
		
		// 2) 클라이언트의 브라우저가 응답받은 HTML 코드를 해석하여
		// 화면에 출력해준다.
		
		// 서버 -> 클라이언트 응답하기
		// HttpServletResponse 객체 이용
		
		// 응답처리1 : 응답하는 문서의 형식과 문자 인코딩 지정
		resp.setContentType("text/html; charset=UTF-8");
		
		// 응답처리2 : 서버 -> 클라이언트로 연결되는 출력용 스트림 얻어오기
		PrintWriter out = resp.getWriter();
		
		// 응답처리3 : 출력할 HTML 코드 만들기
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		
		sb.append("<head>");
		sb.append("<title>서버 응답 페이지</title>");
		sb.append("</head>");
		
		sb.append("<body>");
		sb.append("<h1>응답 페이지 입니다.</h1>");
		sb.append("<p>입력받은 이름 : " + name);
		sb.append("<p>입력받은 나이 : " + age);
		sb.append("</body>");

		sb.append("</html>");
		
		// 응답처리4 :  출력 스트림을 이용해 클라이언트에게 HTML코드 출력(응답)
		out.write(sb.toString());
	}
	
}
