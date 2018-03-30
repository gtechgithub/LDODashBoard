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
<table id="table1"><caption> <STRONG> URL Connectivity Check </STRONG> <br> <br> </caption>
	<tr>
		<td> URL Description </td>
		<td> URL Link </td>
		<td> URL Response Code </td>
		<td> URL Response Description </td>		
		<td> URL Status </td>		
	</tr>
	
	<c:forEach items="${LnkConnectionResponse}" var="linkConnectionResponse">
	        <tr>
	        	<td> ${linkConnectionResponse.urlDescription}</td>
	        	<td> <a href="${linkConnectionResponse.urlLink}" style="color:yellow" target="_blank">${linkConnectionResponse.urlLink}</a></td>
	        	<td> ${linkConnectionResponse.urlResponseCode}</td>
	        	<td> ${linkConnectionResponse.urlResponseDescription}</td>
	        	<td> ${linkConnectionResponse.urlResponseStatus}</td>
	        		        	
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
