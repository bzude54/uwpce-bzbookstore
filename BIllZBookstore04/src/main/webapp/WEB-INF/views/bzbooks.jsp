<%@ include file="bzinclude.jsp" %>
<html>
<head>
	<title><fmt:message key="bookspage"/></title>
</head>
<body>
<%@ include file="bzheader.jsp"%>

<br />

<h1>
	<fmt:message key="books"/>  
</h1>

<br style="clear:both;" />

<table border="1">
<c:forEach items="${booksMap}" var="book">
	<tr>
		<td><c:out value="${book.value.title}"/></td>
		<td><c:out value="${book.value.author}"/></td>
		<td><fmt:formatNumber value="${book.value.price}" type="currency"/></td>
		<td><a href="bzbookdetails?id=${book.value.ISBN}">Book Details</a></td>
	</tr>
</c:forEach>
</table>

</body>
</html>
