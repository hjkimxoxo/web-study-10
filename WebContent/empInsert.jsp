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
<script type="text/javascript" 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>
<script type="text/javascript">
$(function(){
	$('#add').on("click", function(){
		var newEmp = {
				empNo:$('#empNo').val(),
				empName:$('#empName').val(),
				title:{titleNo:$('#title').val()},
				manager:{empNo:$('#manager').val()},
				salary:$('#salary').val(),
				dept:{deptNo:$("#dept").val()},
				email:$('#email').val(), 
				passwd:$('#passwd').val(),
				regDate:$('#redDate').val(),
				tel:$('#tel').val(),
				picUrl:$('#picUrl').val()
				};
		
		$.ajax({
	           type:"post",
	           url:"EmpInsertHandler",
	           cache: false,
	           data: JSON.stringify(newEmp),
	           complete:function(data){
	               alert("추가되었습니다." + data);
	               window.location.href="EmployeeListHandler";
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
            $("#dept").append(sCont);   
        }
    });
});
</script>
</head>
<body>
	<fieldset>
		<legend>사원 추가</legend>
		<ul>
			<li>
				<label for="empNo">사원 번호</label>
				<input id="empNo" type=text name="empNo">
				<button id="check">중복 체크</button>
			</li>
			<li>
				<label for="empName">사원명</label>
				<input id="empName" type=text name="empName">
			</li>
			<li>
				<label for="title">직책</label>
				<!-- <input id ="title" type=text name="title"> -->
				<select id="title"></select>
			</li>
			<li>
				<label for="manager">직속상사</label>
				<input id="manager" type=text name="manager">
			</li>
			<li>
				<label for="salary">급여</label>
				<input id="salary" type=text name="salary">
			</li>
			<li>
				<label for="dept">부서</label>
				<!--  <input id ="dept" type=text name="dept">-->
				<select id="dept"></select>
			</li>
			<li>
				<label for="email">이메일</label>
				<input id="email" type=email name="email">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input id="passwd" type=password name="passwd">
			</li>
			<li>
				<label for="date">입사일</label>
				<input id="redDate" type=date name="redDate">	
			</li>
			<li>
				<label for="tel">연락처</label>
				<input id="tel" type=text name="tel">
			</li>
			<li>
				<label for="pic">사진</label>
				<input id="picUrl" type=text name="pic">
			</li>
			<li>
				<button id="add">추가</button>
				<button id="cancel">취소</button>
			</li>
		</ul>
	</fieldset>
</body>
</html>