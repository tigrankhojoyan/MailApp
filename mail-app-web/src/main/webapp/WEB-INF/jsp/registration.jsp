<%--
  Created by IntelliJ IDEA.
  User: tigran
  Date: 1/22/17
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"></link>
</head>
<body>

<div class="form-container">

    <h1>Please, enter data needed for registartion </h1>

    <form:form method="POST" modelAttribute="user" class="form-horizontal">

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="userName">User Name</label>

                <div class="col-md-7">
                    <form:input type="text" path="userName" id="userName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="userName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Password</label>

                <div class="col-md-7">
                    <form:password id="password" name="password" path="password" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="firstName">First Name</label>

                <div class="col-md-7">
                    <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="lastName">Last Name</label>

                <div class="col-md-7">
                    <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="userDetails.birthDate">Date of birth</label>

                <div class="col-md-7">
                    <form:input type="text" path="userDetails.birthDate" id="userDetails.birthDate"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="userDetails.birthDate" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="userDetails.email">Email</label>

                <div class="col-md-7">
                    <form:input type="text" path="userDetails.email" id="userDetails.email"
                                class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="userDetails.email" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="userDetails.gender">Gender</label>

                <div class="col-md-7" class="form-control input-sm">
                    <form:radiobutton path="userDetails.gender" value="MALE"/>Male
                    <form:radiobutton path="userDetails.Gender" value="FEMALE"/>Female
                    <div class="has-error">
                        <form:errors path="userDetails.Gender" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="userRole">Roles</label>
                <div class="col-md-7">
                    <form:select path="userRole" items="{ADMIN, USER}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="userRole" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <input type="submit" value="Submit"/>
    </form:form>
</div>


</body>
</html>
