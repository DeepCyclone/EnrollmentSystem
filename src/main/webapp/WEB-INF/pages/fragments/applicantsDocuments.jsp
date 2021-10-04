<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="d-flex align-items-start">

    <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
        <button class="nav-link active" id="v-pills-data-tab" data-bs-toggle="pill" data-bs-target="#v-pills-data" type="button" role="tab" aria-controls="v-pills-data" aria-selected="true">Applicant data</button>
        <button class="nav-link" id="v-pills-settings-tab" data-bs-toggle="pill" data-bs-target="#v-pills-settings" type="button" role="tab" aria-controls="v-pills-settings" aria-selected="false">Account settings</button>
    </div>


    <div class="tab-content" id="v-pills-tabContent">
        <div class="tab-pane fade show active" id="v-pills-data" role="tabpanel" aria-labelledby="v-pills-data-tab">
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
                            <c:forEach var = "facility" items="${requestScope.get('facilites')}">
                                <div class="form-check form-switch">
                                    <label class="form-check-label" for="${facility}}">${facility}</label>
                                    <input class="form-check-input" type="checkbox" id="${facility}">
                                </div>
                            </c:forEach>
                        </div>
                        <input type = "hidden" name="action" value="update_info">
                        <input type = "submit" value="submit">
<%--                        //TOD0 Выше подгружается из БД--%>
                    </form>

                </div>

                <div class="tab-pane fade" id="nav-faculties" role="tabpanel" aria-labelledby="nav-faculties-tab">
                    <form name="faculties-tab" method="post" action="controller">
                    <div class = "education-form-selector" id = "education-form-selector"></div>
                    <div class = "marks-input" id = "marks-input">
                        <select class="subjects-marks-input" id = "subjects-marks-input" onchange="marksSelectorListener()">
                        <c:forEach var = "mark" items="${requestScope.get('marks')}">
                            <label for = "${mark.name}">${mark.name}</label>
                            <input type = "checkbox" name="${mark.name}" id = ${mark.name} >${mark.resultValue}</input>
                        </c:forEach>
                        </select>
                    </div>
                        <div class = "faculties-selector" id = "faculties-selector">

                        </div>
                        <button type="submit" value="save"></button>
                    </form>
                </div>
            </div>

            <c:out value="Статус: ${requestScope.get('enrollmentStatus')}"/>
        </div>


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
                <input type = "submit">
            </form>
        </div>
    </div>
</div>

<script type="application/javascript">
    function marksSelectorListener(){
        if(document.getElementById("faculties-selector").value !== ''){
            const buttonCode = "<div class=\"form-check form-switch\">" +
            "<input class=\"form-check-input\" type=\"checkbox\" id=\"flexSwitchCheckChecked\" checked>" +
            "<label class=\"form-check-label\" for=\"flexSwitchCheckChecked\">Checked switch checkbox input</label>" +
            "</div>"
                document.getElementById("education-form-selector").innerHTML = buttonCode;
        }
        else{
            document.getElementById("education-form-selector").innerHTML = '';
        }
    }
</script>

