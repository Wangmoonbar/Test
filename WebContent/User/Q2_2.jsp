<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="  crossorigin="anonymous"></script>
</head>
	<BODY>
		<TABLE border="1" width="300">
			<TR>
				<TD align="center">
					��ܫe�ݿ�J�����
				</TD>
			</TR>
			<TR>
				<TD align="center">
					�b�� = ${userVO.id}
				</TD>
			</TR>
			<TR>
				<TD align="center">
					�K�X =  ${userVO.pswd}
				</TD>
			</TR>
			<TR>
				<TD align="center">
					�y�z =  <��ܸ�Ʈw��� desc �����>
				</TD>
			</TR>
			<TR>
				<TD align="center">
					<input type="button" id="btn" value="�^�W��">
				</TD>
			</TR>
		</TABLE>
		
		<script>
			$("#btn").click(function(){
				history.back();
			})
		
		
		
		</script>
	</BODY>
</html>