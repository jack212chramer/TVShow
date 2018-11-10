<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    
<select class="form-control" id="sel1" name="person_id">
        		<c:forEach items="${personList}" var="person"> 
	        <option value="${person.getId()}">${person.getName()}</option>
	        </c:forEach>
 </select>