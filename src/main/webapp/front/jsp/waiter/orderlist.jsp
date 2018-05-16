<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="entity.Order" scope="page" id="order"/>
<jsp:useBean class="entity.Product" scope="page" id="product"/>
<jsp:useBean class="entity.OrderProduct" scope="page" id="orderProduct"/>
<style>
    <%@include file="/front/css/menu.css"%>
    <%@include file="/front/css/orderlist.css" %>
</style>

<div class="order-container">
    <div class="qa-message-list">
        <c:choose>
            <c:when test="${orders!=null}">
                <c:forEach var="order" items="${orders}">
                    <div class="message-item">
                        <div class="message-inner">
                            <div class="message-head clearfix">
                                <form action="/cafe.by/close_order?idOrder=${order.idOrder}" method="post">
                                    <div class="user-detail">
                                        <h5 class="handle">Заказ №${order.idOrder} </h5>
                                        <c:choose>
                                            <c:when test="${order.status eq 'true'}">
                                                <button type="submit" class="btn-right"
                                                        class="btn btn-default">Закрыть счет</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" disabled="disabled" class="btn-right"
                                                        class="btn btn-default">Счет закрыт</button>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="post-meta">
                                            <div class="asker-meta">
                                                <span class="qa-message-what"></span>
                                                <span class="qa-message-when">
                                                <span class="qa-message-when-data">${order.date}</span></br>
                                            </span>
                                                <span class="qa-message-who">
                                                <span class="qa-message-who-pad">Общая сумма: </span>
                                                <span class="qa-message-who-data">${order.totalCost} BYN</span></br>
                                            </span>
                                                <span class="qa-message-what"></span>
                                                <span class="qa-message-who">
                                                <span class="qa-message-who-pad">Сумма со скидкой: </span>
                                                <span class="qa-message-who-data">${order.costWithDiscount} BYN</span></br>
                                            </span>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="qa-message-content">
                                <c:forEach var="orderProduct" items="${order_products}">

                                    <c:if test="${orderProduct.idOrder==order.idOrder}">
                                        <c:forEach var="product" items="${products}">
                                            <c:if test="${orderProduct.idProduct==product.idProduct}">

                                                    <p>
                                                        <c:choose>
                                                            <c:when test="${locale eq 'ru'}">
                                                                ${product.name} (${product.cost} BYN x ${orderProduct.quantity})
                                                            </c:when>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${order.status eq 'true'}">
                                                                <button type="submit" class="btn-right"
                                                                        class="btn btn-default">${remove_from_basket}</button>
                                                            </c:when>
                                                        </c:choose>
                                                    </p>

                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </div><!-- ./qa-message-list -->

</div>
<!-- ./container -->
