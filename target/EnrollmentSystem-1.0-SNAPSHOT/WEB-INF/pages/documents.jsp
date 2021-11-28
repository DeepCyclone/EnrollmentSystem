<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.language" var = "bundle"/>

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
        <button class="nav-link active" id="v-pills-data-tab" data-bs-toggle="pill" data-bs-target="#v-pills-data" type="button" role="tab" aria-controls="v-pills-data" aria-selected="true">
            <c:choose>
                <c:when test="${sessionScope.get('role') == '1'}">
                    <th><fmt:message key="enrollmentsystem.managingPanel" bundle="${bundle}"/></th>
                </c:when>
                <c:when test="${sessionScope.get('role') == '2'}">
                    <th><fmt:message key="enrollmentsystem.enrollmentPanel" bundle="${bundle}"/></th>
                </c:when>
                <c:when test="${sessionScope.get('role') == '3'}">
                    <th><fmt:message key="enrollmentsystem.applicantPanel" bundle="${bundle}"/></th>
                </c:when>
            </c:choose>
        </button>
        <button class="nav-link" id="v-pills-settings-tab" data-bs-toggle="pill" data-bs-target="#v-pills-settings" type="button" role="tab" aria-controls="v-pills-settings" aria-selected="false">
            <fmt:message key="enrollmentsystem.accountSettingsTab" bundle="${bundle}"/>
        </button>

        <c:if test="${sessionScope.get('role') == '1'}">
        <button class="nav-link" id="v-pills-management-tab" data-bs-toggle="pill" data-bs-target="#v-pills-management" type="button" role="tab" aria-controls="v-pills-management" aria-selected="true">
            <th><fmt:message key="enrollmentsystem.systemManagementPanel" bundle="${bundle}"/></th>
        </button>
        </c:if>

    </div>


    <div class="tab-content" id="v-pills-tabContent">
        <div class="tab-pane fade show active" id="v-pills-data" role="tabpanel" aria-labelledby="v-pills-data-tab">
            <c:choose>
                <c:when test="${sessionScope.get('role') == '1'}">
                    <%@include file="fragments/adminDocuments.jspf"%>
                </c:when>
                <c:when test="${sessionScope.get('role') == '2'}">
                    <%@include file="fragments/moderDocuments.jspf"%>
                </c:when>
                <c:when test="${sessionScope.get('role') == '3'}">
                    <%@include file="fragments/applicantsDocuments.jspf"%>
                </c:when>
            </c:choose>
        </div>

            <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                <form action="controller" method="post" id="settings">
                    <table class="table table-bordered border-primary">
                        <tr>
                            <td>
                                <label for="login">
                                    <fmt:message key="enrollmentsystem.loginData" bundle="${bundle}"/>
                                </label>
                                <input name = "login" id = "login" minlength="3" maxlength="30" size="15" value="${sessionScope.get('login')}">
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label for="oldPassword">
                                    <fmt:message key="enrollmentsystem.oldPassword" bundle="${bundle}"/>
                                </label>
                                <input type = "password" name = "oldPassword" id = "oldPassword" minlength="4" maxlength="10" autocomplete="new-password">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="newPassword">
                                    <fmt:message key="enrollmentsystem.newPassword" bundle="${bundle}"/>
                                </label>

                                <input type = "password" name = "newPassword" id = "newPassword" minlength="4" maxlength="10">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="email">
                                    <fmt:message key="enrollmentsystem.emailData" bundle="${bundle}"/>
                                </label>
                                <input name = "email" id="email" type="email"/>
                            </td>
                        </tr>
                    </table>
                    <input type = "hidden" name="action" value="update_info">
                    <input type = "submit" value = <fmt:message key="enrollmentsystem.processInfo" bundle="${bundle}"/>>
                </form>
            </div>
            <c:if test="${sessionScope.get('role') == '1'}">
                <div class="tab-pane fade" id="v-pills-management" role="tabpanel" aria-labelledby="v-pills-management-tab">
                    <form method="post" name="enrollStart" action="controller">
                        <input type="hidden" name="action" value="start_Enrollment">
                        <button type="submit">Enroll</button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>



