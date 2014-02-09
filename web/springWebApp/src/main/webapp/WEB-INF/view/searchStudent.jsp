<%--
  Created by IntelliJ IDEA.
  User: dmendoza
  Date: 1/21/14
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
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
    <script type="text/javascript" src="${contextPath}/dwr/interface/StudentServiceImpl.js"></script>
    <script type="text/javascript" src="${contextPath}/dwr/engine.js"></script>
</head>
<body>
    <h3>Search Student</h3>
    <form method="post" action="${contextPath}/student/search/process">
        Enter name (Either First or Last): <input type="text" name="name"/>   <input type="submit" value="search"/>
    </form>

    <c:if test="${students != null}">
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
                <td><a href="${contextPath}/student/edit/0-${student.id}">Update | </a>
                    <a href="${contextPath}/student/delete/0-${student.id}">Delete</a></td>
                <td><a href="${contextPath}/subject/manage/${student.id}">Subjects</a></td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
    <b>${message}</b><br/>
    <a href="${contextPath}/student/add">Add New Student</a><br/>
    <a href="${contextPath}/student/passed">View Passing Students</a><br/>
    <a href="${contextPath}/home">Back</a><br/>


    <input type = "text" id="search"/><br/>
    <input type="button" onclick="searchList()"/>

    <script type="text/javascript">
        function searchList() {
            var nameToSearch = document.getElementById("search").value;
            StudentServiceImpl.getSearchResults()
        }
    </script>
</body>
</html>
