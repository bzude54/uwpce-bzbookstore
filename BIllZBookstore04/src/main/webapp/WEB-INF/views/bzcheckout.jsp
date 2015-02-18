<%@ include file="bzinclude.jsp" %>
<html>
<head>
	<title><fmt:message key="checkouttitle"/></title>
</head>
<body>
<%@ include file="bzheader.jsp"%>

<br />

<h1>
	<fmt:message key="checkout"/>  
</h1>
<p>This is your current pending order and the information we have on file for your shipping address and credit card.
Please update your shipping address and credit card as necessary.</p>

<br />
<form:form method="post" modelAttribute="bZCheckoutInfo">
<table>
	<form:hidden path="cartId" />
	<tr><td>Cart total price: </td><td><fmt:formatNumber value="${ bZCheckoutInfo.cartTotalPrice }" type="currency"/></td></tr>   			
    <tr><td><form:label path="userStreetAddress">Shipping Address street:</form:label></td><td><form:input path="userStreetAddress"/></td></tr>
    <tr><td><form:label path="userCity">Shipping address city:</form:label></td><td><form:input path="userCity"/></td></tr>
    <tr><td><form:label path="userState">Shipping address state:</form:label></td><td><form:input path="userState"/></td></tr>
    <tr><td><form:label path="userZip">Shipping address zipcode:</form:label></td><td><form:input path="userZip"/></td></tr>
    <tr><td><form:label path="userCreditCard">Credit card number 1:</form:label></td><td><form:input path="userCreditCard"/></td></tr>
    <tr><td colspan="2"><input type="submit"/></td></tr>
 </table>

</form:form>

</body>
</html>