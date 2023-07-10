package ch.bfh.java.experiments.softwareengineering.observer;

public interface ModuleListener {
    void update(String eventType, String module);
}