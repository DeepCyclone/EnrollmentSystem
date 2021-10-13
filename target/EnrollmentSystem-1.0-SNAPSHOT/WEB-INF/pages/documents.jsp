<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style><%@include file="/resources/css/style.css"%></style>
</head>
<body>

<jsp:include page="/WEB-INF/pages/header.jsp"/>
<div class="d-flex align-items-start">

    <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
        <button class="nav-link active" id="v-pills-data-tab" data-bs-toggle="pill" data-bs-target="#v-pills-data" type="button" role="tab" aria-controls="v-pills-data" aria-selected="true">Applicant data</button>
        <button class="nav-link" id="v-pills-settings-tab" data-bs-toggle="pill" data-bs-target="#v-pills-settings" type="button" role="tab" aria-controls="v-pills-settings" aria-selected="false">Account settings</button>
    </div>


    <div class="tab-content" id="v-pills-tabContent">
        <div class="tab-pane fade show active" id="v-pills-data" role="tabpanel" aria-labelledby="v-pills-data-tab">
            <c:choose>
                <c:when test="${sessionScope.get('role') == '1'}">
                    <%@include file="fragments/adminDocuments.jsp"%>
                </c:when>
                <c:when test="${sessionScope.get('role') == '2'}">
                    <%@include file="fragments/moderDocuments.jsp"%>
                </c:when>
                <c:when test="${sessionScope.get('role') == '3'}">
                    <%@include file="fragments/applicantsDocuments.jsp"%>
                </c:when>
            </c:choose>

            <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                <form action="controller" method="post" id="settings">
                    <table class="table table-bordered border-primary">
                        <tr>
                            <td>
                                <label for="login">login</label>
                                <input name = "login" id = "login" type="text">
                                <label for="password">password</label>
                                <input name = "password" id="password" type="text"/>
                                <label for="email">email</label>
                                <input name = "email" id="email" type="text"/>
                            </td>
                        </tr>
                    </table>
                    <input type = "hidden" name="action" value="update_info">
                    <input type = "submit" value="submit">
                </form>
            </div>
        </div>
    </div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>



