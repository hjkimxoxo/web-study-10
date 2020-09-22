<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

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

$(function(){
	$('#modify').on("click", function(){
        alert("수정");
       if(!status){
    	   $('input#empName').attr("readonly", false);
    	   $('select#title').attr("disabled", false);
    	   $('input#manager').attr("readonly",false);
    	   $('input#salary').attr("readonly",false);
    	   $('select#department').attr("disabled", false);
    	   $('input#regDate').attr("readonly", false);
    	   $('input#email').attr("readonly",false);
    	   $('input#tel').attr("readonly",false);
    	   $('input#picUrl').attr("readonly",false);
    	 
    	   status=true;
    	   
       }else{
    		var emp = {
    				empNo:$('#empNo').val(),
    				empName:$('#empName').val(),
    				title:{titleNo:$('#title').val()},
    				manager:{empNo:$('#manager').val()},
    				salary:$('#salary').val(),
    				dept:{deptNo:$("#department").val()},
    				email:$('#email').val(), 
    				passwd:$('#passwd').val(),
    				regDate:$('#regDate').val(),
    				tel:$('#tel').val(),
    				picUrl:$('#picUrl').val()
    				
    				};
    		$.ajax({
    			type: "post",
    			url:"EmpModifyHandler",
    			data:JSON.stringify(emp),
    			success: function(data){
    				alert(data);
    			}
    		})
   		}
	});
		
		$('#list').on("click", function(){
		    location.href="EmployeeListHandler";
		});
		
		$("#title").val(${emp.title.titleNo}).prop("selected", true);
		$("#department").val(${emp.dept.deptNo}).prop("selected", true);
		
	})	
	});
</script>
</head>
<body>
 <fieldset>
 	<legend>사원 정보</legend>
 	<!-- <option value="" selected disabled hidden>==선택하세요==</option>
 	
-->
 	<ul>
 	
 		<li>
 			<label for="empNo">사원번호</label>
 			<input id="empNo" type="text" name="empNo" value="${emp.empNo}" readonly>
 		</li>
 		<li>
 			<label for="empName">사원명</label>
 			<input id="empName" type="text" name="empName" value="${emp.empName}" readonly>
 		</li>
 		<li>
 			<label for="title">직책</label>
 			<select id="title" disabled></select>
 		</li>
 		<li>
 			<label for="manager">직속상사</label>
 			<input id="manager" type="text" name="manager" value="${emp.manager.empName }" readonly>
 		</li>
 		<li>
 			<label for="salary">급여</label>
 			<input id="salary" type="text" name="salary" value="${emp.salary}" readonly>
 		</li>
 		<li>
 			<label for="dept">부서</label>
 			<select id="department" disabled></select>
 		</li>
 		<li>
 			
 			<label for="regDate">입사일</label>
 			<input id="regDate" type=date value=<fmt:formatDate value="${emp.regDate}" pattern="yyyy-MM-dd" /> readonly >	
 		</li>
 		<li>
 			<label for="email">이메일</label>
 			<input id="email" type="text" name="email" value="${emp.email}" readonly>
 		</li>
 		<li>
 			<label for="tel">연락처</label>
 			<input id="tel" type="text" name="tel" value="${emp.tel}" readonly>
 		</li>
 		<li>
 			<label for="picUrl">사진</label>
 			<input id="picUrl" type="text" name="picUrl" value="${emp.picUrl}" readonly>
 		</li>
 		
 	</ul>
 	
		<button id="modify">수정</button>
		<button id="delete">삭제</button>
		<button id="list">목록</button>
 </fieldset>
</body>
</html>