package ch.bfh.java.experiments.web.todospa.controller;

import ch.bfh.java.experiments.web.todospa.model.todo.Todo;
import ch.bfh.java.experiments.web.todospa.model.todo.TodoNotFoundException;
import ch.bfh.java.experiments.web.todospa.model.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api2/todos/*")
public class TodoListServlet extends HttpServlet {

	private static final ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String pathInfo = request.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) {
				// get all todos
				String category = request.getParameter("category");
				User user = (User) request.getAttribute("user");
				List<Todo> todos = category == null ?
						user.getTodoList().getTodos() : user.getTodoList().getTodos(category);
				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json");
				objectMapper.writeValue(response.getOutputStream(), todos);
			} else {
				// get todoM
				int id = Integer.parseInt(pathInfo.substring(1));
				User user = (User) request.getAttribute("user");
				Todo todo = user.getTodoList().findTodo(id);
				response.setStatus(HttpServletResponse.SC_OK);
				response.setContentType("application/json");
				objectMapper.writeValue(response.getOutputStream(), todo);
			}
		} catch (JsonProcessingException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (NumberFormatException | TodoNotFoundException ex) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			// check request path
			String pathInfo = request.getPathInfo();
			if (pathInfo != null && !pathInfo.equals("/")) {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			// parse and validate todoM
			Todo todo = objectMapper.readValue(request.getInputStream(), Todo.class);
			if (todo.getId() != null || todo.getTitle() == null || todo.getTitle().isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			// add todoM
			User user = (User) request.getAttribute("user");
			user.getTodoList().addTodo(todo);
			response.setStatus(HttpServletResponse.SC_CREATED);
			response.setHeader("Location", request.getRequestURI() + "/" + todo.getId());
			response.setContentType("application/json");
			objectMapper.writeValue(response.getOutputStream(), todo);
		} catch (JsonProcessingException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			// check request path
			String pathInfo = request.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			// parse and validate todoM
			int id = Integer.parseInt(pathInfo.substring(1));
			Todo todo = objectMapper.readValue(request.getInputStream(), Todo.class);
			if (todo.getId() != id || todo.getTitle() == null || todo.getTitle().isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			// update todoM
			User user = (User) request.getAttribute("user");
			user.getTodoList().updateTodo(todo);
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json");
			objectMapper.writeValue(response.getOutputStream(), todo);
		} catch (JsonProcessingException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (NumberFormatException | TodoNotFoundException ex) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			// check request path
			String pathInfo = request.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return;
			}
			// remove todoM
			int id = Integer.parseInt(pathInfo.substring(1));
			User user = (User) request.getAttribute("user");
			user.getTodoList().removeTodo(id);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (NumberFormatException | TodoNotFoundException ex) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
