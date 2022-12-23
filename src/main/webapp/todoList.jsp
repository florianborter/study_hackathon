<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
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
    <a href="todo.jsp" class="button primary">New Todo</a>
</body>
</html>
