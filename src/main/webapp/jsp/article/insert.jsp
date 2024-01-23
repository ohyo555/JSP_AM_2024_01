<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"">
<title>게시물 등록</title>
</head>
<body>

	<a href="../home/main">메인으로 이동</a>
	<from action = "">
		<div>
			제목 :
			<input type="text" name="title" placeholder="제목"><br></div>
		<div>
			내용 :
			<input type="text" name="body" placeholder="내용"></div>
			
		<div>
			<input type="submit" value="전송">
			<input type="reset" value="취소">
		</div>
	</from>
	
</body>
</html>