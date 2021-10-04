<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EnrollmentSystem</title>
</head>
<body>
<c:redirect url="/controller">
    <c:param name="action" value="preload_welcomePage"/>
</c:redirect>

<%--<jsp:include page="/controller?action=preload_welcomePage"/>//TODO вариант странный без смены ссылки--%>
</body>
</html>
