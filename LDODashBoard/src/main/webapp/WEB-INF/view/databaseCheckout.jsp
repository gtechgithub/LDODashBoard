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
<table id="table1"><caption> Database Checkout </caption>
	<tr>
		<td> Database Description </td>
		<td> Database Name </td>
		<td> Activity Status </td>
	</tr>
	
	<c:forEach items="${DatabaseCheckout}" var="checkout">
	    <c:if test="${fn:containsIgnoreCase(checkout.description, 'TSE/SYDNEY/4M')}">
	        <tr>
				<td> ${checkout.description} </td>
				<td> ${checkout.dbName}</td>
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'true')}">
					<td>  Active </td> 
				</c:if>	
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'false')}">
					<td bgcolor="yellow"><strong>In Active</strong> </td>
				</c:if>
			</tr>
	    </c:if>
	    
	    <c:if test="${fn:containsIgnoreCase(checkout.description, 'HKFE')}">
	        <tr>
				<td> ${checkout.description} </td>
				<td> ${checkout.dbName}</td>
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'true')}">
					<td>  Active </td> 
				</c:if>	
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'false')}">
					<td bgcolor="yellow"><strong>In Active</strong> </td>
				</c:if>
			</tr>
	    </c:if>
	    
	    <c:if test="${fn:containsIgnoreCase(checkout.description, 'SGX')}">
	        <tr>
				<td> ${checkout.description} </td>
				<td> ${checkout.dbName}</td>
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'true')}">
					<td>  Active </td> 
				</c:if>	
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'false')}">
					<td bgcolor="yellow"><strong>In Active</strong> </td>
				</c:if>
			</tr>
	    </c:if>
	    
	    <c:if test="${fn:containsIgnoreCase(checkout.description, 'TOCOM')}">
	        <tr>
				<td> ${checkout.description} </td>
				<td> ${checkout.dbName}</td>
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'true')}">
					<td>  Active </td> 
				</c:if>	
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'false')}">
					<td bgcolor="yellow"><strong>In Active</strong> </td>
				</c:if>
			</tr>
	    </c:if>
	    
	    <c:if test="${fn:containsIgnoreCase(checkout.description, 'LONDON_PLN20100')}">
	        <tr>
				<td> ${checkout.description} </td>
				<td> ${checkout.dbName}</td>
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'true')}">
					<td>  Active </td> 
				</c:if>	
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'false')}">
					<td bgcolor="yellow"><strong>In Active</strong> </td>
				</c:if>
			</tr>
	    </c:if>
	   
	    <c:if test="${fn:containsIgnoreCase(checkout.description, 'LONDON_PLN20300')}">
	        <tr>
				<td> ${checkout.description} </td>
				<td> ${checkout.dbName}</td>
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'true')}">
					<td>  Active </td> 
				</c:if>	
				<c:if test="${fn:containsIgnoreCase(checkout.activityStatus,'false')}">
					<td bgcolor="yellow"><strong>In Active</strong> </td>
				</c:if>
			</tr>
	    </c:if> 
    </c:forEach>

</table>

<br>

<c:url value="/showDatabaseCheckoutLogs" var="messageUrl3">

<!--<c:param name="serverLogs" value="${QueryLogs}"/> -->
</c:url>

<a href="${messageUrl3}" style="color:yellow" target="_blank"> View Checkout Logs </a>

<!--  

<a href="<c:url value="/showDatabaseCheckoutLogs" />"> View Checkout Logs</a>

-->


<br><br>
<c:url value="/L1OutputDisplayPage?gcmmLink2=true" var="messageUrl2" />
Click <a href="${messageUrl2}" style="color:blue" onclick="doSomething('displayContent','/LDODashBoard/L1OutputDisplayPage?','gcmmLink2=true');return false;"> Here</a>
 to Close

</div>
</body>
</html>
