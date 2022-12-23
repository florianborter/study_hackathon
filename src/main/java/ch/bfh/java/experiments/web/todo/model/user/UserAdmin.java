package ch.bfh.java.experiments.web.todo.model.user;


import ch.bfh.java.experiments.web.todo.model.todo.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserAdmin {

	private static final UserAdmin instance = new UserAdmin();
	private final List<User> users = new ArrayList<>();

	public static UserAdmin getInstance() {
		return instance;
	}

	private UserAdmin() {
		User user = new User("test", "12345");
		Todo todo = new Todo("Todo", "Test", LocalDate.now());
		user.getTodoList().addTodo(todo);
		users.add(user);
	}

	public User registerUser(String username, String password) throws UserAlreadyExistsException {
		if (findUser(username) != null) {
			throw new UserAlreadyExistsException();
		}
		User user = new User(username, password);
		users.add(user);
		return user;
	}

	public User loginUser(String username, String password) throws InvalidCredentialsException {
		User user = findUser(username);
		if (user == null || !user.getPassword().equals(password)) {
			throw new InvalidCredentialsException();
		}
		return user;
	}

	private User findUser(String username) {
		return users.stream().filter(user -> user.getName().equals(username)).findFirst().orElse(null);
	}
}
