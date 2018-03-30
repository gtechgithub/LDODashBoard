<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<base target="_parent" />
<link rel="stylesheet" type="text/css"
	href="/LDODashBoard/css/mystyle.css" />

<title>Welcome</title>
<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript">
</script> 

</head>

<body id="body" onload="breakout_of_frame()">
	<div style="text-align: center">
		<h2 id="h2">
			LDO Support Dashboard<br> <br>
		</h2>

		<img src="/LDODashBoard/images/SupportDashboard.jpg" />
		<br> <br>
		<c:if test="${not empty error}">
			<!--   <h3 id="h3"> Your login attempt was not successful, try again </h3> -->
			<img src="/LDODashBoard/images/failedLogin.png"/>
			
  		</c:if>

		<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>

			<h3 id="h3">
				<table  align="center">
					<tr>
						<td>User:</td>
						<td><input type='text' name='j_username' value=''></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type='password' name='j_password' /></td>
						<td colspan='2'><input name="submit" type="submit"
							value="Submit" /></td>
						<td colspan='2'><input name="reset" type="reset" /></td>
					</tr>
				</table>
			</h3>
		</form>
	</div>
</body>
</html>