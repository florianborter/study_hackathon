package ch.bfh.java.experiments.softwareengineering.observer;

public class MessageListener implements ModuleListener {
    private final String messageServiceEndpoint;

    public MessageListener(String messageServiceEndpoint) {
        this.messageServiceEndpoint = messageServiceEndpoint;
    }

    @Override
    public void update(String eventType, String module) {
        System.out.println("Sending message to " + messageServiceEndpoint + " about event " + eventType + " in " + module);
    }
}