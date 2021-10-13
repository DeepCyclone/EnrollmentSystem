<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    <%@include file="dropdownsUploader.js"%>
</script>

<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <button class="nav-link active" id="nav-personal-tab" data-bs-toggle="tab" data-bs-target="#nav-personal" type="button" role="tab" aria-controls="nav-personal" aria-selected="true">Personal data</button>
        <button class="nav-link" id="nav-faculties-tab" data-bs-toggle="tab" data-bs-target="#nav-faculties" type="button" role="tab" aria-controls="nav-faculties" aria-selected="false">Faculties</button>
    </div>
</nav>

<div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-personal" role="tabpanel" aria-labelledby="nav-personal-tab">

        <form action="controller" method="post" id="personalInfo">
            <table class="table table-bordered border-primary">
                <tr>
                    <td>
                        <label for="name">name</label>
                        <input name = "name" id = "name" type="text" value="${requestScope.get('userinfo').name}">
                        <label for="surname">surname</label>
                        <input name = "surname" id="surname" type="text" value="${requestScope.get('userinfo').surname}"/>
                        <label for="patronymic">patronymic</label>
                        <input name = "patronymic" id="patronymic" type="text" value="${requestScope.get('userinfo').patronymic}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="passport">passport</label>
                        <input name = "passport" id = "passport" type="text" value="${requestScope.get('userinfo').passport}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="address">address</label>
                        <input name = "address" id = "address" type="text" value="${requestScope.get('userinfo').address}"/>
                    </td>
                </tr>
            </table>
            <label for = "facilities-selector">Choose facilities you have</label>
            <div class= "facilities-selector" id = "facilities-selector">
                    <div class="form-check form-switch">
                        <label class="form-check-label" for="goldmedalFacility">Gold Medal</label>
                        <input class="form-check-input" type="checkbox" id="goldmedalFacility" name="goldmedalFacility">
                    </div>
                    <div class="form-check form-switch">
                        <label class="form-check-label" for="orphanFacility">is Orphan</label>
                        <input class="form-check-input" type="checkbox" id="orphanFacility" name="orphanFacility">
                    </div>
            </div>
            <input type = "hidden" name="action" value="update_info">
            <input type = "submit" value="submit">
            <%--Выше подгружается из БД--%>
        </form>

    </div>

    <div class="tab-pane fade" id="nav-faculties" role="tabpanel" aria-labelledby="nav-faculties-tab">
        <form name="faculties-tab" method="post" action="controller">
            <div class = "education-form-selector" id = "education-form-selector"></div>
            <div class = "marks-input" id = "marks-input">
                <c:forEach var = "mark" items="${requestScope.get('marksValues')}">
                    <div onload = "preloadFacultiesMap()">
                        <label for = "${mark.name}">${mark.name}</label>
                        <input class = "mark-cell" type="text" oninput="{marksSelectorListener(this)}" value="${mark.value}" name="mark:${mark.id}:${mark.name}" id = ${mark.name}>
                    </div>
                </c:forEach>
                </ul>
            </div>
            <table id = 'faculties-selector'>

            </table>
            <input type="hidden" name = "action" value="UPDATE_STUDYING_INFO">
            <input type="submit" value="save"/>
        </form>
    </div>
</div>

<c:out value="Статус: ${requestScope.get('enrollmentStatus')}"/>
</div>




