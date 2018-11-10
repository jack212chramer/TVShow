<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<script src="resources/scripts/headerAndFooter.js"></script>
<script src="resources/scripts/adminPanel.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/pagination.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div id="includeHeader"></div>


<div class="container">
Add person:          
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Name</th>
        <th>image</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr>
      <form action="addPerson">
        <td><input type="text" name="name"></td>
        <td><input type="text" name="image"></td>
        <td><input type="submit" value="Add"></td>
        </form>
      </tr>
    </tbody>
  </table>
</div>


<div class="container">
<form name="decForm" action="personPanel" method="post" enctype="utf8">
		          <input  type="text" name="search" placeholder="search by name or id">
		          <input id="submit"  type="submit" value="Search user">
		          </form>                 
  <table class="table table-striped">
    <thead>
      <tr>
        <th>id</th>
        <th>name</th>
        <th>image</th>
        <th></th>
        <th></th>
      </tr>
    </thead>
    <tbody>
         <c:set var="list" value="${container.getList()}" scope="page" />
<c:forEach items="${list}" var="innerList">
<form action="updatePerson" method="post">
      <tr>
        <td>${innerList.getId()}</td>
        <input type="text" name="person_id" value="${innerList.getId()}" style="display:none">
        <td><input type="text" name="name" value="${innerList.getName()}"></td>
        <td><input type="text" name="image" value="${innerList.getImage()}"></td>
        <td><input type="submit" value="Update"></td>
        </form>
        <td><form action="deletePerson" method="post">
		<input type="text" value="${innerList.getId()}" name="person_id" style="display:none">
		<input type="submit" value="Delete person">
		</form></td>
      </tr>
     
 </c:forEach>
    </tbody>
  </table>
</div>


  <c:set var="results" value="${container }" scope="page" />
      <c:if test="${results.getPages()>1}">
<div id="pagination">
	<div class="pagination">
	  <a href="<c:out value="?search=${results.getSearch()}&page=1"></c:out>">&laquo;</a>
	  
	   <c:if test = "${results.getPage() <= 5}">
	  	 <c:set var="startPage" value="1" scope="page" />
	   </c:if>
	   
	   <c:if test = "${results.getPage() > 5}">
	  	 <c:set var="startPage" value="${results.getPage()-4}" scope="page" />
	   </c:if>
	   
	  <c:forEach var = "i" begin = "${startPage}" end = "${results.getPage()}">
	  <a  href="<c:out value="?search=${results.getSearch()}&page=${i}"></c:out>"><c:out value="${i}"></c:out></a>
	  <c:set var="activePage" value="${i}" scope="page" />
	  
	  </c:forEach>
	  
	   <a class="active" href=""><c:out value="${activePage+1}"></c:out></a>
	   
	    <c:if test = "${results.getPages()-results.getPage() >= 5}">
	  	 <c:set var="lastPage" value="${results.getPage()+5}" scope="page" />
	   </c:if>
	   
	   <c:if test = "${results.getPages()-results.getPage() < 5}">
	  	 <c:set var="lastPage" value="${results.getPages()}" scope="page" />
	   </c:if>
	    
	   <c:forEach var = "j" begin = "${activePage+2}" end = "${lastPage}">
	  <a  href="<c:out value="?search=${results.getSearch()}&page=${j}"></c:out>"><c:out value="${j}"></c:out></a>
	  </c:forEach>
	  <a href="<c:out value="?search=${results.getSearch()}&page=${results.getPages()}"></c:out>">&raquo;</a>
	</div>
</div>
</c:if>



<div id="includeFooter"></div>
</body>
</html>