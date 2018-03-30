
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<link rel="stylesheet" type="text/css" href="/LDODashBoard/css/mystyle.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login Error</title>

</head>

<body id="body">

<div class="color-white" style="text-align:center">
	<br>
	<img src="/LDODashBoard/images/failedLogin.png"/>
	<br>
	<h3 id="h3">
		<c:url value="/welcome" var="messageUrl" />
		<a href="${messageUrl}">Try once again, Click Here !!</a>
		<br>
		<br>
		or 
		<br>
		<i> Please contact DD LDO IT Asia Pac Support (list.ldo-it-apac@credit-suisse.com) </i>
	</h3>
</div>

</body>

</html>

