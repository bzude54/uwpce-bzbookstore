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
<br />
<form:form method="post" modelAttribute="bzcheckoutinfo">
<div style="width:300px;text-align:right">
    <form:label path="cartTotalPrice">Cart total price:</form:label><form:input path="cartTotalPrice"/><br>
    <form:label path="userShippingStreetAddress">Shipping Address street:</form:label><form:input path="shippingStreetAddress"/><br>
    <form:label path="userShippingState">Shipping address state:</form:label><form:input path="shippingState"/><br>
    <form:label path="userShippingZip">Shipping address zipcode:</form:label><form:input path="shippingZip"/><br>
    <form:label path="userCreditCard">Credit card number 1:</form:label><form:input path="creditCard1"/><br>
    <input type="submit"/>
</div>
</form:form>

</body>
</html>