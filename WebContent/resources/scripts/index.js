window.onload = function() {
	CheckSizeOnLoad();
	getRecentlyWatched();
	getHighestRated();
	
	 $(function(){
	      $("#includeHeader").load("header"); 
	    });
	 $(function(){
	      $("#joinUs").load("joinUs"); 
	    });
	 $(function(){
	      $("#sayHello").load("sayHello"); 
	    });
	 $(function(){
	      $("#highestRated").load("getHighestRated"); 
	    });
	 $(function(){
	      $("#recentlyWatched").load("getRecentlyWatched"); 
	    });
	 $(function(){
	      $("#includeFooter").load("footer"); 
	    });
}
function getRecentlyWatched() {
    $.ajax({
        url : "getRecentlyWatched",
        dataType : 'json'
    })
    .done(recWatched => {
    	if(recWatched.list.length==5){
       // SET RECENTLY WATCHED ELEMENT
    	//IMAGES
        document.getElementById("recentlyWatched_img1").src="http://image.tmdb.org/t/p/w185"+recWatched.list[0].poster_path;
        document.getElementById("recentlyWatched_img2").src="http://image.tmdb.org/t/p/w185"+recWatched.list[1].poster_path;
        document.getElementById("recentlyWatched_img3").src="http://image.tmdb.org/t/p/w185"+recWatched.list[2].poster_path;
        document.getElementById("recentlyWatched_img4").src="http://image.tmdb.org/t/p/w185"+recWatched.list[3].poster_path;
        document.getElementById("recentlyWatched_img5").src="http://image.tmdb.org/t/p/w185"+recWatched.list[4].poster_path;
        //TITLE
        document.getElementById("recentlyWatched_title1").innerText=recWatched.list[0].name;
        document.getElementById("recentlyWatched_title2").innerText=recWatched.list[1].name;
        document.getElementById("recentlyWatched_title3").innerText=recWatched.list[2].name;
        document.getElementById("recentlyWatched_title4").innerText=recWatched.list[3].name;
        document.getElementById("recentlyWatched_title5").innerText=recWatched.list[4].name;
        //HREF
        document.getElementById("recentlyWatched_href1").href="serial?tmdb_id="+recWatched.list[0].tmdb_id;
        document.getElementById("recentlyWatched_href2").href="serial?tmdb_id="+recWatched.list[1].tmdb_id;
        document.getElementById("recentlyWatched_href3").href="serial?tmdb_id="+recWatched.list[2].tmdb_id;
        document.getElementById("recentlyWatched_href4").href="serial?tmdb_id="+recWatched.list[3].tmdb_id;
        document.getElementById("recentlyWatched_href5").href="serial?tmdb_id="+recWatched.list[4].tmdb_id;
        //RATING
        document.getElementById("recentlyWatched_rating1").innerText=recWatched.list[0].rating;
        document.getElementById("recentlyWatched_rating2").innerText=recWatched.list[1].rating;
        document.getElementById("recentlyWatched_rating3").innerText=recWatched.list[2].rating;
        document.getElementById("recentlyWatched_rating4").innerText=recWatched.list[3].rating;
        document.getElementById("recentlyWatched_rating5").innerText=recWatched.list[4].rating;
        //HIDE NOTE IF USER DID NOT RATE A SHOW
        hideEmptyRating(recWatched.list[0].rating,5);
        hideEmptyRating(recWatched.list[1].rating,6);
        hideEmptyRating(recWatched.list[2].rating,7);
        hideEmptyRating(recWatched.list[3].rating,8);
        hideEmptyRating(recWatched.list[4].rating,9);
        //COLOR RATING
        colorRating(recWatched.list[0].rating,5);
        colorRating(recWatched.list[1].rating,6);
        colorRating(recWatched.list[2].rating,7);
        colorRating(recWatched.list[3].rating,8);
        colorRating(recWatched.list[4].rating,9);

    }else{
    	document.getElementById("RecentlyWatched").style.display="none";
    }
    });
}
function getHighestRated() {
    $.ajax({
        url : "getHighestRated",
        dataType : 'json'
    })
    .done(hiRated => {
    	if(hiRated.list.length==5){
       // SET GIGHEST RATED ELEMENT
    	//IMAGES
        document.getElementById("highestRated_img1").src="http://image.tmdb.org/t/p/w185"+hiRated.list[0].poster_path;
        document.getElementById("highestRated_img2").src="http://image.tmdb.org/t/p/w185"+hiRated.list[1].poster_path;
        document.getElementById("highestRated_img3").src="http://image.tmdb.org/t/p/w185"+hiRated.list[2].poster_path;
        document.getElementById("highestRated_img4").src="http://image.tmdb.org/t/p/w185"+hiRated.list[3].poster_path;
        document.getElementById("highestRated_img5").src="http://image.tmdb.org/t/p/w185"+hiRated.list[4].poster_path;
        //TITLE
        document.getElementById("highestRated_title1").innerText=hiRated.list[0].name;
        document.getElementById("highestRated_title2").innerText=hiRated.list[1].name;
        document.getElementById("highestRated_title3").innerText=hiRated.list[2].name;
        document.getElementById("highestRated_title4").innerText=hiRated.list[3].name;
        document.getElementById("highestRated_title5").innerText=hiRated.list[4].name;
        //RATING
        document.getElementById("highestRated_rating1").innerText=hiRated.list[0].rating;
        document.getElementById("highestRated_rating2").innerText=hiRated.list[1].rating;
        document.getElementById("highestRated_rating3").innerText=hiRated.list[2].rating;
        document.getElementById("highestRated_rating4").innerText=hiRated.list[3].rating;
        document.getElementById("highestRated_rating5").innerText=hiRated.list[4].rating;
        //HREF
        document.getElementById("highestRated_href1").href="serial?tmdb_id="+hiRated.list[0].tmdb_id;
        document.getElementById("highestRated_href2").href="serial?tmdb_id="+hiRated.list[1].tmdb_id;
        document.getElementById("highestRated_href3").href="serial?tmdb_id="+hiRated.list[2].tmdb_id;
        document.getElementById("highestRated_href4").href="serial?tmdb_id="+hiRated.list[3].tmdb_id;
        document.getElementById("highestRated_href5").href="serial?tmdb_id="+hiRated.list[4].tmdb_id;
      //COLOR RATING
        colorRating(hiRated.list[0].rating,0);
        colorRating(hiRated.list[1].rating,1);
        colorRating(hiRated.list[2].rating,2);
        colorRating(hiRated.list[3].rating,3);
        colorRating(hiRated.list[4].rating,4);
    	}else{
        	document.getElementById("highestRated").style.display="none";
        }
    });
}
function hideEmptyRating(i,j){
	if(i==0){
		 document.getElementsByClassName("user_rating")[j].style.display="none";
	}
}
function colorRating(i,j){
	if(i>=7){
		 document.getElementsByClassName("user_rating")[j].style.color="green";
		 document.getElementsByClassName("user_rating")[j].style.backgroundColor="#e6ffe6";
	}else if(i>=5){
		document.getElementsByClassName("user_rating")[j].style.color="orange";
		 document.getElementsByClassName("user_rating")[j].style.backgroundColor="#ffffcc";
	}else{
		document.getElementsByClassName("user_rating")[j].style.color="red";
		 document.getElementsByClassName("user_rating")[j].style.backgroundColor="#ffe6e6";
	}
}
window.addEventListener("resize", function() {
		 if (window.matchMedia("(min-width: 990px)").matches) {
		    	
		    	changeFirstElementResolution(0);
		    } else if(window.matchMedia("(min-width: 767px)").matches){
		    	
		    	changeFirstElementResolution(1);
		    }else{
		    	
		    	changeFirstElementResolution(2);
		    }
});		
	function CheckSizeOnLoad(){
		 if (window.matchMedia("(min-width: 990px)").matches) {
		    	
		    	changeFirstElementResolution(0);
		    } else if(window.matchMedia("(min-width: 767px)").matches){
		    	
		    	changeFirstElementResolution(1);
		    }else{
		    	
		    	changeFirstElementResolution(2);
		    }
	}
	
	
	
	function changeFirstElementResolution(i){
		
		try {
			
		if(i==0){
			document.getElementById("first_element_h3").style.paddingTop="150px";
			document.getElementById("first_element_h3").style.fontSize="28px";
			document.getElementById("first_element_p").style.fontSize="21px";
			document.getElementById("first_element_img").style.width="100%";
			//top5 
			document.getElementById("top5_p").style.fontSize="22px";
			  
		
		}else if(i==1){
			document.getElementById("first_element_h3").style.paddingTop="50px";
			document.getElementById("first_element_h3").style.fontSize="28px";
			document.getElementById("first_element_p").style.fontSize="21px";
			document.getElementById("first_element_img").style.width="100%";
			//top5 
			document.getElementById("top5_p").style.fontSize="22px";
		}else if(i==2){
			document.getElementById("first_element_h3").style.paddingTop="50px";
			document.getElementById("first_element_h3").style.fontSize="22px";
			document.getElementById("first_element_p").style.fontSize="16px";
			document.getElementById("first_element_img").style.width="60%";
			//top5 
			document.getElementById("top5_p").style.fontSize="15px";
		}
		}
		catch(err) {
		    //ignore - element is hidden for logged users
		}
	}
	
	
