<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>TodoList</title>
</head>
<body>
    <c:if test="${empty user}">
        <c:redirect url="login.jsp"/>
    </c:if>
    <h1>${user.name}'s Todo List</h1>

    <table>
        <tr>
            <th>Title</th>
            <th>Category</th>
            <th>Due Date</th>
        </tr>
        <c:forEach items="${todos}" var="todo">
            <tr>
                <td>${todo.title}</td>
                <td>${todo.category}</td>
                <td>${todo.dueDate}</td>
            </tr>
        </c:forEach>
    </table>
    <form action="/userAdmin" method="post">
        <a href="todo.jsp" class="button primary">New Todo</a>
        <button name="action" type="submit" value="logout">Logout</button>
    </form>
</body>
</html>
