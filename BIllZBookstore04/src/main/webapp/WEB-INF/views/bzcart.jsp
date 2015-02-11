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

<form:form method="POST" modelAttribute="cartinfo">
	<c:forEach var="cartitem" items="${ cartinfo.cart }" varStatus="status">
        <table>
     		<tbody>
     			<tr>
        			<th>Key</th>
        			<th>Value</th>
    			</tr>
    			<tr>
            		<td>${cartitem.key}</td>
            		<td><input name="cartitem['${cartitem.key}']" value="${cartitem.value.cartItemQty}"></td>
        		</tr>
     		</tbody>
     	</table> 
	</c:forEach><br />
    <input type="submit"/>
</form:form>


</body>
</html>