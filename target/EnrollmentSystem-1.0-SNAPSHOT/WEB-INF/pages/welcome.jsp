<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="localization.language" var = "bundle"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    <%@include file="/resources/js/documentsPageLogic.js"%>
</script>

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
    <table class = "table table-bordered border-primary">
        <caption style="caption-side: top">
            <fmt:message key="enrollmentsystem.processInfo" bundle="${bundle}"/>
        </caption>
        <tr>
            <th><fmt:message key="enrollmentsystem.facultyColumn" bundle="${bundle}"/>
            <th><fmt:message key="enrollmentsystem.budgPlaces" bundle="${bundle}"/>
            <th><fmt:message key="enrollmentsystem.payablePlaces" bundle="${bundle}"/>
            <th><fmt:message key="enrollmentsystem.totalRequestsBudg" bundle="${bundle}"/>
            <th><fmt:message key="enrollmentsystem.totalRequestsPaid" bundle="${bundle}"/>
        </tr>
        <tr>
            <c:forEach var="faculty" items="${requestScope.get('facultiesList')}">
                <td>${faculty.name}</td>
                <td>${faculty.budgetAdmissionPlan}</td>
                <td>${faculty.paidAdmissionPlan}</td>
                <td>${requestScope.get("totalRequestsBudg").get(faculty.name)}</td>
                <td>${requestScope.get("totalRequestsPaid").get(faculty.name)}</td>
                <tr></tr>
            </c:forEach>
        </tr>
    </table>
    <nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <li class="page-item <c:if test="${requestScope.get('prevPage') == null}">disabled</c:if>">
            <a class="page-link" href="controller?action=PRELOAD_WELCOMEPAGE&page=${requestScope.get('prevPage')}" tabindex="-1">Previous</a>
        </li>
        <c:forEach var="i" begin="${requestScope.get('currentPage')}" end="${requestScope.get('endPage')}">
            <li class="page-item">
                <a class="page-link" href="controller?action=PRELOAD_WELCOMEPAGE&page=${i}">${i}</a>
            </li>
        </c:forEach>

        <li class="page-item <c:if test="${requestScope.get('nextPage') == null}">disabled</c:if>">
            <a class="page-link" href="controller?action=PRELOAD_WELCOMEPAGE&page=${requestScope.get('nextPage')}">Next</a>
        </li>
    </ul>
</nav>
</div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
