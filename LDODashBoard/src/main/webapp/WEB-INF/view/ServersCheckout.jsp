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
<script>
$(document).ready( function(){	
	$(":button").click( function() {
		//alert("2");
		//alert("id:" + $(this).attr('id'));
	 	//window.open("../../C:\\");
	 	//window.open();
	 	//alert(window.location);
	 	$.value  = myFunction($(this).attr('id'));
	 	//alert ($.value);

	 	//window.open("http://google.com");
	 	//window.open("file://sgw20036024/clearvision/");
	 	//window.open("file:" + $.value);
	 	window.open("resource/ViewShared" + $(this).attr('id') + ".bat");
	 	//window.open("resource/ViewSharedSGW20028335.bat");
	 	//window.open("http://www.google.com");
	});
	
	function myFunction(serverid)
	{
		//alert(serverid);

		<c:forEach items="${Server}" var="server">
			if ("${server.serverName}" === serverid)
			{
				//alert('return serverid:' + serverid);
				return "${server.serverSharedPath}";
				//break;
			}
			
			if ("${server.DRserverName}" === serverid)
			{
				//alert('return serverid:' + serverid);
				return "${server.DRserverSharedPath}";
				//break;
			}
		</c:forEach>
		
		/*
		<c:forEach items="${Server}" var="server">
		
			<c:if test="${fn:containsIgnoreCase(server.serverName,serverid)}">
				return ${server.serverSharedPath};
			</c:if>		
		
			<c:if test="${fn:containsIgnoreCase(server.DRserverName,serverid)}">
				return ${server.DRserverSharedPath};
			</c:if>		
			
		</c:forEach>
		*/
	}
});

</script>
</head>
<body>
<div align="center" style="color:white">
<table id="table1"><caption> <STRONG> ${captionName} </STRONG> <br> <br> </caption>
	<tr>
		<td> Server Description </td>
		<td> Prod Server Name </td>
		<td> DR Server Name </td>
	</tr>
	
	<c:forEach items="${Server}" var="server">
	        <tr>
	        	<td> ${server.serverDescription}</td>
				<td> 
					<input type="button" id="${server.serverName}" value="${server.serverName}"></input>
				</td>
				<td> 
					<input type="button" id="${server.DRserverName}" value="${server.DRserverName}"></input>
				</td>				
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
