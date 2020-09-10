<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>
<script type="text/javascript">
$(function(){
	$('#insertEmp').on("click", function(){
        alert("사원추가");
        $.ajax({
            type:"get",
            url:"EmpInsertHandler",
            success: function(){
                window.location.href="empInsert.jsp";
            }
        });
    });
	
    
    $.post("TitleListHandler", function(json){
       var dataLength = json.length;
       if ( dataLength >=1 ){
           var sCont = "";
           for ( i=0 ; i < dataLength ; i++){
               sCont += "<option value=" + json[i].titleNo + ">" + json[i].titleName + "</>";
           }
           $("#title").append(sCont);   
       }
    });
    
    $.post("DeptListHandler", function(json){
        var dataLength = json.length;
        if ( dataLength >=1 ){
            var sCont = "";
            for ( i=0 ; i < dataLength ; i++){
                sCont += "<option value=" + json[i].deptNo + ">" + json[i].deptName + "</>";
            }
            $("#department").append(sCont);   
        }
    });
    
    $('#addEmp').on("click", function(){
        self.location = "empAdd.jsp";
    });
  /*   $.post("EmpListHandler", function(json){
        var dataLength = json.length;
        if ( dataLength >=1 ){
            var sCont = "";
            for ( i=0 ; i < dataLength ; i++){
               sCont += "<tr>";
               sCont += "<td>" + json[i].empNo + "</td>";
               sCont += "<td>" + json[i].empName + "</td>";
               sCont += "<td>" + json[i].title.titleName + "("+ json[i].title.titleNo + ")</td>";
               if (json[i].manager.empNo != 0){
                   sCont += "<td>" + json[i].manager.empName + "("+ json[i].manager.empNo + ")</td>";
               }else{
                   sCont += "<td></td>"; 
               }
               sCont += "<td>" + json[i].salary.toLocaleString("ko") + "</td>";
               sCont += "<td>" + json[i].dept.deptName + "("+ json[i].dept.deptNo + ")</td>";
               sCont += "<td>" + moment(json[i].regDate).format('LL') + "</td>";
               sCont += "<td>" + json[i].email + "</td>";
               sCont += "<td>" + json[i].tel + "</td>";
               sCont += "</tr>";
           }
            $("table > tbody:last-child").append(sCont);    
           $("#load:last-child").append(sCont);   
       } 
    });*/
     
});


</script>
</head>
<body>

<label for="title">직책</label>
<select id="title">
</select>
<label for="department">부서</label>
<select id="department">
</select>

<%--
직책
 <select id="title" name="title">
<c:forEach var="title" items="${title}" varStatus="status">
<option value="${title.titleName}">${title.titleName}</option>
</c:forEach>
</select>

부서
<select id="dept" name="dept">
<c:forEach var="dept" items="${dept}" varStatus="status">
<option value="${dept.deptName}">${dept.deptName}</option>
</c:forEach>
</select> --%>
<h2>사원 목록</h2>
<table>
		<thead>
		<tr>
			<td>사원번호</td>
			<td>사원명</td>
			<td>직책</td>
			<td>직속상사</td>
			<td>급여</td>
			<td>부서</td>
			<td>입사일</td>
			<td>이메일</td>
			<td>연락처</td>
			<td>사진</td>
		</tr>
		</thead>
	<c:forEach var="emp" items="${list}">
		<tr>
			<td>${emp.empNo}</td>
			<td><a href="EmpNoHandler?empNo=${emp.empNo}">${emp.empName}</a></td>
			<td>${emp.title.titleName}(${emp.title.titleNo})</td>
			<td>${emp.manager.empName}</td>
			<td><fmt:formatNumber value="${emp.salary}" pattern="#,###"/></td>
			<td>${emp.dept.deptName}(${emp.dept.deptNo})</td>
			<td><fmt:formatDate value="${emp.regDate}" pattern="yyyy년 MM월 dd일" /></td>
			<td>${emp.email}</td>
			<td>${emp.tel}</td>
			<td>${emp.picUrl}</td>

		</tr>
	</c:forEach>
		
</table>
<button id="insertEmp">사원 추가</button>

</body>
</html>