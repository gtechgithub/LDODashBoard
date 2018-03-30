<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/LDODashBoard/css/mystyle.css" />
<title>Logout LDO Dashboard</title>
</head>
<body id="body">

	<div class="color-white" style="text-align:center">
	
	<h3 id="h3">
	<img src="/LDODashBoard/images/logoutImage.jpg"/>
	<br>
	<br>
	
		<c:url value="/welcome" var="messageUrl" />
		<a href="${messageUrl}">Click here to Login</a>
	</h3>
  	</div>
</body>
</html>