window.onload = function() {
	var news_id = getUrlParameter('news_id');
	 $(function(){
	      $("#includeHeader").load("header"); 
	    });
	 $(function(){
	      $("#includeComments").load("comments?type=1&id="+news_id); 
	    });
	 $(function(){
	      $("#includeFooter").load("footer"); 
	    });
}


var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}