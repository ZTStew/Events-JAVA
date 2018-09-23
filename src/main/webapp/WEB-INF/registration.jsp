<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>

<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<h1>Registration:</h1>
	<div class = "body">
		<form:form action="/users/registration" method="POST" modelAttribute="user">
		<p><form:errors path="firstName" class = "error"></form:errors></p>
		<p>
			<form:label path="firstName">First Name: </form:label>
			<form:input path="firstName"></form:input>
		</p>
		
		<p><form:errors path="lastName" class = "error"></form:errors></p>
		<p>
			<form:label path="lastName">Last Name: </form:label>
			<form:input path="lastName"></form:input>
		</p>
		
		<c:if test="${emailMatchError != null}" >
			<p class="error">${emailMatchError}</p>				
		</c:if>
		
		<p><form:errors path="email" class = "error"></form:errors></p>
		<p>
			<form:label path="email">Email: </form:label>
			<form:input path="email"></form:input>
		</p>
		
		<p><form:errors path="city" class = "error"></form:errors></p>
		<p>
			<form:label path="city">City: </form:label>
			<form:input path="city"></form:input>
		</p>
		
		<p><form:errors path="state" class = "error"></form:errors></p>
		<p>
			<form:label path="state">State: </form:label>
			<form:input path="state"></form:input>
		</p>
		
		<c:if test="${passwordMatchError != null}" >
			<p class="error">${passwordMatchError}</p>				
		</c:if>
		
		<p><form:errors path="password" class = "error"></form:errors></p>
		<p>
			<form:label path="password">Password: </form:label>
			<form:input path="password"></form:input>
		</p>
		
		<p><form:errors path="confirm" class = "error"></form:errors></p>
		<p>
			<form:label path="confirm">Reconfirm Password: </form:label>
			<form:input path="confirm"></form:input>
		</p>
		
		<input type = "submit" value = "Register"/>
	</form:form>
	<a href = "/users/login">To Login</a>
	</div>
</body>
</html>