<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<h3 id="settingsH3">Administrator panel</h3>
            
            <c:if test="${user_privileges>0}">
            <a href="userPanel"><button class="editButton">Visit User Panel</button></a>
            <a href="showPanel"><button class="editButton">Visit Series Edition Panel</button></a>
             <a href="newsPanel"><button class="editButton">Visit News Edition Panel</button></a>
             <a href="personPanel"><button class="editButton">Visit Person Edition Panel</button></a>
            </c:if>
            
                   
</div>
<div id="includeFooter"></div>
</body>
</html>