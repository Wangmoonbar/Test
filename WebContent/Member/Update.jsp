<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="  crossorigin="anonymous"></script>
</head>
<body>

	<form id="form1" method="post" action="${pageContext.request.contextPath}/MemberServlet"> 
		<TABLE border="1" width="300">
			<TR>
				<TD align="center">
					�b��<input type="text" name="id" id="id" value="${memberVO.id}">
				</TD>
			</TR>
			<TR>
				<TD align="center">
					�K�X<input type="text" name="pswd" id="pswd" value="${memberVO.pswd}">
				</TD>
			</TR>
			<TR>
				<TD align="center">
					
					<input type="hidden" name="seq" value="${memberVO.seq}">
					<input type="hidden" name="action" value="update">
					<input type="button" id="btn" value="�ק�">
					<input type="reset" name="rstbtn" value="����">
				</TD>
			</TR>
		</TABLE>
	</form>
		<div id="errorMsgs" style="color:red;">
		
		</div>
	
	<script>
		$("#btn").click(function(){
			let id = $("#id").val().trim();
			let pswd = $("#pswd").val().trim();
			let nameReg = /^\w{1,5}$/;
			let str = "";
			
			if(!nameReg.test(id) || id == ""){
				str += "�b��: �п�J���׬�1-5���^��r���μƦr<br>";
			}
			if(!nameReg.test(pswd) || pswd == ""){
				str += "�K�X: �п�J���׬�1-5���^��r���μƦr";
			}
			
			if(str == ""){
				$("#form1").submit();
			}else{
				$("#errorMsgs").html(str);
			}
		})
	</script>

</body>
</html>