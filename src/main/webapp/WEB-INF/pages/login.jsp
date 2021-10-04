<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Authentication</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <style>
        <%@include file="/resources/css/style.css"%>
        <%@include file="/resources/css/login.css"%>
    </style>
</head>
<body>
<%--@elvariable id="contacts" type="java.util.Set<com.wrox.Contact>"--%>

<%--<c:out value="" default="ABC"></c:out>--%>

<%--<c:if test="${a == b}"></c:if> можно ещё засейвить одно значение через var и потом использовать в других проверках--%>

<%--<c:choose>--%>
<%--    <c:when test=""></c:when>--%>
<%--    <c:when test=""></c:when>--%>
<%--    <c:otherwise></c:otherwise>--%>
<%--</c:choose>--%>

<%--<c:forEach var="i" begin="0" end="100" step="3">--%>
<%--    generated ${i}--%>
<%--</c:forEach>--% также можно итерироваться по коллекциям и т.п. !!!>



<%--<a href="<c:url value="/index.jsp" context="/EnrollmentSystem">--%>
<%--<c:param name="forumId" value="12" />--%>
<%--</c:url>">Product Forum</a>--%>


<%--<c:set><c:remove var=""></c:remove></c:set>--% можно устанавливать свои переменные>
<%--<jsp:forward> <c:redirect></c:redirect> --%>
<jsp:include page="/WEB-INF/pages/header.jsp"/>

<c:if test="${sessionScope.get('login') != null}">
    <c:redirect url="/documents"/>
</c:if>
<%--передача пароля в защищённом виде!//TODO--%>
<div class = "auth">
<form action = "controller" method = "post">
    <label for = "login">Enter login</label>
    <input name = "login" id = "login" minlength="3" maxlength="30" size="15">
        <br>
    <label for = "password">Enter password</label>
    <input type = "password" name = "password" id = "password" minlength="4" maxlength="10">
    <br>
    <input type="hidden" name="action" value="authorization">
    <input type="submit" value="Log in">
</form>
    <c:if test="${requestScope.get('invalidCredentials') eq 'true'}">
        <c:out value="You've entered invalid username or password"/>
    </c:if>
</div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
