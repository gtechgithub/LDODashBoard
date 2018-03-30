<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>

<meta charset="utf-8">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript"></script>
<script src="/LDODashBoard/js/AuditDisplayPage.js" language="JavaScript" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-filter/0.5.8/angular-filter.min.js"></script>
<link rel="stylesheet" type="text/css" href="/LDODashBoard/css/mystyle.css" />
<link rel="stylesheet" type="text/css" href="/LDODashBoard/css/AuditDisplayPage.css" />



<body>


	<div ng-app="myApp" ng-controller="myCtrl" style="color:white" div align="left">

		<br/><br/>	
		<form name = "audit" novalidate>
	
			<label for="marketArraySel" >Market:</label>
			<select id="marketArraySel" ng-model="marketArraySel" ng-options="market as getMarketFullName(market) for market in marketArray | orderBy:'name' track by market.id" ng-change="updateChanges()" ng-selected="selectedExpression()">
		    <option value="">[NO SELECTION]</option>
		    </select>

			&nbsp;
			<label for="accountText" >Account:</label>
			<input type="text" id="accountText" ng-model="accountName" ng-change="onAccountTextChange()"></input>	

			<br/>
		    <label for="marketNameType" >Type the Market:</label>
		    <input type="text" id="marketNameType" ng-model="marketNameType" ng-change="selectmarketByName(marketNameType)" /> 		    		    
			
			<br/><br/>
			
			<label for="textareavalue" ng-show="marketArraySel.id && accountName">Selected Details:</label>
			<textarea id="textareavalue" ng-model="textareavalue" style="color:blue" disabled ng-show="marketArraySel.id && accountName">{{textareavalue}}</textarea>
			<br />
			<!-- 
		    <span size=10><STRONG> Selected Market: {{marketArraySel.name}} </STRONG> </span>
		    
		    <span id="tab"></span>
		    <span id="tab"></span>
		    <span id="tab"></span>
		   	<span id="tab"><STRONG> Selected Account: {{accountName}} </STRONG></span>

            -->
		   	
			<br/> <br/>
			
			<input type="reset" ng-click="reset()" value="RESET"></input>
			&nbsp;
			&nbsp;
			<input type="button" ng-click="submitfunction()" value="SUBMIT" ng-disabled="!marketArraySel.id && !accountName"></input>
		</form>

	<br><br>
	
	<span id="tab"></span><span id="tab"></span><span id="tab"></span><span id="tab"></span><span id="tab"></span><span id="tab"></span><span id="tab"></span><span id="tab"></span>
	<c:url value="/L1OutputDisplayPage?gcmmLink2=true" var="messageUrl2" />
	Click <a href="${messageUrl2}" style="color:yellow" onclick="doSomething('displayContent','/LDODashBoard/L1OutputDisplayPage?','gcmmLink2=true');return false;"> Here</a>
	 to Close
 
	</div>
</body>
</html>
