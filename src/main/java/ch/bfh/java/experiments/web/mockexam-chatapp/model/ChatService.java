package chatapp.model;

import java.util.ArrayList;
import java.util.List;
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

	public List<ChatMessage> getMessages(String subject) {
		return messages.stream().filter(message -> message.getSubject().equalsIgnoreCase(subject)).collect(Collectors.toList());
	}

	public List<String> getSubjects() {
		return messages.stream().map(ChatMessage::getSubject).distinct().collect(Collectors.toList());
	}

	public void addMessage(ChatMessage message) {
		message.setId(++lastId);
		messages.add(message);
	}

	public boolean removeMessage(Integer id) {
		return messages.removeIf(message -> message.getId().equals(id));
	}
}
