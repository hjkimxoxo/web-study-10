<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="web_study_10.ds.JdbcUtil"%>
<%@page import="web_study_10.ds.JndiDS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터베이스 연동 테스트</title>
</head>
<body>
	<c:set var="con" value="${JdbcUtil.getConnection()}"></c:set>
	<c:out value="${con}"></c:out><br>
	<c:set var="con1" value="${JndiDS.getConnection()}"></c:set>
	<c:out value="${con1}"></c:out><br>
	<br><a href="TitleListHandler">직책 목록</a><br>
	<a href="DeptListHandler">부서 목록</a>
</body>
</html>