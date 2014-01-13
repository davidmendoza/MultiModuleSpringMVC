<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<title>Student Spring MVC</title>
</head>
<body>
	<h3>Student Grades</h3>
	
	<table border="2">
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Level</th>
			<th>Math </th>
			<th>English</th>
			<th>Science</th>
			<th>Action</th>
		</tr>
		
	<c:forEach items="${students}" var="student">
		<form:form method="post" action="${contextPath}/student/grades/process" commandName="student">
		<tr>
			<form:hidden path="id" value="${student.id}"/>
			<td>${student.id }</td>
			<td>${student.firstName }</td>
			<td>${student.lastName }</td>
			<td>${student.level }</td>
			<td><form:input path="grade.math" value="${student.grade.math}" size="5" maxlength="2"/></td>
		    <td><form:input path="grade.english" value="${student.grade.english }" size="5" maxlength="2"/></td>
			<td><form:input path="grade.science" value="${student.grade.science }" size="5" maxlength="2"/></td>
			<td><input type="submit" value="Save"/></td> 
		</tr>
		</form:form>
	</c:forEach>	
	</table>
	<a href="${contextPath}/index">Back to Main Menu</a><br/>
	<b>${message}</b>
</body>
</html>