<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>todo webapp</title>
</head>
<body>
<div>
    <form method="post" action="/todoList">
        <label for="title">Title:</label><input id="title" name="title"><br>
        <label for="category">Category:</label><input id="category" name="category"><br>
        <label for="dueDate">Due date:</label><input id="dueDate" name="dueDate" type="date"><br>
        <input type="submit" value="Save Todo">
    </form>
</div>
</body>
</html>