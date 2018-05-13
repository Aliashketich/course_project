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
    <%@include file="form.jsp"%>
</body>
</html>