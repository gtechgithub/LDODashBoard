<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript">
</script>
 
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<link rel="stylesheet" type="text/css" href="/LDODashBoard/css/mystyle.css" /> 
<meta charset="utf-8">

</head>
<body>
<div align="center" style="color:white">


<table id="table1"><caption> <STRONG> AppView DisplayPage </STRONG> <br> <br> </caption>
	<tr>
		<td> JobName </td>
		<td> Description </td>
		<td> ODATE </td>
		<td> StartTime </td>		
		<td> EndTime </td>	
		<td> Status </td>	
		<td> RunDuration </td>	
		<td> CyclicJob </td>	
	</tr>
	
	<c:forEach items="${appviewbatch}" var="appview">
	        <tr>
	        	<td> ${appview.jobName}</td>
	        	<td> ${appview.description}</td>
	        	<td> ${appview.oDATE}</td>
	        	<td> ${appview.startTime}</td>
	        	<td> ${appview.endTime}</td>
	        	<td> ${appview.status}</td>
	        	<td> ${appview.runDuration}</td>
	        	<td> ${appview.cyclicJob}</td>
			</tr>
    </c:forEach>

</table>

<br>
<c:url value="/L1OutputDisplayPage?gcmmLink2=true" var="messageUrl2" />
Click <a href="${messageUrl2}" style="color:yellow" onclick="doSomething('displayContent','/LDODashBoard/L1OutputDisplayPage?','gcmmLink2=true');return false;"> Here</a>
 to Close


</div>
</body>
</html>
