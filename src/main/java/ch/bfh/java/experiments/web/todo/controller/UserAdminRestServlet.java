package ch.bfh.java.experiments.web.todo.controller;

import ch.bfh.java.experiments.web.todo.model.user.InvalidCredentialsException;
import ch.bfh.java.experiments.web.todo.model.user.User;
import ch.bfh.java.experiments.web.todo.model.user.UserAdmin;
import ch.bfh.java.experiments.web.todo.model.user.UserAlreadyExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/users")
public class UserAdminRestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");

        User user = null;
        try {
            if (action.equals("register")) {
                user = UserAdmin.getInstance().registerUser(username, password);

            } else if (action.equals("login")) {
                user = UserAdmin.getInstance().loginUser(username, password);
            } else {
                request.getSession().invalidate();
            }
        } catch (UserAlreadyExistsException e) {
            request.setAttribute("message", "User already exists");
        } catch (InvalidCredentialsException e) {
            request.setAttribute("message", "Invalid credentials");
        }

        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("todoList");
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
