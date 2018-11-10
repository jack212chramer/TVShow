window.onload = function() {
	 $(function(){
	      $("#includeHeader").load("header"); 
	    });
	 $(function(){
	      $("#includeFooter").load("footer"); 
	    });
}

function comparePasswords(){
	var formOk = true;
	 
	 var password = document.forms["editPassword"]["password"].value;
	 var cpassword = document.forms["editPassword"]["confirmPassword"].value;
	 
	    if (password!=cpassword) {
	    	alert("Passwords don't match.");
	    	formOk=false;
	    }   
	    
	  return formOk;
}

function checkAvailability() { 
	var username = document.forms["editUsername"]["username"].value;
    
    $.ajax({
        url : "userAlreadyExists?username="+username,
        dataType : 'text'
    })
    .done(response => {
    	if(response=="1"){
    		document.getElementById("username_alert").style.display="block"; 
    		$('form :submit').prop('disabled', true);
    		$('form :submit').css({ opacity: 0.5 });
    		$('form :submit').css({ cursor: "default" });
    	}else{
    		document.getElementById("username_alert").style.display="none";
    		$('form :submit').prop('disabled', false);
    		$('form :submit').css({ opacity: 1 });
    		$('form :submit').css({ cursor: "pointer" });
    	}
    });	  
    
}
