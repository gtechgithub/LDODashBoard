<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript">
</script> 
<link rel="stylesheet" type="text/css" href="/LDODashBoard/css/mystyle.css" /> 
<meta charset="utf-8">
</head>
<body>
<div align="center" style="color:white">


<table id="table1"><caption> ${QueryExecutionResults.queryDescription} </caption>
	<tr>
	<c:forEach items="${QueryExecutionResults.queryColumnNames}" var="columnnames">
			<th> ${columnnames} </th>
    </c:forEach>
	</tr>

	<c:forEach items="${QueryExecutionResults.queryRowColResult}" var="actualrowvalues">
		<tr>
			<td> ${actualrowvalues.product} </td>
			<td> ${actualrowvalues.ip_address} </td>
			<td> ${actualrowvalues.http_port} </td>
			<td> ${actualrowvalues.rmi_port} </td>
			<td> ${actualrowvalues.node_type} </td>
			<td> ${actualrowvalues.host_name} </td>
			<td> ${actualrowvalues.status} </td>
			<td> ${actualrowvalues.laastseen} </td>
			
		</tr>
    </c:forEach>

</table>
<br><br>
<c:url value="/L1OutputDisplayPage?gcmmLink2=true" var="messageUrl2" />
Click <a href="${messageUrl2}" style="color:yellow" onclick="doSomething('displayContent','/LDODashBoard/L1OutputDisplayPage?','gcmmLink2=true');return false;"> Here</a>
 to Close

</div>
</body>
</html>
