<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
	<h1>${todo.todoTitle}</h1>

		<hr>
		
		<h4>할 일 수정</h4>
		<form action="/todo/update" method="post" id="addForm">
			<div>
				제목 : <input type="text" name="title" value=""${todo.todoTitle}"">
			</div>
			<div>
				<textarea rows="3" cols="50" name="detail" value="${todo.todoDetail}"></textarea>
			</div>
			<button>수정하기</button>
		</form>
	
	<hr>
</body>
</html>