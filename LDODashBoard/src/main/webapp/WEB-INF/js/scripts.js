/**
 * 
 */

/**
 *  this function will break out from the iframe and display 
 * in the parent/top window
 */
function breakout_of_frame()
 {
   if (top.location != location) {
     top.location.href = document.location.href ;
   }
 }

/** this function will display the redirectUrl in the iframe window
 * 
 * @param redirectUrl
 * @param UrlValue
 */
function doSomething(iframeToFocus, redirectUrl, UrlValue){
	//alert ('doSomething:' + iframeToFocus);
	var ifrm = parent.document.getElementById(iframeToFocus);
	var doc = ifrm.contentDocument? ifrm.contentDocument: ifrm.contentWindow.document;
	//alert (doc.getElementById("h1").innerHTML);
	
	//set focus by assigning some space
	//please make sure the h1 is available in the current page of iframe to focus
	//doc.getElementById("h1").innerHTML = " ";
	var urlString = redirectUrl + UrlValue;
	//alert (urlString);
	ifrm.contentWindow.location.href = urlString;
}




/** this function will display the redirectUrl in the iframe window
 * 
 * @param redirectUrl
 * @param UrlValue
 */
function doFocus(iframeToFocus, redirectUrl, UrlValue){
	alert ('iframeToFocus:' + iframeToFocus);
	var ifrm = parent.document.getElementById(iframeToFocus);
	var doc = ifrm.contentDocument? ifrm.contentDocument: ifrm.contentWindow.document;
	//alert (doc.getElementById("h1").innerHTML);
	
	//set focus by assigning some space
	//please make sure the h1 is available in the current page of iframe to focus
	doc.getElementById("h1").innerHTML = " ";
}

/**
 * this function will resize the iframe accordingly based on client window
 */
function resizeIframe(obj) {
	  //alert(screen.height);
  obj.height = screen.height;
}

function happycode(){
	   alert('helo');
}


/**************************************************************/
/* Prepares the cv to be dynamically expandable/collapsible   */
/**************************************************************/
function prepareList() {
    $('#expList').find('li:has(ul)')
    .click( function(event) {
        if (this == event.target) {
            $(this).toggleClass('expanded');
            $(this).children('ul').toggle('medium');
        }
        return false;
    })
    .addClass('collapsed')
    .children('ul').hide();
};


/**************************************************************/
/* Functions to execute on loading the document               */
/**************************************************************/
$(document).ready( function() {
    prepareList()
});