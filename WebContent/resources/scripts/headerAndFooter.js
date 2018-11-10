window.addEventListener("resize", function() {
		 if (window.matchMedia("(min-width: 990px)").matches) {
		    	changeHeaderResolution(0);
		    
		    } else if(window.matchMedia("(min-width: 767px)").matches){
		    	changeHeaderResolution(1);
		    	
		    }else{
		    	changeHeaderResolution(2);
		    	
		    }
});		
	function CheckSizeOnLoad(){
		 if (window.matchMedia("(min-width: 990px)").matches) {
		    	changeHeaderResolution(0);
		    
		    } else if(window.matchMedia("(min-width: 767px)").matches){
		    	changeHeaderResolution(1);
		    	
		    }else{
		    	changeHeaderResolution(2);
		    	
		    }
	}
	
	
	function changeHeaderResolution(i){
		/*
		if(i==0){
		//	document.getElementById("logo").style.width="100%";
	    	for(var i=0;i<document.getElementsByClassName("navigationOption").length;i++){
	    		document.getElementsByClassName("navigationOption")[i].style.fontSize="18px";
	    		document.getElementsByClassName("navigationOption")[i].style.marginRight="50px";
	    	}
		}else if(i==1){
		//	document.getElementById("logo").style.width="100%";
			for(var i=0;i<document.getElementsByClassName("navigationOption").length;i++){
	    		document.getElementsByClassName("navigationOption")[i].style.fontSize="15px";
	    		document.getElementsByClassName("navigationOption")[i].style.marginRight="25px";
	    	}
		}else if(i==2){
		//	document.getElementById("logo").style.width="50%";
			for(var i=0;i<document.getElementsByClassName("navigationOption").length;i++){
	    		document.getElementsByClassName("navigationOption")[i].style.fontSize="15px";
	    		document.getElementsByClassName("navigationOption")[i].style.marginRight="25px";
	    	}
		}*/
	}
	
	
	
