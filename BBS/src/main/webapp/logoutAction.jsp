<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화리뷰 게시판</title>
</head>
<body>
	<%
		session.invalidate();
	%>
	<script type="text/javascript">
		location.href = 'main.jsp';
	</script>
</body>
</html>