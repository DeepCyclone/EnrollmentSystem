<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class = "users-list" id = "users-list">
    <table class="table-light">
        <tbody>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Факультеты</th>
            <th>Форма обучения</th>
            <th>Статус</th>
            <th>Изменение статуса</th>
        </tr>
    <c:forEach var="user" items="${requestScope.get('users')}">
        <td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.surname}</td>
            <td>${user.name}</td>
            <td>${user.patronymic}</td>
            <td>
            <c:forEach var = "faculty" items="${user.faculties}">
                <p>${faculty}</p>
            </c:forEach>
            </td>
            <td>${user.educationFormName}</td>
            <td>${user.enrollmentStatus}</td>
            <td>
                <input type = "checkbox" value="зачислить"/>
            </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</div>