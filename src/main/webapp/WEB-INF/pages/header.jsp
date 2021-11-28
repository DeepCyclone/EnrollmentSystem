<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="localization.language" var = "bundle"/>

<nav class="navbar navbar-expand-lg" style="opacity: 50%">
    <div class="container-fluid">
        <ul class="nav">

            <li class="nav-item d-flex align-items-center">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cpu" viewBox="0 0 16 16">
                        <path d="M5 0a.5.5 0 0 1 .5.5V2h1V.5a.5.5 0 0 1 1 0V2h1V.5a.5.5 0 0 1 1 0V2h1V.5a.5.5 0 0 1 1 0V2A2.5 2.5 0 0 1 14 4.5h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14v1h1.5a.5.5 0 0 1 0 1H14a2.5 2.5 0 0 1-2.5 2.5v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14h-1v1.5a.5.5 0 0 1-1 0V14A2.5 2.5 0 0 1 2 11.5H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2v-1H.5a.5.5 0 0 1 0-1H2A2.5 2.5 0 0 1 4.5 2V.5A.5.5 0 0 1 5 0zm-.5 3A1.5 1.5 0 0 0 3 4.5v7A1.5 1.5 0 0 0 4.5 13h7a1.5 1.5 0 0 0 1.5-1.5v-7A1.5 1.5 0 0 0 11.5 3h-7zM5 6.5A1.5 1.5 0 0 1 6.5 5h3A1.5 1.5 0 0 1 11 6.5v3A1.5 1.5 0 0 1 9.5 11h-3A1.5 1.5 0 0 1 5 9.5v-3zM6.5 6a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3z"></path>
                    </svg>
                    EnrollmentSystem
                </a>
                <c:if test="${sessionScope.get('login') != null}">
                        <fmt:message key="enrollmentsystem.greeting" bundle="${bundle}">
                            <fmt:param>${sessionScope.get('login')}</fmt:param>
                        </fmt:message>
                </c:if>
            </li>
        </ul>

        <ul class="nav justify-content-end">

            <li class = "nav-item">
                <a class="nav-link" href="faculties">
                    <fmt:message key="enrollmentsystem.faculties" bundle="${bundle}"/>
                </a>
            </li>

            <li class = "nav-item">
                <a class="nav-link" href="aboutUs">
                    <fmt:message key="enrollmentsystem.aboutUs" bundle="${bundle}"/>
                </a>
            </li>

            <li class = "nav-item">
                <div class="btn-group">
                    <button class="btn btn-secondary" type="button" id="dropdownMenuClickableInside" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
<%--                        <fmt:message key="enrollmentsystem.languagebutton" bundle="${bundle}"/>--%>
<%--                        <img src="language.png">--%>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuClickableInside">
                        <li><a class="dropdown-item" href="controller?action=change_language&locale=ru_RU">Ru</a></li>
                        <li><a class="dropdown-item" href="controller?action=change_language&locale=en_US">En</a></li>
                    </ul>
                </div>
            </li>

            <c:choose>
            <c:when test = "${sessionScope.get('login') != null}">
                    <li class="nav-item">
                        <c:choose>
                            <c:when test="${sessionScope.get('role') == '1'}">
                                <a class="nav-link" href="controller?action=PRELOAD_ADMIN_PAGE">
                                    <fmt:message key="enrollmentsystem.cabinet" bundle="${bundle}"/>
                                </a>
                            </c:when>
                            <c:when test="${sessionScope.get('role') == '2'}">
                                <a class="nav-link" href="controller?action=">
                                    <fmt:message key="enrollmentsystem.cabinet" bundle="${bundle}"/>
                                </a>
                            </c:when>
                            <c:when test="${sessionScope.get('role') == '3'}">
                                <a class="nav-link" href="controller?action=PRELOAD_USERINFO_PAGE">
                                    <fmt:message key="enrollmentsystem.cabinet" bundle="${bundle}"/>
                                </a>
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?action=logout">
                            <fmt:message key="enrollmentsystem.logout" bundle="${bundle}"/>
                        </a>
                    </li>
            </c:when>

            <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="login">
                            <fmt:message key="enrollmentsystem.login" bundle="${bundle}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="registration">
                            <fmt:message key="enrollmentsystem.signUp" bundle="${bundle}"/>
                        </a>
                    </li>
            </c:otherwise>

        </c:choose>
        </ul>
    </div>
</nav>