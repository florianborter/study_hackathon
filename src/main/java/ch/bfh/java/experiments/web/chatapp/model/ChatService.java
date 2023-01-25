package ch.bfh.java.experiments.web.chatapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChatService {

	private static final ChatService instance = new ChatService();

	private final List<ChatMessage> messages = new ArrayList<>();
	private int lastId = 0;

	public static ChatService getInstance() {
		return instance;
	}

	private ChatService() {
		try (Scanner scanner = new Scanner(ChatService.class.getResourceAsStream("/messages.txt"))) {
			while (scanner.hasNextLine()) {
				String[] tokens = scanner.nextLine().split(":");
				addMessage(new ChatMessage(tokens[0], tokens[1]));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public List<ChatMessage> getMessages() {
		return messages;
	}

	public void addMessage(ChatMessage message) {
		message.setId(++lastId);
		messages.add(message);
	}

	public List<ChatMessage> getMessages(String subject) {
		return getMessages().stream().filter(chatMessage -> chatMessage.getSubject().equals(subject)).collect(Collectors.toList());
	}

	public List<String> getSubjects() {
		return getMessages().stream().map(ChatMessage::getSubject).distinct().collect(Collectors.toList());
	}

	public void removeMessage(int id) {
		Optional<ChatMessage> message = messages.stream().filter(it -> it.getId() == id).findFirst();
		message.ifPresent(messages::remove);
	}
}
