<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<script src="/LDODashBoard/js/scripts.js" language="JavaScript" type="text/javascript"></script> 
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/es5-shim/2.2.0/es5-shim.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-filter/0.5.8/angular-filter.min.js"></script>
<link rel="stylesheet" type="text/css" href="/LDODashBoard/css/mystyle.css" /> 

<script>


angular.module("myApp", [])
.controller("myCtrl", function ($scope,$http){
 $scope.display = ${DisplayResponse.cvAuditClassList};
 //$scope.display = [{"name":"ABN","account":"jkj"}];

});
</script>

<link rel="stylesheet" type="text/css"	href="/LDODashBoard/css/mystyle.css" />
	
<title>L1 DashBoard Display page</title>

<body id="body">
<h1 id="h1"> </h1>

<div ng-app="myApp" ng-controller="myCtrl" style="color:white">
{{ display[0].db_stamp_string }}

</div>
${message}
</body>
</html>




