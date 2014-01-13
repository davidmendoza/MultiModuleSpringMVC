<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Spring MVC</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>

<body>
	<h3>Student Profile Application using Spring MVC</h3>
	<a href="${contextPath}/student/add">Add New Student</a><br/>
	<a href="${contextPath}/student/view">View Students</a><br/>
    <a href="${contextPath}/student/grades">View Grades</a><br/>
	<b>${message}</b>
</body>
</html>