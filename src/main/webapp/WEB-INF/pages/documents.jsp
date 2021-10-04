<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<c:choose>
    <c:when test="${sessionScope.get('role') == '1'}">
        <c:out value="You are admin"/>
    </c:when>
    <c:when test="${sessionScope.get('role') == '2'}">
        <c:out value="You are moder"/>
    </c:when>
    <c:when test="${sessionScope.get('role') == '3'}">
        <%@include file="fragments/applicantsDocuments.jsp"%>
     </c:when>
</c:choose>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>



