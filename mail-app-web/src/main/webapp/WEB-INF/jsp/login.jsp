<%--
  Created by IntelliJ IDEA.
  User: tigran
  Date: 1/7/17
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">

    </style>
    <link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"/>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<div id="login-overlay" class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">Login to myApp</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-xs-6">
                    <div class="well">
                        <form:form method="POST" modelAttribute="user">
                            <form:errors cssStyle="color: #ac2925"/>
                            <div class="form-group">
                                <label for="userName" class="control-label">Username</label>
                                <form:input type="text" class="form-control" path="userName" id="userName" value=""
                                            required="" title="Please enter you username"
                                            placeholder="example@gmail.com"/>
                                  <span class="help-block">
                            <form:errors path="userName" cssStyle="color: #ac2925"/></span>

                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label">Password</label>
                                <form:password class="form-control" id="password" name="password" path="password"
                                               title="Please enter your password"/>
                                  <span class="help-block">
                        <form:errors path="password" cssStyle="color: #ac2925"/>
                        </span>
                            </div>
                            <div id="loginErrorMsg" class="alert alert-error hide">Wrong username og password</div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="remember" id="remember"> Remember login
                                </label>

                                <p class="help-block">(if this is a private computer)</p>
                            </div>
                            <button type="submit" class="btn btn-success btn-block">Login</button>
                            <a href="/forgot/" class="btn btn-default btn-block">Help to login</a>
                        </form:form>
                    </div>
                </div>
                <div class="col-xs-6">
                    <p class="lead">Register now for <span class="text-success">FREE</span></p>
                    <ul class="list-unstyled" style="line-height: 2">
                        <li><span class="fa fa-check text-success"></span> See all your orders</li>
                        <li><span class="fa fa-check text-success"></span> Fast re-order</li>
                        <li><span class="fa fa-check text-success"></span> Save your favorites</li>
                        <li><span class="fa fa-check text-success"></span> Fast checkout</li>
                        <li><span class="fa fa-check text-success"></span> Get a gift
                            <small>(only new customers)</small>
                        </li>
                        <li><a href="/read-more/"><u>Read more</u></a></li>
                    </ul>
                    <p><a href="/api/usermvc/registration" class="btn btn-info btn-block">Yes please, register now!</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>