package ch.bfh.java.experiments.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getRequestURL());
        response.setStatus(SC_OK);
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            out.println("Hello World");
            out.println(LocalDateTime.now().withNano(0));
        }
    }
}