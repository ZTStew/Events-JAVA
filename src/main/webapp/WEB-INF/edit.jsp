<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>

<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div>
		<form action = "/events" method = "get">
			<input type = "submit" value = "Back To DashBoard">
		</form>
		<form action = "/users/logout" method = "get">
			<input type = "submit" value = "Logout">
		</form>
	</div>
	
	<form:form method = "POST" action = "/events/edit/${ event.id }" modelAttribute = "event">
    	
    	<p><form:errors path="name"/></p>
    	<p>
        	<form:label path="name">Event Name</form:label>
        	<form:input path="name"/>
    	</p>
		
		<p><form:errors path="city" class = "error"></form:errors></p>
		<p>
			<form:label path="city">City Name: </form:label>
			<form:input path="city"></form:input>
		</p>
		
		<p><form:errors path="state" class = "error"></form:errors></p>
		<p>
			<form:label path="state">State Initials: </form:label>
			<form:input path="state"></form:input>
		</p>
		
		<p><form:errors path="date" class = "error"></form:errors></p>
		<p>
			<form:label path="date">Date: </form:label>
			<form:input type="date" path="date"></form:input>
		</p>
		<input type = "submit" value = "Create Event">
	</form:form>

</body>
</html>