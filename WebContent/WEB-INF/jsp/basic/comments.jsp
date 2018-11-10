<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
   <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="resources/css/base.css">
  <!DOCTYPE html>


<c:set var="i" scope="request" value="0"/>
<c:set var = "list" scope = "session" value = "${container.getList()}"/>
<h3>Comment section:</h3>
<c:forEach items="${list}" var="element"> 
<c:if test="${i<4}">
<div class="media comment_single">
  <div class="media-left">
    <img src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="media-object" 
    style="width:60px; margin-right:15px;">
  </div>
  <div class="media-body">
    <h4 class="media-heading"><a href="myShows?user_id=${element.getUser_id()}">${element.getUsername()}</a></h4>
    <p>${element.getText()}</p>
    <c:if test="${element.getUser_id()==user_id || user_privileges>0}">
    <form action="deleteComment" method="post">
    <input type="text" value="${element.getComment_id()}" name="comment_id" style="display:none;">
    <input class="btn btn-link" type="submit" value="delete comment">
    </form>
    </c:if>
  </div>
</div>
</c:if>


    <c:if test="${i>=4}">	
    <div id="showComments" class="collapse">
<div class="media comment_single">
  <div class="media-left">
    <img src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png" class="media-object" 
    style="width:60px; margin-right:15px;">
  </div>
  <div class="media-body">
    <h4 class="media-heading"><a href="myShows?user_id=${element.getUser_id()}">${element.getUsername()}</a></h4>
    <p>${element.getText()}</p>
        <c:if test="${element.getUser_id()==user_id || user_privileges>0}">
    <form action="deleteComment" method="post">
    <input type="text" value="${element.getComment_id()}" name="comment_id" style="display:none;">
    <input class="btn btn-link" type="submit" value="delete comment">
    </form>
    </c:if>
  </div>
</div>
    </div>
    </c:if>
    <c:set var="i" scope="request" value="${i+1}"/>
</c:forEach>

<c:if test="${list.size()>4}">
<button style="width:100%" class="btn bbtn" data-toggle="collapse" data-target="#showComments">More comments</button>
</c:if>
<c:if test = "${user == null}">
<br>
<p>Please log-in to leave a comment.</p>
</c:if>
<c:if test = "${user != null}">
<form class="form-horizontal" action="addComment" method="post" onsubmit="return checkCommentLength()">
  <div class="form-group">
  <br>
  <textarea maxlength="5000" name="comment" class="form-control" rows="5" id="comment" placeholder="Enter your comment here..."></textarea>
	<br>
	<input value="${container.getId()}" type="number" style="display:none;" name="id">
	<input value="${container.getType()}" type="number" style="display:none;" name="type">
	<button type="submit" class="btn bbtn">Submit</button>
</div>
</form>
</c:if>

<script>
function checkCommentLength(){
	var value = document.getElementById("comment").value;
	if(value.length>0){
		return true;
	}else{
		alert("Your comment is empty!");
		return false;
	}
}
</script>