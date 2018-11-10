<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<title>Settings</title>
 <link rel="stylesheet" type="text/css" href="resources/css/settings.css">
<script src="resources/scripts/settings.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
 <link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
 
</head>
<body>
<div id="includeHeader"></div>

<div id="settingsContainer">
<h3 id="settingsH3">Hello ${user}, what would you like to change?</h3>

<button class="editButton" data-toggle="collapse" data-target="#username">Edit username</button>
<div id="username" class="collapse">
 <form class="settingsForm" name="editUsername" action="editUsername" method="post" enctype="utf8" ">
		    <br/>New username:<input placeholder="${userDto.getUsername()}" onChange="checkAvailability()" type="text" name="username" pattern="^[a-zA-Z0-9]{3,}$" title="Must contain at least 3 or more characters" required>
     <p id="username_alert">Username already exists.</p>
     <br/><input class="submit" type="submit" value="Change Username">
            </form>
          </div>
          
            
    <button class="editButton" data-toggle="collapse" data-target="#password">Edit password</button>
<div id="password" class="collapse">
 <form class="settingsForm" name="editPassword" action="editPassword" method="post" enctype="utf8" onsubmit="return comparePasswords()">
		    <br/>New password:<input type="password" name="password" pattern="^(?=.*\d).{6,}$" title="Password must be at least 6 digits long and include at least one numeric digit." required>
		     <br/>Repeat new password:<input type="password" name="confirmPassword" required>
     <br/><input class="submit" type="submit" value="Change Password">
            </form>
            </div>
            
             <button class="editButton" data-toggle="collapse" data-target="#email">Edit e-mail</button>
<div id="email" class="collapse">
 <form class="settingsForm" name="editEmail" action="editEmail" method="post" enctype="utf8"">
		    <br/>New e-mail:<input type="text" placeholder="${userDto.getEmail()}" name="email" pattern="^\S+@\S+$" required>
     <br/><input class="submit" type="submit" value="Change e-mail">
            </form>
            </div>
            
            <c:if test="${user_privileges>0}">
            <a href="adminPanel"><button class="editButton">Visit Admin Panel</button></a>
            </c:if>
            
                   
</div>
<div id="includeFooter"></div>
</body>
</html>