<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>

<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/es5-shim/2.2.0/es5-shim.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-filter/0.5.8/angular-filter.min.js"></script>
<link rel="stylesheet" type="text/css" href="/LDODashBoard/css/mystyle.css" /> 
<meta charset="utf-8">

<script>
var app = angular.module('myApp', ['angular.filter']);
app.controller('myCtrl', function($scope,$http) {
    //$scope.appview1 =[{"jobName":"CTTCVS007D","description":"Start ClearVision SFE - Exchange Server","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS007D","description":"Start ClearVision SFE - Exchange Server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"LDOCVG008D","description":"Start Stream Gateway for SFE","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":"04/27 18:30:27","status":"ENDED OK","runDuration":"N  "},{"jobName":"LDOCVG008D","description":"Start Stream Gateway for SFE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVS008D","description":"Start ClearVision NZFOE - Exchange Server","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS008D","description":"Start ClearVision NZFOE - Exchange Server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"LDOCVG009D","description":"Start Stream Gateway for NZFOE","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":"04/27 18:30:26","status":"ENDED OK","runDuration":"N  "},{"jobName":"LDOCVG009D","description":"Start Stream Gateway for NZFOE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVS010D","description":"Start ClearVision TOCOM - Exchange server","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS010D","description":"Start ClearVision TOCOM - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"LDOCVG003D","description":"Start Stream Gateway for TOCOM","oDATE":"04/26","startTime":"04/27 06:00:13","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"LDOCVG003D","description":"Start Stream Gateway for TOCOM","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVS005D","description":"Start ClearVision TFX - Exchange server","oDATE":"04/26","startTime":"04/27 07:15:04","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS005D","description":"Start ClearVision TFX - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0715","runDuration":"N  "},{"jobName":"CTTCVS004D","description":"Start ClearVision TSE - Exchange server","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS004D","description":"Start ClearVision TSE - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"LDOCVG007D","description":"Start Stream Gateway for TSE","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"LDOCVG007D","description":"Start Stream Gateway for TSE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVM005D","description":"Start ClearVision SFE/NZ - MemberServer","oDATE":"04/26","startTime":"04/27 02:00:07","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM005D","description":"Start ClearVision SFE/NZ - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0200","runDuration":"N  "},{"jobName":"CTTCVC004D","description":"Start ClearVision OSE/TSE/TFX/SFE/NZ-Security","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC004D","description":"Start ClearVision OSE/TSE/TFX/SFE/NZ-Security","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVM004D","description":"Start ClearVision OSE/TSE/TFX - MemberServer","oDATE":"04/26","startTime":"04/27 06:05:03","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM004D","description":"Start ClearVision OSE/TSE/TFX - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0605","runDuration":"N  "},{"jobName":"CTTCVM007D","description":"Start ClearVision TOCOM - MemberServer","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM007D","description":"Start ClearVision TOCOM - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVC006D","description":"Start ClearVision TOCOM - SecurityServer","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC006D","description":"Start ClearVision TOCOM - SecurityServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVM006D","description":"Start ClearVision SGX - MemberServer","oDATE":"04/26","startTime":"04/27 07:05:02","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM006D","description":"Start ClearVision SGX - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0705","runDuration":"N  "},{"jobName":"CTTCVC005D","description":"Start ClearVision SGX - SecurityServer","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC005D","description":"Start ClearVision SGX - SecurityServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVS003D","description":"Start ClearVision HKFE - Exchange server","oDATE":"04/26","startTime":"04/27 04:58:00","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS003D","description":"Start ClearVision HKFE - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0458","runDuration":"N  "},{"jobName":"CTTCVM001D","description":"Start ClearVision HKFE - MemberServer","oDATE":"04/26","startTime":"04/27 03:05:01","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM001D","description":"Start ClearVision HKFE - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0305","runDuration":"N  "},{"jobName":"CTTCVC001D","description":"Start ClearVision HKFE - SecurityServer","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC001D","description":"Start ClearVision HKFE - SecurityServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVC003D","description":"Start ClearVision SGX - Exchange server","oDATE":"04/26","startTime":"04/27 08:28:01","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC003D","description":"Start ClearVision SGX - Exchange server","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0828","runDuration":"N  "},{"jobName":"CTTCVS001D","description":"Start ClearVision 4M01","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS001D","description":"Start ClearVision 4M01","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVS002D","description":"Start ClearVision 4M02","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS002D","description":"Start ClearVision 4M02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVM002D","description":"Start ClearVision 4M - MemberServer","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM002D","description":"Start ClearVision 4M - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVC002D","description":"Start ClearVision 4M - SecurityServer","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC002D","description":"Start ClearVision 4M - SecurityServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVS006D","description":"Start ClearVision Welcome Table","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVS011D","description":"Start ClearVision KSE 4M","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVS011D","description":"Start ClearVision KSE 4M","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVM008D","description":"Start ClearVision KSE 4M - MemberServer","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVM008D","description":"Start ClearVision KSE 4M - MemberServer","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVC004D","description":"Start ClearVision OSE/TSE/TFX/SFE/NZ-Security","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVC004D","description":"Start ClearVision OSE/TSE/TFX/SFE/NZ-Security","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVB001D","description":"Start ExportBack for HKFE","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB001D","description":"Start ExportBack for HKFE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVB002D","description":"Start ExportBack for HKFE Equities","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB002D","description":"Start ExportBack for HKFE Equities","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVB003D","description":"Start ExportBack for TOCOM 4M","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB003D","description":"Start ExportBack for TOCOM 4M","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVB004D","description":"Start ExportBack for SFE, NZFOE, 4M","oDATE":"04/26","startTime":"04/27 04:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB004D","description":"Start ExportBack for SFE, NZFOE, 4M","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0400","runDuration":"N  "},{"jobName":"CTTCVB005D","description":"Start ExportBack for SFE, NZFOE, 4M Equities","oDATE":"04/26","startTime":"04/27 04:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB005D","description":"Start ExportBack for SFE, NZFOE, 4M Equities","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0400","runDuration":"N  "},{"jobName":"CTTCVB006D","description":"Start ExportBack for SGX","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB006D","description":"Start ExportBack for SGX","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVB007D","description":"Start ExportBack for TOCOM CV","oDATE":"04/26","startTime":"04/27 06:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB007D","description":"Start ExportBack for TOCOM CV","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0600","runDuration":"N  "},{"jobName":"CTTCVB008D","description":"Start ExportBack for OSE, TFX, TSE","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVB008D","description":"Start ExportBack for OSE, TFX, TSE","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "},{"jobName":"CTTCVF001D","description":"Start EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF001D","description":"Start EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVF002D","description":"Start F2B Orders EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF002D","description":"Start F2B Orders EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVF003D","description":"Start Reporting EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 07:00:12","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF003D","description":"Start Reporting EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0700","runDuration":"N  "},{"jobName":"CTTCVF004D","description":"Start Allocation EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 04:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF004D","description":"Start Allocation EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0400","runDuration":"N  "},{"jobName":"CTTCVF005D","description":"Start Utility EFlow for PSGCLV02","oDATE":"04/26","startTime":"04/27 03:00:05","endTime":" ","status":"Executing","runDuration":"N  "},{"jobName":"CTTCVF005D","description":"Start Utility EFlow for PSGCLV02","oDATE":"04/27","startTime":" ","endTime":" ","status":"Wait Time 0300","runDuration":"N  "}];
        
    /**
    $http.get("http://localhost:8080/LDODashBoard/AppViewJson.json?appid=CV-APAC-CHECKOUT")
    .then(function(response) {
        $scope.appview1 = response.data;
        $scope.status = response.status;
       
    },
    function(errResponse){
        console.error('Error while fetching users');
    }
    ); 
    **/
    
    $scope.appview1 = ${appviewbatch};
    
	
    $scope.GetoDATEValue = function () {
        $scope.oDATESelectId = $scope.selectedItem1.oDATE;
        <!-- window.alert("Selected Value: " + selectId + "\nSelected Text: " + selectName ); -->
    };
    
    $scope.GetStatusValue = function () {
        $scope.statusSelectId = $scope.selectedItem2.status;
        <!-- window.alert("Selected Value: " + selectId + "\nSelected Text: " + selectName ); -->
    };
});
</script>



