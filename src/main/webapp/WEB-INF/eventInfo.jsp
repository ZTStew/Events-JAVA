<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Info</title>

<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class = "wrapper">
	<div class = "leftSide">
		<h1>${ event.name }</h1>
		<h3>Host: ${ event.user.firstName } ${ event.user.lastName }</h3>
		<h3>Date: <fmt:formatDate pattern = "mm/dd/yyyy" value = "${event.date}" /></h3>
		<h3>Location: ${ event.city }, ${ event.state }</h3>
		<h3>People Attending: ${ event.users.size() }</h3>
		
		<table>
			<tr>
				<th>Name: </th>
				<th>Location: </th>
				<th>Actions:</th>
			</tr>
			<c:forEach items="${ event.users }" var="user">
				<tr>
					<td>${ user.lastName }</td>
					<td>${ user.city }, ${ user.state }</td>
					<td><a href="/events/unjoin/info/${ event.id }">Un-Join</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class = "rightSide">
		<form action = "/events" method = "get">
			<input type = "submit" value = "Back To DashBoard">
		</form>
		<form action = "/users/logout" method = "get">
			<input type = "submit" value = "Logout">
		</form>
		<h1>Message Wall</h1>
		<div class = "msBox">
			<c:forEach items="${ event.messages }" var="msg">
				<h3>${ msg.user.lastName }: ${ msg.text }</h3>
			</c:forEach>
		</div>
		
		<h2>Add Message: </h2>
		<form:form action="/events/new/message/${ event.id }" method="post" modelAttribute="message">
			<form:errors path = "text"></form:errors>
			<form:label path = "text">
				<form:input path = "text"></form:input>
			</form:label>
			<input type = "submit" value = "Post Comment">
		</form:form>
	</div>
</div>
</body>
</html>