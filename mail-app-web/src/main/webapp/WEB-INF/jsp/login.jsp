<%--
  Created by IntelliJ IDEA.
  User: tigran
  Date: 1/7/17
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Login</title>
</head>
<body>
<form:form id="loginForm" method="post" action="login" modelAttribute="loginUser">

  <form:label path="userName">Enter your user-name</form:label>
  <form:input id="userName" name="userName" path="userName" /><br>
  <form:label path="password">Please enter your password</form:label>
  <form:password id="password" name="password" path="password" /><br>
  <input type="submit" value="Submit" />
</form:form>
</body>
</html>
