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
 <a style="margin-bottom:100px;" href="newShow"><button>Add new show</button></a>          
  <table class="table table-striped">
    <thead>
      <tr>
        <th>id</th>
        <th>title</th>
        <th>First air date</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
     <c:set var="list" value="${container.getList()}" scope="page" />
<c:forEach items="${list}" var="innerList">
      <tr>
        <td><c:out value="${innerList.getTmdb_id()}"/></td>
        <td> <a href="serial?tmdb_id=${innerList.getTmdb_id()}"><c:out value="${innerList.getName()}"/></a></td>
        <td><c:out value="${innerList.getFirst_air_date()}"/></td>
        <td> <a href="editShow?tmdb_id=${innerList.getTmdb_id()}">Edit Show</a>
         <a style="color:red;" href="deleteShow?tmdb_id=${innerList.getTmdb_id()}">Delete Show</a></td>
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