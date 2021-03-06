<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.animal.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AnimalService animalSvc = new AnimalService();
	List<AnimalVO> list = animalSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有動物資料 - listAllAnimal.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>


	<table id="table-1">
		<tr>
			<td>
				<h3>所有動物資料 - listAllAnimal.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>動物編號</th>
			<th>動物名稱</th>
			<th>年齡</th>
			<th>體重</th>
			<th>照片</th>
			<th>放養區</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="animalVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${animalVO.id}</td>
				<td>${animalVO.name}</td>
				<td>${animalVO.age}</td>
				<td>${animalVO.weight}</td>
				<td><img
					src="${pageContext.request.contextPath}/animal/DBGifReader1.do?id=${animalVO.id}"
					alt="尚無圖片" width="100px;" height="120px" "/></td>

				<td>${animalVO.areano}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/animal/animal.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="id" value="${animalVO.id}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/animal/animal.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="id" value="${animalVO.id}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>