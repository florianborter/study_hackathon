package ch.bfh.java.experiments.web.todo.controller;

import ch.bfh.java.experiments.web.todo.model.todo.Todo;
import ch.bfh.java.experiments.web.todo.model.todo.TodoList;
import ch.bfh.java.experiments.web.todo.model.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet("/todoList")
public class TodoListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            request.setAttribute("todos", user.getTodoList().getTodos());
            request.getRequestDispatcher("todoList.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String dueDateString = request.getParameter("dueDate");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.GERMAN);
            LocalDate dueDate = LocalDate.parse(dueDateString, formatter);

            Todo todo = new Todo(title, category, dueDate);
            user.getTodoList().addTodo(todo);

            response.sendRedirect("todoList");
        } else  {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }


    }
}
