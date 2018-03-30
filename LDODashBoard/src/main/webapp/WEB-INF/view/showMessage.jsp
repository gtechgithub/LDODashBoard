<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>

<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript">
 </script> 
<link rel="stylesheet" type="text/css"
	href="/LDODashBoard/css/mystyle.css" />
	
<title>L1 DashBoard page</title>
</head>

<body id="body">

	<div style='text-align:center;'> <h3 id="color-yellow">${message}</h3>  </div>
	<div align="right" >
	<strong>
	<a href="/LDODashBoard" style="color:green"> HOME </a>&nbsp;&nbsp;
	<a href="/LDODashBoard/logoutPage.htm" style="color:red">LOGOUT </a> 
	</strong>
	</div>
	
<iframe id="mainContent" src="/LDODashBoard/L1SrcLinkPage.htm"  width=20% scrolling="no" onload="resizeIframe(this);"></iframe>
<iframe id="displayContent"  src="/LDODashBoard/L1OutputDisplayPage.htm" width=78% scrolling="yes" onload="resizeIframe(this);"></iframe>

</body>
</html>

