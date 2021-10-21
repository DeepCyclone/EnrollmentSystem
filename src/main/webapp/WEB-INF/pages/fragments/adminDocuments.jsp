<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="localization.language" var = "bundle"/>

<script>
    <%@include file="adminPageLogic.js"%>
</script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

<div class = "users-list" id = "users-list">
    <table class="table table-light">
        <tbody>
        <tr>
            <th><fmt:message key="enrollmentsystem.idField" bundle="${bundle}"/></th>
            <th><fmt:message key="enrollmentsystem.loginData" bundle="${bundle}"/></th>
            <th><fmt:message key="enrollmentsystem.surnameField" bundle="${bundle}"/></th>
            <th><fmt:message key="enrollmentsystem.name" bundle="${bundle}"/></th>
            <th><fmt:message key="enrollmentsystem.patronymic" bundle="${bundle}"/></th>
            <th><fmt:message key="enrollmentsystem.facultyColumn" bundle="${bundle}"/></th>
            <th><fmt:message key="enrollmentsystem.educationForm" bundle="${bundle}"/></th>
            <th><fmt:message key="enrollmentsystem.enrollmentStatus" bundle="${bundle}"/></th>
        </tr>
    <c:forEach var="user" items="${requestScope.get('users')}">
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.surname}</td>
            <td>${user.name}</td>
            <td>${user.patronymic}</td>
            <td>
            <c:forEach var = "info" items="${user.enrollmentInfo}">
                    ${info.facultyName}
                        <c:forEach var = "i" begin="0" end="${info.educationFormStatuses.size()}">
                            <br>
                        </c:forEach>
            </c:forEach>
            </td>

            <td>
            <c:forEach var = "info" items="${user.enrollmentInfo}">
                <c:forEach var = "form" items="${info.educationFormStatuses}">
                    <p>
                        ${form.key}
                    </p>
                </c:forEach>
            </c:forEach>
            </td>

            <td>
                <c:forEach var = "info" items="${user.enrollmentInfo}">
                    <c:forEach var = "form" items="${info.educationFormStatuses}">
                        <div>
                                ${form.value}
                        <div class="btn-group">
                            <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Change status
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <input type="button" onclick="parseAndSendUserStatus(this)" class="btn-check btn-sm" name="${user.id}:${info.facultyName}:In progress" id="${user.id}:${info.facultyName}:1" autocomplete="off" value="${form.key}:In progress">
                                    <label class="btn btn-outline-danger btn-sm" for="${user.id}:${info.facultyName}:1">
                                        In progress
                                    </label>
                                </li>
                                <li>
                                    <input type="button" onselect="parseAndSendUserStatus(this)" class="btn-check" name="${user.id}:${info.facultyName}:Enrolled" id="${user.id}:${info.facultyName}:2" autocomplete="off" value="${form.key}:Enrolled">
                                    <label class="btn btn-outline-danger btn-sm" for="${user.id}:${info.facultyName}:2">
                                        Enrolled
                                    </label>
                                </li>
                                <li>
                                    <input type="button" onclick="parseAndSendUserStatus(this)" class="btn-check" name="${user.id}:${info.facultyName}:Declined" id="${user.id}:${info.facultyName}:3" autocomplete="off" value="${form.key}:Declined">
                                    <label class="btn btn-outline-danger btn-sm" for="${user.id}:${info.facultyName}:3">
                                        Declined
                                    </label>
                                </li>
                                <li>
                                    <input type="button" onclick="parseAndSendUserStatus(this)" class="btn-check" name="${user.id}:${info.facultyName}:Documents accepted" id="${user.id}:${info.facultyName}:4" autocomplete="off" value="${form.key}:Documents accepted">
                                    <label class="btn btn-outline-danger btn-sm" for="${user.id}:${info.facultyName}:4">
                                        Documents accepted
                                    </label>
                                </li>
                            </ul>
                        </div>
                        </div>
                        <br>
                    </c:forEach>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item <c:if test="${requestScope.get('prevPage') == null}">disabled</c:if>">
                <a class="page-link" href="controller?action=PRELOAD_ADMIN_PAGE&page=${requestScope.get('prevPage')}" tabindex="-1">Previous</a>
            </li>
            <c:forEach var="i" begin="${requestScope.get('currentPage')}" end="${requestScope.get('endPage')}">
                <li class="page-item">
                    <a class="page-link" href="controller?action=PRELOAD_ADMIN_PAGE&page=${i}">${i}</a>
                </li>
            </c:forEach>

            <li class="page-item <c:if test="${requestScope.get('nextPage') == null}">disabled</c:if>">
                <a class="page-link" href="controller?action=PRELOAD_ADMIN_PAGE&page=${requestScope.get('nextPage')}">Next</a>
            </li>
        </ul>
    </nav>
</div>