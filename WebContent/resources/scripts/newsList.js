window.onload = function() {
	 $(function(){
	      $("#includeHeader").load("header"); 
	    });
	 $(function(){
	      $("#includeFooter").load("footer"); 
	    });
	 
	 CheckSizeOnLoad();
}
function CheckSizeOnLoad(){
	 if (window.matchMedia("(min-width: 767px)").matches) {
		 changeResolution(0);
	    
	    } else if(window.matchMedia("(min-width: 576px)").matches){
	    	changeResolution(1);
	    	
	    }else{
	    	changeResolution(2);
	    }
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

function changeResolution(i){
	if(i==0){
		document.getElementsByClassName("mainNewsBlur")[0].style.height="400px";
		document.getElementsByClassName("mainNewsBlurh3")[0].style.marginTop="270px";
		document.getElementsByClassName("mainNewsBlurh3")[0].style.height="125px";
		document.getElementsByClassName("mainNewsBlurh3")[0].style.fontSize="28px";
	}else if(i==1){
		document.getElementsByClassName("mainNewsBlur")[0].style.height="250px";	
		document.getElementsByClassName("mainNewsBlurh3")[0].style.marginTop="170px";
		document.getElementsByClassName("mainNewsBlurh3")[0].style.height="75px";
		document.getElementsByClassName("mainNewsBlurh3")[0].style.fontSize="18px";
	}else if(i==2){
		document.getElementsByClassName("mainNewsBlur")[0].style.height="250px";
		document.getElementsByClassName("mainNewsBlurh3")[0].style.marginTop="170px";
		document.getElementsByClassName("mainNewsBlurh3")[0].style.height="75px";
		document.getElementsByClassName("mainNewsBlurh3")[0].style.fontSize="18px";
	}
}
