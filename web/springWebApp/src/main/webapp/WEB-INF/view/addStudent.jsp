<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <script type="text/javascript" src="${contextPath}/dwr/interface/StudentServiceImpl.js"/>
    <script type="text/javascript" src="${contextPath}/dwr/engine.js"/>
<title>Student Spring MVC</title>

    <script type="text/javascript">
        function validateForm() {
            var firstName = document.forms["studentForm"]["firstName"].value;
            var lastName = document.forms["studentForm"]["lastName"].value;
            var level = document.forms["studentForm"]["level"].value;
            var gender = document.getElementsByName("gender");
            var status = document.getElementsByName("status");
            var birthday = document.forms["studentForm"]["birthday"].value;

            if (firstName == null || firstName.trim() == "") {
                alert("First name must be filled up");
                return false;
            } else if (lastName == null || lastName.trim() == "") {
                alert("Last name must be filled up");
                return false;
            } else if (gender[0].checked == false && gender[1].checked == false) {
                alert("Please pick gender");
                return false;
            } else if (status[0].checked == false && status[1].checked == false) {
                alert("Please pick status");
                return false;
            } else if (birthday.trim() == "") {
                alert("Please enter birthday");
                return false;
            }
        }
    </script>
</head>

<body>
    <h3>Student Form</h3>
	<form:form method="post" action="${contextPath}/student/process" name="studentForm" commandName="student" onsubmit="return validateForm()">
	    First Name:<form:input path="firstName" value="${student.firstName}"/>
	    <form:errors path="firstName"/><br/>
	    Last Name:<form:input path="lastName" value="${student.lastName}"/>
	    <form:errors path="lastName"/><br/>
	    Year Level:<form:select path="level">
	                   <option value=null disabled selected>Choose a Year Level</option>
	                   <option value="1" ${student.level == '1' ? 'selected' : '' }>1st Year</option>
	                   <option value="2" ${student.level == '2' ? 'selected' : '' }>2nd Year</option>
	                   <option value="3" ${student.level == '3' ? 'selected' : '' }>3rd Year</option>
	                   <option value="4" ${student.level == '4' ? 'selected' : '' }>4th Year</option>
	               </form:select>
	               <form:errors path="level"/><br/>
	               <form:hidden path="id" value="${student.id}"/>
	    Gender: <input type="radio" name="gender" value="MALE" ${student.gender == 'MALE' ? 'checked' : '' }>Male
	    		<input type="radio" name="gender" value="FEMALE" ${student.gender == 'FEMALE' ? 'checked' : '' }>Female
	    		<form:errors path="gender"/><br/>
	   
	    Status: <input type="radio" name="status" value="true" ${student.status == 'true' ? 'checked' : '' }>Active
	       	    <input type="radio" name="status" value="false" ${student.status == 'false' ? 'checked' : '' }>Inactive
	       	    <form:errors path="status"/><br/>
        Birthday (Day/Month/Year): <input type="text" name="birthday" placeholder="MM/DD/YYYY" maxlength="10"/><br/>
	       	    <form:hidden path="pageNo" value="${student.pageNo}"/>
	    <input type="submit" value="Save Student"/>
	</form:form>
    <a href="${contextPath}/home">Back</a><br/>
    <b>${message}</b>
</body>
</html>