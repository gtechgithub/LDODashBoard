
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript"></script>


<link rel="stylesheet" type="text/css"	href="/LDODashBoard/css/mystyle.css" />
<link rel="stylesheet" type="text/css"	href="/LDODashBoard/css/ListMenu.css" />


<title>L2 DashBoard Link page</title>
</head>

<body id="body">

<ul id="expList"> 
  	<li>
    <a href="#">Clearvision</a>
      <ul> 
	      <li>
	      <a href="#">Database Checkout</a>
		   <ul>
		      <li>
	    	  	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl1" />
				<a href="${messageUrl1}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=true');return false;"> Asia</a> <br>
		       </li>
	         <li>
	      	   <c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl1" />
			   <a href="${messageUrl1}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=CVEMEADBCHECKOUT');return false;"> Europe</a> <br>
	         </li>
	         <li>
	      	   <c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl1" />
			   <a href="${messageUrl1}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=CVAMERICADBCHECKOUT');return false;"> America</a> <br>
	         </li>
	         
		   </ul>
	      </li>
	      <li>
	      	<c:url value="/L1OutputDisplayPage?hostCheckout=true" var="messageUrl2" />
			<a href="${messageUrl2}" onClick="doSomething('displayContent','/LDODashBoard/hostCheckout?','checkout=ASIA');return false;"> Asia Host Checkout</a> <br>
	       </li>
	      <li>
	      	<c:url value="/L1OutputDisplayPage?hostCheckout=true" var="messageUrl3" />
			<a href="${messageUrl3}" onClick="doSomething('displayContent','/LDODashBoard/hostCheckout?','checkout=EMEA');return false;"> EMEA Host Checkout</a> <br>
	       </li>
	      <li>
	      	<c:url value="/L1OutputDisplayPage?hostCheckout=true" var="messageUrl4" />
			<a href="${messageUrl4}" onClick="doSomething('displayContent','/LDODashBoard/hostCheckout?','checkout=AMERICA');return false;"> America Host Checkout</a> <br>
	       </li>
           <li>
	      	<c:url value="/L2OutputDisplayPage?passwordlessremote=true" var="messageUrl1" />
			<a href="${messageUrl1}" onClick="doSomething('displayContent','/LDODashBoard/passwordlessremote?','checkout=true');return false;"> PasswordLess MSTSC</a> <br>
	       </li>
      </ul>	       
    </li>
    <li>
    <a href="#">LDM</a>
	 <ul>
	 	<li>
	 		<c:url value="/L1OutputDisplayPage?ldmUnprocessedLink=true" var="messageUrl5" />
			<a href="${messageUrl5}" onClick="doSomething('displayContent','/LDODashBoard/L1OutputDisplayPage?','ldmUnprocessedLink=true');return false;">LDM/EXMAN count</a> <br>
	 	</li>
	     <li>
	      	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl6" />
			<a href="${messageUrl6}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=LDMDB');return false;"> Database Checkout</a> <br>
	     </li>
  	 	
	 </ul>
    </li>
    <li>
    <a href="#">GCMM </a>
	 <ul>
	 	<li>
			<c:url value="/L1OutputDisplayPage?gcmmLink2=true" var="messageUrl7" />
			<a href="${messageUrl7}" onClick="doSomething('displayContent','/LDODashBoard/L1OutputDisplayPage?','gcmmLink2=true');return false;"> GCMM process check</a>	<br>
	 	</li>
	 	<li>
	      	<c:url value="/L1OutputDisplayPage?lnkConnectionGroupCheck=true" var="messageUrl10" />
			<a href="${messageUrl10}" onClick="doSomething('displayContent','/LDODashBoard/lnkConnectionGroupCheck?','lnkConnectionGroup=gcmmlink');return false;">  URL Connection</a> <br>
		</li>
	 	
	 </ul>
    </li>
    <li>
    <a href="#">OPUS </a>
	 <ul>
	     <li>
	      	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl8" />
			<a href="${messageUrl8}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=OPUSDB');return false;"> Database Checkout</a> <br>
	     </li>
	 </ul>
    </li>
    <li>
    <a href="#">CDR </a>
	 <ul>
	     <li>
	      	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl9" />
			<a href="${messageUrl9}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=CDRDB');return false;"> Database Checkout</a> <br>
	     </li>

	 	<li>
	      	<c:url value="/L1OutputDisplayPage?lnkConnectionGroupCheck=true" var="messageUrl10" />
			<a href="${messageUrl10}" onClick="doSomething('displayContent','/LDODashBoard/lnkConnectionGroupCheck?','lnkConnectionGroup=cdrlink');return false;">  URL Connection</a> <br>
		</li>

	 	<li>
	      	<c:url value="/commfee04Monitor" var="messageUrl11" />
			<a href="${messageUrl11}" onClick="doSomething('displayContent','/LDODashBoard/commfee04Monitor','');return false;">  COMFEE04D Monitor</a> <br>
		</li>
	     
	 </ul>
    </li>
    <li>
    <a href="#">TLM </a>
	 <ul>
	 	<li>
	      	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl10" />
			<a href="${messageUrl10}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=TLMDB');return false;"> DB Checkout</a> <br>
		</li>
	 	<li>
	      	<c:url value="/L1OutputDisplayPage?lnkConnectionGroupCheck=true" var="messageUrl10" />
			<a href="${messageUrl10}" onClick="doSomething('displayContent','/LDODashBoard/lnkConnectionGroupCheck?','lnkConnectionGroup=tlmlink');return false;">  URL Connection</a> <br>
		</li>

		
	 </ul>
    </li>    
    <li>
    <a href="#">Match Derivatives </a>
	 <ul>
	 	<li>
	      	<c:url value="/L1OutputDisplayPage?hostCheckout=true" var="messageUrl10" />
			<a href="${messageUrl10}" onClick="doSomething('displayContent','/LDODashBoard/hostCheckout?','checkout=MatchDerivative');return false;"> MD Host Checkout</a> <br>
		</li>
		
	 	<li>
	      	<c:url value="/L1OutputDisplayPage?lnkConnectionGroupCheck=true" var="messageUrl11" />
			<a href="${messageUrl11}" onClick="doSomething('displayContent','/LDODashBoard/lnkConnectionGroupCheck?','lnkConnectionGroup=matchlink');return false;">  URL Connection</a> <br>
		</li>
	 </ul>
	 
	  
    </li>

    <li>
    <a href="#">Global CashMan </a>
	 <ul>
	 	<li>
	      	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl12" />
			<a href="${messageUrl12}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=GCMTLMDB');return false;"> DB Checkout</a> <br>
		</li>

	 	<li>
	      	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl12" />
			<a href="${messageUrl12}" onClick="doSomething('displayContent','/LDODashBoard/ExecuteQueryResults?','QueryIDName=ClusterInfo');return false;"> Cluster Info</a> <br>
		</li>

	 	<li>
	      	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl12" />
			<a href="${messageUrl12}" onClick="doSomething('displayContent','/LDODashBoard/ExecuteQueryResults?','QueryIDName=MatchProgs');return false;"> Progs Info</a> <br>
		</li>
	 	<li>
	      	<c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl12" />
			<a href="${messageUrl12}" onClick="doSomething('displayContent','/LDODashBoard/ExecuteQueryResults?','QueryIDName=SysControl');return false;"> SysControl Info</a> <br>
		</li>
		
	 </ul>
    </li>
    
  </ul>
         

	
</body>
</html>
