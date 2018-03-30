
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript"></script>


<link rel="stylesheet" type="text/css"	href="/LDODashBoard/css/mystyle.css" />
<link rel="stylesheet" type="text/css"	href="/LDODashBoard/css/ListMenu.css" />

<!--
<script>

 $(document).ready(function () {
        $('#nav li').click(
        function () {
            //show submenu
            $('ul', this).show();
        });
		
		$('#nav li').dblclick(
        function () {
            //show submenu
            $('ul', this).hide();
        });
		
 });
</script>
 -->
	
<title>L1 DashBoard Link page</title>
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
	      <a href="#">Host Checkout</a>
			   <ul>	      
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
			   </ul>
		   </li>    
	       <!-- 
	      <li>
	      	<c:url value="/AppView?appid=true" var="messageUrl6" />
			<a href="${messageUrl6}" onClick="doSomething('displayContent','/LDODashBoard/AppView?','appid=CV-APAC-CHECKOUT');return false;"> Asia Jobs</a> <br>			
	       </li>
	        -->
	        
	      <li>
	      <a href="#">Control-M Checkout</a>
			   <ul>	      
			      <li>
			      	<c:url value="/AppView?appid=true" var="messageUrl7" />
					<a href="${messageUrl7}" onClick="doSomething('displayContent','/LDODashBoard/AppViewJson?','appid=CV-APAC-CHECKOUT');return false;"> Asia Jobs</a> <br>			
			       </li>	       
			      <li>
			      	<c:url value="/AppView?appid=true" var="messageUrl8" />
					<a href="${messageUrl8}" onClick="doSomething('displayContent','/LDODashBoard/AppViewJson?','appid=CV-EMEA-CHECKOUT');return false;"> Emea Jobs</a> <br>			
			       </li>
			      <li>
			      	<c:url value="/AppView?appid=true" var="messageUrl9" />
					<a href="${messageUrl9}" onClick="doSomething('displayContent','/LDODashBoard/AppViewJson?','appid=CV-AMER-CHECKOUT');return false;"> America Jobs</a> <br>			
			       </li>	   	       	       
			   </ul>
		   </li>        	       
	       <li>
		      	<c:url value="/AppView?appid=true" var="messageUrl10" />
				<a href="${messageUrl10}" onClick="doSomething('displayContent','/LDODashBoard/AuditDisplay','');return false;"> Audit Display</a> <br>			
	       
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
	    <li>
	      	<c:url value="/AppView?appid=true" var="messageUrl6" />
			<a href="${messageUrl6}" onClick="doSomething('displayContent','/LDODashBoard/AppView?','appid=LDM-RECS1');return false;"> LDM-RECS1</a> <br>
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
	      	<c:url value="/monitorUnixProcess" var="messageUrl11" />
			<a href="${messageUrl11}" onClick="doSomething('displayContent','/LDODashBoard/monitorUnixProcess?','processName=COMFEE04D');return false;">  COMFEE04D Monitor</a> <br>
		</li>
	    <li>
	      	<c:url value="/AppView?appid=true" var="messageUrl6" />
			<a href="${messageUrl6}" onClick="doSomething('displayContent','/LDODashBoard/AppView?','appid=CDR-COMMFEE');return false;"> CDR Jobs</a> <br>
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
	    <li>
	       <c:url value="/L1OutputDisplayPage?dbCheckout=true" var="messageUrl12" />
		   <a href="${messageUrl12}" onClick="doSomething('displayContent','/LDODashBoard/databaseCheckout?','dbCheckout=MDDBCHECKOUT');return false;"> DB Checkout </a> <br>
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
	 	<li>
	      	<c:url value="/monitorUnixProcess" var="messageUrl13" />
			<a href="${messageUrl13}" onClick="doSomething('displayContent','/LDODashBoard/monitorUnixProcess?','processName=GCMLANDINGCHECHKOUT');return false;"> Landing Feeds</a> <br>
		</li>
		
	 </ul>
    </li>
    
  </ul>
         

	
</body>
</html>

