window.onload = function() {
	
	var tmdb_id = getUrlParameter('tmdb_id');
	 $.post("getSerieStatus",
			    {
			        tmdb_id: tmdb_id,
			    },
			    function(response){
			        if(response=="1"){
			        	watchedButton(1);
			        }else{
			        	watchedButton(0);
			        }
			    });
	 $.post("getRating",
			    {
			        tmdb_id: tmdb_id,
			    },
			    function(response){
			    	if(response>0){
			    		//click on star;
			    		document.getElementsByName("rating")[20-(response*2)].checked = true;
			    	}
			    });
	 CheckSizeOnLoad();
	 
	 $(function(){
	      $("#includeHeader").load("header"); 
	    });
	 $(function(){
	      $("#addRole").load("getAddRole"); 
	    });
	 $(function(){
	      $("#includeSeasons").load("seasons?tmdb_id="+tmdb_id); 
	    });
	 $(function(){
	      $("#includeComments").load("comments?type=0&id="+tmdb_id); 
	    });
	 $(function(){
	      $("#includeFooter").load("footer"); 
	    });
	 document.getElementById("serialHideButton").click()
}

window.addEventListener("resize", function() {
		 if (window.matchMedia("(min-width: 767px)").matches) {
			 changeResolution(0);
		    
		    } else if(window.matchMedia("(min-width: 576px)").matches){
		    	changeResolution(1);
		    	
		    }else{
		    	changeResolution(2);
		    	
		    }
});		
	function CheckSizeOnLoad(){
		 if (window.matchMedia("(min-width: 767px)").matches) {
			 changeResolution(0);
		    
		    } else if(window.matchMedia("(min-width: 576px)").matches){
		    	changeResolution(1);
		    	
		    }else{
		    	changeResolution(2);
		    }
	}
	
	
	function changeResolution(i){
		if(i==0){
			document.getElementsByClassName("overview")[0].style.fontSize="20px";
		}else if(i==1){
			document.getElementsByClassName("overview")[0].style.fontSize="18px";			
		}else if(i==2){
			document.getElementsByClassName("overview")[0].style.fontSize="16px";
		}
	}
	
	
	function watchedButton(i){
		if(i==0){
			document.getElementById("watched_button").innerHTML ="Set as watched";
			document.getElementById("watched_button").style.borderStyle="";
			document.getElementById("watched_button").style.borderColor="";
			document.getElementById("watched_button").style.backgroundColor="";
			document.getElementById("watched_button").style.color="";
			document.getElementById("watched_button").style.fontWeight="";
			document.getElementsByClassName("rating")[0].style.height ="";
			//click on last star;
    		document.getElementsByName("rating")[19].checked = true;
    		//unclick last star to uncheck all stars
    		document.getElementsByName("rating")[19].checked = false;
			document.getElementById("rating-stars").style.visibility="hidden";
			
		}else{
			document.getElementById("watched_button").innerHTML ="Watched";
			document.getElementById("watched_button").style.borderStyle="solid";
			document.getElementById("watched_button").style.borderColor="white";
			document.getElementById("watched_button").style.backgroundColor="green";
			document.getElementById("watched_button").style.color="white";
			document.getElementById("watched_button").style.fontWeight="700";
			document.getElementsByClassName("rating")[0].style.height ="400px";
			document.getElementById("rating-stars").style.visibility="visible";
		}
		
	}
	
	function watchedPressed(){
		var tmdb_id = getUrlParameter('tmdb_id');
		 $.post("getSerieStatus",
				    {
				        tmdb_id: tmdb_id,
				    },
				    function(response){
				        if(response=="1"){
				        	addToMySeries(0);
				        }else{
				        	addToMySeries(1);
				        }
				    });
	}
	
	function addToMySeries(i){
		//	1 - add
		//	0 - delete
		var tmdb_id = getUrlParameter('tmdb_id');
		 $.post("setSerieStatus",
				    {
				        tmdb_id: tmdb_id,
				        status:i
				    });
		 watchedButton(i);
	}
	
	function rateSeries(i){
		var tmdb_id = getUrlParameter('tmdb_id');
		 $.post("setRating",
				    {
				        tmdb_id: tmdb_id,
				        rating:i
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
	};
	
	
