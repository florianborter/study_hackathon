package ch.bfh.java.experiments.web.todo.controller;

import ch.bfh.java.experiments.web.todo.model.todo.Todo;
import ch.bfh.java.experiments.web.todo.model.todo.TodoList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet("/todoList")
public class TodoListServlet extends HttpServlet {

    private final TodoList todoList = new TodoList();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* This is used for simple html response
        response.setContentType("text/html");

        InputStream inputStream = this.getServletContext().getResourceAsStream("./" + "todo.html");

        try {
            response.getOutputStream().write(inputStream.readAllBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }*/

        request.setAttribute("todos", todoList.getTodos());
        request.getRequestDispatcher("todoList.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String dueDateString = request.getParameter("dueDate");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.GERMAN);
        LocalDate dueDate = LocalDate.parse(dueDateString, formatter);

        Todo todo = new Todo(title, category, dueDate);
        todoList.addTodo(todo);

        response.sendRedirect("todoList");
    }
}
