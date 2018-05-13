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
    
</head>
<body>

<div class="container">
    <div class="row" style="margin-top: 0">
        <div class="col-lg-12">

            <c:choose>

                <c:when test="${success}">
                    <h2>Учетная запись успешно активирована.</h2>
                </c:when>

                <c:otherwise>
                    <h2>Указанный токен недействителен.</h2>
                </c:otherwise>

            </c:choose>


        </div>
    </div>
</div>

</body>
</html>