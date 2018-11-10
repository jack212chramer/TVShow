<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TVSeries</title>
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
<link rel="stylesheet" type="text/css" href="resources/css/newsList.css">
<link rel="stylesheet" type="text/css" href="resources/css/base.css">
<script src="resources/scripts/headerAndFooter.js"></script>
<script src="resources/scripts/index.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
  <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
 <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
 
 <meta name="viewport" content="width=device-width; initial-scale=1.0"> 
 
</head>
<body style="margin: 0;">
<script>
  AOS.init();
</script>


<div id="includeHeader"></div>


<c:if test = "${user == null}">
<div id="joinUs"></div>
</c:if>

<c:if test = "${user != null}">
<div  class="container " >

<div id="highestRated"></div>
<div id="recentlyWatched"></div>

</div>
</c:if>

<div id="sayHello"></div>
<div id="includeFooter"></div>



</body>
</html>