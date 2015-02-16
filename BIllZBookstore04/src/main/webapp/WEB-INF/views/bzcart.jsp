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
<c:choose>
<c:when test="${ empty bZSimpleCart.cartItems }">
<p>Your cart is empty.</p>
<p>Click <a href="<c:url value="/bzbooks"/>">here</a> to view our inventory.</p>
</c:when>
<c:otherwise>
<form:form method="POST" modelAttribute="bZSimpleCart" action="bzcart">
	<c:forEach var="cartItems" items="${ bZSimpleCart.cartItems }" varStatus="idx">
        <table border="1" style="border:1px solid blue; padding:2px; text-align:center;">
     		<tbody>
     			<tr>
        			<th>Item ISBN</th>
        			<th>Item title</th>
        			<th>Item subtotal</th>
         			<th>Quantity</th>
    			</tr>
    			<tr>
    			    <td>${cartItems.cartItemId}</td>
       				<td width="50%">${ cartItems.cartItemBook.title }</td>
  					<td><fmt:formatNumber value="${cartItems.cartItemTotalPrice}" type="currency"/></td>    			
    				<td><form:input type="number" min="0" max="99" path="cartItems[${ idx.index }].cartItemQty" /></td>
     			        <form:hidden path="cartItems[${idx.index}].cartItemId"/>
         		</tr>
     		</tbody>
     	</table> 
	</c:forEach>
	
	<br />
	<br />
        <table border="1" style="width:80%; border:1px solid blue; padding:2px; text-align:center; ">
        	<tr>
        		<th>Cart subtotal</th>
        		<th>Tax</th>
        		<th>Shipping</th>
        		<th>Cart Total</th>
        	</tr>
        	<tr>
        		<td><fmt:formatNumber value="${ bZSimpleCart.cartSubtotal }" type="currency"/></td>
        		<td><fmt:formatNumber value="${ bZSimpleCart.cartTax }" type="currency"/></td>
        		<td><fmt:formatNumber value="${ bZSimpleCart.cartShippingCost }" type="currency"/></td>
        		<td><fmt:formatNumber value="${ bZSimpleCart.cartTotal }" type="currency"/></td>        	
        	</tr>
 
       </table>
    <input type="submit"/>
</form:form>

<p>Click <a href="<c:url value="/showcheckout/${ bzcart.cartId }"/>">here</a> to proceed to checkout.</p>
</c:otherwise>
</c:choose>

</body>
</html>