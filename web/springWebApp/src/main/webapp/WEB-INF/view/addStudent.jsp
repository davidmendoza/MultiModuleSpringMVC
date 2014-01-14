<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<title>Student Spring MVC</title>
</head>

<body>
    <h3>Student Form</h3>
	<form:form method="post" action="${contextPath}/student/process" commandName="student">
	    First Name:<form:input path="firstName" value="${student.firstName}"/>
	    <form:errors path="firstName"/><br/>
	    Last Name:<form:input path="lastName" value="${student.lastName}"/>
	    <form:errors path="lastName"/><br/>
	    Year Level:<form:select path="level">
	                   <option disabled selected>Choose a Year Level</option>
	                   <option value="1" ${student.level == '1' ? 'selected' : '' }>1st Year</option>
	                   <option value="2" ${student.level == '2' ? 'selected' : '' }>2nd Year</option>
	                   <option value="3" ${student.level == '3' ? 'selected' : '' }>3rd Year</option>
	                   <option value="4" ${student.level == '4' ? 'selected' : '' }>4th Year</option>
	               </form:select>
	               <form:errors path="level"/><br/>
	               <form:hidden path="id" value="${student.id}"/>
	    Gender: <input type="radio" name="gender" value="Male" ${student.gender == 'Male' ? 'checked' : '' }>Male
	    		<input type="radio" name="gender" value="Female" ${student.gender == 'Female' ? 'checked' : '' }>Female 
	    		<form:errors path="gender"/><br/>
	   	<c:choose>
	   	    <c:when test="${student.status != null}">
	    Status: <input type="radio" name="status" value="true" ${student.status == 'true' ? 'checked' : '' }>Active
	       	    <input type="radio" name="status" value="false" ${student.status == 'false' ? 'checked' : '' }>Inactive
	       	    <form:errors path="status"/><br/>
	       	</c:when>
	       	<c:otherwise>
	       	    <input type="hidden" name="status" value="true"/>
	       	</c:otherwise>
	    </c:choose>
	    <input type="submit" value="Save Student"/>
	</form:form>
    <a href="${contextPath}/index">Back</a><br/>
    <b>${message}</b>
</body>
</html>