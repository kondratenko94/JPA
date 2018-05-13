<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <link rel="stylesheet" href="../../css/main.css" type="text/css">
</head>
<body>

<div class="container">
    <div class="row" style="margin-top: 0">
        <div class="col-lg-12">

            <form:form action='/registration' modelAttribute="user" method="post">

                <spring:bind path="firstName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="control-label">Имя</label>
                        <form:input path="firstName" type="text" class="form-control" placeholder="Имя" />
                        <form:errors path="firstName" class="control-label" />
                    </div>
                </spring:bind>

                <spring:bind path="lastName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="control-label">Фамилия</label>
                        <form:input path="lastName" type="text" class="form-control" placeholder="Фамилия" />
                        <form:errors path="lastName" class="control-label" />
                    </div>
                </spring:bind>

                <spring:bind path="email">
                    <div class="form-group tooltip-demo ${status.error ? 'has-error' : ''}">
                        <label class="control-label">E-mail</label>
                        <form:input path="email" type="text" class="form-control" placeholder="E-mail" />
                        <form:errors path="email" class="control-label" />
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="control-label">Пароль</label>
                        <form:input path="password" type="password" class="form-control" placeholder="Пароль" />
                        <form:errors path="password" class="control-label" />
                    </div>
                </spring:bind>

                <div class="form-group">
                    <label class="control-label">Роль</label>
                    <form:select cssClass="form-control" path="selectedRoleName">
                        <option value="Developer" selected>Developer</option>
                        <option value="Manager">Manager</option>
                    </form:select>
                </div>

                <button type="submit" class="btn btn-default pull-right">Регистрация</button>

            </form:form>

        </div>
    </div>
</div>

</body>
</html>