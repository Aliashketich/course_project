<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="entity.Product" scope="page" id="product"/>

<head>
    <style>
        <%@include file="/front/css/menu.css"%>
        <%@include file="/front/css/orderlist.css" %>
    </style>
    <title>Menu</title>
</head>
<div class="order-container">
    <div class="qa-message-list">
        <c:choose>
            <c:when test="${products!=null}">
                <c:forEach var="product" items="${products}">
                    <div class="message-item">
                        <div class="message-inner">
                            <div class="qa-message-content">
                                <p>
                                    №${product.idProduct}.
                                </p>
                                <p>
                                    Категория: ${product.type}
                                </p>
                                <p>
                                    Название: ${product.name}, цена (1порция): ${product.cost} BYN.
                                </p>
                                <p>
                                    Описание: ${product.description}.
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </div><!-- ./qa-message-list -->
</div>
<!-- ./container -->
