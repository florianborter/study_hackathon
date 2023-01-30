package chatapp.controller;

import chatapp.model.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/api/subjects")
public class SubjectServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(SubjectServlet.class.getName());

	private final ChatService chatService = ChatService.getInstance();
	private final ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Getting subjects");
		List<String> subjects = chatService.getSubjects();
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		objectMapper.writeValue(response.getOutputStream(), subjects);
	}
}
