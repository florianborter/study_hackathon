package ch.bfh.java.experiments.softwareengineering.observer;

public class Main {
    public static void main(String[] args) {
        ModuleEditor editor = new ModuleEditor();
        ModuleListener listener = new MessageListener("https://something.zip");

        editor.events.subscribe("message", listener);

        // the change events do not get printed, as we only subscribed to messages
        editor.changeModule("New Module is happening");
        editor.changeModule("New Module is not happening");
        editor.changeModule("BTI7777");

        // the message events are printed
        editor.sendMessage();
        editor.sendMessage();

        // now also subscribe and print something on change event
        editor.events.subscribe("change", listener);
        editor.changeModule("BTI7");

    }
}
