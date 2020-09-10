<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
    $('#insertDept').on("click", function(){
        alert("부서 추가");
        $.ajax({
            type:"get",
            url:"DeptInsertHandler",
            success: function(data){
                alert("data > " + data);
                window.location.href="deptInsert.jsp?nextNo="+data;
            }
        });
    });
    
});
</script>
</head>
<body>

<h2>부서목록</h2>
<table border=1>
	<tr>
		<td>부서 번호</td>
		<td>부서명 </td>
		<td>부서 위치 </td>
	</tr>
	
	<c:forEach var="dept" items="${list}">	
	<tr>
		<td>${dept.deptNo}</td>
		<td><a href="DeptNoHandler?deptNo=${dept.deptNo}">${dept.deptName }</a></td>
		<td>${dept.floor}</td>
	</tr>
	</c:forEach>
	
</table>
<br>
<button id="insertDept">부서 추가</button>
</body>
</html>