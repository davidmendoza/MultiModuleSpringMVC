<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Spring MVC</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<body>
     <h3>Subjects</h3>
     <table border="2">
         <tr>
             <th>Subject Name</th>
             <th>Subject Grade</th>
             <th>Action</th>
         </tr>
     
     <c:forEach items="${subjects}" var="subject">
         <tr>
             <td>${subject.name}</td>
             <td>${subject.grade}</td>
             <td><a href="${contextPath}/subject/delete/${studentId}-${subject.id}">Delete</a></td>
         </tr>
     </c:forEach>
     </table>
     
     <h4>Average: ${gpa.Average} GPA: ${gpa.GPA}</h4>
     <h4>Add new Subject and Grade</h4>
     <form:form method="post" action="${contextPath}/subject/process/${studentId}" commandName="subject">
     Subject name: <form:input path="name" value="${subject.name}"/>
     <form:errors path="name"/><br/>
     Subject grade: <form:input path="grade" value="${subject.grade}"/>
     <form:errors path="grade"/><br/>
     <input type="submit" value="Save Subject"/>
     </form:form>
     <a href="${contextPath}/index">Back to Index</a><br/>
     <b>${message}</b>
     
</body>
</html>