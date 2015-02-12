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

<form:form method="POST" modelAttribute="bzcart">
	<c:forEach var="cartitem" items="${ bzcart.cart }" varStatus="status">
        <table border="1" style="border:1px solid blue; padding:2px; text-align:center;">
     		<tbody>
     			<tr>
        			<th>Item ISBN</th>
        			<th>Item title</th>
        			<th>Quantity</th>
        			<th>Item subtotal</th>
    			</tr>
    			<tr>
            		<td>${cartitem.cartItemId}</td>
       				<td width="50%">${ cartitem.cartItemBook.title }</td>
            		<td><input name="cartitem[${status.index}]" value="${cartitem.cartItemQty}" /></td>
  					<td><fmt:formatNumber value="${cartitem.cartItemTotalPrice}" type="currency"/></td>
        		</tr>
     		</tbody>
     	</table> 
	</c:forEach><br />
	<br />
        <table border="1" style="width:80%; border:1px solid blue; padding:2px; text-align:center; ">
        	<tr>
        		<th>Cart subtotal</th>
        		<th>Tax</th>
        		<th>Shipping</th>
        		<th>Cart Total</th>
        	</tr>
        	<tr>
        		<td><fmt:formatNumber value="${ bzcart.cartSubtotal }" type="currency"/></td>
        		<td><fmt:formatNumber value="${ bzcart.cartTax }" type="currency"/></td>
        		<td><fmt:formatNumber value="${ bzcart.cartShippingCost }" type="currency"/></td>
        		<td><fmt:formatNumber value="${ bzcart.cartTotal }" type="currency"/></td>        	
        	</tr>
 
       </table>
    <input type="submit"/>
</form:form>

<p>Click <a href="<c:url value="/showcheckout"/${ bzcart.cartId }>">here</a> to proceed to checkout.</p>


</body>
</html>