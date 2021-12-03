<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:out value="${requestScope.get('aboutUsMsg1')}"/>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
