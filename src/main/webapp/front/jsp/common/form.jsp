
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale = 1">
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.js"></script>
    <style>
        <%@include file="/front/css/login_form.css" %>
    </style>
    <script>
        <%@include file="/front/js/form_validation.js" %>
    </script>
</head>
<div id="container">
    <div id="header"></div>
    <div id="content">
        <div id="autoriz_box">
            <div id="login-form">
                <h1>Авторизация в системе</h1>
                <fieldset>
                    <form action="/cafe.by/sign_in" method="post" name="autorizForm" id="autorizForm">
                        <input type="text" id="login_in" name="login" placeholder="Имя пользователя" >
                        <%--<span class="cd-error-message" id="login-span">Некорректный логин</span>--%>
                        <input type="password" id="password_in" name="password" placeholder="Пароль" >
                        <%--<span class="cd-error-message" id="password-span">Некорректный пароль</span>--%>
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
