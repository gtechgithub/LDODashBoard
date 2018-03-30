/**
 * 
 */


angular.module("myApp", ['angular.filter'])
  .controller("myCtrl", function ($scope,$http,$compile)
  {
    $scope.textareavalue = "";

	    $scope.marketArray = [
		{id:"1" , name:"LCHFR" , database:"PLN20100.WORLD"},
		{id:"2" , name:"TA" , database:"PLN20100.WORLD"},
		{id:"3" , name:"VOB" , database:"PLN20100.WORLD"},
		{id:"4" , name:"TURK" , database:"PLN20100.WORLD"},
		{id:"5" , name:"ERX" , database:"PLN20100.WORLD"},
		{id:"6" , name:"ABN" , database:"PLN20100.WORLD"},
		{id:"7" , name:"FTX" , database:"PLN20100.WORLD"},
		{id:"8" , name:"IDEM" , database:"PLN20100.WORLD"},
		{id:"9" , name:"KSEE_SWI" , database:"PLN20100.WORLD"},
		{id:"10" , name:"LTOM_SWI" , database:"PLN20100.WORLD"},
		{id:"11" , name:"MEFFRV" , database:"PLN20100.WORLD"},
		{id:"12" , name:"MIL_SWI" , database:"PLN20100.WORLD"},
		{id:"13" , name:"OSL" , database:"PLN20100.WORLD"},
		{id:"14" , name:"STO" , database:"PLN20100.WORLD"},
		{id:"15" , name:"ADEX" , database:"PLN20100.WORLD"},
		{id:"16" , name:"ATHSE" , database:"PLN20100.WORLD"},
		{id:"17" , name:"SAF" , database:"PLN20100.WORLD"},
		{id:"18" , name:"SOM" , database:"PLN20100.WORLD"},
		{id:"19" , name:"KSC" , database:"PLN20100.WORLD"},
		{id:"20" , name:"7I" , database:"PLN20100.WORLD"},
		{id:"21" , name:"7V" , database:"PLN20100.WORLD"},
		{id:"22" , name:"EUX" , database:"PLN20100.WORLD"},
		{id:"23" , name:"MFM" , database:"PLN20100.WORLD"},
		{id:"24" , name:"MIFE" , database:"PLN20100.WORLD"},
		{id:"25" , name:"TKD" , database:"PLN20100.WORLD"},
		{id:"26" , name:"Monep" , database:"PLN20300.WORLD"},
		{id:"27" , name:"LIFFECOM" , database:"PLN20300.WORLD"},
		{id:"28" , name:"LME_NE" , database:"PLN20300.WORLD"},
		{id:"29" , name:"SGWO" , database:"PLN20300.WORLD"},
		{id:"30" , name:"XLIF" , database:"PLN20300.WORLD"},
		{id:"31" , name:"LCE" , database:"PLN20300.WORLD"},
		{id:"32" , name:"Matif" , database:"PLN20300.WORLD"},
		{id:"33" , name:"28" , database:"PLN20300.WORLD"},
		{id:"34" , name:"BFX" , database:"PLN20300.WORLD"},
		{id:"35" , name:"LIFFE" , database:"PLN20300.WORLD"},
		{id:"36" , name:"LME" , database:"PLN20300.WORLD"}
    ];

    
    $scope.submitfunction = function() {
    	
		var dataObj = {
				name : getById($scope.marketArray,$scope.marketArraySel.id),
				account : $scope.accountName,
				database : getDatabaseById($scope.marketArray,$scope.marketArraySel.id)
		};
    	
   	
    	$http({
            'url' : '/LDODashBoard/AuditDisplayPost',
            'method' : 'POST',
            'headers': {'Content-Type' : 'application/json'},
            'data' : dataObj
    	})     
        .success(function(data, status, headers,config,ele) {
        							
                                   window.alert('Success3');
                                   $scope.message = data;
								   
								   //covert the json object to string 
                                   var message1 = JSON.stringify($scope.message);
                                   
                                   window.alert('message1:' + message1);
                                   //window.location("/LDODashBoard/AuditDisplayPostResponse?argument1=" + "hello");
                                   window.location("/LDODashBoard/AuditDisplayPostResponse?argument1=" + message1);
/*                                   
                                   {"cvAuditClassList":[{"db_seq":13084,"operator":"cricha19","action":"I","cv_table":12,"id":3006538,"comments":"Acc:YP2MD;Firm:L;Off:;Sungard:;"},{"db_seq":31244,"operator":"cricha19","action":"I","cv_table":12,"id":3014027,"comments":"Acc:YP2MD;Firm:L;Off:;Sungard:;"},{"db_seq":40739,"operator":"gdennis2","action":"U","cv_table":5,"id":3014027,"comments":"Clair:AAKASH AGARWAL;"},{"db_seq":56740,"operator":"mzak1","action":"I","cv_table":12,"id":3043260,"comments":"Acc:YP3CW;Firm:L;Off:;Sungard:;"},{"db_seq":56748,"operator":"mzak1","action":"I","cv_table":12,"id":3043264,"comments":"Acc:YP3CW;Firm:L;Off:;Sungard:;"},{"db_seq":52647,"operator":"bkamins11","action":"I","cv_table":12,"id":3041524,"comments":"Acc:YP3CW;Firm:L;Off:;Sungard:;"},{"db_seq":76771,"operator":"rmarczak","action":"I","cv_table":12,"id":3053777,"comments":"Acc:YP3CW;Firm:L;Off:;Sungard:;"},{"db_seq":76772,"operator":"rmarczak","action":"I","cv_table":13,"id":3053777,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":74719,"operator":"iteppel","action":"I","cv_table":13,"id":3043264,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":74749,"operator":"iteppel","action":"I","cv_table":13,"id":3043260,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":79437,"operator":"aimierow","action":"I","cv_table":12,"id":3054314,"comments":"Acc:YP2MD;Firm:L;Off:;Sungard:;"},{"db_seq":79438,"operator":"aimierow","action":"I","cv_table":13,"id":3054314,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":113297,"operator":"iteppel","action":"I","cv_table":12,"id":3106380,"comments":"Acc:B825M;Firm:L;Off:;Sungard:;"},{"db_seq":113298,"operator":"iteppel","action":"I","cv_table":13,"id":3106380,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":113299,"operator":"iteppel","action":"U","cv_table":5,"id":3106380,"comments":"Clair:cs;"},{"db_seq":113300,"operator":"iteppel","action":"I","cv_table":9,"id":3006538,"comments":"Name:%GI-YP2MD%;Broker:%;Ctr:%;CbType:0;AccOptFut:47;CtrType:;"},{"db_seq":113301,"operator":"iteppel","action":"D","cv_table":9,"id":3006538},{"db_seq":113302,"operator":"iteppel","action":"I","cv_table":9,"id":3006538,"comments":"Name:%GI-YP2MD%;Broker:CSBLO;Ctr:%;CbType:0;AccOptFut:47;CtrType:;"},{"db_seq":113303,"operator":"iteppel","action":"D","cv_table":9,"id":3006538},{"db_seq":113304,"operator":"iteppel","action":"I","cv_table":9,"id":3006538,"comments":"Name:%GI-YP2MD%;Broker:CSILO;Ctr:%;CbType:0;AccOptFut:47;CtrType:;"},{"db_seq":109823,"operator":"mskiba","action":"I","cv_table":12,"id":3082104,"comments":"Acc:YP2MD;Firm:L;Off:;Sungard:;"},{"db_seq":109824,"operator":"mskiba","action":"I","cv_table":13,"id":3082104,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":125379,"operator":"mpeitsc1","action":"I","cv_table":12,"id":3118253,"comments":"Acc:YP2MD;Firm:L;Off:;Sungard:;"},{"db_seq":125380,"operator":"mpeitsc1","action":"I","cv_table":13,"id":3118253,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":125382,"operator":"mpeitsc1","action":"I","cv_table":12,"id":3118254,"comments":"Acc:YP2MD;Firm:L;Off:;Sungard:;"},{"db_seq":125383,"operator":"mpeitsc1","action":"I","cv_table":13,"id":3118254,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":125385,"operator":"mpeitsc1","action":"I","cv_table":12,"id":3118255,"comments":"Acc:YP2MD;Firm:L;Off:;Sungard:;"},{"db_seq":125386,"operator":"mpeitsc1","action":"I","cv_table":13,"id":3118255,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"},{"db_seq":125388,"operator":"mpeitsc1","action":"I","cv_table":12,"id":3118256,"comments":"Acc:YP2MD;Firm:L;Off:;Sungard:;"},{"db_seq":125389,"operator":"mpeitsc1","action":"I","cv_table":13,"id":3118256,"comments":"Idcp1:CREDIT SUISSE;Idcp2:;Idcp3:;"}]}
*/
                                })
        .error(function(data, status, headers, config) {
                                    window.alert('Error2');
                                    $scope.message1 =  data;
         });
        
        window.alert("alert4");
    };
    
    $scope.getMarketFullName = function (market)
    {
      return market.name;
    };
    
    $scope.selectmarketByName = function (id)
    {
      var temp = searchpart($scope.marketArray,id);
      $scope.marketArraySel = {id: temp};
    };
    
   $scope.updateChanges = function() {
	   $scope.textareavalue = "Market:" + getById($scope.marketArray,$scope.marketArraySel.id) + "\n\nAccount:" + $scope.accountName + "\n\nDatabase:" + getDatabaseById($scope.marketArray,$scope.marketArraySel.id);
   };
   
   $scope.selectedExpression = function() {
   	   $scope.textareavalue = "Market:" + getById($scope.marketArray,$scope.marketArraySel.id) + "\n\nAccount:" + $scope.accountName + "\n\nDatabase:" + getDatabaseById($scope.marketArray,$scope.marketArraySel.id);

   };

   function getById(arr, id) {
            for (var d = 0, len = arr.length; d < len; d += 1) {
                if (arr[d].id === id) {
                    return arr[d].name; 
                }
            }
        }

	function getDatabaseById(arr,id) {
	   for (var d = 0, len = arr.length; d < len; d += 1) {
		   if (arr[d].id == id) {
			   return arr[d].database;
		   }
	   }
   	}
    function searchpart(arr,name) {
            for (var d = 0, len = arr.length; d < len; d += 1) {
                
                if( arr[d].name.toLowerCase().indexOf(name) >= 0 ) {
                    return arr[d].id;
                }
            }
   }
    
   $scope.reset = function() {
	   
	   $scope.marketArraySel = "";
	   $scope.accountName = "";
	   $scope.textareavalue="";
	   
	   window.alert("hello");
   };
   
  });
  