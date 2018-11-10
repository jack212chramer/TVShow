window.onload = function() {
	getHighestRated();
	
	 $(function(){
	      $("#includeHeader").load("header"); 
	    });
	 $(function(){
	      $("#includeFooter").load("footer"); 
	    });
}
function getHighestRated() {
    $.ajax({
        url : "getMyShows",
        dataType : 'json'
    })
    .done(hiRated => {
    	if(hiRated.tvShowList.length==5){
       // SET GIGHEST RATED ELEMENT
    	//IMAGES
        document.getElementById("highestRated_img1").src="http://image.tmdb.org/t/p/w185"+hiRated.tvShowList[0].poster_path;
        document.getElementById("highestRated_img2").src="http://image.tmdb.org/t/p/w185"+hiRated.tvShowList[1].poster_path;
        document.getElementById("highestRated_img3").src="http://image.tmdb.org/t/p/w185"+hiRated.tvShowList[2].poster_path;
        document.getElementById("highestRated_img4").src="http://image.tmdb.org/t/p/w185"+hiRated.tvShowList[3].poster_path;
        document.getElementById("highestRated_img5").src="http://image.tmdb.org/t/p/w185"+hiRated.tvShowList[4].poster_path;
        //TITLE
        document.getElementById("highestRated_title1").innerText=hiRated.tvShowList[0].name;
        document.getElementById("highestRated_title2").innerText=hiRated.tvShowList[1].name;
        document.getElementById("highestRated_title3").innerText=hiRated.tvShowList[2].name;
        document.getElementById("highestRated_title4").innerText=hiRated.tvShowList[3].name;
        document.getElementById("highestRated_title5").innerText=hiRated.tvShowList[4].name;
        //RATING
        document.getElementById("highestRated_rating1").innerText=hiRated.tvShowList[0].rating;
        document.getElementById("highestRated_rating2").innerText=hiRated.tvShowList[1].rating;
        document.getElementById("highestRated_rating3").innerText=hiRated.tvShowList[2].rating;
        document.getElementById("highestRated_rating4").innerText=hiRated.tvShowList[3].rating;
        document.getElementById("highestRated_rating5").innerText=hiRated.tvShowList[4].rating;
        //HREF
        document.getElementById("highestRated_href1").href="serial?tmdb_id="+hiRated.tvShowList[0].tmdb_id;
        document.getElementById("highestRated_href2").href="serial?tmdb_id="+hiRated.tvShowList[1].tmdb_id;
        document.getElementById("highestRated_href3").href="serial?tmdb_id="+hiRated.tvShowList[2].tmdb_id;
        document.getElementById("highestRated_href4").href="serial?tmdb_id="+hiRated.tvShowList[3].tmdb_id;
        document.getElementById("highestRated_href5").href="serial?tmdb_id="+hiRated.tvShowList[4].tmdb_id;
      //COLOR RATING
        colorRating(hiRated.tvShowList[0].rating,0);
        colorRating(hiRated.tvShowList[1].rating,1);
        colorRating(hiRated.tvShowList[2].rating,2);
        colorRating(hiRated.tvShowList[3].rating,3);
        colorRating(hiRated.tvShowList[4].rating,4);
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

	
	