<body>


	<div ng-app="myApp" ng-controller="myCtrl" style="color:white" div align="left">
	
	   ODATE:
	   <select ng-model="selectedItem1" ng-options="appview2.oDATE for appview2 in appview1 | unique:'oDATE'" ng-change="GetoDATEValue()">
   	   </select>
   	   
   	   &nbsp;
   	   STATUS:
	   <select ng-model="selectedItem2" ng-options="appview2.status for appview2 in appview1 | unique:'status'" ng-change="GetStatusValue()">
   	   </select>
		
		<br><br>
		<div style="color:yellow" > <STRONG> JOBNAME &nbsp; START TIME &nbsp; END TIME &nbsp; DESCRIPTION</STRONG> </div>
		
		<div ng-repeat="view in appview1" ng-if="view.status == statusSelectId && view.oDATE == oDATESelectId" align=left>
         {{view.jobName}} &nbsp; {{view.startTime}} &nbsp; {{view.endTime}} &nbsp; {{view.description}} 
        </div>
            
   
	<br><br>
	<c:url value="/L1OutputDisplayPage?gcmmLink2=true" var="messageUrl2" />
	Click <a href="${messageUrl2}" style="color:yellow" onclick="doSomething('displayContent','/LDODashBoard/L1OutputDisplayPage?','gcmmLink2=true');return false;"> Here</a>
	 to Close
 
	</div>
	



</body>
</html>
