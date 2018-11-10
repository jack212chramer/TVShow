<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<title>Register</title>
 <link rel="stylesheet" type="text/css" href="resources/css/register.css">
<script src="resources/scripts/loginAndRegister.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
 <link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">

</head>
<body style="margin: 0">

<div id="includeHeader"></div>
	
		      
            <form id="regForm" name="registerForm" action="registerCheck" method="post" enctype="utf8" onsubmit="return comparePasswords()">
     		 <h2>Register Details</h2>
		    <br/>Username:<input onChange="checkAvailability()" type="text" name="username" pattern="^[a-zA-Z0-9]{3,}$" title="Must contain at least 3 or more characters" required>
		    <p id="username_alert">Username already exists.</p>
		    <br/>email:<input type="text" name="email" pattern="^\S+@\S+$" required>
            <br/>Password:
            <input type="password" name="password" pattern="^(?=.*\d).{6,}$" title="Password must be at least 6 digits long and include at least one numeric digit." required>
             <br/>Confirm Password:<input type="password" name="confirmPassword" required>
     <br/><input id="submit" type="submit" value="register">
            </form>

    
<div id="includeFooter"></div>
</body>
</html>