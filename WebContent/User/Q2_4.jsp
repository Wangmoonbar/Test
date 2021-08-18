<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.*"%>
<%@ page import="com.user1.model.*"%>

<jsp:useBean id="userSvc" scope="page" class="com.user1.model.UserService"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
	<BODY>
		<table border="1">
		<tbody>
			<tr>
				<td>帳號</td>
				<td>密碼</td>
				<td>編輯</td>
			</tr>
			<c:forEach var="userVO" items="${userSvc.all}">
				<tr>
					<td>${userVO.id}</td>
					<td>${userVO.pswd}</td>
					<td><a href="${pageContext.request.contextPath}/UserController?action=getOneUpdate&user_no=${userVO.user_no}">edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
		<div id="errorMsgs" style="color:red;">
			<c:forEach var="errorMsg" items="${errorMsgs}">
				${errorMsg}<br>
			</c:forEach>
		</div>
	</BODY>
</html>