package ch.bfh.java.experiments.softwareengineering.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoodleManager {
    Map<String, List<ModuleListener>> listeners = new HashMap<>();

    public MoodleManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, ModuleListener listener) {
        List<ModuleListener> listenersByType = listeners.get(eventType);
        listenersByType.add(listener);
    }

    public void unsubscribe(String eventType, ModuleListener listener) {
        List<ModuleListener> listenersByType = listeners.get(eventType);
        listenersByType.remove(listener);
    }

    public void notify(String eventType, String module) {
        List<ModuleListener> users = listeners.get(eventType);
        for (ModuleListener listener : users) {
            listener.update(eventType, module);
        }
    }
}
