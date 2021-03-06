<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<title>Student Spring MVC</title>
</head>
<body>
    <h3>Passing Active Students</h3>
    <table border="2">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Year Level</th>
            <th>Average</th>
            <th>Manage Subjects</th>
        </tr>
        <c:forEach items="${passers}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.level}</td>
            <td>${student.average}</td>
            <td><a href="${contextPath}/subject/manage/${student.id}">Subjects</a></td>
        </tr>
        </c:forEach>
    </table>
    <a href="${contextPath}/home">Back</a><br/>
</body>
</html>