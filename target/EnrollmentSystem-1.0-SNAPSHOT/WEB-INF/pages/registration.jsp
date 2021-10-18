<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="localization.language" var = "bundle"/>

<html>
<head>
    <title><fmt:message key="enrollmentsystem.pageTitle" bundle="${bundle}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
    <form action="controller" method="post">
        <label for="login">
            <fmt:message key="enrollmentsystem.loginField" bundle="${bundle}"/>
        </label>
        <input id="login" name= "login" type="text"/>
        <label for="e-mail">
            <fmt:message key="enrollmentsystem.emailField" bundle="${bundle}"/>
        </label>
        <input id="e-mail" name= "email" type="email"/>
        <label for="passwd">
            <fmt:message key="enrollmentsystem.passwordField" bundle="${bundle}"/>
        </label>
        <input id="passwd" name= "password" type="password"/>
        <input name= "action" value="sign_up" type="hidden">

        <input type="submit">
            <fmt:message key="enrollmentsystem.submittingButton" bundle="${bundle}"/>
    </form>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
