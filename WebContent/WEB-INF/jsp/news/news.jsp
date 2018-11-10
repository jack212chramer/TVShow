<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<title>${news.getHeader()}</title>
<link rel="stylesheet" type="text/css" href="resources/css/headerAndFooter.css">
<link rel="stylesheet" type="text/css" href="resources/css/news.css">
<script src="resources/scripts/headerAndFooter.js"></script>
<script src="resources/scripts/news.js"></script>
  <link rel="stylesheet" type="text/css" href="resources/css/base.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <link rel="stylesheet" href="resources/libraries/bootstrap/css/bootstrap.min.css">
 <script src="resources/libraries/bootstrap/js/bootstrap.min.js"></script>
  <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
 <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
</head>
<body>
<script>
  AOS.init();
</script>
<div id="includeHeader"></div>


<div data-aos="fade-up" class="container news_article">
    <div class="row ">
        <div class="col-12">
        	<h3>${news.getHeader()}</h3>
        </div>
                <div class="col-12 signature">
        	<span><c:out value="${news.getAuthorName()}"/></span>
        	<span><c:out value="${news.getDate()}"/></span>
        </div>
        <div class="col-12">

        </div>
        <div class="col-12 news_article_img">
        	<img  src="${news.getImageSource()}">
        	<span><c:out value="${news.getImageDescription()}"/> </span>
        </div>
        <div class="col-12 news_article_body">
        	
        	${news.getText()}
        	
        </div>


    </div>
</div>





<div data-aos="fade-up" class="comment" id="includeComments"></div>
<div id="includeFooter"></div>
</body>
</html>