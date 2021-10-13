<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
                    <i>Hi,${sessionScope.get('login')}</i>
                </c:if>
            </li>
        </ul>
        <c:choose>
            <c:when test = "${sessionScope.get('login') != null}">
                <ul class="nav justify-content-end">
                    <li class="nav-item">
                        <c:choose>
                            <c:when test="${sessionScope.get('role') == '1'}">
                                <a class="nav-link" href="controller?action=PRELOAD_ADMIN_PAGE">Cabinet</a>
                            </c:when>
                            <c:when test="${sessionScope.get('role') == '2'}">
                                <a class="nav-link" href="controller?action=">Cabinet</a>
                            </c:when>
                            <c:when test="${sessionScope.get('role') == '3'}">
                                <a class="nav-link" href="controller?action=PRELOAD_USERINFO_PAGE">Cabinet</a>
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?action=logout">Logout</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="nav justify-content-end">
                    <li class="nav-item">
                        <a class="nav-link" href="login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="registration">Sign up</a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>
        <li class="nav-item">
            <div class="dropdown">
                <button class="btn btn-secondary" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src= "${pageContext.request.contextPath}/resources/images/ru.png" alt="lang">
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <li><button class="dropdown-item" type="button">

                    </button></li>
                    <li><button class="dropdown-item" type="button">

                    </button></li>
                </ul>
            </div>
        </li>
    </div>
</nav>