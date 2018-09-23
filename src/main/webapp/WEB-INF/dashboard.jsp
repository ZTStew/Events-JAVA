<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard</title>

<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<h1>Congrats. You're here. Now Get OFF.</h1>
	
	<h2>Events in your state: </h2>
	<table>
		<tr>
			<th>Name</th>
			<th>Date</th>
			<th>Location</th>
			<th>Host</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ stateEvents }" var = "event">
		<tr>
			<td><a href="/events/${ event.id }">${ event.name }</a></td>
			<td><fmt:formatDate pattern = "mm/dd/yyyy" value = "${event.date}" /></td>
			<td>${ event.city }, ${ event.state }</td>
			<td>${ event.user.lastName }</td>
			<td>
				<c:if test = "${ event.getUsers().contains(userObj) }">
					<a href="/events/unjoin/${ event.id }">Un-Join</a>
				</c:if>
				<c:if test = "${ !event.getUsers().contains(userObj) }">
					<a href="/events/join/${ event.id }">Join</a>
				</c:if>
				<c:if test = "${ event.user == userObj }">
					<form action="/events/edit/${ event.id }" method = "get">
						<input type = "submit" value = "Edit Event">
					</form>
					<form action="/events/delete/${ event.id }" method = "post">
						<input type = "hidden" name = "_method" value = "delete">
						<input type = "submit" value = "Delete Event">
					</form>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<h2>Events in other states: </h2>
	
	<table>
		<tr>
			<th>Name</th>
			<th>Date</th>
			<th>Location</th>
			<th>Host</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ notStateEvents }" var = "event">
		<tr>
			<td><a href="/events/${ event.id }">${ event.name }</a></td>
			<td><fmt:formatDate pattern = "mm/dd/yyyy" value = "${event.date}" /></td>
			<td>${ event.city }, ${ event.state }</td>
			<td>${ event.user.lastName }</td>
			<td>
				<c:if test = "${ event.getUsers().contains(userObj) }">
					<a href="/events/unjoin/${ event.id }">Un-Join</a>
				</c:if>
				<c:if test = "${ !event.getUsers().contains(userObj) }">
					<a href="/events/join/${ event.id }">Join</a>
				</c:if>
				<c:if test = "${ event.user == userObj }">
					<form action="/events/edit/${ event.id }" method = "get">
						<input type = "submit" value = "Edit Event">
					</form>
					<form action="/events/delete/${ event.id }" method = "post">
						<input type = "hidden" name = "_method" value = "delete">
						<input type = "submit" value = "Delete Event">
					</form>
				</c:if>
			</td>
		<tr>
		</c:forEach>
	</table>
	
	<form:form method = "POST" action = "/events/new" modelAttribute = "event">
		<p><form:errors path="name" class = "error"></form:errors></p>
		<p>
			<form:label path="name">Event Name: </form:label>
			<form:input path="name"></form:input>
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
	
	<a href="/users/logout">Logout</a>
</body>
</html>