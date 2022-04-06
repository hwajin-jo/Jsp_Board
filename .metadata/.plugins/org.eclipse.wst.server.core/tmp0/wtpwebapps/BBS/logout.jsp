<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/custom.css">

<title>영화리뷰 게시판</title>
</head>
 <script type="text/javascript">
 window.history.forward();
 function noBack(){window.history.forward();}
</script>

<body>
	 <%
		session.invalidate();	 	
	 %>
	 <script>
	 	location.href = 'main.jsp';
	 </script>
	 
</body>
</html>