<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="evaluation.Evaluation" %>
<%@ page import="evaluation.EvaluationDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="evaluation" class="evaluation.Evaluation" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화리뷰 게시판</title>
</head>
<body>
	 <%
	 	String userID = null;
	 	if(session.getAttribute("userID") != null){
	 		userID = (String) session.getAttribute("userID");
	 	}
	 	if(userID == null){
	 		PrintWriter script = response.getWriter();
	 		script.println("<script>");
			script.println("alert('로그인을 해주세요.')");
	 		script.println("location.href = 'login.jsp'");
	 		script.println("</script>");
	 	} 
	 	else{
		 	int bbsID = 0; 
		 	if (request.getParameter("bbsID") != null){
		 		bbsID = Integer.parseInt(request.getParameter("bbsID"));
		 	}
		 	if (bbsID == 0){
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('유효하지 않은 글입니다.')");
		 		script.println("location.href = 'login.jsp'");
		 		script.println("</script>");
		 	}
		 	
		 	int likeIt= 0;
			int badIt = 0;
			
			if (request.getParameter("likeit") != null){
				likeIt = Integer.parseInt(request.getParameter("likeit"));
			}
			if (request.getParameter("badit") != null){
				badIt = Integer.parseInt(request.getParameter("badit"));
			}
			
			EvaluationDAO evaluationDAO = new EvaluationDAO();
			ArrayList<Evaluation> list = evaluationDAO.whereList(bbsID, userID);
			int result = 0;
			
			// 평가를 한 적이 없을 때
			if (list.isEmpty()){
				result = evaluationDAO.write(bbsID, userID, likeIt, badIt);
			} // 다른 평가를 하고 싶을 때
			else if (likeIt == list.get(0).getLikeIt() && badIt == list.get(0).getBadIt()){
				result = evaluationDAO.delete(bbsID, userID);
			} // 평가를 지우고 싶을(원래눌렀던 버튼을 한번 더 누르면 평가를 지울 수 있습니다.)

			else{
				result = evaluationDAO.update(bbsID, userID, likeIt, badIt);
			}
	 		if (result == -1){
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('평가를 실패했습니다.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	}
	 		
	 		else{
		 		PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("location.href=document.referrer;");
		 		script.println("</script>");
		 	}
	 	}
	 %>
</body>
</html>