<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<header id="header_main">
	<div class="container">
	    <div class="row">
	        <div class="col-xs-1 col-md-2">
	        	<a href="homepage"><img id="logo" src="https://upload.wikimedia.org/wikipedia/commons/a/a3/Canal%2B_Seriale.png"></a>
	        </div>
	         <c:if test = "${user == null}">
	        <div class="col-xs-11 col-md-8">
	        </c:if>
	         <c:if test = "${user != null}">
	        <div class="col-xs-11 col-md-10">
	        </c:if>
		         <form  action="search" method="get">
		        	<input type="text" id="search" name="search" placeholder=" Search..."pattern=".{2,}"   required title="2 characters minimum">
		        	</form>
	        	<nav class="navigationMain">
					<a href="newsList"><li class="navigationOption">News</li></a>
					<a href="tvShows?sortBy=0"><li class="navigationOption">TV Series</li></a>
					<a href="nextAirDate"><li class="navigationOption">Releases</li></a>
					<a href="top25"><li class="navigationOption">Top 25</li></a>
					 <c:if test = "${user != null}">
						<div class="dropdown">
							<li class="navigationOption"> 
							<c:out value="${user}" default="null"/>
						  <div class="dropdown-content">
						    <a href="myShows?user_id=${user_id}">My shows</a>
						    <a style="margin-top:50px;" href="settings">Settings</a>
						    <a style="margin-top:100px;" href="Logout">Logout</a>
						  </div>
							 </li>
							 </div>
							  </c:if>
							  <c:if test = "${user == null}">
					<a  href="login"><li class="navigationOption">Login</li></a>
					 </c:if>
				</nav>

	        </div>
	         <c:if test = "${user == null}">
	        <div class="col-md-2">
		        	<a  href="register"><div id="Register" class="bbtn">Create account</div></a>		
	        </div>
	         </c:if>
	    </div>
	</div>
</header>