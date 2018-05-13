<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
    <body>

        <div class="container">
            <div class="row" style="margin-top: 0">
                <div class="col-lg-12">

                    <form name='frm' action='/login' method="post">

                        <div class="form-group" style="margin-bottom: 12px;">
                            <label class="control-label">E-mail</label>
                            <input class="form-control" type="text" name="email" placeholder="E-mail"
                                   value="${not empty param.email ? param.email : ''}" >
                        </div>

                        <div class="form-group" style="margin-bottom: 12px;">
                            <label class="control-label">Пароль</label>
                            <input class="form-control" type="password" name="password" placeholder="Пароль" value="">
                        </div>

                        <div class="row" style="margin-bottom: .3em">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <button type="submit" class="btn btn-default pull-right">Войти</button>
                            </div>
                        </div>

                        <c:if test="${not empty param.email}">
                            <div style="display: inline-block; margin-top: .5em; margin-bottom: .5em">

                                <c:choose>
                                    <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}">
                                        <span><i class="fa fa-exclamation" style="margin-right: 4px"></i>Неверное имя пользователя или пароль</span>
                                    </c:when>

                                    <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User is disabled'}">
                                        <span><i class="fa fa-times" style="margin-right: 4px"></i>Учетная запись неактивна</span>
                                    </c:when>
                                </c:choose>

                            </div>
                        </c:if>

                    </form>

                </div>
            </div>
        </div>

    </body>
</html>