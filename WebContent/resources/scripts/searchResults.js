window.onload = function() {
	 $(function(){
	      $("#includeHeader").load("header"); 
	    });
	 $(function(){
	      $("#includeFooter").load("footer"); 
	    });
}

window.addEventListener("resize", function() {
	if (window.matchMedia("(min-width: 1230px)").matches) {
    	
    	changeSearchResultsResolution(0);
    } else if(window.matchMedia("(min-width: 750px)").matches){
    	
    	changeSearchResultsResolution(1);
    }else{
    	
    	changeSearchResultsResolution(2);
    }
});		
	function CheckSizeOnLoad(){
		 if (window.matchMedia("(min-width: 1230px)").matches) {
		    	
		    	changeSearchResultsResolution(0);
		    } else if(window.matchMedia("(min-width: 750px)").matches){
		    	
		    	changeSearchResultsResolution(1);
		    }else{
		    	
		    	changeSearchResultsResolution(2);
		    }
	}
	
	
	
	function changeSearchResultsResolution(i){
		if(i==0){
			document.getElementById("results_1").style.width="1200px";
			

			var x=document.getElementsByClassName("results");
			var i;
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="1000px";
			}

			x=document.getElementsByClassName("results_details_big");
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="800px";
			}
			x=document.getElementsByClassName("results_details");
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="200px";
			}

		}else if(i==1){
			document.getElementById("results_1").style.width="750px";

			var x=document.getElementsByClassName("results");
			var i;
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="750px";
			}

			x=document.getElementsByClassName("results_details_big");
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="550px";
			}
			x=document.getElementsByClassName("results_details");
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="200px";
			}
		}else if(i==2){
			document.getElementById("results_1").style.width="90%";

			var x=document.getElementsByClassName("results");
			var i;
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="90%";
			}

			x=document.getElementsByClassName("results_details_big");
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="50%";
			}
			x=document.getElementsByClassName("results_details");
			for (i = 0; i < x.length; i++) {
			    x[i].style.width="50%";
			}
		}
	}
	
