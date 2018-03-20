<%--
  Created by IntelliJ IDEA.
  User: tigran
  Date: 1/7/17
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>

<H1>Welcome useraldkskdj</H1>

<c:url value="/api/usermvc/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
  <a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
</body>
</html>
