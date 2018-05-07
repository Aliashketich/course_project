<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale = 1">
    <style>
        <%@include file="/front/css/home.css"%>
        <%@include file="/front/css/login_form.css"%>
    </style>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
        <%@include file="/front/js/form_validation.js"%>
    </script>
    <title>Cafe</title>
</head>
<body>
    <div id="container">
        <div id="header"></div>
        <div id="content">
            <div id="autoriz_box">
                <div id="login-form">
                    <h1>Авторизация в системе</h1>
                    <fieldset>
                        <form action="cafe.by/sign_in" method="post" name="autorizForm" id="autorizForm">
                            <input type="text" id="login_in" placeholder="Имя пользователя" >
                            <span class="cd-error-message" id="login-span">Некорректный логин</span>
                            <input type="password" id="password_in" placeholder="Пароль" >
                            <span class="cd-error-message" id="password-span">Некорректный пароль</span>
                            <input type="submit" name="submit" value="ВОЙТИ">
                        </form>
                    </fieldset>
                </div>
            </div>
            <div id="info_box">
                <img id="info_box_image" src="/front/image/info_box.jpg">
            </div>
        </div>
        <div id="footer"></div>
    </div>
</body>
</html>