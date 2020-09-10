<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서추가</title>
<script type="text/javascript" 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$('#add').on("click", function(){
		var newDept = {
				deptNo:$('#deptNo').val(),
				deptName:$('#deptName').val(),
				floor:$('#floor').val()};
		
		$.ajax({
	           type:"post",
	           url:"DeptInsertHandler",
	           cache: false,
	           data: JSON.stringify(newDept),
	           complete:function(data){
	               alert("추가되었습니다." + data);
	               window.location.href="DeptListHandler";
	           }
	    }); 
	})
})
</script>
</head>
<body>
<fieldset>
		<legend>부서 추가</legend>
		<ul>
			<li>
				<label for="deptNo">부서번호</label>
				<input id="deptNo" type="number" name="deptNo" value="${param.nextNo }" readonly>
			</li>
			<li>
				<label for="deptName">부서명</label>
				<input id="deptName" type="text" name="deptName" >
			</li>
			<li>
				<label for="floor">부서 위치</label>
				<input id="floor" type="number" name="floor">
			</li>
			<li>
				<button id="add">추가</button>
				<button id="cancel">취소</button>
			</li>
		</ul>
	</fieldset>
</body>
</html>