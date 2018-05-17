<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customTags" %>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        <%@include file="/front/css/menu.css"%>
        <%@include file="/front/css/orderlist.css"%>
        <%@include file="/front/css/form.css"%>
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
    <title>Waiters</title>
</head>

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
                        <a href="/cafe.by/show_waiter">Официанты</a>
                    </li>
                    <li>
                        <a href="/cafe.by/show_order_admin">Заказы</a>
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
                    <div class="order-container">

                        <div class="qa-message-list">
                            <form action="/cafe.by/add_waiter" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control has-border" id="name"
                                           placeholder="Имя" name="name">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control has-border" id="surname"
                                           placeholder="Фамилия" name="surname">
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control has-border" id="email"
                                           placeholder="Email" name="email">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control has-border" id="login"
                                           placeholder="Логин" name="login">
                                </div>
                                <div class="form-group help">
                                    <input type="password" class="form-control has-border" id="password"
                                           placeholder="Пароль" name="password">
                                </div>
                                <button type="submit" id="signup-button" class="btn btn-default">Добавить</button>
                            </form>
                            <c:choose>
                                <c:when test="${waiters!=null}">
                                    <c:forEach var="waiter" items="${waiters}">
                                        <div class="message-item">
                                            <div class="message-inner">
                                                <div class="clearfix">
                                                    <form action="/cafe.by/delete_waiter?idWaiter=${waiter.idUser}" method="post">
                                                        <div class="user-detail">
                                                            <h5 class="handle">${waiter.name} ${waiter.surname}</h5>
                                                            <button type="submit" class="btn-right" class="btn btn-default">
                                                                Удалить официанта
                                                            </button>
                                                            <div class="post-meta">
                                                                <div class="asker-meta">
                                                                    <span class="qa-message-when-data">Логин: ${waiter.login}</span><br>
                                                                    <span class="qa-message-when-data">Email: ${waiter.email} </span><br>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </div><!-- ./qa-message-list -->
                    </div><!-- ./container -->
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
<!-- /#wrapper -->
