<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="localization.language" var = "bundle"/>

<html>
<head>
    <title><fmt:message key="enrollmentsystem.pageTitle" bundle="${bundle}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <style>
        <%@include file="/resources/css/style.css"%>
        <%@include file="/resources/css/login.css"%>
    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp"/>

<c:if test="${sessionScope.get('login') != null}">
    <c:redirect url="/documents"/>
</c:if>

<div class = "auth">
<form action = "controller" method = "post">
    <label for = "login">
        <fmt:message key="enrollmentsystem.loginField" bundle="${bundle}"/>
    </label>
    <input name = "login" id = "login" minlength="3" maxlength="15" size="10">
    <br>
    <label for = "password">
        <fmt:message key="enrollmentsystem.passwordField" bundle="${bundle}"/>
    </label>
    <input type = "password" name = "password" id = "password" minlength="4" maxlength="10">
    <br>
    <input type="hidden" name="action" value="authentication">
    <input type="submit" value="Log in">
</form>
    <c:if test="${requestScope.get('invalidCredentials') eq 'true'}">
        <c:out value="You've entered invalid username or password"/>
    </c:if>
</div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
