<%@ include file="bzinclude.jsp" %>
<html>
<head>
	<title><fmt:message key="detailspage"/></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<style type="text/css">

.readreviews {
	font-size:16px;
	color: green;
	margin:5px 0;
}

.reviewtext {
	border:1px dashed green;
	margin:5px 0;
	padding:5px;
}

</style>

<script type="text/javascript">
    $(document).ready(function($) {
        $('div.readreviews').click(function() {
            $.ajax({
                type : 'POST',
                url : 'reviews',
                data : {
                    'isbn':'${ bookDetail.ISBN }'
                },
                dataType : "json",
                cache : 'false',
                success : function(response) {
                	$.each(response, function(idx, obj) {
                		alert(obj.reviewUuid);
                		$('div.reviewtext').append(obj.reviewText);
                	});                   
                },
                error : function() {
                    alert('Whoops');
                }
            });  
        });
    });
</script>

</head>
<body>
<%@ include file="bzheader.jsp"%>

<br />
<h1>
	<fmt:message key="details"/> <c:out value="${bookDetail.title}"/> by <c:out value="${bookDetail.author}"/>
</h1>

<br style="clear:both;" />

<div style="width:200px; margin: 0 20px 20px 20px; float:right;"><img width="200" src="<c:url value='${bookDetail.image}' />"/></div>


<table border="1">
	<tr><td width="15%">Book Title:</td><td><c:out value="${ bookDetail.title }"/></td></tr>
	<tr><td>Book Author:</td><td><c:out value="${ bookDetail.author }"/></td></tr>
	<tr><td>Book Description:</td><td><c:out value="${ bookDetail.description }"/></td></tr>
	<tr><td>Book Genre:</td><td><c:out value="${ bookDetail.genre }"/></td></tr>
	<tr><td>Book ISBN:</td><td><c:out value="${ bookDetail.ISBN }"/></td></tr>
	<tr><td>Book Price:</td><td><fmt:formatNumber value="${ bookDetail.price }" type="currency"/></td></tr>
</table>

<c:if test="${not empty username}">	
	<p><a href="<c:url value="/addtocart/${ bookDetail.ISBN }"/>">Add this book to your cart.</a></p>
</c:if>
	<p><a href="<c:url value="/bzbooks"/>">return to book list</a></p>
	
	<div class="readreviews">Read reviews.</div>
	<div class="reviewtext"></div>


</body>
</html>
