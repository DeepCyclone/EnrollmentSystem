<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Приёмка</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<c:out value="${sessionScope.get('description')}"/>
<div class = "enrollmenttable">
    <caption>Ниже расположена информация о ходе приёмной комиссии</caption>
    <table class = "table table-bordered border-primary">
        <tr>
            <th>Факультет</th>
            <th>Количество мест(бюдж)</th>
            <th>Количество мест(платн)</th>
            <th>Описание</th>
            <th>Подано заявлений</th>
        </tr>
        <tr>
            <c:forEach var="faculty" items="${sessionScope.get('facultiesList')}">
                <td>${faculty.name}</td>
                <td>${faculty.budgetAdmissionPlan}</td>
                <td>${faculty.paidAdmissionPlan}</td>
                <td>${faculty.description}</td>
                <td>100</td>
                <tr/>
            </c:forEach>
        </tr>
    </table>
</div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
