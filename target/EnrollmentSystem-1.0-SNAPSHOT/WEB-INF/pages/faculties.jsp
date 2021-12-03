<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="localization.language" var = "bundle"/>

<html>
<head>
    <title>
        <fmt:message key="enrollmentsystem.pageTitle" bundle="${bundle}"/>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<div class = "enrollmenttable">
    <table class = "table">
        <tr>
            <th><fmt:message key="enrollmentsystem.facultyColumn" bundle="${bundle}"/>
            <th><fmt:message key="enrollmentsystem.facultyDescription" bundle="${bundle}"/>
        </tr>
        <tr>
            <c:forEach var="faculty" items="${requestScope.get('facultiesList')}">
            <td>${faculty.name}</td>
            <td>${faculty.description}</td>
        <tr></tr>
        </c:forEach>
        </tr>
    </table>
</div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
