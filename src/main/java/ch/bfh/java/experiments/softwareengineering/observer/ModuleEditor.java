package ch.bfh.java.experiments.softwareengineering.observer;

public class ModuleEditor {
    public MoodleManager events;
    private String module;

    public ModuleEditor() {
        this.events = new MoodleManager("change", "message");
    }

    public void changeModule(String module) {
        this.module = module;
        events.notify("change", this.module);
    }

    public void sendMessage() {
        events.notify("message", this.module);
    }
}