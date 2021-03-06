<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Spring MVC</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${contextPath}/dwr/interface/StudentServiceImpl.js"></script>
<script type="text/javascript" src="${contextPath}/dwr/engine.js"></script>

    <script type="text/javascript">
        function test() {
            var x = document.getElementById("name").value;
            StudentServiceImpl.dwrHelloWorld(x, function handleTest(str) {
                alert(str[0].firstName);
            });
        }
    </script>
</head>

<body>
	<h3>Student Profile Application using Spring MVC</h3>
	<a href="${contextPath}/student/add">Add New Student</a><br/>
	<a href="${contextPath}/student/view?page=0">View Students</a><br/>
    <a href="${contextPath}/student/search">Search Students</a><br/>
	<a href="${contextPath}/student/passed">View Active Passing Students</a><br/>
    <a href="${contextPath}/redirectExample">Controller to Controller example</a><br/>
	<b>${message}</b><br/>


    Enter name: <input type="text" id="name"/>
                <input type="button" onclick="test()" value="press me"/>




</body>
</html>