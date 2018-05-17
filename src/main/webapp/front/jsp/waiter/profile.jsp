<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customTags" %>
<html>
<head>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <meta name="viewport" content="width=device-width, initial-scale = 1">
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/slider/2016/cfb/jquery.carousel.fullscreen.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/slider/2016/cfb/jquery.carousel.fullscreen.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.css"/>
    <script src="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.js"></script>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2016/validator/validator.min.js"></script>
    <meta charset=utf-8"/>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
    </script>
    <style>
        <%@include file="/front/css/menu.css" %>
        <%@include file="/front/css/form.css" %>
        <%@include file="/front/css/product.css" %>
        <%@include file="/front/css/profile.css" %>
    </style>
    <script>
        $(document).ready(function () {
            var trigger = $('.hamburger'),
                overlay = $('.overlay'),
                isClosed = false;

            trigger.click(function () {
                hamburger_cross();
            });

            function hamburger_cross() {

                if (isClosed == true) {
                    overlay.hide();
                    trigger.removeClass('is-open');
                    trigger.addClass('is-closed');
                    isClosed = false;
                } else {
                    overlay.show();
                    trigger.removeClass('is-closed');
                    trigger.addClass('is-open');
                    isClosed = true;
                }
            }

            $('[data-toggle="offcanvas"]').click(function () {
                $('#wrapper').toggleClass('toggled');
            });
        });
    </script>

    <title>Cafe</title>
</head>

<body>
<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <c:choose>
            <c:when test="${role==1}">
                <ul class="nav sidebar-nav">
                    <li class="sidebar-brand">
                        <a href="/cafe.by/index">
                            ${admin.login}
                        </a>
                    </li>
                    <li>
                        <a href="/cafe.by/show_order_admin">Заказы</a>
                    </li>
                    <li>
                        <a href="#">Меню</a>
                    </li>
                    <li>
                        <a href="#">Сотрудники</a>
                    </li>
                    <li>
                        <a href="#">Администраторы</a>
                    </li>
                    <li>
                        <a href="#">Официанты</a>
                    </li>
                    <li>
                        <a href="/cafe.by/sign_out">Выход</a>
                    </li>
                </ul>
            </c:when>
            <c:when test="${role==2}">
                <ul class="nav sidebar-nav">
                    <li class="sidebar-brand">
                        <a href="/cafe.by/index">
                           ${waiter.login}
                        </a>
                    </li>
                    <li>
                        <a href="/cafe.by/show_order_waiter">Мои заказы</a>
                    </li>
                    <li>
                        <a href="/cafe.by/sign_out">Выход</a>
                    </li>
                </ul>
            </c:when>
        </c:choose>
    </nav>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
        <div class="container">
            <div class="row text-center">
                <div class="col-lg-8 col-lg-offset-2">
                    <%--<%@include file="home.jsp"%>--%>
                        <%--<div class="container">--%>
                        <div>
                            <div class="modal-dialog" style="padding-top: 65px;">
                                <div class="modal-content" style="width: 750px;margin:0 0 0 -100px;">
                                    <div class="container">
                                        <div class="row">

                                            <div class="col-md-6">
                                                <form id="profile-form" class="form-horizontal form-horizontal-profile" method="post"
                                                      action="/cafe.by/edit_profile">

                                                    <span class="heading">Ваш профиль</span>

                                                    <div class="form-group">
                                                        <span class="profile-span">Login</span>
                                                        <input type="text" class="form-control has-border" id="profile-login"
                                                               value="${waiter.login}" disabled="disabled" name="login_profile">
                                                    </div>

                                                    <div class="form-group">
                                                        <span class="profile-span">Surname</span>
                                                        <input type="text" class="form-control has-border" id="profile-surname"
                                                               placeholder="Surname" name="surname" value="${waiter.surname}">
                                                        <span class="cd-error-message" id="surname-profile-span">net surname</span>
                                                    </div>

                                                    <div class="form-group">
                                                        <span class="profile-span">Name</span>
                                                        <input type="text" class="form-control has-border" id="profile-name"
                                                               placeholder="Name" name="name" value="${waiter.name}">
                                                        <span class="cd-error-message" id="name-profile-span">net name</span>

                                                    </div>

                                                    <div class="form-group">
                                                        <span class="profile-span">Email</span>
                                                        <input type="email" class="form-control has-border" id="profile-email"
                                                               placeholder="Email" value="${waiter.email}" name="email">
                                                        <span class="cd-error-message" id="email-profile-span">net email</span>

                                                    </div>

                                                    <div id="flex-btn">
                                                        <button type="button" class="btn btn-default"
                                                                id="changepassword">Сменить пароль</button>
                                                        <button type="submit" id="edit-button" class="btn btn-default">Сохранить изменения</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div><!-- /.row -->
                                    </div><!-- /.container -->
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<%@include file="/front/jsp/common/information.jsp" %>

<c:choose>
    <c:when test="${error_data!=null}">
        <script>
            <%@include file="/front/js/information.js" %>
        </script>
    </c:when>
</c:choose>


</body>
</html>
