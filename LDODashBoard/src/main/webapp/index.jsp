<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
	prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>

<link rel="stylesheet" type="text/css"
	href="/LDODashBoard/css/mystyle.css" />

<meta charset="utf-8">

<title>Home Page of LDO DashBoard</title>

</head>
<body id="body">

	<div class="color-white" style="text-align:center">
	
	<img src="/LDODashBoard/images/homeImage.jpg"/>
	
		<h3 id="h3"> 
		<strong> Glad to welcome User: <%=session.getAttribute("username")%>
		</strong> <br> <br>
	
		<c:if test="${empty urlName}">
			<c:set var="urlName" value="/welcome">
			</c:set>
	
			<c:url value="${urlName}" var="messageUrl" />
		  	You have logged out or session expired, click <a href="${messageUrl}">here	</a> to Login!!!
	        <c:set var="urlName" value="" />
		</c:if>
	
		<c:if test="${not empty urlName}">
	
			<c:url value="${urlName}" var="messageUrl" />
			<a href="${messageUrl}">Click Here to Redirect to LDO ${username}  Dashboard</a>
			
			<br>
			<br>
			<br>
	
			<c:url value="/logoutPage" var="messageUrl" />
			<a href="${messageUrl}">Logout</a>
		</c:if>
		</h3>
	</div>
</body>
</html>
