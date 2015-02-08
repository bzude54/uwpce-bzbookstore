<%@ include file="bzinclude.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="userlogin"/></title>
</head>
<body>

<p>Welcome to <fmt:message key="userlogintitle"/>, please enter your username and password</p>
<form:form method="post" modelAttribute="loginUserInfo">
<div style="width:300px;text-align:right">
    <form:label path="userName">Username:</form:label><form:input path="userName"/><br>
    <form:label path="password">Password:</form:label><form:password path="password"/><br>
    <input type="submit"/>
</div>
</form:form>

</body>
</html>
