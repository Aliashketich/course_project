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

        <form id="add-product-form" class="form-horizontal form-horizontal-reg" method="post"
              action="/cafe.by/add_product">
            <span class="heading">Добавление продукта</span>
            <div class="form-group">
                <select id="product-type" name="product_type">
                    <option value="blin">Блины</option>
                    <option value="salat">Салаты</option>
                    <option value="soup">Супы</option>
                    <option value="fish">Блюда из рыбы</option>
                    <option value="chicken">Блюда из курицы</option>
                    <option value="potato">Блюда из картофеля</option>
                    <option value="beef">Блюда из говядины</option>
                    <option value="desserts">Десерты</option>
                    <option value="drink">Напитки</option>
                </select>
            </div>
            <div class="form-group">
                <input type="text" class="form-control has-border" id="name"
                       placeholder="Название" name="name">
            </div>
            <div class="form-group">
                <input type="text" class="form-control has-border" id="cost"
                       placeholder="Цена за порцию" name="cost">
            </div>
            <div class="form-group">
                <input type="text" class="form-control has-border" id="description"
                       placeholder="Описание" name="description">
            </div>
            <button type="submit" id="add-product-button"
                    class="btn btn-default">Добавить в меню</button>
        </form>

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
