<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
String title = request.getParameter("title");
String body = request.getParameter("body");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"">
<title>게시물 수정</title>
</head>
<body>

	<h2>게시물 수정</h2>

	<div>
		번호 :
		<%=articleRow.get("id")%></div>
	<div>
		날짜 :
		<%=articleRow.get("regDate")%></div>
	<div>
		제목 :
		<%=articleRow.get("title")%><br>
		수정할 제목 :
		<input type="text" name="title" placeholder="제목">
	</div>
	<div>
		내용 :
		<%=articleRow.get("body")%><br>
		수정할 내용 :
		<input type="text" name="body" placeholder="내용"></div>
		
	<div>
		<input type="submit" value="전송">
		<input type="reset" value="취소">
	</div>

	<div><a style="color:green" href="list">리스트로 돌아가기</a></div>

</body>
</html>