<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
    <form action="controller" method="post">
        <label for="login">Enter login</label>
        <input id="login" name= "login" type="text"/>
        <label for="e-mail">Enter e-mail</label>
        <input id="e-mail" name= "email" type="email"/>
        <label for="passwd">Enter password</label>
        <input id="passwd" name= "password" type="password"/>
        <input name= "action" value="sign_up" type="hidden">

        <input type="submit">Submit</input>
    </form>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
