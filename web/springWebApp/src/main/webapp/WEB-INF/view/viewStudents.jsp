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
    <h3>Student List</h3>
    <table border="2">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Year Level</th>
            <th>Active?</th>
            <th>Action</th>
            <th>Manage Subjects</th>
        </tr>
        <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.gender}</td>
            <td>${student.level}</td>
            <td>${student.status}</td>
            <td><a href="${contextPath}/student/edit/${pageMap.nextPage}-${student.id}">Update | </a>
            <a href="${contextPath}/student/delete/${pageMap.nextPage}-${student.id}">Delete</a></td>
            <td><a href="${contextPath}/subject/manage/${student.id}">Subjects</a></td>
        </tr>
        </c:forEach>
    </table>
    <a href="${contextPath}/student/view?page=${pageMap.prevPage}">Previous</a>    <a href="${contextPath}/student/view?page=${pageMap.nextPage}">Next</a><br/>
    <a href="${contextPath}/student/add">Add New Student</a><br/>
    <a href="${contextPath}/student/passed">View Passing Students</a><br/>
    <a href="${contextPath}/index">Back</a><br/>
    <b>${message}</b>
</body>
</html>