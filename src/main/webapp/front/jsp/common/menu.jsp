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
                        <a href="/cafe.by/show_order_admin">Заказы</a>
                    </li>
                    <li>
                        <a href="/cafe.by/show_product">Меню</a>
                    </li>
                    <li>
                        <a href="/cafe.by/show_waiter">Официанты</a>
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
                        <a href="/cafe.by/waiter_profile">Мой профиль</a>
                    </li>
                    <li>
                        <a href="/cafe.by/show_product">Меню</a>
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

