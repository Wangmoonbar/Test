<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.animal.model.*"%>

<%
	AnimalVO animalVO = (AnimalVO) request.getAttribute("animalVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>動物資料新增 - addAnimalEmp.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>動物資料新增 - addAnimal.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="animal.do" name="form1">
		<table>
			<tr>
				<td>動物名稱:</td>
				<td><input type="TEXT" name="name" size="45"
					value="<%=(animalVO == null) ? "" : animalVO.getName()%>" /></td>
			</tr>
			<tr>
				<td>年齡:</td>
				<td><input type="TEXT" name="age" size="45"
					value="<%=(animalVO == null) ? "11" : animalVO.getAge()%>" /></td>
			</tr>
			<tr>
				<td>體重:</td>
				<td><input type="TEXT" name="weight" size="45"
					value="<%=(animalVO == null) ? "11" : animalVO.getWeight()%>" /></td>
			</tr>
			
			<tr>
				<td>照片:</td>
				<td>
				
				<img id="preview_img"
					src="<%=request.getContextPath()%>/NoData/none2.jpg" /><br>
					<input type="file" name="picture" size="45" accept="image/*"
					onchange="readURL(this)" targetID="preview_img"
					value="<%=(animalVO == null) ? "" : animalVO.getPicture()%>" /></td>
				<td><font color=red>${errorMsgs.picture}</font></td>
			</tr>


			<tr>
				<td>放養區:</td>
				<td><input type="TEXT" name="areano" size="45"
					value="<%=(animalVO == null) ? "11" : animalVO.getAreano()%>" /></td>
			</tr>

<%-- 			<jsp:useBean id="areaSvc" scope="page" --%>
<%-- 				class="com.area.model.AreaService" /> --%>
<!-- 			<tr> -->
<!-- 				<td>放養區:<font color=red><b>*</b></font></td> -->
<!-- 				<td><select size="1" name="areano"> -->
<%-- 						<c:forEach var="areaVO" items="${areaSvc.all}"> --%>
<%-- 							<option value="${deptVO.deptno}" --%>
<%-- 								${(empVO.deptno==deptVO.deptno)? 'selected':'' }>${deptVO.dname} --%>
<%-- 						</c:forEach> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<script> 
 function readURL(input){
   if(input.files && input.files[0]){
     var imageTagID = input.getAttribute("targetID");
     var reader = new FileReader();
     reader.onload = function (e) {
        var img = document.getElementById(imageTagID);
        img.setAttribute("src", e.target.result)
     }
     reader.readAsDataURL(input.files[0]);
   }
 }
</script> 


</html>