<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
 <link rel="stylesheet" type="text/css" href="resources/css/register.css">
<script src="resources/scripts/loginAndRegister.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
 <link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
 <meta name="viewport" content="width=device-width; initial-scale=1.0"> 
 
    </head>
      <body>
             <div id="includeHeader"></div>
             
            <form id="regForm" name="registerForm" action='<spring:url value="/signin"/>' method="post" enctype="utf8">
     		 <h2>Login Details</h2>
		    <br/>Username:<input type="text" name="username" required>
            <br/>Password:
            <input type="password" name="password" required>
            <c:if test="${message!=null}">
             <p id="username_alert" style="display:block;">${message}</p>
             </c:if>
          
     <br/><input id="submit" type="submit" value="Log-in">
            </form>


<div id="includeFooter"></div>
    </body>
</html>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    