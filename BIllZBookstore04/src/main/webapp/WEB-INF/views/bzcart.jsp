<%@ include file="bzinclude.jsp" %>
<html>
<head>
	<title><fmt:message key="carttitle"/></title>
</head>
<body>
<%@ include file="bzheader.jsp"%>

<br />

<h1>
	<fmt:message key="cart"/>  
</h1>

<form:form method="POST" modelAttribute="cartItems">
	<c:forEach var="item" items="${ cartItems }">
		<p><c:out value="${ item.cartItemBook.title }" />, quantity: <c:out value="${ item.cartItemQty }" /></p>
		<form:input path="cartItemQty" value="cartItemQty"/>
	</c:forEach>
    <input type="submit"/>
</form:form>


</body>
</html>