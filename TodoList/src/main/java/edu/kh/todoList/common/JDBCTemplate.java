package edu.kh.todoList.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/*
 * Template : 양식, 틀, 주형
 *  -> "미리 만들어뒀다" 의미
 * 
 * JDBCTemplate :
 * JDBC 관련 작업을 위한 코드를
 * 미리 작성해서 제공하는 클래스
 * 
 * - Connection 생성
 * - AutoCommit false
 * - commit / rollback
 * - 각종 자원 반환 close()
 * 
 * ********** 중요 **********
 * 어디서든지 JDBCTemplate 클래스를
 * 객체로 만들지 않고도 메서드를 사용할 수 있도록 하기 위해
 * 모든 메서드를 public static 으로 선언
 *  -> 싱글톤 패턴 적용
 * 
 * */


public class JDBCTemplate {
	
	// 필드
	private static Connection conn = null;
	// -> static 메서드에서 사용할 static 필드
	
	
	// 메서드
	/** 호출 시 Connection 객체를 생성해서 반환하는 메서드 + AutoCommit 끄기
	 * @return conn
	 */
	public static Connection getConnection() {
		
		try {
			
			// 이전에 참조하던 Connection 객체가 존재하고
			// 아직 close() 된 상태가 아니라면
			// 새로 만들지 않고 기존 Connection 반환
			if (conn != null && !conn.isClosed()) {
				return conn;
			}
			
			// 1. Properties 객체 생성
			Properties prop = new Properties();
			
			// 2. Properties 가 제공하는 메서드를 이용해서 driver.xml 파일 내용을 읽어오기
			// src/main/resources 경로상에 위치한 driver.xml 파일 읽어오기
			String filePath = JDBCTemplate.class.getResource("/xml/driver.xml").getPath();
			//JDBCTemplate.class.getResource("경로"); 
			// * 클래스패스(classpath ) 내에서 지정된 리소스 파일을 찾는 메서드
			
			// * classPath?
			// Java 프로그램이 클래스를 찾기 위해 검색하는 경로를 의미
			// -> src/main/resources 또는 WEB-INF / classes 에서 찾음

			// getPath() : URL 객체에서 실제 파일 시스템의 경로 중 절대경로 방법으로 얻어옴.
			
			System.out.println("filePath: " + filePath);
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			// 3. prop 에 저장된 값을 이용해서 Connection 객체 생성
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("userName"), prop.getProperty("password"));
			
			// 4. 만들어진 Connection 에서 AutoCommit 끄기
			conn.setAutoCommit(false);
			
		} catch (Exception e) {
			System.out.println("커넥션 생성 중 예외 발생ㅠㅠ");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	//-------------------------------------------------------------------------
	
	
	/** 전달 받은 커넥션에서 수행한 SQL을 Commit 하는 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if( conn != null && !conn.isClosed()) conn.commit();
			
		} catch (Exception e) {
			System.out.println("커밋 중 예외발생");
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/** 전달 받은 커넥션에서 수행한 SQL을 ROLLBACK 하는 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if( conn != null && !conn.isClosed()) conn.rollback();
			
		} catch (Exception e) {
			System.out.println("롤백 중 예외발생");
			e.printStackTrace();
		}
		
	}
	
	
	//-------------------------------------------------------------------------	
	
	// 커넥션, Statement(PreparedStatement), ResultSet
	/** 전달받은 커넥션을 close(자원반환) 하는 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if( conn != null && !conn.isClosed()) conn.rollback();
			
		} catch (Exception e) {
			System.out.println("롤백 중 예외발생");
			e.printStackTrace();
		}
	}
	
	
	/** 전달 받은 Statement or PreparedStatement 둘 다 close()하는 메서드
	 *  + 다형성의 업캐스팅 적용
	 *  -> PreparedStatement 는 Statmement 의 자식
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		
		try {
			if( stmt != null && !stmt.isClosed()) stmt.close();
			
		} catch (Exception e) {
			System.out.println("Statement close() 중 예외발생");
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		
		try {
			if( rs != null && !rs.isClosed()) rs.close();
			
		} catch (Exception e) {
			System.out.println("Resultset close() 중 예외발생");
			e.printStackTrace();
		}
	}
	

}
