<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="  crossorigin="anonymous"></script>
</head>
<body>
		<TABLE border="1" width="300">
			<TR>
				<TD align="center">
					顯示前端輸入查詢失敗資料
				</TD>
			</TR>
			<TR>
				<TD align="center">
					帳號 = ${memberVO.id}
				</TD>
			</TR>
			<TR>
				<TD align="center">
					密碼 = ${memberVO.pswd}
				</TD>
			</TR>
			<TR>
				<TD align="center" style="color:red;">
					失敗原因 =  
					<c:forEach var="errorMsg" items="${errorMsgs}">
						${errorMsg}
					</c:forEach>
				</TD>
			</TR>
			<TR>
				<TD align="center">
					<input type="button" id="btn" value="回上頁">
				</TD>
			</TR>
		</TABLE>

</body>
	<script>
		$("#btn").click(function(){
			history.back();
		})
	</script>
</html>