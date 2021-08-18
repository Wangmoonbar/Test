<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="  crossorigin="anonymous"></script>
</head>
<body>

	<table border="1">
		<tbody>
			<tr>
				<td>帳號${pageContext.request.contextPath}</td>
				<td>密碼</td>
				<td>編輯</td>
			</tr>
			<c:forEach var="memberVO" items="${list}">
				<tr>
					<td>${memberVO.id}</td>
					<td>${memberVO.pswd}</td>
					<td><a href="${pageContext.request.contextPath}/MemberServlet?action=getOneUpdate&seq=${memberVO.seq}">edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
		<div id="errorMsgs" style="color:red;">
			<c:forEach var="errorMsg" items="${errorMsgs}">
				${errorMsg}<br>
			</c:forEach>
		</div>
</body>
</html>