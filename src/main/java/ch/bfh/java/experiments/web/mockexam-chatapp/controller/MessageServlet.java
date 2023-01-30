package chatapp.controller;

import chatapp.model.ChatMessage;
import chatapp.model.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/api/messages/*")
public class MessageServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(MessageServlet.class.getName());

	private final ChatService chatService = ChatService.getInstance();
	private final ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Getting messages");
		List<ChatMessage> messages;
		String subject = request.getParameter("subject");
		if (subject == null || subject.isEmpty()) {
			messages = chatService.getMessages();
		} else {
			messages = chatService.getMessages(subject);
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		objectMapper.writeValue(response.getOutputStream(), messages);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Adding message");
		try {
			// parse and validate chat message
			ChatMessage message = objectMapper.readValue(request.getInputStream(), ChatMessage.class);
			if (message.getId() != null || message.getText() == null || message.getText().isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			// add chat message
			chatService.addMessage(message);
			response.setStatus(HttpServletResponse.SC_CREATED);
			response.setContentType("application/json");
			objectMapper.writeValue(response.getOutputStream(), message);
		} catch (JsonProcessingException ex) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getPathInfo();
		if (path == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		int id;
		try {
			id = Integer.parseInt(path);
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (!chatService.removeMessage(id)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		response.setStatus(HttpServletResponse.SC_OK);
	}
}
