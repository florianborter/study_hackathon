<%--
  Created by IntelliJ IDEA.
  User: flobo
  Date: 02.01.2023
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register / Login</title>
</head>
<body>
    <h1>Login</h1>
    <form method="post" action="/userAdmin">
        <label for="username">username:</label><input id="username" name="username"><br>
        <label for="password">password:</label><input type="password" id="password" name="password"><br>
        <button type="submit" name="action" value="login">Login</button>
        <button type="submit" name="action" value="register">Register</button>
    </form>

    <br><br>
    <div>${message}</div>
</body>
</html>
