package ch.bfh.java.experiments.web.chatapp.controller;

import ch.bfh.java.experiments.web.chatapp.model.ChatMessage;
import ch.bfh.java.experiments.web.chatapp.model.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet({"/api3/messages", "/api3/messages/{id}"})
public class MessageServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(MessageServlet.class.getName());

    private final ChatService chatService = ChatService.getInstance();
    private final ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Getting messages");
        String subject = request.getParameter("subject");
        List<ChatMessage> messages;
        if (subject != null && !subject.isBlank()) {
            messages = chatService.getMessages(subject);
        } else {
            messages = chatService.getMessages();
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            chatService.removeMessage(id);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
